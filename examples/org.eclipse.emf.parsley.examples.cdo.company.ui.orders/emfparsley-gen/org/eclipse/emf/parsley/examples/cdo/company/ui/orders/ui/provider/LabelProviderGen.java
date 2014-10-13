package org.eclipse.emf.parsley.examples.cdo.company.ui.orders.ui.provider;

import com.google.inject.Inject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.examples.cdo.company.Customer;
import org.eclipse.emf.parsley.examples.cdo.company.Order;
import org.eclipse.emf.parsley.examples.cdo.company.OrderDetail;
import org.eclipse.emf.parsley.examples.cdo.company.SalesOrder;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;

@SuppressWarnings("all")
public class LabelProviderGen extends ViewerLabelProvider {
  @Inject
  public LabelProviderGen(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }
  
  public String text(final SalesOrder it) {
    String _xblockexpression = null;
    {
      EObject _eContainer = it.eContainer();
      final Customer customer = ((Customer) _eContainer);
      EList<SalesOrder> _salesOrders = customer.getSalesOrders();
      int _indexOf = _salesOrders.indexOf(it);
      int i = (_indexOf + 1);
      String _name = customer.getName();
      _xblockexpression = ((("order " + Integer.valueOf(i)) + " made by ") + _name);
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
