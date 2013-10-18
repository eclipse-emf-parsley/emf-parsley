/**
 * 
 */
package org.eclipse.emf.parsley.cdo;


import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.view.CDOAdapterPolicy;
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

		if (!t.hasResource(CDOURIData.parse(resourceURI).resource)) {
			return null;
		}
		CDOResource resource = t.getResource(CDOURIData.parse(resourceURI).resource, true);
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
		
		CDOResource resource = t.getOrCreateResource(CDOURIData.parse(resourceURI).resource);

		resourceSet.getResources().add(resource);

		return resource;
	}
	

	private CDOTransaction openTransaction(URI resourceURI){
		Net4jUtil.prepareContainer(IPluginContainer.INSTANCE);
		TCPUtil.prepareContainer(IPluginContainer.INSTANCE);
		
		CDOURIData data = CDOURIData.parse(resourceURI);
		
		String server = data.server;
		String repository = data.repository;
		
		CDOSession cdoSession = sessionManager.getSession(server,repository);
		
		CDOTransaction transaction = cdoSession.openTransaction();
		transaction.options().addChangeSubscriptionPolicy(CDOAdapterPolicy.ALL);
		return transaction;
	}
}