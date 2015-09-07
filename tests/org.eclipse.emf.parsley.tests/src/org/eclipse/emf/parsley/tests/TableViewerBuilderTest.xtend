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

import org.junit.Test

import static extension org.junit.Assert.*

class TableViewerBuilderTest extends AbstractTableViewerTest {

	@Test
	def void testTableRowSize() {
		buildAndFill(
			testContainer, testPackage.classWithName
		)
		syncExecVoid[
			numOfElements.assertEquals(tableViewer.table.items.length)
		]
	}

	@Test
	def void testTableRowTextForString() {
		buildAndFill(
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
		buildAndFill(
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

}
