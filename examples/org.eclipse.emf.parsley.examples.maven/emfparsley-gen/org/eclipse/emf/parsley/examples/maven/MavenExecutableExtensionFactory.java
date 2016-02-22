package org.eclipse.emf.parsley.examples.maven;

import com.google.inject.Injector;
import org.eclipse.emf.parsley.examples.maven.MavenInjectorProvider;
import org.eclipse.emf.parsley.runtime.ui.AbstractGuiceAwareExecutableExtensionFactory;

@SuppressWarnings("all")
public class MavenExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {
  @Override
  public Injector getInjector() throws Exception {
    return MavenInjectorProvider.getInjector();
  }
}
