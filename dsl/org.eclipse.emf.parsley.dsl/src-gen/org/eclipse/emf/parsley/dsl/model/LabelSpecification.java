/**
 */
package org.eclipse.emf.parsley.dsl.model;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.common.types.JvmTypeReference;

import org.eclipse.xtext.xbase.XExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Label Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.LabelSpecification#getParameterType <em>Parameter Type</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.LabelSpecification#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.LabelSpecification#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getLabelSpecification()
 * @model
 * @generated
 */
public interface LabelSpecification extends EObject
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
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getLabelSpecification_ParameterType()
   * @model containment="true"
   * @generated
   */
  JvmTypeReference getParameterType();

  /**
   * Sets the value of the '{@link org.eclipse.emf.parsley.dsl.model.LabelSpecification#getParameterType <em>Parameter Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parameter Type</em>' containment reference.
   * @see #getParameterType()
   * @generated
   */
  void setParameterType(JvmTypeReference value);

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
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getLabelSpecification_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.parsley.dsl.model.LabelSpecification#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

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
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getLabelSpecification_Expression()
   * @model containment="true"
   * @generated
   */
  XExpression getExpression();

  /**
   * Sets the value of the '{@link org.eclipse.emf.parsley.dsl.model.LabelSpecification#getExpression <em>Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expression</em>' containment reference.
   * @see #getExpression()
   * @generated
   */
  void setExpression(XExpression value);

} // LabelSpecification
