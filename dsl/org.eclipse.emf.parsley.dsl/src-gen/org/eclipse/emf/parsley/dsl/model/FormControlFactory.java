/**
 */
package org.eclipse.emf.parsley.dsl.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Form Control Factory</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.FormControlFactory#getControlSpecifications <em>Control Specifications</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getFormControlFactory()
 * @model
 * @generated
 */
public interface FormControlFactory extends EObject
{
  /**
   * Returns the value of the '<em><b>Control Specifications</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.parsley.dsl.model.FormControlSpecification}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Control Specifications</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Control Specifications</em>' containment reference list.
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getFormControlFactory_ControlSpecifications()
   * @model containment="true"
   * @generated
   */
  EList<FormControlSpecification> getControlSpecifications();

} // FormControlFactory
