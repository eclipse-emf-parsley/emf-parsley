/**
 */
package org.eclipse.emf.parsley.tests.models.testmodels;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Base Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.BaseClass#getBaseClassFeature <em>Base Class Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getBaseClass()
 * @model
 * @generated
 */
public interface BaseClass extends EObject {
	/**
	 * Returns the value of the '<em><b>Base Class Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Class Feature</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Class Feature</em>' attribute.
	 * @see #setBaseClassFeature(String)
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getBaseClass_BaseClassFeature()
	 * @model
	 * @generated
	 */
	String getBaseClassFeature();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.tests.models.testmodels.BaseClass#getBaseClassFeature <em>Base Class Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Class Feature</em>' attribute.
	 * @see #getBaseClassFeature()
	 * @generated
	 */
	void setBaseClassFeature(String value);

} // BaseClass
