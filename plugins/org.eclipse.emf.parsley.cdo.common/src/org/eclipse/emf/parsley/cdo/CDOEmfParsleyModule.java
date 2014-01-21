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
package org.eclipse.emf.parsley.cdo;


import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.resource.ResourceLoader;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * @author Lorenzo Bettini
 *
 */
public class CDOEmfParsleyModule extends EmfParsleyGuiceModule {

	public CDOEmfParsleyModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	@Override
	public Class<? extends ResourceLoader> bindResourceLoader() {
		return CDOResourceLoader.class;
	}
	
	public Class<? extends CDOSessionManager> bindCDOSessionManager(){
		return CDOSessionManager.class;
	}
	
}
