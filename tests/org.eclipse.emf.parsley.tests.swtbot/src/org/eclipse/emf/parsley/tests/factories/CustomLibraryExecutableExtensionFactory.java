/**
 * 
 */
package org.eclipse.emf.parsley.tests.factories;

import org.eclipse.emf.parsley.tests.EmfParsleyTestsActivator;

import com.google.inject.Injector;

/**
 * Uses a custom label provider
 * 
 * @author bettini
 * 
 */
public class CustomLibraryExecutableExtensionFactory extends
		EmfParsleyTestsExecutableExtensionFactory {

	@Override
	public Injector getInjector() {
		return EmfParsleyTestsActivator.getDefault().getInjector(
				this.getClass());
	}

}
