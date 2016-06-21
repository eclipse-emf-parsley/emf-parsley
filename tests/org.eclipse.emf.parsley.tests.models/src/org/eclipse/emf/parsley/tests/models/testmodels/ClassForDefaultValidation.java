/**
 */
package org.eclipse.emf.parsley.tests.models.testmodels;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class For Default Validation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForDefaultValidation#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForDefaultValidation#getNotEmpty <em>Not Empty</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForDefaultValidation#getCanBeEmpty <em>Can Be Empty</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getClassForDefaultValidation()
 * @model
 * @generated
 */
public interface ClassForDefaultValidation extends EObject {
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
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getClassForDefaultValidation_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForDefaultValidation#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Not Empty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Not Empty</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Not Empty</em>' attribute.
	 * @see #setNotEmpty(String)
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getClassForDefaultValidation_NotEmpty()
	 * @model required="true"
	 * @generated
	 */
	String getNotEmpty();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForDefaultValidation#getNotEmpty <em>Not Empty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Not Empty</em>' attribute.
	 * @see #getNotEmpty()
	 * @generated
	 */
	void setNotEmpty(String value);

	/**
	 * Returns the value of the '<em><b>Can Be Empty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Can Be Empty</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Can Be Empty</em>' attribute.
	 * @see #setCanBeEmpty(String)
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getClassForDefaultValidation_CanBeEmpty()
	 * @model
	 * @generated
	 */
	String getCanBeEmpty();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForDefaultValidation#getCanBeEmpty <em>Can Be Empty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Can Be Empty</em>' attribute.
	 * @see #getCanBeEmpty()
	 * @generated
	 */
	void setCanBeEmpty(String value);

} // ClassForDefaultValidation
