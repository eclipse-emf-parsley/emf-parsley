package org.eclipse.emf.parsley.tests.swtbot.cdo.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.cdo.server.CDOServerUtil;
import org.eclipse.emf.cdo.server.IRepository;
import org.eclipse.emf.cdo.server.IStore;
import org.eclipse.emf.cdo.server.mem.MEMStoreUtil;
import org.eclipse.emf.cdo.server.net4j.CDONet4jServerUtil;
import org.eclipse.net4j.Net4jUtil;
import org.eclipse.net4j.tcp.TCPUtil;
import org.eclipse.net4j.util.container.IPluginContainer;
import org.eclipse.net4j.util.om.OMPlatform;
import org.eclipse.net4j.util.om.log.PrintLogHandler;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * This class is intended to be used as a CDO server in Memory for tests
 * 
 * @author fraguid
 *
 */

public class CDOServer {
	
	private static final String DEFAULT_PORT = "2036";

	public static void startMemoryRepository(String repoName){
		startMemoryRepository(repoName, DEFAULT_PORT);
	}
	
	public static void startMemoryRepository(String repoName, String port) {
		Net4jUtil.prepareContainer(IPluginContainer.INSTANCE);
		TCPUtil.prepareContainer(IPluginContainer.INSTANCE);
		CDONet4jServerUtil.prepareContainer(IPluginContainer.INSTANCE);
		OMPlatform.INSTANCE.addLogHandler(PrintLogHandler.CONSOLE);
		OMPlatform.INSTANCE.setDebugging(true);
		IStore store = MEMStoreUtil.createMEMStore();

		Map<String, String> props = new HashMap<String, String>();
		props.put(IRepository.Props.OVERRIDE_UUID, repoName);

		IRepository repository = CDOServerUtil.createRepository(repoName, store,	props);
		try {
			CDOServerUtil.addRepository(IPluginContainer.INSTANCE, repository);
		} catch (Throwable e) {
			System.err.println(e);
		}

		IPluginContainer.INSTANCE.getElement("org.eclipse.net4j.acceptors",	"tcp", "0.0.0.0:"+port);
		
		System.out.println(repository + " started!");
	}

}
