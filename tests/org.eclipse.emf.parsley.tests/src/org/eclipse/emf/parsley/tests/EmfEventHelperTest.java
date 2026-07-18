/*******************************************************************************
 * Copyright (c) 2026 Lorenzo Bettini and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.util.EmfEventHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
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
		var result = syncExec(() -> {
			var composite = new Composite(getShell(), SWT.NONE);
			return eventHelper.getEObjectFromMouseEvent(mouseEventFor(composite));
		});

		assertNull(result);
	}

	@Test
	public void testGetEObjectFromMouseEventOnTreeWithoutSelection() {
		var result = syncExec(() -> {
			var tree = new Tree(getShell(), SWT.NONE);
			return eventHelper.getEObjectFromMouseEvent(mouseEventFor(tree));
		});

		assertNull(result);
	}

	@Test
	public void testGetEObjectFromMouseEventOnTreeWithSelection() {
		var object = fixtures.getClassForControlsInstance();

		var result = syncExec(() -> {
			var tree = new Tree(getShell(), SWT.NONE);
			var item = new TreeItem(tree, SWT.NONE);
			item.setData(object);
			tree.setSelection(item);

			return eventHelper.getEObjectFromMouseEvent(mouseEventFor(tree));
		});

		assertSame(object, result);
	}

	@Test
	public void testGetEObjectFromMouseEventOnTableWithoutSelection() {
		var result = syncExec(() -> {
			var table = new Table(getShell(), SWT.NONE);
			return eventHelper.getEObjectFromMouseEvent(mouseEventFor(table));
		});

		assertNull(result);
	}

	@Test
	public void testGetEObjectFromMouseEventOnTableWithSelection() {
		var object = fixtures.getClassForControlsInstance();

		var result = syncExec(() -> {
			var table = new Table(getShell(), SWT.NONE);
			var item = new TableItem(table, SWT.NONE);
			item.setData(object);
			table.setSelection(item);

			return eventHelper.getEObjectFromMouseEvent(mouseEventFor(table));
		});

		assertSame(object, result);
	}

	private MouseEvent mouseEventFor(Widget widget) {
		var event = new Event();
		event.widget = widget;
		return new MouseEvent(event);
	}

}