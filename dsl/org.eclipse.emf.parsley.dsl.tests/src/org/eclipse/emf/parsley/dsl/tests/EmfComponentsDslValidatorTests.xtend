package org.eclipse.emf.parsley.dsl.tests

import com.google.inject.Inject
import org.eclipse.emf.parsley.dsl.EmfParsleyDslInjectorProvider
import org.eclipse.emf.parsley.dsl.model.ModelPackage
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

import static org.eclipse.emf.parsley.dsl.validation.EmfParsleyDslValidator.*

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(EmfParsleyDslInjectorProvider))
class EmfComponentsDslValidatorTests extends EmfComponentsDslAbstractTests {

 	@Inject extension ValidationTestHelper

	@Test
	def void testNonEmptyViewsSpecifications() {
		inputsWithErrors.viewSpecificationIsNotIViewPart.parseModel.
			assertError(
				ModelPackage::eINSTANCE.viewSpecification,
				NOT_I_VIEW_PART,
				"Must be an IViewPart"
			)
	}

	@Test
	def void testNotAnEObjectInEmfFeatureAccess() {
		val model = inputsWithErrors.notAnEObjectInEmfFeatureAccess.parseModel
		model.
			assertError(
				ModelPackage::eINSTANCE.emfFeatureAccess,
				NOT_EOBJECT,
				"Must be an EObject derived class"
			)
	}

	@Test
	def void testValidModuleExtends() {
		val model = inputsWithErrors.notValidModuleExtends.parseModel
		model.
			assertError(
				ModelPackage::eINSTANCE.extendsClause,
				NOT_EMFCOMPONENTS_MODULE,
				"Must be an EmfComponentsGuiceModule derived class"
			)
	}
}