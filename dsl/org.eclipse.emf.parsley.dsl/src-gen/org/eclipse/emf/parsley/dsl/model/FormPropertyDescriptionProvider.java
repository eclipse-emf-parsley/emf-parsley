/**
 */
package org.eclipse.emf.parsley.dsl.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Form Property Description Provider</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.FormPropertyDescriptionProvider#getSpecifications <em>Specifications</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.FormPropertyDescriptionProvider#getLabelSpecifications <em>Label Specifications</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getFormPropertyDescriptionProvider()
 * @model
 * @generated
 */
public interface FormPropertyDescriptionProvider extends EObject
{
  /**
   * Returns the value of the '<em><b>Specifications</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.parsley.dsl.model.PropertyDescriptionSpecification}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Specifications</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Specifications</em>' containment reference list.
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getFormPropertyDescriptionProvider_Specifications()
   * @model containment="true"
   * @generated
   */
  EList<PropertyDescriptionSpecification> getSpecifications();

  /**
   * Returns the value of the '<em><b>Label Specifications</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.parsley.dsl.model.PropertyDescriptionSpecification}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Label Specifications</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Label Specifications</em>' containment reference list.
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getFormPropertyDescriptionProvider_LabelSpecifications()
   * @model containment="true"
   * @generated
   */
  EList<PropertyDescriptionSpecification> getLabelSpecifications();

} // FormPropertyDescriptionProvider
