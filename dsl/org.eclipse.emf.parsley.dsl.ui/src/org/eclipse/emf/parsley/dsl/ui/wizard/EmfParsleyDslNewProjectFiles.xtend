package org.eclipse.emf.parsley.dsl.ui.wizard

class EmfParsleyDslNewProjectFiles {
	
	def exampleDslFile(String projectName)
'''
import java.util.*

/* «projectName» Emf Components Dsl Module file */
module «projectName» {
	
}
'''

	def dslFileWithView(String projectName, String viewId)
'''
import java.util.*

/* «projectName» Emf Components Dsl Module file */
module «projectName» {
	
	parts {
		viewpart «projectName» {
			viewname "«projectName»"
			viewclass «viewId»
		}
	}
	
}
'''
}