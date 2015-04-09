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

package org.eclipse.emf.parsley;

import org.eclipse.emf.parsley.runtime.ui.IImageHelper;
import org.eclipse.emf.parsley.runtime.ui.PluginImageHelper;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.inject.Binder;

/**
 * Default Google Guice bindings to be used in an OSGI - Plug-in environment.
 * 
 * @author Lorenzo Bettini
 * @author Francesco Guidieri - only Javadocs :-)
 * 
 */
public class EmfParsleyGuiceModule extends EmfParsleyJavaGuiceModule {

	private final AbstractUIPlugin plugin;

	public EmfParsleyGuiceModule(AbstractUIPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public void configure(Binder binder) {
		super.configure(binder);
		binder.bind(AbstractUIPlugin.class).toInstance(plugin);
		binder.bind(IDialogSettings.class).toInstance(
				plugin.getDialogSettings());
	}

	/**
	 * Use this binding to provide your own implementation of getImages methods.
	 * Default implementation is {@link PluginImageHelper}.
	 * @return a specialization of {@link IImageHelper}
	 * 
	 */
	@Override
	public Class<? extends IImageHelper> bindIImageHelper() {
		return PluginImageHelper.class;
	}

}
