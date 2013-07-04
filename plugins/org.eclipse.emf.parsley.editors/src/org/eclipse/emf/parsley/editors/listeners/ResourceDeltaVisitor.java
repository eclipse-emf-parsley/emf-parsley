/**
 * 
 */
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

	protected Collection<Resource> changedResources = new ArrayList<Resource>();

	protected Collection<Resource> removedResources = new ArrayList<Resource>();

	public void init(ResourceSet resourceSet,
			Collection<Resource> savedResources) {
		this.resourceSet = resourceSet;
		this.savedResources = savedResources;
	}

	public boolean visit(IResourceDelta delta) {
		if (delta.getResource().getType() == IResource.FILE) {
			if (delta.getKind() == IResourceDelta.REMOVED
					|| delta.getKind() == IResourceDelta.CHANGED
					&& delta.getFlags() != IResourceDelta.MARKERS) {
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
		}

		return true;
	}

	public Collection<Resource> getChangedResources() {
		return changedResources;
	}

	public Collection<Resource> getRemovedResources() {
		return removedResources;
	}
}