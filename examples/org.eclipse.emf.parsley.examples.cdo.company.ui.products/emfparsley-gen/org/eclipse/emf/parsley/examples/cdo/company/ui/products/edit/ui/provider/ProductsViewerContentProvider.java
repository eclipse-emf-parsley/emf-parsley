package org.eclipse.emf.parsley.examples.cdo.company.ui.products.edit.ui.provider;

import com.google.inject.Inject;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider;
import org.eclipse.emf.parsley.examples.cdo.company.Category;
import org.eclipse.emf.parsley.examples.cdo.company.Company;

@SuppressWarnings("all")
public class ProductsViewerContentProvider extends ViewerContentProvider {
  @Inject
  public ProductsViewerContentProvider(final AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  public Object elements(final Resource it) {
    EList<Category> _xifexpression = null;
    int _size = it.getContents().size();
    boolean _greaterThan = (_size > 0);
    if (_greaterThan) {
      EList<Category> _xblockexpression = null;
      {
        EObject _get = it.getContents().get(0);
        final Company company = ((Company) _get);
        _xblockexpression = company.getCategories();
      }
      _xifexpression = _xblockexpression;
    }
    return _xifexpression;
  }
}
