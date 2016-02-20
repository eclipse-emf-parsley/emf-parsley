package org.eclipse.emf.parsley.examples.firstexample.edit.ui.provider;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.examples.extlibrary.Book;
import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.emf.examples.extlibrary.Writer;
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider;

@SuppressWarnings("all")
public class FirstexampleViewerContentProviderGen extends ViewerContentProvider {
  @Inject
  public FirstexampleViewerContentProviderGen(final AdapterFactory adapterFactory) {
    super(adapterFactory);
  }
  
  public Object children(final Library it) {
    EList<Writer> _writers = it.getWriters();
    EList<Book> _books = it.getBooks();
    return Iterables.<EObject>concat(_writers, _books);
  }
}
