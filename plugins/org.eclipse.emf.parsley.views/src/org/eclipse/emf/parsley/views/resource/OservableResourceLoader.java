package org.eclipse.emf.parsley.views.resource;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.parsley.resource.ResourceLoader;

public class OservableResourceLoader extends ResourceLoader {
	
	private AbstractResourcesListener resourceChangeListener;

	@Override
	public Resource getResource(ResourceSet resourceSet, URI resourceURI) {
		Resource resource = super.getResource(resourceSet, resourceURI);
		resourceChangeListener = new AbstractResourcesListener(resourceSet) {
			
			@Override
			public void resourcesChanged(List<String> changedObjectUris) {
				// TODO Auto-generated method stub
				
			}
		};
		AbstractResourcesListener.registerInterestInProteoResources(resourceChangeListener);
		return resource;
	}

}
