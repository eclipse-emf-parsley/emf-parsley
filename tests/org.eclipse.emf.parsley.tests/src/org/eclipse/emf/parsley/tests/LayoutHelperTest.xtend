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
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.eclipse.emf.parsley.viewers.LayoutHelper
import org.eclipse.jface.layout.AbstractColumnLayout
import org.eclipse.jface.layout.TableColumnLayout
import org.eclipse.jface.layout.TreeColumnLayout
import org.eclipse.jface.viewers.ColumnLayoutData
import org.eclipse.jface.viewers.TableLayout
import org.eclipse.jface.viewers.TableViewer
import org.eclipse.jface.viewers.TreeViewer
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Layout
import org.eclipse.swt.widgets.Table
import org.eclipse.swt.widgets.Tree
import org.eclipse.swt.widgets.Widget
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static org.mockito.Mockito.*

import static extension org.junit.Assert.*

/**
 * @author Lorenzo Bettini
 */
class LayoutHelperTest extends AbstractEmfParsleyShellBasedTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	@Inject
	var LayoutHelper layoutHelper

	@Before
	def void setupTests() {
		getOrCreateInjector.injectMembers(this)
	}

	@Test
	def void testWhenTableParentLayoutIsTableColumnLayout() {
		val layout = mock(TableColumnLayout)
		val tableViewer = mockTableViewer(layout)
		layoutHelper.adjustForTableLayout(tableViewer).assertSame(layout)
		assertHeadersAndLinesVisible(tableViewer)
	}

	@Test
	def void testWhenTableParentLayoutIsNotTableColumnLayout() {
		val layout = mock(Layout)
		val tableViewer = mockTableViewer(layout)
		assertTrue(layoutHelper.adjustForTableLayout(tableViewer) instanceof TableLayout)
		assertTableLayout(tableViewer)
		assertHeadersAndLinesVisible(tableViewer)
	}

	@Test
	def void testWhenTreeParentLayoutIsTreeColumnLayout() {
		val layout = mock(TreeColumnLayout)
		val treeViewer = mockTreeViewer(layout)
		layoutHelper.adjustForTableLayout(treeViewer).assertSame(layout)
		assertHeadersAndLinesVisible(treeViewer)
	}

	@Test
	def void testWhenTreeParentLayoutIsNotTableColumnLayout() {
		val layout = mock(Layout)
		val treeViewer = mockTreeViewer(layout)
		assertTrue(layoutHelper.adjustForTableLayout(treeViewer) instanceof TableLayout)
		assertTableLayout(treeViewer)
		assertHeadersAndLinesVisible(treeViewer)
	}

	@Test
	def void testAdjustLayoutColumnDataWhenLayoutIsAbstractColumnLayout() {
		val layout = mock(AbstractColumnLayout)
		val widget = mock(Widget)
		layoutHelper.adjustLayoutColumnData(layout, widget, 0)
		verify(layout).setColumnData(any(Widget), any(ColumnLayoutData))
	}

	@Test
	def void testAdjustLayoutColumnDataWhenLayoutIsTableLayout() {
		val layout = mock(TableLayout)
		val widget = mock(Widget)
		layoutHelper.adjustLayoutColumnData(layout, widget, 0)
		verify(layout).addColumnData(any(ColumnLayoutData))
	}

	@Test
	def void testAdjustLayoutColumnDataWithAnyLayout() {
		val layout = mock(Layout)
		val widget = mock(Widget)
		layoutHelper.adjustLayoutColumnData(layout, widget, 0)
		verifyNoInteractions(layout)
	}

	private def mockTableViewer(Layout layout) {
		val parent = mockParentWithLayout(layout)
		val table = mock(Table) => [
			when(getParent).thenReturn(parent)
		]
		mock(TableViewer) => [
			when(getTable).thenReturn(table)
		]
	}

	private def mockTreeViewer(Layout layout) {
		val parent = mockParentWithLayout(layout)
		val tree = mock(Tree) => [
			when(getParent).thenReturn(parent)
		]
		mock(TreeViewer) => [
			when(getTree).thenReturn(tree)
		]
	}

	private def mockParentWithLayout(Layout layout) {
		mock(Composite) => [
			when(getLayout).thenReturn(layout)
		]
	}

	private def assertHeadersAndLinesVisible(TableViewer tableViewer) {
		val table = tableViewer.table
		verify(table, times(1)).setHeaderVisible(true)
		verify(table, times(1)).setLinesVisible(true)
	}

	private def assertHeadersAndLinesVisible(TreeViewer treeViewer) {
		val tree = treeViewer.tree
		verify(tree, times(1)).setHeaderVisible(true)
		verify(tree, times(1)).setLinesVisible(true)
	}

	private def assertTableLayout(TableViewer tableViewer) {
		val table = tableViewer.table
		verify(table, times(1)).setLayout(any(TableLayout))
	}

	private def assertTableLayout(TreeViewer treeViewer) {
		val tree = treeViewer.tree
		verify(tree, times(1)).setLayout(any(TableLayout))
	}

}
