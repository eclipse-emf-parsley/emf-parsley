/**
 */
package org.eclipse.emf.parsley.dsl.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Features Provider</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.FeaturesProvider#getFeatureSpecifications <em>Feature Specifications</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getFeaturesProvider()
 * @model
 * @generated
 */
public interface FeaturesProvider extends EObject
{
  /**
   * Returns the value of the '<em><b>Feature Specifications</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.parsley.dsl.model.FeatureSpecification}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Feature Specifications</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Feature Specifications</em>' containment reference list.
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getFeaturesProvider_FeatureSpecifications()
   * @model containment="true"
   * @generated
   */
  EList<FeatureSpecification> getFeatureSpecifications();

} // FeaturesProvider
