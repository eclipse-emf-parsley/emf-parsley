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
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
	int TEST_ECLASS = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.BaseClassImpl <em>Base Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.BaseClassImpl
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getBaseClass()
	 * @generated
	 */
	int BASE_CLASS = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.DerivedClassImpl <em>Derived Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.DerivedClassImpl
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getDerivedClass()
	 * @generated
	 */
	int DERIVED_CLASS = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl <em>Class For Controls</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getClassForControls()
	 * @generated
	 */
	int CLASS_FOR_CONTROLS = 8;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassWithNameImpl <em>Class With Name</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassWithNameImpl
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getClassWithName()
	 * @generated
	 */
	int CLASS_WITH_NAME = 9;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.TestContainerImpl <em>Test Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestContainerImpl
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getTestContainer()
	 * @generated
	 */
	int TEST_CONTAINER = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONTAINER__NAME = 0;

	/**
	 * The feature id for the '<em><b>Classes With Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONTAINER__CLASSES_WITH_NAME = 1;

	/**
	 * The feature id for the '<em><b>Classes For Controls</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONTAINER__CLASSES_FOR_CONTROLS = 2;

	/**
	 * The feature id for the '<em><b>Contained</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONTAINER__CONTAINED = 3;

	/**
	 * The feature id for the '<em><b>Classes For Table</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONTAINER__CLASSES_FOR_TABLE = 4;

	/**
	 * The feature id for the '<em><b>Objects For Validation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONTAINER__OBJECTS_FOR_VALIDATION = 5;

	/**
	 * The feature id for the '<em><b>Multiple Contained</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONTAINER__MULTIPLE_CONTAINED = 6;

	/**
	 * The feature id for the '<em><b>Objects For Default Validation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONTAINER__OBJECTS_FOR_DEFAULT_VALIDATION = 7;

	/**
	 * The number of structural features of the '<em>Test Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONTAINER_FEATURE_COUNT = 8;

	/**
	 * The number of operations of the '<em>Test Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONTAINER_OPERATION_COUNT = 0;

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
	 * The meta object id for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.TestEClassForFeatureNameImpl <em>Test EClass For Feature Name</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestEClassForFeatureNameImpl
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getTestEClassForFeatureName()
	 * @generated
	 */
	int TEST_ECLASS_FOR_FEATURE_NAME = 2;

	/**
	 * The feature id for the '<em><b>EField</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_ECLASS_FOR_FEATURE_NAME__EFIELD = 0;

	/**
	 * The number of structural features of the '<em>Test EClass For Feature Name</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_ECLASS_FOR_FEATURE_NAME_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Test EClass For Feature Name</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_ECLASS_FOR_FEATURE_NAME_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ABaseClassImpl <em>ABase Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.ABaseClassImpl
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getABaseClass()
	 * @generated
	 */
	int ABASE_CLASS = 3;

	/**
	 * The number of structural features of the '<em>ABase Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABASE_CLASS_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>ABase Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABASE_CLASS_OPERATION_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Base Class Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_CLASS__BASE_CLASS_FEATURE = 0;

	/**
	 * The feature id for the '<em><b>Base Multi Reference Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_CLASS__BASE_MULTI_REFERENCE_FEATURE = 1;

	/**
	 * The number of structural features of the '<em>Base Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_CLASS_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Base Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_CLASS_OPERATION_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Base Class Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_CLASS__BASE_CLASS_FEATURE = BASE_CLASS__BASE_CLASS_FEATURE;

	/**
	 * The feature id for the '<em><b>Base Multi Reference Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_CLASS__BASE_MULTI_REFERENCE_FEATURE = BASE_CLASS__BASE_MULTI_REFERENCE_FEATURE;

	/**
	 * The feature id for the '<em><b>Derived Class Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_CLASS__DERIVED_CLASS_FEATURE = BASE_CLASS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Derived Multi Reference Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_CLASS__DERIVED_MULTI_REFERENCE_FEATURE = BASE_CLASS_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Derived Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_CLASS_FEATURE_COUNT = BASE_CLASS_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Derived Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_CLASS_OPERATION_COUNT = BASE_CLASS_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.DerivedDerivedClassImpl <em>Derived Derived Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.DerivedDerivedClassImpl
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getDerivedDerivedClass()
	 * @generated
	 */
	int DERIVED_DERIVED_CLASS = 6;

	/**
	 * The feature id for the '<em><b>Base Class Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_DERIVED_CLASS__BASE_CLASS_FEATURE = DERIVED_CLASS__BASE_CLASS_FEATURE;

	/**
	 * The feature id for the '<em><b>Base Multi Reference Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_DERIVED_CLASS__BASE_MULTI_REFERENCE_FEATURE = DERIVED_CLASS__BASE_MULTI_REFERENCE_FEATURE;

	/**
	 * The feature id for the '<em><b>Derived Class Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_DERIVED_CLASS__DERIVED_CLASS_FEATURE = DERIVED_CLASS__DERIVED_CLASS_FEATURE;

	/**
	 * The feature id for the '<em><b>Derived Multi Reference Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_DERIVED_CLASS__DERIVED_MULTI_REFERENCE_FEATURE = DERIVED_CLASS__DERIVED_MULTI_REFERENCE_FEATURE;

	/**
	 * The number of structural features of the '<em>Derived Derived Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_DERIVED_CLASS_FEATURE_COUNT = DERIVED_CLASS_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Derived Derived Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_DERIVED_CLASS_OPERATION_COUNT = DERIVED_CLASS_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.MultipleInheritanceClassImpl <em>Multiple Inheritance Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.MultipleInheritanceClassImpl
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getMultipleInheritanceClass()
	 * @generated
	 */
	int MULTIPLE_INHERITANCE_CLASS = 7;

	/**
	 * The feature id for the '<em><b>Lowercase Name Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLE_INHERITANCE_CLASS__LOWERCASE_NAME_FEATURE = TEST_ECLASS__LOWERCASE_NAME_FEATURE;

	/**
	 * The feature id for the '<em><b>Upper Case Name Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLE_INHERITANCE_CLASS__UPPER_CASE_NAME_FEATURE = TEST_ECLASS__UPPER_CASE_NAME_FEATURE;

	/**
	 * The feature id for the '<em><b>Not Changeable Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLE_INHERITANCE_CLASS__NOT_CHANGEABLE_FEATURE = TEST_ECLASS__NOT_CHANGEABLE_FEATURE;

	/**
	 * The feature id for the '<em><b>Derived Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLE_INHERITANCE_CLASS__DERIVED_FEATURE = TEST_ECLASS__DERIVED_FEATURE;

	/**
	 * The feature id for the '<em><b>Base Class Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLE_INHERITANCE_CLASS__BASE_CLASS_FEATURE = TEST_ECLASS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Base Multi Reference Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLE_INHERITANCE_CLASS__BASE_MULTI_REFERENCE_FEATURE = TEST_ECLASS_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Derived Class Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLE_INHERITANCE_CLASS__DERIVED_CLASS_FEATURE = TEST_ECLASS_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Derived Multi Reference Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLE_INHERITANCE_CLASS__DERIVED_MULTI_REFERENCE_FEATURE = TEST_ECLASS_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Multiple Inheritance Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLE_INHERITANCE_CLASS_FEATURE_COUNT = TEST_ECLASS_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Multiple Inheritance Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLE_INHERITANCE_CLASS_OPERATION_COUNT = TEST_ECLASS_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Boolean Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__BOOLEAN_FEATURE = 0;

	/**
	 * The feature id for the '<em><b>Boolean Object Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__BOOLEAN_OBJECT_FEATURE = 1;

	/**
	 * The feature id for the '<em><b>Boolean Data Type Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__BOOLEAN_DATA_TYPE_FEATURE = 2;

	/**
	 * The feature id for the '<em><b>Boolean Primitive Data Type Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__BOOLEAN_PRIMITIVE_DATA_TYPE_FEATURE = 3;

	/**
	 * The feature id for the '<em><b>Enum Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__ENUM_FEATURE = 4;

	/**
	 * The feature id for the '<em><b>String Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__STRING_FEATURE = 5;

	/**
	 * The feature id for the '<em><b>String Data Type Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__STRING_DATA_TYPE_FEATURE = 6;

	/**
	 * The feature id for the '<em><b>String Feature With Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__STRING_FEATURE_WITH_DEFAULT = 7;

	/**
	 * The feature id for the '<em><b>Unserializable String Data Type Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__UNSERIALIZABLE_STRING_DATA_TYPE_FEATURE = 8;

	/**
	 * The feature id for the '<em><b>Unchangeable String Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__UNCHANGEABLE_STRING_FEATURE = 9;

	/**
	 * The feature id for the '<em><b>Derived String Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__DERIVED_STRING_FEATURE = 10;

	/**
	 * The feature id for the '<em><b>Reference To Class With Name</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__REFERENCE_TO_CLASS_WITH_NAME = 11;

	/**
	 * The feature id for the '<em><b>Multi Reference Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__MULTI_REFERENCE_FEATURE = 12;

	/**
	 * The feature id for the '<em><b>Feature Map Entries</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES = 13;

	/**
	 * The feature id for the '<em><b>Feature Map Entries1</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES1 = 14;

	/**
	 * The feature id for the '<em><b>Feature Map Entries2</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES2 = 15;

	/**
	 * The feature id for the '<em><b>Single Containment Reference</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__SINGLE_CONTAINMENT_REFERENCE = 16;

	/**
	 * The feature id for the '<em><b>Big Decimal Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__BIG_DECIMAL_FEATURE = 17;

	/**
	 * The feature id for the '<em><b>Big Integer Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__BIG_INTEGER_FEATURE = 18;

	/**
	 * The feature id for the '<em><b>Double Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__DOUBLE_FEATURE = 19;

	/**
	 * The feature id for the '<em><b>Double Object Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__DOUBLE_OBJECT_FEATURE = 20;

	/**
	 * The feature id for the '<em><b>Int Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__INT_FEATURE = 21;

	/**
	 * The feature id for the '<em><b>Int Object Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__INT_OBJECT_FEATURE = 22;

	/**
	 * The feature id for the '<em><b>Float Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__FLOAT_FEATURE = 23;

	/**
	 * The feature id for the '<em><b>Float Object Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__FLOAT_OBJECT_FEATURE = 24;

	/**
	 * The feature id for the '<em><b>Short Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__SHORT_FEATURE = 25;

	/**
	 * The feature id for the '<em><b>Short Object Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__SHORT_OBJECT_FEATURE = 26;

	/**
	 * The feature id for the '<em><b>Byte Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__BYTE_FEATURE = 27;

	/**
	 * The feature id for the '<em><b>Byte Object Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__BYTE_OBJECT_FEATURE = 28;

	/**
	 * The feature id for the '<em><b>Date Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__DATE_FEATURE = 29;

	/**
	 * The number of structural features of the '<em>Class For Controls</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS_FEATURE_COUNT = 30;

	/**
	 * The number of operations of the '<em>Class For Controls</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS_OPERATION_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_WITH_NAME__NAME = 0;

	/**
	 * The number of structural features of the '<em>Class With Name</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_WITH_NAME_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Class With Name</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_WITH_NAME_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.BaseClassForFeatureMapEntryImpl <em>Base Class For Feature Map Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.BaseClassForFeatureMapEntryImpl
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getBaseClassForFeatureMapEntry()
	 * @generated
	 */
	int BASE_CLASS_FOR_FEATURE_MAP_ENTRY = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_CLASS_FOR_FEATURE_MAP_ENTRY__NAME = 0;

	/**
	 * The number of structural features of the '<em>Base Class For Feature Map Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_CLASS_FOR_FEATURE_MAP_ENTRY_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Base Class For Feature Map Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_CLASS_FOR_FEATURE_MAP_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForFeatureMapEntry1Impl <em>Class For Feature Map Entry1</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForFeatureMapEntry1Impl
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getClassForFeatureMapEntry1()
	 * @generated
	 */
	int CLASS_FOR_FEATURE_MAP_ENTRY1 = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_FEATURE_MAP_ENTRY1__NAME = BASE_CLASS_FOR_FEATURE_MAP_ENTRY__NAME;

	/**
	 * The number of structural features of the '<em>Class For Feature Map Entry1</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_FEATURE_MAP_ENTRY1_FEATURE_COUNT = BASE_CLASS_FOR_FEATURE_MAP_ENTRY_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Class For Feature Map Entry1</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_FEATURE_MAP_ENTRY1_OPERATION_COUNT = BASE_CLASS_FOR_FEATURE_MAP_ENTRY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForFeatureMapEntry2Impl <em>Class For Feature Map Entry2</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForFeatureMapEntry2Impl
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getClassForFeatureMapEntry2()
	 * @generated
	 */
	int CLASS_FOR_FEATURE_MAP_ENTRY2 = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_FEATURE_MAP_ENTRY2__NAME = BASE_CLASS_FOR_FEATURE_MAP_ENTRY__NAME;

	/**
	 * The number of structural features of the '<em>Class For Feature Map Entry2</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_FEATURE_MAP_ENTRY2_FEATURE_COUNT = BASE_CLASS_FOR_FEATURE_MAP_ENTRY_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Class For Feature Map Entry2</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_FEATURE_MAP_ENTRY2_OPERATION_COUNT = BASE_CLASS_FOR_FEATURE_MAP_ENTRY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForTableImpl <em>Class For Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForTableImpl
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getClassForTable()
	 * @generated
	 */
	int CLASS_FOR_TABLE = 13;

	/**
	 * The feature id for the '<em><b>Class With Name1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_TABLE__CLASS_WITH_NAME1 = 0;

	/**
	 * The feature id for the '<em><b>Class With Name2</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_TABLE__CLASS_WITH_NAME2 = 1;

	/**
	 * The number of structural features of the '<em>Class For Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_TABLE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Class For Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_TABLE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForValidationImpl <em>Class For Validation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForValidationImpl
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getClassForValidation()
	 * @generated
	 */
	int CLASS_FOR_VALIDATION = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_VALIDATION__NAME = 0;

	/**
	 * The feature id for the '<em><b>Not Empty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_VALIDATION__NOT_EMPTY = 1;

	/**
	 * The number of structural features of the '<em>Class For Validation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_VALIDATION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Class For Validation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_VALIDATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForDefaultValidationImpl <em>Class For Default Validation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForDefaultValidationImpl
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getClassForDefaultValidation()
	 * @generated
	 */
	int CLASS_FOR_DEFAULT_VALIDATION = 15;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_DEFAULT_VALIDATION__NAME = 0;

	/**
	 * The feature id for the '<em><b>Not Empty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_DEFAULT_VALIDATION__NOT_EMPTY = 1;

	/**
	 * The feature id for the '<em><b>Can Be Empty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_DEFAULT_VALIDATION__CAN_BE_EMPTY = 2;

	/**
	 * The feature id for the '<em><b>Not Null Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_DEFAULT_VALIDATION__NOT_NULL_REFERENCE = 3;

	/**
	 * The feature id for the '<em><b>Integer Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_DEFAULT_VALIDATION__INTEGER_ATTRIBUTE = 4;

	/**
	 * The number of structural features of the '<em>Class For Default Validation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_DEFAULT_VALIDATION_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Class For Default Validation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_DEFAULT_VALIDATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForCompareImpl <em>Class For Compare</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForCompareImpl
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getClassForCompare()
	 * @generated
	 */
	int CLASS_FOR_COMPARE = 16;

	/**
	 * The feature id for the '<em><b>String Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_COMPARE__STRING_ATTRIBUTE = 0;

	/**
	 * The feature id for the '<em><b>Int Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_COMPARE__INT_ATTRIBUTE = 1;

	/**
	 * The feature id for the '<em><b>Big Decimal Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_COMPARE__BIG_DECIMAL_ATTRIBUTE = 2;

	/**
	 * The feature id for the '<em><b>Date Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_COMPARE__DATE_ATTRIBUTE = 3;

	/**
	 * The number of structural features of the '<em>Class For Compare</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_COMPARE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Class For Compare</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_COMPARE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.EnumForControls <em>Enum For Controls</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.EnumForControls
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getEnumForControls()
	 * @generated
	 */
	int ENUM_FOR_CONTROLS = 17;


	/**
	 * The meta object id for the '<em>Boolean Data Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.Boolean
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getBooleanDataType()
	 * @generated
	 */
	int BOOLEAN_DATA_TYPE = 18;


	/**
	 * The meta object id for the '<em>Boolean Primitive Data Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getBooleanPrimitiveDataType()
	 * @generated
	 */
	int BOOLEAN_PRIMITIVE_DATA_TYPE = 19;


	/**
	 * The meta object id for the '<em>String Data Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getStringDataType()
	 * @generated
	 */
	int STRING_DATA_TYPE = 20;


	/**
	 * The meta object id for the '<em>Unserializable String Data Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getUnserializableStringDataType()
	 * @generated
	 */
	int UNSERIALIZABLE_STRING_DATA_TYPE = 21;


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
	 * Returns the meta object for class '{@link org.eclipse.emf.parsley.tests.models.testmodels.TestEClassForFeatureName <em>Test EClass For Feature Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test EClass For Feature Name</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestEClassForFeatureName
	 * @generated
	 */
	EClass getTestEClassForFeatureName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.TestEClassForFeatureName#getEField <em>EField</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>EField</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestEClassForFeatureName#getEField()
	 * @see #getTestEClassForFeatureName()
	 * @generated
	 */
	EAttribute getTestEClassForFeatureName_EField();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.parsley.tests.models.testmodels.ABaseClass <em>ABase Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ABase Class</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ABaseClass
	 * @generated
	 */
	EClass getABaseClass();

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
	 * Returns the meta object for the reference list '{@link org.eclipse.emf.parsley.tests.models.testmodels.BaseClass#getBaseMultiReferenceFeature <em>Base Multi Reference Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Base Multi Reference Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.BaseClass#getBaseMultiReferenceFeature()
	 * @see #getBaseClass()
	 * @generated
	 */
	EReference getBaseClass_BaseMultiReferenceFeature();

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
	 * Returns the meta object for the reference list '{@link org.eclipse.emf.parsley.tests.models.testmodels.DerivedClass#getDerivedMultiReferenceFeature <em>Derived Multi Reference Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Derived Multi Reference Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.DerivedClass#getDerivedMultiReferenceFeature()
	 * @see #getDerivedClass()
	 * @generated
	 */
	EReference getDerivedClass_DerivedMultiReferenceFeature();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.parsley.tests.models.testmodels.DerivedDerivedClass <em>Derived Derived Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Derived Derived Class</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.DerivedDerivedClass
	 * @generated
	 */
	EClass getDerivedDerivedClass();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.parsley.tests.models.testmodels.MultipleInheritanceClass <em>Multiple Inheritance Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Multiple Inheritance Class</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.MultipleInheritanceClass
	 * @generated
	 */
	EClass getMultipleInheritanceClass();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls <em>Class For Controls</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class For Controls</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls
	 * @generated
	 */
	EClass getClassForControls();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#isBooleanFeature <em>Boolean Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Boolean Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#isBooleanFeature()
	 * @see #getClassForControls()
	 * @generated
	 */
	EAttribute getClassForControls_BooleanFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getBooleanObjectFeature <em>Boolean Object Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Boolean Object Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getBooleanObjectFeature()
	 * @see #getClassForControls()
	 * @generated
	 */
	EAttribute getClassForControls_BooleanObjectFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getBooleanDataTypeFeature <em>Boolean Data Type Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Boolean Data Type Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getBooleanDataTypeFeature()
	 * @see #getClassForControls()
	 * @generated
	 */
	EAttribute getClassForControls_BooleanDataTypeFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#isBooleanPrimitiveDataTypeFeature <em>Boolean Primitive Data Type Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Boolean Primitive Data Type Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#isBooleanPrimitiveDataTypeFeature()
	 * @see #getClassForControls()
	 * @generated
	 */
	EAttribute getClassForControls_BooleanPrimitiveDataTypeFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getEnumFeature <em>Enum Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enum Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getEnumFeature()
	 * @see #getClassForControls()
	 * @generated
	 */
	EAttribute getClassForControls_EnumFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getStringFeature <em>String Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>String Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getStringFeature()
	 * @see #getClassForControls()
	 * @generated
	 */
	EAttribute getClassForControls_StringFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getStringDataTypeFeature <em>String Data Type Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>String Data Type Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getStringDataTypeFeature()
	 * @see #getClassForControls()
	 * @generated
	 */
	EAttribute getClassForControls_StringDataTypeFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getStringFeatureWithDefault <em>String Feature With Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>String Feature With Default</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getStringFeatureWithDefault()
	 * @see #getClassForControls()
	 * @generated
	 */
	EAttribute getClassForControls_StringFeatureWithDefault();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getUnserializableStringDataTypeFeature <em>Unserializable String Data Type Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unserializable String Data Type Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getUnserializableStringDataTypeFeature()
	 * @see #getClassForControls()
	 * @generated
	 */
	EAttribute getClassForControls_UnserializableStringDataTypeFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getUnchangeableStringFeature <em>Unchangeable String Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unchangeable String Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getUnchangeableStringFeature()
	 * @see #getClassForControls()
	 * @generated
	 */
	EAttribute getClassForControls_UnchangeableStringFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getDerivedStringFeature <em>Derived String Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Derived String Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getDerivedStringFeature()
	 * @see #getClassForControls()
	 * @generated
	 */
	EAttribute getClassForControls_DerivedStringFeature();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getReferenceToClassWithName <em>Reference To Class With Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference To Class With Name</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getReferenceToClassWithName()
	 * @see #getClassForControls()
	 * @generated
	 */
	EReference getClassForControls_ReferenceToClassWithName();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getMultiReferenceFeature <em>Multi Reference Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Multi Reference Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getMultiReferenceFeature()
	 * @see #getClassForControls()
	 * @generated
	 */
	EReference getClassForControls_MultiReferenceFeature();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getFeatureMapEntries <em>Feature Map Entries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Feature Map Entries</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getFeatureMapEntries()
	 * @see #getClassForControls()
	 * @generated
	 */
	EAttribute getClassForControls_FeatureMapEntries();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getFeatureMapEntries1 <em>Feature Map Entries1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Feature Map Entries1</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getFeatureMapEntries1()
	 * @see #getClassForControls()
	 * @generated
	 */
	EReference getClassForControls_FeatureMapEntries1();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getFeatureMapEntries2 <em>Feature Map Entries2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Feature Map Entries2</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getFeatureMapEntries2()
	 * @see #getClassForControls()
	 * @generated
	 */
	EReference getClassForControls_FeatureMapEntries2();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getSingleContainmentReference <em>Single Containment Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Single Containment Reference</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getSingleContainmentReference()
	 * @see #getClassForControls()
	 * @generated
	 */
	EReference getClassForControls_SingleContainmentReference();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getBigDecimalFeature <em>Big Decimal Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Big Decimal Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getBigDecimalFeature()
	 * @see #getClassForControls()
	 * @generated
	 */
	EAttribute getClassForControls_BigDecimalFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getBigIntegerFeature <em>Big Integer Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Big Integer Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getBigIntegerFeature()
	 * @see #getClassForControls()
	 * @generated
	 */
	EAttribute getClassForControls_BigIntegerFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getDoubleFeature <em>Double Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Double Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getDoubleFeature()
	 * @see #getClassForControls()
	 * @generated
	 */
	EAttribute getClassForControls_DoubleFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getDoubleObjectFeature <em>Double Object Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Double Object Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getDoubleObjectFeature()
	 * @see #getClassForControls()
	 * @generated
	 */
	EAttribute getClassForControls_DoubleObjectFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getIntFeature <em>Int Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Int Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getIntFeature()
	 * @see #getClassForControls()
	 * @generated
	 */
	EAttribute getClassForControls_IntFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getIntObjectFeature <em>Int Object Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Int Object Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getIntObjectFeature()
	 * @see #getClassForControls()
	 * @generated
	 */
	EAttribute getClassForControls_IntObjectFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getFloatFeature <em>Float Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Float Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getFloatFeature()
	 * @see #getClassForControls()
	 * @generated
	 */
	EAttribute getClassForControls_FloatFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getFloatObjectFeature <em>Float Object Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Float Object Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getFloatObjectFeature()
	 * @see #getClassForControls()
	 * @generated
	 */
	EAttribute getClassForControls_FloatObjectFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getShortFeature <em>Short Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Short Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getShortFeature()
	 * @see #getClassForControls()
	 * @generated
	 */
	EAttribute getClassForControls_ShortFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getShortObjectFeature <em>Short Object Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Short Object Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getShortObjectFeature()
	 * @see #getClassForControls()
	 * @generated
	 */
	EAttribute getClassForControls_ShortObjectFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getByteFeature <em>Byte Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Byte Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getByteFeature()
	 * @see #getClassForControls()
	 * @generated
	 */
	EAttribute getClassForControls_ByteFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getByteObjectFeature <em>Byte Object Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Byte Object Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getByteObjectFeature()
	 * @see #getClassForControls()
	 * @generated
	 */
	EAttribute getClassForControls_ByteObjectFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getDateFeature <em>Date Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date Feature</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls#getDateFeature()
	 * @see #getClassForControls()
	 * @generated
	 */
	EAttribute getClassForControls_DateFeature();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName <em>Class With Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class With Name</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName
	 * @generated
	 */
	EClass getClassWithName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName#getName()
	 * @see #getClassWithName()
	 * @generated
	 */
	EAttribute getClassWithName_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.parsley.tests.models.testmodels.BaseClassForFeatureMapEntry <em>Base Class For Feature Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Base Class For Feature Map Entry</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.BaseClassForFeatureMapEntry
	 * @generated
	 */
	EClass getBaseClassForFeatureMapEntry();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.BaseClassForFeatureMapEntry#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.BaseClassForFeatureMapEntry#getName()
	 * @see #getBaseClassForFeatureMapEntry()
	 * @generated
	 */
	EAttribute getBaseClassForFeatureMapEntry_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForFeatureMapEntry1 <em>Class For Feature Map Entry1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class For Feature Map Entry1</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForFeatureMapEntry1
	 * @generated
	 */
	EClass getClassForFeatureMapEntry1();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForFeatureMapEntry2 <em>Class For Feature Map Entry2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class For Feature Map Entry2</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForFeatureMapEntry2
	 * @generated
	 */
	EClass getClassForFeatureMapEntry2();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForTable <em>Class For Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class For Table</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForTable
	 * @generated
	 */
	EClass getClassForTable();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForTable#getClassWithName1 <em>Class With Name1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Class With Name1</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForTable#getClassWithName1()
	 * @see #getClassForTable()
	 * @generated
	 */
	EReference getClassForTable_ClassWithName1();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForTable#getClassWithName2 <em>Class With Name2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Class With Name2</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForTable#getClassWithName2()
	 * @see #getClassForTable()
	 * @generated
	 */
	EReference getClassForTable_ClassWithName2();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForValidation <em>Class For Validation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class For Validation</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForValidation
	 * @generated
	 */
	EClass getClassForValidation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForValidation#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForValidation#getName()
	 * @see #getClassForValidation()
	 * @generated
	 */
	EAttribute getClassForValidation_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForValidation#getNotEmpty <em>Not Empty</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Not Empty</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForValidation#getNotEmpty()
	 * @see #getClassForValidation()
	 * @generated
	 */
	EAttribute getClassForValidation_NotEmpty();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForDefaultValidation <em>Class For Default Validation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class For Default Validation</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForDefaultValidation
	 * @generated
	 */
	EClass getClassForDefaultValidation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForDefaultValidation#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForDefaultValidation#getName()
	 * @see #getClassForDefaultValidation()
	 * @generated
	 */
	EAttribute getClassForDefaultValidation_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForDefaultValidation#getNotEmpty <em>Not Empty</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Not Empty</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForDefaultValidation#getNotEmpty()
	 * @see #getClassForDefaultValidation()
	 * @generated
	 */
	EAttribute getClassForDefaultValidation_NotEmpty();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForDefaultValidation#getCanBeEmpty <em>Can Be Empty</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Can Be Empty</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForDefaultValidation#getCanBeEmpty()
	 * @see #getClassForDefaultValidation()
	 * @generated
	 */
	EAttribute getClassForDefaultValidation_CanBeEmpty();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForDefaultValidation#getNotNullReference <em>Not Null Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Not Null Reference</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForDefaultValidation#getNotNullReference()
	 * @see #getClassForDefaultValidation()
	 * @generated
	 */
	EReference getClassForDefaultValidation_NotNullReference();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForDefaultValidation#getIntegerAttribute <em>Integer Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Integer Attribute</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForDefaultValidation#getIntegerAttribute()
	 * @see #getClassForDefaultValidation()
	 * @generated
	 */
	EAttribute getClassForDefaultValidation_IntegerAttribute();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForCompare <em>Class For Compare</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class For Compare</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForCompare
	 * @generated
	 */
	EClass getClassForCompare();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForCompare#getStringAttribute <em>String Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>String Attribute</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForCompare#getStringAttribute()
	 * @see #getClassForCompare()
	 * @generated
	 */
	EAttribute getClassForCompare_StringAttribute();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForCompare#getIntAttribute <em>Int Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Int Attribute</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForCompare#getIntAttribute()
	 * @see #getClassForCompare()
	 * @generated
	 */
	EAttribute getClassForCompare_IntAttribute();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForCompare#getBigDecimalAttribute <em>Big Decimal Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Big Decimal Attribute</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForCompare#getBigDecimalAttribute()
	 * @see #getClassForCompare()
	 * @generated
	 */
	EAttribute getClassForCompare_BigDecimalAttribute();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForCompare#getDateAttribute <em>Date Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date Attribute</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForCompare#getDateAttribute()
	 * @see #getClassForCompare()
	 * @generated
	 */
	EAttribute getClassForCompare_DateAttribute();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.parsley.tests.models.testmodels.TestContainer <em>Test Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Container</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestContainer
	 * @generated
	 */
	EClass getTestContainer();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getName()
	 * @see #getTestContainer()
	 * @generated
	 */
	EAttribute getTestContainer_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getClassesWithName <em>Classes With Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Classes With Name</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getClassesWithName()
	 * @see #getTestContainer()
	 * @generated
	 */
	EReference getTestContainer_ClassesWithName();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getClassesForControls <em>Classes For Controls</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Classes For Controls</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getClassesForControls()
	 * @see #getTestContainer()
	 * @generated
	 */
	EReference getTestContainer_ClassesForControls();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getContained <em>Contained</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Contained</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getContained()
	 * @see #getTestContainer()
	 * @generated
	 */
	EReference getTestContainer_Contained();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getClassesForTable <em>Classes For Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Classes For Table</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getClassesForTable()
	 * @see #getTestContainer()
	 * @generated
	 */
	EReference getTestContainer_ClassesForTable();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getObjectsForValidation <em>Objects For Validation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Objects For Validation</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getObjectsForValidation()
	 * @see #getTestContainer()
	 * @generated
	 */
	EReference getTestContainer_ObjectsForValidation();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getMultipleContained <em>Multiple Contained</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Multiple Contained</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getMultipleContained()
	 * @see #getTestContainer()
	 * @generated
	 */
	EReference getTestContainer_MultipleContained();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getObjectsForDefaultValidation <em>Objects For Default Validation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Objects For Default Validation</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestContainer#getObjectsForDefaultValidation()
	 * @see #getTestContainer()
	 * @generated
	 */
	EReference getTestContainer_ObjectsForDefaultValidation();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.emf.parsley.tests.models.testmodels.EnumForControls <em>Enum For Controls</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Enum For Controls</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.EnumForControls
	 * @generated
	 */
	EEnum getEnumForControls();

	/**
	 * Returns the meta object for data type '{@link java.lang.Boolean <em>Boolean Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Boolean Data Type</em>'.
	 * @see java.lang.Boolean
	 * @model instanceClass="java.lang.Boolean"
	 * @generated
	 */
	EDataType getBooleanDataType();

	/**
	 * Returns the meta object for data type '<em>Boolean Primitive Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Boolean Primitive Data Type</em>'.
	 * @model instanceClass="boolean"
	 * @generated
	 */
	EDataType getBooleanPrimitiveDataType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>String Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>String Data Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 * @generated
	 */
	EDataType getStringDataType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Unserializable String Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Unserializable String Data Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String" serializeable="false"
	 * @generated
	 */
	EDataType getUnserializableStringDataType();

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
		 * The meta object literal for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.TestEClassForFeatureNameImpl <em>Test EClass For Feature Name</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestEClassForFeatureNameImpl
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getTestEClassForFeatureName()
		 * @generated
		 */
		EClass TEST_ECLASS_FOR_FEATURE_NAME = eINSTANCE.getTestEClassForFeatureName();
		/**
		 * The meta object literal for the '<em><b>EField</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_ECLASS_FOR_FEATURE_NAME__EFIELD = eINSTANCE.getTestEClassForFeatureName_EField();
		/**
		 * The meta object literal for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ABaseClassImpl <em>ABase Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.ABaseClassImpl
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getABaseClass()
		 * @generated
		 */
		EClass ABASE_CLASS = eINSTANCE.getABaseClass();
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
		 * The meta object literal for the '<em><b>Base Multi Reference Feature</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BASE_CLASS__BASE_MULTI_REFERENCE_FEATURE = eINSTANCE.getBaseClass_BaseMultiReferenceFeature();
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
		/**
		 * The meta object literal for the '<em><b>Derived Multi Reference Feature</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DERIVED_CLASS__DERIVED_MULTI_REFERENCE_FEATURE = eINSTANCE.getDerivedClass_DerivedMultiReferenceFeature();
		/**
		 * The meta object literal for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.DerivedDerivedClassImpl <em>Derived Derived Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.DerivedDerivedClassImpl
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getDerivedDerivedClass()
		 * @generated
		 */
		EClass DERIVED_DERIVED_CLASS = eINSTANCE.getDerivedDerivedClass();
		/**
		 * The meta object literal for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.MultipleInheritanceClassImpl <em>Multiple Inheritance Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.MultipleInheritanceClassImpl
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getMultipleInheritanceClass()
		 * @generated
		 */
		EClass MULTIPLE_INHERITANCE_CLASS = eINSTANCE.getMultipleInheritanceClass();
		/**
		 * The meta object literal for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl <em>Class For Controls</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getClassForControls()
		 * @generated
		 */
		EClass CLASS_FOR_CONTROLS = eINSTANCE.getClassForControls();
		/**
		 * The meta object literal for the '<em><b>Boolean Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__BOOLEAN_FEATURE = eINSTANCE.getClassForControls_BooleanFeature();
		/**
		 * The meta object literal for the '<em><b>Boolean Object Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__BOOLEAN_OBJECT_FEATURE = eINSTANCE.getClassForControls_BooleanObjectFeature();
		/**
		 * The meta object literal for the '<em><b>Boolean Data Type Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__BOOLEAN_DATA_TYPE_FEATURE = eINSTANCE.getClassForControls_BooleanDataTypeFeature();
		/**
		 * The meta object literal for the '<em><b>Boolean Primitive Data Type Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__BOOLEAN_PRIMITIVE_DATA_TYPE_FEATURE = eINSTANCE.getClassForControls_BooleanPrimitiveDataTypeFeature();
		/**
		 * The meta object literal for the '<em><b>Enum Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__ENUM_FEATURE = eINSTANCE.getClassForControls_EnumFeature();
		/**
		 * The meta object literal for the '<em><b>String Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__STRING_FEATURE = eINSTANCE.getClassForControls_StringFeature();
		/**
		 * The meta object literal for the '<em><b>String Data Type Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__STRING_DATA_TYPE_FEATURE = eINSTANCE.getClassForControls_StringDataTypeFeature();
		/**
		 * The meta object literal for the '<em><b>String Feature With Default</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__STRING_FEATURE_WITH_DEFAULT = eINSTANCE.getClassForControls_StringFeatureWithDefault();
		/**
		 * The meta object literal for the '<em><b>Unserializable String Data Type Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__UNSERIALIZABLE_STRING_DATA_TYPE_FEATURE = eINSTANCE.getClassForControls_UnserializableStringDataTypeFeature();
		/**
		 * The meta object literal for the '<em><b>Unchangeable String Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__UNCHANGEABLE_STRING_FEATURE = eINSTANCE.getClassForControls_UnchangeableStringFeature();
		/**
		 * The meta object literal for the '<em><b>Derived String Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__DERIVED_STRING_FEATURE = eINSTANCE.getClassForControls_DerivedStringFeature();
		/**
		 * The meta object literal for the '<em><b>Reference To Class With Name</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_FOR_CONTROLS__REFERENCE_TO_CLASS_WITH_NAME = eINSTANCE.getClassForControls_ReferenceToClassWithName();
		/**
		 * The meta object literal for the '<em><b>Multi Reference Feature</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_FOR_CONTROLS__MULTI_REFERENCE_FEATURE = eINSTANCE.getClassForControls_MultiReferenceFeature();
		/**
		 * The meta object literal for the '<em><b>Feature Map Entries</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES = eINSTANCE.getClassForControls_FeatureMapEntries();
		/**
		 * The meta object literal for the '<em><b>Feature Map Entries1</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES1 = eINSTANCE.getClassForControls_FeatureMapEntries1();
		/**
		 * The meta object literal for the '<em><b>Feature Map Entries2</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES2 = eINSTANCE.getClassForControls_FeatureMapEntries2();
		/**
		 * The meta object literal for the '<em><b>Single Containment Reference</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_FOR_CONTROLS__SINGLE_CONTAINMENT_REFERENCE = eINSTANCE.getClassForControls_SingleContainmentReference();
		/**
		 * The meta object literal for the '<em><b>Big Decimal Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__BIG_DECIMAL_FEATURE = eINSTANCE.getClassForControls_BigDecimalFeature();
		/**
		 * The meta object literal for the '<em><b>Big Integer Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__BIG_INTEGER_FEATURE = eINSTANCE.getClassForControls_BigIntegerFeature();
		/**
		 * The meta object literal for the '<em><b>Double Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__DOUBLE_FEATURE = eINSTANCE.getClassForControls_DoubleFeature();
		/**
		 * The meta object literal for the '<em><b>Double Object Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__DOUBLE_OBJECT_FEATURE = eINSTANCE.getClassForControls_DoubleObjectFeature();
		/**
		 * The meta object literal for the '<em><b>Int Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__INT_FEATURE = eINSTANCE.getClassForControls_IntFeature();
		/**
		 * The meta object literal for the '<em><b>Int Object Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__INT_OBJECT_FEATURE = eINSTANCE.getClassForControls_IntObjectFeature();
		/**
		 * The meta object literal for the '<em><b>Float Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__FLOAT_FEATURE = eINSTANCE.getClassForControls_FloatFeature();
		/**
		 * The meta object literal for the '<em><b>Float Object Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__FLOAT_OBJECT_FEATURE = eINSTANCE.getClassForControls_FloatObjectFeature();
		/**
		 * The meta object literal for the '<em><b>Short Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__SHORT_FEATURE = eINSTANCE.getClassForControls_ShortFeature();
		/**
		 * The meta object literal for the '<em><b>Short Object Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__SHORT_OBJECT_FEATURE = eINSTANCE.getClassForControls_ShortObjectFeature();
		/**
		 * The meta object literal for the '<em><b>Byte Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__BYTE_FEATURE = eINSTANCE.getClassForControls_ByteFeature();
		/**
		 * The meta object literal for the '<em><b>Byte Object Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__BYTE_OBJECT_FEATURE = eINSTANCE.getClassForControls_ByteObjectFeature();
		/**
		 * The meta object literal for the '<em><b>Date Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__DATE_FEATURE = eINSTANCE.getClassForControls_DateFeature();
		/**
		 * The meta object literal for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassWithNameImpl <em>Class With Name</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassWithNameImpl
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getClassWithName()
		 * @generated
		 */
		EClass CLASS_WITH_NAME = eINSTANCE.getClassWithName();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_WITH_NAME__NAME = eINSTANCE.getClassWithName_Name();
		/**
		 * The meta object literal for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.BaseClassForFeatureMapEntryImpl <em>Base Class For Feature Map Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.BaseClassForFeatureMapEntryImpl
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getBaseClassForFeatureMapEntry()
		 * @generated
		 */
		EClass BASE_CLASS_FOR_FEATURE_MAP_ENTRY = eINSTANCE.getBaseClassForFeatureMapEntry();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASE_CLASS_FOR_FEATURE_MAP_ENTRY__NAME = eINSTANCE.getBaseClassForFeatureMapEntry_Name();
		/**
		 * The meta object literal for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForFeatureMapEntry1Impl <em>Class For Feature Map Entry1</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForFeatureMapEntry1Impl
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getClassForFeatureMapEntry1()
		 * @generated
		 */
		EClass CLASS_FOR_FEATURE_MAP_ENTRY1 = eINSTANCE.getClassForFeatureMapEntry1();
		/**
		 * The meta object literal for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForFeatureMapEntry2Impl <em>Class For Feature Map Entry2</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForFeatureMapEntry2Impl
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getClassForFeatureMapEntry2()
		 * @generated
		 */
		EClass CLASS_FOR_FEATURE_MAP_ENTRY2 = eINSTANCE.getClassForFeatureMapEntry2();
		/**
		 * The meta object literal for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForTableImpl <em>Class For Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForTableImpl
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getClassForTable()
		 * @generated
		 */
		EClass CLASS_FOR_TABLE = eINSTANCE.getClassForTable();
		/**
		 * The meta object literal for the '<em><b>Class With Name1</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_FOR_TABLE__CLASS_WITH_NAME1 = eINSTANCE.getClassForTable_ClassWithName1();
		/**
		 * The meta object literal for the '<em><b>Class With Name2</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_FOR_TABLE__CLASS_WITH_NAME2 = eINSTANCE.getClassForTable_ClassWithName2();
		/**
		 * The meta object literal for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForValidationImpl <em>Class For Validation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForValidationImpl
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getClassForValidation()
		 * @generated
		 */
		EClass CLASS_FOR_VALIDATION = eINSTANCE.getClassForValidation();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_VALIDATION__NAME = eINSTANCE.getClassForValidation_Name();
		/**
		 * The meta object literal for the '<em><b>Not Empty</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_VALIDATION__NOT_EMPTY = eINSTANCE.getClassForValidation_NotEmpty();
		/**
		 * The meta object literal for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForDefaultValidationImpl <em>Class For Default Validation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForDefaultValidationImpl
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getClassForDefaultValidation()
		 * @generated
		 */
		EClass CLASS_FOR_DEFAULT_VALIDATION = eINSTANCE.getClassForDefaultValidation();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_DEFAULT_VALIDATION__NAME = eINSTANCE.getClassForDefaultValidation_Name();
		/**
		 * The meta object literal for the '<em><b>Not Empty</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_DEFAULT_VALIDATION__NOT_EMPTY = eINSTANCE.getClassForDefaultValidation_NotEmpty();
		/**
		 * The meta object literal for the '<em><b>Can Be Empty</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_DEFAULT_VALIDATION__CAN_BE_EMPTY = eINSTANCE.getClassForDefaultValidation_CanBeEmpty();
		/**
		 * The meta object literal for the '<em><b>Not Null Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_FOR_DEFAULT_VALIDATION__NOT_NULL_REFERENCE = eINSTANCE.getClassForDefaultValidation_NotNullReference();
		/**
		 * The meta object literal for the '<em><b>Integer Attribute</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_DEFAULT_VALIDATION__INTEGER_ATTRIBUTE = eINSTANCE.getClassForDefaultValidation_IntegerAttribute();
		/**
		 * The meta object literal for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForCompareImpl <em>Class For Compare</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForCompareImpl
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getClassForCompare()
		 * @generated
		 */
		EClass CLASS_FOR_COMPARE = eINSTANCE.getClassForCompare();
		/**
		 * The meta object literal for the '<em><b>String Attribute</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_COMPARE__STRING_ATTRIBUTE = eINSTANCE.getClassForCompare_StringAttribute();
		/**
		 * The meta object literal for the '<em><b>Int Attribute</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_COMPARE__INT_ATTRIBUTE = eINSTANCE.getClassForCompare_IntAttribute();
		/**
		 * The meta object literal for the '<em><b>Big Decimal Attribute</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_COMPARE__BIG_DECIMAL_ATTRIBUTE = eINSTANCE.getClassForCompare_BigDecimalAttribute();
		/**
		 * The meta object literal for the '<em><b>Date Attribute</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_COMPARE__DATE_ATTRIBUTE = eINSTANCE.getClassForCompare_DateAttribute();
		/**
		 * The meta object literal for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.TestContainerImpl <em>Test Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestContainerImpl
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getTestContainer()
		 * @generated
		 */
		EClass TEST_CONTAINER = eINSTANCE.getTestContainer();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CONTAINER__NAME = eINSTANCE.getTestContainer_Name();
		/**
		 * The meta object literal for the '<em><b>Classes With Name</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CONTAINER__CLASSES_WITH_NAME = eINSTANCE.getTestContainer_ClassesWithName();
		/**
		 * The meta object literal for the '<em><b>Classes For Controls</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CONTAINER__CLASSES_FOR_CONTROLS = eINSTANCE.getTestContainer_ClassesForControls();
		/**
		 * The meta object literal for the '<em><b>Contained</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CONTAINER__CONTAINED = eINSTANCE.getTestContainer_Contained();
		/**
		 * The meta object literal for the '<em><b>Classes For Table</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CONTAINER__CLASSES_FOR_TABLE = eINSTANCE.getTestContainer_ClassesForTable();
		/**
		 * The meta object literal for the '<em><b>Objects For Validation</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CONTAINER__OBJECTS_FOR_VALIDATION = eINSTANCE.getTestContainer_ObjectsForValidation();
		/**
		 * The meta object literal for the '<em><b>Multiple Contained</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CONTAINER__MULTIPLE_CONTAINED = eINSTANCE.getTestContainer_MultipleContained();
		/**
		 * The meta object literal for the '<em><b>Objects For Default Validation</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CONTAINER__OBJECTS_FOR_DEFAULT_VALIDATION = eINSTANCE.getTestContainer_ObjectsForDefaultValidation();
		/**
		 * The meta object literal for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.EnumForControls <em>Enum For Controls</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.EnumForControls
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getEnumForControls()
		 * @generated
		 */
		EEnum ENUM_FOR_CONTROLS = eINSTANCE.getEnumForControls();
		/**
		 * The meta object literal for the '<em>Boolean Data Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Boolean
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getBooleanDataType()
		 * @generated
		 */
		EDataType BOOLEAN_DATA_TYPE = eINSTANCE.getBooleanDataType();
		/**
		 * The meta object literal for the '<em>Boolean Primitive Data Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getBooleanPrimitiveDataType()
		 * @generated
		 */
		EDataType BOOLEAN_PRIMITIVE_DATA_TYPE = eINSTANCE.getBooleanPrimitiveDataType();
		/**
		 * The meta object literal for the '<em>String Data Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getStringDataType()
		 * @generated
		 */
		EDataType STRING_DATA_TYPE = eINSTANCE.getStringDataType();
		/**
		 * The meta object literal for the '<em>Unserializable String Data Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getUnserializableStringDataType()
		 * @generated
		 */
		EDataType UNSERIALIZABLE_STRING_DATA_TYPE = eINSTANCE.getUnserializableStringDataType();

	}

} //TestmodelsPackage
