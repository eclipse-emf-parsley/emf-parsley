package org.eclipse.emf.parsley.dsl.tests;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.parsley.dsl.EmfParsleyDslInjectorProvider;
import org.eclipse.emf.parsley.dsl.model.Model;
import org.eclipse.emf.parsley.dsl.model.ModelPackage;
import org.eclipse.emf.parsley.dsl.tests.EmfComponentsDslAbstractTests;
import org.eclipse.emf.parsley.dsl.validation.EmfParsleyDslJavaValidator;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.Extension;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(EmfParsleyDslInjectorProvider.class)
@SuppressWarnings("all")
public class EmfComponentsDslValidatorTests extends EmfComponentsDslAbstractTests {
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Test
  public void testNonEmptyViewsSpecifications() {
    CharSequence _viewSpecificationIsNotIViewPart = this.inputsWithErrors.viewSpecificationIsNotIViewPart();
    Model _parseModel = this.parseModel(_viewSpecificationIsNotIViewPart);
    EClass _viewSpecification = ModelPackage.eINSTANCE.getViewSpecification();
    this._validationTestHelper.assertError(_parseModel, _viewSpecification, 
      EmfParsleyDslJavaValidator.NOT_I_VIEW_PART, 
      "Must be an IViewPart");
  }
  
  @Test
  public void testNotAnEObjectInEmfFeatureAccess() {
    CharSequence _notAnEObjectInEmfFeatureAccess = this.inputsWithErrors.notAnEObjectInEmfFeatureAccess();
    final Model model = this.parseModel(_notAnEObjectInEmfFeatureAccess);
    EClass _emfFeatureAccess = ModelPackage.eINSTANCE.getEmfFeatureAccess();
    this._validationTestHelper.assertError(model, _emfFeatureAccess, 
      EmfParsleyDslJavaValidator.NOT_EOBJECT, 
      "Must be an EObject derived class");
  }
  
  @Test
  public void testValidModuleExtends() {
    CharSequence _notValidModuleExtends = this.inputsWithErrors.notValidModuleExtends();
    final Model model = this.parseModel(_notValidModuleExtends);
    EClass _extendsClause = ModelPackage.eINSTANCE.getExtendsClause();
    this._validationTestHelper.assertError(model, _extendsClause, 
      EmfParsleyDslJavaValidator.NOT_EMFCOMPONENTS_MODULE, 
      "Must be an EmfComponentsGuiceModule derived class");
  }
}
