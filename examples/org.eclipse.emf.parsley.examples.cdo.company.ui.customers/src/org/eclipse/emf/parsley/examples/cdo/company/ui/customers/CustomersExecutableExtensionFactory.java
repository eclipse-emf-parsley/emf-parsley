package org.eclipse.emf.parsley.examples.cdo.company.ui.customers;

import org.osgi.framework.Bundle;

import org.eclipse.emf.parsley.EmfParsleyExtensionFactory;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;

import com.google.inject.Injector;

public class CustomersExecutableExtensionFactory extends
		EmfParsleyExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return CustomersActivator.getDefault().getBundle();
	}

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return CustomersActivator.getDefault().createModule();
	}

	@Override
	protected Injector getInjector() {
		return CustomersActivator.getDefault().getInjector();
	}
}
