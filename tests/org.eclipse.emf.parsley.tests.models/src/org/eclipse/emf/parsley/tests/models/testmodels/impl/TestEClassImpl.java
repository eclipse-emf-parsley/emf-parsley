/**
 */
package org.eclipse.emf.parsley.tests.models.testmodels.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.parsley.tests.models.testmodels.TestEClass;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test EClass</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.TestEClassImpl#getLowercaseNameFeature <em>Lowercase Name Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.TestEClassImpl#getUpperCaseNameFeature <em>Upper Case Name Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestEClassImpl extends MinimalEObjectImpl.Container implements TestEClass {
	/**
	 * The default value of the '{@link #getLowercaseNameFeature() <em>Lowercase Name Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowercaseNameFeature()
	 * @generated
	 * @ordered
	 */
	protected static final String LOWERCASE_NAME_FEATURE_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getLowercaseNameFeature() <em>Lowercase Name Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowercaseNameFeature()
	 * @generated
	 * @ordered
	 */
	protected String lowercaseNameFeature = LOWERCASE_NAME_FEATURE_EDEFAULT;
	/**
	 * The default value of the '{@link #getUpperCaseNameFeature() <em>Upper Case Name Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperCaseNameFeature()
	 * @generated
	 * @ordered
	 */
	protected static final String UPPER_CASE_NAME_FEATURE_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getUpperCaseNameFeature() <em>Upper Case Name Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperCaseNameFeature()
	 * @generated
	 * @ordered
	 */
	protected String upperCaseNameFeature = UPPER_CASE_NAME_FEATURE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestEClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestmodelsPackage.Literals.TEST_ECLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLowercaseNameFeature() {
		return lowercaseNameFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLowercaseNameFeature(String newLowercaseNameFeature) {
		String oldLowercaseNameFeature = lowercaseNameFeature;
		lowercaseNameFeature = newLowercaseNameFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.TEST_ECLASS__LOWERCASE_NAME_FEATURE, oldLowercaseNameFeature, lowercaseNameFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUpperCaseNameFeature() {
		return upperCaseNameFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUpperCaseNameFeature(String newUpperCaseNameFeature) {
		String oldUpperCaseNameFeature = upperCaseNameFeature;
		upperCaseNameFeature = newUpperCaseNameFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.TEST_ECLASS__UPPER_CASE_NAME_FEATURE, oldUpperCaseNameFeature, upperCaseNameFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestmodelsPackage.TEST_ECLASS__LOWERCASE_NAME_FEATURE:
				return getLowercaseNameFeature();
			case TestmodelsPackage.TEST_ECLASS__UPPER_CASE_NAME_FEATURE:
				return getUpperCaseNameFeature();
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
			case TestmodelsPackage.TEST_ECLASS__LOWERCASE_NAME_FEATURE:
				setLowercaseNameFeature((String)newValue);
				return;
			case TestmodelsPackage.TEST_ECLASS__UPPER_CASE_NAME_FEATURE:
				setUpperCaseNameFeature((String)newValue);
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
			case TestmodelsPackage.TEST_ECLASS__LOWERCASE_NAME_FEATURE:
				setLowercaseNameFeature(LOWERCASE_NAME_FEATURE_EDEFAULT);
				return;
			case TestmodelsPackage.TEST_ECLASS__UPPER_CASE_NAME_FEATURE:
				setUpperCaseNameFeature(UPPER_CASE_NAME_FEATURE_EDEFAULT);
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
			case TestmodelsPackage.TEST_ECLASS__LOWERCASE_NAME_FEATURE:
				return LOWERCASE_NAME_FEATURE_EDEFAULT == null ? lowercaseNameFeature != null : !LOWERCASE_NAME_FEATURE_EDEFAULT.equals(lowercaseNameFeature);
			case TestmodelsPackage.TEST_ECLASS__UPPER_CASE_NAME_FEATURE:
				return UPPER_CASE_NAME_FEATURE_EDEFAULT == null ? upperCaseNameFeature != null : !UPPER_CASE_NAME_FEATURE_EDEFAULT.equals(upperCaseNameFeature);
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
		result.append(" (lowercaseNameFeature: ");
		result.append(lowercaseNameFeature);
		result.append(", UpperCaseNameFeature: ");
		result.append(upperCaseNameFeature);
		result.append(')');
		return result.toString();
	}

} //TestEClassImpl
