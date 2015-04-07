/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
/**
 */
package org.eclipse.emf.parsley.tests.models.testmodels.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.parsley.tests.models.testmodels.ABaseClass;
import org.eclipse.emf.parsley.tests.models.testmodels.BaseClass;
import org.eclipse.emf.parsley.tests.models.testmodels.BaseClassForFeatureMapEntry;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForFeatureMapEntry1;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForFeatureMapEntry2;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForTable;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName;
import org.eclipse.emf.parsley.tests.models.testmodels.DerivedClass;
import org.eclipse.emf.parsley.tests.models.testmodels.DerivedDerivedClass;
import org.eclipse.emf.parsley.tests.models.testmodels.EnumForControls;
import org.eclipse.emf.parsley.tests.models.testmodels.MultipleInheritanceClass;
import org.eclipse.emf.parsley.tests.models.testmodels.TestContainer;
import org.eclipse.emf.parsley.tests.models.testmodels.TestEClass;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsFactory;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TestmodelsPackageImpl extends EPackageImpl implements TestmodelsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testEClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aBaseClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass baseClassEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass derivedClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass derivedDerivedClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass multipleInheritanceClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classForControlsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classWithNameEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass baseClassForFeatureMapEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classForFeatureMapEntry1EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classForFeatureMapEntry2EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classForTableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum enumForControlsEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType booleanDataTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType booleanPrimitiveDataTypeEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TestmodelsPackageImpl() {
		super(eNS_URI, TestmodelsFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link TestmodelsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TestmodelsPackage init() {
		if (isInited) return (TestmodelsPackage)EPackage.Registry.INSTANCE.getEPackage(TestmodelsPackage.eNS_URI);

		// Obtain or create and register package
		TestmodelsPackageImpl theTestmodelsPackage = (TestmodelsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TestmodelsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TestmodelsPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theTestmodelsPackage.createPackageContents();

		// Initialize created meta-data
		theTestmodelsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTestmodelsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TestmodelsPackage.eNS_URI, theTestmodelsPackage);
		return theTestmodelsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestEClass() {
		return testEClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestEClass_LowercaseNameFeature() {
		return (EAttribute)testEClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestEClass_UpperCaseNameFeature() {
		return (EAttribute)testEClassEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestEClass_NotChangeableFeature() {
		return (EAttribute)testEClassEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestEClass_DerivedFeature() {
		return (EAttribute)testEClassEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getABaseClass() {
		return aBaseClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBaseClass() {
		return baseClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseClass_BaseClassFeature() {
		return (EAttribute)baseClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBaseClass_BaseMultiReferenceFeature() {
		return (EReference)baseClassEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDerivedClass() {
		return derivedClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDerivedClass_DerivedClassFeature() {
		return (EAttribute)derivedClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDerivedClass_DerivedMultiReferenceFeature() {
		return (EReference)derivedClassEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDerivedDerivedClass() {
		return derivedDerivedClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMultipleInheritanceClass() {
		return multipleInheritanceClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassForControls() {
		return classForControlsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClassForControls_BooleanFeature() {
		return (EAttribute)classForControlsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClassForControls_BooleanObjectFeature() {
		return (EAttribute)classForControlsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClassForControls_BooleanDataTypeFeature() {
		return (EAttribute)classForControlsEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClassForControls_BooleanPrimitiveDataTypeFeature() {
		return (EAttribute)classForControlsEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClassForControls_EnumFeature() {
		return (EAttribute)classForControlsEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClassForControls_StringFeature() {
		return (EAttribute)classForControlsEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClassForControls_UnchangeableStringFeature() {
		return (EAttribute)classForControlsEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClassForControls_DerivedStringFeature() {
		return (EAttribute)classForControlsEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassForControls_ReferenceToClassWithName() {
		return (EReference)classForControlsEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassForControls_MultiReferenceFeature() {
		return (EReference)classForControlsEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClassForControls_FeatureMapEntries() {
		return (EAttribute)classForControlsEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassForControls_FeatureMapEntries1() {
		return (EReference)classForControlsEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassForControls_FeatureMapEntries2() {
		return (EReference)classForControlsEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassWithName() {
		return classWithNameEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClassWithName_Name() {
		return (EAttribute)classWithNameEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBaseClassForFeatureMapEntry() {
		return baseClassForFeatureMapEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseClassForFeatureMapEntry_Name() {
		return (EAttribute)baseClassForFeatureMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassForFeatureMapEntry1() {
		return classForFeatureMapEntry1EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassForFeatureMapEntry2() {
		return classForFeatureMapEntry2EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassForTable() {
		return classForTableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassForTable_ClassWithName1() {
		return (EReference)classForTableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassForTable_ClassWithName2() {
		return (EReference)classForTableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestContainer() {
		return testContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestContainer_ClassesWithName() {
		return (EReference)testContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestContainer_ClassesForControls() {
		return (EReference)testContainerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestContainer_Contained() {
		return (EReference)testContainerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestContainer_ClassesForTable() {
		return (EReference)testContainerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getEnumForControls() {
		return enumForControlsEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getBooleanDataType() {
		return booleanDataTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getBooleanPrimitiveDataType() {
		return booleanPrimitiveDataTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestmodelsFactory getTestmodelsFactory() {
		return (TestmodelsFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		testContainerEClass = createEClass(TEST_CONTAINER);
		createEReference(testContainerEClass, TEST_CONTAINER__CLASSES_WITH_NAME);
		createEReference(testContainerEClass, TEST_CONTAINER__CLASSES_FOR_CONTROLS);
		createEReference(testContainerEClass, TEST_CONTAINER__CONTAINED);
		createEReference(testContainerEClass, TEST_CONTAINER__CLASSES_FOR_TABLE);

		testEClassEClass = createEClass(TEST_ECLASS);
		createEAttribute(testEClassEClass, TEST_ECLASS__LOWERCASE_NAME_FEATURE);
		createEAttribute(testEClassEClass, TEST_ECLASS__UPPER_CASE_NAME_FEATURE);
		createEAttribute(testEClassEClass, TEST_ECLASS__NOT_CHANGEABLE_FEATURE);
		createEAttribute(testEClassEClass, TEST_ECLASS__DERIVED_FEATURE);

		aBaseClassEClass = createEClass(ABASE_CLASS);

		baseClassEClass = createEClass(BASE_CLASS);
		createEAttribute(baseClassEClass, BASE_CLASS__BASE_CLASS_FEATURE);
		createEReference(baseClassEClass, BASE_CLASS__BASE_MULTI_REFERENCE_FEATURE);

		derivedClassEClass = createEClass(DERIVED_CLASS);
		createEAttribute(derivedClassEClass, DERIVED_CLASS__DERIVED_CLASS_FEATURE);
		createEReference(derivedClassEClass, DERIVED_CLASS__DERIVED_MULTI_REFERENCE_FEATURE);

		derivedDerivedClassEClass = createEClass(DERIVED_DERIVED_CLASS);

		multipleInheritanceClassEClass = createEClass(MULTIPLE_INHERITANCE_CLASS);

		classForControlsEClass = createEClass(CLASS_FOR_CONTROLS);
		createEAttribute(classForControlsEClass, CLASS_FOR_CONTROLS__BOOLEAN_FEATURE);
		createEAttribute(classForControlsEClass, CLASS_FOR_CONTROLS__BOOLEAN_OBJECT_FEATURE);
		createEAttribute(classForControlsEClass, CLASS_FOR_CONTROLS__BOOLEAN_DATA_TYPE_FEATURE);
		createEAttribute(classForControlsEClass, CLASS_FOR_CONTROLS__BOOLEAN_PRIMITIVE_DATA_TYPE_FEATURE);
		createEAttribute(classForControlsEClass, CLASS_FOR_CONTROLS__ENUM_FEATURE);
		createEAttribute(classForControlsEClass, CLASS_FOR_CONTROLS__STRING_FEATURE);
		createEAttribute(classForControlsEClass, CLASS_FOR_CONTROLS__UNCHANGEABLE_STRING_FEATURE);
		createEAttribute(classForControlsEClass, CLASS_FOR_CONTROLS__DERIVED_STRING_FEATURE);
		createEReference(classForControlsEClass, CLASS_FOR_CONTROLS__REFERENCE_TO_CLASS_WITH_NAME);
		createEReference(classForControlsEClass, CLASS_FOR_CONTROLS__MULTI_REFERENCE_FEATURE);
		createEAttribute(classForControlsEClass, CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES);
		createEReference(classForControlsEClass, CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES1);
		createEReference(classForControlsEClass, CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES2);

		classWithNameEClass = createEClass(CLASS_WITH_NAME);
		createEAttribute(classWithNameEClass, CLASS_WITH_NAME__NAME);

		baseClassForFeatureMapEntryEClass = createEClass(BASE_CLASS_FOR_FEATURE_MAP_ENTRY);
		createEAttribute(baseClassForFeatureMapEntryEClass, BASE_CLASS_FOR_FEATURE_MAP_ENTRY__NAME);

		classForFeatureMapEntry1EClass = createEClass(CLASS_FOR_FEATURE_MAP_ENTRY1);

		classForFeatureMapEntry2EClass = createEClass(CLASS_FOR_FEATURE_MAP_ENTRY2);

		classForTableEClass = createEClass(CLASS_FOR_TABLE);
		createEReference(classForTableEClass, CLASS_FOR_TABLE__CLASS_WITH_NAME1);
		createEReference(classForTableEClass, CLASS_FOR_TABLE__CLASS_WITH_NAME2);

		// Create enums
		enumForControlsEEnum = createEEnum(ENUM_FOR_CONTROLS);

		// Create data types
		booleanDataTypeEDataType = createEDataType(BOOLEAN_DATA_TYPE);
		booleanPrimitiveDataTypeEDataType = createEDataType(BOOLEAN_PRIMITIVE_DATA_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		derivedClassEClass.getESuperTypes().add(this.getBaseClass());
		derivedDerivedClassEClass.getESuperTypes().add(this.getDerivedClass());
		multipleInheritanceClassEClass.getESuperTypes().add(this.getTestEClass());
		multipleInheritanceClassEClass.getESuperTypes().add(this.getABaseClass());
		multipleInheritanceClassEClass.getESuperTypes().add(this.getDerivedDerivedClass());
		classForFeatureMapEntry1EClass.getESuperTypes().add(this.getBaseClassForFeatureMapEntry());
		classForFeatureMapEntry2EClass.getESuperTypes().add(this.getBaseClassForFeatureMapEntry());

		// Initialize classes, features, and operations; add parameters
		initEClass(testContainerEClass, TestContainer.class, "TestContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTestContainer_ClassesWithName(), this.getClassWithName(), null, "classesWithName", null, 0, -1, TestContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestContainer_ClassesForControls(), this.getClassForControls(), null, "classesForControls", null, 0, -1, TestContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestContainer_Contained(), this.getTestContainer(), null, "contained", null, 0, 1, TestContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestContainer_ClassesForTable(), this.getClassForTable(), null, "classesForTable", null, 0, -1, TestContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testEClassEClass, TestEClass.class, "TestEClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTestEClass_LowercaseNameFeature(), ecorePackage.getEString(), "lowercaseNameFeature", null, 0, 1, TestEClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestEClass_UpperCaseNameFeature(), ecorePackage.getEString(), "UpperCaseNameFeature", null, 0, 1, TestEClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestEClass_NotChangeableFeature(), ecorePackage.getEString(), "notChangeableFeature", null, 0, 1, TestEClass.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestEClass_DerivedFeature(), ecorePackage.getEString(), "derivedFeature", "", 0, 1, TestEClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(aBaseClassEClass, ABaseClass.class, "ABaseClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(baseClassEClass, BaseClass.class, "BaseClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBaseClass_BaseClassFeature(), ecorePackage.getEString(), "baseClassFeature", null, 0, 1, BaseClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBaseClass_BaseMultiReferenceFeature(), this.getClassWithName(), null, "baseMultiReferenceFeature", null, 0, -1, BaseClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(derivedClassEClass, DerivedClass.class, "DerivedClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDerivedClass_DerivedClassFeature(), ecorePackage.getEString(), "derivedClassFeature", null, 0, 1, DerivedClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDerivedClass_DerivedMultiReferenceFeature(), this.getClassWithName(), null, "derivedMultiReferenceFeature", null, 0, -1, DerivedClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(derivedDerivedClassEClass, DerivedDerivedClass.class, "DerivedDerivedClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(multipleInheritanceClassEClass, MultipleInheritanceClass.class, "MultipleInheritanceClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(classForControlsEClass, ClassForControls.class, "ClassForControls", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getClassForControls_BooleanFeature(), ecorePackage.getEBoolean(), "booleanFeature", null, 0, 1, ClassForControls.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClassForControls_BooleanObjectFeature(), ecorePackage.getEBooleanObject(), "booleanObjectFeature", null, 0, 1, ClassForControls.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClassForControls_BooleanDataTypeFeature(), this.getBooleanDataType(), "booleanDataTypeFeature", null, 0, 1, ClassForControls.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClassForControls_BooleanPrimitiveDataTypeFeature(), this.getBooleanPrimitiveDataType(), "booleanPrimitiveDataTypeFeature", null, 0, 1, ClassForControls.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClassForControls_EnumFeature(), this.getEnumForControls(), "enumFeature", null, 0, 1, ClassForControls.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClassForControls_StringFeature(), ecorePackage.getEString(), "stringFeature", null, 0, 1, ClassForControls.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClassForControls_UnchangeableStringFeature(), ecorePackage.getEString(), "unchangeableStringFeature", null, 0, 1, ClassForControls.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClassForControls_DerivedStringFeature(), ecorePackage.getEString(), "derivedStringFeature", null, 0, 1, ClassForControls.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getClassForControls_ReferenceToClassWithName(), this.getClassWithName(), null, "referenceToClassWithName", null, 0, 1, ClassForControls.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassForControls_MultiReferenceFeature(), this.getClassWithName(), null, "multiReferenceFeature", null, 0, -1, ClassForControls.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClassForControls_FeatureMapEntries(), ecorePackage.getEFeatureMapEntry(), "featureMapEntries", null, 0, -1, ClassForControls.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassForControls_FeatureMapEntries1(), this.getClassForFeatureMapEntry1(), null, "featureMapEntries1", null, 0, -1, ClassForControls.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getClassForControls_FeatureMapEntries2(), this.getClassForFeatureMapEntry2(), null, "featureMapEntries2", null, 0, -1, ClassForControls.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(classWithNameEClass, ClassWithName.class, "ClassWithName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getClassWithName_Name(), ecorePackage.getEString(), "name", null, 0, 1, ClassWithName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(baseClassForFeatureMapEntryEClass, BaseClassForFeatureMapEntry.class, "BaseClassForFeatureMapEntry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBaseClassForFeatureMapEntry_Name(), ecorePackage.getEString(), "name", null, 0, 1, BaseClassForFeatureMapEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(classForFeatureMapEntry1EClass, ClassForFeatureMapEntry1.class, "ClassForFeatureMapEntry1", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(classForFeatureMapEntry2EClass, ClassForFeatureMapEntry2.class, "ClassForFeatureMapEntry2", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(classForTableEClass, ClassForTable.class, "ClassForTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClassForTable_ClassWithName1(), this.getClassWithName(), null, "classWithName1", null, 0, 1, ClassForTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassForTable_ClassWithName2(), this.getClassWithName(), null, "classWithName2", null, 0, 1, ClassForTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(enumForControlsEEnum, EnumForControls.class, "EnumForControls");
		addEEnumLiteral(enumForControlsEEnum, EnumForControls.FIRST);
		addEEnumLiteral(enumForControlsEEnum, EnumForControls.SECOND);
		addEEnumLiteral(enumForControlsEEnum, EnumForControls.THIRD);

		// Initialize data types
		initEDataType(booleanDataTypeEDataType, Boolean.class, "BooleanDataType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(booleanPrimitiveDataTypeEDataType, boolean.class, "BooleanPrimitiveDataType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";	
		addAnnotation
		  (getClassForControls_FeatureMapEntries(), 
		   source, 
		   new String[] {
			 "kind", "group"
		   });	
		addAnnotation
		  (getClassForControls_FeatureMapEntries1(), 
		   source, 
		   new String[] {
			 "group", "#featureMapEntries"
		   });	
		addAnnotation
		  (getClassForControls_FeatureMapEntries2(), 
		   source, 
		   new String[] {
			 "group", "#featureMapEntries"
		   });
	}

} //TestmodelsPackageImpl
