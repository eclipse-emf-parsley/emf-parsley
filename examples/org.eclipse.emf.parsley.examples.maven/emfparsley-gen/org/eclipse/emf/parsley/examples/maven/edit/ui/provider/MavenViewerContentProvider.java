package org.eclipse.emf.parsley.examples.maven.edit.ui.provider;

import com.google.inject.Inject;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider;

@SuppressWarnings("all")
public class MavenViewerContentProvider extends ViewerContentProvider {
  @Inject
  public MavenViewerContentProvider(final AdapterFactory adapterFactory) {
    super(adapterFactory);
  }
}
