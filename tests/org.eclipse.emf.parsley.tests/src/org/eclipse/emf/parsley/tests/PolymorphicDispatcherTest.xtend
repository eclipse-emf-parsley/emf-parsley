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
import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcherExtensions
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.junit.Rule
import org.junit.Test

import static extension org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcherExtensions.*
import static extension org.junit.Assert.*

class PolymorphicDispatcherTest {

	var Object target;

	val static IN_BASE_CLASS = "BaseClass.baseClassFeature"

	val static IN_DERIVED_CLASS = "DerivedClass.baseClassFeature"

	val static SPECIAL_CASE = "SpecialCase"

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	static class Customize_BaseClass_baseClassFeature {
		def String text_BaseClass_baseClassFeature(EStructuralFeature feature) {
			return IN_BASE_CLASS
		}
	}

	static class Customize_DerivedClass_baseClassFeature {
		def String text_DerivedClass_baseClassFeature(EStructuralFeature feature) {
			return IN_DERIVED_CLASS
		}
	}

	static class Customize_TestEClassForFeatureName_FeatureNameSpecialCase {
		// bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=494886
		def String text_TestEClassForFeatureName_EField(EStructuralFeature feature) {
			return SPECIAL_CASE
		}
	}

	static class Customize_TestEClassForFeatureName_FeatureNameSpecialCase2 {
		// bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=494886
		def String text_TestEClassForFeatureName_eField(EStructuralFeature feature) {
			return SPECIAL_CASE
		}
	}

	new() {
		// the following is useless... but it's just to have coverage
		// for the protected constructor 
		new PolymorphicDispatcherExtensions() {
			
		}
	}

	@Test
	def void testGivenNoSuchMethodReturnsNull() {
		target = new Object
		null.assertPolymorphicInvoke(
			testPackage.baseClass, testPackage.baseClass_BaseClassFeature
		)
	}

	@Test
	def void testPolymorphicInvokeWithMethodForTheContainingEClass() {
		target = new Customize_BaseClass_baseClassFeature
		IN_BASE_CLASS.assertPolymorphicInvoke(
			testPackage.baseClass, testPackage.baseClass_BaseClassFeature
		)
	}

	@Test
	def void testPolymorphicInvokeWithBaseClassFeatureCustomizedForDerivedClass() {
		// we have a customization for the feature (inherited from the base class)
		// in the context of the derived class
		target = new Customize_DerivedClass_baseClassFeature
		IN_DERIVED_CLASS.assertPolymorphicInvoke(
			testPackage.derivedClass, testPackage.baseClass_BaseClassFeature
		)
	}

	@Test
	def void testPolymorphicInvokeOnDerivedClassCustomizedForBaseClassFeature() {
		// we have a customization for the feature in the context of the base class
		// and we pass the derived class
		target = new Customize_BaseClass_baseClassFeature
		IN_BASE_CLASS.assertPolymorphicInvoke(
			testPackage.derivedClass, testPackage.baseClass_BaseClassFeature
		)
	}

	@Test
	def void testPolymorphicInvokeOnDerivedDerivedClassCustomizedForBaseClassFeature() {
		// we have a customization for the feature in the context of the base class
		// and we pass the derived derived class
		target = new Customize_BaseClass_baseClassFeature
		IN_BASE_CLASS.assertPolymorphicInvoke(
			testPackage.derivedDerivedClass, testPackage.baseClass_BaseClassFeature
		)
	}

	@Test
	def void testPolymorphicInvokeOnMultipleInheritanceClassCustomizedForBaseClassFeature() {
		// we have a customization for the feature in the context of the base class
		// and we pass a derived class with multiple inheritance (extending also the base class)
		target = new Customize_BaseClass_baseClassFeature
		IN_BASE_CLASS.assertPolymorphicInvoke(
			testPackage.multipleInheritanceClass, testPackage.baseClass_BaseClassFeature
		)
	}

	@Test
	def void testPolymorphicInvokeWithSpecialFeatureName() {
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=494886
		target = new Customize_TestEClassForFeatureName_FeatureNameSpecialCase
		SPECIAL_CASE.assertPolymorphicInvoke(
			testPackage.testEClassForFeatureName, testPackage.testEClassForFeatureName_EField
		)
	}

	@Test
	def void testPolymorphicInvokeWithSpecialFeatureName2() {
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=494886
		target = new Customize_TestEClassForFeatureName_FeatureNameSpecialCase2
		SPECIAL_CASE.assertPolymorphicInvoke(
			testPackage.testEClassForFeatureName, testPackage.testEClassForFeatureName_EField
		)
	}

	def private assertPolymorphicInvoke(Object expected, EClass eClass, EStructuralFeature feature) {
		expected.assertEquals(polymorphicInvoke(eClass, feature))
	}

	def private polymorphicInvoke(EClass eClass, EStructuralFeature feature) {
		target.
			polymorphicInvokeBasedOnFeature(eClass, feature, "text_", feature)
	}
}
