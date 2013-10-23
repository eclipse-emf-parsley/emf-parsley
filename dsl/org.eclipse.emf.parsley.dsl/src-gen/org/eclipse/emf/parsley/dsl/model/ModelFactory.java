/**
 */
package org.eclipse.emf.parsley.dsl.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.parsley.dsl.model.ModelPackage
 * @generated
 */
public interface ModelFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ModelFactory eINSTANCE = org.eclipse.emf.parsley.dsl.model.impl.ModelFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Model</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Model</em>'.
   * @generated
   */
  Model createModel();

  /**
   * Returns a new object of class '<em>Module</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Module</em>'.
   * @generated
   */
  Module createModule();

  /**
   * Returns a new object of class '<em>Extends Clause</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Extends Clause</em>'.
   * @generated
   */
  ExtendsClause createExtendsClause();

  /**
   * Returns a new object of class '<em>Label Provider</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Label Provider</em>'.
   * @generated
   */
  LabelProvider createLabelProvider();

  /**
   * Returns a new object of class '<em>Label Specification</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Label Specification</em>'.
   * @generated
   */
  LabelSpecification createLabelSpecification();

  /**
   * Returns a new object of class '<em>Feature Caption Provider</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Feature Caption Provider</em>'.
   * @generated
   */
  FeatureCaptionProvider createFeatureCaptionProvider();

  /**
   * Returns a new object of class '<em>Form Feature Caption Provider</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Form Feature Caption Provider</em>'.
   * @generated
   */
  FormFeatureCaptionProvider createFormFeatureCaptionProvider();

  /**
   * Returns a new object of class '<em>Dialog Feature Caption Provider</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Dialog Feature Caption Provider</em>'.
   * @generated
   */
  DialogFeatureCaptionProvider createDialogFeatureCaptionProvider();

  /**
   * Returns a new object of class '<em>Feature Caption Specification</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Feature Caption Specification</em>'.
   * @generated
   */
  FeatureCaptionSpecification createFeatureCaptionSpecification();

  /**
   * Returns a new object of class '<em>Features Provider</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Features Provider</em>'.
   * @generated
   */
  FeaturesProvider createFeaturesProvider();

  /**
   * Returns a new object of class '<em>Feature Specification</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Feature Specification</em>'.
   * @generated
   */
  FeatureSpecification createFeatureSpecification();

  /**
   * Returns a new object of class '<em>Form Control Factory</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Form Control Factory</em>'.
   * @generated
   */
  FormControlFactory createFormControlFactory();

  /**
   * Returns a new object of class '<em>Dialog Control Factory</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Dialog Control Factory</em>'.
   * @generated
   */
  DialogControlFactory createDialogControlFactory();

  /**
   * Returns a new object of class '<em>Control Factory Specification</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Control Factory Specification</em>'.
   * @generated
   */
  ControlFactorySpecification createControlFactorySpecification();

  /**
   * Returns a new object of class '<em>Proposal Creator</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Proposal Creator</em>'.
   * @generated
   */
  ProposalCreator createProposalCreator();

  /**
   * Returns a new object of class '<em>Proposal Specification</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Proposal Specification</em>'.
   * @generated
   */
  ProposalSpecification createProposalSpecification();

  /**
   * Returns a new object of class '<em>Viewer Content Provider</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Viewer Content Provider</em>'.
   * @generated
   */
  ViewerContentProvider createViewerContentProvider();

  /**
   * Returns a new object of class '<em>Emf Feature Access</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Emf Feature Access</em>'.
   * @generated
   */
  EmfFeatureAccess createEmfFeatureAccess();

  /**
   * Returns a new object of class '<em>With Extends Clause</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>With Extends Clause</em>'.
   * @generated
   */
  WithExtendsClause createWithExtendsClause();

  /**
   * Returns a new object of class '<em>Parts Specifications</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Parts Specifications</em>'.
   * @generated
   */
  PartsSpecifications createPartsSpecifications();

  /**
   * Returns a new object of class '<em>Part Specification</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Part Specification</em>'.
   * @generated
   */
  PartSpecification createPartSpecification();

  /**
   * Returns a new object of class '<em>View Specification</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>View Specification</em>'.
   * @generated
   */
  ViewSpecification createViewSpecification();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  ModelPackage getModelPackage();

} //ModelFactory
