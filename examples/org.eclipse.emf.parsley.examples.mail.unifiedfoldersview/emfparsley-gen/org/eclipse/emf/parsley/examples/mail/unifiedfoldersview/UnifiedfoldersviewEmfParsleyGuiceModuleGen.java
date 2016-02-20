package org.eclipse.emf.parsley.examples.mail.unifiedfoldersview;

import org.eclipse.emf.parsley.examples.mail.accountsview.AccountsviewEmfParsleyGuiceModuleGen;
import org.eclipse.emf.parsley.examples.mail.unifiedfoldersview.edit.ui.provider.UnifiedfoldersviewViewerContentProviderGen;
import org.eclipse.emf.parsley.examples.mail.unifiedfoldersview.ui.provider.UnifiedfoldersviewLabelProviderGen;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * org.eclipse.emf.parsley.examples.mail.unifiedfoldersview Emf Parsley Dsl Module file
 */
@SuppressWarnings("all")
public class UnifiedfoldersviewEmfParsleyGuiceModuleGen extends AccountsviewEmfParsleyGuiceModuleGen {
  public UnifiedfoldersviewEmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends ILabelProvider> bindILabelProvider() {
    return UnifiedfoldersviewLabelProviderGen.class;
  }
  
  @Override
  public Class<? extends IContentProvider> bindIContentProvider() {
    return UnifiedfoldersviewViewerContentProviderGen.class;
  }
}
