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

import com.google.inject.Inject
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.parsley.edit.provider.AdapterFactoryHelper
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*

class AdapterFactoryHelperTest extends AbstractEmfParsleyTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	val static DERIVED_CLASS_FEATURE_PROP_DESCRIPTION = "Derived Class Feature"

	@Inject var AdapterFactoryHelper adapterFactoryHelper

	val instance = EcoreUtil.create(derivedClass)

	@Before
	def void setupFields() {
		getOrCreateInjector.injectMembers(this)
	}

	@Test def void testPropertyDescriptor() {
		DERIVED_CLASS_FEATURE_PROP_DESCRIPTION.assertEquals(
			adapterFactoryHelper.getItemPropertyDescriptor(instance, testPackage.derivedClass_DerivedClassFeature).
				getDisplayName(instance)
		)
	}

	@Test def void testNoPropertyDescriptor() {
		// we simulate the absence of a property description specifying
		// a feature that is not present in the object's EClass
		adapterFactoryHelper.getItemPropertyDescriptor(instance, testPackage.testContainer_ClassesForControls).
			assertNull
	}
}