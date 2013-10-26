package org.eclipse.emf.parsley;

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
		getDefault().getLog().log(status);
	}

	public static void logError(String errorMessage) {
		log(new Status(IStatus.ERROR, EmfParsleyActivator.PLUGIN_ID,
				errorMessage));
	}

	public static void log(Throwable exception) {
		log(new Status(IStatus.ERROR, EmfParsleyActivator.PLUGIN_ID,
				exception.getMessage(),
				exception));
	}
	
}
