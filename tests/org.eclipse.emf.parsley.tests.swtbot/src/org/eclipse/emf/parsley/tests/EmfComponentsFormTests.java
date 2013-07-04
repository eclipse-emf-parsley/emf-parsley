package org.eclipse.emf.parsley.tests;

import static org.eclipse.swtbot.swt.finder.waits.Conditions.shellCloses;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.forms.finder.SWTFormsBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author bettini
 * 
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class EmfComponentsFormTests extends EmfComponentsAbstractTests {

	@Test
	public void detailViewShowsDetailsOnSelection() throws Exception {
		SWTBotView detailView = openTestView(EMF_DETAIL_VIEW);
		// select on the editor's tree
		SWTBotTreeItem rootOfEditorTree = getRootOfEditorTree(EMF_TREE_EDITOR,
				MY_EXTLIBRARY, MY_EXT_LIBRARY_PLATFORM_URI);
		getLibraryWriterNode(rootOfEditorTree).select();
		SWTFormsBot formbot = formBotFromView(detailView);
		formbot.label(ADDRESS_LABEL);
		formbot.text(WRITER_S_ADDRESS_TEXT);
		formbot.label(FIRSTNAME_LABEL);
		// select on the outline view
		getLibraryNode(getRootOfOutlineViewTree()).select();
		formbot.label(ADDRESS_LABEL);
		formbot.text(LIBRARY_S_ADDRESS_TEXT);
		// the label for 'people'
		formbot.label(PEOPLE_LABEL);
		// the inner label listing all the people, before the button "..."
		formbot.label(PEOPLE_TEXT);
		// now select again on the editor's tree
		getLibraryWriterNode(rootOfEditorTree).select();
		formbot.label(ADDRESS_LABEL);
		formbot.text(WRITER_S_ADDRESS_TEXT);
		// detailView.close();
	}

	protected SWTFormsBot formBotFromView(SWTBotView detailView) {
		return new SWTFormsBot(detailView.getWidget());
	}

	@Test
	public void detailViewShowsCustomDetailsOnSelection() throws Exception {
		SWTBotView detailView = openTestView(LIBRARY_CUSTOM_DETAIL_VIEW);
		// select on the editor's tree
		SWTBotTreeItem rootOfEditorTree = getRootOfEditorTree(EMF_TREE_EDITOR,
				MY_EXTLIBRARY, MY_EXT_LIBRARY_PLATFORM_URI);
		getLibraryWriterNode(rootOfEditorTree).select();
		SWTFormsBot formbot = formBotFromView(detailView);
		formbot.label(ADDRESS_LABEL);
		formbot.text(WRITER_S_ADDRESS_TEXT);
		formbot.label(CUSTOM_FIRSTNAME_LABEL);
		formbot.label(CUSTOM_SURNAME_LABEL);
		// select on the outline view
		getLibraryNode(getRootOfOutlineViewTree()).select();
		formbot.label(ADDRESS_LABEL);
		formbot.text(LIBRARY_S_ADDRESS_TEXT);
		// the label for 'people'
		formbot.label(PEOPLE_LABEL);
		// the inner label listing all the people, before the button "..."
		formbot.label(CUSTOM_PEOPLE_TEXT);
	}

	@Test
	public void customControlOfWriterNode() throws Exception {
		SWTBotView detailView = openTestView(LIBRARY_CUSTOM_DETAIL_VIEW);
		// select on the editor's tree
		SWTBotTreeItem rootOfEditorTree = getRootOfEditorTree(EMF_TREE_EDITOR,
				MY_EXTLIBRARY, MY_EXT_LIBRARY_PLATFORM_URI);
		getLibraryWriterNode(rootOfEditorTree).select();
		SWTFormsBot formbot = formBotFromView(detailView);
		formbot.label(ADDRESS_LABEL);
		formbot.text(WRITER_NAME);
	}

	@Test
	public void treeFormViewShowsOnSelection() throws Exception {
		SWTBotView view = openTestView(EMF_TREE_FORM_DETAIL_VIEW);
		// select on the editor's tree
		SWTBotTreeItem rootOfEditorTree = getRootOfEditorTree(EMF_TREE_EDITOR,
				MY_EXTLIBRARY, MY_EXT_LIBRARY_PLATFORM_URI);
		// select on the editor
		getLibraryNode(rootOfEditorTree).select();
		
		// now select on the tree inside the view
		view.bot().tree().getTreeItem(WRITER_LABEL).select();
		
		SWTFormsBot formbot = formBotFromView(view);
		formbot.label(ADDRESS_LABEL);
		formbot.text(WRITER_S_ADDRESS_TEXT);
		formbot.label(FIRSTNAME_LABEL);
	}

	@Test
	public void testMultipleFeatureControlDialog() throws Exception {
		SWTBotView detailView = openTestView(LIBRARY_CUSTOM_DETAIL_VIEW);
		// select on the editor's tree
		SWTBotTreeItem rootOfEditorTree = getRootOfEditorTree(EMF_TREE_EDITOR,
				MY_EXTLIBRARY, MY_EXT_LIBRARY_PLATFORM_URI);
		getLibraryBookNode(rootOfEditorTree).select();
		SWTFormsBot formbot = formBotFromView(detailView);
		formbot.button("...").click();
		SWTBotShell shell = bot.shell("Select -- Book: Lorenzo's Book");
		shell.activate();
		// simulate the addition of a borrower in the dialog
		bot.table(0).select("Borrower: Foo"); // left table
		bot.button("Add").click();
		
		// is the additional proposal there?
		bot.table(0).select("Borrower: Fake Borrower"); // left table
		
		// now the borrower should be in the right table
		bot.table(1).select("Borrower: Foo"); // right table
		bot.button("OK").click();
		bot.waitUntil(shellCloses(shell), 50000);
		
		// check that the selected borrower has been added
		formbot.label("Borrower: Foo");
		
		getEditor(EMF_TREE_EDITOR).close();
	}
}
