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
package org.eclipse.emf.parsley.tests.swtbot.cdo.treeform;

import org.eclipse.emf.parsley.tests.swtbot.cdo.util.CDOAbstractTests;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@SuppressWarnings("restriction")
@RunWith(SWTBotJunit4ClassRunner.class)
public class CDOTreeFormDirtyAndSaveableTests extends CDOAbstractTests {


	private static final String TEST_CDO_FORM_VIEW = "Test CDO form view";
	private static int SLOWDOWN=1000;

	@BeforeClass
	public static void prepare() throws Exception{
		
		startCDOServer();
		closeWelcomePage();
		openTestView(TEST_CDO_FORM_VIEW);
	}
	
	@Test
	public void testView(){
		Assert.assertNotNull(getLibraryView(TEST_CDO_FORM_VIEW));
	}

	@Test
	public void testDirtyForSelectedBook(){
		SWTBotView botView=getLibraryView(TEST_CDO_FORM_VIEW);
		SWTBotTreeItem libraryNode = getLibraryNode(botView);
		libraryNode.select("Book Domain Specific Languages");
		botView.bot().textWithLabel("title").setText("My Tyltle");
		botView.bot().sleep(50);
		Assert.assertTrue(isLibraryViewDirty(TEST_CDO_FORM_VIEW));
		forceSaveView(botView);
		Assert.assertFalse(isLibraryViewDirty(TEST_CDO_FORM_VIEW));
//		botView.close();
	}

	
	@Test
	public void testDirtyForSelectedAuthor(){
		SWTBotView botView=getLibraryView(TEST_CDO_FORM_VIEW);
		SWTBotTreeItem libraryNode = getLibraryNode(botView);
		libraryNode.select("Author Ed Merks");
		botView.bot().textWithLabel("name").setText("My Name");
		botView.bot().sleep(50);
		Assert.assertTrue(isLibraryViewDirty(TEST_CDO_FORM_VIEW));
		forceSaveView(botView);
		Assert.assertFalse(isLibraryViewDirty(TEST_CDO_FORM_VIEW));
//		botView.close();
	}
	
	@After
	public void slowdown() {
		bot.sleep(SLOWDOWN);
	}
	
	private SWTBotTreeItem getLibraryNode(SWTBotView botView) {
		SWTBotTreeItem libraryNode = botView.bot().tree().expandNode("Library");
		return libraryNode;
	}

//	private void forceCloseView(SWTBotView botView) {
//		botView.close();
//		bot.waitUntil(Conditions.shellIsActive("Save Resource"),5000);
//		SWTBotShell shell = bot.shell("Save Resource");
//		Assert.assertNotNull(shell);
//		shell.activate();
//		bot.button("No").click();
//		bot.waitUntil(shellCloses(shell), 50000);
//	}
	
	private void forceSaveView(SWTBotView botView){
		final WorkbenchPage workbenchPage = (WorkbenchPage)botView.getViewReference().getPage();
		final IViewPart view = botView.getViewReference().getView(false);
		if(view instanceof ISaveablePart){	
			PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
				@Override
				public void run() {
					workbenchPage.saveSaveable((ISaveablePart)view, view, false, false);
				}
			});
		}else{
			throw new RuntimeException("View " +view.getTitle() + " is not saveable!");
		}
	}
	
}
