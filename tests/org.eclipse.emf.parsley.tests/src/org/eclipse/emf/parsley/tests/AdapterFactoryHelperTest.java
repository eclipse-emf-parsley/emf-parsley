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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.parsley.edit.provider.AdapterFactoryHelper;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.inject.Inject;

public class AdapterFactoryHelperTest extends AbstractEmfParsleyTest {

	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	private static final String DERIVED_CLASS_FEATURE_PROP_DESCRIPTION = "Derived Class Feature";

	@Inject
	private AdapterFactoryHelper adapterFactoryHelper;

	private final EObject instance = EcoreUtil.create(fixtures.getDerivedClass());

	@Before
	public void setupFields() {
		getOrCreateInjector().injectMembers(this);
	}

	@Test
	public void testPropertyDescriptor() {
		assertEquals(DERIVED_CLASS_FEATURE_PROP_DESCRIPTION,
			adapterFactoryHelper.getItemPropertyDescriptor(instance, 
				fixtures.getTestPackage().getDerivedClass_DerivedClassFeature())
				.getDisplayName(instance)
		);
	}

	@Test
	public void testNoPropertyDescriptor() {
		// we simulate the absence of a property description specifying
		// a feature that is not present in the object's EClass
		assertNull(
			adapterFactoryHelper.getItemPropertyDescriptor(instance, 
				fixtures.getTestPackage().getTestContainer_ClassesForControls())
		);
	}
}
