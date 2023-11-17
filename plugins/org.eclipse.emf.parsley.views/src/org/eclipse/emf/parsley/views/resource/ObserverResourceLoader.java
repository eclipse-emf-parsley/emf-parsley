/*******************************************************************************
 * Copyright (c) 2013, 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Lorenzo Bettini, Francesco Guidieri - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.views.resource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.parsley.resource.ResourceLoader;

/**
 * This Resource Loader implements a resource changes listening mechanism, if the resource is contained in the workspace.
 *
 * @author Francesco Guidieri
 *
 */
public class ObserverResourceLoader extends ResourceLoader {

	private WorkspaceResourcesListener resourceListener;

	@Override
	public Resource getResource(ResourceSet resourceSet, URI resourceURI) {
		Resource resource = super.getResource(resourceSet, resourceURI);
		resourceListener = new WorkspaceResourcesListener(resourceSet);
		return resource;
	}

	public void removeListener(){
		resourceListener.removeWorkspaceListener();
	}

}
