package org.eclipse.emf.parsley.tests.utils;

import com.google.common.base.Objects;
import java.util.Arrays;
import java.util.List;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.examples.library.Book;
import org.eclipse.emf.parsley.examples.library.Borrower;
import org.eclipse.emf.parsley.examples.library.Writer;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class EmfComponentsTestsUtils {
  public String toStringNameBased(final Iterable<EStructuralFeature> features) {
    final Function1<EStructuralFeature,String> _function = new Function1<EStructuralFeature,String>() {
      public String apply(final EStructuralFeature it) {
        String _name = it.getName();
        return _name;
      }
    };
    Iterable<String> _map = IterableExtensions.<EStructuralFeature, String>map(features, _function);
    String _join = IterableExtensions.join(_map, ", ");
    return _join;
  }
  
  public String toStringRep(final List<? extends Object> elements) {
    final Function1<Object,String> _function = new Function1<Object,String>() {
      public String apply(final Object it) {
        String _stringRep = EmfComponentsTestsUtils.this.stringRep(it);
        return _stringRep;
      }
    };
    List<String> _map = ListExtensions.map(elements, _function);
    String _join = IterableExtensions.join(_map, ", ");
    return _join;
  }
  
  protected String _stringRep(final Object o) {
    String _string = o.toString();
    return _string;
  }
  
  protected String _stringRep(final Book book) {
    String _title = book.getTitle();
    String _plus = ("Book: " + _title);
    return _plus;
  }
  
  protected String _stringRep(final Writer writer) {
    String _name = writer.getName();
    String _plus = ("Writer: " + _name);
    return _plus;
  }
  
  protected String _stringRep(final Borrower b) {
    String _firstName = b.getFirstName();
    String _plus = ("Borrower: " + _firstName);
    String _xifexpression = null;
    String _lastName = b.getLastName();
    boolean _notEquals = (!Objects.equal(_lastName, null));
    if (_notEquals) {
      String _lastName_1 = b.getLastName();
      String _plus_1 = (" " + _lastName_1);
      _xifexpression = _plus_1;
    } else {
      _xifexpression = "";
    }
    String _plus_2 = (_plus + _xifexpression);
    return _plus_2;
  }
  
  public String stringRep(final Object book) {
    if (book instanceof Book) {
      return _stringRep((Book)book);
    } else if (book instanceof Borrower) {
      return _stringRep((Borrower)book);
    } else if (book instanceof Writer) {
      return _stringRep((Writer)book);
    } else if (book != null) {
      return _stringRep(book);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(book).toString());
    }
  }
}
