/**
 * 
 */
package org.eclipse.emf.parsley.examples.mail.unifiedfoldersview;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * Acts as a container for folder; this is not part of the mail model but we can
 * represent it in a view.
 * 
 * @author Lorenzo Bettini
 * 
 */
public class UnifiedFolderContainer {

	private String containerName;
	private Resource resource;

	public UnifiedFolderContainer(String containerName, Resource resource) {
		super();
		this.containerName = containerName;
		this.resource = resource;
	}

	public String getContainerName() {
		return containerName;
	}

	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

}
