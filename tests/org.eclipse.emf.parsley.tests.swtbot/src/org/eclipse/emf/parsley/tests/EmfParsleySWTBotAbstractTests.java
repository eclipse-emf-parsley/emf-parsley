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

import static org.eclipse.swtbot.swt.finder.waits.Conditions.shellCloses;
import static org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil.cleanWorkspace;
import static org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil.createFile;
import static org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil.root;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.examples.views.EmfParsleyExamplesViewsActivator;
import org.eclipse.emf.parsley.junit4.ui.util.ImageTester;
import org.eclipse.emf.parsley.tests.pde.utils.PDETargetPlatformUtils;
import org.eclipse.emf.parsley.tests.views.TestOnSelectionLibraryTreeViewWithResourceURI;
import org.eclipse.emf.parsley.util.ActionBarsUtils;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.SubStatusLineManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.forms.finder.SWTFormsBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.ContextMenuHelper;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.results.ListResult;
import org.eclipse.swtbot.swt.finder.results.WidgetResult;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCheckBox;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 * @author Lorenzo Bettini
 * 
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class EmfParsleySWTBotAbstractTests {

	protected static final String EMF_PARSLEY_CATEGORY = "Emf Parsley";

	protected static final String WRITER_LABEL = "Writer Lorenzo Bettini";

	protected static final String WRITER_FIRSTNAME = "Lorenzo";

	protected static final String WRITER_NAME = "Lorenzo Bettini";

	protected static final String BORROWER_LABEL = "Borrower Bar";

	protected static final String CUSTOM_BORROWER_LABEL = "Borrower: Foo";

	protected static final String BOOK_LABEL = "Book Lorenzo's Book";

	protected static final String BOOK_LABEL_CUSTOM = "Book: Lorenzo's Book";

	protected static final String BOOK_TITLE = "Lorenzo's Book";

	protected static final String LIBRARY_LABEL = "Library My Library";

	protected static final String LIBRARY_NAME = "My Library";

	protected static final String DAMAGED_VIDEO_LABEL = "Video Cassette My Damaged Video";

	protected static final String OUTLINE_VIEW = "Outline";

	protected static final String PARSLEY_MODEL_LABEL = "Model";

	protected static final String PARSLEY_MODULE_LABEL = "Module MyTestModule";

	protected static final String TEST_CONTAINER_LABEL = "Test Container";

	protected static final String TEST_CONTAINER_SOURCE_LABEL = "Test Container Source Container";

	protected static final String TEST_CONTAINER_TARGET_LABEL = "Test Container Target Container";

	protected static final String TEST_CONTAINER_ELEMENT_TO_MOVE_LABEL = "Class With Name To Move";

	protected static final String CLASS_FOR_CONTROLS_LABEL = "Class For Controls";

	protected static final String STRING_FEATURE_LABEL = "String Feature";

	protected static final String EMF_TREE_EDITOR = "EMF Tree Editor";

	protected static final String EMF_TREE_EDITOR_XTEXT = "EMF Tree Editor Xtext";

	protected static final String EMF_TREE_EDITOR_NO_MOUSE = "EMF Tree Editor No Mouse Events";

	protected static final String EMF_TREE_EDITOR_OPEN_FORM_DIALOG = "EMF Tree Editor Opening Form Dialog";

	protected static final String EMF_TREE_EDITOR_OPEN_DIALOG = "EMF Tree Editor Opening Dialog";

	protected static final String EMF_TREE_EDITOR_OPEN_DIALOG_UNDOABLE = "EMF Tree Editor Dialog Undoable";

	protected static final String EMF_TREE_EDITOR_CUSTOM_LABEL = "EMF Tree Editor Custom Label";

	protected static final String EMF_CUSTOM_LIBRARY_EDITOR = "EMF Custom Library Editor";

	protected static final String EMF_CUSTOM_MENU_LIBRARY_EDITOR = "EMF Custom Menu Library Editor";

	protected static final String MY_EXTLIBRARY = "My.extlibrary";

	protected static final String MY2_EXTLIBRARY = "My2.extlibrary";

	protected static final String MY_PARSLEY = "My.parsley";

	protected static final String MY_TEST_PROJECT = "MyTestProject";

	protected static final String TEST_CONTAINER = "TestContainer.xmi";

	protected static final String TEST_CONTAINER_FOR_DND = "TestContainerForDnD.xmi";

	public static final String MY_EXTLIBRARY_RELATIVE_PATH = MY_TEST_PROJECT
			+ "/" + MY_EXTLIBRARY;

	public static final String MY2_EXTLIBRARY_RELATIVE_PATH = MY_TEST_PROJECT
			+ "/" + MY2_EXTLIBRARY;

	protected static final String MY_PARSLEY_RELATIVE_PATH = MY_TEST_PROJECT
			+ "/" + MY_PARSLEY;

	protected static final String MY_EXT_LIBRARY_PLATFORM_URI = "platform:/resource/"
			+ MY_EXTLIBRARY_RELATIVE_PATH;

	protected static final String MY_PARSLEY_PLATFORM_URI = "platform:/resource/"
			+ MY_PARSLEY_RELATIVE_PATH;

	public static final String TEST_CONTAINER_RELATIVE_PATH = MY_TEST_PROJECT
			+ "/" + TEST_CONTAINER;

	protected static final String TEST_CONTAINER_PLATFORM_URI = "platform:/resource/"
			+ TEST_CONTAINER_RELATIVE_PATH;

	public static final String TEST_CONTAINER_FOR_DND_RELATIVE_PATH = MY_TEST_PROJECT
			+ "/" + TEST_CONTAINER_FOR_DND;

	protected static final String TEST_CONTAINER_FOR_DND_PLATFORM_URI = "platform:/resource/"
			+ TEST_CONTAINER_FOR_DND_RELATIVE_PATH;

	protected static final String HARDCODED_LIBRARY_PLATFORM_URI = TestOnSelectionLibraryTreeViewWithResourceURI.resourceUri;

	protected static final String LIBRARY_ON_SELECTION_TREE_VIEW_WITH_RESOURCE_URI = "TestOnSelectionLibraryTreeViewWithResourceURI";

	protected static final String LIBRARY_ON_SELECTION_TREE_FORM_VIEW = "TestOnSelectionLibraryTreeFormView";

	protected static final String LIBRARY_EMF_VIEW_CUSTOM_LABEL = "TestOnSelectionLibraryTreeViewWithResourceURI Custom Label";

	protected static final String EMF_DETAIL_VIEW = "Emf Form View";

	protected static final String EMF_DETAIL_READONLY_VIEW = "Emf Read Only Form View";

	protected static final String EMF_TREE_FORM_DETAIL_VIEW = "Emf Tree Form View";

	protected static final String LIBRARY_CUSTOM_DETAIL_VIEW = "Library Emf Detail View";

	protected static final String LIBRARY_BOOKS_TABLE_VIEW = "Library Books Table View";

	protected static final String LIBRARY_BOOKS_TABLE_FORM_VIEW = "Library Books Table Form View";

	protected static final String LIBRARY_BOOKS_TABLE_VIEW_CUSTOM_PROVIDER = "Library Books Table View With Custom Content Provider";

	protected static final String LIBRARY_BOOKS_TABLE_FORM_VIEW_CUSTOM_PROVIDER = "Library Books Table Form View With Custom Content Provider";

	protected static final String TEST_SAVEABLE_TREE_FORM_VIEW = "Library Test Saveable Resource Tree Form View";

	protected static final String TEST_SAVEABLE_TABLE_FORM_VIEW = "Library Test Saveable Resource Table Form View";

	protected static final String TEST_SAVEABLE_TABLE_VIEW = "Library Test Saveable Table View";

	protected static final String TEST_SAVEABLE_TREE_VIEW = "Library Test Saveable Tree View";

	protected static final String TEST_SAVEABLE_TREE_WITH_COLUMNS_VIEW = "Library Test Saveable Tree View With Colunms";

	protected static final String TEST_SAVEABLE_TREE_WITH_SPECIFIC_COLUMNS_VIEW = "Library Test Saveable Tree View With Specific Colunms";

	protected static final String TEST_SAVEABLE_VIEW_WITH_CUSTOM_CONTENT_PROVIDER = "Library Tree View With Custom Content Provider";

	protected static final String TEST_SAVEABLE_VIEW_WITH_CUSTOM_ELEMENTS_CONTENT_PROVIDER = "Library Tree View With Custom Elements Content Provider";

	protected static final String CUSTOM_PROPOSALS_FORM_VIEW = "Custom Proposals Form View";

	protected static final String WRITER_S_ADDRESS_TEXT = "writer's address";

	protected static final String ADDRESS_LABEL = "Address";

	protected static final String ADDRESS_COLUMN_HEADER = "address";

	protected static final String BORROWERS_LABEL = "Borrowers";

	protected static final String BORROWERS_COLUMN_HEADER = "borrowers";

	protected static final String AUTHOR_LABEL = "Author";

	protected static final String AUTHOR_COLUMN_HEADER = "author";

	protected static final String FIRSTNAME_LABEL = "First Name";

	protected static final String FIRSTNAME_COLUMN_HEADER = "firstName";

	protected static final String DAMAGED_LABEL = "Damaged";

	protected static final String CUSTOM_FIRSTNAME_LABEL = "First name";

	protected static final String CUSTOM_SURNAME_LABEL = "Last name";

	protected static final String LIBRARY_S_ADDRESS_TEXT = "Library's address";

	protected static final String PEOPLE_LABEL = "people";

	protected static final String PEOPLE_TEXT = WRITER_LABEL + ", "
			+ BORROWER_LABEL;

	protected static final String CUSTOM_PEOPLE_TEXT = WRITER_LABEL + ", "
			+ CUSTOM_BORROWER_LABEL;

	protected static final String ACTION_DELETE = "Delete";

	protected static final String ACTION_VALIDATE = "Validate";

	protected static final String ACTION_COPY = "Copy";

	protected static final String ACTION_CUT = "Cut";

	protected static final String ACTION_PASTE = "Paste";

	// they have mnemonic so use a space after the string
	protected static final String ACTION_REDO = "Redo ";

	protected static final String ACTION_UNDO = "Undo ";

	protected static final String BOOK_ON_TAPE = "Book On Tape";

	protected static final String NEW_CHILD = "New Child";

	protected static final String CUSTOM_NEW_BOOK = "New book";

	protected static final String CUSTOM_NEW_BOOK_TITLE = "Book A new book";

	protected static final String NEW_SIBLING = "New Sibling";

	protected static final String EMF_PARSLEY_PROJECT = "EmfParsleyProject";

	protected static final String NEW_EMF_PARSLEY_PROJECT = "Emf Parsley Project";

	protected static final String NEW_EMF_PARSLEY_DSL_PROJECT = "Emf Parsley Dsl Based Project";

	protected static final String EMF_PARSLEY_MAIL_RCP_EXAMPLE = "Emf Parsley Mail RCP Example";

	protected static final String EMF_PARSLEY_FIRST_EXAMPLE = "Emf Parsley First Example";

	protected static final String EMF_PARSLEY_VIEWS_EXAMPLES = "Emf Parsley Views Examples";

	protected static final String EMF_PARSLEY_EDITORS_EXAMPLES = "Emf Parsley Editors Examples";

	protected static final String EMF_PARSLEY_E4_EXAMPLE = "Emf Parsley Eclipse 4 Part Example";

	protected static final String EMF_PARSLEY_CDO_SERVER_EXAMPLE = "Emf Parsley Cdo Server Example";

	protected static final String EMF_PARSLEY_CDO_EXAMPLE = "Emf Parsley Cdo Example";

	protected static final String EMF_PARSLEY_RAP_TP_EXAMPLE = "Emf Parsley Rap Target Platform Example";

	protected static final String EMF_PARSLEY_RAP_CDO_EXAMPLE = "Emf Parsley Rap Cdo Example";

	protected static final String EMF_PARSLEY_RAP_EXAMPLE = "Emf Parsley Rap Example";

	protected static final String TEST_ON_SELECTION_TREE_VIEW = "Test On Selection Tree View";

	protected static final String TEST_MODEL_TREE_FORM_VIEW = "Test Model Tree Form View";

	protected static final String TEST_MODEL_TREE_VIEW = "Test Model Tree View";

	protected static final String TEST_MODEL_EDITABLE_TABLE_VIEW = "Test Model Editable Table View";

	protected static final String NEW_OBJECT_FOR_VALIDATION = "Objects For Validation Class For Validation";

	protected static final String OBJECT_FOR_VALIDATION = "Class For Validation";

	protected static final String NOT_EMPTY_LABEL = "Not Empty";

	protected static SWTWorkbenchBot bot;

	protected static Map<String, String> editorNamesToId;

	public EmfParsleySWTBotAbstractTests() {
		// the following are useless... but it's just to have coverage
		// for the protected constructor of EmfParsleyConstants
		// and the protected constructor is "required" by sonar...
		new org.eclipse.emf.parsley.util.EmfParsleyUiUtil() {
			
		};
		new ActionBarsUtils() {
			
		};
		new org.eclipse.emf.parsley.util.EmfCommandsUtil() {
			
		};
	}

	@BeforeClass
	public static void beforeClass() throws Exception {
		PDETargetPlatformUtils.setTargetPlatform();
		
		// force loading of examples.views
		EmfParsleyExamplesViewsActivator.getDefault();
		
		bot = new SWTWorkbenchBot();

		// increase timeout to 10 seconds
		// SWTBotPreferences.TIMEOUT = 10000;

		// in the launch configuration there will be no welcome view
		// try {
		// bot.viewByTitle("Welcome").close();
		// } catch (WidgetNotFoundException e) {
		// // OK!
		// }
		
		SWTBotPreferences.KEYBOARD_LAYOUT = "EN_US";
		
		closeWelcomePage();

		editorNamesToId = new HashMap<String, String>();
		editorNamesToId.put(EMF_TREE_EDITOR,
				EmfParsleySwtBotTestsActivator.EMF_TREE_EDITOR);
		editorNamesToId.put(EMF_TREE_EDITOR_XTEXT,
				EmfParsleySwtBotTestsActivator.EMF_TREE_EDITOR_FOR_XTEXT);
		editorNamesToId.put(EMF_TREE_EDITOR_NO_MOUSE,
				EmfParsleySwtBotTestsActivator.EMF_TREE_EDITOR_NO_MOUSE_ID);
		editorNamesToId.put(EMF_TREE_EDITOR_OPEN_FORM_DIALOG,
				EmfParsleySwtBotTestsActivator.EMF_TREE_EDITOR_OPEN_FORM_DIALOG_ID);
		editorNamesToId.put(EMF_TREE_EDITOR_OPEN_DIALOG,
				EmfParsleySwtBotTestsActivator.EMF_TREE_EDITOR_OPEN_DIALOG_ID);
		editorNamesToId.put(EMF_TREE_EDITOR_OPEN_DIALOG_UNDOABLE,
				EmfParsleySwtBotTestsActivator.EMF_TREE_EDITOR_OPEN_DIALOG_UNDOABLE_ID);
		editorNamesToId.put(EMF_TREE_EDITOR_CUSTOM_LABEL,
				EmfParsleySwtBotTestsActivator.EMF_TREE_EDITOR_CUSTOM_LABEL);
		editorNamesToId.put(EMF_CUSTOM_LIBRARY_EDITOR,
				EmfParsleySwtBotTestsActivator.EMF_EDITOR_FOR_LIBRARY);
		editorNamesToId.put(EMF_CUSTOM_MENU_LIBRARY_EDITOR,
				EmfParsleySwtBotTestsActivator.EMF_EDITOR_FOR_MENU_LIBRARY);
		
		// Change the perspective via the Open Perspective dialog
		bot.menu("Window").menu("Open Perspective").menu("Other...").click();
		SWTBotShell openPerspectiveShell = bot.shell("Open Perspective");
		openPerspectiveShell.activate();

		// select the dialog
		bot.table().select("Plug-in Development");
		bot.button("OK").click();
		
		// in SwtBot 2.2.0 we must use part name since the title
		// of the problems view also contains the items count
		// see also http://www.eclipse.org/forums/index.php/t/640194/
		
		// Error Log view is disturbing since it often shows up
		// and gets the focus, breaking many of our tests, so it's crucial
		// to close it right away.
		// Unfortunately, before Luna, the Error Log view was enabled by
		// default in Plug-in Development perspective, but in Luna it is
		// there anymore.
		if (!isLuna()) {
			bot.viewByPartName("Error Log").close();
		}
		bot.viewByPartName("Problems").show();

		bot.viewByTitle(OUTLINE_VIEW).show();
	}

	@AfterClass
	public static void clean() throws CoreException {
		bot.resetWorkbench();
	}

	@After
	public void runAfterEveryTest() throws CoreException {
		// bot.sleep(2000);
		bot.saveAllEditors();
		cleanWorkspace();
		IResourcesSetupUtil.waitForBuild();
	}

	protected static void closeWelcomePage() throws InterruptedException {
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				if (PlatformUI.getWorkbench().getIntroManager().getIntro() != null) {
					PlatformUI
							.getWorkbench()
							.getIntroManager()
							.closeIntro(
									PlatformUI.getWorkbench().getIntroManager()
											.getIntro());
				}
			}
		});
	}

	protected static boolean isLuna() {
		String version = Platform.getBundle(PlatformUI.PLUGIN_ID).getHeaders()
				.get("Bundle-Version");

		Pattern versionPattern = Pattern.compile("\\d+\\.(\\d+)\\..*");
		Matcher m = versionPattern.matcher(version);
		if (m.matches()) {
			// org.eclipse.ui has minor number 106 for Luna
			int minorVersion = Integer.parseInt(m.group(1));
			if (minorVersion >= 106) {
				return true;
			}
		}

		return false;
	}

	protected static boolean isIndigo() {
		String version = Platform.getBundle(PlatformUI.PLUGIN_ID).getHeaders()
				.get("Bundle-Version");

		Pattern versionPattern = Pattern.compile("\\d+\\.(\\d+)\\..*");
		Matcher m = versionPattern.matcher(version);
		if (m.matches()) {
			// org.eclipse.ui has minor number 7 for Indigo, and surely less than 100
			int minorVersion = Integer.parseInt(m.group(1));
			if (minorVersion < 100) {
				return true;
			}
		}

		return false;
	}

	protected void assertPropertyViewIsOpenedAndCloseIt() {
		SWTBotView propertyView = bot.viewByTitle("Properties");
		// bot.sleep(2000);
		propertyView.close();
	}

	protected SWTBotTreeItem getLibraryWriterNode(SWTBotTreeItem editorTreeRoot) {
		return getWriterNode(getLibraryNode(editorTreeRoot));
	}

	protected SWTBotTreeItem getLibraryBookNode(SWTBotTreeItem editorTreeRoot) {
		return getBookNode(getLibraryNode(editorTreeRoot));
	}

	protected SWTBotTreeItem getBookNode(SWTBotTreeItem treeItem) {
		return treeItem.expand().getNode(BOOK_LABEL);
	}

	protected SWTBotTreeItem getLibraryDamagedVideoCassetteNode(SWTBotTreeItem editorTreeRoot) {
		return getDamagedVideoCassetteNode(getLibraryNode(editorTreeRoot));
	}
	
	protected SWTBotTreeItem accessTreeWithCustomLabels(SWTBotTreeItem rootOfTree) {
		return rootOfTree.expand().getNode("TEST " + LIBRARY_LABEL + " ENDTEST")
				.expand().getNode("TEST " + WRITER_LABEL + " ENDTEST");
	}

	protected SWTBotTreeItem accessTreeWithCustomLibraryLabels(
			SWTBotTreeItem rootOfTree) {
		return rootOfTree.expand().getNode(LIBRARY_LABEL).expand()
				.getNode("Book: Lorenzo's Book");
	}

	protected SWTBotTreeItem getLibraryNode(final SWTBotTreeItem treeItem) {
		return treeItem.expand().getNode(LIBRARY_LABEL);
	}

	protected SWTBotTreeItem getLibraryModifiedNode(final SWTBotTreeItem treeItem) {
		return treeItem.expand().getNode(LIBRARY_LABEL + " MODIFIED");
	}

	protected SWTBotTreeItem getWriterNode(final SWTBotTreeItem treeItem) {
		return treeItem.expand().getNode(WRITER_LABEL);
	}

	protected SWTBotTreeItem getDamagedVideoCassetteNode(final SWTBotTreeItem treeItem) {
		return treeItem.expand().getNode(DAMAGED_VIDEO_LABEL);
	}

	protected SWTBotTreeItem getParsleyModelNode(final SWTBotTreeItem treeItem) {
		return treeItem.expand().getNode(PARSLEY_MODEL_LABEL);
	}

	protected SWTBotTreeItem getParsleyModuleNode(final SWTBotTreeItem treeItem) {
		return treeItem.expand().getNode(PARSLEY_MODULE_LABEL);
	}

	protected SWTBotTreeItem getClassForControlsNode(SWTBotTreeItem editorTreeRoot) {
		return getTestContainerNode(editorTreeRoot).expand().
				getNode(CLASS_FOR_CONTROLS_LABEL);
	}

	protected SWTBotTreeItem getTestContainerNode(final SWTBotTreeItem treeItem) {
		return treeItem.expand().getNode(TEST_CONTAINER_LABEL);
	}

	protected void assertPropertyViewIsNotShown() {
		try {
			SWTBotView propertyView = bot.viewByTitle("Properties");
			propertyView.close();
			fail("Properties view should not show");
		} catch (WidgetNotFoundException e) {
			// OK!
		}
	}

	protected SWTBotTreeItem getRootOfOutlineViewTree() {
		SWTBotTree outlineTree = getRootOfTreeFromView(OUTLINE_VIEW);
		return outlineTree.getTreeItem(MY_EXT_LIBRARY_PLATFORM_URI);
	}

	protected SWTBotTree getRootOfTreeFromView(String view) {
		SWTBotView outlineView = bot.viewByTitle(view);
		SWTBotTree outlineTree = outlineView.bot().tree();
		return outlineTree;
	}

	protected SWTBotTreeItem openEditorAndGetTreeRoot(
			String emfEditorContextMenuString, String fileName,
			String treeRootLabel) throws CoreException,
			InvocationTargetException, InterruptedException, IOException {
		SWTBotTree tree = openEditorAndGetTree(emfEditorContextMenuString, fileName);
		SWTBotTreeItem treeItemRoot = tree.getTreeItem(treeRootLabel);
		return treeItemRoot;
	}

	protected SWTBotTree openEditorAndGetTree(String emfEditorContextMenuString,
			String fileName) throws CoreException, InvocationTargetException,
			InterruptedException, IOException {
		SWTBotEditor editor = openEmfEditorOnTestFile(
				emfEditorContextMenuString, fileName);
		SWTBotTree tree = editor.bot().tree();
		return tree;
	}

	protected SWTBotTreeItem getRootOfTreeEditor(String emfEditorContextMenuString,
			String treeRootLabel) 
					throws CoreException, InvocationTargetException,
						InterruptedException, IOException {
		SWTBotTree tree = getEditorTree(emfEditorContextMenuString);
		return tree.getTreeItem(treeRootLabel);
	}

	protected SWTBotTree getEditorTree(String emfEditorContextMenuString) 
			throws CoreException, InvocationTargetException,
				InterruptedException, IOException {
		SWTBotEditor editor = getEditor(emfEditorContextMenuString);
		SWTBotTree tree = editor.bot().tree();
		return tree;
	}


	protected SWTBotEditor openEmfEditorOnTestFile(
			String emfEditorContextMenuString, String fileName)
			throws CoreException, InvocationTargetException,
			InterruptedException, IOException {
		createProjectAndTestFiles();

		clickOnContextMenu(getFileItemFromTestProject(fileName), "Open With",
				emfEditorContextMenuString);
		// ContextMenuHelper.clickContextMenu(projectTree, "Open With",
		// emfEditorContextMenuString);

		// SWTBotMenu contextMenu = getFileItemFromTestProject(fileName)
		// .contextMenu("Open With");
		// getSubMenuItem(contextMenu, emfEditorContextMenuString).click();

		SWTBotEditor editor = getEditor(emfEditorContextMenuString);
		return editor;
	}

	protected void clickOnContextMenu(SWTBotTreeItem treeItem,
			final String... texts) {
		new SWTBotMenu(contextMenu(treeItem, texts)).click();
	}

	protected void clickOnContextMenu(SWTBotTable table,
			final String... texts) {
		new SWTBotMenu(contextMenu(table, texts)).click();
	}

	protected MenuItem contextMenu(final SWTBotTreeItem treeItem,
			final String... texts) {
		treeItem.select();
		return contextMenu(getSWTBotTree(treeItem), texts);
	}

	protected SWTBotMenu contextMenu(final SWTBotEditor editor,
			final String text) {
		return new SWTBotMenu(
				ContextMenuHelper.contextMenu(editor.toTextEditor().getStyledText(), text));
	}

	protected SWTBotTree getSWTBotTree(final SWTBotTreeItem treeItem) {
		return new SWTBotTree(
				UIThreadRunnable.syncExec(new WidgetResult<Tree>() {
					@Override
					public Tree run() {
						return treeItem.widget.getParent();
					}
				}));
	}

	protected MenuItem contextMenu(SWTBotTree tree, final String... texts) {
		return ContextMenuHelper.contextMenu(tree, texts);
	}

	protected MenuItem contextMenu(SWTBotTable table, final String... texts) {
		return ContextMenuHelper.contextMenu(table, texts);
	}

	protected SWTBotEditor getEditor(String emfEditorContextMenuString) {
		return bot.editorById(editorNamesToId.get(emfEditorContextMenuString));
	}

	protected SWTBotTreeItem getFileItemFromTestProject(String fileName) {
		return getProjectTreeItem(MY_TEST_PROJECT).expand().getNode(fileName);
	}

	protected void createProjectAndTestFile() throws CoreException,
			InvocationTargetException, InterruptedException, IOException {
		createMyTestProject();
		IFile file = createFile(MY_EXTLIBRARY_RELATIVE_PATH,
				localFileContents(MY_EXTLIBRARY));
		assertTrue(file.exists());
	}

	protected void createProjectAndTestFiles() throws CoreException,
			InvocationTargetException, InterruptedException, IOException {
		createProjectAndTestFile();
		assertTrue(createFile(MY_PARSLEY_RELATIVE_PATH,
				localFileContents(MY_PARSLEY)).exists());
		assertTrue(createFile(TEST_CONTAINER_RELATIVE_PATH,
				localFileContents(TEST_CONTAINER)).exists());
		assertTrue(createFile(TEST_CONTAINER_FOR_DND_RELATIVE_PATH,
				localFileContents(TEST_CONTAINER_FOR_DND)).exists());
	}

	protected String localFileContents(String string) throws IOException {
		return EmfParsleySwtBotTestsActivator.localFileContents(string);
	}
	
	protected Library localLibrary(String extlibraryFileName) throws IOException {
		Resource resource = localResource(extlibraryFileName);
		return (Library) resource.getContents().get(0);
	}

	protected Resource localResource(String extlibraryFileName)
			throws IOException {
		File file = EmfParsleySwtBotTestsActivator.localFile(extlibraryFileName);
		URI uri = URI.createFileURI(file.getAbsolutePath());
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.getResource(uri, true);
		return resource;
	}

	protected void createMyTestProject() {
		createProjectWithoutTemplateInWorkspace("General", "Project", MY_TEST_PROJECT);
	}

	protected void createProjectInWorkspace(String category,
			String projectType, String projectName) {
		SWTBotShell shell = createNewProjectWizard(category, projectType,
				projectName);
		
		bot.button("Finish").click();
		assertProjectIsCreated(projectName, shell);
	}
	
	protected void createProjectWithoutTemplateInWorkspace(String category,
			String projectType, String projectName) {
		SWTBotShell shell = createNewProjectWizard(category, projectType,
				projectName);
		//deselect template check
		bot.checkBox(1).deselect();
		
		bot.button("Finish").click();
		assertProjectIsCreated(projectName, shell);
	}
	
	protected void createProjectWithTemplateInWorkspace(String category,
			String projectType, String projectName, String template) {
		SWTBotShell shell = createNewProjectWizard(category, projectType,
				projectName);
		
		bot.button("Next >").click();
		
		bot.table().select(template);
		
		bot.button("Finish").click();
		
		assertProjectIsCreated(projectName, shell);
	}

	protected void createExampleProjectsInWorkspace(String exampleDescription,
			String... expectedProjects) {
		bot.menu("File").menu("New").menu("Project...").click();

		SWTBotShell shell = bot.shell("New Project");
		shell.activate();
		expandNodeSync(bot.tree(), EMF_PARSLEY_CATEGORY, "Examples")
				.select(exampleDescription);
		bot.button("Next >").click();


		bot.button("Finish").click();
		
		waitForShellToClose(shell);
		
		for (String projectName : expectedProjects) {
			assertProjectIsCreated(projectName, shell);
		}
	}

	protected void createProjectInWorkspaceWithView(String category,
			String projectType, String projectName, String viewToSelect) {
		SWTBotShell shell = createNewProjectWizard(category, projectType,
				projectName);

		// advance to the second page
		bot.button("Next >").click();
		if (viewToSelect != null) {
			bot.radio(viewToSelect).click();
		}

		bot.button("Finish").click();
		assertProjectIsCreated(projectName, shell);
	}

	protected SWTBotShell createNewProjectWizard(String category,
			String projectType, String projectName) {
		bot.menu("File").menu("New").menu("Project...").click();

		SWTBotShell shell = bot.shell("New Project");
		shell.activate();
		expandNodeSync(bot.tree(), category).select(projectType);
		bot.button("Next >").click();

		bot.textWithLabel("Project name:").setText(projectName);
		return shell;
	}

	protected SWTBotShell createNewProjectWizard(String category,
			String subCategory, String projectType, String projectName) {
		bot.menu("File").menu("New").menu("Project...").click();

		SWTBotShell shell = bot.shell("New Project");
		shell.activate();
		expandNodeSync(bot.tree(), category, subCategory).select(projectType);
		bot.button("Next >").click();

		bot.textWithLabel("Project name:").setText(projectName);
		return shell;
	}

	protected void assertProjectIsCreated(String projectName, SWTBotShell shell) {
		waitForShellToClose(shell);
		assertProjectIsCreated(projectName);
	}

	protected void assertProjectIsCreated(String projectName) {
		assertTrue("Project doesn't exist", isProjectCreated(projectName));
	}

	protected void waitForBuild() throws CoreException {
		IResourcesSetupUtil.reallyWaitForAutoBuild();
		
		// ensure that all queued workspace operations and locks are released
		try {
			ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {
				@Override
				public void run(IProgressMonitor monitor) throws CoreException {
					// nothing to do!
				}
			}, new NullProgressMonitor());
		} catch (CoreException e) {
			e.printStackTrace();
		}

		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				try {
					IResourcesSetupUtil.cleanBuild();
					IResourcesSetupUtil.fullBuild();
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected static SWTBotView getPackageExplorer() {
		SWTBotView view = bot.viewByTitle("Package Explorer");
		return view;
	}

	protected boolean isProjectCreated(String name) {
		try {
			getProjectTreeItem(name);
			return true;
		} catch (WidgetNotFoundException e) {
			return false;
		}
	}

	protected static SWTBotTree getProjectTree() {
		SWTBotView packageExplorer = getPackageExplorer();
		SWTBotTree tree = packageExplorer.bot().tree();
		return tree;
	}

	protected SWTBotTreeItem getProjectTreeItem(String myTestProject) {
		return getProjectTree().getTreeItem(myTestProject);
	}

	protected void accessXtextFileNodes(SWTBotTreeItem root) {
		getParsleyModuleNode(getParsleyModelNode(root));
	}

	protected void assertTreeItemImage(SWTBotTreeItem item,
			final ImageDescriptor expectedImage) {
		final TreeItem widget = item.widget;
		// part of test that requires UI-thread
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				try {
					Image image = widget.getImage();
					// System.out.println(image);
					ImageTester.assertImageDataIs(expectedImage.getImageData(),
							image.getImageData());
				} catch (Exception ex) {
					ex.printStackTrace();
					fail("due to exception: " + ex.getMessage());
				}
			}
		});
	}

	protected void assertStatusLine(final String expectedStatusLineText) {
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				IWorkbenchPart activePart = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage()
						.getActivePart();
				IActionBars actionBars = ActionBarsUtils
						.getActionBars(activePart);

				if (actionBars == null) {
					fail("cannot get action bars from: " + activePart);
				} else {
					assertStatusLine(expectedStatusLineText, actionBars);
				}
			}

			protected void assertStatusLine(
					final String expectedStatusLineText, IActionBars actionBars) {
				IStatusLineManager statusLineManager = actionBars
						.getStatusLineManager();

				// access the current text of the status line manager as suggested here:
				// http://stackoverflow.com/questions/5173838/reading-eclipse-status-line
				SubStatusLineManager subStatusLineManager = (SubStatusLineManager) statusLineManager;
				Control control = ((StatusLineManager) subStatusLineManager
						.getParent()).getControl();
				Control[] children = ((Composite) control).getChildren();

				for (Control child : children) {
					if (child instanceof CLabel) {
						assertEquals(expectedStatusLineText,
								((CLabel) child).getText());
						return;
					}
				}

				// if we're here we failed
				fail("could not find the text of the status line");
			}
		});
	}

	protected ImageDescriptor getImageDescriptorFromLibraryEdit(
			String imageFileName) {
		return getImageDescriptorFromPlugin(
				"org.eclipse.emf.parsley.examples.library.edit",
				"icons/full/obj16/" + imageFileName);
	}

	protected ImageDescriptor getImageDescriptorFromTest(String imageFileName) {
		return getImageDescriptorFromPlugin(
				"org.eclipse.emf.parsley.tests.swtbot", "icons/"
						+ imageFileName);
	}

	protected ImageDescriptor getImageDescriptorFromPlugin(String pluginId,
			String imageFileName) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(pluginId,
				imageFileName);
	}

	protected SWTBotView openTestView(String viewName) {
		bot.menu("Window").menu("Show View").menu("Other...").click();
		SWTBotShell shell = bot.shell("Show View");
		shell.activate();
		expandNodeSync(bot.tree(), EMF_PARSLEY_CATEGORY).select(viewName);
		bot.button("OK").click();
		waitForShellToClose(shell);
		return getView(viewName);
	}

	protected void waitForShellToClose(SWTBotShell shell) {
		bot.waitUntil(shellCloses(shell), SWTBotPreferences.TIMEOUT);
	}

	protected void undo(final String undoText) {
		bot.menu("Edit").menu("Undo " + undoText).click();
	}

	protected void redo(final String undoText) {
		bot.menu("Edit").menu("Redo " + undoText).click();
	}

	protected SWTBotText undoShortcut(SWTBotText text) {
		text.pressShortcut(SWT.CTRL, 'z');
		return text;
	}

	protected SWTBotText redoShortcut(SWTBotText text) {
		text.pressShortcut(SWT.CTRL | SWT.SHIFT, 'z');
		return text;
	}

	protected SWTBotView getView(String title) {
		return bot.viewByTitle(title);
	}

	protected void closeView(String title) {
		getView(title).close();
	}

	protected void getTableHeader(int tableIndex, String tableHeader) {
		SWTBotTable table = bot.table(tableIndex);
		table.header(tableHeader);
	}

	protected void getTableHeader(String tableHeader) {
		SWTBotTable table = bot.table();
		table.header(tableHeader);
	}

	protected void canAccessStandardEditingActions(SWTBotTreeItem libraryNode) {
		contextMenu(libraryNode, ACTION_UNDO);
		contextMenu(libraryNode, ACTION_REDO);
		contextMenu(libraryNode, ACTION_VALIDATE);
		contextMenu(libraryNode, ACTION_COPY);
		contextMenu(libraryNode, ACTION_CUT);
		contextMenu(libraryNode, ACTION_PASTE);
		contextMenu(libraryNode, ACTION_DELETE);
	}

	protected void assertNoErrorsInProjectAfterAutoBuild() throws CoreException {
		waitForBuild();
		assertNoErrorsInProject();
	}

	protected void assertNoErrorsInProject() throws CoreException {
		IMarker[] markers = root().findMarkers(IMarker.PROBLEM, true,
				IResource.DEPTH_INFINITE);
		List<IMarker> errorMarkers = new LinkedList<IMarker>();
		for (int i = 0; i < markers.length; i++) {
			IMarker iMarker = markers[i];
			if (iMarker.getAttribute(IMarker.SEVERITY).toString()
					.equals("" + IMarker.SEVERITY_ERROR)) {
				errorMarkers.add(iMarker);
			}
		}
		assertEquals(
				"expected no error markers: " + printMarkers(errorMarkers), 0,
				errorMarkers.size());
	}
	
	protected void setEditorContentsSaveAndWaitForAutoBuild(
			SWTBotEditor editor, CharSequence contents) throws CoreException {
		setEditorContentsSaveAndWaitForAutoBuild(editor, contents, true);
	}

	protected void setEditorContentsSaveAndWaitForAutoBuild(
			SWTBotEditor editor, CharSequence contents, boolean expectNoErrors) throws CoreException {
		editor.toTextEditor().setText(contents.toString());
		editor.save();
		if (expectNoErrors)
			assertNoErrorsInProjectAfterAutoBuild();
		else
			waitForBuild();
	}

	protected void assertTextComponent(SWTFormsBot formbot, String text, final boolean editable) {
		final SWTBotText t = formbot.text(text);
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				Assert.assertEquals(editable, t.widget.getEditable());
			}
		});
	}

	protected void assertTextComponent(String text, final boolean editable) {
		final SWTBotText t = bot.text(text);
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				Assert.assertEquals(editable, t.widget.getEditable());
			}
		});
	}

	protected void assertCheckBoxComponent(SWTFormsBot formbot, int index,
			final boolean isChecked, final boolean isEnabled) {
		final SWTBotCheckBox b = formbot.checkBox(index);
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				Assert.assertEquals(isEnabled, b.isEnabled());
				Assert.assertEquals(isChecked, b.isChecked());
			}
		});
	}

	private String printMarkers(List<IMarker> errorMarkers) {
		StringBuffer buffer = new StringBuffer();
		for (IMarker iMarker : errorMarkers) {
			try {
				buffer.append(iMarker.getAttribute(IMarker.MESSAGE) + "\n");
				buffer.append(iMarker.getAttribute(IMarker.SEVERITY) + "\n");
			} catch (CoreException e) {
			}
		}
		return buffer.toString();
	}

	protected void assertEditorText(SWTBotEditor editor, CharSequence expected) {
		Assert.assertEquals(expected.toString().replace("\r", ""), editor
				.toTextEditor().getText().replace("\r", ""));
	}

	protected void assertFormControlsOfBookNode(SWTFormsBot formbot) {
		formbot.label(AUTHOR_LABEL);
		formbot.comboBox(WRITER_LABEL);
	}

	protected void assertFormControlsOfLibraryNode(SWTFormsBot formbot, boolean editable) {
		formbot.label(ADDRESS_LABEL);
		assertTextComponent(formbot, LIBRARY_S_ADDRESS_TEXT, editable);
		formbot.comboBox(0); // for "parentBranch
		// the label for 'people'
		formbot.label(PEOPLE_LABEL);
		// the inner label listing all the people, before the button "..."
		formbot.label(PEOPLE_TEXT);
	}

	protected void assertDialogControlsOfLibraryNode(boolean editable) {
		bot.label(ADDRESS_LABEL);
		assertTextComponent(LIBRARY_S_ADDRESS_TEXT, editable);
		bot.comboBox(0); // for "parentBranch
		// the label for 'people'
		bot.label(PEOPLE_LABEL);
		// the inner label listing all the people, before the button "..."
		bot.label(PEOPLE_TEXT);
	}

	protected void assertDialogControlsOfCustomLibraryNode(boolean editable) {
		bot.label(ADDRESS_LABEL);
		assertTextComponent(LIBRARY_S_ADDRESS_TEXT, editable);
		bot.comboBox(0); // for "parentBranch
		// the label for 'people'
		bot.label(PEOPLE_LABEL);
		// the inner label listing all the people, before the button "..."
		bot.label(CUSTOM_PEOPLE_TEXT);
	}

	protected void assertFormControlsOfWriterNode(SWTFormsBot formbot, boolean editable) {
		formbot.label(ADDRESS_LABEL);
		assertTextComponent(formbot, WRITER_S_ADDRESS_TEXT, editable);
		formbot.label(FIRSTNAME_LABEL);
		formbot.button("..."); // for "books"
	}

	protected void assertDialogControlsOfWriterNode(boolean editable) {
		bot.label(ADDRESS_LABEL);
		assertTextComponent(WRITER_S_ADDRESS_TEXT, editable);
		bot.label(FIRSTNAME_LABEL);
		bot.button("..."); // for "books"
	}

	protected SWTBotEditor assertEditorNotDirty(String editorName) {
		SWTBotEditor editor = getEditor(editorName);
		assertTrue("editor should NOT be in dirty state", !editor.isDirty());
		return editor;
	}

	protected SWTBotEditor assertEditorDirty(String editorName) {
		SWTBotEditor editor = getEditor(editorName);
		assertTrue("editor should be in dirty state", editor.isDirty());
		return editor;
	}

	protected void modifyFormText(SWTFormsBot formbot, String text) {
		formbot.text(text).setText(text + " MODIFIED");
	}

	protected void modifyText(String text) {
		bot.text(text).setText(text + " MODIFIED");
	}

	protected void assertDirtyThenSaveAndAssertNotDirty(String viewName) {
		assertSaveableViewIsDirty(true, viewName);
		saveViewAndAssertNotDirty(viewName);
	}

	protected void assertSaveableViewIsDirty(boolean isDirty, String viewName) {
		ISaveablePart viewAsSaveablePart = getViewAsSaveablePart(viewName);
		assertEquals(isDirty, viewAsSaveablePart.isDirty());
	}

	protected ISaveablePart getViewAsSaveablePart(String viewName) {
		SWTBotView view = getView(viewName);
		IViewPart viewPart = view.getViewReference().getView(false);
		ISaveablePart viewAsSaveablePart = (ISaveablePart) viewPart;
		return viewAsSaveablePart;
	}

	protected void saveViewAndAssertNotDirty(final String viewName) {
		saveView(viewName);
		assertSaveableViewIsDirty(false, viewName);
	}

	protected void saveView(final String viewName) {
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				getViewAsSaveablePart(viewName).doSave(
						new NullProgressMonitor());
			}
		});
	}

	/**
	 * Useful for actions like Save that can show blocking dialogs
	 * @param viewName
	 */
	protected void saveViewAsync(final String viewName) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				getViewAsSaveablePart(viewName).doSave(
						new NullProgressMonitor());
			}
		});
	}

	protected void assertEditorDirtySaveEditorAndAssertNotDirty(String editorName) {
		assertEditorDirty(editorName);
		getEditor(editorName).save();
		assertEditorNotDirty(editorName);
	}

	protected SWTFormsBot formBotFromView(SWTBotView detailView) {
		return new SWTFormsBot(detailView.getWidget());
	}

	protected SWTBotTreeItem expandNodeSync(final SWTBotTree tree, String...names) {
		SWTBotTreeItem current = null;
		for (int i = 0; i < names.length; i++) {
			if (current == null) {
				current = tree.expandNode(names[i]);
			} else {
				current = current.expandNode(names[i]);
			}
			waitForTreeItems(current);
		}
		return current;
	}

	/**
	 * This should prevent test failures in slow machines.
	 * @param treeItem
	 */
	protected void waitForTreeItems(final SWTBotTreeItem treeItem) {
		int retries = 3;
		int msecs = 2000;
		int count = 0;
		while (count < retries) {
			System.out.println("Checking that tree item " + treeItem.getText() + " has children...");
			List<SWTBotTreeItem> foundItems = UIThreadRunnable.syncExec(new ListResult<SWTBotTreeItem>() {
				@Override
				public List<SWTBotTreeItem> run() {
					TreeItem[] items = treeItem.widget.getItems();
					List<SWTBotTreeItem> results = new ArrayList<SWTBotTreeItem>();
					for (TreeItem treeItem : items) {
						results.add(new SWTBotTreeItem(treeItem));
					}
					return results;
				}
			});
			if (foundItems.isEmpty()) {
				treeItem.collapse();
				System.out.println("No chilren... retrying in " + msecs + " milliseconds..."); //$NON-NLS-1$
				try {
					Thread.sleep(msecs);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				treeItem.expand();
			} else if (foundItems.size() == 1 && foundItems.get(0).getText().trim().isEmpty()) {
				treeItem.collapse();
				System.out.println("Only one child with empty text... retrying in " + msecs + " milliseconds..."); //$NON-NLS-1$
				try {
					Thread.sleep(msecs);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				treeItem.expand();
			} else {
				System.out.println("Found " + foundItems.size() + " items. OK!");
				return;
			}
			
			count++;
		}
	}

	protected void assertTableItemsCount(final SWTBotTable table, final int expected) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				assertEquals(expected, table.widget.getItemCount());
			}
		});
	}

	protected void createNewChildAndSelectCreated(SWTBotTreeItem node, String childType) {
		clickOnContextMenu(node, NEW_CHILD, childType);
		// check that the new item was created
		node.expand().getNode(childType);
	}

	protected void createNewSibling(SWTBotTreeItem node, String siblingType) {
		clickOnContextMenu(node, NEW_SIBLING, siblingType);
	}

	protected void assertTableItemsSize(final SWTBotTable table, final int expectedSize) {
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				assertEquals(expectedSize, table.widget.getItems().length);
			}
		});
	}

	protected void createNewSiblingAndSelectCreatedInTree(String view, String nodeForContextMenu, String sibling) {
		final SWTBotTree rootOfTree = getRootOfTreeFromView(view);
		final SWTBotTreeItem writerNode = rootOfTree.getTreeItem(nodeForContextMenu);
		createNewSibling(writerNode, sibling);
		// check that the element is created
		rootOfTree.getTreeItem(sibling);
	}

	protected void createNewSiblingAndAssertTableSize(SWTBotTable table, int initialTableItemsSize, String sibling) {
		assertTableItemsSize(table, initialTableItemsSize);
		table.select(0); // otherwise context menu might not be created
		clickOnContextMenu(table, NEW_SIBLING, sibling);
		assertTableItemsSize(table, initialTableItemsSize+1);
	}

	protected void maximizeCurrentWindow() {
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				ActionFactory.MAXIMIZE.create(window).run();
			}
		});
	}
}
