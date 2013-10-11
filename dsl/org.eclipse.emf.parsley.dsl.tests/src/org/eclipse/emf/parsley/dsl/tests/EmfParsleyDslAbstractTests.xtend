package org.eclipse.emf.parsley.dsl.tests

import com.google.inject.Inject
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.parsley.dsl.model.Model
import org.eclipse.emf.parsley.dsl.tests.inputs.TestInputs
import org.eclipse.emf.parsley.dsl.tests.inputs.TestInputsWithErrors
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.BeforeClass
import org.junit.runner.RunWith

import static extension org.junit.Assert.*
import org.eclipse.emf.parsley.dsl.EmfParsleyDslInjectorProvider

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(EmfParsleyDslInjectorProvider))
class EmfParsleyDslAbstractTests {
	
	@Inject
	protected TestInputs inputs
	
	@Inject
	protected TestInputsWithErrors inputsWithErrors
	
	@Inject extension ParseHelper<Model>
 
    @Inject extension ValidationTestHelper
    
    @BeforeClass
	def static void setCRLF() {
		System::setProperty("line.separator", "\n")
	}
    
    def parseAndAssertNoError(CharSequence s) {
		var ts = s.parse
		ts.assertNoErrors
		ts
	}
	
	def parseAndAssertError(CharSequence s, EClass objectType, String code,
			String messagePart) {
		s.parse.assertError(objectType, code, messagePart)			
	}
	
	def parseModel(CharSequence s) {
		s.parse
	}
	
	def parseAndAssertErrors(CharSequence s) {
		(s.parse.validate.size > 0).assertTrue
	}
	
	def module(CharSequence s) {
		s.parseAndAssertNoError.module
	}
	
	def assertEqualsStrings(Object expected, Object actual) {
		assertEquals("" + expected, "" + actual)
	}

	def partSpecification(CharSequence s) {
		s.module.partsSpecifications.parts.head
	}
}