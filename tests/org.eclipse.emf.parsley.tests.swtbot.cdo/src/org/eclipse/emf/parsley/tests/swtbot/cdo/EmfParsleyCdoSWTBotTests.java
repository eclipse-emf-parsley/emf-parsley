/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - Initial contribution and API
 * Lorenzo Bettini - use SWTBotJunit4ClassRunner to generate screenshots
 *                   in case of failures
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.swtbot.cdo;

import static org.eclipse.swtbot.swt.finder.waits.Conditions.shellCloses;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.cdo.net4j.CDONet4jSessionConfiguration;
import org.eclipse.emf.cdo.net4j.CDONet4jUtil;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.parsley.examples.cdo.server.CDOServer;
import org.eclipse.net4j.connector.IConnector;
import org.eclipse.net4j.util.container.IPluginContainer;
import org.eclipse.net4j.util.security.PasswordCredentialsProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.results.ListResult;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PlatformUI;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class EmfParsleyCdoSWTBotTests {

	private static final String TEST_CDO_FORM_VIEW = "Test CDO form view";
	private static int SLOWDOWN = 1000;

	private static final String PORT = "2037";
	private static final String SERVER = "localhost";
	private static final String REPOSITORY = "demo";
	private static final String USER = "user";
	private static final String PASSWORD = "password";

	private static final String EMF_PARSLEY_CATEGORY = "EMF Parsley";

	private static SWTWorkbenchBot bot;

	@BeforeClass
	public static void prepare() throws Exception {
		startCDOServer();
		bot = new SWTWorkbenchBot();
		SWTBotPreferences.KEYBOARD_LAYOUT = "EN_US";
		closeWelcomePage();
		openTestView(TEST_CDO_FORM_VIEW);
	}

	@AfterClass
	public static void closeView() {
		getLibraryView(TEST_CDO_FORM_VIEW).close();
	}

	@Test
	public void testView() {
		Assert.assertNotNull(getLibraryView(TEST_CDO_FORM_VIEW));
	}

	@Test
	public void testExploreLibraryTree() {
		SWTBotView botView = getLibraryView(TEST_CDO_FORM_VIEW);
		SWTBotTreeItem libraryNode = getLibraryNode(botView);
		Assert.assertNotNull(libraryNode);
	}

	@Test
	public void testSelectAuthor() {
		SWTBotView botView = getLibraryView(TEST_CDO_FORM_VIEW);
		SWTBotTreeItem libraryNode = getLibraryNode(botView);
		SWTBotTreeItem authorNode = libraryNode.getNode("Author Ed Merks");
		Assert.assertNotNull(authorNode);
	}

	@Test
	public void testDetailFormForSelectedAuthor() {
		SWTBotView botView = getLibraryView(TEST_CDO_FORM_VIEW);
		SWTBotTreeItem libraryNode = getLibraryNode(botView);
		libraryNode.select("Author Ed Merks");
		Assert.assertEquals(botView.bot().textWithLabel("Name").getText(), "Ed Merks");
	}

	@Test
	public void testDirtyForSelectedBook() {
		SWTBotView botView = getLibraryView(TEST_CDO_FORM_VIEW);
		SWTBotTreeItem libraryNode = getLibraryNode(botView);
		libraryNode.select("Book Domain Specific Languages");
		botView.bot().textWithLabel("Title").setText("My Tyltle");
		someSleep();
		assertLibraryViewDirty(TEST_CDO_FORM_VIEW, true);
		forceSaveView(botView);
		assertLibraryViewDirty(TEST_CDO_FORM_VIEW, false);
	}

	private void someSleep() {
		bot.sleep(SLOWDOWN);
	}

	private SWTBotTreeItem getLibraryNode(SWTBotView botView) {
		SWTBotTreeItem libraryNode = botView.bot().tree().expandNode("Library");
		return libraryNode;
	}

	private static void startCDOServer() {
		CDOServer.startMemoryRepository(REPOSITORY, PORT);
		Assert.assertTrue(doTestCdoConnection(SERVER, PORT, REPOSITORY));
	}

	private static boolean doTestCdoConnection(String server, String port, String repository) {
		String user = USER;
		String password = PASSWORD;
		try {
			checkCDOServer(server, user, password, port);
		} catch (RuntimeException e) {
			return false;
		}
		return true;
	}

	public static void checkCDOServer(String host, String user, String password, String port) {
		final IConnector connector = (IConnector) IPluginContainer.INSTANCE.getElement("org.eclipse.net4j.connectors",
				"tcp", host + ":" + port);

		CDONet4jSessionConfiguration config = CDONet4jUtil.createNet4jSessionConfiguration();
		config.setConnector(connector);
		config.setRepositoryName("demo");

		PasswordCredentialsProvider credentialsProvider = new PasswordCredentialsProvider(user, password);
		config.setCredentialsProvider(credentialsProvider);

		connector.setOpenChannelTimeout(SWTBotPreferences.TIMEOUT);
		CDOSession session = config.openNet4jSession();
		session.close();
	}

	private void assertLibraryViewDirty(final String id, final boolean expectedDirty) {
		final IViewReference viewReference = getLibraryView(id).getViewReference();
		bot.waitUntil(new DefaultCondition() {
			@Override
			public boolean test() throws Exception {
				boolean dirty = viewReference.isDirty();
				return dirty == expectedDirty;
			}

			@Override
			public String getFailureMessage() {
				return "expected dirty: " + expectedDirty +
						", but was " + viewReference.isDirty();
			}
		});
	}

	private static SWTBotView openTestView(String libraryView) {
		bot.menu("Window").menu("Show View").menu("Other...").click();
		SWTBotShell shell = bot.shell("Show View");
		shell.activate();
		expandNodeSync(bot.tree(), EMF_PARSLEY_CATEGORY).select(libraryView);
		bot.button("OK").click();
		bot.waitUntil(shellCloses(shell), 50000);
		return getLibraryView(libraryView);
	}

	private static SWTBotView getLibraryView(String libraryView) {
		return bot.viewByTitle(libraryView);
	}

	private static void closeWelcomePage() throws InterruptedException {
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				if (PlatformUI.getWorkbench().getIntroManager().getIntro() != null) {
					PlatformUI.getWorkbench().getIntroManager()
							.closeIntro(PlatformUI.getWorkbench().getIntroManager().getIntro());
				}
			}
		});
	}

	private static SWTBotTreeItem expandNodeSync(final SWTBotTree tree, String... names) {
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
	 * 
	 * @param treeItem
	 */
	private static void waitForTreeItems(final SWTBotTreeItem treeItem) {
		int retries = 3;
		int msecs = 2000;
		int count = 0;
		while (count < retries) {
			System.out.println("Checking that tree item " + treeItem.getText() + " has children...");
			List<SWTBotTreeItem> foundItems = UIThreadRunnable.syncExec(new ListResult<SWTBotTreeItem>() {
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

	private void forceSaveView(SWTBotView botView) {
		final IViewPart view = botView.getViewReference().getView(false);
		if (view instanceof ISaveablePart) {
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					((ISaveablePart) view).doSave(new NullProgressMonitor());
				}
			});
		} else {
			throw new RuntimeException("View " + view.getTitle() + " is not saveable!");
		}
	}
}
