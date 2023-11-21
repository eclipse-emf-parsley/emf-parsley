/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.resource;

import java.io.IOException;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * ResourceManager is responsible for tasks concerning a {@link Resource}.
 *
 * @author Lorenzo Bettini - initial API and implementation
 *
 */
public class ResourceManager {

	/**
	 * This method is called by the framework when a loaded resource has to be
	 * initialized; subclasses can redefine it in order to put some contents in
	 * the empty resource.
	 *
	 * The default implementation is empty
	 *
	 * @param resource
	 */
	public void initialize(Resource resource) {
		// the default implementation does nothing
	}

	/**
	 * Saves the passed {@link Resource} and returns whether the resource
	 * has been actually saved.
	 *
	 * @param resource
	 * @return
	 * @throws IOException
	 */
	public boolean save(Resource resource) throws IOException {
		resource.save(null);
		return true;
	}
}
