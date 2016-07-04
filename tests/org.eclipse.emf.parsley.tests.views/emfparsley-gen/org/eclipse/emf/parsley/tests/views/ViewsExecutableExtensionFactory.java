package org.eclipse.emf.parsley.tests.views;

import com.google.inject.Injector;
import org.eclipse.emf.parsley.runtime.ui.AbstractGuiceAwareExecutableExtensionFactory;
import org.eclipse.emf.parsley.tests.views.ViewsInjectorProvider;

@SuppressWarnings("all")
public class ViewsExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {
  @Override
  public Injector getInjector() throws Exception {
    return ViewsInjectorProvider.getInjector();
  }
}
