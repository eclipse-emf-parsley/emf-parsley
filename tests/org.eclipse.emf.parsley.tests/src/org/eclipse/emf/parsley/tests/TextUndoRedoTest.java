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
package org.eclipse.emf.parsley.tests;

import org.eclipse.emf.parsley.composite.DialogControlFactory;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Widget;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TextUndoRedoTest extends AbstractControlFactoryTest {

	/**
	 * An instance to use for testing the creation of a Control
	 * using an AbstractControlFactory
	 */
	protected ClassForControls classForControlsInstance;

	protected DialogControlFactory factory;

	@Before
	public void setupEObject() {
		classForControlsInstance = fixtures.getTestFactory().createClassForControls();
		factory = createAndInitializeFactory();
	}

	@After
	public void disposeFactory() {
		syncExecInRealm(() -> {
			factory.dispose();
			return null;
		});
	}

	@Test
	public void testUndoWithEmptyStack() {
		Control control = setupControl();
		ctrl_z(control);
		assertText(control, "");
	}

	@Test
	public void testRedoWithEmptyStack() {
		Control control = setupControl();
		ctrl_y(control);
		assertText(control, "");
	}

	@Test
	public void testUndo() {
		Control control = setupControl();
		modifyText(control, "1");
		ctrl_z(control);
		assertText(control, "");
	}

	@Test
	public void testNotUndo() {
		Control control = setupControl();
		modifyText(control, "1");
		notifyKeyEventListeners(control, 0, 'a');
		assertText(control, "1");
	}

	@Test
	public void testNotUndo2() {
		Control control = setupControl();
		modifyText(control, "1");
		notifyKeyEventListeners(control, SWT.ALT, 'z');
		assertText(control, "1");
	}

	@Test
	public void testNotUndo3() {
		Control control = setupControl();
		modifyText(control, "1");
		notifyKeyEventListeners(control, SWT.CTRL | SWT.ALT, 'z');
		assertText(control, "1");
	}

	@Test
	public void testRedo() {
		Control control = setupControl();
		modifyText(control, "1");
		ctrl_z(control);
		assertText(control, "");
		ctrl_y(control);
		assertText(control, "1");
	}

	@Test
	public void testRedo2() {
		Control control = setupControl();
		modifyText(control, "1");
		ctrl_z(control);
		assertText(control, "");
		ctrl_shift_z(control);
		assertText(control, "1");
	}

	@Test
	public void testNotRedo() {
		Control control = setupControl();
		modifyText(control, "1");
		ctrl_z(control);
		assertText(control, "");
		notifyKeyEventListeners(control, SWT.CTRL | SWT.SHIFT, 'y');
		assertText(control, "");
	}

	@Test
	public void testNotRedo2() {
		Control control = setupControl();
		modifyText(control, "1");
		ctrl_z(control);
		assertText(control, "");
		notifyKeyEventListeners(control, SWT.CTRL | SWT.SHIFT, 'a');
		assertText(control, "");
	}

	@Test
	public void testNotRedo3() {
		Control control = setupControl();
		modifyText(control, "1");
		ctrl_z(control);
		assertText(control, "");
		notifyKeyEventListeners(control, SWT.CTRL, 'a');
		assertText(control, "");
	}

	private void ctrl_z(Control control) {
		notifyKeyEventListeners(control, SWT.CTRL, 'z');
	}

	private void ctrl_y(Control control) {
		notifyKeyEventListeners(control, SWT.CTRL, 'y');
	}

	private void ctrl_shift_z(Control control) {
		notifyKeyEventListeners(control, SWT.CTRL | SWT.SHIFT, 'z');
	}

	private Control setupControl() {
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_StringFeature());
		assertTextEditable(control, true);
		assertText(control, "");
		return control;
	}

	private DialogControlFactory createAndInitializeFactory() {
		DialogControlFactory dialogControlFactory = new DialogControlFactory();
		initialize(dialogControlFactory, classForControlsInstance);
		return dialogControlFactory;
	}

	private void notifyKeyEventListeners(Widget w, int stateMask, char c) {
		Event e = new Event();
		e.stateMask = stateMask;
		e.keyCode = c;
		w.notifyListeners(SWT.KeyDown, e);
		w.notifyListeners(SWT.KeyUp, e);
	}
}
