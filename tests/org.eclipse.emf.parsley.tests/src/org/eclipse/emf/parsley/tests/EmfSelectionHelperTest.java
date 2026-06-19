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
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.util.EmfSelectionHelper;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Lorenzo Bettini
 */
public class EmfSelectionHelperTest extends AbstractEmfParsleyShellBasedTest {

	@Rule public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	private EmfSelectionHelper selectionHelper;

	@Before
	public void setupContentProvider() {
		selectionHelper = getOrCreateInjector().getInstance(EmfSelectionHelper.class);
	}

	@Test
	public void testFirstSelectedElement() {
		assertNotNull(selectionHelper.getFirstSelectedElement(createSelection("test")));
	}

	@Test
	public void testFirstSelectedElementNull() {
		assertNull(selectionHelper.getFirstSelectedElement(createEmptySelection()));
	}

	@Test
	public void testFirstSelectedElementNonStructuredSelection() {
		assertNull(selectionHelper.getFirstSelectedElement(createNonStructuredSelection()));
	}

	@Test
	public void testFirstSelectedEObject() {
		assertNotNull(selectionHelper.getFirstSelectedEObject(createSelection(fixtures.getClassForControlsInstance())));
	}

	@Test
	public void testFirstSelectedEObjectNonEObject() {
		assertNull(selectionHelper.getFirstSelectedEObject(createSelection("test")));
	}

	private StructuredSelection createSelection(Object o) {
		return new StructuredSelection(o);
	}

	private StructuredSelection createEmptySelection() {
		return new StructuredSelection();
	}

	private ISelection createNonStructuredSelection() {
		return mock(ISelection.class);
	}

}
