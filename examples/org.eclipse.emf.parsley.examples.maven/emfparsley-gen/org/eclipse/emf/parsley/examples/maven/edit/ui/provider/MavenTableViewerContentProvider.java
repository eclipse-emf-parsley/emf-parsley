package org.eclipse.emf.parsley.examples.maven.edit.ui.provider;

import com.google.inject.Inject;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.parsley.edit.ui.provider.TableViewerContentProvider;
import org.eclipse.emf.parsley.inject.parameters.EClassParameter;

@SuppressWarnings("all")
public class MavenTableViewerContentProvider extends TableViewerContentProvider {
  @Inject
  public MavenTableViewerContentProvider(final AdapterFactory adapterFactory, final EClassParameter eClassParameter) {
    super(adapterFactory, eClassParameter);
  }
}
