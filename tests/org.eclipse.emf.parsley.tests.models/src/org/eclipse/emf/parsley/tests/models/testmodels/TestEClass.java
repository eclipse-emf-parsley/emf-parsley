/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
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
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.TestEClass#getLowercaseNameFeature <em>Lowercase Name Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.TestEClass#getUpperCaseNameFeature <em>Upper Case Name Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.TestEClass#getNotChangeableFeature <em>Not Changeable Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.TestEClass#getDerivedFeature <em>Derived Feature</em>}</li>
 * </ul>
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

	/**
	 * Returns the value of the '<em><b>Not Changeable Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Not Changeable Feature</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Not Changeable Feature</em>' attribute.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getTestEClass_NotChangeableFeature()
	 * @model changeable="false"
	 * @generated
	 */
	String getNotChangeableFeature();

	/**
	 * Returns the value of the '<em><b>Derived Feature</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Derived Feature</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Derived Feature</em>' attribute.
	 * @see #setDerivedFeature(String)
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#getTestEClass_DerivedFeature()
	 * @model default="" derived="true"
	 * @generated
	 */
	String getDerivedFeature();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.tests.models.testmodels.TestEClass#getDerivedFeature <em>Derived Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Derived Feature</em>' attribute.
	 * @see #getDerivedFeature()
	 * @generated
	 */
	void setDerivedFeature(String value);
} // TestEClass
