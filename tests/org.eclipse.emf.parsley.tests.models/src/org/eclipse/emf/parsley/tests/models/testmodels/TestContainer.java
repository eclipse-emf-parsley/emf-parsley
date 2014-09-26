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
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getClassesWithName <em>Classes With Name</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getClassesForControls <em>Classes For Controls</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getTestContainer()
 * @model
 * @generated
 */
public interface TestContainer extends EObject {
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

} // TestContainer
