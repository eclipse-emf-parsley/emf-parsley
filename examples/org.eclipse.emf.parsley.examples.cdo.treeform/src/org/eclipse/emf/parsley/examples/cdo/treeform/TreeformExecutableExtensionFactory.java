package org.eclipse.emf.parsley.examples.cdo.treeform;

import org.eclipse.emf.parsley.EmfComponentsGuiceModule;
import org.eclipse.emf.parsley.cdo.CDOEmfComponentsExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

public class TreeformExecutableExtensionFactory extends
		CDOEmfComponentsExtensionFactory{

	@Override
	protected Bundle getBundle() {
		return TreeformCDOActivator.getDefault().getBundle();
	}

	@Override
	protected EmfComponentsGuiceModule getModule() {
		return TreeformCDOActivator.getDefault().createModule();
	}

	@Override
	protected Injector getInjector() {
		return TreeformCDOActivator.getDefault().getInjector();
	}
}
