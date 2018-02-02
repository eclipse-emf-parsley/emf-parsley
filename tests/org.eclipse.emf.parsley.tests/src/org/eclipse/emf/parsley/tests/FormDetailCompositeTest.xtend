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

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.edit.domain.EditingDomain
import org.eclipse.emf.parsley.composite.FormDetailComposite
import org.eclipse.emf.parsley.inject.parameters.CompositeParameters
import org.eclipse.emf.parsley.inject.parameters.EObjectParameter
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyControlBasedTest
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Composite
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*

/**
 * @author Lorenzo Bettini
 */
class FormDetailCompositeTest extends AbstractEmfParsleyControlBasedTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	static class TestableFormDetailComposite extends FormDetailComposite {
		/**
		 * public for tests
		 */
		new(Composite parent, int style, EObject object, EditingDomain editingDomain) {
			super(new CompositeParameters(parent, style), new EObjectParameter(object, editingDomain))
		}

		override getScrolledForm() {
			super.getScrolledForm()
		}

	}

	@Test def void testTitleIsUpdatedOnObjectChange() {
		val o = testFactory.createClassWithName => [name = "Test"]
		val formDetailComposite = new TestableFormDetailComposite(shell, SWT.NONE, o, editingDomain).injectMembers
		"Class With Name Test".assertEquals(formDetailComposite.scrolledForm.text)
		o.name = "Changed"
		syncExecVoid[
			"Class With Name Changed".assertEquals(formDetailComposite.scrolledForm.text)
		]
		formDetailComposite.dispose
	}

	@Test def void testTitleIsUpdatedOnObjectChangeWithoutEditingDomain() {
		val o = testFactory.createClassWithName => [name = "Test"]
		val formDetailComposite = new TestableFormDetailComposite(shell, SWT.NONE, o, null).injectMembers
		"Class With Name Test".assertEquals(formDetailComposite.scrolledForm.text)
		o.name = "Changed"
		syncExecVoid[
			"Class With Name Changed".assertEquals(formDetailComposite.scrolledForm.text)
		]
		formDetailComposite.dispose
	}

	@Test def void testDisposeImmediately() {
		val o = testFactory.createClassWithName => [name = "Test"]
		val formDetailComposite = new TestableFormDetailComposite(shell, SWT.NONE, o, null).injectMembers
		formDetailComposite.dispose
	}

}
