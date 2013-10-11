package org.eclipse.emf.parsley.examples.mail.accountsview;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.ui.EmfParsleyAbstractActivator;
import org.osgi.framework.BundleContext;


/**
 * The activator class controls the plug-in life cycle
 */
public class AccountsviewActivator extends EmfParsleyAbstractActivator {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.emf.parsley.examples.mail.accountsview"; //$NON-NLS-1$

	// The shared instance
	private static AccountsviewActivator plugin;

	public AccountsviewActivator() {
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
	public static AccountsviewActivator getDefault() {
		return plugin;
	}

	/**
	 * Creates the EmfParsleyGuiceModule for this this plugin
	 *
	 * @return the EmfParsleyGuiceModule for this this plugin
	 */
	public EmfParsleyGuiceModule createModule() {
		return new AccountsviewGuiceModule(getDefault());
	}
}
