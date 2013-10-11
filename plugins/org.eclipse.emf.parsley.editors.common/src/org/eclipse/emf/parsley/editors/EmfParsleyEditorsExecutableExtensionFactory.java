package org.eclipse.emf.parsley.editors;

import org.eclipse.emf.parsley.EmfComponentsExtensionFactory;
import org.osgi.framework.Bundle;


public class EmfComponentsEditorsExecutableExtensionFactory extends
		EmfComponentsExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return EmfComponentsEditorsActivator.getDefault().getBundle();
	}



	
}
