package org.eclipse.emf.parsley.examples.firstexample;

import org.eclipse.emf.parsley.EmfComponentsExtensionFactory;
import org.eclipse.emf.parsley.EmfComponentsGuiceModule;
import org.osgi.framework.Bundle;


import com.google.inject.Injector;

public class FirstexampleExecutableExtensionFactory extends
		EmfComponentsExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return FirstexampleActivator.getDefault().getBundle();
	}

	@Override
	protected EmfComponentsGuiceModule getModule() {
		return FirstexampleActivator.getDefault().createModule();
	}

	@Override
	protected Injector getInjector() {
		return FirstexampleActivator.getDefault().getInjector();
	}
}
