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
import com.google.inject.Binder
import com.google.inject.Inject
import com.google.inject.Key
import com.google.inject.Singleton
import com.google.inject.TypeLiteral
import com.google.inject.util.Modules
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule
import org.eclipse.emf.parsley.inject.InjectableParameter
import org.eclipse.emf.parsley.internal.inject.GenericFactory
import org.eclipse.emf.parsley.internal.inject.InjectableParameterProvider
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.junit.Before
import org.junit.Test

import static extension org.junit.Assert.*

class GenericFactoryTest extends AbstractEmfParsleyShellBasedTest {

	static class MyInjectableParameter implements InjectableParameter {
		public var int i

		new(int i) {
			this.i = i
		}

	}

	@Singleton
	static class MyInjectableParameterProvider extends InjectableParameterProvider<MyInjectableParameter> {
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
		public var AnotherInjectableObject nested

		@Inject
		new(MyInjectableParameter param, InjectableObjectFactory factory) {
			super(param)
			// pass an incremented integer
			nested = factory.create(AnotherInjectableObject, param.i+1)
		}

	}

	static class InjectableObjectFactory {
		@Inject
		GenericFactory<InjectableObjectInterface, MyInjectableParameter> internalFactory

		def <T extends InjectableObjectInterface> T create(Class<T> type, int param) {
			return internalFactory.createInstance(type, new MyInjectableParameter(param))
		}
	}

	static class InjectableObjectModule extends EmfParsleyJavaGuiceModule {
		override configure(Binder binder) {
			super.configure(binder)
			// first bind the parameter to our own derived provider
			binder
				.bind(MyInjectableParameter)
				.toProvider(MyInjectableParameterProvider)
			// then bind the generic provider used by the factory to our own derived provider
			binder
				.bind(new TypeLiteral<InjectableParameterProvider<MyInjectableParameter>>() {})
				.to(MyInjectableParameterProvider)
		}
	}

	static class GenericInjectableObject<T extends InjectableParameter> {
		public T value

		@Inject
		new(T value) {
			this.value = value
		}

	}

	static class StringP implements InjectableParameter {
		public String value

		new (String value) {
			this.value = value
		}
	}

	static class EClassP implements InjectableParameter {
		public EClass value

		new (EClass value) {
			this.value = value
		}
	}

	static class GenericInjectableObjectWithString extends GenericInjectableObject<StringP> {
		@Inject
		new (StringP value) {
			super(value)
		}
	}

	static class GenericInjectableObjectWithEClass extends GenericInjectableObject<EClassP> {
		@Inject
		new (EClassP value) {
			super(value)
		}
	}

	var InjectableObjectFactory factory

	@Before
	def void setupFactory() {
		factory = createInjector(new InjectableObjectModule).getInstance(InjectableObjectFactory)
	}

	@Test
	def void testInjectedProviderIsTheOneSpecifiedInProvidedBy() {
		val injector = createInjector(new InjectableObjectModule)
		// retrieve the provider via the injector
		val provider1 = injector.getProvider(Key.get(new TypeLiteral<MyInjectableParameter>() {
		}))
		val provider2 = injector.getProvider(MyInjectableParameter)
		// and also instantiate it directly
		val paramProvider = injector.getInstance(MyInjectableParameterProvider)
		// add the params to the stack on our direct instance
		paramProvider.insertForLaterProvide(new MyInjectableParameter(10))
		paramProvider.insertForLaterProvide(new MyInjectableParameter(20))
		// and create params through the retrieved providers
		20.assertEquals(provider1.get().i)
		10.assertEquals(provider2.get().i)
	}

	@Test
	def void testCanCreateInjectableObject() {
		val c = factory.create(InjectableObject, 10)
		10.assertSame(c.param.i)
	}

	@Test
	def void testCanCreateSeveralInjectableObject() {
		var c = factory.create(InjectableObject, 10)
		10.assertSame(c.param.i)
		c = factory.create(InjectableObject, 20)
		20.assertSame(c.param.i)
	}

	@Test
	def void testCanCreateInjectableObjectCustom() {
		factory = createInjector(new InjectableObjectModule() {
			override configure(Binder binder) {
				super.configure(binder)
				binder.bind(InjectableObject).to(InjectableObjectCustom)
			}
		})
		.getInstance(InjectableObjectFactory)
		val c = factory.create(InjectableObject, 10) as InjectableObjectCustom
		10.assertSame(c.param.i)
		11.assertSame(c.nested.param.i) // the nested is passed an incremented integer
	}

	@Test
	def void testCanCreateInjectableObjectCustomWithModuleOverride() {
		val override = Modules.override(new InjectableObjectModule()).with(new AbstractModule() {
			override protected configure() {
				bind(InjectableObject).to(InjectableObjectCustom)
			}
		})
		factory = createInjector(override).getInstance(InjectableObjectFactory)
		val c = factory.create(InjectableObject, 10) as InjectableObjectCustom
		10.assertSame(c.param.i)
		11.assertSame(c.nested.param.i) // the nested is passed an incremented integer
	}

	@Test
	def void testCanCreateInjectableObjectWithoutCustomProvider() {
		factory = createInjector(new EmfParsleyJavaGuiceModule() {
			override configure(Binder binder) {
				super.configure(binder)
//				val providerTypeLiteral = new TypeLiteral<InjectableParameterProvider<MyInjectableParameter>>() {}
//				// first bind the parameter type to the generic provider used by the factory to a generic instance of the provider
//				binder
//					.bind(MyInjectableParameter)
//					.toProvider(providerTypeLiteral)
//				// then make sure the same provider is used for injecting all those instances
//				binder
//					.bind(providerTypeLiteral)
//					.in(Scopes.SINGLETON)
				InjectableParameterProvider
					.bindInjectableParameterProvider(binder, MyInjectableParameter)
			}
		})
		.getInstance(InjectableObjectFactory)
		val c = factory.create(InjectableObject, 10)
		10.assertSame(c.param.i)
	}

	@Test
	def void testCanCreateGenericInjectableObjects() {
		val injector = createInjector(new AbstractModule() {
			override protected configure() {
				InjectableParameterProvider
					.bindInjectableParameterProvider(binder, StringP)
				InjectableParameterProvider
					.bindInjectableParameterProvider(binder, EClassP)
			}
		})
		val genericInjectableObjectStringFactory =
			injector.getInstance(
				Key.get(new TypeLiteral<GenericFactory<GenericInjectableObject<StringP>, StringP>>() {})
			)
		val genericInjectableObjectEClassFactory =
			injector.getInstance(
				Key.get(new TypeLiteral<GenericFactory<GenericInjectableObject<EClassP>, EClassP>>() {})
			)
		val eclass = EcorePackage.eINSTANCE.EObject
		val o1 = genericInjectableObjectStringFactory
			.createInstance(GenericInjectableObjectWithString, new StringP("test"))
		val o2 = genericInjectableObjectEClassFactory
			.createInstance(GenericInjectableObjectWithEClass, new EClassP(eclass))
		"test".assertEquals(o1.value.value)
		eclass.assertEquals(o2.value.value)
	}
}
