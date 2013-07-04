package org.eclipse.emf.parsley.dsl.generator;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import java.util.Arrays;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslOutputConfigurationProvider;
import org.eclipse.emf.parsley.dsl.jvmmodel.EmfParsleyDslJvmModelInferrer;
import org.eclipse.emf.parsley.dsl.model.Module;
import org.eclipse.emf.parsley.dsl.model.PartSpecification;
import org.eclipse.emf.parsley.dsl.model.ViewSpecification;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class EmfParsleyDslPluginXmlGenerator implements IGenerator {
  @Inject
  private EmfParsleyDslJvmModelInferrer inferrer;
  
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    TreeIterator<EObject> _allContents = resource.getAllContents();
    Iterable<EObject> _iterable = IteratorExtensions.<EObject>toIterable(_allContents);
    Iterable<Module> _filter = Iterables.<Module>filter(_iterable, Module.class);
    for (final Module module : _filter) {
      {
        final CharSequence contents = this.generatePluginXml(module);
        int _length = 0;
        if (contents!=null) {
          _length=contents.length();
        }
        boolean _greaterThan = (_length > 0);
        if (_greaterThan) {
          fsa.generateFile(
            EmfParsleyDslOutputConfigurationProvider.PLUGIN_XML_EMFPARSLEY_GEN_PATH, 
            EmfParsleyDslOutputConfigurationProvider.PROJECT_ROOT_OUTPUT, contents);
        }
      }
    }
  }
  
  public CharSequence generatePluginXml(final Module module) {
    CharSequence _xblockexpression = null;
    {
      EList<PartSpecification> _parts = null;
      if (module!=null) {
        _parts=module.getParts();
      }
      final EList<PartSpecification> partSpecs = _parts;
      CharSequence _xifexpression = null;
      boolean _or = false;
      boolean _equals = Objects.equal(partSpecs, null);
      if (_equals) {
        _or = true;
      } else {
        boolean _isEmpty = partSpecs.isEmpty();
        _or = (_equals || _isEmpty);
      }
      if (_or) {
        StringConcatenation _builder = new StringConcatenation();
        return _builder.toString();
      } else {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("<extension");
        _builder_1.newLine();
        _builder_1.append("      ");
        _builder_1.append("point=\"org.eclipse.ui.views\">");
        _builder_1.newLine();
        _builder_1.append("    ");
        final Function1<PartSpecification,CharSequence> _function = new Function1<PartSpecification,CharSequence>() {
            public CharSequence apply(final PartSpecification it) {
              CharSequence _generateExtensionPoint = EmfParsleyDslPluginXmlGenerator.this.generateExtensionPoint(it);
              return _generateExtensionPoint;
            }
          };
        List<CharSequence> _map = ListExtensions.<PartSpecification, CharSequence>map(partSpecs, _function);
        String _join = IterableExtensions.join(_map, "");
        _builder_1.append(_join, "    ");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("</extension>");
        _builder_1.newLine();
        CharSequence _generatePluginXml = this.generatePluginXml(_builder_1);
        _xifexpression = _generatePluginXml;
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  protected CharSequence _generateExtensionPoint(final PartSpecification partSpecification) {
    return null;
  }
  
  protected CharSequence _generateExtensionPoint(final ViewSpecification viewSpecification) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<view");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("category=\"");
    String _xifexpression = null;
    boolean _or = false;
    String _category = viewSpecification.getCategory();
    boolean _equals = Objects.equal(_category, null);
    if (_equals) {
      _or = true;
    } else {
      String _category_1 = viewSpecification.getCategory();
      boolean _isEmpty = _category_1.isEmpty();
      _or = (_equals || _isEmpty);
    }
    if (_or) {
      _xifexpression = "org.eclipse.emf.parsley";
    } else {
      String _category_2 = viewSpecification.getCategory();
      _xifexpression = _category_2;
    }
    _builder.append(_xifexpression, "      ");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("      ");
    _builder.append("class=\"");
    String _executableExtensionFactoryQN = this.inferrer.executableExtensionFactoryQN(viewSpecification);
    _builder.append(_executableExtensionFactoryQN, "      ");
    _builder.append(":");
    JvmTypeReference _type = viewSpecification.getType();
    String _identifier = _type.getIdentifier();
    _builder.append(_identifier, "      ");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("      ");
    _builder.append("id=\"");
    String _id = viewSpecification.getId();
    _builder.append(_id, "      ");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("      ");
    _builder.append("name=\"");
    String _viewName = viewSpecification.getViewName();
    _builder.append(_viewName, "      ");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("      ");
    _builder.append("restorable=\"true\">");
    _builder.newLine();
    _builder.append("</view>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generatePluginXml(final CharSequence contents) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    _builder.newLine();
    _builder.append("<?eclipse version=\"3.4\"?>");
    _builder.newLine();
    _builder.append("<plugin>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append(contents, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("</plugin>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateExtensionPoint(final PartSpecification viewSpecification) {
    if (viewSpecification instanceof ViewSpecification) {
      return _generateExtensionPoint((ViewSpecification)viewSpecification);
    } else if (viewSpecification != null) {
      return _generateExtensionPoint(viewSpecification);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(viewSpecification).toString());
    }
  }
}
