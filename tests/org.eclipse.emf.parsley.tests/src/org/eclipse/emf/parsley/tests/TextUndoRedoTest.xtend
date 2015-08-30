/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests

import org.eclipse.emf.parsley.composite.DialogControlFactory
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Control
import org.eclipse.swt.widgets.Event
import org.eclipse.swt.widgets.Widget
import org.junit.After
import org.junit.Before
import org.junit.Test

class TextUndoRedoTest extends AbstractControlFactoryTest {

	/**
	 * An instance to use for testing the creation of a Control
	 * using an AbstractControlFactory
	 */
	var protected ClassForControls classForControlsInstance

	var protected DialogControlFactory factory

	@Before
	def void setupEObject() {
		classForControlsInstance = testFactory.createClassForControls
		factory = createAndInitializeFactory
	}

	@After
	def void disposeFactory() {
		syncExecInRealm[|
			factory.dispose();
			return null
		]
	}

	@Test def void testUndoWithEmptyStack() {
		val control = setupControl
		ctrl_z(control)
		control.assertText("")
	}

	@Test def void testRedoWithEmptyStack() {
		val control = setupControl
		ctrl_y(control)
		control.assertText("")
	}

	@Test def void testUndo() {
		val control = setupControl
		control.modifyText("1")
		ctrl_z(control)
		control.assertText("")
	}

	@Test def void testNotUndo() {
		val control = setupControl
		control.modifyText("1")
		control.notifyKeyEventListeners(0, 'a')
		control.assertText("1")
	}

	@Test def void testNotUndo2() {
		val control = setupControl
		control.modifyText("1")
		control.notifyKeyEventListeners(SWT.ALT, 'z')
		control.assertText("1")
	}

	@Test def void testNotUndo3() {
		val control = setupControl
		control.modifyText("1")
		control.notifyKeyEventListeners(SWT.CTRL.bitwiseOr(SWT.ALT), 'z')
		control.assertText("1")
	}

	@Test def void testRedo() {
		val control = setupControl
		control.modifyText("1")
		ctrl_z(control)
		control.assertText("")
		ctrl_y(control)
		control.assertText("1")
	}

	@Test def void testRedo2() {
		val control = setupControl
		control.modifyText("1")
		ctrl_z(control)
		control.assertText("")
		ctrl_shift_z(control)
		control.assertText("1")
	}

	@Test def void testNotRedo() {
		val control = setupControl
		control.modifyText("1")
		ctrl_z(control)
		control.assertText("")
		control.notifyKeyEventListeners(SWT.CTRL.bitwiseOr(SWT.SHIFT), 'y')
		control.assertText("")
	}

	@Test def void testNotRedo2() {
		val control = setupControl
		control.modifyText("1")
		ctrl_z(control)
		control.assertText("")
		control.notifyKeyEventListeners(SWT.CTRL.bitwiseOr(SWT.SHIFT), 'a')
		control.assertText("")
	}

	@Test def void testNotRedo3() {
		val control = setupControl
		control.modifyText("1")
		ctrl_z(control)
		control.assertText("")
		control.notifyKeyEventListeners(SWT.CTRL, 'a')
		control.assertText("")
	}

	def private ctrl_z(Control control) {
		control.notifyKeyEventListeners(SWT.CTRL, 'z')
	}

	def private ctrl_y(Control control) {
		control.notifyKeyEventListeners(SWT.CTRL, 'y')
	}

	def private ctrl_shift_z(Control control) {
		control.notifyKeyEventListeners(SWT.CTRL.bitwiseOr(SWT.SHIFT), 'z')
	}

	def private setupControl() {
		val control = factory.createControl(testPackage.classForControls_StringFeature)
		control.assertTextEditable(true)
		control.assertText("")
		return control
	}

	def private createAndInitializeFactory() {
		new DialogControlFactory() => [initialize(classForControlsInstance)]
	}

	def private notifyKeyEventListeners(Widget w, int stateMask, char c) {
		val e = new Event()
		e.stateMask = stateMask
		e.keyCode = c
		w.notifyListeners(SWT.KeyDown, e)
		w.notifyListeners(SWT.KeyUp, e)
	}
}