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
package org.eclipse.emf.parsley.dsl.tests;

import static org.junit.Assert.fail;

import java.util.Map;

import org.eclipse.emf.parsley.dsl.tests.util.GeneratorExpectedResults;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.util.JavaVersion;
import org.eclipse.xtext.xbase.testing.CompilationTestHelper;
import org.eclipse.xtext.xbase.testing.CompilationTestHelper.Result;
import org.eclipse.xtext.xbase.testing.TemporaryFolder;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.common.base.Joiner;
import com.google.inject.Inject;

@RunWith(XtextRunner.class)
@InjectWith(EmfParsleyDslInjectorProvider.class)
public class EmfParsleyDslGeneratorTest extends EmfParsleyDslAbstractTest {

	@Rule
	@Inject
	public TemporaryFolder temporaryFolder;

	@Inject
	private CompilationTestHelper compilationTestHelper;

	@Before
	public void setup() {
		compilationTestHelper.setJavaVersion(JavaVersion.JAVA11);
	}

	@Test
	public void testEmptyModule() {
		var expected = new GeneratorExpectedResults();
		expected.expectedModule = """
			package my.empty;

			import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
			import org.eclipse.ui.plugin.AbstractUIPlugin;

			@SuppressWarnings("all")
			public class EmptyEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
			  public EmptyEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
			    super(plugin);
			  }
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.emptyModule(), expected);
	}

	@Test
	public void testInjectorProvider() {
		var expected = new GeneratorExpectedResults();
		expected.expectedInjectorProvider = """
			package my.empty;

			import com.google.inject.Guice;
			import com.google.inject.Injector;
			import org.eclipse.emf.parsley.runtime.ui.PluginUtil;

			@SuppressWarnings("all")
			public class EmptyInjectorProvider {
			  private static Injector injector;

			  public static synchronized Injector getInjector() {
			    if (injector == null) {
			      injector = Guice.createInjector(
			        new EmptyEmfParsleyGuiceModule(PluginUtil.getPlugin(
			          PluginUtil.getBundle(EmptyInjectorProvider.class))));
			    }
			    return injector;
			  }
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.emptyModule(), expected);
	}

	@Test
	public void testModuleWithExtends() {
		var expected = new GeneratorExpectedResults();
		expected.expectedModule = """
			package my.empty;

			import org.eclipse.emf.parsley.dsl.tests.additional.MyTestGuiceModule;
			import org.eclipse.ui.plugin.AbstractUIPlugin;

			@SuppressWarnings("all")
			public class EmptyEmfParsleyGuiceModule extends MyTestGuiceModule {
			  public EmptyEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
			    super(plugin);
			  }
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.moduleWithExtends(), expected);
	}

	@Test
	public void testModuleWithExtendsJavaGuiceModule() {
		// bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=474140
		var expected = new GeneratorExpectedResults();
		expected.expectedModule = """
			package my.empty;

			import org.eclipse.emf.parsley.dsl.tests.additional.MyTestJavaGuiceModule;
			import org.eclipse.ui.plugin.AbstractUIPlugin;

			@SuppressWarnings("all")
			public class EmptyEmfParsleyGuiceModule extends MyTestJavaGuiceModule {
			  public EmptyEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
			    // not used
			  }
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.moduleWithExtendsJavaGuiceModule(), expected);
	}

	@Test
	public void testModuleWithExtendsIntermediateJavaGuiceModule() {
		// bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=519645
		var expected = new GeneratorExpectedResults();
		expected.expectedModule = """
			package my.empty;

			import org.eclipse.emf.parsley.dsl.tests.inputs.TestIntermediateEmfParsleyJavaGuiceModule;
			import org.eclipse.ui.plugin.AbstractUIPlugin;

			@SuppressWarnings("all")
			public class EmptyEmfParsleyGuiceModule extends TestIntermediateEmfParsleyJavaGuiceModule {
			  public EmptyEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
			    super(plugin);
			  }
			}
			""";
		assertCorrectJavaCodeGeneration("""
			module my.empty extends org.eclipse.emf.parsley.dsl.tests.inputs.TestIntermediateEmfParsleyJavaGuiceModule {
				
			}
			""", expected);
	}

	@Test
	public void testEmptyLabelProvider() {
		var expected = new GeneratorExpectedResults();
		expected.expectedModule = """
			package my.empty;

			import my.empty.ui.provider.EmptyLabelProvider;
			import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
			import org.eclipse.jface.viewers.ILabelProvider;
			import org.eclipse.ui.plugin.AbstractUIPlugin;

			@SuppressWarnings("all")
			public class EmptyEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
			  public EmptyEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
			    super(plugin);
			  }

			  @Override
			  public Class<? extends ILabelProvider> bindILabelProvider() {
			    return EmptyLabelProvider.class;
			  }
			}
			""";
		expected.expectedLabelProvider = """
			package my.empty.ui.provider;

			import com.google.inject.Inject;
			import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
			import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;

			@SuppressWarnings("all")
			public class EmptyLabelProvider extends ViewerLabelProvider {
			  @Inject
			  public EmptyLabelProvider(final AdapterFactoryLabelProvider delegate) {
			    super(delegate);
			  }
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.emptyLabelProvider(), expected);
	}

	@Test
	public void testEmptyPropertyDescriptionProvider() {
		var expected = new GeneratorExpectedResults();
		expected.expectedModule = """
			package my.empty;

			import my.empty.ui.provider.EmptyFeatureCaptionProvider;
			import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
			import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
			import org.eclipse.ui.plugin.AbstractUIPlugin;

			@SuppressWarnings("all")
			public class EmptyEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
			  public EmptyEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
			    super(plugin);
			  }

			  @Override
			  public Class<? extends FeatureCaptionProvider> bindFeatureCaptionProvider() {
			    return EmptyFeatureCaptionProvider.class;
			  }
			}
			""";
		expected.expectedFeatureCaptionProvider = """
			package my.empty.ui.provider;

			import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;

			@SuppressWarnings("all")
			public class EmptyFeatureCaptionProvider extends FeatureCaptionProvider {
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.emptyPropertyDescriptionProvider(), expected);
	}

	@Test
	public void testEmptyLabelSpecifications() {
		var expected = new GeneratorExpectedResults();
		expected.expectedLabelProvider = """
			package my.empty.ui.provider;

			import com.google.inject.Inject;
			import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
			import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;

			@SuppressWarnings("all")
			public class EmptyLabelProvider extends ViewerLabelProvider {
			  @Inject
			  public EmptyLabelProvider(final AdapterFactoryLabelProvider delegate) {
			    super(delegate);
			  }
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.emptyLabelSpecifications(), expected);
	}

	@Test
	public void testLabelProvider() {
		var expected = new GeneratorExpectedResults();
		expected.expectedLabelProvider = """
			package my.empty.ui.provider;

			import com.google.inject.Inject;
			import java.util.List;
			import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
			import org.eclipse.emf.parsley.examples.library.Book;
			import org.eclipse.emf.parsley.examples.library.BookOnTape;
			import org.eclipse.emf.parsley.examples.library.Borrower;
			import org.eclipse.emf.parsley.examples.library.Lendable;
			import org.eclipse.emf.parsley.examples.library.Library;
			import org.eclipse.emf.parsley.examples.library.Writer;
			import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;
			import org.eclipse.jface.resource.JFaceResources;
			import org.eclipse.swt.SWT;
			import org.eclipse.swt.graphics.Color;
			import org.eclipse.swt.graphics.Font;
			import org.eclipse.swt.graphics.ImageData;
			import org.eclipse.swt.widgets.Display;
			import org.eclipse.xtext.xbase.lib.Functions.Function1;
			import org.eclipse.xtext.xbase.lib.ListExtensions;
			import org.eclipse.xtext.xbase.lib.StringExtensions;

			@SuppressWarnings("all")
			public class EmptyLabelProvider extends ViewerLabelProvider {
			  @Inject
			  public EmptyLabelProvider(final AdapterFactoryLabelProvider delegate) {
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
			      final Function1<Lendable, Integer> _function = (Lendable b) -> {
			        return Integer.valueOf(b.getCopies());
			      };
			      List<Integer> _map = ListExtensions.<Lendable, Integer>map(it.getBorrowed(), _function);
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
			    boolean _isNullOrEmpty = StringExtensions.isNullOrEmpty(writer.getName());
			    if (_isNullOrEmpty) {
			      _xifexpression = "noname.gif";
			    } else {
			      _xifexpression = new ImageData("writer.jpeg");
			    }
			    return _xifexpression;
			  }

			  public Font font(final Library it) {
			    Font _bold = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);
			    return _bold;
			  }

			  public Color foreground(final Library it) {
			    Color _systemColor = Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
			    return _systemColor;
			  }

			  public Color background(final Library it) {
			    Color _systemColor = Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
			    return _systemColor;
			  }
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.labelProvider(), expected);
	}

	@Test
	public void testLabelProviderWithExtends() {
		var expected = new GeneratorExpectedResults();
		expected.expectedLabelProvider = """
			package my.empty.ui.provider;

			import com.google.inject.Inject;
			import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
			import org.eclipse.emf.parsley.dsl.tests.inputs.TestLabelProvider;

			@SuppressWarnings("all")
			public class EmptyLabelProvider extends TestLabelProvider {
			  @Inject
			  public EmptyLabelProvider(final AdapterFactoryLabelProvider delegate) {
			    super(delegate);
			  }
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.labelProviderWithExtends(), expected);
	}

	@Test
	public void testLabelProviderWithFields() {
		var expected = new GeneratorExpectedResults();
		expected.expectedLabelProvider = """
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
			public class EmptyLabelProvider extends ViewerLabelProvider {
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

			  private final List<String> listOfString = ObjectExtensions.<ArrayList<String>>operator_doubleArrow(new ArrayList<String>(), ((Procedure1<ArrayList<String>>) (ArrayList<String> it) -> {
			    it.add("first");
			    it.add("second");
			  }));

			  public List<String> getListOfString() {
			    return this.listOfString;
			  }

			  @Inject
			  public EmptyLabelProvider(final AdapterFactoryLabelProvider delegate) {
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
			""";
		assertCorrectJavaCodeGeneration(inputs.labelProviderWithFields(), expected);
	}

	@Test
	public void testTableLabelProvider() {
		var expected = new GeneratorExpectedResults();
		expected.expectedTableLabelProvider = """
			package my.empty.ui.provider;

			import org.eclipse.emf.parsley.examples.library.Book;
			import org.eclipse.emf.parsley.examples.library.Library;
			import org.eclipse.emf.parsley.examples.library.Writer;
			import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider;
			import org.eclipse.jface.resource.JFaceResources;
			import org.eclipse.swt.SWT;
			import org.eclipse.swt.graphics.Color;
			import org.eclipse.swt.graphics.Font;
			import org.eclipse.swt.graphics.ImageData;
			import org.eclipse.swt.widgets.Display;
			import org.eclipse.xtext.xbase.lib.StringExtensions;

			@SuppressWarnings("all")
			public class EmptyTableLabelProvider extends TableColumnLabelProvider {
			  public String text_Library_name(final Library it) {
			    return "Name";
			  }

			  public String text_Library_books(final Library it) {
			    return "Books";
			  }

			  public String text_Writer_lastName(final Writer it) {
			    String _firstUpper = StringExtensions.toFirstUpper(it.getName());
			    return _firstUpper;
			  }

			  public Object image_Book_author(final Book it) {
			    Object _xifexpression = null;
			    boolean _isNullOrEmpty = StringExtensions.isNullOrEmpty(it.getAuthor().getName());
			    if (_isNullOrEmpty) {
			      _xifexpression = "noname.gif";
			    } else {
			      _xifexpression = new ImageData("writer.jpeg");
			    }
			    return _xifexpression;
			  }

			  public Font font_Library_name(final Library it) {
			    Font _bold = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);
			    return _bold;
			  }

			  public Color foreground_Library_books(final Library it) {
			    Color _systemColor = Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
			    return _systemColor;
			  }

			  public Color background_Library_address(final Library it) {
			    Color _systemColor = Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
			    return _systemColor;
			  }

			  public Font rowFont(final Library it) {
			    Font _bold = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);
			    return _bold;
			  }

			  public Color rowForeground(final Library it) {
			    Color _systemColor = Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
			    return _systemColor;
			  }

			  public Color rowBackground(final Library it) {
			    Color _systemColor = Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
			    return _systemColor;
			  }
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.tableLabelProvider(), expected);
	}

	@Test
	public void testTableLabelProviderWithExtends() {
		var expected = new GeneratorExpectedResults();
		expected.expectedTableLabelProvider = """
			package my.empty.ui.provider;

			import org.eclipse.emf.parsley.dsl.tests.inputs.TestTableColumnLabelProvider;

			@SuppressWarnings("all")
			public class EmptyTableLabelProvider extends TestTableColumnLabelProvider {
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.tableLabelProviderWithExtends(), expected);
	}

	@Test
	public void testTableLabelProviderWithFields() {
		var expected = new GeneratorExpectedResults();
		expected.expectedTableLabelProvider = """
			package my.empty.ui.provider;

			import com.google.inject.Inject;
			import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider;
			import org.eclipse.jface.viewers.ILabelProvider;

			@SuppressWarnings("all")
			public class EmptyTableLabelProvider extends TableColumnLabelProvider {
			  @Inject
			  private ILabelProvider parentLabelProvider;

			  public ILabelProvider getParentLabelProvider() {
			    return this.parentLabelProvider;
			  }

			  public void setParentLabelProvider(final ILabelProvider parentLabelProvider) {
			    this.parentLabelProvider = parentLabelProvider;
			  }
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.tableLabelProviderWithFields(), expected);
	}

	@Test
	public void testFeatureCaptionProvider() {
		var expected = new GeneratorExpectedResults();
		expected.expectedModule = """
			package my.empty;

			import my.empty.ui.provider.EmptyFeatureCaptionProvider;
			import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
			import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
			import org.eclipse.ui.plugin.AbstractUIPlugin;

			@SuppressWarnings("all")
			public class EmptyEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
			  public EmptyEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
			    super(plugin);
			  }

			  @Override
			  public Class<? extends FeatureCaptionProvider> bindFeatureCaptionProvider() {
			    return EmptyFeatureCaptionProvider.class;
			  }
			}
			""";
		expected.expectedFeatureCaptionProvider = """
			package my.empty.ui.provider;

			import org.eclipse.emf.ecore.EStructuralFeature;
			import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
			import org.eclipse.xtext.xbase.lib.StringExtensions;

			@SuppressWarnings("all")
			public class EmptyFeatureCaptionProvider extends FeatureCaptionProvider {
			  public String text_Library_name(final EStructuralFeature it) {
			    return "Name";
			  }

			  public String text_Library_books(final EStructuralFeature it) {
			    return "Books";
			  }

			  public String text_Writer_lastName(final EStructuralFeature it) {
			    String _firstUpper = StringExtensions.toFirstUpper(it.getName());
			    return _firstUpper;
			  }
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.featureCaptionProvider(), expected);
	}

	@Test
	public void testFeatureCaptionProviderWithExtends() {
		var expected = new GeneratorExpectedResults();
		expected.expectedModule = """
			package my.empty;

			import my.empty.ui.provider.EmptyFeatureCaptionProvider;
			import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
			import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
			import org.eclipse.ui.plugin.AbstractUIPlugin;

			@SuppressWarnings("all")
			public class EmptyEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
			  public EmptyEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
			    super(plugin);
			  }

			  @Override
			  public Class<? extends FeatureCaptionProvider> bindFeatureCaptionProvider() {
			    return EmptyFeatureCaptionProvider.class;
			  }
			}
			""";
		expected.expectedFeatureCaptionProvider = """
			package my.empty.ui.provider;

			import org.eclipse.emf.ecore.EStructuralFeature;
			import org.eclipse.emf.parsley.dsl.tests.inputs.TestFeatureCaptionProvider;
			import org.eclipse.xtext.xbase.lib.StringExtensions;

			@SuppressWarnings("all")
			public class EmptyFeatureCaptionProvider extends TestFeatureCaptionProvider {
			  public String text_Library_name(final EStructuralFeature it) {
			    return "Name";
			  }

			  public String text_Writer_lastName(final EStructuralFeature it) {
			    String _firstUpper = StringExtensions.toFirstUpper(it.getName());
			    return _firstUpper;
			  }
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.featureCaptionProviderWithExtends(), expected);
	}

	@Test
	public void testFormFeatureCaptionProvider() {
		var expected = new GeneratorExpectedResults();
		expected.expectedModule = """
			package my.empty;

			import my.empty.ui.provider.EmptyFormFeatureCaptionProvider;
			import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
			import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider;
			import org.eclipse.ui.plugin.AbstractUIPlugin;

			@SuppressWarnings("all")
			public class EmptyEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
			  public EmptyEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
			    super(plugin);
			  }

			  @Override
			  public Class<? extends FormFeatureCaptionProvider> bindFormFeatureCaptionProvider() {
			    return EmptyFormFeatureCaptionProvider.class;
			  }
			}
			""";
		expected.expectedFormFeatureCaptionProvider = """
			package my.empty.ui.provider;

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
			public class EmptyFormFeatureCaptionProvider extends FormFeatureCaptionProvider {
			  public String text_Library_name(final EStructuralFeature it) {
			    return "Name";
			  }

			  public String text_Library_books(final EStructuralFeature it) {
			    return "Books";
			  }

			  public String text_Writer_lastName(final EStructuralFeature it) {
			    String _firstUpper = StringExtensions.toFirstUpper(it.getName());
			    return _firstUpper;
			  }

			  public Label label_Library_name(final Composite parent, final EStructuralFeature it) {
			    Label _createLabel = this.createLabel(parent, "Name");
			    return _createLabel;
			  }

			  public Label label_Library_books(final Composite parent, final EStructuralFeature it) {
			    Label _createLabel = this.createLabel(parent, EXTLibraryPackage.eINSTANCE.getLibrary(), EXTLibraryPackage.eINSTANCE.getLibrary_Books());
			    return _createLabel;
			  }

			  public Label label_Writer_lastName(final Composite parent, final EStructuralFeature it) {
			    Label _label = new Label(parent, SWT.NONE);
			    final Procedure1<Label> _function = (Label l) -> {
			      l.setText(it.getName());
			    };
			    return ObjectExtensions.<Label>operator_doubleArrow(_label, _function);
			  }
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.formFeatureCaptionProvider(), expected);
	}

	@Test
	public void testFormFeatureCaptionProviderWithExtends() {
		var expected = new GeneratorExpectedResults();
		expected.expectedFormFeatureCaptionProvider = """
			package my.empty.ui.provider;

			import org.eclipse.emf.parsley.dsl.tests.inputs.TestFormFeatureCaptionProvider;

			@SuppressWarnings("all")
			public class EmptyFormFeatureCaptionProvider extends TestFormFeatureCaptionProvider {
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.formFeatureCaptionProviderWithExtends(), expected);
	}

	@Test
	public void testDialogFeatureCaptionProvider() {
		var expected = new GeneratorExpectedResults();
		expected.expectedModule = """
			package my.empty;

			import my.empty.ui.provider.EmptyDialogFeatureCaptionProvider;
			import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
			import org.eclipse.emf.parsley.ui.provider.DialogFeatureCaptionProvider;
			import org.eclipse.ui.plugin.AbstractUIPlugin;

			@SuppressWarnings("all")
			public class EmptyEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
			  public EmptyEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
			    super(plugin);
			  }

			  @Override
			  public Class<? extends DialogFeatureCaptionProvider> bindDialogFeatureCaptionProvider() {
			    return EmptyDialogFeatureCaptionProvider.class;
			  }
			}
			""";
		expected.expectedDialogFeatureCaptionProvider = """
			package my.empty.ui.provider;

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
			public class EmptyDialogFeatureCaptionProvider extends DialogFeatureCaptionProvider {
			  public String text_Library_name(final EStructuralFeature it) {
			    return "Name";
			  }

			  public String text_Library_books(final EStructuralFeature it) {
			    return "Books";
			  }

			  public String text_Writer_lastName(final EStructuralFeature it) {
			    String _firstUpper = StringExtensions.toFirstUpper(it.getName());
			    return _firstUpper;
			  }

			  public Label label_Library_name(final Composite parent, final EStructuralFeature it) {
			    Label _createLabel = this.createLabel(parent, "Name");
			    return _createLabel;
			  }

			  public Label label_Library_books(final Composite parent, final EStructuralFeature it) {
			    Label _createLabel = this.createLabel(parent, EXTLibraryPackage.eINSTANCE.getLibrary(), EXTLibraryPackage.eINSTANCE.getLibrary_Books());
			    return _createLabel;
			  }

			  public Label label_Writer_lastName(final Composite parent, final EStructuralFeature it) {
			    Label _label = new Label(parent, SWT.NONE);
			    final Procedure1<Label> _function = (Label l) -> {
			      l.setText(it.getName());
			    };
			    return ObjectExtensions.<Label>operator_doubleArrow(_label, _function);
			  }
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.dialogFeatureCaptionProvider(), expected);
	}

	@Test
	public void testDialogFeatureCaptionProviderWithExtends() {
		var expected = new GeneratorExpectedResults();
		expected.expectedDialogFeatureCaptionProvider = """
			package my.empty.ui.provider;

			import org.eclipse.emf.parsley.dsl.tests.inputs.TestDialogFeatureCaptionProvider;

			@SuppressWarnings("all")
			public class EmptyDialogFeatureCaptionProvider extends TestDialogFeatureCaptionProvider {
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.dialogFeatureCaptionProviderWithExtends(), expected);
	}

	@Test
	public void testFeaturesProvider() {
		var expected = new GeneratorExpectedResults();
		expected.expectedModule = """
			package my.empty;

			import my.empty.ui.provider.EmptyFeaturesProvider;
			import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
			import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
			import org.eclipse.ui.plugin.AbstractUIPlugin;

			@SuppressWarnings("all")
			public class EmptyEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
			  public EmptyEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
			    super(plugin);
			  }

			  @Override
			  public Class<? extends FeaturesProvider> bindFeaturesProvider() {
			    return EmptyFeaturesProvider.class;
			  }
			}
			""";
		expected.expectedFeaturesProvider = """
			package my.empty.ui.provider;

			import org.eclipse.emf.parsley.ui.provider.EClassToEStructuralFeatureAsStringsMap;
			import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;

			@SuppressWarnings("all")
			public class EmptyFeaturesProvider extends FeaturesProvider {
			  @Override
			  public void buildStringMap(final EClassToEStructuralFeatureAsStringsMap stringMap) {
			    super.buildStringMap(stringMap);
			   \s
			    stringMap.mapTo("org.eclipse.emf.parsley.examples.library.Library",
			      "name");
			    stringMap.mapTo("org.eclipse.emf.parsley.examples.library.Writer",
			      "firstName", "lastName", "books");
			  }
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.featuresProvider(), expected);
	}

	@Test
	public void testFeaturesProviderWithExtends() {
		var expected = new GeneratorExpectedResults();
		expected.expectedFeaturesProvider = """
			package my.empty.ui.provider;

			import org.eclipse.emf.parsley.dsl.tests.inputs.TestFeaturesProvider;
			import org.eclipse.emf.parsley.ui.provider.EClassToEStructuralFeatureAsStringsMap;

			@SuppressWarnings("all")
			public class EmptyFeaturesProvider extends TestFeaturesProvider {
			  @Override
			  public void buildStringMap(final EClassToEStructuralFeatureAsStringsMap stringMap) {
			    super.buildStringMap(stringMap);
			   \s
			  }
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.featuresProviderWithExtends(), expected);
	}

	@Test
	public void testTableFeaturesProvider() {
		var expected = new GeneratorExpectedResults();
		expected.expectedModule = """
			package my.empty;

			import my.empty.ui.provider.EmptyTableFeaturesProvider;
			import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
			import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider;
			import org.eclipse.ui.plugin.AbstractUIPlugin;

			@SuppressWarnings("all")
			public class EmptyEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
			  public EmptyEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
			    super(plugin);
			  }

			  @Override
			  public Class<? extends TableFeaturesProvider> bindTableFeaturesProvider() {
			    return EmptyTableFeaturesProvider.class;
			  }
			}
			""";
		expected.expectedTableFeaturesProvider = """
			package my.empty.ui.provider;

			import org.eclipse.emf.parsley.ui.provider.EClassToEStructuralFeatureAsStringsMap;
			import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider;

			@SuppressWarnings("all")
			public class EmptyTableFeaturesProvider extends TableFeaturesProvider {
			  @Override
			  public void buildStringMap(final EClassToEStructuralFeatureAsStringsMap stringMap) {
			    super.buildStringMap(stringMap);
			   \s
			    stringMap.mapTo("org.eclipse.emf.parsley.examples.library.Library",
			      "name");
			    stringMap.mapTo("org.eclipse.emf.parsley.examples.library.Writer",
			      "firstName", "lastName", "books");
			  }
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.tableFeaturesProvider(), expected);
	}

	@Test
	public void testTableFeaturesProviderWithExtends() {
		var expected = new GeneratorExpectedResults();
		expected.expectedTableFeaturesProvider = """
			package my.empty.ui.provider;

			import org.eclipse.emf.parsley.dsl.tests.inputs.TestTableFeaturesProvider;
			import org.eclipse.emf.parsley.ui.provider.EClassToEStructuralFeatureAsStringsMap;

			@SuppressWarnings("all")
			public class EmptyTableFeaturesProvider extends TestTableFeaturesProvider {
			  @Override
			  public void buildStringMap(final EClassToEStructuralFeatureAsStringsMap stringMap) {
			    super.buildStringMap(stringMap);
			   \s
			  }
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.tableFeaturesProviderWithExtends(), expected);
	}

	@Test
	public void testFormControlFactory() {
		var expected = new GeneratorExpectedResults();
		expected.expectedModule = """
			package my.empty;

			import my.empty.binding.EmptyFormControlFactory;
			import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
			import org.eclipse.emf.parsley.composite.FormControlFactory;
			import org.eclipse.ui.plugin.AbstractUIPlugin;

			@SuppressWarnings("all")
			public class EmptyEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
			  public EmptyEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
			    super(plugin);
			  }

			  @Override
			  public Class<? extends FormControlFactory> bindFormControlFactory() {
			    return EmptyFormControlFactory.class;
			  }
			}
			""";
		expected.expectedFormControlFactory = """
			package my.empty.binding;

			import org.eclipse.core.databinding.observable.value.IObservableValue;
			import org.eclipse.emf.ecore.EStructuralFeature;
			import org.eclipse.emf.parsley.composite.FormControlFactory;
			import org.eclipse.emf.parsley.examples.library.Book;
			import org.eclipse.emf.parsley.examples.library.Borrower;
			import org.eclipse.emf.parsley.examples.library.Library;
			import org.eclipse.emf.parsley.examples.library.Writer;
			import org.eclipse.emf.parsley.util.DatabindingUtil;
			import org.eclipse.jface.databinding.swt.ISWTObservableValue;
			import org.eclipse.swt.SWT;
			import org.eclipse.swt.widgets.Control;
			import org.eclipse.swt.widgets.Label;
			import org.eclipse.xtext.xbase.lib.Functions.Function1;
			import org.eclipse.xtext.xbase.lib.IterableExtensions;
			import org.eclipse.xtext.xbase.lib.ListExtensions;

			@SuppressWarnings("all")
			public class EmptyFormControlFactory extends FormControlFactory {
			  public Control control_Library_name(final Library it) {
			    return null;
			  }

			  public Control control_Writer_books(final Writer it) {
			    final Function1<Book, String> _function = (Book it_1) -> {
			      return it_1.getTitle();
			    };
			    Label _createLabel = this.createLabel(
			      IterableExtensions.join(ListExtensions.<Book, String>map(it.getBooks(), _function), ", "));
			    return _createLabel;
			  }

			  public Control control_Writer_name(final IObservableValue observableValue, final EStructuralFeature feature) {
			    Control control = createControl_Writer_name();
			    bindValue(
			    	feature,
			    	createTarget_Writer_name(control),
			    	observableValue);
			    return control;
			  }

			  protected Control createControl_Writer_name() {
			    return this.createLabel(this.getParent(), "");
			  }

			  protected IObservableValue createTarget_Writer_name(final Control it) {
			    return DatabindingUtil.observeText(it);
			  }

			  public Control control_Writer_firstName(final IObservableValue observableValue, final EStructuralFeature feature) {
			    Control control = createControl_Writer_firstName();
			    bindValue(
			    	feature,
			    	createTarget_Writer_firstName(control),
			    	observableValue);
			    return control;
			  }

			  protected Control createControl_Writer_firstName() {
			    Label _createLabel = this.getToolkit().createLabel(this.getParent(), "");
			    return _createLabel;
			  }

			  protected IObservableValue createTarget_Writer_firstName(final Control it) {
			    ISWTObservableValue _observeText = DatabindingUtil.observeText(it, SWT.Modify);
			    return _observeText;
			  }

			  public Control control_Borrower_firstName(final Borrower it) {
			    return this.createText(it.getFirstName(), SWT.MULTI, SWT.BORDER,\s
			      SWT.WRAP, SWT.V_SCROLL);
			  }
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.formControlFactory(), expected);
	}

	@Test
	public void testFormControlFactoryWithExtends() {
		var expected = new GeneratorExpectedResults();
		expected.expectedFormControlFactory = """
			package my.empty.binding;

			import org.eclipse.emf.parsley.dsl.tests.inputs.TestFormControlFactory;

			@SuppressWarnings("all")
			public class EmptyFormControlFactory extends TestFormControlFactory {
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.formControlFactoryWithExtends(), expected);
	}

	@Test
	public void testDialogControlFactory() {
		var expected = new GeneratorExpectedResults();
		expected.expectedModule = """
			package my.empty;

			import my.empty.binding.EmptyDialogControlFactory;
			import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
			import org.eclipse.emf.parsley.composite.DialogControlFactory;
			import org.eclipse.ui.plugin.AbstractUIPlugin;

			@SuppressWarnings("all")
			public class EmptyEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
			  public EmptyEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
			    super(plugin);
			  }

			  @Override
			  public Class<? extends DialogControlFactory> bindDialogControlFactory() {
			    return EmptyDialogControlFactory.class;
			  }
			}
			""";
		expected.expectedDialogControlFactory = """
			package my.empty.binding;

			import org.eclipse.core.databinding.observable.value.IObservableValue;
			import org.eclipse.emf.ecore.EStructuralFeature;
			import org.eclipse.emf.parsley.composite.DialogControlFactory;
			import org.eclipse.emf.parsley.examples.library.Book;
			import org.eclipse.emf.parsley.examples.library.Borrower;
			import org.eclipse.emf.parsley.examples.library.Library;
			import org.eclipse.emf.parsley.examples.library.Writer;
			import org.eclipse.emf.parsley.util.DatabindingUtil;
			import org.eclipse.jface.databinding.swt.ISWTObservableValue;
			import org.eclipse.swt.SWT;
			import org.eclipse.swt.widgets.Control;
			import org.eclipse.swt.widgets.Label;
			import org.eclipse.xtext.xbase.lib.Functions.Function1;
			import org.eclipse.xtext.xbase.lib.IterableExtensions;
			import org.eclipse.xtext.xbase.lib.ListExtensions;

			@SuppressWarnings("all")
			public class EmptyDialogControlFactory extends DialogControlFactory {
			  public Control control_Library_name(final Library it) {
			    return null;
			  }

			  public Control control_Writer_books(final Writer it) {
			    final Function1<Book, String> _function = (Book it_1) -> {
			      return it_1.getTitle();
			    };
			    Label _createLabel = this.createLabel(
			      IterableExtensions.join(ListExtensions.<Book, String>map(it.getBooks(), _function), ", "));
			    return _createLabel;
			  }

			  public Control control_Writer_name(final IObservableValue observableValue, final EStructuralFeature feature) {
			    Control control = createControl_Writer_name();
			    bindValue(
			    	feature,
			    	createTarget_Writer_name(control),
			    	observableValue);
			    return control;
			  }

			  protected Control createControl_Writer_name() {
			    return this.createLabel(this.getParent(), "");
			  }

			  protected IObservableValue createTarget_Writer_name(final Control it) {
			    return DatabindingUtil.observeText(it);
			  }

			  public Control control_Writer_firstName(final IObservableValue observableValue, final EStructuralFeature feature) {
			    Control control = createControl_Writer_firstName();
			    bindValue(
			    	feature,
			    	createTarget_Writer_firstName(control),
			    	observableValue);
			    return control;
			  }

			  protected Control createControl_Writer_firstName() {
			    Label _createLabel = this.createLabel(this.getParent(), "");
			    return _createLabel;
			  }

			  protected IObservableValue createTarget_Writer_firstName(final Control it) {
			    ISWTObservableValue _observeText = DatabindingUtil.observeText(it, SWT.Modify);
			    return _observeText;
			  }

			  public Control control_Borrower_firstName(final Borrower it) {
			    return this.createText(it.getFirstName(), SWT.MULTI, SWT.BORDER,\s
			      SWT.WRAP, SWT.V_SCROLL);
			  }
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.dialogControlFactory(), expected);
	}

	@Test
	public void testDialogControlFactoryWithExtends() {
		var expected = new GeneratorExpectedResults();
		expected.expectedDialogControlFactory = """
			package my.empty.binding;

			import org.eclipse.emf.parsley.dsl.tests.inputs.TestDialogControlFactory;

			@SuppressWarnings("all")
			public class EmptyDialogControlFactory extends TestDialogControlFactory {
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.dialogControlFactoryWithExtends(), expected);
	}

	@Test
	public void testViewerContentProvider() {
		var expected = new GeneratorExpectedResults();
		expected.expectedModule = """
			package my.empty;

			import my.empty.edit.ui.provider.EmptyViewerContentProvider;
			import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
			import org.eclipse.jface.viewers.IContentProvider;
			import org.eclipse.ui.plugin.AbstractUIPlugin;

			@SuppressWarnings("all")
			public class EmptyEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
			  public EmptyEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
			    super(plugin);
			  }

			  @Override
			  public Class<? extends IContentProvider> bindIContentProvider() {
			    return EmptyViewerContentProvider.class;
			  }
			}
			""";
		expected.expectedViewerContentProvider = """
			package my.empty.edit.ui.provider;

			import com.google.common.collect.Iterables;
			import com.google.common.collect.Iterators;
			import com.google.inject.Inject;
			import java.util.Iterator;
			import org.eclipse.emf.common.notify.AdapterFactory;
			import org.eclipse.emf.common.util.EList;
			import org.eclipse.emf.ecore.EObject;
			import org.eclipse.emf.ecore.resource.Resource;
			import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider;
			import org.eclipse.emf.parsley.examples.library.Book;
			import org.eclipse.emf.parsley.examples.library.Library;
			import org.eclipse.emf.parsley.examples.library.Writer;

			@SuppressWarnings("all")
			public class EmptyViewerContentProvider extends ViewerContentProvider {
			  @Inject
			  public EmptyViewerContentProvider(final AdapterFactory adapterFactory) {
			    super(adapterFactory);
			  }

			  public Object elements(final Resource it) {
			    Iterator<Library> _filter = Iterators.<Library>filter(it.getAllContents(), Library.class);
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
			""";
		assertCorrectJavaCodeGeneration(inputs.viewerContentProvider(), expected);
	}

	@Test
	public void testTableViewerContentProvider() {
		var expected = new GeneratorExpectedResults();
		expected.expectedModule = """
			package my.empty;

			import my.empty.edit.ui.provider.EmptyTableViewerContentProvider;
			import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
			import org.eclipse.emf.parsley.edit.ui.provider.TableViewerContentProvider;
			import org.eclipse.ui.plugin.AbstractUIPlugin;

			@SuppressWarnings("all")
			public class EmptyEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
			  public EmptyEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
			    super(plugin);
			  }

			  @Override
			  public Class<? extends TableViewerContentProvider> bindTableViewerContentProvider() {
			    return EmptyTableViewerContentProvider.class;
			  }
			}
			""";
		expected.expectedTableViewerContentProvider = """
			package my.empty.edit.ui.provider;

			import com.google.common.collect.Iterables;
			import com.google.common.collect.Iterators;
			import com.google.inject.Inject;
			import java.util.Iterator;
			import org.eclipse.emf.common.notify.AdapterFactory;
			import org.eclipse.emf.common.util.EList;
			import org.eclipse.emf.ecore.EObject;
			import org.eclipse.emf.ecore.resource.Resource;
			import org.eclipse.emf.parsley.edit.ui.provider.TableViewerContentProvider;
			import org.eclipse.emf.parsley.examples.library.Book;
			import org.eclipse.emf.parsley.examples.library.Library;
			import org.eclipse.emf.parsley.examples.library.Writer;

			@SuppressWarnings("all")
			public class EmptyTableViewerContentProvider extends TableViewerContentProvider {
			  @Inject
			  public EmptyTableViewerContentProvider(final AdapterFactory adapterFactory) {
			    super(adapterFactory);
			  }

			  public Object elements(final Resource it) {
			    Iterator<Library> _filter = Iterators.<Library>filter(it.getAllContents(), Library.class);
			    return _filter;
			  }

			  public Object elements(final Library it) {
			    EList<Book> _books = it.getBooks();
			    EList<Writer> _writers = it.getWriters();
			    Iterable<EObject> _plus = Iterables.<EObject>concat(_books, _writers);
			    return _plus;
			  }

			  public Object elements(final Writer writer) {
			    EList<Book> _books = writer.getBooks();
			    return _books;
			  }

			  public Object elements(final Book it) {
			    Writer _author = it.getAuthor();
			    return _author;
			  }
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.tableViewerContentProvider(), expected);
	}

	@Test
	public void testViewerContentProviderWithExtends() {
		var expected = new GeneratorExpectedResults();
		expected.expectedViewerContentProvider = """
			package my.empty.edit.ui.provider;

			import com.google.inject.Inject;
			import org.eclipse.emf.common.notify.AdapterFactory;
			import org.eclipse.emf.parsley.dsl.tests.inputs.TestViewerContentProvider;

			@SuppressWarnings("all")
			public class EmptyViewerContentProvider extends TestViewerContentProvider {
			  @Inject
			  public EmptyViewerContentProvider(final AdapterFactory adapterFactory) {
			    super(adapterFactory);
			  }
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.viewerContentProviderWithExtends(), expected);
	}

	@Test
	public void testProposalCreator() {
		var expected = new GeneratorExpectedResults();
		expected.expectedModule = """
			package my.empty;

			import my.empty.binding.EmptyProposalCreator;
			import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
			import org.eclipse.emf.parsley.composite.ProposalCreator;
			import org.eclipse.ui.plugin.AbstractUIPlugin;

			@SuppressWarnings("all")
			public class EmptyEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
			  public EmptyEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
			    super(plugin);
			  }

			  @Override
			  public Class<? extends ProposalCreator> bindProposalCreator() {
			    return EmptyProposalCreator.class;
			  }
			}
			""";
		expected.expectedProposalCreator = """
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
			public class EmptyProposalCreator extends ProposalCreator {
			  public List<?> proposals_Library_name(final Library it, final EStructuralFeature feature) {
			    return CollectionLiterals.<String>newArrayList("foo", "bar");
			  }

			  public List<?> proposals_Writer_books(final Writer it, final EStructuralFeature feature) {
			    return it.getBooks();
			  }

			  public List<?> proposals_Book_author(final Book it, final EStructuralFeature feature) {
			    Writer _createWriter = EXTLibraryFactory.eINSTANCE.createWriter();
			    final Procedure1<Writer> _function = (Writer it_1) -> {
			      it_1.setName("Foo");
			    };
			    Writer _doubleArrow = ObjectExtensions.<Writer>operator_doubleArrow(_createWriter, _function);
			    Writer _createWriter_1 = EXTLibraryFactory.eINSTANCE.createWriter();
			    final Procedure1<Writer> _function_1 = (Writer it_1) -> {
			      it_1.setName("Bar");
			    };
			    Writer _doubleArrow_1 = ObjectExtensions.<Writer>operator_doubleArrow(_createWriter_1, _function_1);
			    return CollectionLiterals.<Writer>newArrayList(_doubleArrow, _doubleArrow_1);
			  }

			  public List<?> proposals_Borrower_borrowed(final Borrower it, final EStructuralFeature feature) {
			    List<Object> _defaultProposals = this.defaultProposals(feature);
			    final Procedure1<List<Object>> _function = (List<Object> it_1) -> {
			      Book _createBook = EXTLibraryFactory.eINSTANCE.createBook();
			      final Procedure1<Book> _function_1 = (Book it_2) -> {
			        it_2.setTitle("Fake Book");
			      };
			      Book _doubleArrow = ObjectExtensions.<Book>operator_doubleArrow(_createBook, _function_1);
			      it_1.add(_doubleArrow);
			    };
			    final List<Object> p = ObjectExtensions.<List<Object>>operator_doubleArrow(_defaultProposals, _function);
			    return p;
			  }
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.proposalCreator(), expected);
	}

	@Test
	public void testProposalCreatorWithExtends() {
		var expected = new GeneratorExpectedResults();
		expected.expectedProposalCreator = """
			package my.empty.binding;

			import org.eclipse.emf.parsley.dsl.tests.inputs.TestProposalCreator;

			@SuppressWarnings("all")
			public class EmptyProposalCreator extends TestProposalCreator {
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.proposalCreatorWithExtends(), expected);
	}

	@Test
	public void testMenuBuilder() {
		var expected = new GeneratorExpectedResults();
		expected.expectedModule = """
			package my.empty;

			import my.empty.edit.action.EmptyMenuBuilder;
			import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
			import org.eclipse.emf.parsley.edit.action.EditingMenuBuilder;
			import org.eclipse.ui.plugin.AbstractUIPlugin;

			@SuppressWarnings("all")
			public class EmptyEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
			  public EmptyEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
			    super(plugin);
			  }

			  @Override
			  public Class<? extends EditingMenuBuilder> bindEditingMenuBuilder() {
			    return EmptyMenuBuilder.class;
			  }
			}
			""";
		expected.expectedMenuBuilder = """
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
			public class EmptyMenuBuilder extends EditingMenuBuilder {
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
			    final Procedure1<Writer> _function = (Writer it) -> {
			      it.setName("This is a new writer");
			    };
			    Writer _doubleArrow = ObjectExtensions.<Writer>operator_doubleArrow(_createWriter, _function);
			    ArrayList<IMenuContributionSpecification> _newArrayList = CollectionLiterals.<IMenuContributionSpecification>newArrayList(
			      this.<Writer>actionAdd("New Writer", _writers, _doubleArrow));
			    return _newArrayList;
			  }

			  public List<IMenuContributionSpecification> emfMenuContributions(final Writer it) {
			    EList<Book> _books = it.getBooks();
			    Book _createBook = this.libraryFactory.createBook();
			    final Procedure1<Book> _function = (Book it_1) -> {
			      it_1.setTitle("New book");
			    };
			    Book _doubleArrow = ObjectExtensions.<Book>operator_doubleArrow(_createBook, _function);
			    IMenuContributionSpecification _actionAdd = this.<Book>actionAdd("New book", _books, _doubleArrow);
			    return Collections.<IMenuContributionSpecification>unmodifiableList(CollectionLiterals.<IMenuContributionSpecification>newArrayList(_actionAdd));
			  }
			}
			""";
		assertCorrectJavaCodeGeneration("""
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
							actionAdd("New Writer", lib.writers,\s
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
			""", expected);
	}

	@Test
	public void testConfigurator() {
		var expected = new GeneratorExpectedResults();
		expected.expectedModule = """
			package my.empty;

			import my.empty.config.EmptyConfigurator;
			import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
			import org.eclipse.emf.parsley.config.Configurator;
			import org.eclipse.ui.plugin.AbstractUIPlugin;

			@SuppressWarnings("all")
			public class EmptyEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
			  public EmptyEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
			    super(plugin);
			  }

			  @Override
			  public Class<? extends Configurator> bindConfigurator() {
			    return EmptyConfigurator.class;
			  }
			}
			""";
		expected.expectedConfigurator = """
			package my.empty.config;

			import org.eclipse.emf.common.util.URI;
			import org.eclipse.emf.ecore.EClass;
			import org.eclipse.emf.parsley.config.Configurator;
			import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage;
			import org.eclipse.emf.parsley.examples.library.Library;
			import org.eclipse.emf.parsley.examples.library.Writer;

			@SuppressWarnings("all")
			public class EmptyConfigurator extends Configurator {
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
			}
			""";
		assertCorrectJavaCodeGeneration(inputs.configuratorExample(), expected);
	}

	@Test
	public void testResourceManager() {
		var expected = new GeneratorExpectedResults();
		expected.expectedModule = """
			package my.empty;

			import my.empty.resource.EmptyResourceManager;
			import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
			import org.eclipse.emf.parsley.resource.ResourceManager;
			import org.eclipse.ui.plugin.AbstractUIPlugin;

			@SuppressWarnings("all")
			public class EmptyEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
			  public EmptyEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
			    super(plugin);
			  }

			  @Override
			  public Class<? extends ResourceManager> bindResourceManager() {
			    return EmptyResourceManager.class;
			  }
			}
			""";
		expected.expectedResourceManager = """
			package my.empty.resource;

			import java.io.IOException;
			import org.eclipse.emf.common.util.EList;
			import org.eclipse.emf.ecore.EObject;
			import org.eclipse.emf.ecore.resource.Resource;
			import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory;
			import org.eclipse.emf.parsley.examples.library.Library;
			import org.eclipse.emf.parsley.resource.ResourceManager;

			@SuppressWarnings("all")
			public class EmptyResourceManager extends ResourceManager {
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
			""";
		assertCorrectJavaCodeGeneration(inputs.resourceManagerExample(), expected);
	}

	@Test
	public void testEmptyResourceManager() {
		assertCorrectJavaCodeGeneration("""
			module m {
				resourceManager {
				}
			}
			""", new GeneratorExpectedResults());
	}

	@Test
	public void testEmptyResourceManagerMethods() {
		assertCorrectJavaCodeGeneration("""
			module m {
				resourceManager {
					initializeResource {}
				}
			}
			""", new GeneratorExpectedResults());
	}

	@Test
	public void testViewsSpecifications() {
		var expected = new GeneratorExpectedResults();
		expected.expectedModule = """
			package my.test;

			import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
			import org.eclipse.ui.plugin.AbstractUIPlugin;

			@SuppressWarnings("all")
			public class TestEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
			  public TestEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
			    super(plugin);
			  }
			}
			""";
		expected.expectedExecutableExtensionFactory = """
			package my.test;

			import com.google.inject.Injector;
			import org.eclipse.emf.parsley.runtime.ui.AbstractGuiceAwareExecutableExtensionFactory;

			@SuppressWarnings("all")
			public class TestExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {
			  @Override
			  public Injector getInjector() throws Exception {
			    return TestInjectorProvider.getInjector();
			  }
			}
			""";
		expected.expectedPluginXmlGen = """
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
			""";
		assertCorrectJavaCodeGeneration(inputs.multipleViewsSpecifications(), expected);
	}

	@Test
	public void testTypeBinding() {
		var expected = new GeneratorExpectedResults();
		expected.expectedModule = """
			package my.empty;

			import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
			import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;
			import org.eclipse.jface.viewers.IBaseLabelProvider;
			import org.eclipse.jface.viewers.ILabelProvider;
			import org.eclipse.ui.plugin.AbstractUIPlugin;

			@SuppressWarnings("all")
			public class EmptyEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
			  public EmptyEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
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
			""";
		assertCorrectJavaCodeGeneration("""
			import org.eclipse.jface.viewers.ILabelProvider
			import org.eclipse.jface.viewers.IBaseLabelProvider
			import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider

			module my.empty {
				bindings {
					type ILabelProvider -> ViewerLabelProvider
					type IBaseLabelProvider -> ViewerLabelProvider
				}
			}
			""", expected);
	}

	@Test
	public void testProviderBinding() {
		var expected = new GeneratorExpectedResults();
		expected.expectedModule = """
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
			public class EmptyEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
			  public EmptyEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
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
			    Class<? extends TestFeaturesProviderProvider> _class = new TestFeaturesProviderProvider().getClass();
			    return _class;
			  }
			}
			""";
		assertCorrectJavaCodeGeneration("""
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
			""", expected);
	}

	@Test
	public void testValueBinding() {
		var expected = new GeneratorExpectedResults();
		expected.expectedModule = """
			package my.empty;

			import java.util.Collections;
			import java.util.List;
			import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
			import org.eclipse.ui.plugin.AbstractUIPlugin;
			import org.eclipse.xtext.xbase.lib.CollectionLiterals;

			@SuppressWarnings("all")
			public class EmptyEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
			  public EmptyEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
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
			""";
		assertCorrectJavaCodeGeneration("""
			import java.util.List

			module my.empty {
				bindings {
					value List<Integer> TableColumnWeights -> #[5, 2]
					value String foo -> "foo"
				}
			}
			""", expected);
	}

	@Test
	public void testValueBindingWithSubtype() {
		var expected = new GeneratorExpectedResults();
		expected.expectedModule = """
			package my.empty;

			import java.util.ArrayList;
			import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
			import org.eclipse.ui.plugin.AbstractUIPlugin;
			import org.eclipse.xtext.xbase.lib.CollectionLiterals;

			@SuppressWarnings("all")
			public class EmptyEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
			  public EmptyEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
			    super(plugin);
			  }

			  @Override
			  public ArrayList<Integer> valueTableColumnWeights() {
			    ArrayList<Integer> _newArrayList = CollectionLiterals.<Integer>newArrayList(Integer.valueOf(5), Integer.valueOf(2));
			    return _newArrayList;
			  }
			}
			""";
		assertCorrectJavaCodeGeneration("""
			import java.util.ArrayList

			module my.empty {
				bindings {
					value ArrayList<Integer> TableColumnWeights -> newArrayList(5, 2)
				}
			}
			""", expected);
	}

	private void assertCorrectJavaCodeGeneration(CharSequence input, GeneratorExpectedResults expected) {
		try {
			compilationTestHelper.compile(input, result -> {
				assertNoValidationErrors(result);

				for (Map.Entry<String, CharSequence> e : result.getAllGeneratedResources().entrySet()) {
					if (e.getKey().endsWith("Module.java")) {
						if (expected.expectedModule != null)
							assertEqualsStrings(expected.expectedModule, e.getValue());
					} else if (e.getKey().endsWith("InjectorProvider.java")) {
						if (expected.expectedInjectorProvider != null)
							assertEqualsStrings(expected.expectedInjectorProvider, e.getValue());
					} else if (e.getKey().endsWith("ExecutableExtensionFactory.java")) {
						if (expected.expectedExecutableExtensionFactory != null)
							assertEqualsStrings(expected.expectedExecutableExtensionFactory, e.getValue());
					} else if (e.getKey().endsWith("FormFeatureCaptionProvider.java")) {
						if (expected.expectedFormFeatureCaptionProvider != null)
							assertEqualsStrings(expected.expectedFormFeatureCaptionProvider, e.getValue());
					} else if (e.getKey().endsWith("DialogFeatureCaptionProvider.java")) {
						if (expected.expectedDialogFeatureCaptionProvider != null)
							assertEqualsStrings(expected.expectedDialogFeatureCaptionProvider, e.getValue());
					} else if (e.getKey().endsWith("FeatureCaptionProvider.java")) {
						if (expected.expectedFeatureCaptionProvider != null)
							assertEqualsStrings(expected.expectedFeatureCaptionProvider, e.getValue());
					} else if (e.getKey().endsWith("TableFeaturesProvider.java")) {
						if (expected.expectedTableFeaturesProvider != null)
							assertEqualsStrings(expected.expectedTableFeaturesProvider, e.getValue());
					} else if (e.getKey().endsWith("FeaturesProvider.java")) {
						if (expected.expectedFeaturesProvider != null)
							assertEqualsStrings(expected.expectedFeaturesProvider, e.getValue());
					} else if (e.getKey().endsWith("TableLabelProvider.java")) {
						if (expected.expectedTableLabelProvider != null)
							assertEqualsStrings(expected.expectedTableLabelProvider, e.getValue());
					} else if (e.getKey().endsWith("LabelProvider.java")) {
						if (expected.expectedLabelProvider != null)
							assertEqualsStrings(expected.expectedLabelProvider, e.getValue());
					} else if (e.getKey().endsWith("FormControlFactory.java")) {
						if (expected.expectedFormControlFactory != null)
							assertEqualsStrings(expected.expectedFormControlFactory, e.getValue());
					} else if (e.getKey().endsWith("DialogControlFactory.java")) {
						if (expected.expectedDialogControlFactory != null)
							assertEqualsStrings(expected.expectedDialogControlFactory, e.getValue());
					} else if (e.getKey().endsWith("TableViewerContentProvider.java")) {
						if (expected.expectedTableViewerContentProvider != null)
							assertEqualsStrings(expected.expectedTableViewerContentProvider, e.getValue());
					} else if (e.getKey().endsWith("ViewerContentProvider.java")) {
						if (expected.expectedViewerContentProvider != null)
							assertEqualsStrings(expected.expectedViewerContentProvider, e.getValue());
					} else if (e.getKey().endsWith("ProposalCreator.java")) {
						if (expected.expectedProposalCreator != null)
							assertEqualsStrings(expected.expectedProposalCreator, e.getValue());
					} else if (e.getKey().endsWith("MenuBuilder.java")) {
						if (expected.expectedMenuBuilder != null)
							assertEqualsStrings(expected.expectedMenuBuilder, e.getValue());
					} else if (e.getKey().endsWith("Configurator.java")) {
						if (expected.expectedConfigurator != null)
							assertEqualsStrings(expected.expectedConfigurator, e.getValue());
					} else if (e.getKey().endsWith("ResourceManager.java")) {
						if (expected.expectedResourceManager != null)
							assertEqualsStrings(expected.expectedResourceManager, e.getValue());
					} else if (e.getKey().endsWith(".xml_emfparsley_gen")) {
						if (expected.expectedPluginXmlGen != null)
							assertEqualsStrings(expected.expectedPluginXmlGen, e.getValue());
					} else if (e.getKey().endsWith("plugin.xml")) {
						if (expected.expectedPluginXmlGen != null)
							assertEqualsStrings(expected.expectedPluginXmlGen, e.getValue());
					} else {
						fail("unexpected generated code: " + e.getValue());
					}
				}

				result.getCompiledClass(); // check Java compilation succeeds
			});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void assertNoValidationErrors(Result result) {
		var allErrors = result.getErrorsAndWarnings().stream()
			.filter(issue -> issue.getSeverity() == Severity.ERROR)
			.toList();
		if (!allErrors.isEmpty()) {
			throw new IllegalStateException("One or more resources contained errors : " +
				Joiner.on(',').join(allErrors));
		}
	}
}
