package org.eclipse.emf.parsley.examples.mail.unifiedfoldersview;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.emf.parsley.examples.mail.unifiedfoldersview.UnifiedfoldersviewEmfParsleyGuiceModuleGen;
import org.eclipse.emf.parsley.runtime.ui.PluginUtil;

@SuppressWarnings("all")
public class UnifiedfoldersviewInjectorProvider {
  private static Injector injector;
  
  public static synchronized Injector getInjector() throws Exception {
    if (injector == null) {
      injector = Guice.createInjector(
        new UnifiedfoldersviewEmfParsleyGuiceModuleGen(PluginUtil.getPlugin(
          PluginUtil.getBundle(UnifiedfoldersviewInjectorProvider.class))));
    }
    return injector;
  }
}
