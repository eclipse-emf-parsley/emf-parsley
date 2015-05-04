package org.eclipse.emf.parsley.examples.firstexample.edit.action;

import java.util.Collections;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.examples.extlibrary.Book;
import org.eclipse.emf.examples.extlibrary.EXTLibraryFactory;
import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.emf.examples.extlibrary.Writer;
import org.eclipse.emf.parsley.edit.action.EditingMenuBuilder;
import org.eclipse.emf.parsley.edit.action.IMenuContributionSpecification;
import org.eclipse.emf.parsley.runtime.util.IAcceptor;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

@SuppressWarnings("all")
public class MenuBuilderGen extends EditingMenuBuilder {
  private final EXTLibraryFactory factory = EXTLibraryFactory.eINSTANCE;
  
  public EXTLibraryFactory getFactory() {
    return this.factory;
  }
  
  public List<IMenuContributionSpecification> emfMenuContributions(final Writer w) {
    EObject _eContainer = w.eContainer();
    EList<Book> _books = ((Library) _eContainer).getBooks();
    Book _createBook = this.factory.createBook();
    final IAcceptor<Book> _function = new IAcceptor<Book>() {
      @Override
      public void accept(final Book book) {
        book.setTitle("A new book");
        book.setAuthor(w);
      }
    };
    IMenuContributionSpecification _actionAdd = this.<Book>actionAdd(
      "New book", _books, _createBook, _function);
    return Collections.<IMenuContributionSpecification>unmodifiableList(CollectionLiterals.<IMenuContributionSpecification>newArrayList(_actionAdd));
  }
  
  public List<IMenuContributionSpecification> emfMenuContributions(final Book b) {
    EObject _eContainer = b.eContainer();
    EList<Writer> _writers = ((Library) _eContainer).getWriters();
    Writer _createWriter = this.factory.createWriter();
    final IAcceptor<Writer> _function = new IAcceptor<Writer>() {
      @Override
      public void accept(final Writer writer) {
        writer.setName("A new writer");
        EList<Book> _books = writer.getBooks();
        _books.add(b);
      }
    };
    IMenuContributionSpecification _actionAdd = this.<Writer>actionAdd(
      "New writer", _writers, _createWriter, _function);
    return Collections.<IMenuContributionSpecification>unmodifiableList(CollectionLiterals.<IMenuContributionSpecification>newArrayList(_actionAdd));
  }
}
