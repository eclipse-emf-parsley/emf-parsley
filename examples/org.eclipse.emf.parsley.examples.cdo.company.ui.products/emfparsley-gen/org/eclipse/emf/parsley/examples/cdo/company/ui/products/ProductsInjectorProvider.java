package org.eclipse.emf.parsley.examples.cdo.company.ui.products;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.emf.parsley.examples.cdo.company.ui.products.ProductsEmfParsleyGuiceModule;
import org.eclipse.emf.parsley.runtime.ui.PluginUtil;

@SuppressWarnings("all")
public class ProductsInjectorProvider {
  private static Injector injector;
  
  public static synchronized Injector getInjector() throws Exception {
    if (injector == null) {
      injector = Guice.createInjector(
        new ProductsEmfParsleyGuiceModule(PluginUtil.getPlugin(
          PluginUtil.getBundle(ProductsInjectorProvider.class))));
    }
    return injector;
  }
}
