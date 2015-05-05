/**
 */
package org.eclipse.emf.parsley.tests.models.testmodels;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Derived Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.DerivedClass#getDerivedClassFeature <em>Derived Class Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.DerivedClass#getDerivedMultiReferenceFeature <em>Derived Multi Reference Feature</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getDerivedClass()
 * @model
 * @generated
 */
public interface DerivedClass extends BaseClass {
	/**
	 * Returns the value of the '<em><b>Derived Class Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Derived Class Feature</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Derived Class Feature</em>' attribute.
	 * @see #setDerivedClassFeature(String)
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getDerivedClass_DerivedClassFeature()
	 * @model
	 * @generated
	 */
	String getDerivedClassFeature();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.tests.models.testmodels.DerivedClass#getDerivedClassFeature <em>Derived Class Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Derived Class Feature</em>' attribute.
	 * @see #getDerivedClassFeature()
	 * @generated
	 */
	void setDerivedClassFeature(String value);

	/**
	 * Returns the value of the '<em><b>Derived Multi Reference Feature</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Derived Multi Reference Feature</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Derived Multi Reference Feature</em>' reference list.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getDerivedClass_DerivedMultiReferenceFeature()
	 * @model
	 * @generated
	 */
	EList<ClassWithName> getDerivedMultiReferenceFeature();

} // DerivedClass
