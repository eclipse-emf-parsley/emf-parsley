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
	 * The number of structural features of the '<em>Test EClass</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_ECLASS_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Test EClass</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_ECLASS_OPERATION_COUNT = 0;


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

	}

} //TestmodelsPackage
