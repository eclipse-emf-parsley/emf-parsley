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
package org.eclipse.emf.parsley.generator.common

import static extension org.eclipse.emf.parsley.generator.common.EmfParsleyProjectFilesGeneratorUtil.*

/**
 * @author Lorenzo Bettini - initial API and implementation
 */
class EmfParsleyProjectFilesGenerator {

	def activatorName(String projectName)
	'''«projectName.buildClassNameFromProject»Activator'''

	def extFactoryName(String projectName)
	'''«projectName.buildClassNameFromProject»ExecutableExtensionFactory'''

	def generateActivator(String projectName)
'''
package «projectName»;

import org.osgi.framework.BundleContext;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * The activator class controls the plug-in life cycle
 */
public class «projectName.activatorName» extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "«projectName»"; //$NON-NLS-1$

	// The shared instance
	private static «projectName.activatorName» plugin;

	public «projectName.activatorName»() {
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
	public static «projectName.activatorName» getDefault() {
		return plugin;
	}

}
'''

}
