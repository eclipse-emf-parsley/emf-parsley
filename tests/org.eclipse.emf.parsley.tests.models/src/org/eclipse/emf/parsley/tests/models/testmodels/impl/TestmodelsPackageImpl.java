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
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

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
		testEClassEClass = createEClass(TEST_ECLASS);
		createEAttribute(testEClassEClass, TEST_ECLASS__LOWERCASE_NAME_FEATURE);
		createEAttribute(testEClassEClass, TEST_ECLASS__UPPER_CASE_NAME_FEATURE);
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

		// Initialize classes, features, and operations; add parameters
		initEClass(testEClassEClass, TestEClass.class, "TestEClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTestEClass_LowercaseNameFeature(), ecorePackage.getEString(), "lowercaseNameFeature", null, 0, 1, TestEClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestEClass_UpperCaseNameFeature(), ecorePackage.getEString(), "UpperCaseNameFeature", null, 0, 1, TestEClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //TestmodelsPackageImpl
