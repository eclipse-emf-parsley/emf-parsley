/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Label
import org.eclipse.ui.forms.FormColors
import org.eclipse.ui.forms.widgets.FormToolkit
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*
import org.eclipse.emf.parsley.inject.parameters.FormToolkitParameter

class FormFeatureCaptionProviderTest extends AbstractEmfParsleyShellBasedTest {
	
	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()
	
	val testPackage = TestmodelsPackage.eINSTANCE
	
	var FormColors formColors = null
	
	@Before def void createFormColors() {
		syncExecVoid[|formColors = new FormColors(display)]
	}
	
	@Test def void testDefaultText() {
		val provider = new FormFeatureCaptionProvider(formToolkitParam) => [initialize]
		val feature = testPackage.derivedClass_DerivedClassFeature
		feature.name.assertEquals(provider.getText(derivedClass, feature))
	}

	@Test def void testDefaultLabel() {
		val provider = new FormFeatureCaptionProvider(formToolkitParam) => [initialize]
		val feature = testPackage.derivedClass_DerivedClassFeature
		"Derived Class Feature".assertEquals(syncExec[|
			provider.getLabel(shell, derivedClass.createInstance, feature).text
		])
	}
	
	@Test def void testLabelTextDifferentFromText() {
		val expectedText = "DerivedClass.derivedClassFeature"
		val expectedLabelText = "Label.DerivedClass.derivedClassFeature"
		val testFeature = testPackage.derivedClass_DerivedClassFeature
		
		val provider = new FormFeatureCaptionProvider(formToolkitParam) {
			def String text_DerivedClass_derivedClassFeature(EStructuralFeature feature) {
				return expectedText
			}
			
			def Label label_DerivedClass_derivedClassFeature(Composite parent, EStructuralFeature feature) {
				return createLabel(parent, expectedLabelText)
			}
		} => [initialize]
		
		expectedText.assertEquals(provider.getText(derivedClass.createInstance, testFeature))
		expectedLabelText.assertEquals(syncExec[|
			provider.getLabel(shell, derivedClass.createInstance, testFeature).text
		])
	}

	@Test def void testCreatedLabelUsesFormToolkit() {
		val expectedLabelText = "Label.DerivedClass.derivedClassFeature"
		val testFeature = testPackage.derivedClass_DerivedClassFeature
		
		val provider = new FormFeatureCaptionProvider(formToolkitParam) {
			def Label label_DerivedClass_derivedClassFeature(Composite parent, EStructuralFeature feature) {
				return createLabel(parent, expectedLabelText)
			}
		} => [initialize]
		
		// the FormToolkit adapts the label using FormColors
		// so we check that it is actually adapted
		formColors.background.assertEquals(syncExec[|
			provider.getLabel(shell, derivedClass.createInstance, testFeature).background
		])
	}

	@Test def void testDefaultLabelUsesCustomText() {
		val expectedText = "DerivedClass.derivedClassFeature"
		val testFeature = testPackage.derivedClass_DerivedClassFeature
		
		val provider = new FormFeatureCaptionProvider(formToolkitParam) {
			def String text_DerivedClass_derivedClassFeature(EStructuralFeature feature) {
				return expectedText
			}
		} => [initialize]
		
		expectedText.assertEquals(provider.getText(derivedClass.createInstance, testFeature))
		expectedText.assertEquals(syncExec[|
			provider.getLabel(shell, derivedClass.createInstance, testFeature).text
		])
	}

	@Test def void testBaseClassFeatureInBaseClass() {
		val expectedText = "BaseClass.baseClassFeature"
		
		val provider = new FormFeatureCaptionProvider(formToolkitParam) {
			def String text_BaseClass_baseClassFeature(EStructuralFeature feature) {
				return expectedText
			}
		} => [initialize]
		
		expectedText.assertEquals(provider.getText(
			baseClass.createInstance, testPackage.baseClass_BaseClassFeature
		))
	}

	@Test def void testBaseClassFeatureInDerivedClass() {
		val expectedText = "BaseClass.baseClassFeature"
		
		val provider = new FormFeatureCaptionProvider(formToolkitParam) {
			def String text_BaseClass_baseClassFeature(EStructuralFeature feature) {
				return expectedText
			}
		} => [initialize]
		
		expectedText.assertEquals(provider.getText(
			derivedClass.createInstance, testPackage.baseClass_BaseClassFeature
		))
	}

	def private initialize(FormFeatureCaptionProvider provider) {
		provider.injectMembers
	}

	def private getFormToolkitParam() {
		syncExec[new FormToolkitParameter(new FormToolkit(display))]
	}

	def private createInstance(EClass eClass) {
		EcoreUtil.create(eClass)
	}
}