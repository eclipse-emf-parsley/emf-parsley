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

import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.syncExec;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.allOf;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.widgetOfType;
import static org.eclipse.swtbot.swt.finder.waits.Conditions.shellCloses;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swtbot.eclipse.finder.exceptions.QuickFixNotFoundException;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.forms.finder.SWTFormsBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.AbstractMatcher;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.results.WidgetResult;
import org.eclipse.swtbot.swt.finder.utils.MessageFormat;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.WaitForObjectCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Lorenzo Bettini
 * 
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class EmfParsleyFormTests extends EmfParsleySWTBotAbstractTests {
	
	protected final Logger log = Logger.getLogger(getClass());
	
	public EmfParsleyFormTests() {
		log.setLevel(Level.DEBUG);
	}

	@Test
	public void detailViewShowsDetailsOnSelection() throws Exception {
		boolean editable = true;
		SWTBotView detailView = openTestView(EMF_DETAIL_VIEW);
		// select on the editor's tree
		SWTBotTreeItem rootOfEditorTree = openEditorAndGetTreeRoot(EMF_TREE_EDITOR,
				MY_EXTLIBRARY, MY_EXT_LIBRARY_PLATFORM_URI);
		getLibraryWriterNode(rootOfEditorTree).select();
		SWTFormsBot formbot = formBotFromView(detailView);
		assertFormControlsOfWriterNode(formbot, editable);
		// select on the outline view
		getLibraryNode(getRootOfOutlineViewTree()).select();
		assertFormControlsOfLibraryNode(formbot, editable);
		// select the book
		getLibraryBookNode(rootOfEditorTree).select();
		assertFormControlsOfBookNode(formbot);
		// select the damaged video cassette node
		getLibraryDamagedVideoCassetteNode(rootOfEditorTree).select();
		assertCheckBoxComponent(formbot, 0, true, true);
		// now select again on the editor's tree
		getLibraryWriterNode(rootOfEditorTree).select();
		formbot.label(ADDRESS_LABEL);
		assertTextComponent(formbot, WRITER_S_ADDRESS_TEXT, editable);
		// detailView.close();
	}

	@Test
	public void checkFormDatabinding() throws Exception {
		SWTBotView detailView = openTestView(EMF_DETAIL_VIEW);
		// select on the editor's tree
		getLibraryNode(openEditorAndGetTreeRoot(EMF_TREE_EDITOR,
				MY_EXTLIBRARY, MY_EXT_LIBRARY_PLATFORM_URI)).select();
		modifyFormText(formBotFromView(detailView), LIBRARY_NAME);
		assertEditorDirty(EMF_TREE_EDITOR);
		getEditor(EMF_TREE_EDITOR).close();
	}

	@Test
	public void detailReadOnlyViewShowsDetailsOnSelection() throws Exception {
		boolean editable = false;
		SWTBotView detailView = openTestView(EMF_DETAIL_READONLY_VIEW);
		// select on the editor's tree
		SWTBotTreeItem rootOfEditorTree = openEditorAndGetTreeRoot(EMF_TREE_EDITOR,
				MY_EXTLIBRARY, MY_EXT_LIBRARY_PLATFORM_URI);
		getLibraryWriterNode(rootOfEditorTree).select();
		SWTFormsBot formbot = formBotFromView(detailView);
		formbot.label(ADDRESS_LABEL);
		assertTextComponent(formbot, WRITER_S_ADDRESS_TEXT, editable);
		formbot.label(FIRSTNAME_LABEL);
		
		// Button "..." must not be present in readonly mode
		assertNoButton(formbot, detailView);
		
		// select on the outline view
		getLibraryNode(getRootOfOutlineViewTree()).select();
		formbot.label(ADDRESS_LABEL);
		assertTextComponent(formbot, LIBRARY_S_ADDRESS_TEXT, editable);
		// the label for 'people'
		formbot.label(PEOPLE_LABEL);
		// the inner label listing all the people, before the button "..."
		formbot.label(PEOPLE_TEXT);
		// select the book
		getLibraryBookNode(rootOfEditorTree).select();
		formbot.label(AUTHOR_LABEL);
		// in readonly mode it is a Text component
		assertTextComponent(formbot, WRITER_LABEL, editable);
		// select the damaged video cassette node
		getLibraryDamagedVideoCassetteNode(rootOfEditorTree).select();
		// in readonly mode the checkbox must not be enabled
		assertCheckBoxComponent(formbot, 0, true, false);
		// now select again on the editor's tree
		getLibraryWriterNode(rootOfEditorTree).select();
		formbot.label(ADDRESS_LABEL);
		assertTextComponent(formbot, WRITER_S_ADDRESS_TEXT, editable);
		// detailView.close();
	}

	@SuppressWarnings({ "unchecked" })
	protected void assertNoButton(SWTFormsBot formbot, SWTBotView view) {
		Matcher<Control> allOf = allOf(widgetOfType(Control.class));
		List<? extends Control> controls = formbot.widgets(allOf,
				view.getWidget());
		for (Control c : controls) {
			Assert.assertFalse("Expected no Button!", c instanceof Button);
		}
	}

	@Test
	public void treeFormViewShowsOnSelection() throws Exception {
		SWTBotView view = openTestView(EMF_TREE_FORM_DETAIL_VIEW);
		// select on the editor's tree
		SWTBotTreeItem rootOfEditorTree = openEditorAndGetTreeRoot(EMF_TREE_EDITOR,
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
		SWTBotTreeItem rootOfEditorTree = openEditorAndGetTreeRoot(EMF_TREE_EDITOR,
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
		bot.waitUntil(shellCloses(shell), SWTBotPreferences.TIMEOUT);
		
		// check that the selected borrower has been added
		formbot.label("Borrower: Foo");
		
		getEditor(EMF_TREE_EDITOR).close();
	}

	// @Test This test fails too often probably due to some missed
	// synchronization... however, the interesting one is
	// testContentAssistInFormAndSelect
	// so I'll comment this out (Lorenzo)
	public void testContentAssistInFormContainsProposals() throws Exception {
		SWTFormsBot formbot = setupFormForContentAssistTest();
		SWTBotText text = formbot.textWithLabel(STRING_FEATURE_LABEL);
		List<String> proposals = getContentAssistProposals(text);
		assertEquals("[First Proposal, Second Proposal]", proposals.toString());
		getEditor(EMF_TREE_EDITOR).close();
	}

	@Test
	public void testContentAssistInFormAndSelect() throws Exception {
		if (!isIndigo()) {
			SWTFormsBot formbot = setupFormForContentAssistTest();
			SWTBotText text = formbot.textWithLabel(STRING_FEATURE_LABEL);
			// select the content assist proposal
			selectContentAssistProposal(text, "Second Proposal");
			// and check that the text has changed
			formbot.text("Second Proposal");
			getEditor(EMF_TREE_EDITOR).saveAndClose();
		}
	}

	private SWTFormsBot setupFormForContentAssistTest() throws CoreException,
			InvocationTargetException, InterruptedException, IOException {
		SWTBotView detailView = openTestView(CUSTOM_PROPOSALS_FORM_VIEW);
		// select on the editor's tree
		SWTBotTreeItem rootOfEditorTree = openEditorAndGetTreeRoot(EMF_TREE_EDITOR,
				TEST_CONTAINER, TEST_CONTAINER_PLATFORM_URI);
		getClassForControlsNode(rootOfEditorTree).select();
		SWTFormsBot formbot = formBotFromView(detailView);
		return formbot;
	}

	public List<String> getContentAssistProposals(SWTBotText text) {
		WaitForObjectCondition<SWTBotTable> autoCompleteAppears = autoCompleteAppears(text, tableWithRows());
		waitUntil(autoCompleteAppears);
		final SWTBotTable autoCompleteTable = autoCompleteAppears.get(0);
		List<String> proposals = getRows(autoCompleteTable);
		makeProposalsDisappear(text);
		return proposals;
	}

	public void selectContentAssistProposal(SWTBotText text, String proposalText) {
		WaitForObjectCondition<SWTBotTable> autoCompleteTable = autoCompleteAppears(text, tableWithRows());
		waitUntil(autoCompleteTable);
		selectProposal(autoCompleteTable.get(0), proposalText);
	}

	private WaitForObjectCondition<SWTBotTable> autoCompleteAppears(final SWTBotText text, Matcher<SWTBotTable> tableMatcher) {
		return new WaitForObjectCondition<SWTBotTable>(tableMatcher) {
			@Override
			protected List<SWTBotTable> findMatches() {
				try {
					text.pressShortcut(SWT.CTRL, 0, ' ');
					SWTBotTable autoCompleteTable = getProposalTable(text);
					if (matcher.matches(autoCompleteTable)) {
						log.debug("matched table, returning");
						return Arrays.asList(autoCompleteTable);
					}
				} catch (Throwable e) {
					makeProposalsDisappear(text);
				}
				return null;
			}
			
			@Override
			public String getFailureMessage() {
				return "Could not find auto complete proposal using matcher " + matcher;
			}
			
		};
	}

	private SWTBotTable getProposalTable(SWTBotText text) {
		log.debug("Finding table containing proposals.");
		try {
			Table table = bot.widget(widgetOfType(Table.class), activatePopupShell(text).widget);
			SWTBotTable swtBotTable = new SWTBotTable(table);
			log.debug(MessageFormat.format("Found table containing proposals -- {0}", getRows(swtBotTable)));
			return swtBotTable;
		} catch (Exception e) {
			throw new QuickFixNotFoundException("Quickfix options not found. Giving up.", e); //$NON-NLS-1$
		}
	}

	private Matcher<SWTBotTable> tableWithRows() {
		return new AbstractMatcher<SWTBotTable>() {

			@Override
			protected boolean doMatch(Object item) {
				List<String> rows = getRows((SWTBotTable) item);
				return !rows.isEmpty();
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("table with rows");
			}
		};
	}

	private SWTBotShell activatePopupShell(final SWTBotText text) {
		log.debug("Activating quickfix shell."); //$NON-NLS-1$
		try {
			Shell mainWindow = syncExec(new WidgetResult<Shell>() {
				@Override
				public Shell run() {
					return text.widget.getShell();
				}
			});

			final List<Shell> shells = bot.shells("", mainWindow);
			log.debug(MessageFormat.format("Obtained shells: {0}", 
					syncExec(new Result<String>() {
						@Override
						public String run() {
							return shells.toString();
						}
					}))); //$NON-NLS-1$
			Shell widgetShell = syncExec(new WidgetResult<Shell>() {
				@Override
				public Shell run() {
					for(int j=0; j<shells.size(); j++) {
						Shell s = shells.get(j);
						Control[] children = s.getChildren();
						for (int i = 0; i < children.length; i++) {
							//Select shell which has content assist table
							log.debug(MessageFormat.format("Examining shell: {0}", 
									children[i].getClass().getName()));
							if(children[i] instanceof Composite) {
								log.debug("Found Composite");
								return s;
							}
						}
					}
					return shells.get(0);
				}
			});
			SWTBotShell shell = new SWTBotShell(widgetShell);
			shell.activate();
			log.debug("Activated quickfix shell."); //$NON-NLS-1$
			return shell;
		} catch (Exception e) {
			throw new QuickFixNotFoundException("Quickfix popup not found. Giving up.", e); //$NON-NLS-1$
		}
	}

	private List<String> getRows(SWTBotTable table) {
		int rowCount = table.rowCount();
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < rowCount; i++)
			result.add(table.cell(i, 0));
		return result;
	}

	private void selectProposal(SWTBotTable proposalTable, String proposalText) {
		log.debug(MessageFormat.format("Trying to select proposal {0}", proposalText)); //$NON-NLS-1$
		if (proposalTable.containsItem(proposalText)) {
			selectProposal(proposalTable, proposalTable.indexOf(proposalText));
			return;
		}
		throw new QuickFixNotFoundException("Quickfix options not found. Giving up."); //$NON-NLS-1$
	}
	
	private void selectProposal(final SWTBotTable proposalTable, final int proposalIndex) {
		log.debug(MessageFormat.format("Trying to select proposal with index {0}", proposalIndex)); //$NON-NLS-1$
		UIThreadRunnable.syncExec(new VoidResult() {
			@Override
			public void run() {
				Table table = proposalTable.widget;
				log.debug(MessageFormat.format("Selecting row [{0}] {1} in {2}", proposalIndex, table.getItem(proposalIndex).getText(), //$NON-NLS-1$
						table));
				table.setSelection(proposalIndex);
				Event event = new Event();
				event.type = SWT.Selection;
				event.widget = table;
				event.item = table.getItem(proposalIndex);
				table.notifyListeners(SWT.Selection, event);
				table.notifyListeners(SWT.DefaultSelection, event);
			}
		});
	}
	
	private void makeProposalsDisappear(SWTBotText text) {
		// clear away all content assists for next retry.
		log.debug("Making proposals disappear.");
		text.setFocus();
	}

	private void waitUntil(WaitForObjectCondition<SWTBotTable> table) {
		bot.waitUntil(table, SWTBotPreferences.TIMEOUT);
	}
}
