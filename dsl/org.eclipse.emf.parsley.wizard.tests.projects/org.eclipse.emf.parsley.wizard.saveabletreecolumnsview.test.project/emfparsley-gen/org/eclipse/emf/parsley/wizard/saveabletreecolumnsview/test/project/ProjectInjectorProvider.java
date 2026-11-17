package org.eclipse.emf.parsley.wizard.saveabletreecolumnsview.test.project;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.emf.parsley.runtime.ui.PluginUtil;

@SuppressWarnings("all")
public class ProjectInjectorProvider {
  private static Injector injector;

  public static synchronized Injector getInjector() {
    if (injector == null) {
      injector = Guice.createInjector(
        new ProjectEmfParsleyGuiceModule(PluginUtil.getPlugin(
          PluginUtil.getBundle(ProjectInjectorProvider.class))));
    }
    return injector;
  }
}
