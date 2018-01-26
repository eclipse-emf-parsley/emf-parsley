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

import com.google.inject.Binder
import com.google.inject.Key
import com.google.inject.ProvisionException
import com.google.inject.TypeLiteral
import javax.inject.Inject
import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule
import org.eclipse.emf.parsley.inject.CompositeParameters
import org.eclipse.emf.parsley.inject.InjectableComposite
import org.eclipse.emf.parsley.internal.inject.InjectableParameterProvider
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.junit.Test

import static extension org.junit.Assert.*

class InjectableCompositeTest extends AbstractEmfParsleyShellBasedTest {

	val providerTypeKey = Key.get(new TypeLiteral<InjectableParameterProvider<CompositeParameters>>() {});

	private static class CustomInjectableComposite extends InjectableComposite {
		@Inject
		new(CompositeParameters params) {
			super(params)
		}
	}

	@Test(expected=ProvisionException)
	def void cannotInjectWithoutExplicitBindings() {
		getOrCreateInjector.getInstance(InjectableComposite)
	}

	@Test
	def void canInjectWithProvider() {
		val injector = getOrCreateInjector
		val provider = injector.getInstance(providerTypeKey)
		provider.insertForLaterProvide(new CompositeParameters(shell, 1))
		val o = injector.getInstance(InjectableComposite)
		shell.assertSame(o.parent)
		assertTrue("style should not be positive, instead is " + o.style, o.style > 0)
	}

	@Test
	def void canInjectCustomImplWithProvider() {
		val injector = createInjector(new EmfParsleyJavaGuiceModule() {
			override configure(Binder binder) {
				super.configure(binder)
				binder.bind(InjectableComposite).to(CustomInjectableComposite)
			}
		})
		val provider = injector.getInstance(providerTypeKey)
		provider.insertForLaterProvide(new CompositeParameters(shell, 1))
		val o = injector.getInstance(InjectableComposite)
		CustomInjectableComposite.assertSame(o.class)
		shell.assertSame(o.parent)
		assertTrue("style should not be positive, instead is " + o.style, o.style > 0)
	}

}
