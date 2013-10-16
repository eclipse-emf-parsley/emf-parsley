/**
 * 
 */
package org.eclipse.emf.parsley.tests.factories;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.listeners.IEditorMouseListener;
import org.eclipse.emf.parsley.listeners.OpenFormBasedDialogMouseAdapter;
import org.eclipse.emf.parsley.tests.EmfParsleyTestsActivator;

/**
 * Opens a dialog on double click.
 * 
 * @author Lorenzo Bettini
 * 
 */
public class OpenFormDialogMouseEventExecutableExtensionFactory extends
		EmfParsleyTestsExecutableExtensionFactory {

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return new EmfParsleyGuiceModule(EmfParsleyTestsActivator.getDefault()) {

			@Override
			public Class<? extends IEditorMouseListener> bindIEditorMouseListener() {
				return OpenFormBasedDialogMouseAdapter.class;
			}

		};
	}

}
