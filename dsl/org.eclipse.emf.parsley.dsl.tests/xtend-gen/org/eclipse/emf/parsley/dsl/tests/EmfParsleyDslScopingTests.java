package org.eclipse.emf.parsley.dsl.tests;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.parsley.dsl.EmfParsleyDslInjectorProvider;
import org.eclipse.emf.parsley.dsl.model.Model;
import org.eclipse.emf.parsley.dsl.model.ModelPackage;
import org.eclipse.emf.parsley.dsl.model.Module;
import org.eclipse.emf.parsley.dsl.model.PropertyDescriptionProvider;
import org.eclipse.emf.parsley.dsl.model.PropertyDescriptionSpecification;
import org.eclipse.emf.parsley.dsl.scoping.EmfParsleyDslXbaseBatchScopeProvider;
import org.eclipse.emf.parsley.dsl.tests.EmfComponentsDslAbstractTests;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(EmfParsleyDslInjectorProvider.class)
@SuppressWarnings("all")
public class EmfParsleyDslScopingTests extends EmfComponentsDslAbstractTests {
  @Inject
  @Extension
  private EmfParsleyDslXbaseBatchScopeProvider _emfParsleyDslXbaseBatchScopeProvider;
  
  @Test
  public void testFeaturesForLabelSpecifications() {
    CharSequence _propertyDescriptionSpecifications = this.inputs.propertyDescriptionSpecifications();
    Model _parseModel = this.parseModel(_propertyDescriptionSpecifications);
    Module _module = _parseModel.getModule();
    PropertyDescriptionProvider _propertyDescriptionProvider = _module.getPropertyDescriptionProvider();
    EList<PropertyDescriptionSpecification> _labelSpecifications = _propertyDescriptionProvider.getLabelSpecifications();
    PropertyDescriptionSpecification _head = IterableExtensions.<PropertyDescriptionSpecification>head(_labelSpecifications);
    EReference _propertyDescriptionSpecification_Feature = ModelPackage.eINSTANCE.getPropertyDescriptionSpecification_Feature();
    this.assertScope(_head, _propertyDescriptionSpecification_Feature, 
      "name, books, borrowers, writers, employees, stock, branches, parentBranch, people, address, class");
  }
  
  private void assertScope(final EObject o, final EReference ref, final String expected) {
    String[] _split = expected.split(", ");
    final List<String> listExpected = IterableExtensions.<String>toList(((Iterable<String>)Conversions.doWrapArray(_split)));
    IScope _scope = this._emfParsleyDslXbaseBatchScopeProvider.getScope(o, ref);
    Iterable<IEObjectDescription> _allElements = _scope.getAllElements();
    final Function1<IEObjectDescription,QualifiedName> _function = new Function1<IEObjectDescription,QualifiedName>() {
      public QualifiedName apply(final IEObjectDescription it) {
        QualifiedName _name = it.getName();
        return _name;
      }
    };
    final Iterable<QualifiedName> scope = IterableExtensions.<IEObjectDescription, QualifiedName>map(_allElements, _function);
    for (final String exp : listExpected) {
      String _plus = ("not found: " + exp);
      final Function1<QualifiedName,Boolean> _function_1 = new Function1<QualifiedName,Boolean>() {
        public Boolean apply(final QualifiedName it) {
          String _string = it.toString();
          boolean _equals = Objects.equal(exp, _string);
          return Boolean.valueOf(_equals);
        }
      };
      boolean _exists = IterableExtensions.<QualifiedName>exists(scope, _function_1);
      Assert.assertTrue(_plus, _exists);
    }
    for (final QualifiedName sc : scope) {
      String _string = sc.toString();
      String _plus_1 = ("not expected: " + _string);
      final Function1<String,Boolean> _function_2 = new Function1<String,Boolean>() {
        public Boolean apply(final String it) {
          String _string = sc.toString();
          boolean _equals = Objects.equal(_string, it);
          return Boolean.valueOf(_equals);
        }
      };
      boolean _exists_1 = IterableExtensions.<String>exists(listExpected, _function_2);
      Assert.assertTrue(_plus_1, _exists_1);
    }
  }
}
