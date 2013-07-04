package org.eclipse.emf.parsley.examples.mail.messageview;

import org.eclipse.emf.parsley.EmfComponentsExtensionFactory;
import org.eclipse.emf.parsley.EmfComponentsGuiceModule;
import org.osgi.framework.Bundle;


import com.google.inject.Injector;

public class MessageviewExecutableExtensionFactory extends
		EmfComponentsExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return MessageviewActivator.getDefault().getBundle();
	}

	@Override
	protected EmfComponentsGuiceModule getModule() {
		return MessageviewActivator.getDefault().createModule();
	}

	@Override
	protected Injector getInjector() {
		return MessageviewActivator.getDefault().getInjector();
	}
}
