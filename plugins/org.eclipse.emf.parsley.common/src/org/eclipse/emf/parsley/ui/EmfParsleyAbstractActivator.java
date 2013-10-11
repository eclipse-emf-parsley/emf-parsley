/**
 * 
 */
package org.eclipse.emf.parsley.ui;

import static com.google.inject.Guice.createInjector;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.inject.Injector;

/**
 * An abstract activator useful for wizards to be used as the base class for
 * generated Java classes.
 * 
 * @author Lorenzo Bettini
 * 
 */
public abstract class EmfParsleyAbstractActivator extends AbstractUIPlugin {

	// the singleton Injector instance for this plugin
	private Injector injector;

	/**
	 * The constructor
	 */
	public EmfParsleyAbstractActivator() {
	}

	/**
	 * Returns the singleton Injector for this plugin
	 * 
	 * @return the singleton Injector for this plugin
	 */
	public Injector getInjector() {
		if (injector == null)
			injector = createInjector(createModule());
		return injector;
	}

	/**
	 * Creates the EmfParsleyGuiceModule for this this plugin
	 * 
	 * @return the EmfParsleyGuiceModule for this this plugin
	 */
	protected abstract EmfParsleyGuiceModule createModule();
}
