package org.eclipse.emf.parsley.examples.maven;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.emf.parsley.examples.maven.MavenEmfParsleyGuiceModuleGen;
import org.eclipse.emf.parsley.runtime.ui.PluginUtil;

@SuppressWarnings("all")
public class MavenInjectorProvider {
  private static Injector injector;
  
  public static synchronized Injector getInjector() throws Exception {
    if (injector == null) {
      injector = Guice.createInjector(
        new MavenEmfParsleyGuiceModuleGen(PluginUtil.getPlugin(
          PluginUtil.getBundle(MavenInjectorProvider.class))));
    }
    return injector;
  }
}
