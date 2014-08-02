/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.swtbot.cdo.util;

import static org.eclipse.swtbot.swt.finder.waits.Conditions.shellCloses;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
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
import org.eclipse.swtbot.swt.finder.results.ListResult;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.PlatformUI;
import org.junit.BeforeClass;

public class CDOAbstractTests {

	protected static final String PORT = "2037";
	protected static final String SERVER ="localhost";
	protected static final String REPOSITORY ="demo";
	protected static final String MY_RESOURCE ="my_res";
	protected static final String USER ="user";
	protected static final String PASSWORD ="password";

	protected static final String EMF_PARSLEY_CATEGORY = "Emf Parsley";

	protected static SWTWorkbenchBot bot;

	protected  static void startCDOServer(){
		CDOServer.startMemoryRepository(REPOSITORY, PORT);
		Assert.isTrue(doTestCdoConnection(SERVER, PORT, REPOSITORY));
	}
	
	protected static boolean doTestCdoConnection(String server, String port, String repository){
		String user = USER;
		String password = PASSWORD;
		try{
			checkCDOServer(server, user, password, port);
		}catch (RuntimeException e){
			return false;
		}
		return true;
	}
	
	public static void checkCDOServer(String host, String user, String password, String port) {
		final IConnector connector = (IConnector) IPluginContainer.INSTANCE.getElement("org.eclipse.net4j.connectors","tcp", host+":"+port  ); 

		CDONet4jSessionConfiguration config = CDONet4jUtil.createNet4jSessionConfiguration();
		config.setConnector(connector);
		config.setRepositoryName("demo");

		PasswordCredentialsProvider credentialsProvider = new PasswordCredentialsProvider(user, password);			
		config.setCredentialsProvider(credentialsProvider);
		
		connector.setOpenChannelTimeout(1000);
		CDOSession session = config.openNet4jSession();
		session.close();
	}

	@BeforeClass
	public static void initBot() throws Exception {
		bot = new SWTWorkbenchBot();
		SWTBotPreferences.KEYBOARD_LAYOUT = "EN_US";
	}

	public CDOAbstractTests() {
		super();
	}
	
	protected void doSaveView(){
		SWTBotToolbarButton toggleButtonSave = bot.toolbarButtonWithTooltip("Save (Ctrl+S)");
		Assert.isNotNull(toggleButtonSave);
		toggleButtonSave.click();
	}

	protected boolean isLibraryViewDirty(String id){
		 return getLibraryView(id).getViewReference().isDirty();
	}

	protected void doCloseLibraryView(String id){
		getLibraryView(id).close();
	}
	
	protected static SWTBotView openTestView(String libraryView) {
		bot.menu("Window").menu("Show View").menu("Other...").click();
		SWTBotShell shell = bot.shell("Show View");
		shell.activate();
		expandNodeSync(bot.tree(), EMF_PARSLEY_CATEGORY).select(libraryView);
		bot.button("OK").click();
		bot.waitUntil(shellCloses(shell), 50000);
		return getLibraryView(libraryView);
	}

	protected void undo(String undoText) {
		bot.menu("Edit").menu("Undo " + undoText).click();
	}

	protected static SWTBotView getLibraryView(String libraryView) {
		return bot.viewByTitle(libraryView);
	}


	protected int doGetItemCountInView(String viewId){
		SWTBotTree tree = bot.viewById(viewId).bot().tree();
		return tree.rowCount();
	}

	protected void doSelectFirstItem(String viewId){
		SWTBotTree tree = bot.viewById(viewId).bot().tree();
		tree.select(0);
	}
	
	protected SWTBotTreeItem doGetNodeWithText(String viewId, String nodeText){
		SWTBotTree tree = bot.viewById(viewId).bot().tree();
		return tree.getTreeItem(nodeText);
	}

	protected void doSelectLastItem(String viewId){
		SWTBotTree tree = bot.viewById(viewId).bot().tree();
		tree.select(tree.rowCount()-1);
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

	protected static SWTBotTreeItem expandNodeSync(final SWTBotTree tree, String...names) {
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
	protected static void waitForTreeItems(final SWTBotTreeItem treeItem) {
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

}