package library.business;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.net4j.CDONet4jSessionConfiguration;
import org.eclipse.emf.cdo.net4j.CDONet4jUtil;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.util.CDOURIUtil;
import org.eclipse.emf.cdo.view.CDOAdapterPolicy;
import org.eclipse.emf.cdo.view.CDOView;
import org.eclipse.emf.common.util.URI;
import org.eclipse.net4j.Net4jUtil;
import org.eclipse.net4j.connector.IConnector;
import org.eclipse.net4j.tcp.TCPUtil;
import org.eclipse.net4j.util.container.IPluginContainer;
import org.eclipse.net4j.util.lifecycle.ILifecycle;
import org.eclipse.net4j.util.lifecycle.LifecycleEventAdapter;
import org.eclipse.net4j.util.security.PasswordCredentialsProvider;

public class CommonBusiness {

	public static final String SERVER_CONNECTION_ERROR = "Server connection error";

	private static String port="2036";

	private static String server="localhost";

	private static String user;

	private static String password;
	
	
	private static final String REPOSITORY_DEMO = "demo";

	static Map<String, CDOResource> resourceMap=new HashMap<String, CDOResource>();

	//	static CDOSessionConfiguration configuration;
	static CDOSession session;

	static {
		Net4jUtil.prepareContainer(IPluginContainer.INSTANCE);
		TCPUtil.prepareContainer(IPluginContainer.INSTANCE);

		// OMPlatform.INSTANCE.addLogHandler(PrintLogHandler.CONSOLE);
		// OMPlatform.INSTANCE.addTraceHandler(PrintTraceHandler.CONSOLE);
		// OMPlatform.INSTANCE.setDebugging(true);
	}

	public static CDOSession getSessionForTest(String host) {
		return session=openSession(host, REPOSITORY_DEMO);
	}

	public static org.eclipse.emf.cdo.session.CDOSession getSession() {
		if(session==null){
			try{
			session=openSession(getHostFromPreferences(), REPOSITORY_DEMO);
			}catch(RuntimeException exception){
				throw new RuntimeException( "Error connecting "+server+":"+port+". "+exception.getLocalizedMessage());
			}
		}
		return session;
	}



	public static String getHostFromPreferences() {
		return server;
	}

	
	public static synchronized CDOResource getResource(String resourceName){
		if(!resourceMap.containsKey(resourceName)){
			CDOView view = CommonBusiness.getSession().openView();
			view.options().addChangeSubscriptionPolicy(CDOAdapterPolicy.ALL);
			CDOResource resource = view.getResource(resourceName);
			resourceMap.put(resourceName, resource);
		}
		return resourceMap.get(resourceName);
	}

	public static URI createResourceURI(String resourcePath) {
		return CDOURIUtil.createResourceURI(session, resourcePath);
	}
	

	

	public static String getCDOServerUri(){
		return "cdo://"+server+":"+port+"/demo";
	}

	private static CDOSession openSession(String host, String repoName) {
		final IConnector connector = (IConnector) IPluginContainer.INSTANCE
				.getElement( //
						"org.eclipse.net4j.connectors", // Product group
						"tcp", // Type
						host+":"+port); 

		CDONet4jSessionConfiguration config = CDONet4jUtil.createNet4jSessionConfiguration();
		config.setConnector(connector);
		config.setRepositoryName(repoName);

		if(user!=null && password!=null){
			PasswordCredentialsProvider credentialsProvider = new PasswordCredentialsProvider(user, password);
			config.setCredentialsProvider(credentialsProvider);
		}
		CDOSession session = config.openNet4jSession();

		session.addListener(new LifecycleEventAdapter() {
			@Override
			protected void onDeactivated(ILifecycle lifecycle) {
				connector.close();
			}
		});

		return session;
	}
	
	public static void setPort(String port) {
		invalidateSession();
		CommonBusiness.port = port;
	}

	private static void invalidateSession() {
		closeSession();
		session=null;
	}

	public static void setServer(String server) {
		invalidateSession();
		CommonBusiness.server = server;
	}

	public static void setUser(String user) {
		invalidateSession();
		CommonBusiness.user = user;
	}

	public static void setPassword(String password) {
		invalidateSession();
		CommonBusiness.password = password;
	}

	public static void closeSession() {
		if(session!=null){
			session.close();
		}
	}
}
