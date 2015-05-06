package org.eclipse.emf.parsley.tests.views;

import org.osgi.framework.Bundle;

import org.eclipse.emf.parsley.EmfParsleyExtensionFactory;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;

import com.google.inject.Injector;

public class ViewsExecutableExtensionFactory extends
		EmfParsleyExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return ViewsActivator.getDefault().getBundle();
	}

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return ViewsActivator.getDefault().createModule();
	}

	@Override
	protected Injector getInjector() {
		return ViewsActivator.getDefault().getInjector();
	}
}
