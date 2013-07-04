package org.eclipse.emf.parsley.examples.mail.accountsview;

import org.eclipse.emf.parsley.EmfComponentsExtensionFactory;
import org.eclipse.emf.parsley.EmfComponentsGuiceModule;
import org.osgi.framework.Bundle;


import com.google.inject.Injector;

public class AccountsviewExecutableExtensionFactory extends
		EmfComponentsExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return AccountsviewActivator.getDefault().getBundle();
	}

	@Override
	protected EmfComponentsGuiceModule getModule() {
		return AccountsviewActivator.getDefault().createModule();
	}

	@Override
	protected Injector getInjector() {
		return AccountsviewActivator.getDefault().getInjector();
	}
}
