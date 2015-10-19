/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Lorenzo Bettini
 * 
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class EmfParsleySaveableViewTests extends EmfParsleySWTBotAbstractTests {

	boolean treeFormViewOpened = false;
	
	boolean tableFormViewOpened = false;

	boolean tableViewOpened = false;

	boolean treeViewOpened = false;

	boolean treeMultipleRootsViewOpened = false;

	@Before
	public void runBefore() {
		treeFormViewOpened = false;
		tableViewOpened = false;
		treeViewOpened = false;
		tableFormViewOpened = false;
	}

	@Override
	@After
	public void runAfterEveryTest() throws CoreException {
		if (treeFormViewOpened)
			closeView(TEST_SAVEABLE_TREE_FORM_VIEW);
		if (tableFormViewOpened)
			closeView(TEST_SAVEABLE_TABLE_FORM_VIEW);
		if (tableViewOpened)
			closeView(TEST_SAVEABLE_TABLE_VIEW);
		if (treeViewOpened)
			closeView(TEST_SAVEABLE_TREE_VIEW);
		if (treeMultipleRootsViewOpened)
			closeView(TEST_SAVEABLE_VIEW_WITH_CUSTOM_ELEMENTS_CONTENT_PROVIDER);
		super.runAfterEveryTest();
	}

	@Test
	public void canAccessEditingActionsOnSaveableResourceTreeFormView()
			throws Exception {
		SWTBotTreeItem libraryNode = prepareSaveableTreeFormViewAndGetLibraryNode();
		canAccessStandardEditingActions(libraryNode);
	}

	@Test
	public void canPerformNewChildActionOnSaveableResourceTreeFormView()
			throws Exception {
		SWTBotTreeItem libraryNode = prepareSaveableTreeFormViewAndGetLibraryNode();
		createNewChild(libraryNode, BOOK_ON_TAPE);
		assertDirtyThenSaveAndAssertNotDirty(TEST_SAVEABLE_TREE_FORM_VIEW);
	}

	@Test
	public void addingNewElementSelectsTheAddedElement()
			throws Exception {
		SWTBotTreeItem libraryNode = prepareSaveableTreeViewAndGetLibraryNode();
		// to effectively execute this test, the tree must not be expanded
		Assert.assertFalse(libraryNode.isExpanded());
		createNewChild(libraryNode, BOOK_ON_TAPE);
		// now the tree must be expanded
		Assert.assertTrue(libraryNode.isExpanded());
		// and the new added node must be selected
		Assert.assertTrue(libraryNode.getNode(BOOK_ON_TAPE).isSelected());
		assertDirtyThenSaveAndAssertNotDirty(TEST_SAVEABLE_TREE_VIEW);
	}

	@Test
	public void canPerformDeleteActionOnSaveableResourceTreeFormView()
			throws Exception {
		clickOnContextMenu(
				getWriterNode(prepareSaveableTreeFormViewAndGetLibraryNode()),
				ACTION_DELETE);
		assertDirtyThenSaveAndAssertNotDirty(TEST_SAVEABLE_TREE_FORM_VIEW);
	}

	@Test
	public void canPerformUndoDeleteActionOnSaveableResourceTreeFormView()
			throws Exception {
		SWTBotTreeItem libraryNode = prepareSaveableTreeFormViewAndGetLibraryNode();
		clickOnContextMenu(getWriterNode(libraryNode), ACTION_DELETE);
		assertSaveableViewIsDirty(true, TEST_SAVEABLE_TREE_FORM_VIEW);
		undo(ACTION_DELETE);
		// make sure the writer is back
		getWriterNode(libraryNode);
		saveViewAndAssertNotDirty(TEST_SAVEABLE_TREE_FORM_VIEW);
	}

	@Test
	public void canPerformDeleteActionOnSaveableTableView() throws Exception {
		SWTBotTable table = prepareSaveableTableView();
		assertTableItemsSize(table, 2);
		table.select(0); // otherwise context menu might not be created
		clickOnContextMenu(table, ACTION_DELETE);
		assertTableItemsSize(table, 1);
		assertDirtyThenSaveAndAssertNotDirty(TEST_SAVEABLE_TABLE_VIEW);
	}

	@Test
	public void canPerformNewSiblingActionOnSaveableTableView()
			throws Exception {
		SWTBotTable table = prepareSaveableTableView();
		assertTableItemsSize(table, 2);
		table.select(0); // otherwise context menu might not be created
		clickOnContextMenu(table, NEW_SIBLING, "Book");
		assertTableItemsSize(table, 3);
		assertDirtyThenSaveAndAssertNotDirty(TEST_SAVEABLE_TABLE_VIEW);
	}

	@Test
	public void canPerformDeleteActionOnSaveableTableFormView() throws Exception {
		SWTBotTable table = prepareSaveableTableFormView();
		assertTableItemsSize(table, 2);
		table.select(0); // otherwise context menu might not be created
		clickOnContextMenu(table, ACTION_DELETE);
		assertTableItemsSize(table, 1);
		assertDirtyThenSaveAndAssertNotDirty(TEST_SAVEABLE_TABLE_FORM_VIEW);
	}

	@Test
	public void canPerformNewSiblingActionOnSaveableTableFormView()
			throws Exception {
		SWTBotTable table = prepareSaveableTableFormView();
		assertTableItemsSize(table, 2);
		table.select(0); // otherwise context menu might not be created
		clickOnContextMenu(table, NEW_SIBLING, "Book");
		assertTableItemsSize(table, 3);
		assertDirtyThenSaveAndAssertNotDirty(TEST_SAVEABLE_TABLE_FORM_VIEW);
	}
	
	@Test
	public void canPerformUndoDeleteActionOnSaveableTreeView() throws Exception {
		SWTBotTreeItem libraryNode = prepareSaveableTreeViewAndGetLibraryNode();
		clickOnContextMenu(getWriterNode(libraryNode), ACTION_DELETE);
		assertSaveableViewIsDirty(true, TEST_SAVEABLE_TREE_VIEW);
		undo(ACTION_DELETE);
		// make sure the writer is back
		getWriterNode(libraryNode);
		saveViewAndAssertNotDirty(TEST_SAVEABLE_TREE_VIEW);
	}

	@Test
	public void doubleClickOpensDialogOnSaveableTreeView() throws Exception {
		SWTBotTreeItem libraryNode = prepareSaveableTreeViewAndGetLibraryNode();
		checkDoubleClickDialog(libraryNode, TEST_SAVEABLE_TREE_VIEW);
	}

	@Test
	public void doubleClickOpensDialogOnSaveableTreeFormView() throws Exception {
		SWTBotTreeItem libraryNode = prepareSaveableTreeFormViewAndGetLibraryNode();
		checkDoubleClickDialog(libraryNode, TEST_SAVEABLE_TREE_FORM_VIEW);
	}

	@Test
	public void treeViewWithMultipleRootsIsRefreshedWhenNewElementIsAdded() throws Exception {
		SWTBotTree tree = prepareSaveableTreeViewForMultipleRootElements();
		SWTBotTreeItem[] allItems = tree.getAllItems();
		SWTBotTreeItem firstElement = allItems[0];
		int initialSize = allItems.length;
		clickOnContextMenu(firstElement, NEW_SIBLING, "Book");
		allItems = tree.getAllItems();
		assertEquals(initialSize + 1, allItems.length);
		SWTBotTreeItem addedSibling = allItems[1];
		assertEquals("Book: null", addedSibling.getText());
	}

	@Test
	public void treeViewWithMultipleRootsIsRefreshedWhenElementIsDeleted() throws Exception {
		SWTBotTree tree = prepareSaveableTreeViewForMultipleRootElements();
		SWTBotTreeItem[] allItems = tree.getAllItems();
		SWTBotTreeItem firstElement = allItems[0];
		int initialSize = allItems.length;
		clickOnContextMenu(firstElement, ACTION_DELETE);
		allItems = tree.getAllItems();
		assertEquals(initialSize - 1, allItems.length);
	}

	@Test
	public void undoRedoDeleteActionUpdatesDirtyStateCorrectly() throws Exception {
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=479700
		SWTBotTreeItem libraryNode = prepareSaveableTreeViewAndGetLibraryNode();
		clickOnContextMenu(getWriterNode(libraryNode), ACTION_DELETE);
		assertSaveableViewIsDirty(true, TEST_SAVEABLE_TREE_VIEW);
		undo(ACTION_DELETE);
		// make sure the writer is back
		getWriterNode(libraryNode);
		// undo must have reset the dirty state to false
		assertSaveableViewIsDirty(false, TEST_SAVEABLE_TREE_VIEW);
		redo(ACTION_DELETE);
		// redo must have set the dirty state to true
		assertSaveableViewIsDirty(true, TEST_SAVEABLE_TREE_VIEW);
		saveViewAndAssertNotDirty(TEST_SAVEABLE_TREE_VIEW);
	}

	protected void checkDoubleClickDialog(SWTBotTreeItem libraryNode, String viewName) {
		libraryNode.doubleClick();
		bot.shell(LIBRARY_LABEL);
		assertDialogControlsOfCustomLibraryNode(true);
		modifyText(LIBRARY_NAME);
		bot.button("OK").click();
		assertDirtyThenSaveAndAssertNotDirty(viewName);
	}

	protected void createNewChild(SWTBotTreeItem libraryNode, String childType) {
		clickOnContextMenu(libraryNode, NEW_CHILD, childType);
		// check that the new item was created
		libraryNode.expand().getNode(childType);
	}

	protected void assertTableItemsSize(final SWTBotTable table,
			final int expectedSize) {
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				assertEquals(expectedSize, table.widget.getItems().length);
			}
		});
	}

	protected SWTBotTreeItem prepareSaveableTreeFormViewAndGetLibraryNode()
			throws CoreException, InvocationTargetException,
			InterruptedException, IOException {
		createProjectAndTestFiles();
		openTestView(TEST_SAVEABLE_TREE_FORM_VIEW);
		treeFormViewOpened = true;
		SWTBotTreeItem libraryNode = getRootOfTreeFromView(
				TEST_SAVEABLE_TREE_FORM_VIEW).getTreeItem(LIBRARY_LABEL);
		return libraryNode;
	}

	protected SWTBotTable prepareSaveableTableView() throws CoreException,
			InvocationTargetException, InterruptedException, IOException {
		createProjectAndTestFiles();
		openTestView(TEST_SAVEABLE_TABLE_VIEW);
		SWTBotTable table = bot.table();
		tableViewOpened = true;
		return table;
	}
	
	protected SWTBotTable prepareSaveableTableFormView() throws CoreException,
			InvocationTargetException, InterruptedException, IOException {
		createProjectAndTestFiles();
		openTestView(TEST_SAVEABLE_TABLE_FORM_VIEW);
		SWTBotTable table = bot.table();
		tableFormViewOpened = true;
		return table;
	}

	protected SWTBotTreeItem prepareSaveableTreeViewAndGetLibraryNode()
			throws CoreException, InvocationTargetException,
			InterruptedException, IOException {
		createProjectAndTestFiles();
		openTestView(TEST_SAVEABLE_TREE_VIEW);
		treeViewOpened = true;
		SWTBotTreeItem libraryNode = getRootOfTreeFromView(
				TEST_SAVEABLE_TREE_VIEW).getTreeItem(LIBRARY_LABEL);
		return libraryNode;
	}

	protected SWTBotTree prepareSaveableTreeViewForMultipleRootElements()
			throws CoreException, InvocationTargetException,
			InterruptedException, IOException {
		createProjectAndTestFiles();
		openTestView(TEST_SAVEABLE_VIEW_WITH_CUSTOM_ELEMENTS_CONTENT_PROVIDER);
		treeMultipleRootsViewOpened = true;
		SWTBotTree tree = getRootOfTreeFromView(
				TEST_SAVEABLE_VIEW_WITH_CUSTOM_ELEMENTS_CONTENT_PROVIDER);
		return tree;
	}
}
