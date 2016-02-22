package org.eclipse.emf.parsley.examples.maven.edit.ui.provider;

import com.google.inject.Inject;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.parsley.edit.ui.provider.TableViewerContentProvider;

@SuppressWarnings("all")
public class MavenTableViewerContentProviderGen extends TableViewerContentProvider {
  @Inject
  public MavenTableViewerContentProviderGen(final AdapterFactory adapterFactory) {
    super(adapterFactory);
  }
}
