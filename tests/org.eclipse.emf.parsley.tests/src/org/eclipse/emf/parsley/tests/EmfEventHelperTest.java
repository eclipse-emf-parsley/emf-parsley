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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.util.EmfEventHelper;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Lorenzo Bettini
 */
public class EmfEventHelperTest extends AbstractEmfParsleyShellBasedTest {

	@Rule public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	private EmfEventHelper eventHelper;

	@Before
	public void setupTests() {
		eventHelper = getOrCreateInjector().getInstance(EmfEventHelper.class);
	}

	@Test
	public void testGetEObjectFromMouseEventWithUnhandledSource() {
		final var mouseEvent = mockMouseEvent(mock(Composite.class));
		assertNull(eventHelper.getEObjectFromMouseEvent(mouseEvent));
	}

	@Test
	public void testGetEObjectFromMouseEventOnTreeWithoutSelection() {
		final var widget = mock(Tree.class);
		when(widget.getSelectionCount()).thenReturn(0);
		final var mouseEvent = mockMouseEvent(widget);
		assertNull(eventHelper.getEObjectFromMouseEvent(mouseEvent));
		verify(widget).getSelectionCount();
	}

	@Test
	public void testGetEObjectFromMouseEventOnTreeWithSelection() {
		final var object = fixtures.getClassForControlsInstance();
		final var item = mock(TreeItem.class);
		when(item.getData()).thenReturn(object);
		final var widget = mock(Tree.class);
		when(widget.getSelectionCount()).thenReturn(1);
		final var selectionList = new ArrayList<TreeItem>();
		selectionList.add(item);
		when(widget.getSelection()).thenReturn(selectionList.toArray(new TreeItem[0]));
		final var mouseEvent = mockMouseEvent(widget);
		assertNotNull(eventHelper.getEObjectFromMouseEvent(mouseEvent));
		verify(widget).getSelectionCount();
		verify(widget).getSelection();
	}

	@Test
	public void testGetEObjectFromMouseEventOnTableWithoutSelection() {
		final var widget = mock(Table.class);
		when(widget.getSelectionCount()).thenReturn(0);
		final var mouseEvent = mockMouseEvent(widget);
		assertNull(eventHelper.getEObjectFromMouseEvent(mouseEvent));
		verify(widget).getSelectionCount();
	}

	@Test
	public void testGetEObjectFromMouseEventOnTableWithSelection() {
		final var object = fixtures.getClassForControlsInstance();
		final var item = mock(TableItem.class);
		when(item.getData()).thenReturn(object);
		final var widget = mock(Table.class);
		when(widget.getSelectionCount()).thenReturn(1);
		final var selectionList = new ArrayList<TableItem>();
		selectionList.add(item);
		when(widget.getSelection()).thenReturn(selectionList.toArray(new TableItem[0]));
		final var mouseEvent = mockMouseEvent(widget);
		assertNotNull(eventHelper.getEObjectFromMouseEvent(mouseEvent));
		verify(widget).getSelectionCount();
		verify(widget).getSelection();
	}

	private MouseEvent mockMouseEvent(Widget widget) {
		final var mouseEvent = mock(MouseEvent.class);
		when(mouseEvent.getSource()).thenReturn(widget);
		return mouseEvent;
	}

}
