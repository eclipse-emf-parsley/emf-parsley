/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.emf.parsley.edit;

import java.io.IOException;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * Handles saving of {@link Resource}
 * 
 * @author Lorenzo Bettini
 *
 */
public class ResourceSaveManager {

	public boolean save(Resource resource) throws IOException {
		return save(resource, null);
	}
	
	public boolean save(Resource resource, Map<?, ?> options) throws IOException {
		if (!precondition(resource))
			return false;
		resource.save(options);
		return true;
	}

	protected boolean precondition(Resource resource) {
		return true;
	}
}
