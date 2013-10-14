/**
 */
package org.eclipse.emf.parsley.dsl.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Module</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.Module#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.Module#getExtendsClause <em>Extends Clause</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.Module#getLabelProvider <em>Label Provider</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.Module#getPropertyDescriptionProvider <em>Property Description Provider</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.Module#getFormPropertyDescriptionProvider <em>Form Property Description Provider</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.Module#getDialogPropertyDescriptionProvider <em>Dialog Property Description Provider</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.Module#getFeaturesProvider <em>Features Provider</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.Module#getFormControlFactory <em>Form Control Factory</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.Module#getDialogControlFactory <em>Dialog Control Factory</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.Module#getProposalCreator <em>Proposal Creator</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.Module#getViewerContentProvider <em>Viewer Content Provider</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.Module#getPartsSpecifications <em>Parts Specifications</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getModule()
 * @model
 * @generated
 */
public interface Module extends WithExtendsClause
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getModule_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.parsley.dsl.model.Module#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Extends Clause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Extends Clause</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Extends Clause</em>' containment reference.
   * @see #setExtendsClause(ExtendsClause)
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getModule_ExtendsClause()
   * @model containment="true"
   * @generated
   */
  ExtendsClause getExtendsClause();

  /**
   * Sets the value of the '{@link org.eclipse.emf.parsley.dsl.model.Module#getExtendsClause <em>Extends Clause</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Extends Clause</em>' containment reference.
   * @see #getExtendsClause()
   * @generated
   */
  void setExtendsClause(ExtendsClause value);

  /**
   * Returns the value of the '<em><b>Label Provider</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Label Provider</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Label Provider</em>' containment reference.
   * @see #setLabelProvider(LabelProvider)
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getModule_LabelProvider()
   * @model containment="true"
   * @generated
   */
  LabelProvider getLabelProvider();

  /**
   * Sets the value of the '{@link org.eclipse.emf.parsley.dsl.model.Module#getLabelProvider <em>Label Provider</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Label Provider</em>' containment reference.
   * @see #getLabelProvider()
   * @generated
   */
  void setLabelProvider(LabelProvider value);

  /**
   * Returns the value of the '<em><b>Property Description Provider</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Property Description Provider</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Property Description Provider</em>' containment reference.
   * @see #setPropertyDescriptionProvider(PropertyDescriptionProvider)
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getModule_PropertyDescriptionProvider()
   * @model containment="true"
   * @generated
   */
  PropertyDescriptionProvider getPropertyDescriptionProvider();

  /**
   * Sets the value of the '{@link org.eclipse.emf.parsley.dsl.model.Module#getPropertyDescriptionProvider <em>Property Description Provider</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Property Description Provider</em>' containment reference.
   * @see #getPropertyDescriptionProvider()
   * @generated
   */
  void setPropertyDescriptionProvider(PropertyDescriptionProvider value);

  /**
   * Returns the value of the '<em><b>Form Property Description Provider</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Form Property Description Provider</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Form Property Description Provider</em>' containment reference.
   * @see #setFormPropertyDescriptionProvider(FormPropertyDescriptionProvider)
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getModule_FormPropertyDescriptionProvider()
   * @model containment="true"
   * @generated
   */
  FormPropertyDescriptionProvider getFormPropertyDescriptionProvider();

  /**
   * Sets the value of the '{@link org.eclipse.emf.parsley.dsl.model.Module#getFormPropertyDescriptionProvider <em>Form Property Description Provider</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Form Property Description Provider</em>' containment reference.
   * @see #getFormPropertyDescriptionProvider()
   * @generated
   */
  void setFormPropertyDescriptionProvider(FormPropertyDescriptionProvider value);

  /**
   * Returns the value of the '<em><b>Dialog Property Description Provider</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Dialog Property Description Provider</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Dialog Property Description Provider</em>' containment reference.
   * @see #setDialogPropertyDescriptionProvider(DialogPropertyDescriptionProvider)
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getModule_DialogPropertyDescriptionProvider()
   * @model containment="true"
   * @generated
   */
  DialogPropertyDescriptionProvider getDialogPropertyDescriptionProvider();

  /**
   * Sets the value of the '{@link org.eclipse.emf.parsley.dsl.model.Module#getDialogPropertyDescriptionProvider <em>Dialog Property Description Provider</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Dialog Property Description Provider</em>' containment reference.
   * @see #getDialogPropertyDescriptionProvider()
   * @generated
   */
  void setDialogPropertyDescriptionProvider(DialogPropertyDescriptionProvider value);

  /**
   * Returns the value of the '<em><b>Features Provider</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Features Provider</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Features Provider</em>' containment reference.
   * @see #setFeaturesProvider(FeaturesProvider)
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getModule_FeaturesProvider()
   * @model containment="true"
   * @generated
   */
  FeaturesProvider getFeaturesProvider();

  /**
   * Sets the value of the '{@link org.eclipse.emf.parsley.dsl.model.Module#getFeaturesProvider <em>Features Provider</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Features Provider</em>' containment reference.
   * @see #getFeaturesProvider()
   * @generated
   */
  void setFeaturesProvider(FeaturesProvider value);

  /**
   * Returns the value of the '<em><b>Form Control Factory</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Form Control Factory</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Form Control Factory</em>' containment reference.
   * @see #setFormControlFactory(FormControlFactory)
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getModule_FormControlFactory()
   * @model containment="true"
   * @generated
   */
  FormControlFactory getFormControlFactory();

  /**
   * Sets the value of the '{@link org.eclipse.emf.parsley.dsl.model.Module#getFormControlFactory <em>Form Control Factory</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Form Control Factory</em>' containment reference.
   * @see #getFormControlFactory()
   * @generated
   */
  void setFormControlFactory(FormControlFactory value);

  /**
   * Returns the value of the '<em><b>Dialog Control Factory</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Dialog Control Factory</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Dialog Control Factory</em>' containment reference.
   * @see #setDialogControlFactory(DialogControlFactory)
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getModule_DialogControlFactory()
   * @model containment="true"
   * @generated
   */
  DialogControlFactory getDialogControlFactory();

  /**
   * Sets the value of the '{@link org.eclipse.emf.parsley.dsl.model.Module#getDialogControlFactory <em>Dialog Control Factory</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Dialog Control Factory</em>' containment reference.
   * @see #getDialogControlFactory()
   * @generated
   */
  void setDialogControlFactory(DialogControlFactory value);

  /**
   * Returns the value of the '<em><b>Proposal Creator</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Proposal Creator</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Proposal Creator</em>' containment reference.
   * @see #setProposalCreator(ProposalCreator)
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getModule_ProposalCreator()
   * @model containment="true"
   * @generated
   */
  ProposalCreator getProposalCreator();

  /**
   * Sets the value of the '{@link org.eclipse.emf.parsley.dsl.model.Module#getProposalCreator <em>Proposal Creator</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Proposal Creator</em>' containment reference.
   * @see #getProposalCreator()
   * @generated
   */
  void setProposalCreator(ProposalCreator value);

  /**
   * Returns the value of the '<em><b>Viewer Content Provider</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Viewer Content Provider</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Viewer Content Provider</em>' containment reference.
   * @see #setViewerContentProvider(ViewerContentProvider)
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getModule_ViewerContentProvider()
   * @model containment="true"
   * @generated
   */
  ViewerContentProvider getViewerContentProvider();

  /**
   * Sets the value of the '{@link org.eclipse.emf.parsley.dsl.model.Module#getViewerContentProvider <em>Viewer Content Provider</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Viewer Content Provider</em>' containment reference.
   * @see #getViewerContentProvider()
   * @generated
   */
  void setViewerContentProvider(ViewerContentProvider value);

  /**
   * Returns the value of the '<em><b>Parts Specifications</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parts Specifications</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parts Specifications</em>' containment reference.
   * @see #setPartsSpecifications(PartsSpecifications)
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getModule_PartsSpecifications()
   * @model containment="true"
   * @generated
   */
  PartsSpecifications getPartsSpecifications();

  /**
   * Sets the value of the '{@link org.eclipse.emf.parsley.dsl.model.Module#getPartsSpecifications <em>Parts Specifications</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parts Specifications</em>' containment reference.
   * @see #getPartsSpecifications()
   * @generated
   */
  void setPartsSpecifications(PartsSpecifications value);

} // Module
