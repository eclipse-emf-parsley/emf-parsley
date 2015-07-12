/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.tests

import com.google.inject.Inject
import org.eclipse.emf.parsley.dsl.tests.util.CustomCompilationTestHelper
import org.eclipse.emf.parsley.dsl.tests.util.EmfParsleyDslInjectorProviderCustom
import org.eclipse.emf.parsley.dsl.tests.util.GeneratorExpectedResults
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.TemporaryFolder
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.*

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(EmfParsleyDslInjectorProviderCustom))
class EmfParsleyDslGeneratorTests extends EmfParsleyDslAbstractTests {
	
	@Rule
	@Inject public TemporaryFolder temporaryFolder
 
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
	def testLabelProvider() {
		inputs.labelProvider.assertCorrectJavaCodeGeneration(
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
      final Function1<Lendable, Integer> _function = new Function1<Lendable, Integer>() {
        public Integer apply(final Lendable b) {
          return Integer.valueOf(b.getCopies());
        }
      };
      List<Integer> _map = ListExtensions.<Lendable, Integer>map(_borrowed, _function);
      final String buffer = ("borrowed: " + _map);
      _xblockexpression = buffer.toUpperCase();
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
      _xifexpression = new ImageData("writer.jpeg");
    }
    return _xifexpression;
  }
}
''']
		)
	}

	@Test
	def testLabelProviderWithExtends() {
		inputs.labelProviderWithExtends.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedLabelProvider = 
'''
package my.empty.ui.provider;

import com.google.inject.Inject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.dsl.tests.inputs.TestLabelProvider;

@SuppressWarnings("all")
public class LabelProviderGen extends TestLabelProvider {
  @Inject
  public LabelProviderGen(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }
}
''']
		)
	}

	@Test
	def testLabelProviderWithFields() {
		inputs.labelProviderWithFields.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedLabelProvider = 
'''
package my.empty.ui.provider;

import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.dsl.tests.inputs.TestExtensions;
import org.eclipse.emf.parsley.dsl.tests.inputs.TestExtensions2;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class LabelProviderGen extends ViewerLabelProvider {
  @Inject
  private ILabelProvider parentLabelProvider;
  
  public ILabelProvider getParentLabelProvider() {
    return this.parentLabelProvider;
  }
  
  public void setParentLabelProvider(final ILabelProvider parentLabelProvider) {
    this.parentLabelProvider = parentLabelProvider;
  }
  
  @Extension
  private final TestExtensions myExtensions = new TestExtensions();
  
  public TestExtensions getMyExtensions() {
    return this.myExtensions;
  }
  
  private final TestExtensions2 myExtensions2 = new TestExtensions2();
  
  public TestExtensions2 getMyExtensions2() {
    return this.myExtensions2;
  }
  
  private final List<String> listOfString = ObjectExtensions.<ArrayList<String>>operator_doubleArrow(new ArrayList<String>(), new Procedure1<ArrayList<String>>() {
    public void apply(final ArrayList<String> it) {
      it.add("first");
      it.add("second");
    }
  });
  
  public List<String> getListOfString() {
    return this.listOfString;
  }
  
  @Inject
  public LabelProviderGen(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }
  
  public String text(final Library it) {
    final ArrayList<Object> myList = new ArrayList<Object>();
    this.myExtensions.printList(myList);
    this.myExtensions2.printList2(myList);
    String _text = this.parentLabelProvider.getText(it);
    return ("result: " + _text);
  }
}
''']
		)
	}

	@Test
	def testTableLabelProvider() {
		inputs.tableLabelProvider.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedTableLabelProvider = 
'''
package my.empty.ui.provider;

import org.eclipse.emf.parsley.examples.library.Book;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.examples.library.Writer;
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class TableLabelProviderGen extends TableColumnLabelProvider {
  public String text_Library_name(final Library it) {
    return "Name";
  }
  
  public String text_Library_books(final Library it) {
    return "Books";
  }
  
  public String text_Writer_lastName(final Writer it) {
    String _name = it.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    return _firstUpper;
  }
  
  public Object image_Book_author(final Book it) {
    Object _xifexpression = null;
    Writer _author = it.getAuthor();
    String _name = _author.getName();
    boolean _isNullOrEmpty = StringExtensions.isNullOrEmpty(_name);
    if (_isNullOrEmpty) {
      _xifexpression = "noname.gif";
    } else {
      _xifexpression = new ImageData("writer.jpeg");
    }
    return _xifexpression;
  }
}
''']
		)
	}

	@Test
	def testTableLabelProviderWithExtends() {
		inputs.tableLabelProviderWithExtends.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedTableLabelProvider = 
'''
package my.empty.ui.provider;

import org.eclipse.emf.parsley.dsl.tests.inputs.TestTableColumnLabelProvider;

@SuppressWarnings("all")
public class TableLabelProviderGen extends TestTableColumnLabelProvider {
}
''']
		)
	}

	@Test
	def testTableLabelProviderWithFields() {
		inputs.tableLabelProviderWithFields.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedTableLabelProvider = 
'''
package my.empty.ui.provider;

import com.google.inject.Inject;
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;

@SuppressWarnings("all")
public class TableLabelProviderGen extends TableColumnLabelProvider {
  @Inject
  private ILabelProvider parentLabelProvider;
  
  public ILabelProvider getParentLabelProvider() {
    return this.parentLabelProvider;
  }
  
  public void setParentLabelProvider(final ILabelProvider parentLabelProvider) {
    this.parentLabelProvider = parentLabelProvider;
  }
}
''']
		)
	}

	@Test
	def testFeatureCaptionProvider() {
		inputs.featureCaptionProvider.assertCorrectJavaCodeGeneration(
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
	def testFeatureCaptionProviderWithExtends() {
		inputs.featureCaptionProviderWithExtends.assertCorrectJavaCodeGeneration(
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
import org.eclipse.emf.parsley.dsl.tests.inputs.TestFeatureCaptionProvider;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class FeatureCaptionProviderGen extends TestFeatureCaptionProvider {
  public String text_Library_name(final EStructuralFeature it) {
    return "Name";
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
	def testFormFeatureCaptionProvider() {
		inputs.formFeatureCaptionProvider.assertCorrectJavaCodeGeneration(
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

import org.eclipse.emf.ecore.EClass;
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
    EClass _library = EXTLibraryPackage.eINSTANCE.getLibrary();
    EReference _library_Books = EXTLibraryPackage.eINSTANCE.getLibrary_Books();
    Label _createLabel = this.createLabel(parent, _library, _library_Books);
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
    return ObjectExtensions.<Label>operator_doubleArrow(_label, _function);
  }
}
''']
		)
	}

	@Test
	def testFormFeatureCaptionProviderWithExtends() {
		inputs.formFeatureCaptionProviderWithExtends.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedFormFeatureCaptionProvider = 
'''
package my.empty.ui.provider;

import org.eclipse.emf.parsley.dsl.tests.inputs.TestFormFeatureCaptionProvider;

@SuppressWarnings("all")
public class FormFeatureCaptionProviderGen extends TestFormFeatureCaptionProvider {
}
''']
		)
	}

	@Test
	def testDialogFeatureCaptionProvider() {
		inputs.dialogFeatureCaptionProvider.assertCorrectJavaCodeGeneration(
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

import org.eclipse.emf.ecore.EClass;
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
    EClass _library = EXTLibraryPackage.eINSTANCE.getLibrary();
    EReference _library_Books = EXTLibraryPackage.eINSTANCE.getLibrary_Books();
    Label _createLabel = this.createLabel(parent, _library, _library_Books);
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
    return ObjectExtensions.<Label>operator_doubleArrow(_label, _function);
  }
}
''']
		)
	}

	@Test
	def testDialogFeatureCaptionProviderWithExtends() {
		inputs.dialogFeatureCaptionProviderWithExtends.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedDialogFeatureCaptionProvider = 
'''
package my.empty.ui.provider;

import org.eclipse.emf.parsley.dsl.tests.inputs.TestDialogFeatureCaptionProvider;

@SuppressWarnings("all")
public class DialogFeatureCaptionProviderGen extends TestDialogFeatureCaptionProvider {
}
''']
		)
	}

	@Test
	def testFeaturesProvider() {
		inputs.featuresProvider.assertCorrectJavaCodeGeneration(
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
expectedFeaturesProvider = 
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
	def testFeaturesProviderWithExtends() {
		inputs.featuresProviderWithExtends.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedFeaturesProvider = 
'''
package my.empty.ui.provider;

import org.eclipse.emf.parsley.dsl.tests.inputs.TestFeaturesProvider;
import org.eclipse.emf.parsley.ui.provider.EClassToEStructuralFeatureAsStringsMap;

@SuppressWarnings("all")
public class FeaturesProviderGen extends TestFeaturesProvider {
  @Override
  public void buildStringMap(final EClassToEStructuralFeatureAsStringsMap stringMap) {
    super.buildStringMap(stringMap);
    
  }
}
''']
		)
	}

	@Test
	def testTableFeaturesProvider() {
		inputs.tableFeaturesProvider.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedModule =
'''
package my.empty;

import my.empty.ui.provider.TableFeaturesProviderGen;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends TableFeaturesProvider> bindTableFeaturesProvider() {
    return TableFeaturesProviderGen.class;
  }
}
'''
expectedTableFeaturesProvider = 
'''
package my.empty.ui.provider;

import org.eclipse.emf.parsley.ui.provider.EClassToEStructuralFeatureAsStringsMap;
import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider;

@SuppressWarnings("all")
public class TableFeaturesProviderGen extends TableFeaturesProvider {
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
	def testTableFeaturesProviderWithExtends() {
		inputs.tableFeaturesProviderWithExtends.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedTableFeaturesProvider = 
'''
package my.empty.ui.provider;

import org.eclipse.emf.parsley.dsl.tests.inputs.TestTableFeaturesProvider;
import org.eclipse.emf.parsley.ui.provider.EClassToEStructuralFeatureAsStringsMap;

@SuppressWarnings("all")
public class TableFeaturesProviderGen extends TestTableFeaturesProvider {
  @Override
  public void buildStringMap(final EClassToEStructuralFeatureAsStringsMap stringMap) {
    super.buildStringMap(stringMap);
    
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
import org.eclipse.emf.parsley.composite.FormControlFactory;
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
import org.eclipse.emf.parsley.composite.FormControlFactory;
import org.eclipse.emf.parsley.examples.library.Book;
import org.eclipse.emf.parsley.examples.library.Borrower;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.examples.library.Writer;
import org.eclipse.emf.parsley.util.DatabindingUtil;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
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
    final Function1<Book, String> _function = new Function1<Book, String>() {
      public String apply(final Book it) {
        return it.getTitle();
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
    Composite _parent = this.getParent();
    return this.createLabel(_parent, "");
  }
  
  protected IObservableValue createTarget_Writer_name(final Control it) {
    return DatabindingUtil.observeText(it);
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
    Composite _parent = this.getParent();
    Label _createLabel = _toolkit.createLabel(_parent, "");
    return _createLabel;
  }
  
  protected IObservableValue createTarget_Writer_firstName(final Control it) {
    ISWTObservableValue _observeText = DatabindingUtil.observeText(it, SWT.Modify);
    return _observeText;
  }
  
  public Control control_Borrower_firstName(final Borrower it) {
    String _firstName = it.getFirstName();
    return this.createText(_firstName, SWT.MULTI, SWT.BORDER, 
      SWT.WRAP, SWT.V_SCROLL);
  }
}
''']
		)
	}

	@Test
	def testFormControlFactoryWithExtends() {
		inputs.formControlFactoryWithExtends.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedFormControlFactory =
'''
package my.empty.binding;

import org.eclipse.emf.parsley.dsl.tests.inputs.TestFormControlFactory;

@SuppressWarnings("all")
public class FormControlFactoryGen extends TestFormControlFactory {
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
import org.eclipse.emf.parsley.composite.DialogControlFactory;
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
import org.eclipse.emf.parsley.composite.DialogControlFactory;
import org.eclipse.emf.parsley.examples.library.Book;
import org.eclipse.emf.parsley.examples.library.Borrower;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.examples.library.Writer;
import org.eclipse.emf.parsley.util.DatabindingUtil;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
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
    final Function1<Book, String> _function = new Function1<Book, String>() {
      public String apply(final Book it) {
        return it.getTitle();
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
    Composite _parent = this.getParent();
    return this.createLabel(_parent, "");
  }
  
  protected IObservableValue createTarget_Writer_name(final Control it) {
    return DatabindingUtil.observeText(it);
  }
  
  public Control control_Writer_firstName(final DataBindingContext dataBindingContext, final IObservableValue observableValue) {
    Control control = createControl_Writer_firstName();
    dataBindingContext.bindValue(
    	createTarget_Writer_firstName(control),
    	observableValue);
    return control;
  }
  
  protected Control createControl_Writer_firstName() {
    Composite _parent = this.getParent();
    Label _createLabel = this.createLabel(_parent, "");
    return _createLabel;
  }
  
  protected IObservableValue createTarget_Writer_firstName(final Control it) {
    ISWTObservableValue _observeText = DatabindingUtil.observeText(it, SWT.Modify);
    return _observeText;
  }
  
  public Control control_Borrower_firstName(final Borrower it) {
    String _firstName = it.getFirstName();
    return this.createText(_firstName, SWT.MULTI, SWT.BORDER, 
      SWT.WRAP, SWT.V_SCROLL);
  }
}
''']
		)
	}

	@Test
	def testDialogControlFactoryWithExtends() {
		inputs.dialogControlFactoryWithExtends.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedDialogControlFactory =
'''
package my.empty.binding;

import org.eclipse.emf.parsley.dsl.tests.inputs.TestDialogControlFactory;

@SuppressWarnings("all")
public class DialogControlFactoryGen extends TestDialogControlFactory {
}
''']
		)
	}

	@Test
	def testViewerContentProvider() {
		inputs.viewerContentProvider.assertCorrectJavaCodeGeneration(
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
import com.google.common.collect.Iterators;
import com.google.inject.Inject;
import java.util.Iterator;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
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
  
  public Object elements(final Resource it) {
    TreeIterator<EObject> _allContents = it.getAllContents();
    Iterator<Library> _filter = Iterators.<Library>filter(_allContents, Library.class);
    return _filter;
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
	def testViewerContentProviderWithExtends() {
		inputs.viewerContentProviderWithExtends.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedViewerContentProvider =
'''
package my.empty.edit.ui.provider;

import com.google.inject.Inject;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.parsley.dsl.tests.inputs.TestViewerContentProvider;

@SuppressWarnings("all")
public class ViewerContentProviderGen extends TestViewerContentProvider {
  @Inject
  public ViewerContentProviderGen(final AdapterFactory adapterFactory) {
    super(adapterFactory);
  }
}
''']
		)
	}

	@Test
	def testProposalCreator() {
		inputs.proposalCreator.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedModule =
'''
package my.empty;

import my.empty.binding.ProposalCreatorGen;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.composite.ProposalCreator;
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

import java.util.List;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.composite.ProposalCreator;
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
  public List<?> proposals_Library_name(final Library it, final EStructuralFeature feature) {
    return CollectionLiterals.<String>newArrayList("foo", "bar");
  }
  
  public List<?> proposals_Writer_books(final Writer it, final EStructuralFeature feature) {
    return it.getBooks();
  }
  
  public List<?> proposals_Book_author(final Book it, final EStructuralFeature feature) {
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
    return CollectionLiterals.<Writer>newArrayList(_doubleArrow, _doubleArrow_1);
  }
  
  public List<?> proposals_Borrower_borrowed(final Borrower it, final EStructuralFeature feature) {
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
	def testProposalCreatorWithExtends() {
		inputs.proposalCreatorWithExtends.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedProposalCreator =
'''
package my.empty.binding;

import org.eclipse.emf.parsley.dsl.tests.inputs.TestProposalCreator;

@SuppressWarnings("all")
public class ProposalCreatorGen extends TestProposalCreator {
}
''']
		)
	}

	@Test
	def testMenuBuilder() {
'''
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer

module my.empty {
	
	menuBuilder {
		val EXTLibraryFactory libraryFactory = EXTLibraryFactory.eINSTANCE;
		
		menus {
			Object -> #[
				actionRedo,
				actionUndo,
				separator,
				actionCopy,
				actionPaste
			]
			
			Writer -> {
				#[
					actionUndo,
					separator,
					submenu("Submenu1", #[
						actionCopy,
						submenu("Submenu2", #[
							actionCut
						])
					]),
					actionPaste
				]
			}
		}
		
		emfMenus {
			Library lib -> newArrayList(
				actionAdd("New Writer", lib.writers, 
					libraryFactory.createWriter => [
						name = "This is a new writer"
					]
				)
			)
			Writer -> #[
				actionAdd("New book", books,
					libraryFactory.createBook => [
						title = "New book"
					]
				)
			]
		}
	}
}
'''
		.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedModule =
'''
package my.empty;

import my.empty.edit.action.MenuBuilderGen;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.edit.action.EditingMenuBuilder;
import org.eclipse.ui.plugin.AbstractUIPlugin;

@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends EditingMenuBuilder> bindEditingMenuBuilder() {
    return MenuBuilderGen.class;
  }
}
'''
expectedMenuBuilder =
'''
package my.empty.edit.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.parsley.edit.action.EditingMenuBuilder;
import org.eclipse.emf.parsley.edit.action.IMenuContributionSpecification;
import org.eclipse.emf.parsley.examples.library.Book;
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.examples.library.Writer;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class MenuBuilderGen extends EditingMenuBuilder {
  private final EXTLibraryFactory libraryFactory = EXTLibraryFactory.eINSTANCE;
  
  public EXTLibraryFactory getLibraryFactory() {
    return this.libraryFactory;
  }
  
  public List<IMenuContributionSpecification> menuContributions(final Object it) {
    IMenuContributionSpecification _actionRedo = this.actionRedo();
    IMenuContributionSpecification _actionUndo = this.actionUndo();
    IMenuContributionSpecification _separator = this.separator();
    IMenuContributionSpecification _actionCopy = this.actionCopy();
    IMenuContributionSpecification _actionPaste = this.actionPaste();
    return Collections.<IMenuContributionSpecification>unmodifiableList(CollectionLiterals.<IMenuContributionSpecification>newArrayList(_actionRedo, _actionUndo, _separator, _actionCopy, _actionPaste));
  }
  
  public List<IMenuContributionSpecification> menuContributions(final Writer it) {
    IMenuContributionSpecification _actionUndo = this.actionUndo();
    IMenuContributionSpecification _separator = this.separator();
    IMenuContributionSpecification _actionCopy = this.actionCopy();
    IMenuContributionSpecification _actionCut = this.actionCut();
    IMenuContributionSpecification _submenu = this.submenu("Submenu2", Collections.<IMenuContributionSpecification>unmodifiableList(CollectionLiterals.<IMenuContributionSpecification>newArrayList(_actionCut)));
    IMenuContributionSpecification _submenu_1 = this.submenu("Submenu1", Collections.<IMenuContributionSpecification>unmodifiableList(CollectionLiterals.<IMenuContributionSpecification>newArrayList(_actionCopy, _submenu)));
    IMenuContributionSpecification _actionPaste = this.actionPaste();
    return Collections.<IMenuContributionSpecification>unmodifiableList(CollectionLiterals.<IMenuContributionSpecification>newArrayList(_actionUndo, _separator, _submenu_1, _actionPaste));
  }
  
  public List<IMenuContributionSpecification> emfMenuContributions(final Library lib) {
    EList<Writer> _writers = lib.getWriters();
    Writer _createWriter = this.libraryFactory.createWriter();
    final Procedure1<Writer> _function = new Procedure1<Writer>() {
      public void apply(final Writer it) {
        it.setName("This is a new writer");
      }
    };
    Writer _doubleArrow = ObjectExtensions.<Writer>operator_doubleArrow(_createWriter, _function);
    IMenuContributionSpecification _actionAdd = this.<Writer>actionAdd("New Writer", _writers, _doubleArrow);
    ArrayList<IMenuContributionSpecification> _newArrayList = CollectionLiterals.<IMenuContributionSpecification>newArrayList(_actionAdd);
    return _newArrayList;
  }
  
  public List<IMenuContributionSpecification> emfMenuContributions(final Writer it) {
    EList<Book> _books = it.getBooks();
    Book _createBook = this.libraryFactory.createBook();
    final Procedure1<Book> _function = new Procedure1<Book>() {
      public void apply(final Book it) {
        it.setTitle("New book");
      }
    };
    Book _doubleArrow = ObjectExtensions.<Book>operator_doubleArrow(_createBook, _function);
    IMenuContributionSpecification _actionAdd = this.<Book>actionAdd("New book", _books, _doubleArrow);
    return Collections.<IMenuContributionSpecification>unmodifiableList(CollectionLiterals.<IMenuContributionSpecification>newArrayList(_actionAdd));
  }
}
''']
		)
	}

	@Test
	def testConfigurator() {
		inputs.configuratorExample
		.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedModule =
'''
package my.empty;

import my.empty.config.ConfiguratorGen;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.ui.plugin.AbstractUIPlugin;

@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends Configurator> bindConfigurator() {
    return ConfiguratorGen.class;
  }
}
'''
expectedConfigurator =
'''
package my.empty.config;

import com.google.common.collect.Iterables;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.examples.library.Writer;

@SuppressWarnings("all")
public class ConfiguratorGen extends Configurator {
  private final EXTLibraryPackage libraryPackage = EXTLibraryPackage.eINSTANCE;
  
  public EXTLibraryPackage getLibraryPackage() {
    return this.libraryPackage;
  }
  
  public URI resourceURI(final Library lib) {
    URI _createFileURI = URI.createFileURI("file:/foo");
    return _createFileURI;
  }
  
  public URI resourceURI(final Writer it) {
    return null;
  }
  
  public EClass eClass(final Library lib) {
    EClass _library = this.libraryPackage.getLibrary();
    return _library;
  }
  
  public EClass eClass(final Writer it) {
    EClass _writer = this.libraryPackage.getWriter();
    return _writer;
  }
  
  public EStructuralFeature eStructuralFeature(final Library lib) {
    EReference _library_Books = this.libraryPackage.getLibrary_Books();
    return _library_Books;
  }
  
  public EStructuralFeature eStructuralFeature(final Writer it) {
    EReference _library_Writers = this.libraryPackage.getLibrary_Writers();
    return _library_Writers;
  }
  
  public Object contents(final Library lib, final Resource resource) {
    EList<EObject> _contents = resource.getContents();
    Iterable<Writer> _filter = Iterables.<Writer>filter(_contents, Writer.class);
    return _filter;
  }
}
''']
		)
	}

	@Test
	def testResourceManager() {
		inputs.resourceManagerExample
		.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedModule =
'''
package my.empty;

import my.empty.resource.ResourceManagerGen;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.resource.ResourceManager;
import org.eclipse.ui.plugin.AbstractUIPlugin;

@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends ResourceManager> bindResourceManager() {
    return ResourceManagerGen.class;
  }
}
'''
expectedResourceManager =
'''
package my.empty.resource;

import java.io.IOException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.resource.ResourceManager;

@SuppressWarnings("all")
public class ResourceManagerGen extends ResourceManager {
  private final EXTLibraryFactory libraryFactory = EXTLibraryFactory.eINSTANCE;
  
  public EXTLibraryFactory getLibraryFactory() {
    return this.libraryFactory;
  }
  
  @Override
  public void initialize(final Resource it) {
    EList<EObject> _contents = it.getContents();
    Library _createLibrary = this.libraryFactory.createLibrary();
    _contents.add(_createLibrary);
  }
  
  @Override
  public boolean save(final Resource it) throws IOException {
    it.save(null);
    return true;
  }
}
''']
		)
	}

	@Test
	def testEmptyResourceManager() {
		'''
		module m {
			resourceManager {
			}
		}
		'''
		.assertCorrectJavaCodeGeneration
	}

	@Test
	def testEmptyResourceManagerMethods() {
		'''
		module m {
			resourceManager {
				initializeResource {}
			}
		}
		'''
		.assertCorrectJavaCodeGeneration
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

	@Test
	def testTypeBinding() {
'''
import org.eclipse.jface.viewers.ILabelProvider
import org.eclipse.jface.viewers.IBaseLabelProvider
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider

module my.empty {
	bindings {
		type ILabelProvider -> ViewerLabelProvider
		type IBaseLabelProvider -> ViewerLabelProvider
	}
}
'''
		.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedModule
= '''
package my.empty;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends ILabelProvider> bindILabelProvider() {
    return ViewerLabelProvider.class;
  }
  
  public Class<? extends IBaseLabelProvider> bindIBaseLabelProvider() {
    return ViewerLabelProvider.class;
  }
}
''']
		)
	}

	@Test
	def testProviderBinding() {
'''
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
import org.eclipse.emf.parsley.edit.domain.DefaultAdapterFactoryEditingDomainProvider
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider
import org.eclipse.emf.parsley.dsl.tests.inputs.TestViewerLabelProviderProvider
import org.eclipse.emf.parsley.dsl.tests.inputs.TestFeaturesProviderProvider
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider

module my.empty {
	bindings {
		provide AdapterFactoryEditingDomain -> DefaultAdapterFactoryEditingDomainProvider
		provide ViewerLabelProvider -> TestViewerLabelProviderProvider
		// we can also use an expression, as long as the type is correct
		provide FeaturesProvider -> (new TestFeaturesProviderProvider).class
	}
}
'''
		.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedModule
= '''
package my.empty;

import com.google.inject.Provider;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.dsl.tests.inputs.TestFeaturesProviderProvider;
import org.eclipse.emf.parsley.dsl.tests.inputs.TestViewerLabelProviderProvider;
import org.eclipse.emf.parsley.edit.domain.DefaultAdapterFactoryEditingDomainProvider;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends Provider<AdapterFactoryEditingDomain>> provideAdapterFactoryEditingDomain() {
    return DefaultAdapterFactoryEditingDomainProvider.class;
  }
  
  public Class<? extends Provider<ViewerLabelProvider>> provideViewerLabelProvider() {
    return TestViewerLabelProviderProvider.class;
  }
  
  public Class<? extends Provider<FeaturesProvider>> provideFeaturesProvider() {
    TestFeaturesProviderProvider _testFeaturesProviderProvider = new TestFeaturesProviderProvider();
    Class<? extends TestFeaturesProviderProvider> _class = _testFeaturesProviderProvider.getClass();
    return _class;
  }
}
''']
		)
	}

	@Test
	def testValueBinding() {
'''
import java.util.List

module my.empty {
	bindings {
		value List<Integer> TableColumnWeights -> #[5, 2]
		value String foo -> "foo"
	}
}
'''
		.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedModule
= '''
package my.empty;

import java.util.Collections;
import java.util.List;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public List<Integer> valueTableColumnWeights() {
    return Collections.<Integer>unmodifiableList(CollectionLiterals.<Integer>newArrayList(Integer.valueOf(5), Integer.valueOf(2)));
  }
  
  public String valuefoo() {
    return "foo";
  }
}
''']
		)
	}

	@Test
	def testValueBindingWithSubtype() {
'''
import java.util.ArrayList

module my.empty {
	bindings {
		value ArrayList<Integer> TableColumnWeights -> newArrayList(5, 2)
	}
}
'''
		.assertCorrectJavaCodeGeneration(
			new GeneratorExpectedResults() => [
expectedModule
= '''
package my.empty;

import java.util.ArrayList;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public ArrayList<Integer> valueTableColumnWeights() {
    ArrayList<Integer> _newArrayList = CollectionLiterals.<Integer>newArrayList(Integer.valueOf(5), Integer.valueOf(2));
    return _newArrayList;
  }
}
''']
		)
	}

	def private assertCorrectJavaCodeGeneration(CharSequence input) {
		input.assertCorrectJavaCodeGeneration(new GeneratorExpectedResults)
	}

	def private assertCorrectJavaCodeGeneration(CharSequence input,
			GeneratorExpectedResults expected) {
		input.compile [
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
				} else if (e.key.endsWith("TableFeaturesProviderGen.java")) {
					if (expected.expectedTableFeaturesProvider != null)
						assertEqualsStrings(expected.expectedTableFeaturesProvider, e.value)
				} else if (e.key.endsWith("FeaturesProviderGen.java")) {
					if (expected.expectedFeaturesProvider != null)
						assertEqualsStrings(expected.expectedFeaturesProvider, e.value)
				} else if (e.key.endsWith("TableLabelProviderGen.java")) {
					if (expected.expectedTableLabelProvider != null)
						assertEqualsStrings(expected.expectedTableLabelProvider, e.value)
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
				} else if (e.key.endsWith("MenuBuilderGen.java")) {
					if (expected.expectedMenuBuilder != null)
						assertEqualsStrings(expected.expectedMenuBuilder, e.value)
				} else if (e.key.endsWith("ConfiguratorGen.java")) {
					if (expected.expectedConfigurator != null)
						assertEqualsStrings(expected.expectedConfigurator, e.value)
				} else if (e.key.endsWith("ResourceManagerGen.java")) {
					if (expected.expectedResourceManager!= null)
						assertEqualsStrings(expected.expectedResourceManager, e.value)
				} else if (e.key.endsWith(".xml_emfparsley_gen")) {
					if (expected.expectedPluginXmlGen != null)
						assertEqualsStrings(expected.expectedPluginXmlGen, e.value)
				} else
					fail("unexpected generated code: " + e.value)
			}
			
			compiledClass // check Java compilation succeeds
		]
	}
}
