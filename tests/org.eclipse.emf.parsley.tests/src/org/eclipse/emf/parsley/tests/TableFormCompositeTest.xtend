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

import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.parsley.composite.CompositeFactory
import org.eclipse.emf.parsley.composite.TableFormComposite
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.eclipse.swt.SWT
import org.junit.Before
import org.junit.Test

import static extension org.junit.Assert.*

/**
 * Make sure we can instantiate all our composites via dependency injection.
 */
class TableFormCompositeTest extends AbstractEmfParsleyShellBasedTest {

	var TableFormComposite tableFormComposite

	val type = EcorePackage.eINSTANCE.EObject

	@Before
	def void setupComposite() {
		val injector = getOrCreateInjector
		tableFormComposite = injector
			.getInstance(CompositeFactory)
			.createTableFormComposite(shell, SWT.NONE, type)
	}

	@Test def void testUpdateWithNullObject() {
		tableFormComposite.update(null)
		tableFormComposite.viewer.input.assertNull
	}

	@Test def void testUpdateWithObject() {
		val arg = new Object
		tableFormComposite.update(arg)
		arg.assertSame(tableFormComposite.viewer.input)
	}
}
