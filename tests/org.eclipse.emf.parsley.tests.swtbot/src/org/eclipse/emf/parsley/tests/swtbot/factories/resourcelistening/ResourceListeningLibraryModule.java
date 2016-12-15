/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.swtbot.factories.resourcelistening;


import org.eclipse.emf.parsley.resource.ResourceLoader;
import org.eclipse.emf.parsley.tests.swtbot.factories.CustomLibraryModule;
import org.eclipse.emf.parsley.views.resource.ObserverResourceLoader;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public final class ResourceListeningLibraryModule extends
		CustomLibraryModule {
	
	public ResourceListeningLibraryModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	@Override
	public Class<? extends ResourceLoader> bindResourceLoader() {
		return ObserverResourceLoader.class;
	}
	
}