package org.eclipse.emf.parsley.examples.mail.accountsview;

import org.eclipse.emf.parsley.EmfParsleyExtensionFactory;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.osgi.framework.Bundle;


import com.google.inject.Injector;

public class AccountsviewExecutableExtensionFactory extends
		EmfParsleyExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return AccountsviewActivator.getDefault().getBundle();
	}

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return AccountsviewActivator.getDefault().createModule();
	}

	@Override
	protected Injector getInjector() {
		return AccountsviewActivator.getDefault().getInjector();
	}
}
