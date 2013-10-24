package org.eclipse.emf.parsley.dsl.ui.wizard

import org.eclipse.emf.parsley.generator.common.EmfParsleyProjectFilesGenerator

class EmfParsleyDslNewProjectFiles extends EmfParsleyProjectFilesGenerator {
	
	def exampleDslFile(String projectName)
'''
import java.util.*

/* «projectName» Emf Parsley Dsl Module file */
module «projectName» {
	
}
'''

	def dslFileWithView(String projectName, String viewId)
'''
import java.util.*

/* «projectName» Emf Parsley Dsl Module file */
module «projectName» {
	
	parts {
		viewpart «projectName» {
			viewname "«projectName.prefixFromProject»"
			viewclass «viewId»
		}
	}
	
}
'''
}