/*******************************************************************************
 * Copyright (c) 2018 RCP Vision (http://www.rcp-vision.com) and others.
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
import org.eclipse.emf.parsley.dialogs.DialogFactory
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Make sure we can instantiate all our composites via dependency injection.
 */
class DialogFactoryTest extends AbstractEmfParsleyShellBasedTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	var EObject object

	var DialogFactory factory

	@Before
	def void setupFactory() {
		factory = getOrCreateInjector.getInstance(DialogFactory)
		object = testFactory.createClassWithName => [name = "Test"]
	}

	@Test
	def void canDetailDialog() {
		factory.createDetailDialog(shell, "title", object, editingDomain)
	}

	@Test
	def void canDetailFormBasedDialog() {
		factory.createDetailFormBasedDialog(shell, "title", object, editingDomain)
	}
}
