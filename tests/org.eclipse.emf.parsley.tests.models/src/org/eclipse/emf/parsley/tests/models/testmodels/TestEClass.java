/**
 */
package org.eclipse.emf.parsley.tests.models.testmodels;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test EClass</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.TestEClass#getLowercaseNameFeature <em>Lowercase Name Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.TestEClass#getUpperCaseNameFeature <em>Upper Case Name Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getTestEClass()
 * @model
 * @generated
 */
public interface TestEClass extends EObject {

	/**
	 * Returns the value of the '<em><b>Lowercase Name Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lowercase Name Feature</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lowercase Name Feature</em>' attribute.
	 * @see #setLowercaseNameFeature(String)
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getTestEClass_LowercaseNameFeature()
	 * @model
	 * @generated
	 */
	String getLowercaseNameFeature();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.tests.models.testmodels.TestEClass#getLowercaseNameFeature <em>Lowercase Name Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lowercase Name Feature</em>' attribute.
	 * @see #getLowercaseNameFeature()
	 * @generated
	 */
	void setLowercaseNameFeature(String value);

	/**
	 * Returns the value of the '<em><b>Upper Case Name Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Upper Case Name Feature</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Upper Case Name Feature</em>' attribute.
	 * @see #setUpperCaseNameFeature(String)
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getTestEClass_UpperCaseNameFeature()
	 * @model
	 * @generated
	 */
	String getUpperCaseNameFeature();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.tests.models.testmodels.TestEClass#getUpperCaseNameFeature <em>Upper Case Name Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upper Case Name Feature</em>' attribute.
	 * @see #getUpperCaseNameFeature()
	 * @generated
	 */
	void setUpperCaseNameFeature(String value);
} // TestEClass
