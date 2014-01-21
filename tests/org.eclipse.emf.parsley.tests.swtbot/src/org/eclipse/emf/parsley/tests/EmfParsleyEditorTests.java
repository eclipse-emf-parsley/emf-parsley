/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertTrue;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Lorenzo Bettini
 * 
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class EmfParsleyEditorTests extends EmfParsleyAbstractTests {

	public void canCreateProject() throws Exception {
		createMyTestProject();
	}

	public void canCreateProjectWithFile() throws Exception {
		createProjectAndTestFile();
		getProjectTreeItem(MY_TEST_PROJECT).expand();
	}

	@Test
	public void canOpenEmfFormEditorOnTestFile() throws Exception {
		openEmfEditorOnTestFile(EMF_TREE_EDITOR, MY_EXTLIBRARY);
	}

	@Test
	public void canAccessEditorTreeOfLibrary() throws Exception {
		getWriterNode(getLibraryNode(openEditorAndGetTreeRoot(EMF_TREE_EDITOR,
				MY_EXTLIBRARY, MY_EXT_LIBRARY_PLATFORM_URI)));
	}

	@Test
	public void canAccessContextMenuOfLibrary() throws Exception {
		SWTBotTreeItem libraryNode = getLibraryNode(openEditorAndGetTreeRoot(
				EMF_TREE_EDITOR, MY_EXTLIBRARY, MY_EXT_LIBRARY_PLATFORM_URI));
		clickOnContextMenu(libraryNode, NEW_CHILD, BOOK_ON_TAPE);
		// check that the new item was created
		libraryNode.expand().getNode(BOOK_ON_TAPE);
		SWTBotEditor editor = assertEditorDirty(EMF_TREE_EDITOR);
		undo("New " + BOOK_ON_TAPE);
		assertEditorNotDirty(EMF_TREE_EDITOR);
		editor.save();
	}

	@Test
	public void canAccessStandardEditingActionsOnTreeEditor() throws Exception {
		SWTBotTreeItem libraryNode = getLibraryNode(openEditorAndGetTreeRoot(
				EMF_TREE_EDITOR, MY_EXTLIBRARY, MY_EXT_LIBRARY_PLATFORM_URI));
		canAccessStandardEditingActions(libraryNode);
	}

	@Test
	public void canAccessEditorTreeOfStatemachine() throws Exception {
		accessStateMachineNodes(openEditorAndGetTreeRoot(
				EMF_TREE_EDITOR_STATEMACHINE, MY_STATEMACHINE,
				MY_STATEMACHINE_PLATFORM_URI));
	}

	@Test
	public void canPopulateOutlineView() throws Exception {
		openEmfEditorOnTestFile(EMF_TREE_EDITOR, MY_EXTLIBRARY);
		getRootOfOutlineViewTree();
	}

	@Test
	public void canOpenPropertyViewWithDoubleClick() throws Exception {
		// double click on Library node on the tree of the editor and outline
		// view should open the property view
		getLibraryNode(
				openEditorAndGetTreeRoot(EMF_TREE_EDITOR, MY_EXTLIBRARY,
						MY_EXT_LIBRARY_PLATFORM_URI)).doubleClick();
		assertPropertyViewIsOpenedAndCloseIt();
		getLibraryNode(getRootOfOutlineViewTree()).doubleClick();
		assertPropertyViewIsOpenedAndCloseIt();
	}

	//@Test
	// this takes some time to test (due to timeout)
	// and it does not make much sense as a test (Lorenzo)
	public void testEditorWithNoMouseEvents() throws Exception {
		// double click on Library node on the tree of the editor and outline
		// view should NOT open the property view
		getLibraryNode(
				openEditorAndGetTreeRoot(EMF_TREE_EDITOR_NO_MOUSE, MY_EXTLIBRARY,
						MY_EXT_LIBRARY_PLATFORM_URI)).doubleClick();
		assertPropertyViewIsNotShown();
		getLibraryNode(getRootOfOutlineViewTree()).doubleClick();
		assertPropertyViewIsNotShown();
	}

	@Test
	public void testEditorWithCustomLabels() throws Exception {
		SWTBotTreeItem rootOfTree = openEditorAndGetTreeRoot(
				EMF_TREE_EDITOR_CUSTOM_LABEL, MY_EXTLIBRARY,
				MY_EXT_LIBRARY_PLATFORM_URI);
		accessTreeWithCustomLabels(rootOfTree);
		accessTreeWithCustomLabels(getRootOfOutlineViewTree());
	}

	@Test
	public void testEditorWithCustomLibraryLabels() throws Exception {
		SWTBotTreeItem rootOfTree = openEditorAndGetTreeRoot(
				EMF_CUSTOM_LIBRARY_EDITOR, MY_EXTLIBRARY,
				MY_EXT_LIBRARY_PLATFORM_URI);
		SWTBotTreeItem treeItem = accessTreeWithCustomLibraryLabels(rootOfTree);
		assertTreeItemImage(treeItem, getImageDescriptorFromTest("book2.png"));
		treeItem = accessTreeWithCustomLibraryLabels(getRootOfOutlineViewTree());
		assertTreeItemImage(treeItem, getImageDescriptorFromTest("book2.png"));
	}

	//@Test
	// TODO this does not seem to work anymore...
	// the image is the same, but probably some internal modifications
	// or adjustments make the images differ from the binary point of view...
	public void testEditorWithCustomLibraryLabelsInEditPlugin() throws Exception {
		SWTBotTreeItem rootOfTree = openEditorAndGetTreeRoot(
				EMF_TREE_EDITOR_CUSTOM_LABEL, MY_EXTLIBRARY,
				MY_EXT_LIBRARY_PLATFORM_URI);
		SWTBotTreeItem treeItem = accessTreeWithCustomLabels(rootOfTree);
		assertTreeItemImage(treeItem, getImageDescriptorFromTest("custom_book.png"));
		treeItem = accessTreeWithCustomLabels(getRootOfOutlineViewTree());
		assertTreeItemImage(treeItem, getImageDescriptorFromTest("custom_book.png"));
	}

	@Test
	public void canSelectInOutlineView() throws Exception {
		SWTBotTreeItem editorTreeRoot = openEditorAndGetTreeRoot(EMF_TREE_EDITOR,
				MY_EXTLIBRARY, MY_EXT_LIBRARY_PLATFORM_URI);
		getLibraryWriterNode(getRootOfOutlineViewTree()).select();
		assertTrue("writer node should be selected",
				getLibraryWriterNode(editorTreeRoot).isSelected());
	}

	@Test
	public void statusLineFromOutlineView() throws Exception {
		openEditorAndGetTreeRoot(EMF_TREE_EDITOR, MY_EXTLIBRARY,
				MY_EXT_LIBRARY_PLATFORM_URI);
		getLibraryWriterNode(getRootOfOutlineViewTree()).select();
		assertStatusLine("Selected Object: " + WRITER_LABEL);
	}

	@Test
	public void statusLineFromEditor() throws Exception {
		getLibraryWriterNode(
				openEditorAndGetTreeRoot(EMF_TREE_EDITOR, MY_EXTLIBRARY,
						MY_EXT_LIBRARY_PLATFORM_URI)).select();
		assertStatusLine("Selected Object: " + WRITER_LABEL);
	}

	@Test
	public void tableViewShowsTablesOnSelection() throws Exception {
		SWTBotView tableView = openTestView(EMF_SHOW_ALL_TABLE_VIEW);
		// select on the editor's tree
		SWTBotTreeItem rootOfEditorTree = openEditorAndGetTreeRoot(EMF_TREE_EDITOR,
				MY_EXTLIBRARY, MY_EXT_LIBRARY_PLATFORM_URI);
		getLibraryWriterNode(rootOfEditorTree).select();
		getTableHeader(ADDRESS_LABEL);
		getTableHeader(FIRSTNAME_LABEL);
		// select on the outline view
		getLibraryNode(getRootOfOutlineViewTree()).select();
		getTableHeader(ADDRESS_LABEL);
		// the writers table is the second one
		getTableHeader(1, FIRSTNAME_LABEL);
		tableView.close();
	}

	@Test
	public void selectionViewOnSelection() throws Exception {
		SWTBotView selectionView = openTestView(LIBRARY_EMF_VIEW);
		// select on the editor's tree
		SWTBotTreeItem rootOfEditorTree = openEditorAndGetTreeRoot(EMF_TREE_EDITOR,
				MY_EXTLIBRARY, MY_EXT_LIBRARY_PLATFORM_URI);
		// we select the library in the editor...
		getLibraryNode(rootOfEditorTree).select();
		// and the selection view should show its children (so we must find the
		// writer)
		getRootOfTreeFromView(LIBRARY_EMF_VIEW).getTreeItem(WRITER_LABEL);
		selectionView.close();
	}

	@Test
	public void selectionViewOnSelectionOnStatemachine() throws Exception {
		SWTBotView selectionView = openTestView(LIBRARY_EMF_VIEW);
		// select on the editor's tree
		SWTBotTreeItem rootOfEditorTree = openEditorAndGetTreeRoot(
				EMF_TREE_EDITOR_STATEMACHINE, MY_STATEMACHINE,
				MY_STATEMACHINE_PLATFORM_URI);
		// we select the statemachine in the editor...
		getStatemachineNode(rootOfEditorTree).select();
		// and the selection view should show its children (so we must find the
		// events)
		getRootOfTreeFromView(LIBRARY_EMF_VIEW).getTreeItem(EVENT_LABEL);
		// and states
		getTransitionNode(getRootOfTreeFromView(LIBRARY_EMF_VIEW).getTreeItem(
				STATE_LABEL));
		selectionView.close();
	}
}
