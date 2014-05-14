package org.eclipse.emf.parsley.examples.cdo.company.ui.orders;

import org.osgi.framework.Bundle;

import org.eclipse.emf.parsley.EmfParsleyExtensionFactory;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;

import com.google.inject.Injector;

public class OrdersExecutableExtensionFactory extends
		EmfParsleyExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return OrdersActivator.getDefault().getBundle();
	}

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return OrdersActivator.getDefault().createModule();
	}

	@Override
	protected Injector getInjector() {
		return OrdersActivator.getDefault().getInjector();
	}
}
