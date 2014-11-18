package org.eclipse.emf.parsley.doc.websitegen.generator

import org.eclipse.emf.parsley.doc.websitegen.bootstrap.Body
import org.eclipse.emf.parsley.doc.websitegen.bootstrap.HtmlExtensions
import org.eclipse.emf.parsley.doc.websitegen.bootstrap.PostProcessor
import org.eclipse.emf.parsley.doc.websitegen.bootstrap.XdocExtensions
import com.google.inject.Binder
import com.google.inject.Guice
import com.google.inject.Inject
import com.google.inject.Module
import java.io.File
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
import org.eclipse.xtext.xdoc.XdocRuntimeModule
import org.eclipse.xtext.xdoc.XdocStandaloneSetup
import org.eclipse.xtext.xdoc.xdoc.AbstractSection
import org.eclipse.xtext.xdoc.xdoc.Document
import org.eclipse.xtext.xdoc.xdoc.ImageRef
import org.eclipse.emf.parsley.doc.websitegen.xdocgen.DocumentLoad

import static extension com.google.common.io.Files.*
import org.eclipse.xtext.xdoc.xdoc.Chapter
import org.eclipse.xtext.xdoc.xdoc.Part

class Documentation extends AbstractXdocBaseWebsite {
	
	static final val MENU_SECTION_MAX_LENGHT=21
	
	new() {
		doc = docLoader.loadDocument(xdocDocumentRootFolder)
	}
	
//	override getStandaloneSetup() {
//		new DocumentationSetup
//	}
	
	def getXdocDocumentRootFolder() {
		'../org.eclipse.emf.parsley.doc/xdoc/documentation'
	}

	override path() {
		"documentation.html"
	}
	
	val Document doc
	
	@Inject DocumentLoad docLoader
	@Inject extension Body
	@Inject extension HtmlExtensions
	@Inject PostProcessor processor
	
	override website() {
		processor.postProcess(super.website())
	}
	
	override generateTo(File targetDir) {
		super.generateTo(targetDir)
		copyImages(doc, targetDir)
	}
	
	def copyImages(Document doc, File targetDir) {
		val iter = EcoreUtil::getAllContents(doc.eResource.resourceSet, true)
		iter.filter(typeof(ImageRef)).forEach[
			val source = new File(eResource.URI.trimSegments(1).toFileString, it.path)
			if (!source.exists)
				throw new IllegalStateException("Referenced Image "+source.canonicalPath+" does not exist in "+eResource.URI.lastSegment+" line "+NodeModelUtils::getNode(it).startLine)
			val target = new File(targetDir, it.path)
			println(target.canonicalPath)
			
			source.newInputStreamSupplier.copy(target)
		]
	}
	
	override contents() '''
		<!-- Marketing messaging and featurettes
		================================================== -->
		<!-- Wrap the rest of the page in another container to center all the content. -->
		<div class="containerdoc marketing">
			<!-- SIDEBAR -->
			<div style="position: fixed;" class="col-md-2">
				«doc.menu»
			</div>
					
			<!-- START THE FEATURETTES -->
			«doc.body»
			</br></br></br></br></br></br></br>
			<!-- /END THE FEATURETTES -->
		</div>
	'''
	
	
	
	def menu(Document doc) '''
		<ul class="dropdown-menu" style="animation: myfirst 1.5s;margin: 0px; max-width: 250px; display: block;box-shadow: 0 0px 7px rgba(0,0,0,.175);">
			«FOR chapter : doc.chapters»
				<li class="activemenu"><a tabindex="-1" href="#par">«chapter.title.toHtmlText»</a></li>
				«FOR section : chapter.subSections»
					<li><a class="submenu" tabindex="-1" href="#par">«section.title.toHtmlText.limitLenght(MENU_SECTION_MAX_LENGHT)»</a></li>
				«ENDFOR»
				<li class="divider"></li>
			«ENDFOR»
			«FOR part : doc.parts»
				«FOR chapter : part.chapters»
					<li«printActiveMenu()»><a tabindex="-1" href="#par">«chapter.title.toHtmlText»</a></li>
					«FOR section : chapter.subSections»
						<li><a class="submenu" tabindex="-1" href="#par">«section.title.toHtmlText.limitLenght(21)»</a></li>
					«ENDFOR»
					<li class="divider"></li>
				«ENDFOR»
			«ENDFOR»			
			«additionalLinks»
		</ul>
	'''
	
	boolean first=true
	
	def printActiveMenu(){
		if(first){
			first=false
			''' class="activemenu" '''	
		}	
	}
	
	def limitLenght(CharSequence str, int maxLenght){
		if(str.length>maxLenght){
			var whiteSpace=str.toString.indexOf(' ',maxLenght)
			if(whiteSpace>0){
				return str.subSequence(0,whiteSpace)+'''</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'''+str.subSequence(whiteSpace+1,str.length)
			}
		}
		str
	}
	
	def additionalLinks() '''
		<li>&nbsp;</li>
		<li style="color : #333;">Additional Resources
		<li><a href="documentation/2.7.0/Xtext%20Documentation.pdf">Documentation <img src="images/pdf_icon.gif"></a>
		<li><a href="http://download.eclipse.org/modeling/tmf/xtext/javadoc/2.7/">API Documentation (JavaDoc)</a>
	'''
	
	override protected getDocument() {
		doc
	}
}

//class DocumentationSetup extends XdocStandaloneSetup implements Module {
//	
//	override createInjector() {
//		val module = new XdocRuntimeModule
//		Guice::createInjector(module, this)
//	}
//	
//	override configure(Binder binder) {
//		binder.bind(typeof(Body)).to(typeof(DocumentationBody))
//	}
//}

//class DocumentationBody extends Body {
//	@Inject extension XdocExtensions
//	@Inject extension HtmlExtensions
//	
//	override h1(AbstractSection chapter) '''
//		<!-- chapter -->
//		<section id="«chapter.hrefId»" style="padding-top: 68px; margin-top: -68px;">
//			<div class="row">
//				<div class="span8 offset3">
//					<h1 style="padding-top: 30px;">
//						«chapter.title.toHtmlText»
//					</h1>
//					<hr style="margin-top: 5px; margin-bottom: 5px;">
//					«FOR content : chapter.contents»
//						«content?.toHtmlParagraph»
//					«ENDFOR»
//					«FOR section: chapter.sections»
//						«section.h2»
//					«ENDFOR»
//				</div>
//			</div>
//		</section>
//	'''
//	
//	override h2(AbstractSection section) '''
//		<!--  section -->
//		<section id="«section.hrefId»" style="padding-top: 68px; margin-top: -68px;">
//		<h2 style="padding-top: 15px;">«section.title.toHtmlText»</h2>
//		«FOR content : section.contents»
//			«content.toHtmlParagraph»
//		«ENDFOR»
//		«FOR subsection: section.sections»
//			«subsection.h3plus(3)»
//		«ENDFOR»
//		</section>
//	'''
//	
//	
//}