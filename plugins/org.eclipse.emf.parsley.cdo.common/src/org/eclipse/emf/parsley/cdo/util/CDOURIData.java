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

	private String server;

	private String repository;

	private String resource;

	public CDOURIData(String host, String sessionName, String resourceName) {
		super();
		this.server = host;
		this.repository = sessionName;
		this.resource = resourceName;
	}

	public String getServer() {
		return server;
	}

	public String getRepository() {
		return repository;
	}

	public String getResource() {
		return resource;
	}

	public static CDOURIData parse(URI uri) {
		String server = uri.authority();
		if (server == null) {
			throw new IllegalArgumentException("missing server in URI: " + uri);
		}
		String scheme = uri.scheme();
		if (!"cdo".equals(scheme)) {
			throw new IllegalArgumentException("not a cdo scheme: " + uri);
		}
		String repository = uri.segment(0);
		String resource = uri.segment(1);
		return new CDOURIData(server, repository, resource);
	}
}
