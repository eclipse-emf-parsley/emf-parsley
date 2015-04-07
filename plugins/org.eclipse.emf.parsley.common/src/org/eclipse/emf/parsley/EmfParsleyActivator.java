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

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class EmfParsleyActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.emf.parsley"; //$NON-NLS-1$

	// The shared instance
	private static EmfParsleyActivator plugin;
	
	private static final Logger LOGGER = Logger.getLogger(EmfParsleyActivator.class);

	/**
	 * The constructor
	 */
	public EmfParsleyActivator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static EmfParsleyActivator getDefault() {
		return plugin;
	}

	public static void log(Status status) {
		EmfParsleyActivator p = getDefault();
		if (p != null) {
			p.getLog().log(status);
		} else {
			LOGGER.error(status.toString());
		}
	}

	public static void logError(String errorMessage) {
		log(new Status(IStatus.ERROR, EmfParsleyActivator.PLUGIN_ID,
				errorMessage));
	}

	public static void logError(String errorMessage, Throwable exception) {
		LOGGER.error(errorMessage, exception);
	}

	public static void log(Throwable exception) {
		log(new Status(IStatus.ERROR, EmfParsleyActivator.PLUGIN_ID,
				exception.getMessage(),
				exception));
	}
	
}
