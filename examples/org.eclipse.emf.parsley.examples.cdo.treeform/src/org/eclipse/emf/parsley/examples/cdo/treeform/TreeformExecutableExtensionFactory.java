package org.eclipse.emf.parsley.examples.cdo.treeform;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.cdo.CDOEmfParsleyExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

public class TreeformExecutableExtensionFactory extends
		CDOEmfParsleyExtensionFactory{

	@Override
	protected Bundle getBundle() {
		return TreeformCDOActivator.getDefault().getBundle();
	}

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return TreeformCDOActivator.getDefault().createModule();
	}

	@Override
	protected Injector getInjector() {
		return TreeformCDOActivator.getDefault().getInjector();
	}
}
