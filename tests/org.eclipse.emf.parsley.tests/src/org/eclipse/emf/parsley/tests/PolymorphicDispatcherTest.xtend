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
import org.junit.Test

import static extension org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcherExtensions.*
import static extension org.junit.Assert.*

class PolymorphicDispatcherTest extends EmfParsleyAbstractTest {

	var Object target;
	
	val static IN_BASE_CLASS = "BaseClass.baseClassFeature"

	val static IN_DERIVED_CLASS = "DerivedClass.baseClassFeature"
	
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

	def private assertPolymorphicInvoke(Object expected, EClass eClass, EStructuralFeature feature) {
		expected.assertEquals(polymorphicInvoke(eClass, feature))
	}
	
	def private polymorphicInvoke(EClass eClass, EStructuralFeature feature) {
		target.
			polymorphicInvokeBasedOnFeature(eClass, feature, "text_", feature)
	}
}
