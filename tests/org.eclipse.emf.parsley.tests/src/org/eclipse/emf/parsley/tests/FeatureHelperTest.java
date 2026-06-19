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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.util.FeatureHelper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class FeatureHelperTest extends AbstractEmfParsleyTest {

	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	/**
	 * An instance to use for testing the creation of a Control
	 * using an AbstractControlFactory
	 */
	protected ClassForControls classForControlsInstance;

	private FeatureHelper featureHelper;

	@Before
	public void setupEObject() {
		classForControlsInstance = fixtures.getTestFactory().createClassForControls();
		featureHelper = getOrCreateInjector().getInstance(FeatureHelper.class);
	}

	@Test
	public void testBooleanFeature() {
		assertBooleanFeature(fixtures.getTestPackage().getClassForControls_BooleanFeature());
	}

	@Test
	public void testBooleanObjectFeature() {
		assertBooleanFeature(fixtures.getTestPackage().getClassForControls_BooleanObjectFeature());
	}

	@Test
	public void testBooleanDataTypeFeature() {
		assertBooleanFeature(fixtures.getTestPackage().getClassForControls_BooleanDataTypeFeature());
	}

	@Test
	public void testBooleanPrimitiveDataTypeFeature() {
		assertBooleanFeature(fixtures.getTestPackage().getClassForControls_BooleanPrimitiveDataTypeFeature());
	}

	@Test
	public void testNonBooleanFeature() {
		assertNotBooleanFeature(fixtures.getTestPackage().getClassForControls_StringFeature());
	}

	@Test
	public void testNonBooleanFeature2() {
		assertNotBooleanFeature(fixtures.getTestPackage().getClassForControls_ReferenceToClassWithName());
	}

	@Test
	public void testNonBooleanDataTypeFeature() {
		assertNotBooleanFeature(fixtures.getTestPackage().getClassForControls_StringDataTypeFeature());
	}

	@Test
	public void testEditableDataType() {
		assertEditable(fixtures.getTestPackage().getClassForControls_StringFeature());
	}

	@Test
	public void testEditable() {
		assertEditable(fixtures.getTestPackage().getClassForControls_ReferenceToClassWithName());
	}

	@Test
	public void testEditableSerializableDataType() {
		assertEditable(fixtures.getTestPackage().getClassForControls_StringDataTypeFeature());
	}

	@Test
	public void testNotEditableDerived() {
		assertNotEditable(fixtures.getTestPackage().getClassForControls_DerivedStringFeature());
	}

	@Test
	public void testNotEditableNotChangeable() {
		assertNotEditable(fixtures.getTestPackage().getClassForControls_UnchangeableStringFeature());
	}

	@Test
	public void testNotEditableUnserializableDataType() {
		assertNotEditable(fixtures.getTestPackage().getClassForControls_UnserializableStringDataTypeFeature());
	}

	@Test
	public void testHasPredefinedProposalsReference() {
		assertPredefinedProposals(fixtures.getTestPackage().getClassForControls_ReferenceToClassWithName());
	}

	@Test
	public void testHasPredefinedProposalsEnum() {
		assertPredefinedProposals(fixtures.getTestPackage().getClassForControls_EnumFeature());
	}

	@Test
	public void testNoPredefinedProposals() {
		assertNotPredefinedProposals(fixtures.getTestPackage().getClassForControls_StringFeature());
	}

	@Test
	public void testIsEnum() {
		assertEnum(fixtures.getTestPackage().getClassForControls_EnumFeature());
	}

	@Test
	public void testIsNotEnum() {
		assertNotEnum(fixtures.getTestPackage().getClassForControls_StringFeature());
	}

	private void assertBooleanFeature(EStructuralFeature feature) {
		assertTrue(featureHelper.isBooleanFeature(feature));
	}

	private void assertNotBooleanFeature(EStructuralFeature feature) {
		assertFalse(featureHelper.isBooleanFeature(feature));
	}

	private void assertEditable(EStructuralFeature feature) {
		assertTrue(featureHelper.isEditable(feature));
	}

	private void assertNotEditable(EStructuralFeature feature) {
		assertFalse(featureHelper.isEditable(feature));
	}

	private void assertPredefinedProposals(EStructuralFeature feature) {
		assertTrue(featureHelper.hasPredefinedProposals(feature));
	}

	private void assertNotPredefinedProposals(EStructuralFeature feature) {
		assertFalse(featureHelper.hasPredefinedProposals(feature));
	}

	private void assertEnum(EStructuralFeature feature) {
		assertTrue(featureHelper.isEnum(feature));
	}

	private void assertNotEnum(EStructuralFeature feature) {
		assertFalse(featureHelper.isEnum(feature));
	}
}
