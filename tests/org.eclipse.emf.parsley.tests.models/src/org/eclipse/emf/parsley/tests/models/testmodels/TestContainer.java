/**
 */
package org.eclipse.emf.parsley.tests.models.testmodels;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getClassesWithName <em>Classes With Name</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getClassesForControls <em>Classes For Controls</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getContained <em>Contained</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getClassesForTable <em>Classes For Table</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getObjectsForValidation <em>Objects For Validation</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getMultipleContained <em>Multiple Contained</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getObjectsForDefaultValidation <em>Objects For Default Validation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getTestContainer()
 * @model
 * @generated
 */
public interface TestContainer extends EObject {
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
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getTestContainer_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Classes With Name</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classes With Name</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classes With Name</em>' containment reference list.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getTestContainer_ClassesWithName()
	 * @model containment="true"
	 * @generated
	 */
	EList<ClassWithName> getClassesWithName();

	/**
	 * Returns the value of the '<em><b>Classes For Controls</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classes For Controls</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classes For Controls</em>' containment reference list.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getTestContainer_ClassesForControls()
	 * @model containment="true"
	 * @generated
	 */
	EList<ClassForControls> getClassesForControls();

	/**
	 * Returns the value of the '<em><b>Contained</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contained</em>' containment reference.
	 * @see #setContained(TestContainer)
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getTestContainer_Contained()
	 * @model containment="true"
	 * @generated
	 */
	TestContainer getContained();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getContained <em>Contained</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Contained</em>' containment reference.
	 * @see #getContained()
	 * @generated
	 */
	void setContained(TestContainer value);

	/**
	 * Returns the value of the '<em><b>Classes For Table</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForTable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classes For Table</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classes For Table</em>' containment reference list.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getTestContainer_ClassesForTable()
	 * @model containment="true"
	 * @generated
	 */
	EList<ClassForTable> getClassesForTable();

	/**
	 * Returns the value of the '<em><b>Objects For Validation</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForValidation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Objects For Validation</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Objects For Validation</em>' containment reference list.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getTestContainer_ObjectsForValidation()
	 * @model containment="true"
	 * @generated
	 */
	EList<ClassForValidation> getObjectsForValidation();

	/**
	 * Returns the value of the '<em><b>Multiple Contained</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.parsley.tests.models.testmodels.TestContainer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multiple Contained</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multiple Contained</em>' containment reference list.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getTestContainer_MultipleContained()
	 * @model containment="true"
	 * @generated
	 */
	EList<TestContainer> getMultipleContained();

	/**
	 * Returns the value of the '<em><b>Objects For Default Validation</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForDefaultValidation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Objects For Default Validation</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Objects For Default Validation</em>' containment reference list.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getTestContainer_ObjectsForDefaultValidation()
	 * @model containment="true"
	 * @generated
	 */
	EList<ClassForDefaultValidation> getObjectsForDefaultValidation();

} // TestContainer
