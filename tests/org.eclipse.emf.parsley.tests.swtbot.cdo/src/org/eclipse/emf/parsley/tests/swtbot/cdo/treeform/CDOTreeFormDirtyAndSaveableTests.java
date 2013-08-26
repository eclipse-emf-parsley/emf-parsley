package org.eclipse.emf.parsley.tests.swtbot.cdo.treeform;

import static org.eclipse.swtbot.swt.finder.waits.Conditions.shellCloses;
import library.business.CommonBusiness;

import org.eclipse.emf.parsley.examples.cdo.treeform.TreeformCDOActivator;
import org.eclipse.emf.parsley.tests.swtbot.cdo.util.CDOAbstractTests;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CDOTreeFormDirtyAndSaveableTests extends CDOAbstractTests {


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
	public void testDirtyForSelectedBook(){
		SWTBotView botView=getLibraryView(TEST_CDO_FORM_VIEW);
		SWTBotTreeItem libraryNode = getLibraryNode(botView);
		libraryNode.select("Book Domain Specific Languages");
		botView.bot().textWithLabel("title").setText("My Tyltle");
		Assert.assertTrue(isLibraryViewDirty(TEST_CDO_FORM_VIEW));
		forceCloseView(botView);
	}

	
	@Test
	public void testDirtyForSelectedAuthor(){
		SWTBotView botView=getLibraryView(TEST_CDO_FORM_VIEW);
		SWTBotTreeItem libraryNode = getLibraryNode(botView);
		libraryNode.select("Author Ed Merks");
		botView.bot().textWithLabel("name").setText("My Name");
		bot.sleep(100);
		Assert.assertTrue(isLibraryViewDirty(TEST_CDO_FORM_VIEW));
		forceCloseView(botView);
	}
	
	@After
	public void slowdown() {
		bot.sleep(SLOWDOWN);
	}
	
	private SWTBotTreeItem getLibraryNode(SWTBotView botView) {
		SWTBotTreeItem libraryNode = botView.bot().tree().expandNode("Library");
		return libraryNode;
	}

	private void forceCloseView(SWTBotView botView) {
		botView.close();
		bot.waitUntil(Conditions.shellIsActive("Save Resource"),5000);
		SWTBotShell shell = bot.shell("Save Resource");
		Assert.assertNotNull(shell);
		shell.activate();
		bot.button("No").click();
		bot.waitUntil(shellCloses(shell), 50000);
	}
	
}
