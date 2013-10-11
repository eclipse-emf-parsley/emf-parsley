package org.eclipse.emf.parsley.examples.mail.mailsview;

import org.eclipse.emf.parsley.EmfParsleyExtensionFactory;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.osgi.framework.Bundle;


import com.google.inject.Injector;

public class MailsviewExecutableExtensionFactory extends
		EmfParsleyExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return MailsviewActivator.getDefault().getBundle();
	}

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return MailsviewActivator.getDefault().createModule();
	}

	@Override
	protected Injector getInjector() {
		return MailsviewActivator.getDefault().getInjector();
	}
}
