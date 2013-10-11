package org.eclipse.emf.parsley.examples.mail.messageview;

import org.eclipse.emf.parsley.EmfParsleyExtensionFactory;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.osgi.framework.Bundle;


import com.google.inject.Injector;

public class MessageviewExecutableExtensionFactory extends
		EmfParsleyExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return MessageviewActivator.getDefault().getBundle();
	}

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return MessageviewActivator.getDefault().createModule();
	}

	@Override
	protected Injector getInjector() {
		return MessageviewActivator.getDefault().getInjector();
	}
}
