package org.eclipse.emf.parsley.examples.mail.unifiedfoldersview;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.emf.parsley.runtime.ui.PluginUtil;

@SuppressWarnings("all")
public class UnifiedfoldersviewInjectorProvider {
  private static Injector injector;

  public static synchronized Injector getInjector() {
    if (injector == null) {
      injector = Guice.createInjector(
        new UnifiedfoldersviewEmfParsleyGuiceModule(PluginUtil.getPlugin(
          PluginUtil.getBundle(UnifiedfoldersviewInjectorProvider.class))));
    }
    return injector;
  }
}
