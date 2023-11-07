package org.eclipse.emf.parsley.examples.cdo.company.ui.customers;

import com.google.inject.Injector;
import org.eclipse.emf.parsley.runtime.ui.AbstractGuiceAwareExecutableExtensionFactory;

@SuppressWarnings("all")
public class CustomersExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {
  @Override
  public Injector getInjector() throws Exception {
    return CustomersInjectorProvider.getInjector();
  }
}
