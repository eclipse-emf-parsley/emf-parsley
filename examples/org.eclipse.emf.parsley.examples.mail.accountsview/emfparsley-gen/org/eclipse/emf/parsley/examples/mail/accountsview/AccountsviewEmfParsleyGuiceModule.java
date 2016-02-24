package org.eclipse.emf.parsley.examples.mail.accountsview;

import com.google.inject.Provider;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.edit.IEditingStrategy;
import org.eclipse.emf.parsley.edit.UndoableEditingStrategy;
import org.eclipse.emf.parsley.edit.domain.GlobalAdapterFactoryEditingDomainProvider;
import org.eclipse.emf.parsley.examples.mail.accountsview.config.AccountsviewConfigurator;
import org.eclipse.emf.parsley.examples.mail.accountsview.edit.ui.provider.AccountsviewViewerContentProvider;
import org.eclipse.emf.parsley.examples.mail.accountsview.resource.AccountsviewResourceManager;
import org.eclipse.emf.parsley.examples.mail.accountsview.ui.provider.AccountsviewLabelProvider;
import org.eclipse.emf.parsley.resource.ResourceManager;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * org.eclipse.emf.parsley.examples.mail.accountsview Emf Parsley Dsl Module file
 */
@SuppressWarnings("all")
public class AccountsviewEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
  public AccountsviewEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends IEditingStrategy> bindIEditingStrategy() {
    return UndoableEditingStrategy.class;
  }
  
  @Override
  public Class<? extends Provider<AdapterFactoryEditingDomain>> provideAdapterFactoryEditingDomain() {
    return GlobalAdapterFactoryEditingDomainProvider.class;
  }
  
  @Override
  public Class<? extends ILabelProvider> bindILabelProvider() {
    return AccountsviewLabelProvider.class;
  }
  
  @Override
  public Class<? extends IContentProvider> bindIContentProvider() {
    return AccountsviewViewerContentProvider.class;
  }
  
  @Override
  public Class<? extends Configurator> bindConfigurator() {
    return AccountsviewConfigurator.class;
  }
  
  @Override
  public Class<? extends ResourceManager> bindResourceManager() {
    return AccountsviewResourceManager.class;
  }
}
