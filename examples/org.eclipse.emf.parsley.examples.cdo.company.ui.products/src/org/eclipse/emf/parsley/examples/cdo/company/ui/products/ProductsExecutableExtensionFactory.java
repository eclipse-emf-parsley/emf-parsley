package org.eclipse.emf.parsley.examples.cdo.company.ui.products;

import org.eclipse.emf.parsley.EmfParsleyExtensionFactory;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

public class ProductsExecutableExtensionFactory extends
		EmfParsleyExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return ProductsActivator.getDefault().getBundle();
	}

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return ProductsActivator.getDefault().createModule();
	}

	@Override
	protected Injector getInjector() {
		return ProductsActivator.getDefault().getInjector();
	}
}
