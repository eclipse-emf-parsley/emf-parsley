package org.eclipse.emf.parsley.examples.cdo.company.ui.products.edit.ui.provider;

import com.google.inject.Inject;
import java.util.List;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider;
import org.eclipse.emf.parsley.examples.cdo.company.Category;
import org.eclipse.emf.parsley.examples.cdo.company.Company;
import org.eclipse.emf.parsley.examples.cdo.company.SalesOrder;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

@SuppressWarnings("all")
public class ViewerContentProviderGen extends ViewerContentProvider {
  @Inject
  public ViewerContentProviderGen(final AdapterFactory adapterFactory) {
    super(adapterFactory);
  }
  
  public Object elements(final Resource it) {
    EList<Category> _xifexpression = null;
    EList<EObject> _contents = it.getContents();
    int _size = _contents.size();
    boolean _greaterThan = (_size > 0);
    if (_greaterThan) {
      EList<Category> _xblockexpression = null;
      {
        EList<EObject> _contents_1 = it.getContents();
        EObject _get = _contents_1.get(0);
        final Company company = ((Company) _get);
        EList<Category> _categories = company.getCategories();
        _xblockexpression = (_categories);
      }
      _xifexpression = _xblockexpression;
    }
    return _xifexpression;
  }
  
  public Object elements(final SalesOrder it) {
    List<Object> _emptyList = CollectionLiterals.<Object>emptyList();
    return _emptyList;
  }
}
