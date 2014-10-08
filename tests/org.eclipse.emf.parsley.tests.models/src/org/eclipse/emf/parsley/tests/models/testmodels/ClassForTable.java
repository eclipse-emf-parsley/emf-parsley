/**
 */
package org.eclipse.emf.parsley.tests.models.testmodels;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class For Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForTable#getClassWithName1 <em>Class With Name1</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForTable#getClassWithName2 <em>Class With Name2</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getClassForTable()
 * @model
 * @generated
 */
public interface ClassForTable extends EObject {
	/**
	 * Returns the value of the '<em><b>Class With Name1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class With Name1</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class With Name1</em>' reference.
	 * @see #setClassWithName1(ClassWithName)
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getClassForTable_ClassWithName1()
	 * @model
	 * @generated
	 */
	ClassWithName getClassWithName1();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForTable#getClassWithName1 <em>Class With Name1</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class With Name1</em>' reference.
	 * @see #getClassWithName1()
	 * @generated
	 */
	void setClassWithName1(ClassWithName value);

	/**
	 * Returns the value of the '<em><b>Class With Name2</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class With Name2</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class With Name2</em>' reference.
	 * @see #setClassWithName2(ClassWithName)
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getClassForTable_ClassWithName2()
	 * @model
	 * @generated
	 */
	ClassWithName getClassWithName2();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForTable#getClassWithName2 <em>Class With Name2</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class With Name2</em>' reference.
	 * @see #getClassWithName2()
	 * @generated
	 */
	void setClassWithName2(ClassWithName value);

} // ClassForTable
