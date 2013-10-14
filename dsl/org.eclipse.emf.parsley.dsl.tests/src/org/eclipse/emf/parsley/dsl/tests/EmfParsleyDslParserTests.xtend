package org.eclipse.emf.parsley.dsl.tests

import com.google.inject.Inject
import org.eclipse.emf.parsley.dsl.EmfParsleyDslInjectorProvider
import org.eclipse.emf.parsley.dsl.model.ModelPackage
import org.eclipse.xtext.diagnostics.Diagnostic
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(EmfParsleyDslInjectorProvider))
class EmfParsleyDslParserTests extends EmfParsleyDslAbstractTests {

 	@Inject extension ValidationTestHelper

	@Test
	def void testEmptyModule() {
		inputs.emptyModule.parseAndAssertNoError
	}

	@Test
	def void testModuleWithExtends() {
		inputs.moduleWithExtends.parseAndAssertNoError
	}

	@Test
	def void testEmptyLabelProvider() {
		inputs.emptyLabelProvider.parseAndAssertNoError
	}

	@Test
	def void testEmptyLabelSpecifications() {
		inputs.emptyLabelSpecifications.parseAndAssertNoError
	}

	@Test
	def void testLabelSpecifications() {
		inputs.labelSpecifications.parseAndAssertNoError
	}
	
	@Test
	def void testDuplicateLabelSpecifications() {
		inputsWithErrors.duplicateLabelSpecifications.parseAndAssertErrors
	}
	
	@Test
	def void testPropertyDescriptionSpecifications() {
		inputs.propertyDescriptionSpecifications.parseAndAssertNoError
	}

	@Test
	def void testFormPropertyDescriptionSpecifications() {
		inputs.formPropertyDescriptionSpecifications.parseAndAssertNoError
	}

	@Test
	def void testDialogPropertyDescriptionSpecifications() {
		inputs.dialogPropertyDescriptionSpecifications.parseAndAssertNoError
	}

	@Test
	def void testWrongFeatureLabelSpecifications() {
		val model = inputsWithErrors.wrongPropertyDescriptionSpecifications.parseModel
		model.assertError(
			ModelPackage::eINSTANCE.propertyDescriptionSpecification,
			Diagnostic::LINKING_DIAGNOSTIC,
			"newArrayList"
		)
		model.assertError(
			ModelPackage::eINSTANCE.propertyDescriptionSpecification,
			Diagnostic::LINKING_DIAGNOSTIC,
			"getBooks"
		)
	}
	
	@Test
	def void testFeaturesSpecifications() {
		inputs.featuresSpecifications.parseAndAssertNoError
	}

	@Test
	def void testFormControlSpecifications() {
		inputs.formControlSpecifications.parseAndAssertNoError
	}

	@Test
	def void testProposalsSpecifications() {
		inputs.proposalsSpecifications.parseAndAssertNoError
	}

	@Test
	def void testViewerContentProviderSpecifications() {
		inputs.viewerContentProviderSpecifications.parseAndAssertNoError
	}

	@Test
	def void testEmptyViewsSpecifications() {
		inputs.emptyViewsSpecifications.parseAndAssertNoError
	}

	@Test
	def void testNonEmptyViewsSpecifications() {
		inputs.nonEmptyViewsSpecifications.parseAndAssertNoError
	}

	@Test
	def void testMultipleViewsSpecifications() {
		inputs.multipleViewsSpecifications.parseAndAssertNoError
	}
}