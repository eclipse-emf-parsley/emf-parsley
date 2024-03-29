package org.eclipse.emf.parsley.examples.cdo.company.ui.customers.edit.ui.provider;

import com.google.inject.Inject;
import java.util.List;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider;
import org.eclipse.emf.parsley.examples.cdo.company.Company;
import org.eclipse.emf.parsley.examples.cdo.company.Customer;
import org.eclipse.emf.parsley.examples.cdo.company.SalesOrder;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

@SuppressWarnings("all")
public class CustomersViewerContentProvider extends ViewerContentProvider {
  @Inject
  public CustomersViewerContentProvider(final AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  public Object elements(final Resource it) {
    EList<Customer> _xifexpression = null;
    int _size = it.getContents().size();
    boolean _greaterThan = (_size > 0);
    if (_greaterThan) {
      EList<Customer> _xblockexpression = null;
      {
        EObject _get = it.getContents().get(0);
        final Company company = ((Company) _get);
        _xblockexpression = company.getCustomers();
      }
      _xifexpression = _xblockexpression;
    }
    return _xifexpression;
  }

  public Object children(final SalesOrder it) {
    List<Object> _emptyList = CollectionLiterals.<Object>emptyList();
    return _emptyList;
  }
}
