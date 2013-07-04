package org.eclipse.emf.parsley.dsl.tests;

import com.google.inject.Inject;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.parsley.dsl.EmfParsleyDslInjectorProvider;
import org.eclipse.emf.parsley.dsl.model.Model;
import org.eclipse.emf.parsley.dsl.model.Module;
import org.eclipse.emf.parsley.dsl.model.PartSpecification;
import org.eclipse.emf.parsley.dsl.tests.inputs.TestInputs;
import org.eclipse.emf.parsley.dsl.tests.inputs.TestInputsWithErrors;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(EmfParsleyDslInjectorProvider.class)
@SuppressWarnings("all")
public class EmfComponentsDslAbstractTests {
  @Inject
  protected TestInputs inputs;
  
  @Inject
  protected TestInputsWithErrors inputsWithErrors;
  
  @Inject
  @Extension
  private ParseHelper<Model> _parseHelper;
  
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @BeforeClass
  public static void setCRLF() {
    System.setProperty("line.separator", "\n");
  }
  
  public Model parseAndAssertNoError(final CharSequence s) {
    try {
      Model _xblockexpression = null;
      {
        Model ts = this._parseHelper.parse(s);
        this._validationTestHelper.assertNoErrors(ts);
        _xblockexpression = (ts);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void parseAndAssertError(final CharSequence s, final EClass objectType, final String code, final String messagePart) {
    try {
      Model _parse = this._parseHelper.parse(s);
      this._validationTestHelper.assertError(_parse, objectType, code, messagePart);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public Model parseModel(final CharSequence s) {
    try {
      Model _parse = this._parseHelper.parse(s);
      return _parse;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void parseAndAssertErrors(final CharSequence s) {
    try {
      Model _parse = this._parseHelper.parse(s);
      List<Issue> _validate = this._validationTestHelper.validate(_parse);
      int _size = _validate.size();
      boolean _greaterThan = (_size > 0);
      Assert.assertTrue(_greaterThan);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public Module module(final CharSequence s) {
    Model _parseAndAssertNoError = this.parseAndAssertNoError(s);
    Module _module = _parseAndAssertNoError.getModule();
    return _module;
  }
  
  public void assertEqualsStrings(final Object expected, final Object actual) {
    String _plus = ("" + expected);
    String _plus_1 = ("" + actual);
    Assert.assertEquals(_plus, _plus_1);
  }
  
  public PartSpecification partSpecification(final CharSequence s) {
    Module _module = this.module(s);
    EList<PartSpecification> _parts = _module.getParts();
    PartSpecification _head = IterableExtensions.<PartSpecification>head(_parts);
    return _head;
  }
}
