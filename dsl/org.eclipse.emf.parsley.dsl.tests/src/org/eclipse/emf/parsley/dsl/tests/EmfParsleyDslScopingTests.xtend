package org.eclipse.emf.parsley.dsl.tests

import com.google.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.parsley.dsl.EmfParsleyDslInjectorProvider
import org.eclipse.emf.parsley.dsl.model.ModelPackage
import org.eclipse.emf.parsley.dsl.scoping.EmfParsleyDslXbaseBatchScopeProvider
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.*

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(EmfParsleyDslInjectorProvider))
class EmfParsleyDslScopingTests extends EmfParsleyDslAbstractTests {

	@Inject extension EmfParsleyDslXbaseBatchScopeProvider

	@Test
	def void testFeaturesForLabelSpecifications() {
		inputs.propertyDescriptionSpecifications.parseModel.
			module.propertyDescriptionProvider.labelSpecifications.head.
				assertScope
				(ModelPackage::eINSTANCE.propertyDescriptionSpecification_Feature,
					"name, books, borrowers, writers, employees, stock, branches, parentBranch, people, address, class"
				)
			
	} 	

	def private assertScope(EObject o, EReference ref, String expected) {
		val listExpected = expected.split(", ").toList
		val scope = o.getScope(ref).allElements.map[name]
		for (exp : listExpected) {
			assertTrue("not found: " + exp, scope.exists[exp == it.toString])
		}
		for (sc : scope) {
			assertTrue("not expected: " + sc.toString, listExpected.exists[sc.toString == it])
		}
	}
}