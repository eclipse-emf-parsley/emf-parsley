/*******************************************************************************
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
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
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.composite.FormDetailComposite;
import org.eclipse.emf.parsley.inject.parameters.CompositeParameters;
import org.eclipse.emf.parsley.inject.parameters.EObjectParameter;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyControlBasedTest;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Lorenzo Bettini
 */
public class FormDetailCompositeTest extends AbstractEmfParsleyControlBasedTest {

	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	public static class TestableFormDetailComposite extends FormDetailComposite {
		/**
		 * public for tests
		 */
		public TestableFormDetailComposite(Composite parent, int style, EObject object, EditingDomain editingDomain) {
			super(new CompositeParameters(parent, style), new EObjectParameter(object, editingDomain));
		}

		@Override
		public ScrolledForm getScrolledForm() {
			return super.getScrolledForm();
		}
	}

	@Test
	public void testTitleIsUpdatedOnObjectChange() {
		var o = fixtures.createClassWithName("Test");
		var formDetailComposite = injectMembers(new TestableFormDetailComposite(getShell(), SWT.NONE, o, fixtures.getEditingDomain()));
		assertEquals("Class With Name Test", formDetailComposite.getScrolledForm().getText());
		o.setName("Changed");
		syncExecVoid(() -> {
			assertEquals("Class With Name Changed", formDetailComposite.getScrolledForm().getText());
		});
		formDetailComposite.dispose();
	}

	@Test
	public void testTitleIsUpdatedOnObjectChangeWithoutEditingDomain() {
		var o = fixtures.createClassWithName("Test");
		var formDetailComposite = injectMembers(new TestableFormDetailComposite(getShell(), SWT.NONE, o, null));
		assertEquals("Class With Name Test", formDetailComposite.getScrolledForm().getText());
		o.setName("Changed");
		syncExecVoid(() -> {
			assertEquals("Class With Name Changed", formDetailComposite.getScrolledForm().getText());
		});
		formDetailComposite.dispose();
	}

	@Test
	public void testDisposeImmediately() {
		var o = fixtures.createClassWithName("Test");
		var formDetailComposite = injectMembers(new TestableFormDetailComposite(getShell(), SWT.NONE, o, null));
		formDetailComposite.dispose();
		assertTrue(formDetailComposite.isDisposed());
	}
}
