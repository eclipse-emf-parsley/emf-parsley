/*******************************************************************************
 * Copyright (c) 2013, 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.views.resource;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.parsley.resource.ResourceLoader;

public class ObservableResourceLoader extends ResourceLoader {
	
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
		return resource;
	}

}
