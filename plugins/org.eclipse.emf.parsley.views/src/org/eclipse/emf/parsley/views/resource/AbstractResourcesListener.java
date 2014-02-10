/*******************************************************************************
 * Copyright (c) 2013, 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.views.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.PlatformUI;

public abstract class AbstractResourcesListener {

	private IResourceChangeListener resourceChangeListener = new IResourceChangeListener() {

		public void resourceChanged(final IResourceChangeEvent event) {
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

				public void run() {
					manageEvent(event);
				}
			});
		}
	};
	private ResourceSet resourceSet;

	public AbstractResourcesListener(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	private void manageEvent(final IResourceChangeEvent event) {
		final IResourceDelta delta = event.getDelta();

		final ResourceDeltaVisitor visitor = new ResourceDeltaVisitor();
		try {
			delta.accept(visitor);
		} catch (final CoreException e) {
			e.printStackTrace();
		}

		final List<String> changedObjectUris = new ArrayList<String>();
		if (!visitor.getChangedResources().isEmpty()) {
			for (final Resource resource : visitor.getChangedResources()) {
				final String resourceUri = EcoreUtil.getURI(
						resource.getContents().get(0)).toString();
				changedObjectUris.add(resourceUri);
			}
		}

		resourcesChanged(changedObjectUris);
	}

	public abstract void resourcesChanged(List<String> changedObjectUris);

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
					resource.load(Collections.EMPTY_MAP);
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
			resourceSet.getResources().add(resource);
		}

		public Collection<Resource> getChangedResources() {
			return this.changedResources;
		}
	}

}
