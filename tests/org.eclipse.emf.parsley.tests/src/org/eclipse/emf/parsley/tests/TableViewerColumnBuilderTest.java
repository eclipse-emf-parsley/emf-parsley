/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.eclipse.emf.parsley.internal.viewers.EObjectTableViewerComparator;
import org.eclipse.jface.viewers.ColumnLayoutData;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.TableColumn;
import org.junit.Test;

public class TableViewerColumnBuilderTest extends AbstractViewerTest {

	@Test
	public void testTableColumnHeader() {
		buildAndFillTableViewer(
			fixtures.getTestContainer().getClassesForControls(), fixtures.getTestPackage().getClassForControls()
		);
		syncExecVoid(() -> {
			// the first feature of ClassForControls
			assertEquals("booleanFeature", tableViewer.getTable().getColumns()[0].getText());
			assertEquals("booleanObjectFeature", tableViewer.getTable().getColumns()[1].getText());
		});
	}

	@Test
	public void testCustomColumnWeights() {
		buildAndFillTableViewer(
			createInjector(
				new EmfParsleyGuiceModuleForTesting() {
					@Override
					public int[] valueTableColumnWeights() {
						return new int[] {5, 2};
					}
				}
			),
			fixtures.getTestContainer().getClassesForControls(), fixtures.getTestPackage().getClassForControls()
		);
		syncExecVoid(() -> {
			List<ColumnLayoutData> columnData = tableLayoutColumnData(tableViewer.getTable().getLayout());
			assertColumnWeight(columnData.get(0), 5);
			assertColumnWeight(columnData.get(1), 2);
			// 3 is the default one
			assertColumnWeight(columnData.get(2), 3);
		});
	}

	@Test
	public void testTableRowSize() {
		buildAndFillTableViewer(
			fixtures.getTestContainer(), fixtures.getTestPackage().getClassWithName()
		);
		syncExecVoid(() -> {
			assertEquals(numOfElements, tableViewer.getTable().getItems().length);
		});
	}

	@Test
	public void testTableRowTextForString() {
		buildAndFillTableViewer(
			fixtures.getTestContainer(), fixtures.getTestPackage().getClassWithName()
		);
		syncExecVoid(() -> {
			assertEquals("name 0",
				tableViewer.getTable().getItems()[0].getText(0)
			);
		});
	}

	@Test
	public void testTableRowTextForEObject() {
		buildAndFillTableViewer(
			fixtures.getTestContainer(), fixtures.getTestPackage().getClassForTable()
		);
		syncExecVoid(() -> {
			assertEquals("Class With Name name 0",
				tableViewer.getTable().getItems()[0].getText(0)
			);
			assertEquals("Class With Name name 0",
				tableViewer.getTable().getItems()[0].getText(1)
			);
		});
	}

	@Test
	public void testSortableColumnBuilder() {
		final int SORT_NONE = 0;
		final int SORT_UP = 128;
		final int SORT_DOWN = 1024;

		buildAndFillTableViewer(
			fixtures.getTestContainer().getClassesForControls(), fixtures.getTestPackage().getClassForControls()
		);
		syncExecVoid(() -> {
			TableColumn[] tableColumns = tableViewer.getTable().getColumns();
			EObjectTableViewerComparator tableViewerComparator = (EObjectTableViewerComparator) tableViewer.getComparator();
			int columnIndex = 0;
			// for each column order change is tested for each direction
			for (TableColumn column : tableColumns) {
				assertEquals(SORT_NONE, tableViewer.getTable().getSortDirection());
				column.notifyListeners(SWT.Selection, new Event());
				// make sure that the property index of the comparator is updated
				// according to the right column
				assertEquals(columnIndex,
					tableViewerComparator.getPropertyIndex());
				columnIndex++;
				assertEquals(column, tableViewer.getTable().getSortColumn());
				assertEquals(SORT_UP, tableViewer.getTable().getSortDirection());

				column.notifyListeners(SWT.Selection, new Event());
				assertEquals(column, tableViewer.getTable().getSortColumn());
				assertEquals(SORT_DOWN, tableViewer.getTable().getSortDirection());

				column.notifyListeners(SWT.Selection, new Event());
				assertEquals(column, tableViewer.getTable().getSortColumn());
				assertEquals(SORT_NONE, tableViewer.getTable().getSortDirection());
			}
		});
	}

	@SuppressWarnings("unchecked")
	private List<ColumnLayoutData> tableLayoutColumnData(Layout layout) {
		// columns is a private field in TableLayout so we must
		// use reflection to access it (there's no accessor method either)
		try {
			var columns = layout.getClass().getDeclaredField("columns");
			columns.setAccessible(true);
			return (List<ColumnLayoutData>) columns.get(layout);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private void assertColumnWeight(ColumnLayoutData data, int expectedWeight) {
		assertEquals(expectedWeight, ((ColumnWeightData) data).weight);
	}
}
