package org.eclipse.emf.parsley.tests.swtbot.cdo.util;

import static org.eclipse.swtbot.swt.finder.waits.Conditions.shellCloses;
import library.business.CommonBusiness;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.cdo.net4j.CDONet4jUtil;
import org.eclipse.emf.cdo.net4j.CDOSessionConfiguration;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.parsley.resource.ResourceLoader;
import org.eclipse.net4j.connector.IConnector;
import org.eclipse.net4j.util.container.IPluginContainer;
import org.eclipse.net4j.util.security.PasswordCredentialsProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.PlatformUI;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.google.inject.Injector;
import com.google.inject.Provider;

public class CDOAbstractTests {

	protected static final String PORT = "2037";
	protected static final String SERVER ="localhost";
	protected static final String REPOSITORY ="demo";
	protected static final String MY_RESOURCE ="my_res";
	protected static final String USER ="user";
	protected static final String PASSWORD ="password";

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

		CDOSessionConfiguration config = CDONet4jUtil.createSessionConfiguration();
		config.setConnector(connector);
		config.setRepositoryName("demo");

		PasswordCredentialsProvider credentialsProvider = new PasswordCredentialsProvider(user, password);			
		config.getAuthenticator().setCredentialsProvider(credentialsProvider);
		
		connector.setOpenChannelTimeout(1000);
		CDOSession session = config.openSession();
		session.close();
	}
	

	protected static SWTWorkbenchBot bot;

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
	
	
protected static final String EMF_PARSLEY_CATEGORY = "Emf Parsley";
	
	protected static SWTBotView openTestView(String libraryView) {
		bot.menu("Window").menu("Show View").menu("Other...").click();
		SWTBotShell shell = bot.shell("Show View");
		shell.activate();
		bot.tree().expandNode(EMF_PARSLEY_CATEGORY).select(libraryView);
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
}