/**
 * 
 */
package org.eclipse.emf.parsley.tests.factories.resourcelistening;

import org.eclipse.emf.parsley.tests.EmfParsleyTestsActivator;
import org.eclipse.emf.parsley.tests.factories.EmfParsleyTestsExecutableExtensionFactory;

import com.google.inject.Injector;

/**
 * Uses an editable table
 * 
 * @author fguidieri
 * 
 */
public class ResourceListeningLibraryExecutableExtensionFactory extends
		EmfParsleyTestsExecutableExtensionFactory {

	@Override
	public Injector getInjector() {
		return EmfParsleyTestsActivator.getDefault().getInjector(
				this.getClass());
	}

}
