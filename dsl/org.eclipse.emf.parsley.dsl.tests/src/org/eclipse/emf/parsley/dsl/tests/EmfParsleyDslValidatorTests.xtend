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
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.parsley.EmfParsleyGuiceModule
import org.eclipse.emf.parsley.binding.DialogControlFactory
import org.eclipse.emf.parsley.binding.FormControlFactory
import org.eclipse.emf.parsley.binding.ProposalCreator
import org.eclipse.emf.parsley.dsl.EmfParsleyDslInjectorProvider
import org.eclipse.emf.parsley.dsl.model.ModelPackage
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.factories.TreeFormFactory
import org.eclipse.emf.parsley.ui.provider.DialogFeatureCaptionProvider
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider
import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider
import org.eclipse.emf.parsley.views.AbstractSaveableTreeView
import org.eclipse.ui.IViewPart
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

import static org.eclipse.emf.parsley.dsl.validation.EmfParsleyDslValidator.*

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(EmfParsleyDslInjectorProvider))
class EmfParsleyDslValidatorTests extends EmfParsleyDslAbstractTests {

 	@Inject extension ValidationTestHelper

	@Test
	def void testViewSpecificationIsNotIViewPart() {
		inputsWithErrors.viewSpecificationIsNotIViewPart.parseModel.
		assertTypeMismatch(
			ModelPackage.eINSTANCE.viewSpecification,
			IViewPart,
			Library
		)
	}

	@Test
	def void testNotAnEObjectInEmfFeatureAccess() {
		inputsWithErrors.notAnEObjectInEmfFeatureAccess.parseModel.
		assertTypeMismatch(
			ModelPackage.eINSTANCE.emfFeatureAccess,
			EObject,
			AbstractSaveableTreeView
		)
	}

	@Test
	def void testNotValidModuleExtends() {
		inputsWithErrors.notValidModuleExtends.parseModel.
			assertTypeMismatch(
				ModelPackage.eINSTANCE.extendsClause,
				EmfParsleyGuiceModule,
				Library
			)
	}

	@Test
	def void testNotValidLabelProviderExtends() {
		"labelProvider".
			assertExtendsTypeMismatch(ViewerLabelProvider)
	}

	@Test
	def void testNotValidTableLabelProviderExtends() {
		"tableLabelProvider".
			assertExtendsTypeMismatch(TableColumnLabelProvider)
	}

	@Test
	def void testNotValidFeatureCaptionProviderExtends() {
		"featureCaptionProvider".
			assertExtendsTypeMismatch(FeatureCaptionProvider)
	}

	@Test
	def void testNotValidFormFeatureCaptionProviderExtends() {
		"formFeatureCaptionProvider".
			assertExtendsTypeMismatch(FormFeatureCaptionProvider)
	}

	@Test
	def void testNotValidDialogFeatureCaptionProviderExtends() {
		"dialogFeatureCaptionProvider".
			assertExtendsTypeMismatch(DialogFeatureCaptionProvider)
	}

	@Test
	def void testNotValidFeaturesProviderExtends() {
		"featuresProvider".
			assertExtendsTypeMismatch(FeaturesProvider)
	}

	@Test
	def void testNotValidTableFeaturesProviderExtends() {
		"tableFeaturesProvider".
			assertExtendsTypeMismatch(TableFeaturesProvider)
	}

	@Test
	def void testNotValidFormControlFactoryExtends() {
		"formControlFactory".
			assertExtendsTypeMismatch(FormControlFactory)
	}

	@Test
	def void testNotValidDialogControlFactoryExtends() {
		"dialogControlFactory".
			assertExtendsTypeMismatch(DialogControlFactory)
	}

	@Test
	def void testNotValidProposalsExtends() {
		"proposals".
			assertExtendsTypeMismatch(ProposalCreator)
	}

	@Test
	def void testNotValidViewerContentProviderExtends() {
		"viewerContentProvider".
			assertExtendsTypeMismatch(ViewerContentProvider)
	}

	@Test
	def void testNotValidTreeFormFactoryExtends() {
		"treeFormFactory".
			assertExtendsTypeMismatch(TreeFormFactory)
	}

	def private assertExtendsTypeMismatch(String keyword, Class<?> expectedType) {
		// the wrong actual type is always Library in these tests
		'''
		import org.eclipse.emf.parsley.examples.library.Library
		
		module my.empty {
			«keyword» extends Library {}
		}
		'''.parseModel.assertTypeMismatch(
			ModelPackage.eINSTANCE.extendsClause,
			expectedType,
			Library
		)
	}

	def private assertTypeMismatch(EObject e, EClass eClass, Class<?> expectedType, Class<?> actualType) {
		e.assertError(
			eClass,
			TYPE_MISMATCH,
			"Type mismatch: cannot convert from " + actualType.simpleName +
				" to " + expectedType.simpleName
		)
	}
}