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
import static org.junit.Assert.assertNotNull;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.ProvisionException;
import com.google.inject.Singleton;

import org.eclipse.emf.parsley.inject.AfterInject;
import org.eclipse.emf.parsley.inject.EmfParsleyLifecycle;
import org.eclipse.emf.parsley.internal.inject.AfterInjectTypeListener;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest;
import org.junit.Test;

public class AfterInjectTypeListenerTest extends AbstractEmfParsleyShellBasedTest {

	private static class Foo {
	}

	private static class Bar {
	}

	private static class FooBar {
	}

	@EmfParsleyLifecycle
	private static abstract class MyClass {
		@Inject
		Foo foo;
		Bar bar;
		FooBar fooBar;

		@Inject
		MyClass(Bar bar) {
			this.bar = bar;
		}

		/**
		 * This will be called only after all fields and methods are injected (even in
		 * subclasses).
		 */
		@AfterInject
		private void init() {
			fooBar = createFooBar();
		}

		/**
		 * Otherwise we get a warning that init() is never called locally;
		 * it will be called during Inject lifecycle
		 */
		public void justToAvoidNeverUsedLocally() {
			init();
		}

		protected abstract FooBar createFooBar();

		public Foo getFoo() {
			return foo;
		}

		public Bar getBar() {
			return bar;
		}

		public FooBar getFooBar() {
			return fooBar;
		}
	}

	private static class MySubclass extends MyClass {
		@Inject
		Provider<FooBar> fooBarProvider;

		@Inject
		MySubclass(Bar bar) {
			super(bar);
		}

		@Override
		protected FooBar createFooBar() {
			return fooBarProvider.get();
		}
	}

	private static class MyWrongSubclass extends MyClass {
		@Inject
		MyWrongSubclass(Bar bar) {
			super(bar);
		}

		@Override
		protected FooBar createFooBar() {
			throw new RuntimeException("intentional");
		}
	}

	@Singleton
	private static class Appender {
		StringBuilder builder = new StringBuilder();

		public void append(String s) {
			builder.append(s);
		}

		@Override
		public String toString() {
			return builder.toString();
		}
	}

	@EmfParsleyLifecycle
	private static class Base {
		@Inject
		Appender appender;

		@AfterInject
		protected void baseInit() {
			appender.append("Base");
		}
	}

	@EmfParsleyLifecycle
	private static class Derived extends Base {
		@Inject
		Appender appender;

		@AfterInject
		protected void derivedInit() {
			appender.append("Derived");
		}
	}

	private static class AfterInjectModule extends AbstractModule {

		@Override
		protected void configure() {
			AfterInjectTypeListener.bindAfterInjectTypeListener(binder());
			bind(MyClass.class).to(MySubclass.class);
		}
	}

	@Test
	public void testOk() {
		var injector = Guice.createInjector(new AfterInjectModule());
		var o = injector.getInstance(MyClass.class);
		assertNotNull(o.getFoo());
		assertNotNull(o.getBar());
		assertNotNull(o.getFooBar());
	}

	@Test(expected = ProvisionException.class)
	public void testWrongImplementation() {
		var injector = Guice.createInjector(new AfterInjectModule());
		injector.getInstance(MyWrongSubclass.class);
	}

	@Test
	public void testSuperClassAfterInjectAreCalledFirst() {
		var injector = Guice.createInjector(new AfterInjectModule());
		var appender = injector.getInstance(Appender.class);
		injector.getInstance(Derived.class);
		assertEquals("BaseDerived", appender.toString());
	}
}
