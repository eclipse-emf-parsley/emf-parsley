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
package org.eclipse.emf.parsley.tests.swtbot;

import static org.junit.Assert.assertTrue;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Lorenzo Bettini
 * 
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class EmfParsleyEditorTests extends EmfParsleySWTBotAbstractTests {

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
	public void canAccessCustomContextMenuOfLibrary() throws Exception {
		SWTBotTreeItem libraryNode = getLibraryNode(openEditorAndGetTreeRoot(
				EMF_CUSTOM_MENU_LIBRARY_EDITOR, MY_EXTLIBRARY, MY_EXT_LIBRARY_PLATFORM_URI));
		SWTBotTreeItem writerNode = getWriterNode(libraryNode);
		clickOnContextMenu(writerNode, CUSTOM_NEW_BOOK);
		// check that the new item was created
		libraryNode.expand().getNode(CUSTOM_NEW_BOOK_TITLE);
		SWTBotEditor editor = assertEditorDirty(EMF_CUSTOM_MENU_LIBRARY_EDITOR);
		undo("Do Command");
		assertEditorNotDirty(EMF_CUSTOM_MENU_LIBRARY_EDITOR);
		editor.save();
	}

	@Test
	public void changingSelectionUpdatesContextMenu() throws Exception {
		SWTBotTreeItem libraryNode = getLibraryNode(openEditorAndGetTreeRoot(
				EMF_TREE_EDITOR, MY_EXTLIBRARY, MY_EXT_LIBRARY_PLATFORM_URI));
		clickOnContextMenu(libraryNode, NEW_CHILD, BOOK_ON_TAPE);
		// check that the new item was created
		libraryNode.expand().getNode(BOOK_ON_TAPE);
		// change the selection to the writer node
		libraryNode.expand().getNode(WRITER_LABEL).select();
		// change the selection back to the created book node
		SWTBotTreeItem bookOnTapeNode = libraryNode.expand().getNode(BOOK_ON_TAPE).select();
		clickOnContextMenu(bookOnTapeNode, ACTION_DELETE);
		// check that we did not remove the writer node
		libraryNode.expand().getNode(WRITER_LABEL).select();
		SWTBotEditor editor = assertEditorDirty(EMF_TREE_EDITOR);
		editor.save();
	}

	@Test
	public void canAccessStandardEditingActionsOnTreeEditor() throws Exception {
		SWTBotTreeItem libraryNode = getLibraryNode(openEditorAndGetTreeRoot(
				EMF_TREE_EDITOR, MY_EXTLIBRARY, MY_EXT_LIBRARY_PLATFORM_URI));
		canAccessStandardEditingActions(libraryNode);
	}

	@Test
	public void canAccessEditorTreeOfXtextFile() throws Exception {
		accessXtextFileNodes(openEditorAndGetTreeRoot(
				EMF_TREE_EDITOR_XTEXT, MY_PARSLEY,
				MY_PARSLEY_PLATFORM_URI));
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

}
