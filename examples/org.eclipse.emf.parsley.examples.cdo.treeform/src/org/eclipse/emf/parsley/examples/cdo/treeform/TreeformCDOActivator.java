package org.eclipse.emf.parsley.examples.cdo.treeform;

import org.osgi.framework.BundleContext;

import org.eclipse.emf.parsley.EmfComponentsGuiceModule;
import org.eclipse.emf.parsley.ui.EmfComponentsAbstractActivator;

/**
 * The activator class controls the plug-in life cycle
 */
public class TreeformCDOActivator extends EmfComponentsAbstractActivator {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.emf.parsley.examples.cdo.treeform"; //$NON-NLS-1$

	// The shared instance
	private static TreeformCDOActivator plugin;

	public TreeformCDOActivator() {
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
	public static TreeformCDOActivator getDefault() {
		return plugin;
	}

	/**
	 * Creates the EmfComponentsGuiceModule for this this plugin
	 *
	 * @return the EmfComponentsGuiceModule for this this plugin
	 */
	public EmfComponentsGuiceModule createModule() {
		return new TreeformGuiceModule(getDefault());
	}
}
