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

class TreeViewerBuilderTest extends AbstractTreeViewerTest {

	@Test
	def void testTableRowSize() {
		buildAndFill(
			testContainer, testPackage.classWithName
		)
		syncExecVoid[
			// the tree contains all the elements, independently from
			// the eclass for representing the table columns
			(numOfElements * 3).assertEquals(treeViewer.tree.itemCount)
		]
	}

	@Test
	def void testTableRowTextForString() {
		buildAndFill(
			testContainer, testPackage.classWithName
		)
		syncExecVoid[
			"Class With Name name 0".assertEquals(
				treeViewer.tree.items.head.getText(0)
			)
		]
	}

}
