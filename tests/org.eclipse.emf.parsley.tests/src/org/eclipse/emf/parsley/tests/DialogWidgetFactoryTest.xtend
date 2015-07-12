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

import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyControlBasedTest
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.eclipse.emf.parsley.widgets.DialogWidgetFactory
import org.eclipse.swt.SWT
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*

class DialogWidgetFactoryTest extends AbstractEmfParsleyControlBasedTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()
	
	/**
	 * An instance to use for testing the creation of a Control
	 * using an AbstractControlFactory
	 */
	var protected ClassForControls classForControlsInstance
	
	var protected DialogWidgetFactory factory
	
	val static TEST_TEXT = "Test"

	@Before
	def void setupFactory() {
		classForControlsInstance = testFactory.createClassForControls
		factory = getOrCreateInjector.getInstance(DialogWidgetFactory)
		factory.init(shell)
	}

	@Test def void testParent() {
		shell.assertSame(factory.parent)
	}

	@Test def void testCheckBox() {
		val control = factory.createButton("", SWT.CHECK)
		control.assertCheckbox(false)
	}

	@Test def void testLabel() {
		val control = factory.createLabel(TEST_TEXT)
		control.assertLabel(TEST_TEXT)
	}

	@Test def void testText() {
		val control = factory.createText(TEST_TEXT)
		control.assertText(TEST_TEXT)
	}

	@Test def void testTextWithStyle() {
		val control = factory.createText(TEST_TEXT, SWT.NO_SCROLL)
		control.assertText(TEST_TEXT)
		control.assertStyle(SWT.NO_SCROLL)
	}

	@Test def void testCombo() {
		val control = factory.createComboViewer(SWT.NO_SCROLL)
		control.assertStyle(SWT.NO_SCROLL)
	}

	@Test def void testDateTime() {
		factory.createDateTime()
	}

	@Test def void testDateTimeWithStyle() {
		val control = factory.createDateTime(SWT.NO_SCROLL)
		control.assertStyle(SWT.NO_SCROLL)
	}

	@Test def void testDateTimeWithParent() {
		val control = factory.createDateTime(shell)
		// use the default style
		control.assertStyle(SWT.DROP_DOWN)
	}

}