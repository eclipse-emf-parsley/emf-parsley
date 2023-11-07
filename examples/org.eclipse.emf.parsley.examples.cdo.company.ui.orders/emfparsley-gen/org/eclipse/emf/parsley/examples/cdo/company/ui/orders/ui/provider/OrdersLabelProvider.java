package org.eclipse.emf.parsley.examples.cdo.company.ui.orders.ui.provider;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.examples.cdo.company.Customer;
import org.eclipse.emf.parsley.examples.cdo.company.Order;
import org.eclipse.emf.parsley.examples.cdo.company.OrderDetail;
import org.eclipse.emf.parsley.examples.cdo.company.SalesOrder;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;

@SuppressWarnings("all")
public class OrdersLabelProvider extends ViewerLabelProvider {
  @Inject
  public OrdersLabelProvider(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }

  public String text(final SalesOrder it) {
    String _xblockexpression = null;
    {
      EObject _eContainer = it.eContainer();
      final Customer customer = ((Customer) _eContainer);
      int _indexOf = customer.getSalesOrders().indexOf(it);
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
