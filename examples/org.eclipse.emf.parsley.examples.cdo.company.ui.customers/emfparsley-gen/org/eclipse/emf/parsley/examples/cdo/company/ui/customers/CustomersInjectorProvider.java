package org.eclipse.emf.parsley.examples.cdo.company.ui.customers;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.emf.parsley.examples.cdo.company.ui.customers.CustomersEmfParsleyGuiceModuleGen;
import org.eclipse.emf.parsley.runtime.ui.PluginUtil;

@SuppressWarnings("all")
public class CustomersInjectorProvider {
  private static Injector injector;
  
  public static synchronized Injector getInjector() throws Exception {
    if (injector == null) {
      injector = Guice.createInjector(
        new CustomersEmfParsleyGuiceModuleGen(PluginUtil.getPlugin(
          PluginUtil.getBundle(CustomersInjectorProvider.class))));
    }
    return injector;
  }
}
