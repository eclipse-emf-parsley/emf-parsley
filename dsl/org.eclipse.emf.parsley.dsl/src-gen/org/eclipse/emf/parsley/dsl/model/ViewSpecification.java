/**
 */
package org.eclipse.emf.parsley.dsl.model;

import org.eclipse.xtext.common.types.JvmTypeReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>View Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.ViewSpecification#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.ViewSpecification#getViewName <em>View Name</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.ViewSpecification#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.ViewSpecification#getCategory <em>Category</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getViewSpecification()
 * @model
 * @generated
 */
public interface ViewSpecification extends PartSpecification
{
  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(String)
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getViewSpecification_Id()
   * @model
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link org.eclipse.emf.parsley.dsl.model.ViewSpecification#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

  /**
   * Returns the value of the '<em><b>View Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>View Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>View Name</em>' attribute.
   * @see #setViewName(String)
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getViewSpecification_ViewName()
   * @model
   * @generated
   */
  String getViewName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.parsley.dsl.model.ViewSpecification#getViewName <em>View Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>View Name</em>' attribute.
   * @see #getViewName()
   * @generated
   */
  void setViewName(String value);

  /**
   * Returns the value of the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' containment reference.
   * @see #setType(JvmTypeReference)
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getViewSpecification_Type()
   * @model containment="true"
   * @generated
   */
  JvmTypeReference getType();

  /**
   * Sets the value of the '{@link org.eclipse.emf.parsley.dsl.model.ViewSpecification#getType <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' containment reference.
   * @see #getType()
   * @generated
   */
  void setType(JvmTypeReference value);

  /**
   * Returns the value of the '<em><b>Category</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Category</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Category</em>' attribute.
   * @see #setCategory(String)
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#getViewSpecification_Category()
   * @model
   * @generated
   */
  String getCategory();

  /**
   * Sets the value of the '{@link org.eclipse.emf.parsley.dsl.model.ViewSpecification#getCategory <em>Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Category</em>' attribute.
   * @see #getCategory()
   * @generated
   */
  void setCategory(String value);

} // ViewSpecification
