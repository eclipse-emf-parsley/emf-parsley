/**
 */
package org.eclipse.emf.parsley.dsl.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.xtext.common.types.JvmMember;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.FeatureSpecification#getFeatures <em>Features</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getFeatureSpecification()
 * @model
 * @generated
 */
public interface FeatureSpecification extends EmfFeatureAccess
{
  /**
   * Returns the value of the '<em><b>Features</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.xtext.common.types.JvmMember}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Features</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Features</em>' reference list.
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getFeatureSpecification_Features()
   * @model
   * @generated
   */
  EList<JvmMember> getFeatures();

} // FeatureSpecification
