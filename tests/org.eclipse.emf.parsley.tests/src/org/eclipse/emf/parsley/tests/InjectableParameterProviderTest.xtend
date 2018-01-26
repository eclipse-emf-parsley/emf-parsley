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

import com.google.inject.AbstractModule
import com.google.inject.Inject
import com.google.inject.Key
import com.google.inject.TypeLiteral
import com.google.inject.util.Modules
import org.eclipse.emf.parsley.inject.InjectableParameter
import org.eclipse.emf.parsley.internal.inject.InjectableParameterProvider
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.junit.Test

import static extension org.junit.Assert.*

class InjectableParameterProviderTest extends AbstractEmfParsleyShellBasedTest {

	static class MyInjectableParameter implements InjectableParameter {
		public var int i

		new(int i) {
			this.i = i
		}

	}

	static class AnotherMyInjectableParameter implements InjectableParameter {
		public var String s

		new(String s) {
			this.s = s
		}

	}

	static interface InjectableObjectInterface {

	}

	static class InjectableObject implements InjectableObjectInterface {
		public var MyInjectableParameter param

		@Inject
		new(MyInjectableParameter param) {
			this.param = param
		}

	}

	static class AnotherInjectableObject implements InjectableObjectInterface {
		public var MyInjectableParameter param

		@Inject
		new(MyInjectableParameter param) {
			this.param = param
		}

	}

	static class InjectableObjectCustom extends InjectableObject {
		@Inject
		new(MyInjectableParameter param) {
			super(param)
		}
	}

	static class DefaultModule extends AbstractModule {
		override protected configure() {
			InjectableParameterProvider.bindInjectableParameterProvider(binder(), MyInjectableParameter)
		}
	}

	val providerKey = Key.get(new TypeLiteral<InjectableParameterProvider<MyInjectableParameter>>() {});

	val defaultModule = new DefaultModule

	@Test
	def void testCanCreateSeveralInjectableObject() {
		val injector = createInjector(defaultModule)
		val provider = injector.getInstance(providerKey)
		// add the params to the stack on our direct instance
		provider.insertForLaterProvide(new MyInjectableParameter(10))
		provider.insertForLaterProvide(new MyInjectableParameter(20))
		// and create params through the injector
		var c = injector.getInstance(InjectableObject)
		20.assertSame(c.param.i)
		c = injector.getInstance(InjectableObject)
		10.assertSame(c.param.i)
	}

	@Test
	def void testProviderIsSingleton() {
		val injector = createInjector(defaultModule)
		val provider1 = injector.getInstance(providerKey)
		val provider2 = injector.getInstance(providerKey)
		// add the params to the stack on the two providers
		provider1.insertForLaterProvide(new MyInjectableParameter(10))
		provider2.insertForLaterProvide(new MyInjectableParameter(20))
		// and create params through the injector
		var c = injector.getInstance(InjectableObject)
		20.assertSame(c.param.i)
		c = injector.getInstance(InjectableObject)
		10.assertSame(c.param.i)
	}

	@Test
	def void testInjectableObjectCustomImplementation() {
		val injector = createInjector(new DefaultModule() {
			override protected configure() {
				super.configure()
				bind(InjectableObject).to(InjectableObjectCustom)
			}
		})
		val provider = injector.getInstance(providerKey)
		provider.insertForLaterProvide(new MyInjectableParameter(10))
		var c = injector.getInstance(InjectableObject) as InjectableObjectCustom
		10.assertSame(c.param.i)
	}

	@Test
	def void testInjectableObjectCustomWithModuleOverride() {
		val injector = createInjector(
			Modules.override(defaultModule).with(new AbstractModule() {
				override protected configure() {
					bind(InjectableObject).to(InjectableObjectCustom)
				}
			}))
		val provider = injector.getInstance(providerKey)
		provider.insertForLaterProvide(new MyInjectableParameter(10))
		var c = injector.getInstance(InjectableObject) as InjectableObjectCustom
		10.assertSame(c.param.i)
	}

	@Test
	def void testDifferentProvidersDontInterfere() {
		val anotherProviderKey = Key.get(new TypeLiteral<InjectableParameterProvider<AnotherMyInjectableParameter>>() {});
		val injector = createInjector(new DefaultModule() {
			override protected configure() {
				super.configure()
				InjectableParameterProvider.bindInjectableParameterProvider(binder(), AnotherMyInjectableParameter)
			}
		})
		val provider1 = injector.getInstance(providerKey)
		provider1.insertForLaterProvide(new MyInjectableParameter(10))
		val provider2 = injector.getInstance(anotherProviderKey)
		provider2.insertForLaterProvide(new AnotherMyInjectableParameter("test"))
		var c = injector.getInstance(InjectableObject)
		10.assertSame(c.param.i)
		"test".assertEquals(injector.getInstance(AnotherMyInjectableParameter).s)
	}
}
