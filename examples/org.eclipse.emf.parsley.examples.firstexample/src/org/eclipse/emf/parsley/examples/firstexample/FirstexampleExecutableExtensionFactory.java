package org.eclipse.emf.parsley.examples.firstexample;

import org.eclipse.emf.parsley.EmfParsleyExtensionFactory;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.osgi.framework.Bundle;


import com.google.inject.Injector;

public class FirstexampleExecutableExtensionFactory extends
		EmfParsleyExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return FirstexampleActivator.getDefault().getBundle();
	}

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return FirstexampleActivator.getDefault().createModule();
	}

	@Override
	protected Injector getInjector() {
		return FirstexampleActivator.getDefault().getInjector();
	}
}
