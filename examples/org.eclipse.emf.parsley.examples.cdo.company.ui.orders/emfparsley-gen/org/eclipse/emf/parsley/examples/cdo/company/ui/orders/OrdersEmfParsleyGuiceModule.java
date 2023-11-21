package org.eclipse.emf.parsley.examples.cdo.company.ui.orders;

import org.eclipse.emf.parsley.examples.cdo.company.CompanyGuiceModule;
import org.eclipse.emf.parsley.examples.cdo.company.ui.orders.ui.provider.OrdersLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * org.eclipse.emf.parsley.examples.cdo.company.ui.orders EMF Parsley Dsl Module file
 */
@SuppressWarnings("all")
public class OrdersEmfParsleyGuiceModule extends CompanyGuiceModule {
  public OrdersEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
    super(plugin);
  }

  @Override
  public Class<? extends ILabelProvider> bindILabelProvider() {
    return OrdersLabelProvider.class;
  }
}
