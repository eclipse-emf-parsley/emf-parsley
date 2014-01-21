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
package org.eclipse.emf.parsley.resource;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * @author Lorenzo Bettini
 * 
 */
public class LoadResourceResponse {

	protected Resource resource;

	protected Exception exception;

	public LoadResourceResponse(Resource resource, Exception exception) {
		super();
		this.resource = resource;
		this.exception = exception;
	}

	public Resource getResource() {
		return resource;
	}

	public Exception getException() {
		return exception;
	}

}
