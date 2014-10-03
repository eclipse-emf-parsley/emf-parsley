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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.PlatformUI;

/**
 * This implementation of a {@link IResourceChangeListener} gets changes in the resource and apply them to the model instance. 
 * It works only if the resource is contained in the workspace.
 * 
 * @author Francesco Guidieri
 * @author Lorenzo Bettini - logging
 */
public class WorkspaceResourcesListener implements IResourceChangeListener{

	private ResourceSet resourceSet;
	
	private static final Logger LOGGER = Logger.getLogger(WorkspaceResourcesListener.class);

	public WorkspaceResourcesListener(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}
	public void resourceChanged(final IResourceChangeEvent event) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

			public void run() {
				manageEvent(event);
			}
		});
	}

	private void manageEvent(final IResourceChangeEvent event) {
		final IResourceDelta delta = event.getDelta();

		final ResourceDeltaVisitor visitor = new ResourceDeltaVisitor();
		try {
			delta.accept(visitor);
		} catch (final CoreException e) {
			LOGGER.error("manageEnvent", e);
		}

		final List<String> changedObjectUris = new ArrayList<String>();
		if (!visitor.getChangedResources().isEmpty()) {
			for (final Resource resource : visitor.getChangedResources()) {
				final String resourceUri = EcoreUtil.getURI(
						resource.getContents().get(0)).toString();
				changedObjectUris.add(resourceUri);
			}
		}

		aftertResourcesChanged(changedObjectUris);
	}

	/**
	 * This method is necessary to remove all listeners, it is intended to be called to prepare for garbage
	 */
	public void removeWorkspaceListener(){
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
	}
	
	protected void aftertResourcesChanged(List<String> changedObjectUris) {
		// the default implementation does nothing
	}

	private class ResourceDeltaVisitor implements IResourceDeltaVisitor {

		private final Collection<Resource> changedResources = new ArrayList<Resource>();

		public boolean visit(final IResourceDelta delta) {
			final int type = delta.getResource().getType();
			if (type == IResource.FILE) {
				if (delta.getKind() == IResourceDelta.CHANGED
						&& delta.getFlags() != IResourceDelta.MARKERS) {
					final String fullPath = delta.getFullPath().toString();
					if (!(fullPath.contains(".git") || fullPath
							.endsWith(".project"))) {
						final Resource resource = resourceSet.getResource(
								URI.createPlatformResourceURI(fullPath, true),
								true);
						reload(resource);
						this.changedResources.add(resource);
						return true;
					}
				}
			}
			return true;
		}

		private void reload(final Resource resource) {
			if (resource.isLoaded()) {
				resource.unload();
				try {
					resource.load(Collections.emptyMap());
				} catch (final IOException e) {
					LOGGER.error("reload", e);
				}
			}
			resourceSet.getResources().add(resource);
		}

		public Collection<Resource> getChangedResources() {
			return this.changedResources;
		}
	}

}
