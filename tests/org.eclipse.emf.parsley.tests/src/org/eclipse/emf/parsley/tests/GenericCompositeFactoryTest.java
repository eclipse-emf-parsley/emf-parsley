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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule;
import org.eclipse.emf.parsley.inject.GenericCompositeFactory;
import org.eclipse.emf.parsley.inject.InjectableComposite;
import org.eclipse.emf.parsley.inject.parameters.CompositeParameters;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.junit.Before;
import org.junit.Test;

public class GenericCompositeFactoryTest extends AbstractEmfParsleyShellBasedTest {

	private GenericCompositeFactory factory;

	public static class InjectableCompositeForTests extends InjectableComposite {
		@Inject
		public InjectableCompositeForTests(CompositeParameters params) {
			super(params);
		}
	}

	/**
	 * This also gets a ILabelProvider injected in the constructor and
	 * also has an injected field.
	 */
	public static class InjectableCompositeForTestsCustom extends InjectableCompositeForTests {
		@Inject
		public IContentProvider contentProvider;
		public ILabelProvider labelProvider;

		@Inject
		public InjectableCompositeForTestsCustom(CompositeParameters params, ILabelProvider labelProvider) {
			super(params);
			this.labelProvider = labelProvider;
		}
	}

	/**
	 * This also gets a GenericCompositeFactory injected in the constructor and
	 * uses that for creating by injection a nested composite (with itself as the parent).
	 */
	public static class InjectableCompositeWithNestedCompositeForTests extends InjectableComposite {
		public InjectableCompositeForTests nested;

		@Inject
		public InjectableCompositeWithNestedCompositeForTests(CompositeParameters params, GenericCompositeFactory factory) {
			super(params);
			nested = factory.create(InjectableCompositeForTests.class, this, getStyle());
		}
	}

	@Singleton
	public static class SingletonToInject {
	}

	/**
	 * This also gets an injected field that is expected to be a singleton.
	 */
	public static class InjectableCompositeWithSingletonForTests extends InjectableComposite {
		@Inject
		public SingletonToInject singleton;

		@Inject
		public InjectableCompositeWithSingletonForTests(CompositeParameters params) {
			super(params);
		}
	}

	@Before
	public void setupFactory() {
		factory = getOrCreateInjector().getInstance(GenericCompositeFactory.class);
	}

	@Test
	public void testCanCreateInjectableComposite() {
		var c = factory.create(InjectableCompositeForTests.class, getShell(), SWT.ARROW);
		assertSame(getShell(), c.getParent());
		assertTrue("style should not be positive, instead is " + c.getStyle(), c.getStyle() > 0);
	}

	@Test
	public void testCustomImplementationOfCreatedType() {
		factory = createInjector(new EmfParsleyJavaGuiceModule() {
			public Class<? extends InjectableCompositeForTests> bindInjectableCompositeForTests() {
				return InjectableCompositeForTestsCustom.class;
			}
		}).getInstance(GenericCompositeFactory.class);
		var c = factory.create(InjectableCompositeForTests.class, getShell(), SWT.ARROW);
		assertSame(InjectableCompositeForTestsCustom.class, c.getClass());
		assertSame(getShell(), c.getParent());
		assertTrue("style should not be positive, instead is " + c.getStyle(), c.getStyle() > 0);
		var cc = (InjectableCompositeForTestsCustom) c;
		assertNotNull(cc.contentProvider);
		assertNotNull(cc.labelProvider);
	}

	@Test
	public void testCanInjectNestedComposite() {
		var c = factory.create(InjectableCompositeWithNestedCompositeForTests.class, getShell(), SWT.ARROW);
		assertSame(getShell(), c.getParent());
		assertTrue("style should not be positive, instead is " + c.getStyle(), c.getStyle() > 0);
		assertSame(c, c.nested.getParent());
		assertTrue("style should not be positive, instead is " + c.nested.getStyle(), c.nested.getStyle() > 0);
	}

	@Test
	public void testSingletonIsRespected() {
		var singleton = getOrCreateInjector().getInstance(SingletonToInject.class);
		var c = factory.create(InjectableCompositeWithSingletonForTests.class, getShell(), SWT.ARROW);
		assertSame(singleton, c.singleton);
	}
}
