/**
 */
package org.eclipse.emf.parsley.dsl.model;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.common.types.JvmTypeReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Emf Feature Access</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.EmfFeatureAccess#getParameterType <em>Parameter Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getEmfFeatureAccess()
 * @model
 * @generated
 */
public interface EmfFeatureAccess extends EObject
{
  /**
   * Returns the value of the '<em><b>Parameter Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parameter Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameter Type</em>' containment reference.
   * @see #setParameterType(JvmTypeReference)
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getEmfFeatureAccess_ParameterType()
   * @model containment="true"
   * @generated
   */
  JvmTypeReference getParameterType();

  /**
   * Sets the value of the '{@link org.eclipse.emf.parsley.dsl.model.EmfFeatureAccess#getParameterType <em>Parameter Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parameter Type</em>' containment reference.
   * @see #getParameterType()
   * @generated
   */
  void setParameterType(JvmTypeReference value);

} // EmfFeatureAccess
