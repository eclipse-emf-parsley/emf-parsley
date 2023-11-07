package org.eclipse.emf.parsley.examples.mail.unifiedfoldersview;

import com.google.inject.Injector;
import org.eclipse.emf.parsley.runtime.ui.AbstractGuiceAwareExecutableExtensionFactory;

@SuppressWarnings("all")
public class UnifiedfoldersviewExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {
  @Override
  public Injector getInjector() throws Exception {
    return UnifiedfoldersviewInjectorProvider.getInjector();
  }
}
