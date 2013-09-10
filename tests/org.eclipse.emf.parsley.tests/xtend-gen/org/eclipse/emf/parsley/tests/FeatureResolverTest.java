package org.eclipse.emf.parsley.tests;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.ecore.FeatureNamePath;
import org.eclipse.emf.parsley.ecore.FeatureResolver;
import org.eclipse.emf.parsley.examples.library.Book;
import org.eclipse.emf.parsley.examples.library.BookOnTape;
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory;
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage;
import org.eclipse.emf.parsley.examples.library.Employee;
import org.eclipse.emf.parsley.examples.library.Item;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.examples.library.Writer;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class FeatureResolverTest {
  private FeatureResolver resolver = new Function0<FeatureResolver>() {
    public FeatureResolver apply() {
      FeatureResolver _featureResolver = new FeatureResolver();
      return _featureResolver;
    }
  }.apply();
  
  @Test
  public void testResolver1() {
    FeatureNamePath _featureNamePath = new FeatureNamePath("title");
    FeatureNamePath _featureNamePath_1 = new FeatureNamePath("firstName");
    FeatureNamePath _featureNamePath_2 = new FeatureNamePath("lastName");
    ArrayList<FeatureNamePath> _newArrayList = CollectionLiterals.<FeatureNamePath>newArrayList(_featureNamePath_1, _featureNamePath_2);
    FeatureNamePath _featureNamePath_3 = new FeatureNamePath("author", _newArrayList);
    final ArrayList<FeatureNamePath> paths = CollectionLiterals.<FeatureNamePath>newArrayList(_featureNamePath, _featureNamePath_3);
    EClass _book = EXTLibraryPackage.eINSTANCE.getBook();
    this.assertFeatureList(_book, paths, "title, firstName, lastName");
  }
  
  @Test
  public void testResolver2() {
    FeatureNamePath _featureNamePath = new FeatureNamePath("title");
    FeatureNamePath _featureNamePath_1 = new FeatureNamePath("minutesLength");
    FeatureNamePath _featureNamePath_2 = new FeatureNamePath("firstName");
    FeatureNamePath _featureNamePath_3 = new FeatureNamePath("lastName");
    ArrayList<FeatureNamePath> _newArrayList = CollectionLiterals.<FeatureNamePath>newArrayList(_featureNamePath_2, _featureNamePath_3);
    FeatureNamePath _featureNamePath_4 = new FeatureNamePath("reader", _newArrayList);
    FeatureNamePath _featureNamePath_5 = new FeatureNamePath("name");
    FeatureNamePath _featureNamePath_6 = new FeatureNamePath("books");
    ArrayList<FeatureNamePath> _newArrayList_1 = CollectionLiterals.<FeatureNamePath>newArrayList(_featureNamePath_5, _featureNamePath_6);
    FeatureNamePath _featureNamePath_7 = new FeatureNamePath("author", _newArrayList_1);
    final ArrayList<FeatureNamePath> paths = CollectionLiterals.<FeatureNamePath>newArrayList(_featureNamePath, _featureNamePath_1, _featureNamePath_4, _featureNamePath_7);
    EClass _bookOnTape = EXTLibraryPackage.eINSTANCE.getBookOnTape();
    this.assertFeatureList(_bookOnTape, paths, "title, minutesLength, firstName, lastName, name, books");
  }
  
  @Test
  public void testResolver3() {
    FeatureNamePath _featureNamePath = new FeatureNamePath("firstName");
    FeatureNamePath _featureNamePath_1 = new FeatureNamePath("lastName");
    FeatureNamePath _featureNamePath_2 = new FeatureNamePath("firstName");
    FeatureNamePath _featureNamePath_3 = new FeatureNamePath("lastName");
    FeatureNamePath _featureNamePath_4 = new FeatureNamePath("firstName");
    FeatureNamePath _featureNamePath_5 = new FeatureNamePath("lastName");
    ArrayList<FeatureNamePath> _newArrayList = CollectionLiterals.<FeatureNamePath>newArrayList(_featureNamePath_4, _featureNamePath_5);
    FeatureNamePath _featureNamePath_6 = new FeatureNamePath("manager", _newArrayList);
    ArrayList<FeatureNamePath> _newArrayList_1 = CollectionLiterals.<FeatureNamePath>newArrayList(_featureNamePath_2, _featureNamePath_3, _featureNamePath_6);
    FeatureNamePath _featureNamePath_7 = new FeatureNamePath("manager", _newArrayList_1);
    final ArrayList<FeatureNamePath> paths = CollectionLiterals.<FeatureNamePath>newArrayList(_featureNamePath, _featureNamePath_1, _featureNamePath_7);
    EClass _employee = EXTLibraryPackage.eINSTANCE.getEmployee();
    this.assertFeatureList(_employee, paths, "firstName, lastName, firstName, lastName, firstName, lastName");
  }
  
  public Library createModel() {
    Library _createLibrary = EXTLibraryFactory.eINSTANCE.createLibrary();
    final Procedure1<Library> _function = new Procedure1<Library>() {
      public void apply(final Library it) {
        Writer _createWriter = EXTLibraryFactory.eINSTANCE.createWriter();
        final Procedure1<Writer> _function = new Procedure1<Writer>() {
          public void apply(final Writer it) {
            it.setFirstName("Test");
            it.setLastName("Writer");
          }
        };
        final Writer writer = ObjectExtensions.<Writer>operator_doubleArrow(_createWriter, _function);
        Writer _createWriter_1 = EXTLibraryFactory.eINSTANCE.createWriter();
        final Procedure1<Writer> _function_1 = new Procedure1<Writer>() {
          public void apply(final Writer it) {
            it.setFirstName("Test2");
            it.setLastName("Writer2");
          }
        };
        final Writer writer2 = ObjectExtensions.<Writer>operator_doubleArrow(_createWriter_1, _function_1);
        EList<Writer> _writers = it.getWriters();
        _writers.add(writer);
        EList<Writer> _writers_1 = it.getWriters();
        _writers_1.add(writer2);
        EList<Book> _books = it.getBooks();
        Book _createBook = EXTLibraryFactory.eINSTANCE.createBook();
        final Procedure1<Book> _function_2 = new Procedure1<Book>() {
          public void apply(final Book it) {
            it.setTitle("Test Book");
            it.setAuthor(writer);
          }
        };
        Book _doubleArrow = ObjectExtensions.<Book>operator_doubleArrow(_createBook, _function_2);
        _books.add(_doubleArrow);
        EList<Item> _stock = it.getStock();
        BookOnTape _createBookOnTape = EXTLibraryFactory.eINSTANCE.createBookOnTape();
        final Procedure1<BookOnTape> _function_3 = new Procedure1<BookOnTape>() {
          public void apply(final BookOnTape it) {
            it.setReader(writer);
            it.setAuthor(writer2);
          }
        };
        BookOnTape _doubleArrow_1 = ObjectExtensions.<BookOnTape>operator_doubleArrow(_createBookOnTape, _function_3);
        _stock.add(_doubleArrow_1);
        Employee _createEmployee = EXTLibraryFactory.eINSTANCE.createEmployee();
        final Procedure1<Employee> _function_4 = new Procedure1<Employee>() {
          public void apply(final Employee it) {
            it.setFirstName("E");
            it.setLastName("1");
          }
        };
        final Employee employee1 = ObjectExtensions.<Employee>operator_doubleArrow(_createEmployee, _function_4);
        Employee _createEmployee_1 = EXTLibraryFactory.eINSTANCE.createEmployee();
        final Procedure1<Employee> _function_5 = new Procedure1<Employee>() {
          public void apply(final Employee it) {
            it.setFirstName("E");
            it.setLastName("2");
            it.setManager(employee1);
          }
        };
        final Employee employee2 = ObjectExtensions.<Employee>operator_doubleArrow(_createEmployee_1, _function_5);
        EList<Employee> _employees = it.getEmployees();
        _employees.add(employee1);
        EList<Employee> _employees_1 = it.getEmployees();
        _employees_1.add(employee2);
      }
    };
    Library _doubleArrow = ObjectExtensions.<Library>operator_doubleArrow(_createLibrary, _function);
    return _doubleArrow;
  }
  
  public void assertFeatureList(final EClass eClass, final List<FeatureNamePath> paths, final CharSequence expected) {
    List<EStructuralFeature> _features = this.resolver.getFeatures(eClass, paths);
    this.assertFeatureList(_features, expected);
  }
  
  public void assertFeatureList(final List<EStructuralFeature> features, final CharSequence expected) {
    String _string = expected.toString();
    final Function1<EStructuralFeature,String> _function = new Function1<EStructuralFeature,String>() {
      public String apply(final EStructuralFeature it) {
        String _name = it.getName();
        return _name;
      }
    };
    List<String> _map = ListExtensions.<EStructuralFeature, String>map(features, _function);
    String _join = IterableExtensions.join(_map, ", ");
    Assert.assertEquals(_string, _join);
  }
}
