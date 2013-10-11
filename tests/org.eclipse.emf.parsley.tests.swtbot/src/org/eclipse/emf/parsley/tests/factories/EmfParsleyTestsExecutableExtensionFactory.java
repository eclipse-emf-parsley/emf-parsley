/**
 * 
 */
package org.eclipse.emf.parsley.tests.factories;


import org.eclipse.emf.parsley.EmfParsleyExtensionFactory;
import org.eclipse.emf.parsley.tests.EmfParsleyTestsActivator;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

/**
 * Uses a custom label provider
 * 
 * @author bettini
 * 
 */
public class EmfParsleyTestsExecutableExtensionFactory extends
		EmfParsleyExtensionFactory {

	/**
	 * We override it since we use guice to load a class from this very plugin.
	 * 
	 * @see org.eclipse.emf.parsley.EmfParsleyExtensionFactory#getBundle()
	 */
	@Override
	protected Bundle getBundle() {
		return EmfParsleyTestsActivator.getDefault().getBundle();
	}

	@Override
	public Injector getInjector() {
		return super.getInjector();
	}

}
