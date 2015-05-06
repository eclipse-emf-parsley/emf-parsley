/**
 */
package org.eclipse.emf.parsley.tests.models.testmodels.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.parsley.tests.models.testmodels.ClassForValidation;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class For Validation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForValidationImpl#getNotEmpty <em>Not Empty</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ClassForValidationImpl extends MinimalEObjectImpl.Container implements ClassForValidation {
	/**
	 * The default value of the '{@link #getNotEmpty() <em>Not Empty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotEmpty()
	 * @generated
	 * @ordered
	 */
	protected static final String NOT_EMPTY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNotEmpty() <em>Not Empty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotEmpty()
	 * @generated
	 * @ordered
	 */
	protected String notEmpty = NOT_EMPTY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassForValidationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestmodelsPackage.Literals.CLASS_FOR_VALIDATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNotEmpty() {
		return notEmpty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNotEmpty(String newNotEmpty) {
		String oldNotEmpty = notEmpty;
		notEmpty = newNotEmpty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_VALIDATION__NOT_EMPTY, oldNotEmpty, notEmpty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestmodelsPackage.CLASS_FOR_VALIDATION__NOT_EMPTY:
				return getNotEmpty();
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
			case TestmodelsPackage.CLASS_FOR_VALIDATION__NOT_EMPTY:
				setNotEmpty((String)newValue);
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
			case TestmodelsPackage.CLASS_FOR_VALIDATION__NOT_EMPTY:
				setNotEmpty(NOT_EMPTY_EDEFAULT);
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
			case TestmodelsPackage.CLASS_FOR_VALIDATION__NOT_EMPTY:
				return NOT_EMPTY_EDEFAULT == null ? notEmpty != null : !NOT_EMPTY_EDEFAULT.equals(notEmpty);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (notEmpty: ");
		result.append(notEmpty);
		result.append(')');
		return result.toString();
	}

} //ClassForValidationImpl
