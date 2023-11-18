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
package org.eclipse.emf.parsley.editors.listeners;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Extracted and adapted from EmfAbstractEditor
 *
 * @author Lorenzo Bettini
 *
 */
public class ResourceDeltaVisitor implements IResourceDeltaVisitor {
	protected ResourceSet resourceSet;

	protected Collection<Resource> savedResources;

	protected Collection<Resource> changedResources = new ArrayList<>();

	protected Collection<Resource> removedResources = new ArrayList<>();

	public void init(ResourceSet resourceSet,
			Collection<Resource> savedResources) {
		this.resourceSet = resourceSet;
		this.savedResources = savedResources;
	}

	@Override
	public boolean visit(IResourceDelta delta) {
		if (delta.getResource().getType() == IResource.FILE && deltaShouldBeHandled(delta)) {
			// see whether it's a resource of our resource set
			Resource resource = resourceSet.getResource(URI
					.createPlatformResourceURI(delta.getFullPath()
							.toString(), true), false);
			if (resource != null) {
				if (delta.getKind() == IResourceDelta.REMOVED) {
					removedResources.add(resource);
				} else if (!savedResources.remove(resource)) {
					changedResources.add(resource);
				}
			}
		}

		return true;
	}

	private boolean deltaShouldBeHandled(IResourceDelta delta) {
		return delta.getKind() == IResourceDelta.REMOVED
				|| delta.getKind() == IResourceDelta.CHANGED
				&& delta.getFlags() != IResourceDelta.MARKERS;
	}

	public Collection<Resource> getChangedResources() {
		return changedResources;
	}

	public Collection<Resource> getRemovedResources() {
		return removedResources;
	}
}