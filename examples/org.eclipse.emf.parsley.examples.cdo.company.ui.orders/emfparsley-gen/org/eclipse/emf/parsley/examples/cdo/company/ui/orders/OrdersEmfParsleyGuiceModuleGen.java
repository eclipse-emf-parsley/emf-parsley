package org.eclipse.emf.parsley.examples.cdo.company.ui.orders;

import org.eclipse.emf.parsley.examples.cdo.company.CompanyGuiceModule;
import org.eclipse.emf.parsley.examples.cdo.company.ui.orders.ui.provider.OrdersLabelProviderGen;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * org.eclipse.emf.parsley.examples.cdo.company.ui.orders Emf Parsley Dsl Module file
 */
@SuppressWarnings("all")
public class OrdersEmfParsleyGuiceModuleGen extends CompanyGuiceModule {
  public OrdersEmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends ILabelProvider> bindILabelProvider() {
    return OrdersLabelProviderGen.class;
  }
}
