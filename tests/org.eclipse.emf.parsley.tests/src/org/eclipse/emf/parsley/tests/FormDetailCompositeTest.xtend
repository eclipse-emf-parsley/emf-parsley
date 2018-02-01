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
package org.eclipse.emf.parsley.tests

import com.google.inject.Inject
import org.eclipse.emf.edit.domain.EditingDomain
import org.eclipse.emf.parsley.composite.FormDetailComposite
import org.eclipse.emf.parsley.inject.parameters.CompositeParameters
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyControlBasedTest
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Composite
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*

/**
 * @author Lorenzo Bettini
 */
class FormDetailCompositeTest extends AbstractEmfParsleyControlBasedTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	@Inject EditingDomain editingDomain

	var TestableFormDetailComposite formDetailComposite

	static class TestableFormDetailComposite extends FormDetailComposite {
		/**
		 * public for tests
		 */
		new(Composite parent, int style) {
			super(new CompositeParameters(parent, style))
		}

		override getScrolledForm() {
			super.getScrolledForm()
		}

	}

	/**
	 * For testing the case when isDisposed returns true while the form title
	 * adapter gets a notifyChanged
	 */
	static class FormDetailCompositeWithCustomIsDisposed extends FormDetailComposite {
		var constructorCalled = false

		new(Composite parent, int style) {
			super(new CompositeParameters(parent, style))
			// during the constructor, isDisposed is called and during
			// the constructor isDisposed must NOT return true
			constructorCalled = true
		}

		override isDisposed() {
			if (!constructorCalled)
				return super.isDisposed()
			else
				return true
		}

	}

	@Before def void setupTestCase() {
		val injector = getOrCreateInjector
		injector.injectMembers(this)
		formDetailComposite = new TestableFormDetailComposite(shell, SWT.NONE)
		injector.injectMembers(formDetailComposite)
	}

	@Test def void testTitleIsUpdatedOnObjectChange() {
		val o = testFactory.createClassWithName => [ name = "Test" ]
		formDetailComposite.init(o, editingDomain)
		"Class With Name Test".assertEquals(formDetailComposite.scrolledForm.text)
		o.name = "Changed"
		syncExecVoid[
			"Class With Name Changed".assertEquals(formDetailComposite.scrolledForm.text)
		]
		formDetailComposite.dispose
	}
	
	@Test def void testTitleIsUpdatedOnObjectChangeWithoutEditingDomain() {
		val o = testFactory.createClassWithName => [ name = "Test" ]
		formDetailComposite.init(o)
		"Class With Name Test".assertEquals(formDetailComposite.scrolledForm.text)
		o.name = "Changed"
		syncExecVoid[
			"Class With Name Changed".assertEquals(formDetailComposite.scrolledForm.text)
		]
		formDetailComposite.dispose
	}

	@Test def void testDisposeWhenInitIsNotCalled() {
		val injector = getOrCreateInjector
		val formDetailComposite = new FormDetailComposite(new CompositeParameters(shell, SWT.NONE))
		injector.injectMembers(formDetailComposite)
		formDetailComposite.dispose
	}

	@Test def void testDisposeWhenWidgetIsDisposed() {
		val injector = getOrCreateInjector
		val formDetailComposite = new FormDetailCompositeWithCustomIsDisposed(shell, SWT.NONE)
		injector.injectMembers(formDetailComposite)
		val o = testFactory.createClassWithName => [ name = "Test" ]
		formDetailComposite.init(o)
		formDetailComposite.dispose
	}

}
