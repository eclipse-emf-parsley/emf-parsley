/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
/**
 */
package org.eclipse.emf.parsley.tests.models.testmodels.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.emf.parsley.tests.models.testmodels.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage
 * @generated
 */
public class TestmodelsSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TestmodelsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestmodelsSwitch() {
		if (modelPackage == null) {
			modelPackage = TestmodelsPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case TestmodelsPackage.TEST_CONTAINER: {
				TestContainer testContainer = (TestContainer)theEObject;
				T result = caseTestContainer(testContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelsPackage.TEST_ECLASS: {
				TestEClass testEClass = (TestEClass)theEObject;
				T result = caseTestEClass(testEClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelsPackage.BASE_CLASS: {
				BaseClass baseClass = (BaseClass)theEObject;
				T result = caseBaseClass(baseClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelsPackage.DERIVED_CLASS: {
				DerivedClass derivedClass = (DerivedClass)theEObject;
				T result = caseDerivedClass(derivedClass);
				if (result == null) result = caseBaseClass(derivedClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelsPackage.CLASS_FOR_CONTROLS: {
				ClassForControls classForControls = (ClassForControls)theEObject;
				T result = caseClassForControls(classForControls);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelsPackage.CLASS_WITH_NAME: {
				ClassWithName classWithName = (ClassWithName)theEObject;
				T result = caseClassWithName(classWithName);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelsPackage.BASE_CLASS_FOR_FEATURE_MAP_ENTRY: {
				BaseClassForFeatureMapEntry baseClassForFeatureMapEntry = (BaseClassForFeatureMapEntry)theEObject;
				T result = caseBaseClassForFeatureMapEntry(baseClassForFeatureMapEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelsPackage.CLASS_FOR_FEATURE_MAP_ENTRY1: {
				ClassForFeatureMapEntry1 classForFeatureMapEntry1 = (ClassForFeatureMapEntry1)theEObject;
				T result = caseClassForFeatureMapEntry1(classForFeatureMapEntry1);
				if (result == null) result = caseBaseClassForFeatureMapEntry(classForFeatureMapEntry1);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelsPackage.CLASS_FOR_FEATURE_MAP_ENTRY2: {
				ClassForFeatureMapEntry2 classForFeatureMapEntry2 = (ClassForFeatureMapEntry2)theEObject;
				T result = caseClassForFeatureMapEntry2(classForFeatureMapEntry2);
				if (result == null) result = caseBaseClassForFeatureMapEntry(classForFeatureMapEntry2);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test EClass</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test EClass</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestEClass(TestEClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Base Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Base Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBaseClass(BaseClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Derived Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Derived Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDerivedClass(DerivedClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Class For Controls</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Class For Controls</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClassForControls(ClassForControls object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Class With Name</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Class With Name</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClassWithName(ClassWithName object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Base Class For Feature Map Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Base Class For Feature Map Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBaseClassForFeatureMapEntry(BaseClassForFeatureMapEntry object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Class For Feature Map Entry1</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Class For Feature Map Entry1</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClassForFeatureMapEntry1(ClassForFeatureMapEntry1 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Class For Feature Map Entry2</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Class For Feature Map Entry2</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClassForFeatureMapEntry2(ClassForFeatureMapEntry2 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestContainer(TestContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //TestmodelsSwitch
