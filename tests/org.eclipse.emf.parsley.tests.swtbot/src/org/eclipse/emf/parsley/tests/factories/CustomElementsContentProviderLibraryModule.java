/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.factories;


import org.eclipse.emf.parsley.tests.providers.CustomElementsLibraryViewerContentProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public final class CustomElementsContentProviderLibraryModule extends
		CustomLibraryModule {
	public CustomElementsContentProviderLibraryModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	@Override
	public Class<? extends IContentProvider> bindIContentProvider() {
		return CustomElementsLibraryViewerContentProvider.class;
	}
}