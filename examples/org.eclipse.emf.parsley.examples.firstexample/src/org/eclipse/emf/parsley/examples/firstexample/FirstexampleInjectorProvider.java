package org.eclipse.emf.parsley.examples.firstexample;

import static com.google.inject.Guice.createInjector;
import static org.eclipse.emf.parsley.runtime.ui.PluginUtil.getBundle;
import static org.eclipse.emf.parsley.runtime.ui.PluginUtil.getPlugin;

import com.google.inject.Injector;

public class FirstexampleInjectorProvider {

	private static Injector injector;

	public static synchronized Injector getInjector() throws Exception {
		if (injector == null) {
			injector = createInjector(
					new FirstexampleGuiceModule(getPlugin(getBundle(FirstexampleInjectorProvider.class))));
		}
		return injector;
	}
}
