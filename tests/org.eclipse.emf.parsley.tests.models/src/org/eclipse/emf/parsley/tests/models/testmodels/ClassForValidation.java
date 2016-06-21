/**
 */
package org.eclipse.emf.parsley.tests.models.testmodels;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class For Validation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForValidation#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForValidation#getNotEmpty <em>Not Empty</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getClassForValidation()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='NotEmpty'"
 * @generated
 */
public interface ClassForValidation extends EObject {
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
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getClassForValidation_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForValidation#getName <em>Name</em>}' attribute.
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
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getClassForValidation_NotEmpty()
	 * @model
	 * @generated
	 */
	String getNotEmpty();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForValidation#getNotEmpty <em>Not Empty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Not Empty</em>' attribute.
	 * @see #getNotEmpty()
	 * @generated
	 */
	void setNotEmpty(String value);

} // ClassForValidation
