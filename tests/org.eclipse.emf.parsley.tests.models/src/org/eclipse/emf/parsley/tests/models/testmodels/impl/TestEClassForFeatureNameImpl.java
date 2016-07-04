/**
 */
package org.eclipse.emf.parsley.tests.models.testmodels.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.parsley.tests.models.testmodels.TestEClassForFeatureName;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test EClass For Feature Name</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.TestEClassForFeatureNameImpl#getEField <em>EField</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TestEClassForFeatureNameImpl extends MinimalEObjectImpl.Container implements TestEClassForFeatureName {
	/**
	 * The default value of the '{@link #getEField() <em>EField</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEField()
	 * @generated
	 * @ordered
	 */
	protected static final String EFIELD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEField() <em>EField</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEField()
	 * @generated
	 * @ordered
	 */
	protected String eField = EFIELD_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestEClassForFeatureNameImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestmodelsPackage.Literals.TEST_ECLASS_FOR_FEATURE_NAME;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEField() {
		return eField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEField(String newEField) {
		String oldEField = eField;
		eField = newEField;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.TEST_ECLASS_FOR_FEATURE_NAME__EFIELD, oldEField, eField));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestmodelsPackage.TEST_ECLASS_FOR_FEATURE_NAME__EFIELD:
				return getEField();
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
			case TestmodelsPackage.TEST_ECLASS_FOR_FEATURE_NAME__EFIELD:
				setEField((String)newValue);
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
			case TestmodelsPackage.TEST_ECLASS_FOR_FEATURE_NAME__EFIELD:
				setEField(EFIELD_EDEFAULT);
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
			case TestmodelsPackage.TEST_ECLASS_FOR_FEATURE_NAME__EFIELD:
				return EFIELD_EDEFAULT == null ? eField != null : !EFIELD_EDEFAULT.equals(eField);
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
		result.append(" (eField: ");
		result.append(eField);
		result.append(')');
		return result.toString();
	}

} //TestEClassForFeatureNameImpl
