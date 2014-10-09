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

import com.google.inject.Binder
import com.google.inject.Guice
import com.google.inject.TypeLiteral
import com.google.inject.name.Names
import java.util.List
import org.eclipse.emf.parsley.EmfParsleyConstants
import org.eclipse.emf.parsley.builders.TableViewerBuilder
import org.eclipse.jface.viewers.ColumnLayoutData
import org.eclipse.jface.viewers.ColumnWeightData
import org.eclipse.swt.widgets.Layout
import org.junit.Test

import static extension org.junit.Assert.*

class TableViewerColumnBuilderTest extends AbstractTableViewerTest {

	@Test
	def void testTableColumnHeader() {
		buildAndFill(
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
		tableViewerBuilder = Guice.createInjector(
				new EmfParsleyGuiceModuleForTesting() {
					override configureTableColumnWeights(Binder binder) {
						binder.bind(new TypeLiteral<List<Integer>>() {}).annotatedWith
							(Names.named(EmfParsleyConstants.TABLE_COLUMN_WEIGHTS)).
								toInstance(#[5,2]);
					}
					
				}
			).getInstance(TableViewerBuilder)
		buildAndFill(
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
