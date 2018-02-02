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
import com.google.inject.TypeLiteral
import com.google.inject.util.Modules
import java.util.ArrayList
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule
import org.eclipse.emf.parsley.inject.parameters.FactoryParameter
import org.eclipse.emf.parsley.inject.parameters.InjectableParameter
import org.eclipse.emf.parsley.internal.inject.GenericFactory
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.junit.Before
import org.junit.Test

import static extension org.junit.Assert.*

class GenericFactoryTest extends AbstractEmfParsleyShellBasedTest {

	@FactoryParameter
	private static class MyInjectableParameter implements InjectableParameter {
		public var int i

		new() {
			
		}

		new(int i) {
			this.i = i
		}

	}

	private static interface InjectableObjectInterface {

	}

	private static class InjectableObject implements InjectableObjectInterface {
		public var MyInjectableParameter param

		@Inject
		new(MyInjectableParameter param) {
			this.param = param
		}

	}

	private static class AnotherInjectableObject implements InjectableObjectInterface {
		public var MyInjectableParameter param

		@Inject
		new(MyInjectableParameter param) {
			this.param = param
		}

	}

	private static class InjectableObjectCustom extends InjectableObject {
		public var AnotherInjectableObject nested

		@Inject
		new(MyInjectableParameter param, InjectableObjectFactory factory) {
			super(param)
			// pass an incremented integer
			nested = factory.create(AnotherInjectableObject, param.i+1)
		}

	}

	private static class InjectableObjectFactory {
		@Inject
		GenericFactory<InjectableObjectInterface> internalFactory

		def <T extends InjectableObjectInterface> T create(Class<T> type, int param) {
			return internalFactory.createInstance(type, new MyInjectableParameter(param))
		}
	}

	private static class InjectableObjectModule extends EmfParsleyJavaGuiceModule {
		override configure(Binder binder) {
			super.configure(binder)
		}
	}

	@FactoryParameter
	private static class GenericInjectableObject<T extends InjectableParameter> {
		public T value

		@Inject
		new(T value) {
			this.value = value
		}

	}

	@FactoryParameter
	private static class StringP implements InjectableParameter {
		public String value

		new () {
			
		}

		new (String value) {
			this.value = value
		}
	}

	@FactoryParameter
	private static class EClassP implements InjectableParameter {
		public EClass value

		new () {
			
		}

		new (EClass value) {
			this.value = value
		}
	}

	private static class GenericInjectableObjectWithString extends GenericInjectableObject<StringP> {
		@Inject
		new (StringP value) {
			super(value)
		}
	}

	private static class GenericInjectableObjectWithEClass extends GenericInjectableObject<EClassP> {
		@Inject
		new (EClassP value) {
			super(value)
		}
	}

	/**
	 * The injected parameters are themselves injectable objects
	 * with injectable parameters.
	 * 
	 * Though we don't specify arguments in the factory for this
	 * constructor (GenericInjectableObjectWithString, GenericInjectableObjectWithEClass),
	 * Guice will be able to inject the arguments
	 * after injecting EStringP and EClassP into
	 * GenericInjectableObjectWithString, GenericInjectableObjectWithEClass.
	 */
	private static class ClassWithNestedInjectableObjects {
		public GenericInjectableObjectWithString withString
		public GenericInjectableObjectWithEClass withEClass

		@Inject
		new (GenericInjectableObjectWithString withString, GenericInjectableObjectWithEClass withEClass) {
			this.withString = withString
			this.withEClass = withEClass
		}
	}

	var InjectableObjectFactory factory

	@Before
	def void setupFactory() {
		factory = createInjector(new InjectableObjectModule).getInstance(InjectableObjectFactory)
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
	def void testCanCreateGenericInjectableObjects() {
		val injector = getOrCreateInjector
		val genericInjectableObjectStringFactory =
			injector.getInstance(
				Key.get(new TypeLiteral<GenericFactory<GenericInjectableObject<StringP>>>() {})
			)
		val genericInjectableObjectEClassFactory =
			injector.getInstance(
				Key.get(new TypeLiteral<GenericFactory<GenericInjectableObject<EClassP>>>() {})
			)
		val eclass = EcorePackage.eINSTANCE.EObject
		val o1 = genericInjectableObjectStringFactory
			.createInstance(GenericInjectableObjectWithString, new StringP("test"))
		val o2 = genericInjectableObjectEClassFactory
			.createInstance(GenericInjectableObjectWithEClass, new EClassP(eclass))
		"test".assertEquals(o1.value.value)
		eclass.assertEquals(o2.value.value)
	}

	@Test
	def void testCanInjectWithNestedInjectableObjects() {
		val injector = getOrCreateInjector
		val factory = injector.getInstance(
			Key.get(new TypeLiteral<GenericFactory<ClassWithNestedInjectableObjects>>() {})
		)
		val eclass = EcorePackage.eINSTANCE.EObject
		// StringP and EClassP are not directly arguments
		// for GenericFactory<ClassWithNestedInjectableObjects
		// they are arguments for the constructors of the arguments
		val o = factory.createInstance(
			ClassWithNestedInjectableObjects,
			new StringP("test"),
			new EClassP(eclass)
		)
		"test".assertEquals(o.withString.value.value)
		eclass.assertEquals(o.withEClass.value.value)
	}

	@Test(expected=NullPointerException)
	def void testInjectNull() {
		val injector = getOrCreateInjector
		val genericInjectableObjectStringFactory =
			injector.getInstance(
				Key.get(new TypeLiteral<GenericFactory<GenericInjectableObject<StringP>>>() {})
			)
		genericInjectableObjectStringFactory
			.createInstance(GenericInjectableObjectWithString, null)
	}

	@Test
	def void testMultiThreading() throws Exception {
		val injector = getOrCreateInjector
		val factory =
			injector.getInstance(
				Key.get(new TypeLiteral<GenericFactory<GenericInjectableObject<StringP>>>() {})
			)
		val threads = new ArrayList;
		val exceptions = new ArrayList;
		for (var i = 0; i <100; i++) {
			val thread = new Thread() {
				override void run() {
					try {
						factory.createInstance(GenericInjectableObjectWithString, new StringP("test"))
					} catch (Exception e) {
						exceptions.add(e);
					}
				}
			};
			threads.add(thread);
			thread.start();
		}
		for (Thread thread : threads) {
			thread.join();
		}
		if (!exceptions.isEmpty()) {
			throw exceptions.get(0);
		}
	}
}
