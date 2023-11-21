package org.eclipse.emf.parsley.examples.cdo.company.ui.products.ui.provider;

import com.google.inject.Inject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.examples.cdo.company.Category;
import org.eclipse.emf.parsley.examples.cdo.company.Product;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;

@SuppressWarnings("all")
public class ProductsLabelProvider extends ViewerLabelProvider {
  @Inject
  public ProductsLabelProvider(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }

  public String text(final Category it) {
    String _name = it.getName();
    return _name;
  }

  public String text(final Product it) {
    String _name = it.getName();
    String _plus = ("Product: " + _name);
    return _plus;
  }

  public Object image(final Product it) {
    return "product2.png";
  }

  public Object image(final Category it) {
    return "category.png";
  }
}
