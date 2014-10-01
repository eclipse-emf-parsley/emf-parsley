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
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider
import org.junit.Test

import static extension org.junit.Assert.*

class FeatureCaptionProviderTest extends EmfParsleyAbstractTest {
	
	@Test def void testDefault() {
		val provider = new FeatureCaptionProvider()
		val feature = testPackage.derivedClass_DerivedClassFeature
		feature.name.assertEquals(provider.getText(derivedClass, feature))
	}
	
	@Test def void testDerivedClassFeatureInDerivedClass() {
		val expectedText = "DerivedClass.derivedClassFeature"
		
		val provider = new FeatureCaptionProvider() {
			def String text_DerivedClass_derivedClassFeature(EStructuralFeature feature) {
				return expectedText
			}
		}
		
		expectedText.assertEquals(provider.getText(
			derivedClass, testPackage.derivedClass_DerivedClassFeature
		))
	}

	@Test def void testBaseClassFeatureInBaseClass() {
		val expectedText = "BaseClass.baseClassFeature"
		
		val provider = new FeatureCaptionProvider() {
			def String text_BaseClass_baseClassFeature(EStructuralFeature feature) {
				return expectedText
			}
		}
		
		expectedText.assertEquals(provider.getText(
			baseClass, testPackage.baseClass_BaseClassFeature
		))
	}

	@Test def void testBaseClassFeatureInDerivedClass() {
		val expectedText = "DerivedClass.baseClassFeature"
		
		val provider = new FeatureCaptionProvider() {
			def String text_DerivedClass_baseClassFeature(EStructuralFeature feature) {
				return expectedText
			}
		}
		
		expectedText.assertEquals(provider.getText(
			derivedClass, testPackage.baseClass_BaseClassFeature
		))
	}

}