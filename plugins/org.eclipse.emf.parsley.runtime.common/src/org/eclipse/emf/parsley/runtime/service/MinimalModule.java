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
package org.eclipse.emf.parsley.runtime.service;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.inject.Binder;

/**
 * @author Lorenzo Bettini
 * 
 */
public class MinimalModule extends AbstractGenericModule {

	private final AbstractUIPlugin plugin;

	public MinimalModule(AbstractUIPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public void configure(Binder binder) {
		super.configure(binder);
		binder.bind(AbstractUIPlugin.class).toInstance(plugin);
		binder.bind(IDialogSettings.class).toInstance(
				plugin.getDialogSettings());
	}
}
