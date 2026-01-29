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
package org.eclipse.emf.parsley.tests;

import com.google.inject.Inject;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.viewers.LayoutHelper;
import org.eclipse.jface.layout.AbstractColumnLayout;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.ColumnLayoutData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Widget;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * @author Lorenzo Bettini
 */
public class LayoutHelperTest extends AbstractEmfParsleyShellBasedTest {

	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	@Inject
	private LayoutHelper layoutHelper;

	@Before
	public void setupTests() {
		getOrCreateInjector().injectMembers(this);
	}

	@Test
	public void testWhenTableParentLayoutIsTableColumnLayout() {
		TableColumnLayout layout = mock(TableColumnLayout.class);
		TableViewer tableViewer = mockTableViewer(layout);
		assertSame(layout, layoutHelper.adjustForTableLayout(tableViewer));
		assertHeadersAndLinesVisible(tableViewer);
	}

	@Test
	public void testWhenTableParentLayoutIsNotTableColumnLayout() {
		Layout layout = mock(Layout.class);
		TableViewer tableViewer = mockTableViewer(layout);
		assertTrue(layoutHelper.adjustForTableLayout(tableViewer) instanceof TableLayout);
		assertTableLayout(tableViewer);
		assertHeadersAndLinesVisible(tableViewer);
	}

	@Test
	public void testWhenTreeParentLayoutIsTreeColumnLayout() {
		TreeColumnLayout layout = mock(TreeColumnLayout.class);
		TreeViewer treeViewer = mockTreeViewer(layout);
		assertSame(layout, layoutHelper.adjustForTableLayout(treeViewer));
		assertHeadersAndLinesVisible(treeViewer);
	}

	@Test
	public void testWhenTreeParentLayoutIsNotTableColumnLayout() {
		Layout layout = mock(Layout.class);
		TreeViewer treeViewer = mockTreeViewer(layout);
		assertTrue(layoutHelper.adjustForTableLayout(treeViewer) instanceof TableLayout);
		assertTableLayout(treeViewer);
		assertHeadersAndLinesVisible(treeViewer);
	}

	@Test
	public void testAdjustLayoutColumnDataWhenLayoutIsAbstractColumnLayout() {
		AbstractColumnLayout layout = mock(AbstractColumnLayout.class);
		Widget widget = mock(Widget.class);
		layoutHelper.adjustLayoutColumnData(layout, widget, 0);
		verify(layout).setColumnData(any(Widget.class), any(ColumnLayoutData.class));
	}

	@Test
	public void testAdjustLayoutColumnDataWhenLayoutIsTableLayout() {
		TableLayout layout = mock(TableLayout.class);
		Widget widget = mock(Widget.class);
		layoutHelper.adjustLayoutColumnData(layout, widget, 0);
		verify(layout).addColumnData(any(ColumnLayoutData.class));
	}

	@Test
	public void testAdjustLayoutColumnDataWithAnyLayout() {
		Layout layout = mock(Layout.class);
		Widget widget = mock(Widget.class);
		layoutHelper.adjustLayoutColumnData(layout, widget, 0);
		verifyNoInteractions(layout);
	}

	private TableViewer mockTableViewer(Layout layout) {
		Composite parent = mockParentWithLayout(layout);
		Table table = mock(Table.class);
		when(table.getParent()).thenReturn(parent);
		TableViewer tableViewer = mock(TableViewer.class);
		when(tableViewer.getTable()).thenReturn(table);
		return tableViewer;
	}

	private TreeViewer mockTreeViewer(Layout layout) {
		Composite parent = mockParentWithLayout(layout);
		Tree tree = mock(Tree.class);
		when(tree.getParent()).thenReturn(parent);
		TreeViewer treeViewer = mock(TreeViewer.class);
		when(treeViewer.getTree()).thenReturn(tree);
		return treeViewer;
	}

	private Composite mockParentWithLayout(Layout layout) {
		Composite composite = mock(Composite.class);
		when(composite.getLayout()).thenReturn(layout);
		return composite;
	}

	private void assertHeadersAndLinesVisible(TableViewer tableViewer) {
		Table table = tableViewer.getTable();
		verify(table, times(1)).setHeaderVisible(true);
		verify(table, times(1)).setLinesVisible(true);
	}

	private void assertHeadersAndLinesVisible(TreeViewer treeViewer) {
		Tree tree = treeViewer.getTree();
		verify(tree, times(1)).setHeaderVisible(true);
		verify(tree, times(1)).setLinesVisible(true);
	}

	private void assertTableLayout(TableViewer tableViewer) {
		Table table = tableViewer.getTable();
		verify(table, times(1)).setLayout(any(TableLayout.class));
	}

	private void assertTableLayout(TreeViewer treeViewer) {
		Tree tree = treeViewer.getTree();
		verify(tree, times(1)).setLayout(any(TableLayout.class));
	}

}
