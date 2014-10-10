package org.eclipse.emf.parsley.examples.mail.accountsview;

import com.google.inject.Provider;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.edit.IEditingStrategy;
import org.eclipse.emf.parsley.edit.UndoableEditingStrategy;
import org.eclipse.emf.parsley.edit.domain.GlobalAdapterFactoryEditingDomainProvider;
import org.eclipse.emf.parsley.examples.mail.accountsview.custom.MailEmptyResourceInitializer;
import org.eclipse.emf.parsley.examples.mail.accountsview.edit.ui.provider.ViewerContentProviderGen;
import org.eclipse.emf.parsley.examples.mail.accountsview.ui.provider.LabelProviderGen;
import org.eclipse.emf.parsley.resource.EmptyResourceInitializer;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * org.eclipse.emf.parsley.examples.mail.accountsview Emf Parsley Dsl Module file
 */
@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  public Class<? extends EmptyResourceInitializer> bindEmptyResourceInitializer() {
    return MailEmptyResourceInitializer.class;
  }
  
  public Class<? extends IEditingStrategy> bindIEditingStrategy() {
    return UndoableEditingStrategy.class;
  }
  
  public Class<? extends Provider<AdapterFactoryEditingDomain>> provideAdapterFactoryEditingDomain() {
    return GlobalAdapterFactoryEditingDomainProvider.class;
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
