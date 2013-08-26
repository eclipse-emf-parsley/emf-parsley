package org.eclipse.emf.parsley.tests.swtbot.cdo.treeform;

import library.business.CommonBusiness;

import org.eclipse.emf.parsley.examples.cdo.treeform.TreeformCDOActivator;
import org.eclipse.emf.parsley.tests.swtbot.cdo.util.CDOAbstractTests;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CDOTreeFormTests extends CDOAbstractTests {


	private static final String TEST_CDO_FORM_VIEW = "Test CDO form view";
	private static int SLOWDOWN=1000;

	@BeforeClass
	public static void prepare() throws Exception{
		
		startCDOServer();
		CommonBusiness.setPort(PORT);
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
		Assert.assertEquals(botView.bot().textWithLabel("title").getText(),"Domain Specific Languages");
		Assert.assertEquals(botView.bot().textWithLabel("pages").getText(),"0");
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
		Assert.assertEquals(botView.bot().textWithLabel("name").getText(),"Ed Merks");
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
