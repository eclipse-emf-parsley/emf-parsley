/**
 * 
 */
package org.eclipse.emf.parsley.tests.factories;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.listeners.IEditorMouseListener;
import org.eclipse.emf.parsley.listeners.NoOpMouseAdapter;
import org.eclipse.emf.parsley.tests.EmfParsleyTestsActivator;

/**
 * Uses a mouse adapter that does not react to mouse events.
 * 
 * @author bettini
 * 
 */
public class NoMouseEventExecutableExtensionFactory extends
		EmfParsleyTestsExecutableExtensionFactory {

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return new EmfParsleyGuiceModule(EmfParsleyTestsActivator.getDefault()) {

			@Override
			public Class<? extends IEditorMouseListener> bindIEditorMouseListener() {
				return NoOpMouseAdapter.class;
			}

		};
	}

}
