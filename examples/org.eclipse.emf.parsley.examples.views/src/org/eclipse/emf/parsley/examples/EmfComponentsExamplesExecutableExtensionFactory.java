package org.eclipse.emf.parsley.examples;


import org.eclipse.emf.parsley.EmfComponentsExtensionFactory;
import org.eclipse.emf.parsley.EmfComponentsGuiceModule;
import org.osgi.framework.Bundle;

public class EmfComponentsExamplesExecutableExtensionFactory extends
		EmfComponentsExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return Activator.getDefault().getBundle();
	}

	@Override
	protected EmfComponentsGuiceModule getModule() {
		return new EmfComponentsExamplesModule(Activator.getDefault());
	}

}
