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

class EmfParsleyProjectFilesGenerator {

	def prefixFromProject(String projectName) {
		var prefixName = projectName
		val dotIndex = projectName.lastIndexOf(".")
		if (dotIndex > 0)
			prefixName = projectName.substring(dotIndex+1)
		return prefixName.toFirstUpper
	}

	def activatorName(String projectName)
	'''«projectName.prefixFromProject»Activator'''

	def moduleName(String projectName)
	'''«projectName.prefixFromProject»GuiceModule'''

	def extFactoryName(String projectName)
	'''«projectName.prefixFromProject»ExecutableExtensionFactory'''

	def generateManifest(String projectName)
'''
Manifest-Version: 1.0
Bundle-ManifestVersion: 2
Bundle-Name: «projectName»
Bundle-SymbolicName: «projectName»;singleton:=true
Bundle-Version: 1.0.0.qualifier
Bundle-Activator: «projectName».«projectName.activatorName»
Require-Bundle: org.eclipse.ui,
 org.eclipse.core.runtime,
 org.eclipse.emf.parsley,
 org.eclipse.emf.parsley.views
Bundle-ActivationPolicy: lazy
Bundle-RequiredExecutionEnvironment: JavaSE-1.6
'''

	def generateBuildProperties(boolean hasPluginXml)
'''
source.. = src/
output.. = bin/
bin.includes = META-INF/,\
               .«IF hasPluginXml»,\
               plugin.xml«ENDIF»
'''

	def generateActivator(String projectName)
'''
package «projectName»;

import org.osgi.framework.BundleContext;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.ui.EmfParsleyAbstractActivator;

/**
 * The activator class controls the plug-in life cycle
 */
public class «projectName.activatorName» extends EmfParsleyAbstractActivator {

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

	/**
	 * Creates the EmfParsleyGuiceModule for this this plugin
	 *
	 * @return the EmfParsleyGuiceModule for this this plugin
	 */
	public EmfParsleyGuiceModule createModule() {
		return new «projectName.moduleName»(getDefault());
	}
}
'''
	
	def generateExecutableExtensionFactory(String projectName)
'''
package «projectName»;

import org.osgi.framework.Bundle;

import org.eclipse.emf.parsley.EmfParsleyExtensionFactory;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;

import com.google.inject.Injector;

public class «projectName.extFactoryName» extends
		EmfParsleyExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return «projectName.activatorName».getDefault().getBundle();
	}

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return «projectName.activatorName».getDefault().createModule();
	}

	@Override
	protected Injector getInjector() {
		return «projectName.activatorName».getDefault().getInjector();
	}
}
'''

	def generateModule(String projectName, String superClass)
'''
package «projectName»;

import org.eclipse.ui.plugin.AbstractUIPlugin;

«IF superClass == "EmfParsleyGuiceModule"»
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
«ENDIF»

public class «projectName.moduleName» extends «superClass» {

	public «projectName.moduleName»(AbstractUIPlugin plugin) {
		super(plugin);
	}

}
'''
}
