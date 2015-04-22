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
		generator.genEmptyDslModule("testproject").assertGeneratedContentEqualsTo(
'''
/* testproject Emf Parsley Dsl Module file */
module testproject {
	
}
'''
		)
	}

	@Test
	def void testDslModuleWithFQN() {
		generator.genEmptyDslModule("mypackage.testproject").assertGeneratedContentEqualsTo(
'''
/* mypackage.testproject Emf Parsley Dsl Module file */
module mypackage.testproject {
	
}
'''
		)
	}

	@Test
	def void testDslModuleWithView() {
		generator.genDslModuleWithViewPart("mypackage.testproject", "my.views.MyView", "").assertGeneratedContentEqualsTo(
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

	@Test
	def void testConfiguratorGen() {
		generator.genConfigurator(
			'''
			«generator.genResourceURI("MyClass")»
			«generator.genContents("MyClass")»
			«generator.genEClass("MyClass")»
			«generator.genEStructuralFeature("MyClass")»
			'''
		).assertGeneratedContentEqualsTo(
'''
configurator {
	resourceURI {
		MyClass -> {
			// TODO create and return a org.eclipse.emf.common.util.URI
			return null;
		}
	}
	contents {
		MyClass -> {
			// TODO return the contents from the resource
			// e.g., resource.^contents.get(0)
		}
	}
	eClass {
		MyClass -> {
			// TODO return the EClass to represent
		}
	}
	eStructuralFeature {
		MyClass -> {
			// TODO return the EStructuralFeature to get the elements to represent
		}
	}
}
'''
		)
	}

	@Test
	def void testViewGen() {
		generator.genViewClass("mypackage.testproject", "MyClass", "my.views.MyBaseClass").assertGeneratedContentEqualsTo(
'''
package mypackage.testproject;

public class MyClass extends my.views.MyBaseClass {
	
}
'''
		)
	}
	

	def private assertGeneratedContentEqualsTo(CharSequence actual, CharSequence expected) {
		expected.toString.assertEquals(actual.toString)
	}
}