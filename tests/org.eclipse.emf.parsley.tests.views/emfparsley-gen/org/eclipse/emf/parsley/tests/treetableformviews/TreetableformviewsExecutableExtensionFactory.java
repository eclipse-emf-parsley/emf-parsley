package org.eclipse.emf.parsley.tests.treetableformviews;

import com.google.inject.Injector;
import org.eclipse.emf.parsley.runtime.ui.AbstractGuiceAwareExecutableExtensionFactory;
import org.eclipse.emf.parsley.tests.treetableformviews.TreetableformviewsInjectorProvider;

@SuppressWarnings("all")
public class TreetableformviewsExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {
  @Override
  public Injector getInjector() throws Exception {
    return TreetableformviewsInjectorProvider.getInjector();
  }
}
