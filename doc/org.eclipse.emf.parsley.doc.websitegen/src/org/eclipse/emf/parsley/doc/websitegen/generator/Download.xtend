package org.eclipse.emf.parsley.doc.websitegen.generator


import com.google.inject.Inject
import java.io.File
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.parsley.doc.websitegen.bootstrap.Body
import org.eclipse.emf.parsley.doc.websitegen.bootstrap.HtmlExtensions
import org.eclipse.emf.parsley.doc.websitegen.bootstrap.PostProcessor
import org.eclipse.emf.parsley.doc.websitegen.xdocgen.DocumentLoad
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
import org.eclipse.xtext.xdoc.xdoc.Document
import org.eclipse.xtext.xdoc.xdoc.ImageRef

import static extension com.google.common.io.Files.*


class Download extends  AbstractXdocBaseWebsite {
	
	static final val MENU_SECTION_MAX_LENGHT=21
	
	new() {
		doc = docLoader.loadDocument(xdocDocumentRootFolder)
	}
	
//	override getStandaloneSetup() {
//		new DocumentationSetup
//	}
	
	def getXdocDocumentRootFolder() {
		'../org.eclipse.emf.parsley.doc/xdoc/download'
	}

	override path() {
		"download.html"
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
			val sources = new File(eResource.URI.trimSegments(1).toFileString, it.path)
			if (!sources.exists)
				throw new IllegalStateException("Referenced Image "+sources.canonicalPath+" does not exist in "+eResource.URI.lastSegment+" line "+NodeModelUtils::getNode(it).startLine)
			val target = new File(targetDir, it.path)
			println(target.canonicalPath)
			
			sources.newInputStreamSupplier.copy(target)
		]
	}
	
	override contents() '''
		<!-- START THE FEATURETTES -->
		<div id="page">  
			<div class="inner">
				«doc.body»
			</div>
			</br></br></br></br></br></br></br>
			<!-- /END THE FEATURETTES -->
		</div>
	'''
	
	
		
	override protected getDocument() {
		doc
	}
}

