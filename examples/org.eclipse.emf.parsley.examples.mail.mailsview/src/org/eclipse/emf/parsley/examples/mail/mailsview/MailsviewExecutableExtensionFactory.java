package org.eclipse.emf.parsley.examples.mail.mailsview;

import org.eclipse.emf.parsley.EmfComponentsExtensionFactory;
import org.eclipse.emf.parsley.EmfComponentsGuiceModule;
import org.osgi.framework.Bundle;


import com.google.inject.Injector;

public class MailsviewExecutableExtensionFactory extends
		EmfComponentsExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return MailsviewActivator.getDefault().getBundle();
	}

	@Override
	protected EmfComponentsGuiceModule getModule() {
		return MailsviewActivator.getDefault().createModule();
	}

	@Override
	protected Injector getInjector() {
		return MailsviewActivator.getDefault().getInjector();
	}
}
