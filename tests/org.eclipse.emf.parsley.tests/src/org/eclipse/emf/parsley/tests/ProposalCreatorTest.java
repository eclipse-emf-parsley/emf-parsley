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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.composite.ProposalCreator;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest;
import org.eclipse.emf.parsley.tests.models.testmodels.BaseClass;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.jface.viewers.ILabelProvider;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class ProposalCreatorTest extends AbstractEmfParsleyTest {
	
	private ProposalCreator defaultProposalCreator;
	
	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();
	
	@Before
	public void setupEObject() {
		defaultProposalCreator = new ProposalCreator();
	}

	@Test
	public void testCustomProposals() {
		final var proposalCreator = new ProposalCreator() {
			@SuppressWarnings("unused")
			public List<String> proposals_ClassForControls_stringFeature(ClassForControls e) {
				return Arrays.asList("First Proposal", "Second Proposal");
			}
		};
		assertProposals(
			proposalCreator.proposals(fixtures.getClassForControlsInstance(), fixtures.getTestPackage().getClassForControls_StringFeature()),
			"First Proposal, Second Proposal");
	}

	@Test
	public void testCustomProposalsForBaseClassFeatureInDerivedClass() {
		// although we pass a DerivedClass object we use the customization
		// specified for BaseClass
		final var proposalCreator = new ProposalCreator() {
			@SuppressWarnings("unused")
			public List<String> proposals_BaseClass_baseClassFeature(BaseClass e) {
				return Arrays.asList("First Proposal", "Second Proposal");
			}
		};
		assertProposals(
			proposalCreator.proposals(fixtures.getTestFactory().createDerivedClass(), fixtures.getTestPackage().getBaseClass_BaseClassFeature()),
			"First Proposal, Second Proposal");
	}

	/**
	 * The second form of customization: EObject, EStructuralFeature
	 */
	@Test
	public void testCustomProposals2() {
		final var proposalCreator = new ProposalCreator() {
			@SuppressWarnings("unused")
			public List<String> proposals_ClassForControls_stringFeature(ClassForControls e, EStructuralFeature f) {
				return Arrays.asList("First Proposal", "Second Proposal");
			}
		};
		assertProposals(
			proposalCreator.proposals(fixtures.getClassForControlsInstance(), fixtures.getTestPackage().getClassForControls_StringFeature()),
			"First Proposal, Second Proposal");
	}

	@Test
	public void testDefaultProposalsForAttribute() {
		assertNull(
			defaultProposalCreator
				.proposals(fixtures.getClassForControlsInstance(), fixtures.getTestPackage().getClassForControls_StringFeature()));
	}

	@Test
	public void testDefaultProposalsWithoutResourceSet() {
		assertProposals(
			defaultProposalCreator
				.proposals(fixtures.getClassForControlsInstance(), fixtures.getTestPackage().getClassForControls_ReferenceToClassWithName()),
			"");
	}

	@Test
	public void testDefaultProposalsForEnumFeature() {
		assertProposals(
			defaultProposalCreator
				.proposals(fixtures.getClassForControlsInstance(), fixtures.getTestPackage().getClassForControls_EnumFeature()),
			"FIRST, SECOND, THIRD");
	}

	@Test
	public void testDefaultProposalsWithResource() {
		final var res = fixtures.createResource();
		res.getContents().add(fixtures.getClassForControlsInstance());

		fixtures.createClassWithName(res, "Ref1");
		fixtures.createClassWithName(res, "Ref2");
		fixtures.createClassWithName(res, "Ref3");

		assertProposals(
			defaultProposalCreator
				.proposals(fixtures.getClassForControlsInstance(), fixtures.getTestPackage().getClassForControls_ReferenceToClassWithName()),
			"Class With Name Ref1, Class With Name Ref2, Class With Name Ref3");
	}

	@Test
	public void testDefaultProposalsWithManuallySetResource() {
		final var res = fixtures.createResource();
		
		fixtures.createClassWithName(res, "Ref1");
		fixtures.createClassWithName(res, "Ref2");
		fixtures.createClassWithName(res, "Ref3");

		defaultProposalCreator.setResource(res);

		assertProposals(
			defaultProposalCreator
				.proposals(fixtures.getClassForControlsInstance(), fixtures.getTestPackage().getClassForControls_ReferenceToClassWithName()),
			"Class With Name Ref1, Class With Name Ref2, Class With Name Ref3");
	}

	@Test
	public void testDefaultProposalsWithResourceSet() {
		final var res = fixtures.createResourceInResouceSet();
		res.getContents().add(fixtures.getClassForControlsInstance());
		
		fixtures.createClassWithName(res, "Ref1");
		fixtures.createClassWithName(res, "Ref2");
		fixtures.createClassWithName(res, "Ref3");
		
		assertProposals(
			defaultProposalCreator
				.proposals(fixtures.getClassForControlsInstance(), fixtures.getTestPackage().getClassForControls_ReferenceToClassWithName()),
			"Class With Name Ref1, Class With Name Ref2, Class With Name Ref3");
	}

	@Test
	public void testDefaultProposalsInDifferentResources() {
		final var res = fixtures.createResourceInResouceSet();
		final var res2 = fixtures.createResourceInResouceSet();
		
		res.getContents().add(fixtures.getClassForControlsInstance());
		
		fixtures.createClassWithName(res, "Ref1");
		fixtures.createClassWithName(res2, "Ref2");
		fixtures.createClassWithName(res, "Ref3");
		
		// note that the proposals are ordered differently w.r.t. the previous test
		//  since they are in different resources.
		assertProposals(
			defaultProposalCreator
				.proposals(fixtures.getClassForControlsInstance(), fixtures.getTestPackage().getClassForControls_ReferenceToClassWithName()),
			"Class With Name Ref1, Class With Name Ref3, Class With Name Ref2");
	}

	private void assertProposals(List<Object> proposals, String expected) {
		final var labelProvider = getOrCreateInjector().getInstance(ILabelProvider.class);
		assertEquals(
			expected,
			proposals.stream()
				.map(it -> labelProvider.getText(it))
				.collect(Collectors.joining(", ")));
	}
}
