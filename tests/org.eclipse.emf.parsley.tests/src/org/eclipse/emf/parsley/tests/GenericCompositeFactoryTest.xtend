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

import com.google.inject.Inject
import com.google.inject.Singleton
import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule
import org.eclipse.emf.parsley.inject.CompositeParameters
import org.eclipse.emf.parsley.inject.GenericCompositeFactory
import org.eclipse.emf.parsley.inject.InjectableComposite
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.eclipse.jface.viewers.IContentProvider
import org.eclipse.jface.viewers.ILabelProvider
import org.eclipse.swt.SWT
import org.junit.Before
import org.junit.Test

import static extension org.junit.Assert.*

class GenericCompositeFactoryTest extends AbstractEmfParsleyShellBasedTest {

	var GenericCompositeFactory factory

	static class InjectableCompositeForTests extends InjectableComposite {
		@Inject
		new(CompositeParameters params) {
			super(params)
		}
	}

	/**
	 * This also gets a ILabelProvider injected in the constructor and
	 * also has an injected field.
	 */
	static class InjectableCompositeForTestsCustom extends InjectableCompositeForTests {
		@Inject var public IContentProvider contentProvider
		var public ILabelProvider labelProvider

		@Inject
		new(CompositeParameters params, ILabelProvider labelProvider) {
			super(params)
			this.labelProvider = labelProvider
		}
	}

	/**
	 * This also gets a GenericCompositeFactory injected in the constructor and
	 * uses that for creating by injection a nested composite (with itself as the parent).
	 */
	static class InjectableCompositeWithNestedCompositeForTests extends InjectableComposite {
		var public InjectableCompositeForTests nested

		@Inject
		new(CompositeParameters params, GenericCompositeFactory factory) {
			super(params)
			nested = factory.create(InjectableCompositeForTests, this, style)
		}

	}

	@Singleton
	static class SingletonToInject {
		
	}

	/**
	 * This also gets an injected field that is expected to be a singleton.
	 */
	static class InjectableCompositeWithSingletonForTests extends InjectableComposite {
		@Inject var public SingletonToInject singleton

		@Inject
		new(CompositeParameters params) {
			super(params)
		}
	}

	@Before
	def void setupFactory() {
		factory = getOrCreateInjector.getInstance(GenericCompositeFactory)
	}

	@Test
	def void testCanCreateInjectableComposite() {
		val c = factory.create(InjectableCompositeForTests, shell, SWT.ARROW)
		shell.assertSame(c.parent)
		assertTrue("style should not be positive, instead is " + c.style, c.style > 0)
	}

	@Test
	def void testCustomImplementationOfCreatedType() {
		factory = createInjector(new EmfParsleyJavaGuiceModule() {
			def Class<? extends InjectableCompositeForTests> bindInjectableCompositeForTests() {
				InjectableCompositeForTestsCustom
			}
		}).getInstance(GenericCompositeFactory)
		val c = factory.create(InjectableCompositeForTests, shell, SWT.ARROW)
		InjectableCompositeForTestsCustom.assertSame(c.class)
		shell.assertSame(c.parent)
		assertTrue("style should not be positive, instead is " + c.style, c.style > 0)
		val cc = c as InjectableCompositeForTestsCustom
		assertNotNull(cc.contentProvider)
		assertNotNull(cc.labelProvider)
	}

	@Test
	def void testCanInjectNestedComposite() {
		val c = factory.create(InjectableCompositeWithNestedCompositeForTests, shell, SWT.ARROW)
		shell.assertSame(c.parent)
		assertTrue("style should not be positive, instead is " + c.style, c.style > 0)
		c.assertSame(c.nested.parent)
		assertTrue("style should not be positive, instead is " + c.nested.style, c.nested.style > 0)
	}

	@Test
	def void testSingletonIsRespected() {
		val singleton = getOrCreateInjector.getInstance(SingletonToInject)
		val c = factory.create(InjectableCompositeWithSingletonForTests, shell, SWT.ARROW)
		singleton.assertSame(c.singleton)
	}
}
