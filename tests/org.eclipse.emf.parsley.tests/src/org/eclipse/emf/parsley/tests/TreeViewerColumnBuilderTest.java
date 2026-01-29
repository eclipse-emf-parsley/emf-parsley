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

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
import org.eclipse.jface.viewers.ColumnLayoutData;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.swt.widgets.Layout;
import org.junit.Test;

import com.google.inject.Injector;

public class TreeViewerColumnBuilderTest extends AbstractViewerTest {

	public static class CustomFeatureCaptionProvider extends FeatureCaptionProvider {
		public String text_ClassForControls_booleanFeature(EStructuralFeature feature) {
			return "bool";
		}
	}

	@Test
	public void testTableColumnHeader() {
		buildAndFillTreeViewer(
			fixtures.getTestContainer().getClassesForControls(),
			fixtures.getTestPackage().getClassForControls()
		);
		syncExecVoid(() -> {
			// the first column is the tree so we skip it
			// the first feature of ClassForControls
			assertEquals("booleanFeature", treeViewer.getTree().getColumns()[1].getText());
			assertEquals("booleanObjectFeature", treeViewer.getTree().getColumns()[2].getText());
		});
	}

	@Test
	public void testTableColumnHeaderWithCustomFeatureCaptionProvider() {
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=491063
		buildAndFillTreeViewer(
			withCustomFeatureCaptionProvider(),
			fixtures.getTestContainer().getClassesForControls(),
			fixtures.getTestPackage().getClassForControls()
		);
		syncExecVoid(() -> {
			// the first column is the tree so we skip it
			// custom caption
			assertEquals("bool", treeViewer.getTree().getColumns()[1].getText());
			assertEquals("booleanObjectFeature", treeViewer.getTree().getColumns()[2].getText());
		});
	}

	@Test
	public void testTableColumnHeaderWithListOfFeatures() {
		buildAndFillTreeViewerWithFeatures(
			Arrays.asList(
				fixtures.getTestPackage().getClassForControls_BooleanFeature(),
				fixtures.getTestPackage().getClassForControls_BooleanObjectFeature()
			),
			fixtures.getTestPackage().getClassForControls()
		);
		syncExecVoid(() -> {
			// the first column is the tree so we skip it
			// the first feature of ClassForControls
			assertEquals("booleanFeature", treeViewer.getTree().getColumns()[1].getText());
			assertEquals("booleanObjectFeature", treeViewer.getTree().getColumns()[2].getText());
		});
	}

	@Test
	public void testTableColumnHeaderWithListOfFeaturesWithCustomFeatureCaptionProvider() {
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=491063
		buildAndFillTreeViewerWithFeatures(
			withCustomFeatureCaptionProvider(),
			Arrays.asList(
				fixtures.getTestPackage().getClassForControls_BooleanFeature(),
				fixtures.getTestPackage().getClassForControls_BooleanObjectFeature()
			),
			fixtures.getTestPackage().getClassForControls()
		);
		syncExecVoid(() -> {
			// the first column is the tree so we skip it
			// custom caption
			assertEquals("bool", treeViewer.getTree().getColumns()[1].getText());
			assertEquals("booleanObjectFeature", treeViewer.getTree().getColumns()[2].getText());
		});
	}

	@Test
	public void testCustomColumnWeights() {
		buildAndFillTreeViewer(
			createInjector(new EmfParsleyGuiceModuleForTesting() {
				@Override
				public List<Integer> valueTableColumnWeights() {
					return Arrays.asList(5, 2, 4);
				}
			}),
			fixtures.getTestContainer().getClassesForControls(),
			fixtures.getTestPackage().getClassForControls()
		);
		syncExecVoid(() -> {
			List<ColumnLayoutData> columnData = tableLayoutColumnData(treeViewer.getTree().getLayout());
			// the first column is the tree
			assertColumnWeight(columnData.get(0), 5);
			assertColumnWeight(columnData.get(1), 2);
			assertColumnWeight(columnData.get(2), 4);
			// 3 is the default one
			assertColumnWeight(columnData.get(3), 3);
		});
	}

	@Test
	public void testTableRowSize() {
		buildAndFillTreeViewer(
			fixtures.getTestContainer(), fixtures.getTestPackage().getClassWithName()
		);
		syncExecVoid(() -> {
			// the tree contains all the elements, independently from
			// the eclass for representing the table columns
			assertEquals(numOfElements * 3, treeViewer.getTree().getItemCount());
		});
	}

	@Test
	public void testTableRowTextForString() {
		buildAndFillTreeViewer(
			fixtures.getTestContainer(), fixtures.getTestPackage().getClassWithName()
		);
		syncExecVoid(() -> {
			assertEquals("Class With Name name 0",
				treeViewer.getTree().getItems()[0].getText(0)
			);
		});
	}

	@SuppressWarnings("unchecked")
	private List<ColumnLayoutData> tableLayoutColumnData(Layout layout) {
		// columns is a private field in TableLayout so we must
		// use reflection to access it (there's no accessor method either)
		try {
			Field columns = layout.getClass().getDeclaredField("columns");
			columns.setAccessible(true);
			return (List<ColumnLayoutData>) columns.get(layout);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private void assertColumnWeight(ColumnLayoutData data, int expectedWeight) {
		assertEquals(expectedWeight, ((ColumnWeightData) data).weight);
	}

	private Injector withCustomFeatureCaptionProvider() {
		return createInjector(new EmfParsleyGuiceModuleForTesting() {
			@Override
			public Class<? extends FeatureCaptionProvider> bindFeatureCaptionProvider() {
				return CustomFeatureCaptionProvider.class;
			}
		});
	}
}
