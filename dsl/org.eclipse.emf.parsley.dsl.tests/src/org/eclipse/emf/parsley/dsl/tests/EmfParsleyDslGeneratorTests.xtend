package org.eclipse.emf.parsley.dsl.tests

import com.google.inject.Inject
import org.eclipse.emf.parsley.dsl.EmfParsleyDslInjectorProvider
import org.eclipse.emf.parsley.dsl.tests.util.CustomCompilationTestHelper
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.*
import org.eclipse.emf.parsley.dsl.tests.util.GeneratorExpectedResults

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(EmfParsleyDslInjectorProvider))
class EmfParsleyDslGeneratorTests extends EmfParsleyDslAbstractTests {
 
	@Inject extension CustomCompilationTestHelper

	@Test
	def testEmptyModule() {
		inputs.emptyModule.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedModule
= '''
package my.empty;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.ui.plugin.AbstractUIPlugin;

@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
}
''']
		)
	}

	@Test
	def testModuleWithExtends() {
		inputs.moduleWithExtends.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedModule = 			
'''
package my.empty;

import org.eclipse.emf.parsley.dsl.tests.additional.MyTestGuiceModule;
import org.eclipse.ui.plugin.AbstractUIPlugin;

@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends MyTestGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
}
''']
		)
	}

	@Test
	def testEmptyLabelProvider() {
		inputs.emptyLabelProvider.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedModule =
'''
package my.empty;

import my.empty.ui.provider.LabelProviderGen;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends ILabelProvider> bindILabelProvider() {
    return LabelProviderGen.class;
  }
}
'''
expectedLabelProvider = 
'''
package my.empty.ui.provider;

import com.google.inject.Inject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;

@SuppressWarnings("all")
public class LabelProviderGen extends ViewerLabelProvider {
  @Inject
  public LabelProviderGen(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }
}
''']
		)
	}

	@Test
	def testEmptyPropertyDescriptionProvider() {
		inputs.emptyPropertyDescriptionProvider.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedModule =
'''
package my.empty;

import my.empty.ui.provider.FeatureCaptionProviderGen;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends FeatureCaptionProvider> bindFeatureCaptionProvider() {
    return FeatureCaptionProviderGen.class;
  }
}
'''
expectedFeatureCaptionProvider =
'''
package my.empty.ui.provider;

import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;

@SuppressWarnings("all")
public class FeatureCaptionProviderGen extends FeatureCaptionProvider {
}
''']
		)
	}

	@Test
	def testEmptyLabelSpecifications() {
		inputs.emptyLabelSpecifications.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedLabelProvider = 
'''
package my.empty.ui.provider;

import com.google.inject.Inject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;

@SuppressWarnings("all")
public class LabelProviderGen extends ViewerLabelProvider {
  @Inject
  public LabelProviderGen(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }
}
''']
		)
	}

	@Test
	def testLabelSpecifications() {
		inputs.labelSpecifications.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedLabelProvider = 
'''
package my.empty.ui.provider;

import com.google.inject.Inject;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.examples.library.Book;
import org.eclipse.emf.parsley.examples.library.BookOnTape;
import org.eclipse.emf.parsley.examples.library.Borrower;
import org.eclipse.emf.parsley.examples.library.Lendable;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.examples.library.Writer;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class LabelProviderGen extends ViewerLabelProvider {
  @Inject
  public LabelProviderGen(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }
  
  public String text(final Library it) {
    return "foo";
  }
  
  public String text(final Writer writer) {
    String _name = writer.getName();
    return _name;
  }
  
  public String text(final Book it) {
    String _title = it.getTitle();
    return _title;
  }
  
  public String text(final Lendable it) {
    int _copies = it.getCopies();
    String _plus = ("copies: " + Integer.valueOf(_copies));
    return _plus;
  }
  
  public String text(final Borrower it) {
    String _xblockexpression = null;
    {
      EList<Lendable> _borrowed = it.getBorrowed();
      final Function1<Lendable,Integer> _function = new Function1<Lendable,Integer>() {
        public Integer apply(final Lendable b) {
          int _copies = b.getCopies();
          return Integer.valueOf(_copies);
        }
      };
      List<Integer> _map = ListExtensions.<Lendable, Integer>map(_borrowed, _function);
      final String buffer = ("borrowed: " + _map);
      String _upperCase = buffer.toUpperCase();
      _xblockexpression = (_upperCase);
    }
    return _xblockexpression;
  }
  
  public String text(final BookOnTape it) {
    String _title = it.getTitle();
    return _title;
  }
  
  public Object image(final Library it) {
    return "library.jpeg";
  }
  
  public Object image(final Writer writer) {
    Object _xifexpression = null;
    String _name = writer.getName();
    boolean _isNullOrEmpty = StringExtensions.isNullOrEmpty(_name);
    if (_isNullOrEmpty) {
      _xifexpression = "noname.gif";
    } else {
      ImageData _imageData = new ImageData("writer.jpeg");
      _xifexpression = _imageData;
    }
    return _xifexpression;
  }
}
''']
		)
	}

	@Test
	def testPropertyDescriptionSpecifications() {
		inputs.propertyDescriptionSpecifications.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedModule = 
'''
package my.empty;

import my.empty.ui.provider.FeatureCaptionProviderGen;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends FeatureCaptionProvider> bindFeatureCaptionProvider() {
    return FeatureCaptionProviderGen.class;
  }
}
'''
expectedFeatureCaptionProvider = 
'''
package my.empty.ui.provider;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class FeatureCaptionProviderGen extends FeatureCaptionProvider {
  public String text_Library_name(final EStructuralFeature it) {
    return "Name";
  }
  
  public String text_Library_books(final EStructuralFeature it) {
    return "Books";
  }
  
  public String text_Writer_lastName(final EStructuralFeature it) {
    String _name = it.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    return _firstUpper;
  }
}
''']
		)
	}

	@Test
	def testFormPropertyDescriptionSpecifications() {
		inputs.formPropertyDescriptionSpecifications.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedModule = 
'''
package my.empty;

import my.empty.ui.provider.FormFeatureCaptionProviderGen;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends FormFeatureCaptionProvider> bindFormFeatureCaptionProvider() {
    return FormFeatureCaptionProviderGen.class;
  }
}
'''
expectedFormFeatureCaptionProvider = 
'''
package my.empty.ui.provider;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage;
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class FormFeatureCaptionProviderGen extends FormFeatureCaptionProvider {
  public String text_Library_name(final EStructuralFeature it) {
    return "Name";
  }
  
  public String text_Library_books(final EStructuralFeature it) {
    return "Books";
  }
  
  public String text_Writer_lastName(final EStructuralFeature it) {
    String _name = it.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    return _firstUpper;
  }
  
  public Label label_Library_name(final Composite parent, final EStructuralFeature it) {
    Label _createLabel = this.createLabel(parent, "Name");
    return _createLabel;
  }
  
  public Label label_Library_books(final Composite parent, final EStructuralFeature it) {
    EReference _library_Books = EXTLibraryPackage.eINSTANCE.getLibrary_Books();
    Label _createLabel = this.createLabel(parent, _library_Books);
    return _createLabel;
  }
  
  public Label label_Writer_lastName(final Composite parent, final EStructuralFeature it) {
    Label _label = new Label(parent, SWT.NONE);
    final Procedure1<Label> _function = new Procedure1<Label>() {
      public void apply(final Label l) {
        String _name = it.getName();
        l.setText(_name);
      }
    };
    Label _doubleArrow = ObjectExtensions.<Label>operator_doubleArrow(_label, _function);
    return _doubleArrow;
  }
}
''']
		)
	}

	@Test
	def testDialogPropertyDescriptionSpecifications() {
		inputs.dialogPropertyDescriptionSpecifications.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedModule = 
'''
package my.empty;

import my.empty.ui.provider.DialogFeatureCaptionProviderGen;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.ui.provider.DialogFeatureCaptionProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends DialogFeatureCaptionProvider> bindDialogFeatureCaptionProvider() {
    return DialogFeatureCaptionProviderGen.class;
  }
}
'''
expectedDialogFeatureCaptionProvider = 
'''
package my.empty.ui.provider;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage;
import org.eclipse.emf.parsley.ui.provider.DialogFeatureCaptionProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class DialogFeatureCaptionProviderGen extends DialogFeatureCaptionProvider {
  public String text_Library_name(final EStructuralFeature it) {
    return "Name";
  }
  
  public String text_Library_books(final EStructuralFeature it) {
    return "Books";
  }
  
  public String text_Writer_lastName(final EStructuralFeature it) {
    String _name = it.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    return _firstUpper;
  }
  
  public Label label_Library_name(final Composite parent, final EStructuralFeature it) {
    Label _createLabel = this.createLabel(parent, "Name");
    return _createLabel;
  }
  
  public Label label_Library_books(final Composite parent, final EStructuralFeature it) {
    EReference _library_Books = EXTLibraryPackage.eINSTANCE.getLibrary_Books();
    Label _createLabel = this.createLabel(parent, _library_Books);
    return _createLabel;
  }
  
  public Label label_Writer_lastName(final Composite parent, final EStructuralFeature it) {
    Label _label = new Label(parent, SWT.NONE);
    final Procedure1<Label> _function = new Procedure1<Label>() {
      public void apply(final Label l) {
        String _name = it.getName();
        l.setText(_name);
      }
    };
    Label _doubleArrow = ObjectExtensions.<Label>operator_doubleArrow(_label, _function);
    return _doubleArrow;
  }
}
''']
		)
	}

	@Test
	def testFeaturesSpecifications() {
		inputs.featuresSpecifications.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedModule =
'''
package my.empty;

import my.empty.ui.provider.FeaturesProviderGen;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends FeaturesProvider> bindFeaturesProvider() {
    return FeaturesProviderGen.class;
  }
}
'''
expectedFeatureProvider = 
'''
package my.empty.ui.provider;

import org.eclipse.emf.parsley.ui.provider.EClassToEStructuralFeatureAsStringsMap;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;

@SuppressWarnings("all")
public class FeaturesProviderGen extends FeaturesProvider {
  @Override
  public void buildStringMap(final EClassToEStructuralFeatureAsStringsMap stringMap) {
    super.buildStringMap(stringMap);
    
    stringMap.mapTo("org.eclipse.emf.parsley.examples.library.Library",
      "name");
    stringMap.mapTo("org.eclipse.emf.parsley.examples.library.Writer",
      "firstName", "lastName", "books");
  }
}
''']
		)
	}

	@Test
	def testFormControlFactory() {
		inputs.formControlFactory.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedModule =
'''
package my.empty;

import my.empty.binding.FormControlFactoryGen;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.binding.FormControlFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;

@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends FormControlFactory> bindFormControlFactory() {
    return FormControlFactoryGen.class;
  }
}
'''
expectedFormControlFactory =
'''
package my.empty.binding;

import java.util.List;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.parsley.binding.FormControlFactory;
import org.eclipse.emf.parsley.examples.library.Book;
import org.eclipse.emf.parsley.examples.library.Borrower;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.examples.library.Writer;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class FormControlFactoryGen extends FormControlFactory {
  public Control control_Library_name(final Library it) {
    return null;
  }
  
  public Control control_Writer_books(final Writer it) {
    EList<Book> _books = it.getBooks();
    final Function1<Book,String> _function = new Function1<Book,String>() {
      public String apply(final Book it) {
        String _title = it.getTitle();
        return _title;
      }
    };
    List<String> _map = ListExtensions.<Book, String>map(_books, _function);
    String _join = IterableExtensions.join(_map, ", ");
    Label _createLabel = this.createLabel(_join);
    return _createLabel;
  }
  
  public Control control_Writer_name(final DataBindingContext dataBindingContext, final IObservableValue observableValue) {
    Control control = createControl_Writer_name();
    dataBindingContext.bindValue(
    	createTarget_Writer_name(control),
    	observableValue);
    return control;
  }
  
  protected Control createControl_Writer_name() {
    Label _createLabel = this.createLabel(this.parent, "");
    return _createLabel;
  }
  
  protected IObservableValue createTarget_Writer_name(final Control it) {
    ISWTObservableValue _observeText = SWTObservables.observeText(it);
    return _observeText;
  }
  
  public Control control_Writer_firstName(final DataBindingContext dataBindingContext, final IObservableValue observableValue) {
    Control control = createControl_Writer_firstName();
    dataBindingContext.bindValue(
    	createTarget_Writer_firstName(control),
    	observableValue);
    return control;
  }
  
  protected Control createControl_Writer_firstName() {
    FormToolkit _toolkit = this.getToolkit();
    Label _createLabel = _toolkit.createLabel(this.parent, "");
    return _createLabel;
  }
  
  protected IObservableValue createTarget_Writer_firstName(final Control it) {
    ISWTObservableValue _observeText = SWTObservables.observeText(it, SWT.Modify);
    return _observeText;
  }
  
  public Control control_Borrower_firstName(final Borrower it) {
    String _firstName = it.getFirstName();
    Text _createText = this.createText(_firstName, SWT.MULTI, SWT.BORDER, 
      SWT.WRAP, SWT.V_SCROLL);
    return _createText;
  }
}
''']
		)
	}

	@Test
	def testDialogControlFactory() {
		inputs.dialogControlFactory.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedModule =
'''
package my.empty;

import my.empty.binding.DialogControlFactoryGen;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.binding.DialogControlFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;

@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends DialogControlFactory> bindDialogControlFactory() {
    return DialogControlFactoryGen.class;
  }
}
'''
expectedDialogControlFactory =
'''
package my.empty.binding;

import java.util.List;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.parsley.binding.DialogControlFactory;
import org.eclipse.emf.parsley.examples.library.Book;
import org.eclipse.emf.parsley.examples.library.Borrower;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.examples.library.Writer;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class DialogControlFactoryGen extends DialogControlFactory {
  public Control control_Library_name(final Library it) {
    return null;
  }
  
  public Control control_Writer_books(final Writer it) {
    EList<Book> _books = it.getBooks();
    final Function1<Book,String> _function = new Function1<Book,String>() {
      public String apply(final Book it) {
        String _title = it.getTitle();
        return _title;
      }
    };
    List<String> _map = ListExtensions.<Book, String>map(_books, _function);
    String _join = IterableExtensions.join(_map, ", ");
    Label _createLabel = this.createLabel(_join);
    return _createLabel;
  }
  
  public Control control_Writer_name(final DataBindingContext dataBindingContext, final IObservableValue observableValue) {
    Control control = createControl_Writer_name();
    dataBindingContext.bindValue(
    	createTarget_Writer_name(control),
    	observableValue);
    return control;
  }
  
  protected Control createControl_Writer_name() {
    Label _createLabel = this.createLabel(this.parent, "");
    return _createLabel;
  }
  
  protected IObservableValue createTarget_Writer_name(final Control it) {
    ISWTObservableValue _observeText = SWTObservables.observeText(it);
    return _observeText;
  }
  
  public Control control_Writer_firstName(final DataBindingContext dataBindingContext, final IObservableValue observableValue) {
    Control control = createControl_Writer_firstName();
    dataBindingContext.bindValue(
    	createTarget_Writer_firstName(control),
    	observableValue);
    return control;
  }
  
  protected Control createControl_Writer_firstName() {
    Label _createLabel = this.createLabel(this.parent, "");
    return _createLabel;
  }
  
  protected IObservableValue createTarget_Writer_firstName(final Control it) {
    ISWTObservableValue _observeText = SWTObservables.observeText(it, SWT.Modify);
    return _observeText;
  }
  
  public Control control_Borrower_firstName(final Borrower it) {
    String _firstName = it.getFirstName();
    Text _createText = this.createText(_firstName, SWT.MULTI, SWT.BORDER, 
      SWT.WRAP, SWT.V_SCROLL);
    return _createText;
  }
}
''']
		)
	}

	@Test
	def testViewerContentProviderSpecifications() {
		inputs.viewerContentProviderSpecifications.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedModule =
'''
package my.empty;

import my.empty.edit.ui.provider.ViewerContentProviderGen;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends IContentProvider> bindIContentProvider() {
    return ViewerContentProviderGen.class;
  }
}
'''
expectedViewerContentProvider =
'''
package my.empty.edit.ui.provider;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider;
import org.eclipse.emf.parsley.examples.library.Book;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.examples.library.Writer;

@SuppressWarnings("all")
public class ViewerContentProviderGen extends ViewerContentProvider {
  @Inject
  public ViewerContentProviderGen(final AdapterFactory adapterFactory) {
    super(adapterFactory);
  }
  
  public Object children(final Library it) {
    EList<Book> _books = it.getBooks();
    EList<Writer> _writers = it.getWriters();
    Iterable<EObject> _plus = Iterables.<EObject>concat(_books, _writers);
    return _plus;
  }
  
  public Object children(final Writer writer) {
    EList<Book> _books = writer.getBooks();
    return _books;
  }
  
  public Object children(final Book it) {
    Writer _author = it.getAuthor();
    return _author;
  }
}
''']
		)
	}

	@Test
	def testProposalSpecifications() {
		inputs.proposalsSpecifications.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedModule =
'''
package my.empty;

import my.empty.binding.ProposalCreatorGen;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.binding.ProposalCreator;
import org.eclipse.ui.plugin.AbstractUIPlugin;

@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends ProposalCreator> bindProposalCreator() {
    return ProposalCreatorGen.class;
  }
}
'''
expectedProposalCreator =
'''
package my.empty.binding;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.binding.ProposalCreator;
import org.eclipse.emf.parsley.examples.library.Book;
import org.eclipse.emf.parsley.examples.library.Borrower;
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.examples.library.Writer;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class ProposalCreatorGen extends ProposalCreator {
  public List<? extends Object> proposals_Library_name(final Library it, final EStructuralFeature feature) {
    ArrayList<String> _newArrayList = CollectionLiterals.<String>newArrayList("foo", "bar");
    return _newArrayList;
  }
  
  public List<? extends Object> proposals_Writer_books(final Writer it, final EStructuralFeature feature) {
    EList<Book> _books = it.getBooks();
    return _books;
  }
  
  public List<? extends Object> proposals_Book_author(final Book it, final EStructuralFeature feature) {
    Writer _createWriter = EXTLibraryFactory.eINSTANCE.createWriter();
    final Procedure1<Writer> _function = new Procedure1<Writer>() {
      public void apply(final Writer it) {
        it.setName("Foo");
      }
    };
    Writer _doubleArrow = ObjectExtensions.<Writer>operator_doubleArrow(_createWriter, _function);
    Writer _createWriter_1 = EXTLibraryFactory.eINSTANCE.createWriter();
    final Procedure1<Writer> _function_1 = new Procedure1<Writer>() {
      public void apply(final Writer it) {
        it.setName("Bar");
      }
    };
    Writer _doubleArrow_1 = ObjectExtensions.<Writer>operator_doubleArrow(_createWriter_1, _function_1);
    ArrayList<Writer> _newArrayList = CollectionLiterals.<Writer>newArrayList(_doubleArrow, _doubleArrow_1);
    return _newArrayList;
  }
  
  public List<? extends Object> proposals_Borrower_borrowed(final Borrower it, final EStructuralFeature feature) {
    List<Object> _defaultProposals = this.defaultProposals(feature);
    final Procedure1<List<Object>> _function = new Procedure1<List<Object>>() {
      public void apply(final List<Object> it) {
        Book _createBook = EXTLibraryFactory.eINSTANCE.createBook();
        final Procedure1<Book> _function = new Procedure1<Book>() {
          public void apply(final Book it) {
            it.setTitle("Fake Book");
          }
        };
        Book _doubleArrow = ObjectExtensions.<Book>operator_doubleArrow(_createBook, _function);
        it.add(_doubleArrow);
      }
    };
    final List<Object> p = ObjectExtensions.<List<Object>>operator_doubleArrow(_defaultProposals, _function);
    return p;
  }
}
''']
		)
	}

	@Test
	def testViewsSpecifications() {
		inputs.multipleViewsSpecifications.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedModule =
'''
package my.test;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.ui.plugin.AbstractUIPlugin;

@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
}
'''
expectedPluginXmlGen =
'''
<?xml version="1.0" encoding="UTF-8"?>
<?eclipse version="3.4"?>
<plugin>
	<extension
	      point="org.eclipse.ui.views">
	    <view
	          category="org.eclipse.emf.parsley"
	          class="my.test.TestExecutableExtensionFactory:org.eclipse.emf.parsley.views.AbstractSaveableTreeView"
	          id="my.view.tree.part"
	          name="My Tree View"
	          restorable="true">
	    </view>
	    <view
	          category="my.view.category"
	          class="my.test.TestExecutableExtensionFactory:org.eclipse.emf.parsley.views.AbstractSaveableTreeFormView"
	          id="my.view.form.part"
	          name="My Tree Form View"
	          restorable="true">
	    </view>
	</extension>
</plugin>
'''
		])
	}

	def private assertCorrectJavaCodeGeneration(CharSequence input,
			GeneratorExpectedResults expected) {
		input.compileAll [
			for (e : allGeneratedResources.entrySet) {
				if (e.key.endsWith("ModuleGen.java")) {
					if (expected.expectedModule != null)
						assertEqualsStrings(expected.expectedModule, e.value)
				} else if (e.key.endsWith("FormFeatureCaptionProviderGen.java")) {
					if (expected.expectedFormFeatureCaptionProvider != null)
						assertEqualsStrings(expected.expectedFormFeatureCaptionProvider, e.value)
				} else if (e.key.endsWith("DialogFeatureCaptionProviderGen.java")) {
					if (expected.expectedDialogFeatureCaptionProvider != null)
						assertEqualsStrings(expected.expectedDialogFeatureCaptionProvider, e.value)
				} else if (e.key.endsWith("FeatureCaptionProviderGen.java")) {
					if (expected.expectedFeatureCaptionProvider != null)
						assertEqualsStrings(expected.expectedFeatureCaptionProvider, e.value)
				} else if (e.key.endsWith("FeaturesProviderGen.java")) {
					if (expected.expectedFeatureProvider != null)
						assertEqualsStrings(expected.expectedFeatureProvider, e.value)
				} else if (e.key.endsWith("LabelProviderGen.java")) {
					if (expected.expectedLabelProvider != null)
						assertEqualsStrings(expected.expectedLabelProvider, e.value)
				} else if (e.key.endsWith("FormControlFactoryGen.java")) {
					if (expected.expectedFormControlFactory != null)
						assertEqualsStrings(expected.expectedFormControlFactory, e.value)
				} else if (e.key.endsWith("DialogControlFactoryGen.java")) {
					if (expected.expectedDialogControlFactory != null)
						assertEqualsStrings(expected.expectedDialogControlFactory, e.value)
				} else if (e.key.endsWith("ViewerContentProviderGen.java")) {
					if (expected.expectedViewerContentProvider != null)
						assertEqualsStrings(expected.expectedViewerContentProvider, e.value)
				} else if (e.key.endsWith("ProposalCreatorGen.java")) {
					if (expected.expectedProposalCreator != null)
						assertEqualsStrings(expected.expectedProposalCreator, e.value)
				} else if (e.key.endsWith(".xml_emfparsley_gen")) {
					if (expected.expectedPluginXmlGen != null)
						assertEqualsStrings(expected.expectedPluginXmlGen, e.value)
				} else
					fail("unexpected generated code: " + e.value)
			}
			
			// this will issue Java generation
			compileToJava
		]
	}
}