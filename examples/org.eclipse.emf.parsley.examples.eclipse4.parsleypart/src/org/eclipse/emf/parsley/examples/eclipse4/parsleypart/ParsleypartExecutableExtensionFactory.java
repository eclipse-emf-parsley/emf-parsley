package org.eclipse.emf.parsley.examples.eclipse4.parsleypart;

import org.osgi.framework.Bundle;

import org.eclipse.emf.parsley.EmfParsleyExtensionFactory;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;

import com.google.inject.Injector;

public class ParsleypartExecutableExtensionFactory extends
		EmfParsleyExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return ParsleypartActivator.getDefault().getBundle();
	}

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return ParsleypartActivator.getDefault().createModule();
	}

	@Override
	protected Injector getInjector() {
		return ParsleypartActivator.getDefault().getInjector();
	}
}
