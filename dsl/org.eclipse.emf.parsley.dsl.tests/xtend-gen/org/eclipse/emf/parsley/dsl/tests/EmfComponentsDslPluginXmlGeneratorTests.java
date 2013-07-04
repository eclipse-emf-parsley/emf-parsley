package org.eclipse.emf.parsley.dsl.tests;

import com.google.inject.Inject;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.dsl.EmfParsleyDslInjectorProvider;
import org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslOutputConfigurationProvider;
import org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslPluginXmlGenerator;
import org.eclipse.emf.parsley.dsl.model.Model;
import org.eclipse.emf.parsley.dsl.model.Module;
import org.eclipse.emf.parsley.dsl.model.PartSpecification;
import org.eclipse.emf.parsley.dsl.tests.EmfComponentsDslAbstractTests;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.InMemoryFileSystemAccess;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(EmfParsleyDslInjectorProvider.class)
@SuppressWarnings("all")
public class EmfComponentsDslPluginXmlGeneratorTests extends EmfComponentsDslAbstractTests {
  @Inject
  private EmfParsleyDslPluginXmlGenerator pluginXmlGenerator;
  
  @Test
  public void testEmptyContents() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    _builder.newLine();
    _builder.append("<?eclipse version=\"3.4\"?>");
    _builder.newLine();
    _builder.append("<plugin>");
    _builder.newLine();
    _builder.append("</plugin>");
    _builder.newLine();
    this.assertPluginXmlContents("", _builder);
  }
  
  @Test
  public void testViewExtensionPoint() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<view");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("category=\"org.eclipse.emf.parsley\"");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("class=\"my.test.TestExecutableExtensionFactory:org.eclipse.emf.parsley.views.AbstractSaveableTreeView\"");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("id=\"my.view.part\"");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("name=\"My View\"");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("restorable=\"true\">");
    _builder.newLine();
    _builder.append("</view>");
    _builder.newLine();
    CharSequence _nonEmptyViewsSpecifications = this.inputs.nonEmptyViewsSpecifications();
    PartSpecification _partSpecification = this.partSpecification(_nonEmptyViewsSpecifications);
    CharSequence _generateExtensionPoint = this.pluginXmlGenerator.generateExtensionPoint(_partSpecification);
    this.assertEqualsStrings(_builder, _generateExtensionPoint);
  }
  
  @Test
  public void testNoViewSpecification() {
    CharSequence _emptyModule = this.inputs.emptyModule();
    Module _module = this.module(_emptyModule);
    this.assertPluginXmlContents(_module, "");
  }
  
  @Test
  public void testSingleViewSpecification() {
    CharSequence _nonEmptyViewsSpecifications = this.inputs.nonEmptyViewsSpecifications();
    Module _module = this.module(_nonEmptyViewsSpecifications);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    _builder.newLine();
    _builder.append("<?eclipse version=\"3.4\"?>");
    _builder.newLine();
    _builder.append("<plugin>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<extension");
    _builder.newLine();
    _builder.append("\t      ");
    _builder.append("point=\"org.eclipse.ui.views\">");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("<view");
    _builder.newLine();
    _builder.append("\t          ");
    _builder.append("category=\"org.eclipse.emf.parsley\"");
    _builder.newLine();
    _builder.append("\t          ");
    _builder.append("class=\"my.test.TestExecutableExtensionFactory:org.eclipse.emf.parsley.views.AbstractSaveableTreeView\"");
    _builder.newLine();
    _builder.append("\t          ");
    _builder.append("id=\"my.view.part\"");
    _builder.newLine();
    _builder.append("\t          ");
    _builder.append("name=\"My View\"");
    _builder.newLine();
    _builder.append("\t          ");
    _builder.append("restorable=\"true\">");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("</view>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</extension>");
    _builder.newLine();
    _builder.append("</plugin>");
    _builder.newLine();
    this.assertPluginXmlContents(_module, _builder);
  }
  
  @Test
  public void testMultipleViewSpecification() {
    CharSequence _multipleViewsSpecifications = this.inputs.multipleViewsSpecifications();
    Module _module = this.module(_multipleViewsSpecifications);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    _builder.newLine();
    _builder.append("<?eclipse version=\"3.4\"?>");
    _builder.newLine();
    _builder.append("<plugin>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<extension");
    _builder.newLine();
    _builder.append("\t      ");
    _builder.append("point=\"org.eclipse.ui.views\">");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("<view");
    _builder.newLine();
    _builder.append("\t          ");
    _builder.append("category=\"org.eclipse.emf.parsley\"");
    _builder.newLine();
    _builder.append("\t          ");
    _builder.append("class=\"my.test.TestExecutableExtensionFactory:org.eclipse.emf.parsley.views.AbstractSaveableTreeView\"");
    _builder.newLine();
    _builder.append("\t          ");
    _builder.append("id=\"my.view.tree.part\"");
    _builder.newLine();
    _builder.append("\t          ");
    _builder.append("name=\"My Tree View\"");
    _builder.newLine();
    _builder.append("\t          ");
    _builder.append("restorable=\"true\">");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("</view>");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("<view");
    _builder.newLine();
    _builder.append("\t          ");
    _builder.append("category=\"my.view.category\"");
    _builder.newLine();
    _builder.append("\t          ");
    _builder.append("class=\"my.test.TestExecutableExtensionFactory:org.eclipse.emf.parsley.views.AbstractSaveableTreeFormView\"");
    _builder.newLine();
    _builder.append("\t          ");
    _builder.append("id=\"my.view.form.part\"");
    _builder.newLine();
    _builder.append("\t          ");
    _builder.append("name=\"My Tree Form View\"");
    _builder.newLine();
    _builder.append("\t          ");
    _builder.append("restorable=\"true\">");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("</view>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</extension>");
    _builder.newLine();
    _builder.append("</plugin>");
    _builder.newLine();
    this.assertPluginXmlContents(_module, _builder);
  }
  
  @Test
  public void testPluginXmlGen() {
    CharSequence _multipleViewsSpecifications = this.inputs.multipleViewsSpecifications();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    _builder.newLine();
    _builder.append("<?eclipse version=\"3.4\"?>");
    _builder.newLine();
    _builder.append("<plugin>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<extension");
    _builder.newLine();
    _builder.append("\t      ");
    _builder.append("point=\"org.eclipse.ui.views\">");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("<view");
    _builder.newLine();
    _builder.append("\t          ");
    _builder.append("category=\"org.eclipse.emf.parsley\"");
    _builder.newLine();
    _builder.append("\t          ");
    _builder.append("class=\"my.test.TestExecutableExtensionFactory:org.eclipse.emf.parsley.views.AbstractSaveableTreeView\"");
    _builder.newLine();
    _builder.append("\t          ");
    _builder.append("id=\"my.view.tree.part\"");
    _builder.newLine();
    _builder.append("\t          ");
    _builder.append("name=\"My Tree View\"");
    _builder.newLine();
    _builder.append("\t          ");
    _builder.append("restorable=\"true\">");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("</view>");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("<view");
    _builder.newLine();
    _builder.append("\t          ");
    _builder.append("category=\"my.view.category\"");
    _builder.newLine();
    _builder.append("\t          ");
    _builder.append("class=\"my.test.TestExecutableExtensionFactory:org.eclipse.emf.parsley.views.AbstractSaveableTreeFormView\"");
    _builder.newLine();
    _builder.append("\t          ");
    _builder.append("id=\"my.view.form.part\"");
    _builder.newLine();
    _builder.append("\t          ");
    _builder.append("name=\"My Tree Form View\"");
    _builder.newLine();
    _builder.append("\t          ");
    _builder.append("restorable=\"true\">");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("</view>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</extension>");
    _builder.newLine();
    _builder.append("</plugin>");
    _builder.newLine();
    this.assertPluginXmlGeneration(_multipleViewsSpecifications, _builder);
  }
  
  private void assertPluginXmlGeneration(final CharSequence input, final CharSequence expected) {
    InMemoryFileSystemAccess _inMemoryFileSystemAccess = new InMemoryFileSystemAccess();
    final InMemoryFileSystemAccess access = _inMemoryFileSystemAccess;
    final Model parsed = this.parseAndAssertNoError(input);
    Resource _eResource = parsed.eResource();
    this.pluginXmlGenerator.doGenerate(_eResource, access);
    Map<String,CharSequence> _files = access.getFiles();
    final Set<Entry<String,CharSequence>> entrySet = _files.entrySet();
    int _size = entrySet.size();
    this.assertEqualsStrings(Integer.valueOf(1), Integer.valueOf(_size));
    final Entry<String,CharSequence> e = IterableExtensions.<Entry<String,CharSequence>>head(entrySet);
    String _key = e.getKey();
    String _plus = (EmfParsleyDslOutputConfigurationProvider.PROJECT_ROOT_OUTPUT + 
      EmfParsleyDslOutputConfigurationProvider.PLUGIN_XML_EMFPARSLEY_REL_GEN_PATH);
    int _length = _plus.length();
    final String name = _key.substring(_length);
    this.assertEqualsStrings(
      EmfParsleyDslOutputConfigurationProvider.PLUGIN_XML_EMFPARSLEY_GEN, name);
    CharSequence _value = e.getValue();
    this.assertEqualsStrings(expected, _value);
  }
  
  private void assertPluginXmlContents(final Module module, final CharSequence expected) {
    CharSequence _generatePluginXml = this.pluginXmlGenerator.generatePluginXml(module);
    this.assertEqualsStrings(expected, _generatePluginXml);
  }
  
  private void assertPluginXmlContents(final CharSequence contents, final CharSequence expected) {
    CharSequence _generatePluginXml = this.pluginXmlGenerator.generatePluginXml(contents);
    this.assertEqualsStrings(expected, _generatePluginXml);
  }
}
