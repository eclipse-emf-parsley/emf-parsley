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

import java.util.List
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.parsley.composite.ProposalCreator
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest
import org.eclipse.emf.parsley.tests.models.testmodels.BaseClass
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls
import org.eclipse.jface.viewers.ILabelProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule

class ProposalCreatorTest extends AbstractEmfParsleyTest {
	
	var ProposalCreator defaultProposalCreator
	
	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()
	
	@Before
	def void setupEObject() {
		defaultProposalCreator = new ProposalCreator
	}

	@Test def void testCustomProposals() {
		val proposalCreator = new ProposalCreator() {
			def proposals_ClassForControls_stringFeature(ClassForControls e) {
				return #["First Proposal", "Second Proposal"]
			}
		}
		proposalCreator.proposals(classForControlsInstance, testPackage.classForControls_StringFeature).
			assertProposals("First Proposal, Second Proposal")
	}

	@Test def void testCustomProposalsForBaseClassFeatureInDerivedClass() {
		// although we pass a DerivedClass object we use the customization
		// specified for BaseClass
		val proposalCreator = new ProposalCreator() {
			def proposals_BaseClass_baseClassFeature(BaseClass e) {
				return #["First Proposal", "Second Proposal"]
			}
		}
		proposalCreator.proposals(testFactory.createDerivedClass, testPackage.baseClass_BaseClassFeature).
			assertProposals("First Proposal, Second Proposal")
	}

	/**
	 * The second form of customization: EObject, EStructuralFeature
	 */
	@Test def void testCustomProposals2() {
		val proposalCreator = new ProposalCreator() {
			def proposals_ClassForControls_stringFeature(ClassForControls e, EStructuralFeature f) {
				return #["First Proposal", "Second Proposal"]
			}
		}
		proposalCreator.proposals(classForControlsInstance, testPackage.classForControls_StringFeature).
			assertProposals("First Proposal, Second Proposal")
	}

	@Test def void testDefaultProposalsForAttribute() {
		defaultProposalCreator.
			proposals(classForControlsInstance, testPackage.classForControls_StringFeature).
				assertNull
	}

	@Test def void testDefaultProposalsWithoutResourceSet() {
		defaultProposalCreator.
			proposals(classForControlsInstance, testPackage.classForControls_ReferenceToClassWithName).
				assertProposals("")
	}

	@Test def void testDefaultProposalsForEnumFeature() {
		defaultProposalCreator.
			proposals(classForControlsInstance, testPackage.classForControls_EnumFeature).
				assertProposals("FIRST, SECOND, THIRD")
	}

	@Test def void testDefaultProposalsWithResource() {
		val res = createResource
		res.contents += classForControlsInstance
		
		createClassWithName(res, "Ref1")
		createClassWithName(res, "Ref2")
		createClassWithName(res, "Ref3")

		defaultProposalCreator.
			proposals(classForControlsInstance, testPackage.classForControls_ReferenceToClassWithName).
				assertProposals("Class With Name Ref1, Class With Name Ref2, Class With Name Ref3")
	}

	@Test def void testDefaultProposalsWithManuallySetResource() {
		val res = createResource
		
		createClassWithName(res, "Ref1")
		createClassWithName(res, "Ref2")
		createClassWithName(res, "Ref3")

		defaultProposalCreator.setResource(res)

		defaultProposalCreator.
			proposals(classForControlsInstance, testPackage.classForControls_ReferenceToClassWithName).
				assertProposals("Class With Name Ref1, Class With Name Ref2, Class With Name Ref3")
	}

	@Test def void testDefaultProposalsWithResourceSet() {
		val res = createResourceInResouceSet
		res.contents += classForControlsInstance
		
		createClassWithName(res, "Ref1")
		createClassWithName(res, "Ref2")
		createClassWithName(res, "Ref3")
		
		defaultProposalCreator.
			proposals(classForControlsInstance, testPackage.classForControls_ReferenceToClassWithName).
				assertProposals("Class With Name Ref1, Class With Name Ref2, Class With Name Ref3")
	}

	@Test def void testDefaultProposalsInDifferentResources() {
		val res = createResourceInResouceSet
		val res2 = createResourceInResouceSet
		
		res.contents += classForControlsInstance
		
		createClassWithName(res, "Ref1")
		createClassWithName(res2, "Ref2")
		createClassWithName(res, "Ref3")
		
		// note that the proposals are ordered differently w.r.t. the previous test
		//  since they are in different resources.
		defaultProposalCreator.
			proposals(classForControlsInstance, testPackage.classForControls_ReferenceToClassWithName).
				assertProposals("Class With Name Ref1, Class With Name Ref3, Class With Name Ref2")
	}

	def private assertProposals(List<Object> proposals, CharSequence expected) {
		val labelProvider = getOrCreateInjector.getInstance(ILabelProvider)
		
		expected.toString.assertEquals
			(proposals.map[
				labelProvider.getText(it)
			].join(", "))
	}
}