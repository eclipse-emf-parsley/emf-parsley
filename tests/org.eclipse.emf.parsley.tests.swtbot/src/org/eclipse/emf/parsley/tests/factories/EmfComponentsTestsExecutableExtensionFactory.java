/**
 * 
 */
package org.eclipse.emf.parsley.tests.factories;


import org.eclipse.emf.parsley.EmfComponentsExtensionFactory;
import org.eclipse.emf.parsley.tests.EmfComponentsTestsActivator;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

/**
 * Uses a custom label provider
 * 
 * @author bettini
 * 
 */
public class EmfComponentsTestsExecutableExtensionFactory extends
		EmfComponentsExtensionFactory {

	/**
	 * We override it since we use guice to load a class from this very plugin.
	 * 
	 * @see org.eclipse.emf.parsley.EmfComponentsExtensionFactory#getBundle()
	 */
	@Override
	protected Bundle getBundle() {
		return EmfComponentsTestsActivator.getDefault().getBundle();
	}

	@Override
	public Injector getInjector() {
		return super.getInjector();
	}

}
