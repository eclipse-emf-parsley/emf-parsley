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
package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.google.inject.util.Modules;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule;
import org.eclipse.emf.parsley.inject.parameters.FactoryParameter;
import org.eclipse.emf.parsley.inject.parameters.InjectableParameter;
import org.eclipse.emf.parsley.internal.inject.GenericFactory;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest;
import org.junit.Before;
import org.junit.Test;

public class GenericFactoryTest extends AbstractEmfParsleyShellBasedTest {

	@FactoryParameter
	private static class MyInjectableParameter implements InjectableParameter {
		public int i;

		public MyInjectableParameter() {
		}

		public MyInjectableParameter(int i) {
			this.i = i;
		}
	}

	private interface InjectableObjectInterface {
	}

	private static class InjectableObject implements InjectableObjectInterface {
		public MyInjectableParameter param;

		@Inject
		public InjectableObject(MyInjectableParameter param) {
			this.param = param;
		}
	}

	private static class AnotherInjectableObject implements InjectableObjectInterface {
		public MyInjectableParameter param;

		@Inject
		public AnotherInjectableObject(MyInjectableParameter param) {
			this.param = param;
		}
	}

	private static class InjectableObjectCustom extends InjectableObject {
		public AnotherInjectableObject nested;

		@Inject
		public InjectableObjectCustom(MyInjectableParameter param, InjectableObjectFactory factory) {
			super(param);
			// pass an incremented integer
			nested = factory.create(AnotherInjectableObject.class, param.i + 1);
		}
	}

	private static class InjectableObjectFactory {
		@Inject
		private GenericFactory<InjectableObjectInterface> internalFactory;

		public <T extends InjectableObjectInterface> T create(Class<T> type, int param) {
			return internalFactory.createInstance(type, new MyInjectableParameter(param));
		}
	}

	private static class InjectableObjectModule extends EmfParsleyJavaGuiceModule {
		@Override
		public void configure(Binder binder) {
			super.configure(binder);
		}
	}

	@FactoryParameter
	private static class GenericInjectableObject<T extends InjectableParameter> {
		public T value;

		@Inject
		public GenericInjectableObject(T value) {
			this.value = value;
		}
	}

	@FactoryParameter
	private static class StringP implements InjectableParameter {
		public String value;

		public StringP() {
		}

		public StringP(String value) {
			this.value = value;
		}
	}

	@FactoryParameter
	private static class EClassP implements InjectableParameter {
		public EClass value;

		public EClassP() {
		}

		public EClassP(EClass value) {
			this.value = value;
		}
	}

	private static class GenericInjectableObjectWithString extends GenericInjectableObject<StringP> {
		@Inject
		public GenericInjectableObjectWithString(StringP value) {
			super(value);
		}
	}

	private static class GenericInjectableObjectWithEClass extends GenericInjectableObject<EClassP> {
		@Inject
		public GenericInjectableObjectWithEClass(EClassP value) {
			super(value);
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
		public GenericInjectableObjectWithString withString;
		public GenericInjectableObjectWithEClass withEClass;

		@Inject
		public ClassWithNestedInjectableObjects(GenericInjectableObjectWithString withString,
				GenericInjectableObjectWithEClass withEClass) {
			this.withString = withString;
			this.withEClass = withEClass;
		}
	}

	private InjectableObjectFactory factory;

	@Before
	public void setupFactory() {
		factory = createInjector(new InjectableObjectModule()).getInstance(InjectableObjectFactory.class);
	}

	@Test
	public void testCanCreateInjectableObject() {
		var c = factory.create(InjectableObject.class, 10);
		assertEquals(10, c.param.i);
	}

	@Test
	public void testCanCreateSeveralInjectableObject() {
		var c = factory.create(InjectableObject.class, 10);
		assertEquals(10, c.param.i);
		c = factory.create(InjectableObject.class, 20);
		assertEquals(20, c.param.i);
	}

	@Test
	public void testCanCreateInjectableObjectCustom() {
		factory = createInjector(new InjectableObjectModule() {
			@Override
			public void configure(Binder binder) {
				super.configure(binder);
				binder.bind(InjectableObject.class).to(InjectableObjectCustom.class);
			}
		}).getInstance(InjectableObjectFactory.class);
		var c = (InjectableObjectCustom) factory.create(InjectableObject.class, 10);
		assertEquals(10, c.param.i);
		assertEquals(11, c.nested.param.i); // the nested is passed an incremented integer
	}

	@Test
	public void testCanCreateInjectableObjectCustomWithModuleOverride() {
		var override = Modules.override(new InjectableObjectModule()).with(new AbstractModule() {
			@Override
			protected void configure() {
				bind(InjectableObject.class).to(InjectableObjectCustom.class);
			}
		});
		factory = createInjector(override).getInstance(InjectableObjectFactory.class);
		var c = (InjectableObjectCustom) factory.create(InjectableObject.class, 10);
		assertEquals(10, c.param.i);
		assertEquals(11, c.nested.param.i); // the nested is passed an incremented integer
	}

	@Test
	public void testCanCreateGenericInjectableObjects() {
		var injector = getOrCreateInjector();
		var genericInjectableObjectStringFactory =
			injector.getInstance(
				Key.get(new TypeLiteral<GenericFactory<GenericInjectableObject<StringP>>>() {})
			);
		var genericInjectableObjectEClassFactory =
			injector.getInstance(
				Key.get(new TypeLiteral<GenericFactory<GenericInjectableObject<EClassP>>>() {})
			);
		var eclass = EcorePackage.eINSTANCE.getEObject();
		var o1 = genericInjectableObjectStringFactory
			.createInstance(GenericInjectableObjectWithString.class, new StringP("test"));
		var o2 = genericInjectableObjectEClassFactory
			.createInstance(GenericInjectableObjectWithEClass.class, new EClassP(eclass));
		assertEquals("test", o1.value.value);
		assertEquals(eclass, o2.value.value);
	}

	@Test
	public void testCanInjectWithNestedInjectableObjects() {
		var injector = getOrCreateInjector();
		var factory = injector.getInstance(
			Key.get(new TypeLiteral<GenericFactory<ClassWithNestedInjectableObjects>>() {})
		);
		var eclass = EcorePackage.eINSTANCE.getEObject();
		// StringP and EClassP are not directly arguments
		// for GenericFactory<ClassWithNestedInjectableObjects
		// they are arguments for the constructors of the arguments
		var o = factory.createInstance(
			ClassWithNestedInjectableObjects.class,
			new StringP("test"),
			new EClassP(eclass)
		);
		assertEquals("test", o.withString.value.value);
		assertEquals(eclass, o.withEClass.value.value);
	}

	@Test(expected = NullPointerException.class)
	public void testInjectNull() {
		var injector = getOrCreateInjector();
		var genericInjectableObjectStringFactory =
			injector.getInstance(
				Key.get(new TypeLiteral<GenericFactory<GenericInjectableObject<StringP>>>() {})
			);
		genericInjectableObjectStringFactory
			.createInstance(GenericInjectableObjectWithString.class, (StringP) null);
	}

	@Test
	public void testMultiThreading() throws Exception {
		var injector = getOrCreateInjector();
		var factory =
			injector.getInstance(
				Key.get(new TypeLiteral<GenericFactory<GenericInjectableObject<StringP>>>() {})
			);
		var threads = new ArrayList<Thread>();
		var exceptions = new ArrayList<Exception>();
		for (var i = 0; i < 100; i++) {
			var thread = new Thread() {
				@Override
				public void run() {
					try {
						factory.createInstance(GenericInjectableObjectWithString.class, new StringP("test"));
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
