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

import java.util.List
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.parsley.tests.TreeViewerColumnBuilderTest.CustomFeatureCaptionProvider
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider
import org.eclipse.jface.viewers.ColumnLayoutData
import org.eclipse.jface.viewers.ColumnWeightData
import org.eclipse.swt.widgets.Layout
import org.junit.Test

import static extension org.junit.Assert.*

class TreeViewerColumnBuilderTest extends AbstractViewerTest {

	static class CustomFeatureCaptionProvider extends FeatureCaptionProvider {
		def String text_ClassForControls_booleanFeature(EStructuralFeature feature) {
			return "bool"
		}
	}

	@Test
	def void testTableColumnHeader() {
		buildAndFillTreeViewer(
			testContainer.classesForControls,
			testPackage.classForControls
		)
		syncExecVoid[
			// the first column is the tree so we skip it
			// the first feature of ClassForControls
			"booleanFeature".assertEquals(treeViewer.tree.columns.get(1).text)
			"booleanObjectFeature".assertEquals(treeViewer.tree.columns.get(2).text)
		]
	}

	@Test
	def void testTableColumnHeaderWithCustomFeatureCaptionProvider() {
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=491063
		buildAndFillTreeViewer(
			withCustomFeatureCaptionProvider,
			testContainer.classesForControls,
			testPackage.classForControls
		)
		syncExecVoid[
			// the first column is the tree so we skip it
			// custom caption
			"bool".assertEquals(treeViewer.tree.columns.get(1).text)
			"booleanObjectFeature".assertEquals(treeViewer.tree.columns.get(2).text)
		]
	}

	@Test
	def void testTableColumnHeaderWithListOfFeatures() {
		buildAndFillTreeViewerWithFeatures(
			#[testPackage.classForControls_BooleanFeature, testPackage.classForControls_BooleanObjectFeature],
			testPackage.classForControls
		)
		syncExecVoid[
			// the first column is the tree so we skip it
			// the first feature of ClassForControls
			"booleanFeature".assertEquals(treeViewer.tree.columns.get(1).text)
			"booleanObjectFeature".assertEquals(treeViewer.tree.columns.get(2).text)
		]
	}

	@Test
	def void testTableColumnHeaderWithListOfFeaturesWithCustomFeatureCaptionProvider() {
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=491063
		buildAndFillTreeViewerWithFeatures(
			withCustomFeatureCaptionProvider,
			#[testPackage.classForControls_BooleanFeature, testPackage.classForControls_BooleanObjectFeature],
			testPackage.classForControls
		)
		syncExecVoid[
			// the first column is the tree so we skip it
			// custom caption
			"bool".assertEquals(treeViewer.tree.columns.get(1).text)
			"booleanObjectFeature".assertEquals(treeViewer.tree.columns.get(2).text)
		]
	}

	@Test
	def void testCustomColumnWeights() {
		buildAndFillTreeViewer(
			createInjector(new EmfParsleyGuiceModuleForTesting() {
				override valueTableColumnWeights() {
					#[5, 2]
				}
			}),
			testContainer.classesForControls,
			testPackage.classForControls
		)
		syncExecVoid[
			treeViewer.tree.getLayout.tableLayoutColumnData => [
				// the first column is the tree so we skip it
				get(1).assertColumnWeight(5)
				get(2).assertColumnWeight(2)
				// 3 is the default one
				get(3).assertColumnWeight(3)
			]
		]
	}

	@Test
	def void testTableRowSize() {
		buildAndFillTreeViewer(
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
		buildAndFillTreeViewer(
			testContainer, testPackage.classWithName
		)
		syncExecVoid[
			"Class With Name name 0".assertEquals(
				treeViewer.tree.items.head.getText(0)
			)
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

	private def withCustomFeatureCaptionProvider() {
		createInjector(new EmfParsleyGuiceModuleForTesting() {
			override bindFeatureCaptionProvider() {
				CustomFeatureCaptionProvider
			}
		})
	}
}
