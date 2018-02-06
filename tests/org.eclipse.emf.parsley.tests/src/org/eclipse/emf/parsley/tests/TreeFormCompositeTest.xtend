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

import org.eclipse.emf.parsley.composite.CompositeFactory
import org.eclipse.emf.parsley.composite.TreeFormComposite
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.eclipse.swt.SWT
import org.junit.Before
import org.junit.Test

import static extension org.junit.Assert.*

/**
 * Make sure we can instantiate all our composites via dependency injection.
 */
class TreeFormCompositeTest extends AbstractEmfParsleyShellBasedTest {

	var TreeFormComposite treeFormComposite

	@Before
	def void setupComposite() {
		val injector = getOrCreateInjector
		treeFormComposite = injector
			.getInstance(CompositeFactory)
			.createTreeFormComposite(shell, SWT.NONE)
	}

	@Test def void testUpdateWithNullObject() {
		treeFormComposite.update(null)
		treeFormComposite.viewer.input.assertNull
	}

	@Test def void testUpdateWithObject() {
		val arg = new Object
		treeFormComposite.update(arg)
		arg.assertSame(treeFormComposite.viewer.input)
	}
}
