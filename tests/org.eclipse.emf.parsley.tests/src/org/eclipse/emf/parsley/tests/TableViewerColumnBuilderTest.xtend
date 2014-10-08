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

}
