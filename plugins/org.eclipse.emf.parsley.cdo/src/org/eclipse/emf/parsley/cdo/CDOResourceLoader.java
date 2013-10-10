/**
 * 
 */
package org.eclipse.emf.parsley.cdo;


import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.view.CDOAdapterPolicy;
import org.eclipse.emf.cdo.view.CDOViewInvalidationEvent;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.parsley.cdo.util.CDOURIData;
import org.eclipse.emf.parsley.resource.EmptyResourceInitializer;
import org.eclipse.emf.parsley.resource.LoadResourceResponse;
import org.eclipse.emf.parsley.resource.ResourceLoader;
import org.eclipse.emf.spi.cdo.CDOMergingConflictResolver;
import org.eclipse.net4j.Net4jUtil;
import org.eclipse.net4j.tcp.TCPUtil;
import org.eclipse.net4j.util.container.IPluginContainer;
import org.eclipse.net4j.util.event.IEvent;
import org.eclipse.net4j.util.event.IListener;

import com.google.inject.Inject;

/**
 * @author bettini
 * 
 */
public class CDOResourceLoader extends ResourceLoader {

	@Inject private EmptyResourceInitializer emptyResourceInitializer;
	@Inject private CDOSessionManager sessionManager;
	
	@Override
	public Resource getResource(ResourceSet resourceSet, URI resourceURI) {
		CDOTransaction t =openTransaction(resourceURI);
		t.options().addConflictResolver(new CDOMergingConflictResolver());
		t.options().addChangeSubscriptionPolicy(CDOAdapterPolicy.ALL);
		t.addListener(new IListener() {
			
			public void notifyEvent(IEvent event) {
				if (event instanceof CDOViewInvalidationEvent) {
					CDOViewInvalidationEvent cdoViewInvalidationEvent = (CDOViewInvalidationEvent) event;
					System.out.println(cdoViewInvalidationEvent.getDirtyObjects());
					System.out.println();
				}
			}
		});
		if (!t.hasResource(CDOURIData.parse(resourceURI).resourceName)) {
			return null;
		}
		CDOResource resource = t.getResource(CDOURIData.parse(resourceURI).resourceName, true);
		resourceSet.getResources().add(resource);
		return resource;
	}

	public LoadResourceResponse getResource(AdapterFactoryEditingDomain editingDomain,
			URI resourceURI) {
		Resource resource = null;
		Exception exception = null;

		try {
			// Load the resource through the editing domain.
			resource = getResource(editingDomain.getResourceSet(), resourceURI);
			if(resource==null){
				resource = createResource(editingDomain.getResourceSet(), resourceURI);
				emptyResourceInitializer.initialize(resource);
				((CDOTransaction)((CDOResource)resource).cdoView()).commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exception = e;
		}

		return new LoadResourceResponse(resource, exception);
	}
	
	public Resource createResource(ResourceSet resourceSet, URI resourceURI) {
		CDOTransaction t =openTransaction(resourceURI);
		
		CDOResource resource = t.getOrCreateResource(CDOURIData.parse(resourceURI).resourceName);

		resourceSet.getResources().add(resource);

		return resource;
	}
	
//	private static CDONet4jSession openSession(String repoName, String host) {
//		final IConnector connector = (IConnector) IPluginContainer.INSTANCE
//				.getElement( //
//						"org.eclipse.net4j.connectors", // Product group
//						"tcp", // Type
//						host); // Description
//
//		CDONet4jSessionConfiguration config = CDONet4jUtil.createNet4jSessionConfiguration();
//		config.setConnector(connector);
//		config.setRepositoryName(repoName);
//
//		CDONet4jSession session = config.openNet4jSession();
//
//		session.addListener(new LifecycleEventAdapter() {
//			@Override
//			protected void onDeactivated(ILifecycle lifecycle) {
//				connector.close();
//			}
//		});
//
//		return session;
//	}

	private CDOTransaction openTransaction(URI resourceURI){
		Net4jUtil.prepareContainer(IPluginContainer.INSTANCE);
		TCPUtil.prepareContainer(IPluginContainer.INSTANCE);
		
//		CDOURIData data = CDOURIData.parse(resourceURI);
		
//		String host = data.host;
//		String repoName = data.sessionName;
//		String resourceName = data.resourceName;
		
//		CDONet4jSession cdoSession = openSession(repoName, host);
		CDOSession cdoSession =sessionManager.getSession(null);
		
		CDOTransaction transaction = cdoSession.openTransaction();
		transaction.options().addChangeSubscriptionPolicy(CDOAdapterPolicy.ALL);
		return transaction;
	}
}
