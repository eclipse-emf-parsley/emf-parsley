package org.eclipse.emf.parsley.examples.cdo.treeform;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.emf.parsley.examples.cdo.treeform.TreeformEmfParsleyGuiceModule;
import org.eclipse.emf.parsley.runtime.ui.PluginUtil;

@SuppressWarnings("all")
public class TreeformInjectorProvider {
  private static Injector injector;
  
  public static synchronized Injector getInjector() {
    if (injector == null) {
      injector = Guice.createInjector(
        new TreeformEmfParsleyGuiceModule(PluginUtil.getPlugin(
          PluginUtil.getBundle(TreeformInjectorProvider.class))));
    }
    return injector;
  }
}
