package org.eclipse.emf.parsley.editors;

import org.eclipse.emf.parsley.EmfParsleyExtensionFactory;
import org.osgi.framework.Bundle;


public class EmfParsleyEditorsExecutableExtensionFactory extends
		EmfParsleyExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return EmfParsleyEditorsActivator.getDefault().getBundle();
	}



	
}
