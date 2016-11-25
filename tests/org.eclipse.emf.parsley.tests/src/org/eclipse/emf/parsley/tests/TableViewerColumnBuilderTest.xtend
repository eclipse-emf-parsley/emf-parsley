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
package org.eclipse.emf.parsley.tests

import java.util.List
import org.eclipse.jface.viewers.ColumnLayoutData
import org.eclipse.jface.viewers.ColumnWeightData
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Event
import org.eclipse.swt.widgets.Layout
import org.junit.Test

import static extension org.junit.Assert.*

class TableViewerColumnBuilderTest extends AbstractViewerTest {

	@Test
	def void testTableColumnHeader() {
		buildAndFillTableViewer(
			testContainer.classesForControls, testPackage.classForControls
		)
		syncExecVoid[
			// the first feature of ClassForControls
			"booleanFeature".assertEquals(tableViewer.table.columns.get(0).text)
			"booleanObjectFeature".assertEquals(tableViewer.table.columns.get(1).text)
		]
	}

	@Test
	def void testCustomColumnWeights() {
		buildAndFillTableViewer(
			createInjector(
				new EmfParsleyGuiceModuleForTesting() {
					override valueTableColumnWeights() {
						#[5,2]
					}
				}
			),
			testContainer.classesForControls, testPackage.classForControls
		)
		syncExecVoid[
			tableViewer.table.getLayout.tableLayoutColumnData => [
				get(0).assertColumnWeight(5)
				get(1).assertColumnWeight(2)
				// 3 is the default one
				get(2).assertColumnWeight(3)
			]
		]
	}

	@Test
	def void testTableRowSize() {
		buildAndFillTableViewer(
			testContainer, testPackage.classWithName
		)
		syncExecVoid[
			numOfElements.assertEquals(tableViewer.table.items.length)
		]
	}

	@Test
	def void testTableRowTextForString() {
		buildAndFillTableViewer(
			testContainer, testPackage.classWithName
		)
		syncExecVoid[
			"name 0".assertEquals(
				tableViewer.table.items.head.getText(0)
			)
		]
	}

	@Test
	def void testTableRowTextForEObject() {
		buildAndFillTableViewer(
			testContainer, testPackage.classForTable
		)
		syncExecVoid[
			"Class With Name name 0".assertEquals(
				tableViewer.table.items.head.getText(0)
			)
			"Class With Name name 0".assertEquals(
				tableViewer.table.items.head.getText(1)
			)
		]
	}

	@Test
	def void testSortableColumnBuilder() {
		val SORT_NONE = 0
		val SORT_UP = 128
		val SORT_DOWN = 1024

		buildAndFillTableViewer(
			testContainer.classesForControls, testPackage.classForControls
		)
		syncExecVoid[
			val tableColumns=tableViewer.table.getColumns();
			// for each column order change is tested for each direction
			for (column : tableColumns) {
				assertEquals(SORT_NONE,tableViewer.table.sortDirection)
				column.notifyListeners(SWT.Selection, new Event());
				assertEquals(column,tableViewer.table.sortColumn)
				assertEquals(SORT_UP,tableViewer.table.sortDirection)

				column.notifyListeners(SWT.Selection, new Event());
				assertEquals(column,tableViewer.table.sortColumn)
				assertEquals(SORT_DOWN,tableViewer.table.sortDirection)

				column.notifyListeners(SWT.Selection, new Event());
				assertEquals(column,tableViewer.table.sortColumn)
				assertEquals(SORT_NONE,tableViewer.table.sortDirection)
			}
		]
	}

	def private tableLayoutColumnData(Layout layout) {
		// columns is a private field in TableLayout so we must
		// use reflection to access it (there's no accessor method either)
		val columns = layout.class.getDeclaredField("columns")
		columns.accessible = true
		(columns).get(layout) as List<ColumnLayoutData>
	}

	def private assertColumnWeight(ColumnLayoutData data, int expectedWeight) {
		expectedWeight.assertEquals((data as ColumnWeightData).weight)
	}
}
