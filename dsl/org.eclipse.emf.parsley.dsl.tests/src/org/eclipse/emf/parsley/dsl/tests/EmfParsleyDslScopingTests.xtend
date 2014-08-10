/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.tests

import com.google.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.parsley.dsl.EmfParsleyDslInjectorProvider
import org.eclipse.emf.parsley.dsl.model.ModelPackage
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.scoping.IScopeProvider
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.*

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(EmfParsleyDslInjectorProvider))
class EmfParsleyDslScopingTests extends EmfParsleyDslAbstractTests {

	@Inject extension IScopeProvider

	@Test
	def void testFeaturesForLabelSpecifications() {
		inputs.tableLabelProvider.parseModel.
			module.tableLabelProvider.labelSpecifications.head.
				assertScope
				(ModelPackage::eINSTANCE.featureAssociatedExpression_Feature,
					"name, books, borrowers, writers, employees, stock, branches, parentBranch, people, address, class"
				)
			
	}

	@Test
	def void testFeaturesForPropertyDescriptionSpecifications() {
		inputs.featureCaptionProvider.parseModel.
			module.featureCaptionProvider.specifications.head.
				assertScope
				(ModelPackage::eINSTANCE.featureAssociatedExpression_Feature,
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