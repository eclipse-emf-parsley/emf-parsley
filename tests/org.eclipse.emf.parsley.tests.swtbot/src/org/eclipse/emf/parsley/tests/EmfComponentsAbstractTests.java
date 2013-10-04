package org.eclipse.emf.parsley.tests;

import static org.eclipse.swtbot.swt.finder.waits.Conditions.shellCloses;
import static org.eclipse.xtext.ui.junit.util.IResourcesSetupUtil.cleanWorkspace;
import static org.eclipse.xtext.ui.junit.util.IResourcesSetupUtil.createFile;
import static org.eclipse.xtext.ui.junit.util.IResourcesSetupUtil.root;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.tests.views.LibraryEmfView;
import org.eclipse.emf.parsley.util.ActionBarsUtils;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.SubStatusLineManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
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
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.junit.util.IResourcesSetupUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 * @author bettini
 * 
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class EmfComponentsAbstractTests {

	protected static final String EMF_PARSLEY_CATEGORY = "Emf Parsley";

	protected static final String WRITER_LABEL = "Writer Lorenzo Bettini";

	protected static final String WRITER_NAME = "Lorenzo Bettini";

	protected static final String BORROWER_LABEL = "Borrower Bar";

	protected static final String CUSTOM_BORROWER_LABEL = "Borrower: Foo";

	protected static final String BOOK_LABEL = "Book Lorenzo's Book";

	protected static final String LIBRARY_LABEL = "Library My Library";

	protected static final String DAMAGED_VIDEO_LABEL = "Video Cassette My Damaged Video";

	protected static final String OUTLINE_VIEW = "Outline";

	protected static final String STATEMACHINE_LABEL = "Statemachine";

	protected static final String EVENT_LABEL = "Event doorOpened";

	protected static final String STATE_LABEL = "State idle";

	protected static final String TRANSITION_LABEL = "Transition";

	protected static final String EMF_TREE_EDITOR = "EMF Tree Editor";

	protected static final String EMF_TREE_EDITOR_STATEMACHINE = "EMF Tree Editor Statemachine";

	protected static final String EMF_TREE_EDITOR_NO_MOUSE = "EMF Tree Editor No Mouse Events";

	protected static final String EMF_TREE_EDITOR_CUSTOM_LABEL = "EMF Tree Editor Custom Label";

	protected static final String EMF_CUSTOM_LIBRARY_EDITOR = "EMF Custom Library Editor";

	protected static final String MY_EXTLIBRARY = "My.extlibrary";

	protected static final String MY2_EXTLIBRARY = "My2.extlibrary";

	protected static final String MY_STATEMACHINE = "fowlerdsl.statemachine";

	protected static final String MY_TEST_PROJECT = "MyTestProject";

	public static final String MY_EXTLIBRARY_RELATIVE_PATH = MY_TEST_PROJECT
			+ "/" + MY_EXTLIBRARY;

	public static final String MY2_EXTLIBRARY_RELATIVE_PATH = MY_TEST_PROJECT
			+ "/" + MY2_EXTLIBRARY;

	protected static final String MY_STATEMACHINE_RELATIVE_PATH = MY_TEST_PROJECT
			+ "/" + MY_STATEMACHINE;

	protected static final String MY_EXT_LIBRARY_PLATFORM_URI = "platform:/resource/"
			+ MY_EXTLIBRARY_RELATIVE_PATH;

	protected static final String MY_STATEMACHINE_PLATFORM_URI = "platform:/resource/"
			+ MY_STATEMACHINE_RELATIVE_PATH;

	protected static final String HARDCODED_LIBRARY_PLATFORM_URI = LibraryEmfView.resourceUri;

	protected static final String LIBRARY_EMF_VIEW = "LibraryEmfView";

	protected static final String LIBRARY_EMF_VIEW_CUSTOM_LABEL = "LibraryEmfView Custom Label";

	protected static final String EMF_DETAIL_VIEW = "Emf Form View";
	
	protected static final String EMF_DETAIL_READONLY_VIEW = "Emf Read Only Form View";

	protected static final String EMF_TREE_FORM_DETAIL_VIEW = "Emf Tree Form View";

	protected static final String LIBRARY_CUSTOM_DETAIL_VIEW = "Library Emf Detail View";

	protected static final String EMF_SHOW_ALL_TABLE_VIEW = "Emf Show All Table View";

	protected static final String LIBRARY_TEST_EMF_TABLE_VIEW = "Library Test Table";
	
	protected static final String LIBRARY_BOOKS_TABLE_VIEW = "Library Books Table View";

	protected static final String TEST_SAVEABLE_TREE_FORM_VIEW = "Library Test Saveable Resource Tree Form View";
	
	protected static final String TEST_SAVEABLE_TABLE_FORM_VIEW = "Library Test Saveable Resource Table Form View";

	protected static final String TEST_SAVEABLE_TABLE_VIEW = "Library Test Saveable Table View";

	protected static final String TEST_SAVEABLE_TREE_VIEW = "Library Test Saveable Tree View";

	protected static final String TEST_SAVEABLE_VIEW_WITH_CUSTOM_CONTENT_PROVIDER = "Library Tree View With Custom Content Provider";
	
	protected static final String WRITER_S_ADDRESS_TEXT = "writer's address";

	protected static final String ADDRESS_LABEL = "address";
	
	protected static final String BORROWERS_LABEL = "borrowers";
	
	protected static final String AUTHOR_LABEL = "author";

	protected static final String FIRSTNAME_LABEL = "firstName";

	protected static final String DAMAGED_LABEL = "damaged";

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

	protected static final String NEW_SIBLING = "New Sibling";

	protected static final String EMF_COMPONENTS_PROJECT = "EmfComponentsProject";

	protected static final String NEW_EMF_COMPONENTS_PROJECT = "Emf Parsley Project";

	protected static final String NEW_EMF_COMPONENTS_DSL_PROJECT = "Emf Parsley Dsl Based Project";

	protected static final String EMF_COMPONENTS_MAIL_RCP_EXAMPLE = "Emf Parsley Mail RCP Example";
	
	protected static final String EMF_COMPONENTS_FIRST_EXAMPLE = "Emf Parsley First Example";

	protected static final String EMF_COMPONENTS_VIEWS_EXAMPLES = "Emf Parsley Views Examples";

	protected static SWTWorkbenchBot bot;

	protected static Map<String, String> editorNamesToId;

	@BeforeClass
	public static void beforeClass() throws Exception {
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
				EmfComponentsTestsActivator.EMF_TREE_EDITOR);
		editorNamesToId.put(EMF_TREE_EDITOR_STATEMACHINE,
				EmfComponentsTestsActivator.EMF_TREE_EDITOR_FOR_STATEMACHINE);
		editorNamesToId.put(EMF_TREE_EDITOR_NO_MOUSE,
				EmfComponentsTestsActivator.EMF_TREE_EDITOR_NO_MOUSE_ID);
		editorNamesToId.put(EMF_TREE_EDITOR_CUSTOM_LABEL,
				EmfComponentsTestsActivator.EMF_TREE_EDITOR_CUSTOM_LABEL);
		editorNamesToId.put(EMF_CUSTOM_LIBRARY_EDITOR,
				EmfComponentsTestsActivator.EMF_EDITOR_FOR_LIBRARY);

		bot.viewByTitle(OUTLINE_VIEW).show();
	}

	@AfterClass
	public static void clean() throws CoreException {
		bot.resetWorkbench();
	}

	@After
	public void runAfterEveryTest() throws CoreException {
		// bot.sleep(2000);
		cleanWorkspace();
		IResourcesSetupUtil.waitForAutoBuild();
	}

	protected static void closeWelcomePage() throws InterruptedException {
		Display.getDefault().syncExec(new Runnable() {
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

	protected SWTBotTreeItem getWriterNode(final SWTBotTreeItem treeItem) {
		return treeItem.expand().getNode(WRITER_LABEL);
	}

	protected SWTBotTreeItem getDamagedVideoCassetteNode(final SWTBotTreeItem treeItem) {
		return treeItem.expand().getNode(DAMAGED_VIDEO_LABEL);
	}

	protected SWTBotTreeItem getStatemachineNode(final SWTBotTreeItem treeItem) {
		return treeItem.expand().getNode(STATEMACHINE_LABEL);
	}

	protected SWTBotTreeItem getEventNode(final SWTBotTreeItem treeItem) {
		return treeItem.expand().getNode(EVENT_LABEL);
	}

	protected SWTBotTreeItem getStateNode(final SWTBotTreeItem treeItem) {
		return treeItem.expand().getNode(STATE_LABEL);
	}

	protected SWTBotTreeItem getTransitionNode(final SWTBotTreeItem treeItem) {
		return treeItem.expand().getNode(TRANSITION_LABEL);
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

	protected SWTBotTreeItem getRootOfEditorTree(
			String emfEditorContextMenuString, String fileName,
			String treeRootLabel) throws CoreException,
			InvocationTargetException, InterruptedException, IOException {
		SWTBotTree tree = getEditorTree(emfEditorContextMenuString, fileName);
		SWTBotTreeItem treeItemRoot = tree.getTreeItem(treeRootLabel);
		return treeItemRoot;
	}

	protected SWTBotTree getEditorTree(String emfEditorContextMenuString,
			String fileName) throws CoreException, InvocationTargetException,
			InterruptedException, IOException {
		SWTBotEditor editor = openEmfEditorOnTestFile(
				emfEditorContextMenuString, fileName);
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

	protected SWTBotTree getSWTBotTree(final SWTBotTreeItem treeItem) {
		return new SWTBotTree(
				UIThreadRunnable.syncExec(new WidgetResult<Tree>() {
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
		IFile file = createFile(MY_STATEMACHINE_RELATIVE_PATH,
				localFileContents(MY_STATEMACHINE));
		assertTrue(file.exists());
	}

	protected String localFileContents(String string) throws IOException {
		return EmfComponentsTestsActivator.localFileContents(string);
	}
	
	protected Library localLibrary(String extlibraryFileName) throws IOException {
		File file = EmfComponentsTestsActivator.localFile(extlibraryFileName);
		URI uri = URI.createFileURI(file.getAbsolutePath());
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.getResource(uri, true);
		return (Library) resource.getContents().get(0);
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
		bot.tree().expandNode(EMF_PARSLEY_CATEGORY, "Examples")
				.select(exampleDescription);
		bot.button("Next >").click();


		bot.button("Finish").click();
		
		bot.waitUntil(shellCloses(shell), SWTBotPreferences.TIMEOUT);
		
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
		bot.tree().expandNode(category).select(projectType);
		bot.button("Next >").click();

		bot.textWithLabel("Project name:").setText(projectName);
		return shell;
	}

	protected SWTBotShell createNewProjectWizard(String category,
			String subCategory, String projectType, String projectName) {
		bot.menu("File").menu("New").menu("Project...").click();

		SWTBotShell shell = bot.shell("New Project");
		shell.activate();
		bot.tree().expandNode(category, subCategory).select(projectType);
		bot.button("Next >").click();

		bot.textWithLabel("Project name:").setText(projectName);
		return shell;
	}

	protected void assertProjectIsCreated(String projectName, SWTBotShell shell) {
		// creation of a project might require some time
		bot.waitUntil(shellCloses(shell), SWTBotPreferences.TIMEOUT);
		assertProjectIsCreated(projectName);
	}

	protected void assertProjectIsCreated(String projectName) {
		assertTrue("Project doesn't exist", isProjectCreated(projectName));
	}

	protected void waitForBuild() throws CoreException {
		IResourcesSetupUtil.waitForAutoBuild();
		
		// ensure that all queued workspace operations and locks are released
		try {
			ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {
				public void run(IProgressMonitor monitor) throws CoreException {
					// nothing to do!
				}
			}, new NullProgressMonitor());
		} catch (CoreException e) {
			e.printStackTrace();
		}

		Display.getDefault().syncExec(new Runnable() {
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

	protected void accessStateMachineNodes(SWTBotTreeItem root) {
		getEventNode(getStatemachineNode(root));
		getTransitionNode(getStateNode(getStatemachineNode(root)));
	}

	protected void assertTreeItemImage(SWTBotTreeItem item,
			final ImageDescriptor expectedImage) {
		final TreeItem widget = item.widget;
		// part of test that requires UI-thread
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				try {
					Image image = widget.getImage();
					// System.out.println(image);
					assertImageDataIs(expectedImage.getImageData(),
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

				// this is a terrible hack to read the current text of the
				// status line manager as suggested here:
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

	protected SWTBotView openTestView(String libraryView) {
		bot.menu("Window").menu("Show View").menu("Other...").click();
		SWTBotShell shell = bot.shell("Show View");
		shell.activate();
		bot.tree().expandNode(EMF_PARSLEY_CATEGORY).select(libraryView);
		bot.button("OK").click();
		bot.waitUntil(shellCloses(shell), SWTBotPreferences.TIMEOUT);
		return getLibraryView(libraryView);
	}

	protected void undo(String undoText) {
		bot.menu("Edit").menu("Undo " + undoText).click();
	}

	protected SWTBotView getLibraryView(String libraryView) {
		return bot.viewByTitle(libraryView);
	}

	protected void closeLibraryView(String libraryView) {
		getLibraryView(libraryView).close();
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

	protected static void assertImageDataIs(ImageData expectedImageData,
			ImageData actualImageData) {
		if (expectedImageData.width != actualImageData.width
				|| expectedImageData.height != actualImageData.height) {
			fail(MessageFormat
					.format("Image data do not have the same dimensions ({0}x{1} expected, got {2}x{3})",
							expectedImageData.width, expectedImageData.height,
							actualImageData.width, actualImageData.height));
		}

		for (int y = 0; y < expectedImageData.height; y++) {
			for (int x = 0; x < expectedImageData.width; x++) {
				int actualPixel = actualImageData.getPixel(x, y);
				int expectedPixel = expectedImageData.getPixel(x, y);
				RGB actualRGB = actualImageData.palette.getRGB(actualPixel);
				RGB expectedRGB = expectedImageData.palette
						.getRGB(expectedPixel);
				if (!actualRGB.equals(expectedRGB)) {
					fail(MessageFormat.format(
							"Image data do not match at ({0},{1})", x, y));
				}
			}
		}
	}

	/**
	 * Taken from http://www.prait.ch/wordpress/?p=218
	 * 
	 * @param parentMenu
	 * @param itemText
	 * @return
	 * @throws WidgetNotFoundException
	 */
	protected SWTBotMenu getSubMenuItem(final SWTBotMenu parentMenu,
			final String itemText) throws WidgetNotFoundException {

		MenuItem menuItem = UIThreadRunnable
				.syncExec(new WidgetResult<MenuItem>() {
					public MenuItem run() {
						Menu bar = parentMenu.widget.getMenu();
						if (bar != null) {
							for (MenuItem item : bar.getItems()) {
								if (item.getText().equals(itemText)) {
									return item;
								}
							}
						}
						return null;
					}
				});

		if (menuItem == null) {
			throw new WidgetNotFoundException("MenuItem \"" + itemText
					+ "\" not found.");
		} else {
			return new SWTBotMenu(menuItem);
		}
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
			public void run() {
				Assert.assertEquals(editable, t.widget.getEditable());
			}
		});
	}

	protected void assertCheckBoxComponent(SWTFormsBot formbot, int index,
			final boolean isChecked, final boolean isEnabled) {
		final SWTBotCheckBox b = formbot.checkBox(index);
		Display.getDefault().syncExec(new Runnable() {
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
}
