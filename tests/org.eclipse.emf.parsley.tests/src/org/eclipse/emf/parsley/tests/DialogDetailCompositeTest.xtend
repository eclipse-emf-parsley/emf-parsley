/*******************************************************************************
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
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
import org.eclipse.emf.edit.domain.EditingDomain
import org.eclipse.emf.parsley.composite.DialogDetailComposite
import org.eclipse.emf.parsley.inject.CompositeParameters
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyControlBasedTest
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.eclipse.swt.SWT
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author Lorenzo Bettini
 */
class DialogDetailCompositeTest extends AbstractEmfParsleyControlBasedTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	@Inject EditingDomain editingDomain

	var DialogDetailComposite dialogDetailComposite

	@Before def void setupTestCase() {
		val injector = getOrCreateInjector
		injector.injectMembers(this)
		dialogDetailComposite = new DialogDetailComposite(new CompositeParameters(shell, SWT.NONE))
		injector.injectMembers(dialogDetailComposite)
	}

	@Test def void testInitWithEditingDomain() {
		val o = testFactory.createClassWithName => [name = "Test"]
		dialogDetailComposite.init(o, editingDomain)
	}

	@Test def void testInitWithoutEditingDomain() {
		val o = testFactory.createClassWithName => [name = "Test"]
		dialogDetailComposite.init(o)
	}

}
