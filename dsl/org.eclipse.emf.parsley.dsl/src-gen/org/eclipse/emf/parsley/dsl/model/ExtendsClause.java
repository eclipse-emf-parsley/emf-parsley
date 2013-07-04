/**
 */
package org.eclipse.emf.parsley.dsl.model;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.common.types.JvmTypeReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extends Clause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.ExtendsClause#getSuperType <em>Super Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getExtendsClause()
 * @model
 * @generated
 */
public interface ExtendsClause extends EObject
{
  /**
   * Returns the value of the '<em><b>Super Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Super Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Super Type</em>' containment reference.
   * @see #setSuperType(JvmTypeReference)
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getExtendsClause_SuperType()
   * @model containment="true"
   * @generated
   */
  JvmTypeReference getSuperType();

  /**
   * Sets the value of the '{@link org.eclipse.emf.parsley.dsl.model.ExtendsClause#getSuperType <em>Super Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Super Type</em>' containment reference.
   * @see #getSuperType()
   * @generated
   */
  void setSuperType(JvmTypeReference value);

} // ExtendsClause
