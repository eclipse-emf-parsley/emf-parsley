package org.eclipse.emf.parsley.examples.library;

import org.eclipse.emf.parsley.EmfComponentsExtensionFactory;
import org.eclipse.emf.parsley.EmfComponentsGuiceModule;
import org.osgi.framework.Bundle;


public class ExecutableExtensionFactory extends
		EmfComponentsExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return Activator.getDefault().getBundle();
	}

	@Override
	protected EmfComponentsGuiceModule getModule() {
		return new EmfComponentsGuiceModule(Activator.getDefault());
	}

}
