package org.eclipse.emf.parsley.examples.cdo.company.ui.orders.ui.provider;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.examples.cdo.company.Customer;
import org.eclipse.emf.parsley.examples.cdo.company.Order;
import org.eclipse.emf.parsley.examples.cdo.company.OrderDetail;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;

@SuppressWarnings("all")
public class LabelProviderGen extends ViewerLabelProvider {
  @Inject
  public LabelProviderGen(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }
  
  public String text(final Order it) {
    String _xblockexpression = null;
    {
      EObject _eContainer = it.eContainer();
      final Customer customer = ((Customer) _eContainer);
      String _name = customer.getName();
      String _plus = ("order made by " + _name);
      _xblockexpression = (_plus);
    }
    return _xblockexpression;
  }
  
  public Object image(final Order it) {
    return "order.png";
  }
  
  public Object image(final OrderDetail it) {
    return "order_detail.png";
  }
}
