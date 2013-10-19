package org.eclipse.emf.parsley.examples.mail.unifiedfoldersview;

import org.osgi.framework.Bundle;

import org.eclipse.emf.parsley.EmfParsleyExtensionFactory;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;

import com.google.inject.Injector;

public class UnifiedfoldersviewExecutableExtensionFactory extends
		EmfParsleyExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return UnifiedfoldersviewActivator.getDefault().getBundle();
	}

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return UnifiedfoldersviewActivator.getDefault().createModule();
	}

	@Override
	protected Injector getInjector() {
		return UnifiedfoldersviewActivator.getDefault().getInjector();
	}
}
