package org.eclipse.emf.parsley.examples.firstexample;

import org.eclipse.emf.parsley.EmfComponentsGuiceModule;
import org.eclipse.emf.parsley.ui.EmfComponentsAbstractActivator;
import org.osgi.framework.BundleContext;


/**
 * The activator class controls the plug-in life cycle
 */
public class FirstexampleActivator extends EmfComponentsAbstractActivator {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.emf.parsley.examples.firstexample"; //$NON-NLS-1$

	// The shared instance
	private static FirstexampleActivator plugin;

	public FirstexampleActivator() {
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
	public static FirstexampleActivator getDefault() {
		return plugin;
	}

	/**
	 * Creates the EmfComponentsGuiceModule for this this plugin
	 *
	 * @return the EmfComponentsGuiceModule for this this plugin
	 */
	public EmfComponentsGuiceModule createModule() {
		return new FirstexampleGuiceModule(getDefault());
	}
}
