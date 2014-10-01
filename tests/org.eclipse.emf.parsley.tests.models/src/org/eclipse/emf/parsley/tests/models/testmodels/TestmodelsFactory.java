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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage
 * @generated
 */
public interface TestmodelsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TestmodelsFactory eINSTANCE = org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Test EClass</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test EClass</em>'.
	 * @generated
	 */
	TestEClass createTestEClass();

	/**
	 * Returns a new object of class '<em>ABase Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>ABase Class</em>'.
	 * @generated
	 */
	ABaseClass createABaseClass();

	/**
	 * Returns a new object of class '<em>Base Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Base Class</em>'.
	 * @generated
	 */
	BaseClass createBaseClass();

	/**
	 * Returns a new object of class '<em>Derived Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Derived Class</em>'.
	 * @generated
	 */
	DerivedClass createDerivedClass();

	/**
	 * Returns a new object of class '<em>Derived Derived Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Derived Derived Class</em>'.
	 * @generated
	 */
	DerivedDerivedClass createDerivedDerivedClass();

	/**
	 * Returns a new object of class '<em>Multiple Inheritance Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Multiple Inheritance Class</em>'.
	 * @generated
	 */
	MultipleInheritanceClass createMultipleInheritanceClass();

	/**
	 * Returns a new object of class '<em>Class For Controls</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Class For Controls</em>'.
	 * @generated
	 */
	ClassForControls createClassForControls();

	/**
	 * Returns a new object of class '<em>Class With Name</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Class With Name</em>'.
	 * @generated
	 */
	ClassWithName createClassWithName();

	/**
	 * Returns a new object of class '<em>Base Class For Feature Map Entry</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Base Class For Feature Map Entry</em>'.
	 * @generated
	 */
	BaseClassForFeatureMapEntry createBaseClassForFeatureMapEntry();

	/**
	 * Returns a new object of class '<em>Class For Feature Map Entry1</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Class For Feature Map Entry1</em>'.
	 * @generated
	 */
	ClassForFeatureMapEntry1 createClassForFeatureMapEntry1();

	/**
	 * Returns a new object of class '<em>Class For Feature Map Entry2</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Class For Feature Map Entry2</em>'.
	 * @generated
	 */
	ClassForFeatureMapEntry2 createClassForFeatureMapEntry2();

	/**
	 * Returns a new object of class '<em>Test Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Container</em>'.
	 * @generated
	 */
	TestContainer createTestContainer();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TestmodelsPackage getTestmodelsPackage();

} //TestmodelsFactory
