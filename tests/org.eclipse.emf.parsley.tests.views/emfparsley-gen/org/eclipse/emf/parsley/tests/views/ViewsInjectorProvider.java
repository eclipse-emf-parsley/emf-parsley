package org.eclipse.emf.parsley.tests.views;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.emf.parsley.runtime.ui.PluginUtil;
import org.eclipse.emf.parsley.tests.views.ViewsEmfParsleyGuiceModule;

@SuppressWarnings("all")
public class ViewsInjectorProvider {
  private static Injector injector;
  
  public static synchronized Injector getInjector() {
    if (injector == null) {
      injector = Guice.createInjector(
        new ViewsEmfParsleyGuiceModule(PluginUtil.getPlugin(
          PluginUtil.getBundle(ViewsInjectorProvider.class))));
    }
    return injector;
  }
}
