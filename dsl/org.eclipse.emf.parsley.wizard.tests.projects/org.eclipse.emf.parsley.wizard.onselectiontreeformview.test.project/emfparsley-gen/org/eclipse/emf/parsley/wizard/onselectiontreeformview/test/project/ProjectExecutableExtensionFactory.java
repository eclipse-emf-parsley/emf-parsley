package org.eclipse.emf.parsley.wizard.onselectiontreeformview.test.project;

import com.google.inject.Injector;
import org.eclipse.emf.parsley.runtime.ui.AbstractGuiceAwareExecutableExtensionFactory;

@SuppressWarnings("all")
public class ProjectExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {
  @Override
  public Injector getInjector() throws Exception {
    return ProjectInjectorProvider.getInjector();
  }
}
