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
package org.eclipse.emf.parsley.tests;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcherExtensions;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class PolymorphicDispatcherTest {

	private Object target;

	private static final String IN_BASE_CLASS = "BaseClass.baseClassFeature";

	private static final String IN_DERIVED_CLASS = "DerivedClass.baseClassFeature";

	private static final String SPECIAL_CASE = "SpecialCase";

	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	public static class Customize_BaseClass_baseClassFeature {
		public String text_BaseClass_baseClassFeature(EStructuralFeature feature) {
			return IN_BASE_CLASS;
		}
	}

	public static class Customize_DerivedClass_baseClassFeature {
		public String text_DerivedClass_baseClassFeature(EStructuralFeature feature) {
			return IN_DERIVED_CLASS;
		}
	}

	public static class Customize_TestEClassForFeatureName_FeatureNameSpecialCase {
		// bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=494886
		public String text_TestEClassForFeatureName_EField(EStructuralFeature feature) {
			return SPECIAL_CASE;
		}
	}

	public static class Customize_TestEClassForFeatureName_FeatureNameSpecialCase2 {
		// bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=494886
		public String text_TestEClassForFeatureName_eField(EStructuralFeature feature) {
			return SPECIAL_CASE;
		}
	}

	@Test
	public void testGivenNoSuchMethodReturnsNull() {
		target = new Object();
		assertPolymorphicInvoke(null,
			fixtures.getTestPackage().getBaseClass(), fixtures.getTestPackage().getBaseClass_BaseClassFeature()
		);
	}

	@Test
	public void testPolymorphicInvokeWithMethodForTheContainingEClass() {
		target = new Customize_BaseClass_baseClassFeature();
		assertPolymorphicInvoke(IN_BASE_CLASS,
			fixtures.getTestPackage().getBaseClass(), fixtures.getTestPackage().getBaseClass_BaseClassFeature()
		);
	}

	@Test
	public void testPolymorphicInvokeWithBaseClassFeatureCustomizedForDerivedClass() {
		// we have a customization for the feature (inherited from the base class)
		// in the context of the derived class
		target = new Customize_DerivedClass_baseClassFeature();
		assertPolymorphicInvoke(IN_DERIVED_CLASS,
			fixtures.getTestPackage().getDerivedClass(), fixtures.getTestPackage().getBaseClass_BaseClassFeature()
		);
	}

	@Test
	public void testPolymorphicInvokeOnDerivedClassCustomizedForBaseClassFeature() {
		// we have a customization for the feature in the context of the base class
		// and we pass the derived class
		target = new Customize_BaseClass_baseClassFeature();
		assertPolymorphicInvoke(IN_BASE_CLASS,
			fixtures.getTestPackage().getDerivedClass(), fixtures.getTestPackage().getBaseClass_BaseClassFeature()
		);
	}

	@Test
	public void testPolymorphicInvokeOnDerivedDerivedClassCustomizedForBaseClassFeature() {
		// we have a customization for the feature in the context of the base class
		// and we pass the derived derived class
		target = new Customize_BaseClass_baseClassFeature();
		assertPolymorphicInvoke(IN_BASE_CLASS,
			fixtures.getTestPackage().getDerivedDerivedClass(), fixtures.getTestPackage().getBaseClass_BaseClassFeature()
		);
	}

	@Test
	public void testPolymorphicInvokeOnMultipleInheritanceClassCustomizedForBaseClassFeature() {
		// we have a customization for the feature in the context of the base class
		// and we pass a derived class with multiple inheritance (extending also the base class)
		target = new Customize_BaseClass_baseClassFeature();
		assertPolymorphicInvoke(IN_BASE_CLASS,
			fixtures.getTestPackage().getMultipleInheritanceClass(), fixtures.getTestPackage().getBaseClass_BaseClassFeature()
		);
	}

	@Test
	public void testPolymorphicInvokeWithSpecialFeatureName() {
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=494886
		target = new Customize_TestEClassForFeatureName_FeatureNameSpecialCase();
		assertPolymorphicInvoke(SPECIAL_CASE,
			fixtures.getTestPackage().getTestEClassForFeatureName(), fixtures.getTestPackage().getTestEClassForFeatureName_EField()
		);
	}

	@Test
	public void testPolymorphicInvokeWithSpecialFeatureName2() {
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=494886
		target = new Customize_TestEClassForFeatureName_FeatureNameSpecialCase2();
		assertPolymorphicInvoke(SPECIAL_CASE,
			fixtures.getTestPackage().getTestEClassForFeatureName(), fixtures.getTestPackage().getTestEClassForFeatureName_EField()
		);
	}

	private void assertPolymorphicInvoke(Object expected, EClass eClass, EStructuralFeature feature) {
		assertEquals(expected, polymorphicInvoke(eClass, feature));
	}

	private Object polymorphicInvoke(EClass eClass, EStructuralFeature feature) {
		return PolymorphicDispatcherExtensions.polymorphicInvokeBasedOnFeature(
			target, eClass, feature, "text_", feature);
	}
}
