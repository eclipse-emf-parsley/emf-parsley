package org.eclipse.emf.parsley.examples.cdo.company.ui.customers;

import org.eclipse.emf.parsley.examples.cdo.company.CompanyGuiceModule;
import org.eclipse.emf.parsley.examples.cdo.company.ui.customers.edit.ui.provider.CustomersViewerContentProvider;
import org.eclipse.emf.parsley.examples.cdo.company.ui.customers.ui.provider.CustomersLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * org.eclipse.emf.parsley.examples.cdo.company.ui.customers EMF Parsley Dsl Module file
 */
@SuppressWarnings("all")
public class CustomersEmfParsleyGuiceModule extends CompanyGuiceModule {
  public CustomersEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
    super(plugin);
  }

  @Override
  public Class<? extends ILabelProvider> bindILabelProvider() {
    return CustomersLabelProvider.class;
  }

  @Override
  public Class<? extends IContentProvider> bindIContentProvider() {
    return CustomersViewerContentProvider.class;
  }
}
