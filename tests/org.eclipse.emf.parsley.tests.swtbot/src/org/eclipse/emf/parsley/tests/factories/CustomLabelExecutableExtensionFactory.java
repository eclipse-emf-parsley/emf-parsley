/**
 * 
 */
package org.eclipse.emf.parsley.tests.factories;


import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.tests.EmfParsleyTestsActivator;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;

import com.google.inject.Inject;

/**
 * Uses a custom label provider
 * 
 * @author bettini
 * 
 */
public class CustomLabelExecutableExtensionFactory extends
		EmfParsleyTestsExecutableExtensionFactory {

	public static class CustomLabelProvider extends ViewerLabelProvider {
		@Inject
		public CustomLabelProvider(AdapterFactoryLabelProvider delegate) {
			super(delegate);
		}

		@Override
		public String getText(Object element) {
			if (element instanceof XMLResource) {
				return super.getText(element);
			}
			return "TEST " + super.getText(element) + " ENDTEST";
		}
	}

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return new EmfParsleyGuiceModule(EmfParsleyTestsActivator.getDefault()) {

			@Override
			public Class<? extends ILabelProvider> bindILabelProvider() {
				return CustomLabelProvider.class;
			}

		};
	}

}
