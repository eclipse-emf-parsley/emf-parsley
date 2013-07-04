/**
 */
package org.eclipse.emf.parsley.dsl.model;

import org.eclipse.xtext.common.types.JvmMember;

import org.eclipse.xtext.xbase.XExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Description Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.PropertyDescriptionSpecification#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.PropertyDescriptionSpecification#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getPropertyDescriptionSpecification()
 * @model
 * @generated
 */
public interface PropertyDescriptionSpecification extends EmfFeatureAccess
{
  /**
   * Returns the value of the '<em><b>Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Feature</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Feature</em>' reference.
   * @see #setFeature(JvmMember)
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getPropertyDescriptionSpecification_Feature()
   * @model
   * @generated
   */
  JvmMember getFeature();

  /**
   * Sets the value of the '{@link org.eclipse.emf.parsley.dsl.model.PropertyDescriptionSpecification#getFeature <em>Feature</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Feature</em>' reference.
   * @see #getFeature()
   * @generated
   */
  void setFeature(JvmMember value);

  /**
   * Returns the value of the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expression</em>' containment reference.
   * @see #setExpression(XExpression)
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getPropertyDescriptionSpecification_Expression()
   * @model containment="true"
   * @generated
   */
  XExpression getExpression();

  /**
   * Sets the value of the '{@link org.eclipse.emf.parsley.dsl.model.PropertyDescriptionSpecification#getExpression <em>Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expression</em>' containment reference.
   * @see #getExpression()
   * @generated
   */
  void setExpression(XExpression value);

} // PropertyDescriptionSpecification
