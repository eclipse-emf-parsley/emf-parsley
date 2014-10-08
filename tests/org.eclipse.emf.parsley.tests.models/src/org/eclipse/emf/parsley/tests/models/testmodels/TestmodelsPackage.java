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
	int BASE_CLASS = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.DerivedClassImpl <em>Derived Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.DerivedClassImpl
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getDerivedClass()
	 * @generated
	 */
	int DERIVED_CLASS = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl <em>Class For Controls</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getClassForControls()
	 * @generated
	 */
	int CLASS_FOR_CONTROLS = 7;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassWithNameImpl <em>Class With Name</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassWithNameImpl
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getClassWithName()
	 * @generated
	 */
	int CLASS_WITH_NAME = 8;

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
	 * The feature id for the '<em><b>Classes With Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONTAINER__CLASSES_WITH_NAME = 0;

	/**
	 * The feature id for the '<em><b>Classes For Controls</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONTAINER__CLASSES_FOR_CONTROLS = 1;

	/**
	 * The feature id for the '<em><b>Contained</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONTAINER__CONTAINED = 2;

	/**
	 * The feature id for the '<em><b>Classes For Table</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONTAINER__CLASSES_FOR_TABLE = 3;

	/**
	 * The number of structural features of the '<em>Test Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONTAINER_FEATURE_COUNT = 4;

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
	 * The meta object id for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ABaseClassImpl <em>ABase Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.ABaseClassImpl
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getABaseClass()
	 * @generated
	 */
	int ABASE_CLASS = 2;

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
	int DERIVED_DERIVED_CLASS = 5;

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
	int MULTIPLE_INHERITANCE_CLASS = 6;

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
	 * The feature id for the '<em><b>Unchangeable String Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__UNCHANGEABLE_STRING_FEATURE = 6;

	/**
	 * The feature id for the '<em><b>Reference To Class With Name</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__REFERENCE_TO_CLASS_WITH_NAME = 7;

	/**
	 * The feature id for the '<em><b>Multi Reference Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__MULTI_REFERENCE_FEATURE = 8;

	/**
	 * The feature id for the '<em><b>Feature Map Entries</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES = 9;

	/**
	 * The feature id for the '<em><b>Feature Map Entries1</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES1 = 10;

	/**
	 * The feature id for the '<em><b>Feature Map Entries2</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES2 = 11;

	/**
	 * The number of structural features of the '<em>Class For Controls</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FOR_CONTROLS_FEATURE_COUNT = 12;

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
	int BASE_CLASS_FOR_FEATURE_MAP_ENTRY = 9;

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
	int CLASS_FOR_FEATURE_MAP_ENTRY1 = 10;

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
	int CLASS_FOR_FEATURE_MAP_ENTRY2 = 11;

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
	int CLASS_FOR_TABLE = 12;

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
	 * The meta object id for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.EnumForControls <em>Enum For Controls</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.EnumForControls
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getEnumForControls()
	 * @generated
	 */
	int ENUM_FOR_CONTROLS = 13;


	/**
	 * The meta object id for the '<em>Boolean Data Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.Boolean
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getBooleanDataType()
	 * @generated
	 */
	int BOOLEAN_DATA_TYPE = 14;


	/**
	 * The meta object id for the '<em>Boolean Primitive Data Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getBooleanPrimitiveDataType()
	 * @generated
	 */
	int BOOLEAN_PRIMITIVE_DATA_TYPE = 15;


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
	 * Returns the meta object for class '{@link org.eclipse.emf.parsley.tests.models.testmodels.TestContainer <em>Test Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Container</em>'.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestContainer
	 * @generated
	 */
	EClass getTestContainer();

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
		 * The meta object literal for the '<em><b>Unchangeable String Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_FOR_CONTROLS__UNCHANGEABLE_STRING_FEATURE = eINSTANCE.getClassForControls_UnchangeableStringFeature();
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
		 * The meta object literal for the '{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.TestContainerImpl <em>Test Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestContainerImpl
		 * @see org.eclipse.emf.parsley.tests.models.testmodels.impl.TestmodelsPackageImpl#getTestContainer()
		 * @generated
		 */
		EClass TEST_CONTAINER = eINSTANCE.getTestContainer();
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

	}

} //TestmodelsPackage
