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

import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider
import org.junit.Test

import static extension org.junit.Assert.*
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Composite
import org.eclipse.ui.forms.widgets.FormToolkit
import org.junit.Before
import org.eclipse.ui.forms.FormColors

class FormFeatureCaptionProviderTest extends AbstractShellBasedTest {
	
	val testPackage = TestmodelsPackage.eINSTANCE
	
	var FormColors formColors = null
	
	@Before def void createFormColors() {
		syncExecVoid[|formColors = new FormColors(display)]
	}
	
	@Test def void testDefaultText() {
		val provider = new FormFeatureCaptionProvider() => [initialize]
		val feature = testPackage.derivedClass_DerivedClassFeature
		feature.name.assertEquals(provider.getText(feature))
	}

	@Test def void testDefaultLabel() {
		val provider = new FormFeatureCaptionProvider() => [initialize]
		val feature = testPackage.derivedClass_DerivedClassFeature
		feature.name.assertEquals(syncExec[|
			provider.getLabel(shell, feature).text
		])
	}
	
	@Test def void testLabelTextDifferentFromText() {
		val expectedText = "DerivedClass.derivedClassFeature"
		val expectedLabelText = "Label.DerivedClass.derivedClassFeature"
		val testFeature = testPackage.derivedClass_DerivedClassFeature
		
		val provider = new FormFeatureCaptionProvider() {
			def String text_DerivedClass_derivedClassFeature(EStructuralFeature feature) {
				return expectedText
			}
			
			def Label label_DerivedClass_derivedClassFeature(Composite parent, EStructuralFeature feature) {
				return createLabel(parent, expectedLabelText)
			}
		} => [initialize]
		
		expectedText.assertEquals(provider.getText(testFeature))
		expectedLabelText.assertEquals(syncExec[|
			provider.getLabel(shell, testFeature).text
		])
	}

	@Test def void testCreatedLabelUsesFormToolkit() {
		val expectedLabelText = "Label.DerivedClass.derivedClassFeature"
		val testFeature = testPackage.derivedClass_DerivedClassFeature
		
		val provider = new FormFeatureCaptionProvider() {
			def Label label_DerivedClass_derivedClassFeature(Composite parent, EStructuralFeature feature) {
				return createLabel(parent, expectedLabelText)
			}
		} => [initialize]
		
		// the FormToolkit adapts the label using FormColors
		// so we check that it is actually adapted
		formColors.background.assertEquals(syncExec[|
			provider.getLabel(shell, testFeature).background
		])
	}

	@Test def void testDefaultLabelUsesCustomText() {
		val expectedText = "DerivedClass.derivedClassFeature"
		val testFeature = testPackage.derivedClass_DerivedClassFeature
		
		val provider = new FormFeatureCaptionProvider() {
			def String text_DerivedClass_derivedClassFeature(EStructuralFeature feature) {
				return expectedText
			}
		} => [initialize]
		
		expectedText.assertEquals(provider.getText(testFeature))
		expectedText.assertEquals(syncExec[|
			provider.getLabel(shell, testFeature).text
		])
	}

	@Test def void testBaseClassFeatureInBaseClass() {
		val expectedText = "BaseClass.baseClassFeature"
		
		val provider = new FormFeatureCaptionProvider() {
			def String text_BaseClass_baseClassFeature(EStructuralFeature feature) {
				return expectedText
			}
		} => [initialize]
		
		expectedText.assertEquals(provider.getText(testPackage.baseClass_BaseClassFeature))
	}

	def private initialize(FormFeatureCaptionProvider provider) {
		provider.delegate = new FeatureCaptionProvider
		syncExecVoid[|
			provider.formToolkit = new FormToolkit(display)
		]
	}

}