package org.eclipse.emf.parsley.examples.mail.unifiedfoldersview;

import org.eclipse.emf.parsley.examples.mail.accountsview.AccountsviewEmfParsleyGuiceModule;
import org.eclipse.emf.parsley.examples.mail.unifiedfoldersview.edit.ui.provider.UnifiedfoldersviewViewerContentProvider;
import org.eclipse.emf.parsley.examples.mail.unifiedfoldersview.ui.provider.UnifiedfoldersviewLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * org.eclipse.emf.parsley.examples.mail.unifiedfoldersview EMF Parsley Dsl Module file
 */
@SuppressWarnings("all")
public class UnifiedfoldersviewEmfParsleyGuiceModule extends AccountsviewEmfParsleyGuiceModule {
  public UnifiedfoldersviewEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
    super(plugin);
  }

  @Override
  public Class<? extends ILabelProvider> bindILabelProvider() {
    return UnifiedfoldersviewLabelProvider.class;
  }

  @Override
  public Class<? extends IContentProvider> bindIContentProvider() {
    return UnifiedfoldersviewViewerContentProvider.class;
  }
}
