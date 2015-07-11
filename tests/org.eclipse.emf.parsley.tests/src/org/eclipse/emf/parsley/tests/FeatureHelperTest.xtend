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

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.parsley.binding.FeatureHelper
import org.eclipse.emf.parsley.binding.ProposalCreator
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.eclipse.jface.viewers.ILabelProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*

class FeatureHelperTest extends AbstractEmfParsleyTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()
	
	/**
	 * An instance to use for testing the creation of a Control
	 * using an AbstractControlFactory
	 */
	var protected ClassForControls classForControlsInstance

	var private FeatureHelper featureHelper
	
	@Before
	def void setupEObject() {
		classForControlsInstance = testFactory.createClassForControls
		featureHelper = getOrCreateInjector.getInstance(FeatureHelper)
	}

	@Test def void testBooleanFeature() {
		assertBooleanFeature(testPackage.classForControls_BooleanFeature)
	}

	@Test def void testBooleanObjectFeature() {
		assertBooleanFeature(testPackage.classForControls_BooleanObjectFeature)
	}

	@Test def void testBooleanDataTypeFeature() {
		assertBooleanFeature(testPackage.classForControls_BooleanDataTypeFeature)
	}

	@Test def void testBooleanPrimitiveDataTypeFeature() {
		assertBooleanFeature(testPackage.classForControls_BooleanPrimitiveDataTypeFeature)
	}

	@Test def void testNonBooleanFeature() {
		assertNotBooleanFeature(testPackage.classForControls_StringFeature)
	}

	@Test def void testNonBooleanFeature2() {
		assertNotBooleanFeature(testPackage.classForControls_ReferenceToClassWithName)
	}

	@Test def void testNonBooleanDataTypeFeature() {
		assertNotBooleanFeature(testPackage.classForControls_StringDataTypeFeature)
	}

	@Test def void testEditableDataType() {
		assertEditable(testPackage.classForControls_StringFeature)
	}

	@Test def void testEditable() {
		assertEditable(testPackage.classForControls_ReferenceToClassWithName)
	}

	@Test def void testEditableSerializableDataType() {
		assertEditable(testPackage.classForControls_StringDataTypeFeature)
	}

	@Test def void testNotEditableDerived() {
		assertNotEditable(testPackage.classForControls_DerivedStringFeature)
	}

	@Test def void testNotEditableNotChangeable() {
		assertNotEditable(testPackage.classForControls_UnchangeableStringFeature)
	}

	@Test def void testNotEditableUnserializableDataType() {
		assertNotEditable(testPackage.classForControls_UnserializableStringDataTypeFeature)
	}

	@Test def void testHasPredefinedProposalsReference() {
		assertPredefinedProposals(testPackage.classForControls_ReferenceToClassWithName)
	}

	@Test def void testHasPredefinedProposalsEnum() {
		assertPredefinedProposals(testPackage.classForControls_EnumFeature)
	}

	@Test def void testNoPredefinedProposals() {
		assertNotPredefinedProposals(testPackage.classForControls_StringFeature)
	}

	@Test def void testProposalsForEnum() {
		assertProposals(classForControlsInstance, testPackage.classForControls_EnumFeature,
			"FIRST, SECOND, THIRD"
		)
	}

	@Test def void testProposalsForReference() {
		val res = createResource
		res.contents += classForControlsInstance
		
		createClassWithName(res, "Ref1")
		createClassWithName(res, "Ref2")
		createClassWithName(res, "Ref3")

		assertProposals(classForControlsInstance, testPackage.classForControls_ReferenceToClassWithName,
				"Class With Name Ref1, Class With Name Ref2, Class With Name Ref3")
	}

	@Test def void testCustomProposals() {
		val proposalCreator = new ProposalCreator() {
			def proposals_ClassForControls_stringFeature(ClassForControls e) {
				return #["First Proposal", "Second Proposal"]
			}
		}
		featureHelper.proposalCreator = proposalCreator
		assertProposals(classForControlsInstance, testPackage.classForControls_StringFeature,
			"First Proposal, Second Proposal")
	}


	def private assertBooleanFeature(EStructuralFeature feature) {
		featureHelper.isBooleanFeature(feature).assertTrue
	}

	def private assertNotBooleanFeature(EStructuralFeature feature) {
		featureHelper.isBooleanFeature(feature).assertFalse
	}

	def private assertEditable(EStructuralFeature feature) {
		featureHelper.isEditable(feature).assertTrue
	}

	def private assertNotEditable(EStructuralFeature feature) {
		featureHelper.isEditable(feature).assertFalse
	}

	def private assertPredefinedProposals(EStructuralFeature feature) {
		featureHelper.hasPredefinedProposals(feature).assertTrue
	}

	def private assertNotPredefinedProposals(EStructuralFeature feature) {
		featureHelper.hasPredefinedProposals(feature).assertFalse
	}

	def private assertProposals(EObject o, EStructuralFeature f, CharSequence expected) {
		val labelProvider = getOrCreateInjector.getInstance(ILabelProvider)
		
		expected.toString.assertEquals
			(featureHelper.createProposals(o, f).map[
				labelProvider.getText(it)
			].join(", "))
	}
}