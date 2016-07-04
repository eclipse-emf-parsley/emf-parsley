package org.eclipse.emf.parsley.examples.eclipse4.parsleypart;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.emf.parsley.examples.eclipse4.parsleypart.ParsleypartEmfParsleyGuiceModule;
import org.eclipse.emf.parsley.runtime.ui.PluginUtil;

@SuppressWarnings("all")
public class ParsleypartInjectorProvider {
  private static Injector injector;
  
  public static synchronized Injector getInjector() {
    if (injector == null) {
      injector = Guice.createInjector(
        new ParsleypartEmfParsleyGuiceModule(PluginUtil.getPlugin(
          PluginUtil.getBundle(ParsleypartInjectorProvider.class))));
    }
    return injector;
  }
}
