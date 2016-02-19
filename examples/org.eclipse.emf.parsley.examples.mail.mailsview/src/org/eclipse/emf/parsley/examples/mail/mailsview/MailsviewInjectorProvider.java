package org.eclipse.emf.parsley.examples.mail.mailsview;

import static com.google.inject.Guice.createInjector;
import static org.eclipse.emf.parsley.runtime.ui.PluginUtil.getBundle;
import static org.eclipse.emf.parsley.runtime.ui.PluginUtil.getPlugin;

import com.google.inject.Injector;

public class MailsviewInjectorProvider {

	private static Injector injector;

	public static synchronized Injector getInjector() throws Exception {
		if (injector == null) {
			injector = createInjector(
					new MailsviewGuiceModule(getPlugin(getBundle(MailsviewInjectorProvider.class))));
		}
		return injector;
	}
}
