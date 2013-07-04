package org.eclipse.emf.parsley.examples.firstexample.ui.provider;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.examples.extlibrary.Book;
import org.eclipse.emf.examples.extlibrary.Writer;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;

@SuppressWarnings("all")
public class LabelProviderGen extends ViewerLabelProvider {
  @Inject
  public LabelProviderGen(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }
  
  public String text(final Book b) {
    String _title = b.getTitle();
    String _plus = ("\"" + _title);
    String _plus_1 = (_plus + "\"");
    String _xifexpression = null;
    Writer _author = b.getAuthor();
    boolean _notEquals = (!Objects.equal(_author, null));
    if (_notEquals) {
      Writer _author_1 = b.getAuthor();
      String _name = _author_1.getName();
      String _plus_2 = (" (by " + _name);
      String _plus_3 = (_plus_2 + ")");
      _xifexpression = _plus_3;
    } else {
      _xifexpression = "";
    }
    String _plus_4 = (_plus_1 + _xifexpression);
    return _plus_4;
  }
}
