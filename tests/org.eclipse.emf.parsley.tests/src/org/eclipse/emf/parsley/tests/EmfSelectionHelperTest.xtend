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

import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.eclipse.jface.viewers.ISelection
import org.eclipse.jface.viewers.StructuredSelection
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.eclipse.emf.parsley.util.EmfSelectionHelper

import static org.mockito.Mockito.*
import static extension org.junit.Assert.*

/**
 * @author Lorenzo Bettini
 */
class EmfSelectionHelperTest extends AbstractEmfParsleyShellBasedTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	var EmfSelectionHelper selectionHelper

	@Before
	def void setupContentProvider() {
		selectionHelper = getOrCreateInjector.getInstance(EmfSelectionHelper)
	}

	@Test
	def void testFirstSelectedElement() {
		selectionHelper.getFirstSelectedElement(createSelection("test")).assertNotNull
	}

	@Test
	def void testFirstSelectedElementNull() {
		selectionHelper.getFirstSelectedElement(createEmptySelection).assertNull
	}

	@Test
	def void testFirstSelectedElementNonStructuredSelection() {
		selectionHelper.getFirstSelectedElement(createNonStructuredSelection).assertNull
	}

	@Test
	def void testFirstSelectedEObject() {
		selectionHelper.getFirstSelectedEObject(createSelection(classForControlsInstance)).assertNotNull
	}

	@Test
	def void testFirstSelectedEObjectNonEObject() {
		selectionHelper.getFirstSelectedEObject(createSelection("test")).assertNull
	}

	def private createSelection(Object o) {
		new StructuredSelection(o)
	}

	def private createEmptySelection() {
		new StructuredSelection
	}

	def private createNonStructuredSelection() {
		mock(ISelection)
	}

}
