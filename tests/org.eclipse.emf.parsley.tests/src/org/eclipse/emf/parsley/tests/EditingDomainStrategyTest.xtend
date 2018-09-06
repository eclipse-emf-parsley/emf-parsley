/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests

import com.google.inject.Injector
import org.eclipse.emf.edit.domain.EditingDomain
import org.eclipse.emf.parsley.edit.domain.EditingDomainFinderStrategy
import org.eclipse.emf.parsley.edit.domain.EditingDomainPresetStrategy
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*

class EditingDomainStrategyTest extends AbstractEmfParsleyTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	var Injector injector

	var EditingDomain resourceEditingDomain

	@Before
	def void setupInjector() {
		injector = getOrCreateInjector
		createResourceForTest
	}

	@Test def void testEditingDomainFinderStrategy() {
		val strategy = injector.getInstance(EditingDomainFinderStrategy)
		strategy.editingDomain.assertNull
		strategy.updateEditingDomain(library)
		strategy.editingDomain.assertSame(resourceEditingDomain)
	}

	@Test def void testEditingDomainPresetStrategy() {
		val strategy = injector.getInstance(EditingDomainPresetStrategy)
		strategy.editingDomain.assertNull
		val preset = injector.getInstance(EditingDomain)
		strategy.editingDomain = preset
		strategy.updateEditingDomain(library)
		// still use the preset one
		strategy.editingDomain.assertNotSame(resourceEditingDomain)
		strategy.editingDomain.assertSame(preset)
	}

	def private createResourceForTest() {
		resourceEditingDomain = injector.createEditingDomain
		createTestLibrayResourceAndInitialize(
			resourceEditingDomain.resourceSet.setupResouceFactory
		)
	}

}
