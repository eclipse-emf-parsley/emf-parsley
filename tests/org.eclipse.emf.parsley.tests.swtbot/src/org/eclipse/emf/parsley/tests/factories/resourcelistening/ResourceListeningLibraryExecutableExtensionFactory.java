/**
 * 
 */
package org.eclipse.emf.parsley.tests.factories.resourcelistening;

import org.eclipse.emf.parsley.tests.EmfComponentsTestsActivator;
import org.eclipse.emf.parsley.tests.factories.EmfComponentsTestsExecutableExtensionFactory;

import com.google.inject.Injector;

/**
 * Uses an editable table
 * 
 * @author fguidieri
 * 
 */
public class ResourceListeningLibraryExecutableExtensionFactory extends
		EmfComponentsTestsExecutableExtensionFactory {

	@Override
	public Injector getInjector() {
		return EmfComponentsTestsActivator.getDefault().getInjector(
				this.getClass());
	}

}
