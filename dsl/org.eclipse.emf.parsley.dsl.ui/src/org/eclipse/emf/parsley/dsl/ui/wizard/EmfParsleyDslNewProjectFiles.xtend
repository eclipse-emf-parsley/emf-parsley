package org.eclipse.emf.parsley.dsl.ui.wizard

class EmfParsleyDslNewProjectFiles {
	
	def exampleDslFile(String projectName)
'''
import java.util.*

/* «projectName» Emf Components Dsl Module file */
module «projectName» {
	
}
'''
}