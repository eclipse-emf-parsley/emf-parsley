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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
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
		getWriterNode(getLibraryNode(view.bot().tree().getTreeItem(HARDCODED_LIBRARY_PLATFORM_URI)));
		// bot.sleep(2000);
		view.close();
	}

	@Test
	public void testOnSelectionLibraryTreeView() throws Exception {
		SWTBotView selectionView = openTestView(LIBRARY_ON_SELECTION_TREE_VIEW_WITH_RESOURCE_URI);
		// select on the editor's tree
		SWTBotTreeItem rootOfEditorTree = openEditorAndGetTreeRoot();
		// we select the library in the editor...
		getLibraryNode(rootOfEditorTree).select();
		// and the selection view should show its children (so we must find the
		// writer)
		createNewSiblingAndSelectCreatedInTree(LIBRARY_ON_SELECTION_TREE_VIEW_WITH_RESOURCE_URI, WRITER_LABEL,
				BOOK_ON_TAPE);
		assertOriginalEditorIsNotified();
		selectionView.close();
	}

	@Test
	public void testOnSelectionLibraryTreeViewOnXtextFile() throws Exception {
		SWTBotView selectionView = openTestView(LIBRARY_ON_SELECTION_TREE_VIEW_WITH_RESOURCE_URI);
		// select on the editor's tree
		SWTBotTreeItem rootOfEditorTree = openEditorAndGetTreeRoot(EMF_TREE_EDITOR_XTEXT, MY_PARSLEY,
				MY_PARSLEY_PLATFORM_URI);
		// we select the Xtext Parsley model in the editor...
		getParsleyModelNode(rootOfEditorTree).select();

		// and the selection view should show its children (the Xtext Parsley
		// module)
		getRootOfTreeFromView(LIBRARY_ON_SELECTION_TREE_VIEW_WITH_RESOURCE_URI).getTreeItem(PARSLEY_MODULE_LABEL);
		selectionView.close();
	}

	@Test
	public void testOnSelectionLibraryTreeFormView() throws Exception {
		final SWTBotView view = openTestView(LIBRARY_ON_SELECTION_TREE_FORM_VIEW);

		// select on the editor's tree
		SWTBotTreeItem rootOfEditorTree = openEditorAndGetTreeRoot();
		// we select the library in the editor...
		getLibraryNode(rootOfEditorTree).select();
		// and the selection view should show its children (so we must find the
		// writer)
		getRootOfTreeFromView(LIBRARY_ON_SELECTION_TREE_FORM_VIEW).getTreeItem(WRITER_LABEL).select();

		// now test the form: the writer is already selected in the tree
		SWTFormsBot formbot = formBotFromView(view);
		formbot.label(FIRSTNAME_LABEL);

		// test the context menu
		createNewSiblingAndSelectCreatedInTree(LIBRARY_ON_SELECTION_TREE_FORM_VIEW, WRITER_LABEL, BOOK_ON_TAPE);
		assertOriginalEditorIsNotified();

		view.close();
	}

	@Test
	public void testOnSelectionLibraryBooksTableView() throws Exception {
		final SWTBotView view = openTestView(LIBRARY_BOOKS_TABLE_VIEW);
		// the table should already show the column headers
		getTableHeader(AUTHOR_COLUMN_HEADER);

		final SWTBotTreeItem resourceNode = openEditorAndGetTreeRoot();
		SWTBotTreeItem libraryNode = getLibraryNode(resourceNode);

		// select the library
		libraryNode.select();
		// the table shows the books, select a table row
		bot.table().select(0);

		// select the resource
		resourceNode.select();
		// the table shows the books, select a table row
		bot.table().select(0);

		// test the context menu
		createNewSiblingAndAssertTableSize(bot.table(), 2, "Book");
		assertOriginalEditorIsNotified();

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

		final SWTBotTreeItem resourceNode = openEditorAndGetTreeRoot();
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

		final SWTBotTreeItem resourceNode = openEditorAndGetTreeRoot();
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

		// test the context menu
		createNewSiblingAndAssertTableSize(bot.table(), 2, "Book");
		assertOriginalEditorIsNotified();

		getWriterNode(libraryNode).select();
		assertTableItemsCount(bot.table(), 0);

		view.close();
	}

	@Test
	public void testOnSelectionLibraryBooksTableFormViewWithCustomContentProvider() throws Exception {
		final SWTBotView view = openTestView(LIBRARY_BOOKS_TABLE_FORM_VIEW_CUSTOM_PROVIDER);
		// the table should already show the column headers
		getTableHeader(AUTHOR_COLUMN_HEADER);

		final SWTBotTreeItem resourceNode = openEditorAndGetTreeRoot();
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

	protected SWTBotTreeItem openEditorAndGetTreeRoot()
			throws CoreException, InvocationTargetException, InterruptedException, IOException {
		return openEditorAndGetTreeRoot(EMF_TREE_EDITOR, MY_EXTLIBRARY,
				MY_EXT_LIBRARY_PLATFORM_URI);
	}

	protected void assertOriginalEditorIsNotified() {
		// the original editor is dirty because it's notified through the
		// editing domain of the on selection view's context menu
		assertEditorDirtySaveEditorAndAssertNotDirty(EMF_TREE_EDITOR);
	}
}
