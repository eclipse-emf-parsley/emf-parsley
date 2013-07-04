/**
 * 
 */
package org.eclipse.emf.parsley.tests.factories;

import org.eclipse.emf.parsley.EmfComponentsGuiceModule;
import org.eclipse.emf.parsley.listeners.ViewerMouseAdapter;
import org.eclipse.emf.parsley.listeners.ViewerNoOpMouseAdapter;
import org.eclipse.emf.parsley.tests.EmfComponentsTestsActivator;



/**
 * Uses a mouse adapter that does not react to mouse events.
 * 
 * @author bettini
 * 
 */
public class NoMouseEventExecutableExtensionFactory extends
		EmfComponentsTestsExecutableExtensionFactory {

	@Override
	protected EmfComponentsGuiceModule getModule() {
		return new EmfComponentsGuiceModule(
				EmfComponentsTestsActivator.getDefault()) {

			@Override
			public Class<? extends ViewerMouseAdapter> bindViewerMouseAdapter() {
				return ViewerNoOpMouseAdapter.class;
			}

		};
	}

}
