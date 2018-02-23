package org.eclipse.emf.parsley.tests.treetableformviews;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.emf.parsley.runtime.ui.PluginUtil;
import org.eclipse.emf.parsley.tests.treetableformviews.TreetableformviewsEmfParsleyGuiceModule;

@SuppressWarnings("all")
public class TreetableformviewsInjectorProvider {
  private static Injector injector;
  
  public static synchronized Injector getInjector() {
    if (injector == null) {
      injector = Guice.createInjector(
        new TreetableformviewsEmfParsleyGuiceModule(PluginUtil.getPlugin(
          PluginUtil.getBundle(TreetableformviewsInjectorProvider.class))));
    }
    return injector;
  }
}
