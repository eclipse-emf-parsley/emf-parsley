/**
 * 
 */
package org.eclipse.emf.parsley.tests.factories;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.edit.ActOnCopyEditingStrategy;
import org.eclipse.emf.parsley.edit.IEditingStrategy;
import org.eclipse.emf.parsley.listeners.IEditorMouseListener;
import org.eclipse.emf.parsley.listeners.OpenDialogMouseAdapter;
import org.eclipse.emf.parsley.tests.EmfParsleyTestsActivator;



/**
 * The editing takes place on a copy of the object.
 * 
 * @author Lorenzo Bettini
 * 
 */
public class ActOnCopyEditingStrategyExecutableExtensionFactory extends
		EmfParsleyTestsExecutableExtensionFactory {

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return new EmfParsleyGuiceModule(
				EmfParsleyTestsActivator.getDefault()) {
			
			@Override
			public Class<? extends IEditorMouseListener> bindIEditorMouseListener() {
				return OpenDialogMouseAdapter.class;
			}

			@Override
			public Class<? extends IEditingStrategy> bindIEditingStrategy() {
				return ActOnCopyEditingStrategy.class;
			}

		};
	}

}
