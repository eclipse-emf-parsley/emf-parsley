package org.eclipse.emf.parsley.examples.cdo.company.ui.orders;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.emf.parsley.runtime.ui.PluginUtil;

@SuppressWarnings("all")
public class OrdersInjectorProvider {
  private static Injector injector;

  public static synchronized Injector getInjector() {
    if (injector == null) {
      injector = Guice.createInjector(
        new OrdersEmfParsleyGuiceModule(PluginUtil.getPlugin(
          PluginUtil.getBundle(OrdersInjectorProvider.class))));
    }
    return injector;
  }
}
