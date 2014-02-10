/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Vincenzo Caselli - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.examples.eclipse4.parsleypart;

import org.osgi.framework.BundleContext;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.ui.EmfParsleyAbstractActivator;

/**
 * The activator class controls the plug-in life cycle
 */
public class ParsleypartActivator extends EmfParsleyAbstractActivator {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.emf.parsley.examples.eclipse4.parsleypart"; //$NON-NLS-1$

	// The shared instance
	private static ParsleypartActivator plugin;

	public ParsleypartActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static ParsleypartActivator getDefault() {
		return plugin;
	}

	/**
	 * Creates the EmfParsleyGuiceModule for this this plugin
	 *
	 * @return the EmfParsleyGuiceModule for this this plugin
	 */
	public EmfParsleyGuiceModule createModule() {
		return new ParsleypartGuiceModule(getDefault());
	}
}
