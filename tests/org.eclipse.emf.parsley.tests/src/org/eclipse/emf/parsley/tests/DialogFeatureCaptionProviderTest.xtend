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
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.eclipse.emf.parsley.ui.provider.DialogFeatureCaptionProvider
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Label
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*

class DialogFeatureCaptionProviderTest extends AbstractEmfParsleyShellBasedTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	val static DERIVED_CLASS_FEATURE_PROP_DESCRIPTION = "Derived Class Feature"

	@Test def void testDefaultTextWithInstance() {
		val provider = createCaptionProvider
		val feature = testPackage.derivedClass_DerivedClassFeature
		DERIVED_CLASS_FEATURE_PROP_DESCRIPTION.
			assertEquals(provider.getText(derivedClass.createInstance, feature))
	}
	
	@Test def void testDefaultText() {
		val provider = createCaptionProvider
		val feature = testPackage.derivedClass_DerivedClassFeature
		feature.name.assertEquals(provider.getText(derivedClass, feature))
	}

	@Test def void testCustomDefaultText() {
		// custom defaultText should have precedence over the
		// delegated FeatureCaptionProvider.defaultText
		val provider = new DialogFeatureCaptionProvider() {
			override protected defaultText(EStructuralFeature feature) {
				"default"
			}
		}.injectMembers
		val feature = testPackage.derivedClass_DerivedClassFeature
		"default".assertEquals(provider.getText(derivedClass, feature))
	}

	@Test def void testDelegatedCustomGetText() {
		// the DialogFeatureCaptionProvider has no customization,
		// but the custom FeatureCaptionProvider has a customization
		// and we check that we delegate to it
		val provider = createCaptionProvider
		provider.delegate = new FeatureCaptionProvider() {
			def String text_DerivedClass_derivedClassFeature(EStructuralFeature feature) {
				return "custom"
			}
		}.injectMembers
		val feature = testPackage.derivedClass_DerivedClassFeature
		"custom".assertEquals(provider.getText(derivedClass.createInstance, feature))
	}

	@Test def void testDefaultLabel() {
		val provider = createCaptionProvider
		val feature = testPackage.derivedClass_DerivedClassFeature
		DERIVED_CLASS_FEATURE_PROP_DESCRIPTION.assertEquals(syncExec[|
			provider.getLabel(shell, derivedClass.createInstance, feature).text
		])
	}
	
	@Test def void testLabelTextDifferentFromText() {
		val expectedText = "DerivedClass.derivedClassFeature"
		val expectedLabelText = "Label.DerivedClass.derivedClassFeature"
		val testFeature = testPackage.derivedClass_DerivedClassFeature
		
		val provider = new DialogFeatureCaptionProvider() {
			def String text_DerivedClass_derivedClassFeature(EStructuralFeature feature) {
				return expectedText
			}
			
			def Label label_DerivedClass_derivedClassFeature(Composite parent, EStructuralFeature feature) {
				return createLabel(parent, expectedLabelText)
			}
		}
		
		expectedText.assertEquals(provider.getText(derivedClass.createInstance, testFeature))
		expectedLabelText.assertEquals(syncExec[|
			provider.getLabel(shell, derivedClass.createInstance, testFeature).text
		])
	}

	@Test def void testDefaultLabelUsesCustomText() {
		val expectedText = "DerivedClass.derivedClassFeature"
		val testFeature = testPackage.derivedClass_DerivedClassFeature
		
		val provider = new DialogFeatureCaptionProvider() {
			def String text_DerivedClass_derivedClassFeature(EStructuralFeature feature) {
				return expectedText
			}
		}
		
		expectedText.assertEquals(provider.getText(derivedClass.createInstance, testFeature))
		expectedText.assertEquals(syncExec[|
			provider.getLabel(shell, derivedClass.createInstance, testFeature).text
		])
	}

	@Test def void testBaseClassFeatureInBaseClass() {
		val expectedText = "BaseClass.baseClassFeature"
		
		val provider = new DialogFeatureCaptionProvider() {
			def String text_BaseClass_baseClassFeature(EStructuralFeature feature) {
				return expectedText
			}
		}
		
		expectedText.assertEquals(provider.getText(
			baseClass.createInstance, testPackage.baseClass_BaseClassFeature
		))
	}

	@Test def void testBaseClassFeatureInDerivedClass() {
		val expectedText = "BaseClass.baseClassFeature"
		
		val provider = new DialogFeatureCaptionProvider() {
			def String text_BaseClass_baseClassFeature(EStructuralFeature feature) {
				return expectedText
			}
		}
		
		expectedText.assertEquals(provider.getText(
			derivedClass.createInstance, testPackage.baseClass_BaseClassFeature
		))
	}

	@Test def void testNoPropertyDescriptionFallsBackToFeatureName() {
		val provider = createCaptionProvider
		
		// we simulate the absence of a property description specifying
		// a feature that is not present in the object's EClass
		val feature = testPackage.testContainer_ClassesForControls
		feature.name.assertEquals(provider.getText(
			derivedClass.createInstance, feature
		))
	}

	def private createCaptionProvider() {
		getOrCreateInjector.getInstance(DialogFeatureCaptionProvider)
	}

	def private createInstance(EClass eClass) {
		EcoreUtil.create(eClass)
	}
}