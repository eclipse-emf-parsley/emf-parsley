package org.eclipse.emf.parsley.dsl.tests

import org.eclipse.emf.parsley.dsl.ui.wizard.EmfParsleyDslNewProjectFiles
import org.junit.Test

import static extension org.junit.Assert.*

/**
 * @author Lorenzo Bettini - Initial contribution and API
 */
class EmfParsleyDslNewProjectFilesTests {
	
	val generator = new EmfParsleyDslNewProjectFiles
	
	@Test
	def void testDslModuleWithoutAdditionalContents() {
		generator.exampleDslFile("testproject").assertGeneratedContentEqualsTo(
'''
/* testproject Emf Parsley Dsl Module file */
module testproject {
	
}
'''
		)
	}

	@Test
	def void testDslModuleWithFQN() {
		generator.exampleDslFile("mypackage.testproject").assertGeneratedContentEqualsTo(
'''
/* mypackage.testproject Emf Parsley Dsl Module file */
module mypackage.testproject {
	
}
'''
		)
	}

	@Test
	def void testDslModuleWithView() {
		generator.dslFileWithView("mypackage.testproject", "my.views.MyView").assertGeneratedContentEqualsTo(
'''
import my.views.MyView

/* mypackage.testproject Emf Parsley Dsl Module file */
module mypackage.testproject {
	
	parts {
		viewpart mypackage.testproject {
			viewname "Testproject"
			viewclass MyView
		}
	}
}
'''
		)
	}

	def private assertGeneratedContentEqualsTo(CharSequence actual, CharSequence expected) {
		expected.toString.assertEquals(actual.toString)
	}
}