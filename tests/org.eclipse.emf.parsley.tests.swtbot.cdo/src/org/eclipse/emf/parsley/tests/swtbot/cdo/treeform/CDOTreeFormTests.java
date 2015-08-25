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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class CDOTreeFormTests extends CDOAbstractTests {


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
	public void testExploreLibraryTree(){
		SWTBotView botView=getLibraryView(TEST_CDO_FORM_VIEW);
		SWTBotTreeItem libraryNode = getLibraryNode(botView);
		Assert.assertNotNull(libraryNode);
	}
	
	@Test
	public void testSelectBook(){
		SWTBotView botView=getLibraryView(TEST_CDO_FORM_VIEW);
		SWTBotTreeItem libraryNode = getLibraryNode(botView);
		SWTBotTreeItem bookNode = libraryNode.getNode("Book Domain Specific Languages");
		Assert.assertNotNull(bookNode);
	}

	@Test
	public void testDetailFormForSelectedBook(){
		SWTBotView botView=getLibraryView(TEST_CDO_FORM_VIEW);
		SWTBotTreeItem libraryNode = getLibraryNode(botView);
		libraryNode.select("Book Domain Specific Languages");
		Assert.assertEquals(botView.bot().textWithLabel("Title").getText(),"Domain Specific Languages");
		Assert.assertEquals(botView.bot().textWithLabel("Pages").getText(),"0");
	}

	@Test
	public void testSelectAuthor(){
		SWTBotView botView=getLibraryView(TEST_CDO_FORM_VIEW);
		SWTBotTreeItem libraryNode = getLibraryNode(botView);
		SWTBotTreeItem authorNode = libraryNode.getNode("Author Ed Merks");
		Assert.assertNotNull(authorNode);
	}
	
	@Test
	public void testDetailFormForSelectedAuthor(){
		SWTBotView botView=getLibraryView(TEST_CDO_FORM_VIEW);
		SWTBotTreeItem libraryNode = getLibraryNode(botView);
		libraryNode.select("Author Ed Merks");
		Assert.assertEquals(botView.bot().textWithLabel("Name").getText(),"Ed Merks");
	}
	
	@After
	public void slowdown() {
		bot.sleep(SLOWDOWN);
	}
	
	@AfterClass
	public static void closeView(){
		getLibraryView(TEST_CDO_FORM_VIEW).close();
	}
	
	private SWTBotTreeItem getLibraryNode(SWTBotView botView) {
		SWTBotTreeItem libraryNode = botView.bot().tree().expandNode("Library");
		return libraryNode;
	}


}
