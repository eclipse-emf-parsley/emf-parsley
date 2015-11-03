package org.eclipse.emf.parsley.examples.maven;

import org.osgi.framework.Bundle;

import org.eclipse.emf.parsley.EmfParsleyExtensionFactory;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;

import com.google.inject.Injector;

public class MavenExecutableExtensionFactory extends
		EmfParsleyExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return MavenActivator.getDefault().getBundle();
	}

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return MavenActivator.getDefault().createModule();
	}

	@Override
	protected Injector getInjector() {
		return MavenActivator.getDefault().getInjector();
	}
}
