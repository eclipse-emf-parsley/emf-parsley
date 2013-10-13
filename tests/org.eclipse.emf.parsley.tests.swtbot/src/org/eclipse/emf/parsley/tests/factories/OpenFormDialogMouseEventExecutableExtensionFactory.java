/**
 * 
 */
package org.eclipse.emf.parsley.tests.factories;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.listeners.ViewerMouseAdapter;
import org.eclipse.emf.parsley.listeners.ViewerOpenFormBasedDialogMouseAdapter;
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
		return new EmfParsleyGuiceModule(
				EmfParsleyTestsActivator.getDefault()) {

			@Override
			public Class<? extends ViewerMouseAdapter> bindViewerMouseAdapter() {
				return ViewerOpenFormBasedDialogMouseAdapter.class;
			}

		};
	}

}
