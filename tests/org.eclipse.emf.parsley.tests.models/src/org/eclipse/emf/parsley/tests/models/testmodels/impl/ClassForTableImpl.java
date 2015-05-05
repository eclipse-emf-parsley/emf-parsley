/**
 */
package org.eclipse.emf.parsley.tests.models.testmodels.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.parsley.tests.models.testmodels.ClassForTable;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class For Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForTableImpl#getClassWithName1 <em>Class With Name1</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForTableImpl#getClassWithName2 <em>Class With Name2</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ClassForTableImpl extends MinimalEObjectImpl.Container implements ClassForTable {
	/**
	 * The cached value of the '{@link #getClassWithName1() <em>Class With Name1</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassWithName1()
	 * @generated
	 * @ordered
	 */
	protected ClassWithName classWithName1;

	/**
	 * The cached value of the '{@link #getClassWithName2() <em>Class With Name2</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassWithName2()
	 * @generated
	 * @ordered
	 */
	protected ClassWithName classWithName2;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassForTableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestmodelsPackage.Literals.CLASS_FOR_TABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassWithName getClassWithName1() {
		if (classWithName1 != null && classWithName1.eIsProxy()) {
			InternalEObject oldClassWithName1 = (InternalEObject)classWithName1;
			classWithName1 = (ClassWithName)eResolveProxy(oldClassWithName1);
			if (classWithName1 != oldClassWithName1) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TestmodelsPackage.CLASS_FOR_TABLE__CLASS_WITH_NAME1, oldClassWithName1, classWithName1));
			}
		}
		return classWithName1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassWithName basicGetClassWithName1() {
		return classWithName1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassWithName1(ClassWithName newClassWithName1) {
		ClassWithName oldClassWithName1 = classWithName1;
		classWithName1 = newClassWithName1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_TABLE__CLASS_WITH_NAME1, oldClassWithName1, classWithName1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassWithName getClassWithName2() {
		if (classWithName2 != null && classWithName2.eIsProxy()) {
			InternalEObject oldClassWithName2 = (InternalEObject)classWithName2;
			classWithName2 = (ClassWithName)eResolveProxy(oldClassWithName2);
			if (classWithName2 != oldClassWithName2) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TestmodelsPackage.CLASS_FOR_TABLE__CLASS_WITH_NAME2, oldClassWithName2, classWithName2));
			}
		}
		return classWithName2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassWithName basicGetClassWithName2() {
		return classWithName2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassWithName2(ClassWithName newClassWithName2) {
		ClassWithName oldClassWithName2 = classWithName2;
		classWithName2 = newClassWithName2;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_TABLE__CLASS_WITH_NAME2, oldClassWithName2, classWithName2));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestmodelsPackage.CLASS_FOR_TABLE__CLASS_WITH_NAME1:
				if (resolve) return getClassWithName1();
				return basicGetClassWithName1();
			case TestmodelsPackage.CLASS_FOR_TABLE__CLASS_WITH_NAME2:
				if (resolve) return getClassWithName2();
				return basicGetClassWithName2();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TestmodelsPackage.CLASS_FOR_TABLE__CLASS_WITH_NAME1:
				setClassWithName1((ClassWithName)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_TABLE__CLASS_WITH_NAME2:
				setClassWithName2((ClassWithName)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TestmodelsPackage.CLASS_FOR_TABLE__CLASS_WITH_NAME1:
				setClassWithName1((ClassWithName)null);
				return;
			case TestmodelsPackage.CLASS_FOR_TABLE__CLASS_WITH_NAME2:
				setClassWithName2((ClassWithName)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TestmodelsPackage.CLASS_FOR_TABLE__CLASS_WITH_NAME1:
				return classWithName1 != null;
			case TestmodelsPackage.CLASS_FOR_TABLE__CLASS_WITH_NAME2:
				return classWithName2 != null;
		}
		return super.eIsSet(featureID);
	}

} //ClassForTableImpl
