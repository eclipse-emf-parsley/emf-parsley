package org.eclipse.emf.parsley.examples.mail.accountsview;

import org.eclipse.emf.parsley.EmfComponentsGuiceModule;
import org.eclipse.emf.parsley.examples.mail.accountsview.edit.ui.provider.ViewerContentProviderGen;
import org.eclipse.emf.parsley.examples.mail.accountsview.ui.provider.LabelProviderGen;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * org.eclipse.emf.parsley.examples.mail.accountsview Emf Components Dsl Module file
 */
@SuppressWarnings("all")
public class EmfComponentsGuiceModuleGen extends EmfComponentsGuiceModule {
  public EmfComponentsGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends ILabelProvider> bindILabelProvider() {
    return LabelProviderGen.class;
  }
  
  @Override
  public Class<? extends IContentProvider> bindIContentProvider() {
    return ViewerContentProviderGen.class;
  }
}
