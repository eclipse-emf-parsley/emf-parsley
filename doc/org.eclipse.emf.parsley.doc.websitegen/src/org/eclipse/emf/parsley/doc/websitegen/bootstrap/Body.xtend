package org.eclipse.emf.parsley.doc.websitegen.bootstrap

import com.google.inject.Inject
import org.eclipse.xtext.xdoc.xdoc.AbstractSection

class Body {
	
	@Inject extension XdocExtensions

	@Inject extension HtmlExtensions
	
	@Inject extension TargetPaths
	
	
	def body(AbstractSection rootSection){
		body(rootSection,8)
	}
	
	def body(AbstractSection rootSection, int columns) '''
			</br>
			<!-- scrollup ICON -->
			<a class="scrollup" href="#top"></a>
			<!-- scrollup ICON -->
			«FOR chapter: rootSection.sections.filter[!targetRootElement]»
				«chapter.h1(columns)»
				«printHr(rootSection.sections.filter[!targetRootElement].size)»
			«ENDFOR»
	'''
	
	var i=1;
	
	
	def printHr(int j){
		if(i<j){
			i++
			''' <hr style="width:64.6%;margin-bottom:28px;margin-top:30px;" class="col-md-8 col-md-offset-3"> '''	
		}	
	}

	

	def h1(AbstractSection section,int columns) '''
		<div class="row featurette">
			<div class="col-md-«columns» col-md-offset-3">
				<h1 id="par" class="featurette-heading text-parsley">«section.title.toHtmlText»</h1>
				«FOR content : section.contents»
					«content.toHtmlParagraph»
				«ENDFOR»
				«FOR subSection: section.sections»
			«subSection.h2»
				«ENDFOR»
			</div>
		</div>
	'''

	
	def h2(AbstractSection section) '''
		</br>
		<div >
			<h2 id="par" class="featurette-heading text-parsley1">«section.title.toHtmlText»</h2>
			«FOR content : section.contents»
		«content.toHtmlParagraph»
			«ENDFOR»
			«FOR subSection: section.sections»
				«subSection.h3»
			«ENDFOR»
		</div>
	'''
	
	def CharSequence h3(AbstractSection section) '''
		</br>
		<h3 class="featurette-heading text-parsley2">«section.title.toHtmlText»<a id="«section.hrefId»"></a></h3>
		«FOR content : section.contents»
			«content.toHtmlParagraph»
		«ENDFOR»
		«FOR subSection: section.sections»
				«subSection.h3»
			«ENDFOR»
	'''
}