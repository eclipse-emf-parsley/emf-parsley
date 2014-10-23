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
public class EmfParsleySaveableViewTests extends EmfParsleyAbstractTests {

	boolean treeFormViewOpened = false;
	
	boolean tableFormViewOpened = false;

	boolean tableViewOpened = false;

	boolean treeViewOpened = false;

	@Before
	public void runBefore() {
		treeFormViewOpened = false;
		tableViewOpened = false;
		treeViewOpened = false;
		tableFormViewOpened = false;
	}

	@After
	public void runAfterEveryTest() throws CoreException {
		if (treeFormViewOpened)
			closeLibraryView(TEST_SAVEABLE_TREE_FORM_VIEW);
		if (tableFormViewOpened)
			closeLibraryView(TEST_SAVEABLE_TABLE_FORM_VIEW);
		if (tableViewOpened)
			closeLibraryView(TEST_SAVEABLE_TABLE_VIEW);
		if (treeViewOpened)
			closeLibraryView(TEST_SAVEABLE_TREE_VIEW);
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
		table.select(0); // otherwise context menu might not be created
		clickOnContextMenu(table, ACTION_DELETE);
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
		table.select(0); // otherwise context menu might not be created
		clickOnContextMenu(table, ACTION_DELETE);
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

}
