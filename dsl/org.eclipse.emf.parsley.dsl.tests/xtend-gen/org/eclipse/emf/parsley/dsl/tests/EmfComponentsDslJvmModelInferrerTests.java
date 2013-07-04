package org.eclipse.emf.parsley.dsl.tests;

import com.google.inject.Inject;
import org.eclipse.emf.parsley.dsl.EmfParsleyDslInjectorProvider;
import org.eclipse.emf.parsley.dsl.jvmmodel.EmfParsleyDslJvmModelInferrer;
import org.eclipse.emf.parsley.dsl.model.Module;
import org.eclipse.emf.parsley.dsl.model.PartSpecification;
import org.eclipse.emf.parsley.dsl.tests.EmfComponentsDslAbstractTests;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(EmfParsleyDslInjectorProvider.class)
@SuppressWarnings("all")
public class EmfComponentsDslJvmModelInferrerTests extends EmfComponentsDslAbstractTests {
  @Inject
  private EmfParsleyDslJvmModelInferrer inferrer;
  
  @Test
  public void testEmptyModule() {
    CharSequence _emptyModule = this.inputs.emptyModule();
    this.parseAndAssertNoError(_emptyModule);
  }
  
  @Test
  public void testModuleName() {
    CharSequence _emptyModule = this.inputs.emptyModule();
    Module _module = this.module(_emptyModule);
    String _moduleQN = this.inferrer.moduleQN(_module);
    this.assertEqualsStrings("my.empty.EmfComponentsGuiceModuleGen", _moduleQN);
  }
  
  @Test
  public void testExecutableExtensionFactoryName() {
    CharSequence _emptyModule = this.inputs.emptyModule();
    Module _module = this.module(_emptyModule);
    String _executableExtensionFactoryQN = this.inferrer.executableExtensionFactoryQN(_module);
    this.assertEqualsStrings("my.empty.EmptyExecutableExtensionFactory", _executableExtensionFactoryQN);
  }
  
  @Test
  public void testViewSpecificationExecutableExtensionFactoryName() {
    CharSequence _nonEmptyViewsSpecifications = this.inputs.nonEmptyViewsSpecifications();
    PartSpecification _partSpecification = this.partSpecification(_nonEmptyViewsSpecifications);
    String _executableExtensionFactoryQN = this.inferrer.executableExtensionFactoryQN(_partSpecification);
    this.assertEqualsStrings("my.test.TestExecutableExtensionFactory", _executableExtensionFactoryQN);
  }
  
  @Test
  public void testActivatorName() {
    CharSequence _emptyModule = this.inputs.emptyModule();
    Module _module = this.module(_emptyModule);
    String _activatorQN = this.inferrer.activatorQN(_module);
    this.assertEqualsStrings("my.empty.Activator", _activatorQN);
  }
  
  @Test
  public void testLabelProviderName() {
    CharSequence _emptyModule = this.inputs.emptyModule();
    Module _module = this.module(_emptyModule);
    String _labelProviderQN = this.inferrer.labelProviderQN(_module);
    this.assertEqualsStrings("my.empty.ui.provider.LabelProviderGen", _labelProviderQN);
  }
  
  @Test
  public void testPropertyDescriptionProviderName() {
    CharSequence _emptyModule = this.inputs.emptyModule();
    Module _module = this.module(_emptyModule);
    String _propertyDescriptionProviderQN = this.inferrer.propertyDescriptionProviderQN(_module);
    this.assertEqualsStrings("my.empty.ui.provider.PropertyDescriptionProviderGen", _propertyDescriptionProviderQN);
  }
  
  @Test
  public void testFeaturesProviderName() {
    CharSequence _emptyModule = this.inputs.emptyModule();
    Module _module = this.module(_emptyModule);
    String _featuresProviderQN = this.inferrer.featuresProviderQN(_module);
    this.assertEqualsStrings("my.empty.ui.provider.FeaturesProviderGen", _featuresProviderQN);
  }
  
  @Test
  public void testFormFeatureControlFactoryName() {
    CharSequence _emptyModule = this.inputs.emptyModule();
    Module _module = this.module(_emptyModule);
    String _formFeatureControlFactoryQN = this.inferrer.formFeatureControlFactoryQN(_module);
    this.assertEqualsStrings("my.empty.binding.FormFeatureControlFactoryGen", _formFeatureControlFactoryQN);
  }
  
  @Test
  public void testViewerContentProviderName() {
    CharSequence _emptyModule = this.inputs.emptyModule();
    Module _module = this.module(_emptyModule);
    String _viewerContentProviderQN = this.inferrer.viewerContentProviderQN(_module);
    this.assertEqualsStrings("my.empty.edit.ui.provider.ViewerContentProviderGen", _viewerContentProviderQN);
  }
  
  @Test
  public void testProposalCreatorName() {
    CharSequence _proposalsSpecifications = this.inputs.proposalsSpecifications();
    Module _module = this.module(_proposalsSpecifications);
    String _proposalCreatorQN = this.inferrer.proposalCreatorQN(_module);
    this.assertEqualsStrings("my.empty.binding.ProposalCreatorGen", _proposalCreatorQN);
  }
}
