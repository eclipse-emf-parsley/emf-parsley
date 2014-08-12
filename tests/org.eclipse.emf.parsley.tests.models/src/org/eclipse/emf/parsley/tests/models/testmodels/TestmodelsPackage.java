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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsFactory
 * @model kind="package"
 * @generated
 */
public interface TestmodelsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "testmodels";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///org/eclipse/emf/parsley/tests/models/ParsleyTests.ecore/0.1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "testmodels";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TestmodelsPackage eINSTANCE = org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.TestEClassImpl <em>Test EClass</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestEClassImpl
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getTestEClass()
	 * @generated
	 */
	int TEST_ECLASS = 0;

	/**
	 * The feature id for the '<em><b>Lowercase Name Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_ECLASS__LOWERCASE_NAME_FEATURE = 0;

	/**
	 * The feature id for the '<em><b>Upper Case Name Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_ECLASS__UPPER_CASE_NAME_FEATURE = 1;

	/**
	 * The feature id for the '<em><b>Not Changeable Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_ECLASS__NOT_CHANGEABLE_FEATURE = 2;

	/**
	 * The feature id for the '<em><b>Derived Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_ECLASS__DERIVED_FEATURE = 3;

	/**
	 * The number of structural features of the '<em>Test EClass</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_ECLASS_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Test EClass</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_ECLASS_OPERATION_COUNT = 0;


	/**
	 * The meta object id for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.BaseClassImpl <em>Base Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.BaseClassImpl
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getBaseClass()
	 * @generated
	 */
	int BASE_CLASS = 1;

	/**
	 * The feature id for the '<em><b>Base Class Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_CLASS__BASE_CLASS_FEATURE = 0;

	/**
	 * The number of structural features of the '<em>Base Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_CLASS_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Base Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_CLASS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.DerivedClassImpl <em>Derived Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.DerivedClassImpl
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getDerivedClass()
	 * @generated
	 */
	int DERIVED_CLASS = 2;

	/**
	 * The feature id for the '<em><b>Base Class Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_CLASS__BASE_CLASS_FEATURE = BASE_CLASS__BASE_CLASS_FEATURE;

	/**
	 * The feature id for the '<em><b>Derived Class Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_CLASS__DERIVED_CLASS_FEATURE = BASE_CLASS_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Derived Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_CLASS_FEATURE_COUNT = BASE_CLASS_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Derived Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_CLASS_OPERATION_COUNT = BASE_CLASS_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.parsley.tests.models.testmodels.TestEClass <em>Test EClass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test EClass</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestEClass
	 * @generated
	 */
	EClass getTestEClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.TestEClass#getLowercaseNameFeature <em>Lowercase Name Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lowercase Name Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestEClass#getLowercaseNameFeature()
	 * @see #getTestEClass()
	 * @generated
	 */
	EAttribute getTestEClass_LowercaseNameFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.TestEClass#getUpperCaseNameFeature <em>Upper Case Name Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper Case Name Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestEClass#getUpperCaseNameFeature()
	 * @see #getTestEClass()
	 * @generated
	 */
	EAttribute getTestEClass_UpperCaseNameFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.TestEClass#getNotChangeableFeature <em>Not Changeable Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Not Changeable Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestEClass#getNotChangeableFeature()
	 * @see #getTestEClass()
	 * @generated
	 */
	EAttribute getTestEClass_NotChangeableFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.TestEClass#getDerivedFeature <em>Derived Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Derived Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestEClass#getDerivedFeature()
	 * @see #getTestEClass()
	 * @generated
	 */
	EAttribute getTestEClass_DerivedFeature();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.parsley.tests.models.testmodels.BaseClass <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Base Class</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.BaseClass
	 * @generated
	 */
	EClass getBaseClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.BaseClass#getBaseClassFeature <em>Base Class Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Base Class Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.BaseClass#getBaseClassFeature()
	 * @see #getBaseClass()
	 * @generated
	 */
	EAttribute getBaseClass_BaseClassFeature();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.parsley.tests.models.testmodels.DerivedClass <em>Derived Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Derived Class</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.DerivedClass
	 * @generated
	 */
	EClass getDerivedClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.DerivedClass#getDerivedClassFeature <em>Derived Class Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Derived Class Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.DerivedClass#getDerivedClassFeature()
	 * @see #getDerivedClass()
	 * @generated
	 */
	EAttribute getDerivedClass_DerivedClassFeature();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TestmodelsFactory getTestmodelsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.TestEClassImpl <em>Test EClass</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestEClassImpl
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getTestEClass()
		 * @generated
		 */
		EClass TEST_ECLASS = eINSTANCE.getTestEClass();
		/**
		 * The meta object literal for the '<em><b>Lowercase Name Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_ECLASS__LOWERCASE_NAME_FEATURE = eINSTANCE.getTestEClass_LowercaseNameFeature();
		/**
		 * The meta object literal for the '<em><b>Upper Case Name Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_ECLASS__UPPER_CASE_NAME_FEATURE = eINSTANCE.getTestEClass_UpperCaseNameFeature();
		/**
		 * The meta object literal for the '<em><b>Not Changeable Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_ECLASS__NOT_CHANGEABLE_FEATURE = eINSTANCE.getTestEClass_NotChangeableFeature();
		/**
		 * The meta object literal for the '<em><b>Derived Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_ECLASS__DERIVED_FEATURE = eINSTANCE.getTestEClass_DerivedFeature();
		/**
		 * The meta object literal for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.BaseClassImpl <em>Base Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.BaseClassImpl
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getBaseClass()
		 * @generated
		 */
		EClass BASE_CLASS = eINSTANCE.getBaseClass();
		/**
		 * The meta object literal for the '<em><b>Base Class Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASE_CLASS__BASE_CLASS_FEATURE = eINSTANCE.getBaseClass_BaseClassFeature();
		/**
		 * The meta object literal for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.DerivedClassImpl <em>Derived Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.DerivedClassImpl
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getDerivedClass()
		 * @generated
		 */
		EClass DERIVED_CLASS = eINSTANCE.getDerivedClass();
		/**
		 * The meta object literal for the '<em><b>Derived Class Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DERIVED_CLASS__DERIVED_CLASS_FEATURE = eINSTANCE.getDerivedClass_DerivedClassFeature();

	}

} //TestmodelsPackage
