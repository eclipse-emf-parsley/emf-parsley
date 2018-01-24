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

import com.google.inject.ConfigurationException
import com.google.inject.Inject
import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule
import org.eclipse.emf.parsley.annotation.CompositeParent
import org.eclipse.emf.parsley.annotation.CompositeStyle
import org.eclipse.emf.parsley.composite.GenericCompositeFactory
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.eclipse.jface.viewers.IContentProvider
import org.eclipse.jface.viewers.ILabelProvider
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Composite
import org.junit.Before
import org.junit.Test

import static extension org.junit.Assert.*

class GenericCompositeFactoryTest extends AbstractEmfParsleyShellBasedTest {

	var GenericCompositeFactory factory

	static class CompositeWithoutAnnotationsForTests extends Composite {
		@Inject
		new(Composite parent, int style) {
			super(parent, style)
		}
	}

	static class CompositeWithAnnotationsForTests extends Composite {
		@Inject
		new(@CompositeParent Composite parent, @CompositeStyle int style) {
			super(parent, style)
		}
	}

	/**
	 * This also gets a ILabelProvider injected in the constructor and
	 * also has an injected field.
	 */
	static class CompositeWithAnnotationsForTestsCustom extends CompositeWithAnnotationsForTests {
		@Inject var public IContentProvider contentProvider
		var public ILabelProvider labelProvider

		@Inject
		new(@CompositeParent Composite parent, @CompositeStyle int style, ILabelProvider labelProvider) {
			super(parent, style)
			this.labelProvider = labelProvider
		}
	}

	/**
	 * This also gets a GenericCompositeFactory injected in the constructor and
	 * uses that for creating by injection a nested composite (with itself as the parent).
	 */
	static class CompositeWithWithNestedCompositeForTests extends Composite {
		var public CompositeWithAnnotationsForTests nested

		@Inject
		new(@CompositeParent Composite parent, @CompositeStyle int style, GenericCompositeFactory factory) {
			super(parent, style)
			nested = factory.create(CompositeWithAnnotationsForTests, this, style)
		}

	}

	@Before
	def void setupFactory() {
		factory = getOrCreateInjector.getInstance(GenericCompositeFactory)
	}

	@Test(expected=ConfigurationException)
	def void testCannotInjectWithoutAnnotations() {
		factory.create(CompositeWithoutAnnotationsForTests, shell, SWT.NONE)
	}

	@Test
	def void testCanInjectWithAnnotations() {
		val c = factory.create(CompositeWithAnnotationsForTests, shell, SWT.ARROW)
		shell.assertSame(c.parent)
		assertTrue("style should not be positive, instead is " + c.style, c.style > 0)
	}

	@Test
	def void testCustomImplementationOfCreatedType() {
		factory = createInjector(new EmfParsleyJavaGuiceModule() {
			def Class<? extends CompositeWithAnnotationsForTests> bindCompositeWithAnnotationsForTests() {
				CompositeWithAnnotationsForTestsCustom
			}
		}).getInstance(GenericCompositeFactory)
		val c = factory.create(CompositeWithAnnotationsForTests, shell, SWT.ARROW)
		CompositeWithAnnotationsForTestsCustom.assertSame(c.class)
		shell.assertSame(c.parent)
		assertTrue("style should not be positive, instead is " + c.style, c.style > 0)
		val cc = c as CompositeWithAnnotationsForTestsCustom
		assertNotNull(cc.contentProvider)
		assertNotNull(cc.labelProvider)
	}

	@Test
	def void testCanInjectNestedComposite() {
		val c = factory.create(CompositeWithWithNestedCompositeForTests, shell, SWT.ARROW)
		shell.assertSame(c.parent)
		assertTrue("style should not be positive, instead is " + c.style, c.style > 0)
		c.assertSame(c.nested.parent)
		assertTrue("style should not be positive, instead is " + c.nested.style, c.nested.style > 0)
	}
}
