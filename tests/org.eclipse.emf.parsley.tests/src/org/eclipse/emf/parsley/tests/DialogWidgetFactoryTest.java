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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.eclipse.emf.parsley.inject.parameters.CompositeParameter;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyControlBasedTest;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.widgets.DialogWidgetFactory;
import org.eclipse.emf.parsley.widgets.IWidgetFactory;
import org.eclipse.swt.SWT;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class DialogWidgetFactoryTest extends AbstractEmfParsleyControlBasedTest {

	@Rule public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();
	
	/**
	 * An instance to use for testing the creation of a Control
	 * using an AbstractControlFactory
	 */
	protected ClassForControls classForControlsInstance;
	
	protected IWidgetFactory factory;
	
	private static final String TEST_TEXT = "Test";

	@Before
	public void setupFactory() {
		classForControlsInstance = fixtures.getTestFactory().createClassForControls();
		setupWidgetFactory();
	}
	
	protected void setupWidgetFactory() {
		factory = new DialogWidgetFactory(getCompositeParameter());
		injectMembers(factory);
	}

	@Test public void testParent() {
		assertSame(getShell(), factory.getParent());
	}

	@Test public void testCheckBox() {
		final var control = factory.createButton("", SWT.CHECK);
		assertCheckbox(control, false);
	}

	@Test public void testLabel() {
		final var control = factory.createLabel(TEST_TEXT);
		assertLabel(control, TEST_TEXT);
	}

	@Test public void testText() {
		final var control = factory.createText(TEST_TEXT);
		assertText(control, TEST_TEXT);
	}

	@Test public void testTextWithParent() {
		final var control = factory.createText(getShell(), TEST_TEXT);
		assertText(control, TEST_TEXT);
	}

	@Test public void testTextWithParentTextAndStyle() {
		final var control = factory.createText(getShell(), TEST_TEXT, SWT.NO_SCROLL);
		assertText(control, TEST_TEXT);
		assertStyle(control, SWT.NO_SCROLL);
	}

	@Test public void testTextWithParentAndStyle() {
		final var control = factory.createText(getShell(), SWT.NO_SCROLL);
		assertText(control, "");
		assertStyle(control, SWT.NO_SCROLL);
	}

	@Test public void testTextWithStyle() {
		final var control = factory.createText(TEST_TEXT, SWT.NO_SCROLL);
		assertText(control, TEST_TEXT);
		assertStyle(control, SWT.NO_SCROLL);
	}

	@Test public void testCombo() {
		final var control = factory.createComboViewer(SWT.NO_SCROLL);
		assertStyle(control, SWT.NO_SCROLL);
	}

	@Test public void testComboWithParent() {
		final var control = factory.createComboViewer(getShell(), SWT.NO_SCROLL);
		assertStyle(control, SWT.NO_SCROLL);
	}

	@Test public void testDateTime() {
		assertNotNull(factory.createDateTime());
	}

	@Test public void testDateTimeWithStyle() {
		final var control = factory.createDateTime(SWT.NO_SCROLL);
		assertStyle(control, SWT.NO_SCROLL);
	}

	@Test public void testDateTimeWithParentAndStyle() {
		final var control = factory.createDateTime(getShell(), SWT.NO_SCROLL);
		assertStyle(control, SWT.NO_SCROLL);
	}

	@Test public void testDateTimeWithParent() {
		final var control = factory.createDateTime(getShell());
		// use the default style
		assertStyle(control, SWT.DROP_DOWN);
	}

	protected CompositeParameter getCompositeParameter() {
		return new CompositeParameter(getShell());
	}
}
