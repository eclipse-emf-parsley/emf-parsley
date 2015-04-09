/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
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
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.parsley.binding.AbstractControlFactory
import org.eclipse.emf.parsley.binding.DialogControlFactory
import org.eclipse.emf.parsley.binding.FormControlFactory
import org.eclipse.emf.parsley.binding.MultipleFeatureControl
import org.eclipse.emf.parsley.binding.ProposalCreator
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.eclipse.emf.parsley.junit4.util.TestDefaultRealm
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesTestRule
import org.eclipse.jface.viewers.ISelectionProvider
import org.eclipse.jface.viewers.StructuredSelection
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Combo
import org.eclipse.swt.widgets.Control
import org.eclipse.swt.widgets.Text
import org.eclipse.ui.forms.widgets.FormToolkit
import org.junit.After
import org.junit.Before
import org.junit.Rule

import static org.junit.Assert.*

abstract class OldAbstractControlFactoryTest extends AbstractEmfParsleyShellBasedTest {
	
	var TestDefaultRealm realm;
	
	@Rule public extension EmfParsleyFixturesTestRule fixtures = new EmfParsleyFixturesTestRule()
	
	@Before
	def void setupRealm() {
		realm = new TestDefaultRealm();
	}

	@After
	def void disposeRealm() {
		realm.dispose();
	}

	def protected getEditingDomain() {
		return fixtures.editingDomain
	}

	def protected createResourceSet() {
		return fixtures.createResourceSet
	}

	def protected initialize(DialogControlFactory controlFactory, EObject obj) {
		controlFactory.initializeCommon(obj)
		controlFactory.init(getEditingDomain(), obj, shell)
		// shell must be visibile since we need to check visibility of some controls
		shell.open
	}

	def protected initialize(FormControlFactory controlFactory, EObject obj) {
		controlFactory.initializeCommon(obj)
		// shell must be visibile since we need to check visibility of some controls
		shell.open
		// FormToolkit must be created in the UI thread
		// and the initialization requires databinding, and thus the Realm
		syncExecInRealm[
			controlFactory.init(getEditingDomain(), obj, shell, new FormToolkit(display))
			true
		]
	}

	def protected initializeCommon(AbstractControlFactory controlFactory, EObject obj) {
		controlFactory.proposalCreator = new ProposalCreator
		injectMembers(controlFactory)
	}

	def protected createControl(AbstractControlFactory factory, EStructuralFeature feature) {
		syncExecInRealm(|factory.create(feature))
	}

	def protected assertCheckbox(Control control, boolean checked) {
		control.assertControlClass(Button)
		val button = control as Button;
		syncExecVoid[
			assertTrue("not a checkbox", (button.style.bitwiseAnd(SWT.CHECK) != 0));
			assertEquals(checked, button.selection)
		]
	}

	def protected assertEnabled(Control control, boolean expectedEnabled) {
		syncExecVoid[
			assertEquals(expectedEnabled, control.getEnabled)
		]
	}

	/**
	 * The expectedElements is of the shape (not the space after the comma)
	 * "elem1, elem2, elem3"
	 */
	def protected assertCombo(Control control, String expectedElements, int selected) {
		control.assertControlClass(Combo)
		val combo = control as Combo;
		syncExecVoid[
			assertEquals(expectedElements, combo.items.map[toString].join(", "))
			assertEquals(selected, combo.selectionIndex)
		]
	}

	def protected assertText(Control control, String expectedText) {
		control.assertControlClass(Text)
		val text = control as Text;
		syncExecVoid[
			assertEquals(expectedText, text.text)
		]
	}

	def protected assertMultipleFeatureControl(Control control, String expectedLabelText, boolean isButtonVisible) {
		assertMultipleFeatureControl(control, expectedLabelText, isButtonVisible, true)
	}

	def protected assertMultipleFeatureControl(Control control, String expectedLabelText, boolean isButtonVisible, boolean isButtonEnabled) {
		control.assertControlClass(MultipleFeatureControl)
		val mfc = control as MultipleFeatureControl;
		syncExecVoid[
			assertEquals("...", mfc.button.text)
			assertEquals(expectedLabelText, mfc.label.text)
			assertEquals(isButtonVisible, mfc.button.isVisible)
			assertEquals(isButtonEnabled, mfc.button.isEnabled)
		]
	}

	def protected void setSelectionProgrammatically(ISelectionProvider selectionProvider, EObject...elements) {
		syncExecVoid[
			selectionProvider.setSelection(new StructuredSelection(elements))
		]
	}

	def protected assertTextEditable(Control control, boolean expectedEditable) {
		control.assertControlClass(Text)
		val text = control as Text;
		syncExecVoid[
			assertEquals(expectedEditable, text.editable)
		]
	}

	def protected assertTextEnabled(Control control, boolean expectedEnabled) {
		control.assertControlClass(Text)
		val text = control as Text;
		syncExecVoid[
			assertEquals(expectedEnabled, text.isEnabled)
		]
	}

	def protected assertControlClass(Control control, Class<? extends Control> clazz) {
		assertTrue("expected class: " + clazz.simpleName + 
			", actual class: " + control.class.simpleName, 
			clazz.isAssignableFrom(control.class)
		)
	}

}