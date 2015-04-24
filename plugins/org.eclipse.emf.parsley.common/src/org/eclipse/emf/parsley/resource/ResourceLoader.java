/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.resource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;

import com.google.inject.Inject;

/**
 * ResourceLoader is responsible for loading a {@link Resource}.
 * 
 * @author Lorenzo Bettini
 */
public class ResourceLoader {

	@Inject
	private ResourceManager resourceManager;

	public Resource getResource(ResourceSet resourceSet, URI resourceURI) {
		Resource resource = resourceSet.getResource(resourceURI, true);
		initializeEmptyResource(resource);
		return resource;
	}

	protected ResourceManager getResourceManager() {
		return resourceManager;
	}
	
	/**
	 * Tries to load the resource using the resource set of the editing domain
	 * and returns a {@link LoadResourceResponse}.
	 * 
	 * @param editingDomain
	 * @param resourceURI
	 * @return
	 */
	public LoadResourceResponse getResource(
			AdapterFactoryEditingDomain editingDomain, URI resourceURI) {
		Resource resource = null;
		Exception exception = null;
		try {
			// Load the resource through the editing domain.
			resource = getResource(editingDomain.getResourceSet(), resourceURI);
		} catch (Exception e) {
			exception = e;
			resource = editingDomain.getResourceSet().getResource(resourceURI,
					false);
			initializeEmptyResource(resource);
		}

		return new LoadResourceResponse(resource, exception);
	}

	protected void initializeEmptyResource(Resource resource) {
		if (resource != null && resource.getContents().isEmpty()) {
			getResourceManager().initialize(resource);
		}
	}
}
