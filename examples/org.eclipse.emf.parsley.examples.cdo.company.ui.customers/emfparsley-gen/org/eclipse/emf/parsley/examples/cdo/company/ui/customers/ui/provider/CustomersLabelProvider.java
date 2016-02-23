package org.eclipse.emf.parsley.examples.cdo.company.ui.customers.ui.provider;

import com.google.inject.Inject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.examples.cdo.company.Customer;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;

@SuppressWarnings("all")
public class CustomersLabelProvider extends ViewerLabelProvider {
  @Inject
  public CustomersLabelProvider(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }
  
  public String text(final Customer it) {
    String _name = it.getName();
    return _name;
  }
  
  public Object image(final Customer it) {
    return "customer.png";
  }
}
