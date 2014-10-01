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

package org.eclipse.emf.parsley.cdo.util;

import org.eclipse.emf.common.util.URI;

/**
 * @author Lorenzo Bettini
 * 
 */
public class CDOURIData {

	public String server;

	public String repository;

	public String resource;

	public CDOURIData(String host, String sessionName, String resourceName) {
		super();
		this.server = host;
		this.repository = sessionName;
		this.resource = resourceName;
	}

	public static CDOURIData parse(URI uri) {
		String server = uri.authority();
		if (server == null) {
			throw new IllegalArgumentException("missing server in URI: " + uri);
		}
		String scheme = uri.scheme();
		if (scheme == null || !scheme.equals("cdo")) {
			throw new IllegalArgumentException("not a cdo scheme: " + uri);
		}
		String repository = uri.segment(0);
		String resource = uri.segment(1);
		return new CDOURIData(server, repository, resource);
	}
}
