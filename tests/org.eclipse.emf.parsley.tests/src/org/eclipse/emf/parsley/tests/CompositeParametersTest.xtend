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

import com.google.inject.ProvisionException
import org.eclipse.emf.parsley.composite.CompositeParameters
import org.eclipse.emf.parsley.internal.composite.CompositeParametersProvider
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.junit.Test

import static extension org.junit.Assert.*

class CompositeParametersTest extends AbstractEmfParsleyShellBasedTest {

	// Since the provides has not been prepared
	@Test(expected=ProvisionException)
	def void canInjectCompositeParametersWithoutPreparingTheProvider() {
		getOrCreateInjector.getInstance(CompositeParameters)
	}

	@Test
	def void canInjectCompositeParametersWithProvider() {
		val injector = getOrCreateInjector
		val provider = injector.getInstance(CompositeParametersProvider)
		provider.insertForLaterProvide(new CompositeParameters(shell, 1))
		val params = injector.getInstance(CompositeParameters)
		shell.assertSame(params.parent)
		1.assertEquals(params.style)
	}

	@Test
	def void canInjectSeveralCompositeParameters() {
		val injector = getOrCreateInjector
		val provider = injector.getInstance(CompositeParametersProvider)
		provider.insertForLaterProvide(new CompositeParameters(shell, 1))
		provider.insertForLaterProvide(new CompositeParameters(shell, 2))
		// the provider uses a stack, thus the first params created
		// gets the second arguments (2) and the second one gets (1)
		var params = injector.getInstance(CompositeParameters)
		shell.assertSame(params.parent)
		2.assertEquals(params.style)
		params = injector.getInstance(CompositeParameters)
		shell.assertSame(params.parent)
		1.assertEquals(params.style)
	}

}
