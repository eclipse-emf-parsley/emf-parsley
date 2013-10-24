/**
 */
package org.eclipse.emf.parsley.dsl.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Viewer Content Provider</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.ViewerContentProvider#getElementsSpecifications <em>Elements Specifications</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.ViewerContentProvider#getChildrenSpecifications <em>Children Specifications</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getViewerContentProvider()
 * @model
 * @generated
 */
public interface ViewerContentProvider extends EObject
{
  /**
   * Returns the value of the '<em><b>Elements Specifications</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.parsley.dsl.model.LabelSpecification}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Elements Specifications</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Elements Specifications</em>' containment reference list.
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getViewerContentProvider_ElementsSpecifications()
   * @model containment="true"
   * @generated
   */
  EList<LabelSpecification> getElementsSpecifications();

  /**
   * Returns the value of the '<em><b>Children Specifications</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.parsley.dsl.model.LabelSpecification}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Children Specifications</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Children Specifications</em>' containment reference list.
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getViewerContentProvider_ChildrenSpecifications()
   * @model containment="true"
   * @generated
   */
  EList<LabelSpecification> getChildrenSpecifications();

} // ViewerContentProvider
