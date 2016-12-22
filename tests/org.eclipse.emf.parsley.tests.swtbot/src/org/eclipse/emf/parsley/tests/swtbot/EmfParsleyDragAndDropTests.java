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
package org.eclipse.emf.parsley.tests.swtbot;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Lorenzo Bettini
 * 
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class EmfParsleyDragAndDropTests extends EmfParsleySWTBotAbstractTests {

	private static boolean skipDragAndDropTests = false;

	@BeforeClass
	public static void initializeExecutionFlag() {
		// in some CI systems Drag and Drop does not work
		if ("true".equals(System.getProperty("skipDragAndDropTests"))) {
			skipDragAndDropTests = true;
		}
	}

	@Test
	public void testDragAndDropOnSaveableView()
			throws InvocationTargetException, CoreException, InterruptedException, IOException {
		createProjectAndTestFiles();
		openTestModelTreeFormView();
		SWTBotTreeItem rootOfTree = getRootOfTreeFromView(TEST_MODEL_TREE_VIEW).getTreeItem(TEST_CONTAINER_LABEL)
				.expand();

		SWTBotTreeItem source = getSourceNode(rootOfTree);
		SWTBotTreeItem target = getTargetNode(rootOfTree);
		assertEquals(1, source.getItems().length);
		assertEquals(0, target.getItems().length);

		SWTBotTreeItem sourceItem = getSourceItemNode(source);
		sourceItem.dragAndDrop(target);

		if (executeAssertions()) {
			assertSaveableViewIsDirty(true, TEST_MODEL_TREE_VIEW);

			assertEquals(0, source.getItems().length);
			assertEquals(1, target.getItems().length);
		}
	}

	@Test
	public void testDragAndDropOnSelectionTreeView()
			throws InvocationTargetException, CoreException, InterruptedException, IOException {
		openTestOnSelectionTreeView();
		// create the project, open the editor and select the root
		openEditorAndGetTreeRoot(EMF_TREE_EDITOR, TEST_CONTAINER_FOR_DND,
				TEST_CONTAINER_FOR_DND_PLATFORM_URI).select();
		SWTBotTreeItem rootOfTree = getRootOfTreeFromView(TEST_ON_SELECTION_TREE_VIEW).getTreeItem(TEST_CONTAINER_LABEL)
				.expand();

		SWTBotTreeItem source = getSourceNode(rootOfTree);
		SWTBotTreeItem target = getTargetNode(rootOfTree);
		assertEquals(1, source.getItems().length);
		assertEquals(0, target.getItems().length);

		SWTBotTreeItem sourceItem = getSourceItemNode(source);
		sourceItem.dragAndDrop(target);

		if (executeAssertions()) {
			// since the selection view uses the editing domain of the selected object
			// the editor should now be dirty
			assertEditorDirty(EMF_TREE_EDITOR);

			assertEquals(0, source.getItems().length);
			assertEquals(1, target.getItems().length);
		}
	}

	@Test
	public void testDragAndDropFromOnSelectionTreeViewToEditor()
			throws InvocationTargetException, CoreException, InterruptedException, IOException {
		openTestOnSelectionTreeView();
		// create the project, open the editor and select the root
		SWTBotTreeItem editorRootOfTree = openEditorAndGetTreeRoot(EMF_TREE_EDITOR, TEST_CONTAINER_FOR_DND,
				TEST_CONTAINER_FOR_DND_PLATFORM_URI).select();
		SWTBotTreeItem rootOfTree = getRootOfTreeFromView(TEST_ON_SELECTION_TREE_VIEW).getTreeItem(TEST_CONTAINER_LABEL)
				.expand();

		SWTBotTreeItem source = getSourceNode(rootOfTree);
		SWTBotTreeItem target = getTargetNode(editorRootOfTree.expand().getNode(TEST_CONTAINER_LABEL).expand());
		assertEquals(1, source.getItems().length);
		assertEquals(0, target.getItems().length);

		SWTBotTreeItem sourceItem = getSourceItemNode(source);
		sourceItem.dragAndDrop(target);

		if (executeAssertions()) {
			// since the selection view uses the editing domain of the selected object
			// the editor should now be dirty
			assertEditorDirty(EMF_TREE_EDITOR);

			assertEquals(0, source.getItems().length);
			assertEquals(1, target.getItems().length);
		}
	}

	@Test
	public void testDragAndDropOnEditor()
			throws InvocationTargetException, CoreException, InterruptedException, IOException {
		SWTBotTreeItem rootOfTree = openEditorAndGetTreeRoot(EMF_TREE_EDITOR, TEST_CONTAINER_FOR_DND,
				TEST_CONTAINER_FOR_DND_PLATFORM_URI).expand().getNode(TEST_CONTAINER_LABEL).expand();

		SWTBotTreeItem source = getSourceNode(rootOfTree);
		SWTBotTreeItem target = getTargetNode(rootOfTree);
		assertEquals(1, source.getItems().length);
		assertEquals(0, target.getItems().length);

		SWTBotTreeItem sourceItem = getSourceItemNode(source);
		sourceItem.dragAndDrop(target);

		if (executeAssertions()) {
			assertEditorDirtySaveEditorAndAssertNotDirty(EMF_TREE_EDITOR);

			assertEquals(0, source.getItems().length);
			assertEquals(1, target.getItems().length);
		}
	}

	private SWTBotTreeItem getSourceNode(SWTBotTreeItem treeItem) {
		return treeItem.getNode(TEST_CONTAINER_SOURCE_LABEL);
	}

	private SWTBotTreeItem getTargetNode(SWTBotTreeItem treeItem) {
		return treeItem.getNode(TEST_CONTAINER_TARGET_LABEL);
	}

	private SWTBotTreeItem getSourceItemNode(SWTBotTreeItem treeItem) {
		return treeItem.expand().getNode(TEST_CONTAINER_ELEMENT_TO_MOVE_LABEL);
	}

	private void openTestModelTreeFormView() {
		openTestView(TEST_MODEL_TREE_VIEW);
	}

	private void openTestOnSelectionTreeView() {
		openTestView(TEST_ON_SELECTION_TREE_VIEW);
	}

	private boolean executeAssertions() {
		if (skipDragAndDropTests) {
			System.out.println("Skipping assertions for Drag and Drop tests");
			return false;
		}
		return true;
	}
}
