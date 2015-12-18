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
import org.eclipse.emf.parsley.util.EmfEventHelper
import org.eclipse.swt.events.MouseEvent
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Table
import org.eclipse.swt.widgets.TableItem
import org.eclipse.swt.widgets.Tree
import org.eclipse.swt.widgets.TreeItem
import org.eclipse.swt.widgets.Widget
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static org.mockito.Mockito.*

import static extension org.junit.Assert.*

/**
 * @author Lorenzo Bettini
 */
class EmfEventHelperTest extends AbstractEmfParsleyShellBasedTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	var EmfEventHelper eventHelper

	@Before
	def void setupContentProvider() {
		eventHelper = getOrCreateInjector.getInstance(EmfEventHelper)
	}

	@Test
	def void testGetEObjectFromMouseEventWithUnhandledSource() {
		val mouseEvent = mockMouseEvent(mock(Composite))
		eventHelper.getEObjectFromMouseEvent(mouseEvent).assertNull
	}

	@Test
	def void testGetEObjectFromMouseEventOnTreeWithoutSelection() {
		val widget = mock(Tree) => [
			when(selectionCount).thenReturn(0)
		]
		val mouseEvent = mockMouseEvent(widget)
		eventHelper.getEObjectFromMouseEvent(mouseEvent).assertNull
		verify(widget).selectionCount
	}

	@Test
	def void testGetEObjectFromMouseEventOnTreeWithSelection() {
		val object = classForControlsInstance
		val item = mock(TreeItem) => [
			when(data).thenReturn(object)
		]
		val widget = mock(Tree) => [
			when(selectionCount).thenReturn(1)
			when(selection).thenReturn(newArrayList(item))
		]
		val mouseEvent = mockMouseEvent(widget)
		eventHelper.getEObjectFromMouseEvent(mouseEvent).assertNotNull
		verify(widget).selectionCount
		verify(widget).selection
	}

	@Test
	def void testGetEObjectFromMouseEventOnTableWithoutSelection() {
		val widget = mock(Table) => [
			when(selectionCount).thenReturn(0)
		]
		val mouseEvent = mockMouseEvent(widget)
		eventHelper.getEObjectFromMouseEvent(mouseEvent).assertNull
		verify(widget).selectionCount
	}

	@Test
	def void testGetEObjectFromMouseEventOnTableWithSelection() {
		val object = classForControlsInstance
		val item = mock(TableItem) => [
			when(data).thenReturn(object)
		]
		val widget = mock(Table) => [
			when(selectionCount).thenReturn(1)
			when(selection).thenReturn(newArrayList(item))
		]
		val mouseEvent = mockMouseEvent(widget)
		eventHelper.getEObjectFromMouseEvent(mouseEvent).assertNotNull
		verify(widget).selectionCount
		verify(widget).selection
	}

	private def mockMouseEvent(Widget widget) {
		mock(MouseEvent) => [
			when(source).thenReturn(widget)
		]
	}

}
