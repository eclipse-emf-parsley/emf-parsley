package org.eclipse.emf.parsley.examples.cdo.company.ui.products;

import com.google.inject.Injector;
import org.eclipse.emf.parsley.examples.cdo.company.ui.products.ProductsInjectorProvider;
import org.eclipse.emf.parsley.runtime.ui.AbstractGuiceAwareExecutableExtensionFactory;

@SuppressWarnings("all")
public class ProductsExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {
  @Override
  public Injector getInjector() throws Exception {
    return ProductsInjectorProvider.getInjector();
  }
}
