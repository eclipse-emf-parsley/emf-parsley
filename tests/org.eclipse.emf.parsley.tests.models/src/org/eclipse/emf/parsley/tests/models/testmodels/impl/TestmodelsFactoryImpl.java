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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.parsley.tests.models.testmodels.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TestmodelsFactoryImpl extends EFactoryImpl implements TestmodelsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TestmodelsFactory init() {
		try {
			TestmodelsFactory theTestmodelsFactory = (TestmodelsFactory)EPackage.Registry.INSTANCE.getEFactory(TestmodelsPackage.eNS_URI);
			if (theTestmodelsFactory != null) {
				return theTestmodelsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TestmodelsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestmodelsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case TestmodelsPackage.TEST_ECLASS: return createTestEClass();
			case TestmodelsPackage.BASE_CLASS: return createBaseClass();
			case TestmodelsPackage.DERIVED_CLASS: return createDerivedClass();
			case TestmodelsPackage.CLASS_FOR_CONTROLS: return createClassForControls();
			case TestmodelsPackage.CLASS_WITH_NAME: return createClassWithName();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case TestmodelsPackage.ENUM_FOR_CONTROLS:
				return createEnumForControlsFromString(eDataType, initialValue);
			case TestmodelsPackage.BOOLEAN_DATA_TYPE:
				return createBooleanDataTypeFromString(eDataType, initialValue);
			case TestmodelsPackage.BOOLEAN_PRIMITIVE_DATA_TYPE:
				return createBooleanPrimitiveDataTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case TestmodelsPackage.ENUM_FOR_CONTROLS:
				return convertEnumForControlsToString(eDataType, instanceValue);
			case TestmodelsPackage.BOOLEAN_DATA_TYPE:
				return convertBooleanDataTypeToString(eDataType, instanceValue);
			case TestmodelsPackage.BOOLEAN_PRIMITIVE_DATA_TYPE:
				return convertBooleanPrimitiveDataTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestEClass createTestEClass() {
		TestEClassImpl testEClass = new TestEClassImpl();
		return testEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BaseClass createBaseClass() {
		BaseClassImpl baseClass = new BaseClassImpl();
		return baseClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DerivedClass createDerivedClass() {
		DerivedClassImpl derivedClass = new DerivedClassImpl();
		return derivedClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassForControls createClassForControls() {
		ClassForControlsImpl classForControls = new ClassForControlsImpl();
		return classForControls;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassWithName createClassWithName() {
		ClassWithNameImpl classWithName = new ClassWithNameImpl();
		return classWithName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumForControls createEnumForControlsFromString(EDataType eDataType, String initialValue) {
		EnumForControls result = EnumForControls.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEnumForControlsToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean createBooleanDataTypeFromString(EDataType eDataType, String initialValue) {
		return (Boolean)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBooleanDataTypeToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean createBooleanPrimitiveDataTypeFromString(EDataType eDataType, String initialValue) {
		return (Boolean)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBooleanPrimitiveDataTypeToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestmodelsPackage getTestmodelsPackage() {
		return (TestmodelsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TestmodelsPackage getPackage() {
		return TestmodelsPackage.eINSTANCE;
	}

} //TestmodelsFactoryImpl
