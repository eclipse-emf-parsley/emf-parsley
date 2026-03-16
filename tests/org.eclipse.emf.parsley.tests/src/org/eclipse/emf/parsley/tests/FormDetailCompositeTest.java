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

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.composite.FormDetailComposite;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyControlBasedTest;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * @author Lorenzo Bettini
 */
public class FormDetailCompositeTest extends AbstractEmfParsleyControlBasedTest {

	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	@Inject
	EditingDomain editingDomain;

	private TestableFormDetailComposite formDetailComposite;

	static class TestableFormDetailComposite extends FormDetailComposite {
		/**
		 * public for tests
		 */
		public TestableFormDetailComposite(Composite parent, int style) {
			super(parent, style);
		}

		@Override
		public ScrolledForm getScrolledForm() {
			return super.getScrolledForm();
		}

	}

	/**
	 * For testing the case when isDisposed returns true while the form title
	 * adapter gets a notifyChanged
	 */
	static class FormDetailCompositeWithCustomIsDisposed extends FormDetailComposite {
		private boolean constructorCalled = false;

		public FormDetailCompositeWithCustomIsDisposed(Composite parent, int style) {
			super(parent, style);
			// during the constructor, isDisposed is called and during
			// the constructor isDisposed must NOT return true
			constructorCalled = true;
		}

		@Override
		public boolean isDisposed() {
			if (!constructorCalled)
				return super.isDisposed();
			else
				return true;
		}

	}

	@Before
	public void setupTestCase() {
		Injector injector = getOrCreateInjector();
		injector.injectMembers(this);
		formDetailComposite = new TestableFormDetailComposite(getShell(), SWT.NONE);
		injector.injectMembers(formDetailComposite);
	}

	@Test
	public void testTitleIsUpdatedOnObjectChange() {
		ClassWithName o = fixtures.getTestFactory().createClassWithName();
		o.setName("Test");
		formDetailComposite.init(o, editingDomain);
		assertEquals("Class With Name Test", formDetailComposite.getScrolledForm().getText());
		o.setName("Changed");
		syncExecVoid(() -> {
			assertEquals("Class With Name Changed", formDetailComposite.getScrolledForm().getText());
		});
		formDetailComposite.dispose();
	}
	
	@Test
	public void testTitleIsUpdatedOnObjectChangeWithoutEditingDomain() {
		ClassWithName o = fixtures.getTestFactory().createClassWithName();
		o.setName("Test");
		formDetailComposite.init(o);
		assertEquals("Class With Name Test", formDetailComposite.getScrolledForm().getText());
		o.setName("Changed");
		syncExecVoid(() -> {
			assertEquals("Class With Name Changed", formDetailComposite.getScrolledForm().getText());
		});
		formDetailComposite.dispose();
	}

	@Test
	public void testDisposeWhenInitIsNotCalled() { // NOSONAR: we just ensure it doesn't throw
		Injector injector = getOrCreateInjector();
		FormDetailComposite composite = new FormDetailComposite(getShell(), SWT.NONE);
		injector.injectMembers(composite);
		composite.dispose();
	}

	@Test
	public void testDisposeWhenWidgetIsDisposed() { // NOSONAR: we just ensure it doesn't throw
		Injector injector = getOrCreateInjector();
		FormDetailCompositeWithCustomIsDisposed formDetailComposite = new FormDetailCompositeWithCustomIsDisposed(getShell(), SWT.NONE);
		injector.injectMembers(formDetailComposite);
		ClassWithName o = fixtures.getTestFactory().createClassWithName();
		o.setName("Test");
		formDetailComposite.init(o);
		formDetailComposite.dispose();
	}

}
