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
import com.google.inject.Guice
import com.google.inject.Inject
import com.google.inject.Provider
import com.google.inject.ProvisionException
import org.eclipse.emf.parsley.inject.AfterInject
import org.eclipse.emf.parsley.internal.inject.AfterInjectTypeListener
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.junit.Test

import static extension org.junit.Assert.*
import org.eclipse.emf.parsley.inject.EmfParsleyLifecycle
import com.google.inject.Singleton

class AfterInjectTypeListenerTest extends AbstractEmfParsleyShellBasedTest {

	static private class Foo {
	}

	static private class Bar {
	}

	static private class FooBar {
	}

	@EmfParsleyLifecycle
	static private abstract class MyClass {
		@Inject
		Foo foo;
		Bar bar;
		var FooBar fooBar;

		@Inject
		new(Bar bar) {
			this.bar = bar;
		}

		/**
		 * This will be called only after all fields and methods are injected (even in
		 * subclasses).
		 */
		@AfterInject
		def private void init() {
			fooBar = createFooBar();
		}

		/**
		 * Otherwise we get a warning that init() is never called locally;
		 * it will be called during Inject lifecycle
		 */
		def void justToAvoidNeverUsedLocally() {
			init();
		}

		def protected abstract FooBar createFooBar();

		def Foo getFoo() {
			return foo;
		}

		def Bar getBar() {
			return bar;
		}

		def FooBar getFooBar() {
			return fooBar;
		}

	}

	static private class MySubclass extends MyClass {
		@Inject
		Provider<FooBar> fooBarProvider;

		@Inject
		new(Bar bar) {
			super(bar);
		}

		override protected FooBar createFooBar() {
			return fooBarProvider.get();
		}

	}

	static private class MyWrongSubclass extends MyClass {
		@Inject
		new(Bar bar) {
			super(bar);
		}

		override protected FooBar createFooBar() {
			throw new RuntimeException("intentional");
		}

	}

	@Singleton
	static private class Appender {
		StringBuilder builder = new StringBuilder

		def void append(String s) {
			builder.append(s)
		}

		override toString() {
			builder.toString
		}
	}

	@EmfParsleyLifecycle
	static private class Base {
		@Inject var Appender appender
	
		@AfterInject
		def protected baseInit() {
			appender.append("Base")
		}
	}

	@EmfParsleyLifecycle
	static private class Derived extends Base {
		@Inject var Appender appender
	
		@AfterInject
		def protected derivedInit() {
			appender.append("Derived")
		}
	}

	static private class AfterInjectModule extends AbstractModule {

		override protected void configure() {
			AfterInjectTypeListener.bindAfterInjectTypeListener(binder());
			bind(MyClass).to(MySubclass);
		}

	}

	@Test
	def void testOk() {
		val injector = Guice.createInjector(new AfterInjectModule());
		val o = injector.getInstance(MyClass);
		assertNotNull(o.getFoo());
		assertNotNull(o.getBar());
		assertNotNull(o.getFooBar());
	}

	@Test(expected=ProvisionException)
	def void testWrongImplementation() {
		val injector = Guice.createInjector(new AfterInjectModule());
		injector.getInstance(MyWrongSubclass);
	}

	@Test
	def void testSuperClassAfterInjectAreCalledFirst() {
		val injector = Guice.createInjector(new AfterInjectModule());
		val appender = injector.getInstance(Appender)
		injector.getInstance(Derived)
		"BaseDerived".assertEquals(appender.toString)
	}
}
