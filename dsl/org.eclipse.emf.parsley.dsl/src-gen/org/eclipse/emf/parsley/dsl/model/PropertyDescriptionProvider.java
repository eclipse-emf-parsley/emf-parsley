/**
 */
package org.eclipse.emf.parsley.dsl.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Description Provider</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.PropertyDescriptionProvider#getLabelSpecifications <em>Label Specifications</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getPropertyDescriptionProvider()
 * @model
 * @generated
 */
public interface PropertyDescriptionProvider extends EObject
{
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
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getPropertyDescriptionProvider_LabelSpecifications()
   * @model containment="true"
   * @generated
   */
  EList<PropertyDescriptionSpecification> getLabelSpecifications();

} // PropertyDescriptionProvider
