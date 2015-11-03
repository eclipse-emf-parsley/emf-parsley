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

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.forms.finder.SWTFormsBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Lorenzo Bettini
 * 
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class EmfParsleyOnSelectionViewTests extends EmfParsleySWTBotAbstractTests {

	@Test
	public void testOnSelectionLibraryTreeViewWithResourceURI() throws Exception {
		SWTBotView view = openTestView(LIBRARY_ON_SELECTION_TREE_VIEW_WITH_RESOURCE_URI);
		getWriterNode(getLibraryNode(view.bot().tree()
				.getTreeItem(HARDCODED_LIBRARY_PLATFORM_URI)));
		// bot.sleep(2000);
		view.close();
	}

	@Test
	public void testOnSelectionLibraryTreeView() throws Exception {
		SWTBotView selectionView = openTestView(LIBRARY_ON_SELECTION_TREE_VIEW_WITH_RESOURCE_URI);
		// select on the editor's tree
		SWTBotTreeItem rootOfEditorTree = openEditorAndGetTreeRoot(EMF_TREE_EDITOR,
				MY_EXTLIBRARY, MY_EXT_LIBRARY_PLATFORM_URI);
		// we select the library in the editor...
		getLibraryNode(rootOfEditorTree).select();
		// and the selection view should show its children (so we must find the
		// writer)
		getRootOfTreeFromView(LIBRARY_ON_SELECTION_TREE_VIEW_WITH_RESOURCE_URI).getTreeItem(WRITER_LABEL);
		selectionView.close();
	}

	@Test
	public void testOnSelectionLibraryTreeViewOnXtextFile() throws Exception {
		SWTBotView selectionView = openTestView(LIBRARY_ON_SELECTION_TREE_VIEW_WITH_RESOURCE_URI);
		// select on the editor's tree
		SWTBotTreeItem rootOfEditorTree = openEditorAndGetTreeRoot(
				EMF_TREE_EDITOR_XTEXT, MY_PARSLEY,
				MY_PARSLEY_PLATFORM_URI);
		// we select the Xtext Parsley model in the editor...
		getParsleyModelNode(rootOfEditorTree).select();
		
		// and the selection view should show its children (the Xtext Parsley module)
		getRootOfTreeFromView(LIBRARY_ON_SELECTION_TREE_VIEW_WITH_RESOURCE_URI).getTreeItem(PARSLEY_MODULE_LABEL);
		selectionView.close();
	}

	@Test
	public void testOnSelectionLibraryTreeFormView() throws Exception {
		final SWTBotView view = openTestView(LIBRARY_ON_SELECTION_TREE_FORM_VIEW);
		
		// select on the editor's tree
		SWTBotTreeItem rootOfEditorTree = openEditorAndGetTreeRoot(EMF_TREE_EDITOR,
				MY_EXTLIBRARY, MY_EXT_LIBRARY_PLATFORM_URI);
		// we select the library in the editor...
		getLibraryNode(rootOfEditorTree).select();
		// and the selection view should show its children (so we must find the
		// writer)
		getRootOfTreeFromView(LIBRARY_ON_SELECTION_TREE_FORM_VIEW).getTreeItem(WRITER_LABEL).select();

		// now test the form: the writer is already selected in the tree
		SWTFormsBot formbot = formBotFromView(view);
		formbot.label(FIRSTNAME_LABEL);

		view.close();
	}

	// FIXME this does not work anymore since we do not react on
	// selection on files
	// @Test
	public void emfViewShowsSelectedFile() throws Exception {
		SWTBotView view = openTestView(LIBRARY_ON_SELECTION_TREE_VIEW_WITH_RESOURCE_URI);
		createProjectAndTestFiles();
		getFileItemFromTestProject(MY_EXTLIBRARY).select();
		// bot.sleep(2000);
		getWriterNode(getLibraryNode(getRootOfTreeFromView(LIBRARY_ON_SELECTION_TREE_VIEW_WITH_RESOURCE_URI)
				.getTreeItem(MY_EXT_LIBRARY_PLATFORM_URI)));
		getFileItemFromTestProject(MY_PARSLEY).select();
		// bot.sleep(2000);
		SWTBotTreeItem root = getRootOfTreeFromView(LIBRARY_ON_SELECTION_TREE_VIEW_WITH_RESOURCE_URI)
				.getTreeItem(MY_PARSLEY_PLATFORM_URI);
		accessXtextFileNodes(root);
		
		view.close();
	}

	// FIXME this does not work anymore since we do not react on
	// selection on files
	// @Test
	public void testCustomBookImage() throws Exception {
		SWTBotView view = openTestView(LIBRARY_ON_SELECTION_TREE_VIEW_WITH_RESOURCE_URI);
		createProjectAndTestFiles();
		getFileItemFromTestProject(MY_EXTLIBRARY).select();
		SWTBotTreeItem item = getLibraryBookNode(getRootOfTreeFromView(
				LIBRARY_ON_SELECTION_TREE_VIEW_WITH_RESOURCE_URI).getTreeItem(MY_EXT_LIBRARY_PLATFORM_URI));

		// if org.eclipse.swt.internal.gtk.cairoGraphics is not false
		// then the test for equality of image will fail, since the image
		// from the TreeItem will be preprocessed and then it will
		// be different from the expected one
		// then set the vm arg
		// -Dorg.eclipse.swt.internal.gtk.cairoGraphics=false
		// in the launch.
		System.err.println(System
				.getProperty("org.eclipse.swt.internal.gtk.cairoGraphics"));

		assertTreeItemImage(item,
				getImageDescriptorFromLibraryEdit("custom_book.png"));
		view.close();
	}

	@Test
	public void testOnSelectionLibraryBooksTableView() throws Exception {
		final SWTBotView view = openTestView(LIBRARY_BOOKS_TABLE_VIEW);
		// the table should already show the column headers
		getTableHeader(AUTHOR_COLUMN_HEADER);

		final SWTBotTreeItem resourceNode = openEditorAndGetTreeRoot(
				EMF_TREE_EDITOR, MY_EXTLIBRARY, MY_EXT_LIBRARY_PLATFORM_URI);
		SWTBotTreeItem libraryNode = getLibraryNode(resourceNode);

		// select the library
		libraryNode.select();
		// the table shows the books, select a table row
		bot.table().select(0);

		// select the resource
		resourceNode.select();
		// the table shows the books, select a table row
		bot.table().select(0);

		getWriterNode(libraryNode).select();
		// the table won't show anything
		assertTableItemsCount(bot.table(), 0);

		view.close();
	}

	@Test
	public void testOnSelectionLibraryBooksTableViewWithCustomContentProvider() throws Exception {
		final SWTBotView view = openTestView(LIBRARY_BOOKS_TABLE_VIEW_CUSTOM_PROVIDER);
		// the table should already show the column headers
		getTableHeader(AUTHOR_COLUMN_HEADER);

		final SWTBotTreeItem resourceNode = openEditorAndGetTreeRoot(
				EMF_TREE_EDITOR, MY_EXTLIBRARY, MY_EXT_LIBRARY_PLATFORM_URI);
		SWTBotTreeItem libraryNode = getLibraryNode(resourceNode);

		// select the library
		libraryNode.select();
		// the table shows the books, select a table row
		bot.table().select(0);

		// select the resource
		resourceNode.select();
		// the table shows the books, select a table row
		bot.table().select(0);

		getWriterNode(libraryNode).select();
		// since the custom content provider shows the writer's book
		// when a writer is selected, the table shows books,
		// differently from the previous test
		bot.table().select(0);

		view.close();
	}

	@Test
	public void testOnSelectionLibraryBooksTableFormView() throws Exception {
		final SWTBotView view = openTestView(LIBRARY_BOOKS_TABLE_FORM_VIEW);
		// the table should already show the column headers
		getTableHeader(AUTHOR_COLUMN_HEADER);

		final SWTBotTreeItem resourceNode = openEditorAndGetTreeRoot(
				EMF_TREE_EDITOR, MY_EXTLIBRARY, MY_EXT_LIBRARY_PLATFORM_URI);
		SWTBotTreeItem libraryNode = getLibraryNode(resourceNode);

		// select the library
		libraryNode.select();
		// the table shows the books, select a table row
		bot.table().select(0);

		// select the resource
		resourceNode.select();
		// the table shows the books, select a table row
		bot.table().select(0);

		// now test the form: the book is already selected in the table
		SWTFormsBot formbot = formBotFromView(view);
		formbot.label(AUTHOR_LABEL);

		getWriterNode(libraryNode).select();
		assertTableItemsCount(bot.table(), 0);

		view.close();
	}

	@Test
	public void testOnSelectionLibraryBooksTableFormViewWithCustomContentProvider() throws Exception {
		final SWTBotView view = openTestView(LIBRARY_BOOKS_TABLE_FORM_VIEW_CUSTOM_PROVIDER);
		// the table should already show the column headers
		getTableHeader(AUTHOR_COLUMN_HEADER);

		final SWTBotTreeItem resourceNode = openEditorAndGetTreeRoot(
				EMF_TREE_EDITOR, MY_EXTLIBRARY, MY_EXT_LIBRARY_PLATFORM_URI);
		SWTBotTreeItem libraryNode = getLibraryNode(resourceNode);

		// select the library
		libraryNode.select();
		// the table shows the books, select a table row
		bot.table().select(0);

		// select the resource
		resourceNode.select();
		// the table shows the books, select a table row
		bot.table().select(0);

		// now test the form: the book is already selected in the table
		SWTFormsBot formbot = formBotFromView(view);
		formbot.label(AUTHOR_LABEL);

		getWriterNode(libraryNode).select();
		// since the custom content provider shows the writer's book
		// when a writer is selected, the table shows books,
		// differently from the previous test
		bot.table().select(0);

		// now test the form: the book is already selected in the table
		formbot = formBotFromView(view);
		formbot.label(AUTHOR_LABEL);

		view.close();
	}
}
