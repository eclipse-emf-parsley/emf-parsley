/**
 */
package org.eclipse.emf.parsley.tests.models.testmodels.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.parsley.tests.models.testmodels.DerivedClass;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Derived Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.DerivedClassImpl#getDerivedClassFeature <em>Derived Class Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DerivedClassImpl extends BaseClassImpl implements DerivedClass {
	/**
	 * The default value of the '{@link #getDerivedClassFeature() <em>Derived Class Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDerivedClassFeature()
	 * @generated
	 * @ordered
	 */
	protected static final String DERIVED_CLASS_FEATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDerivedClassFeature() <em>Derived Class Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDerivedClassFeature()
	 * @generated
	 * @ordered
	 */
	protected String derivedClassFeature = DERIVED_CLASS_FEATURE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DerivedClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestmodelsPackage.Literals.DERIVED_CLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDerivedClassFeature() {
		return derivedClassFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDerivedClassFeature(String newDerivedClassFeature) {
		String oldDerivedClassFeature = derivedClassFeature;
		derivedClassFeature = newDerivedClassFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.DERIVED_CLASS__DERIVED_CLASS_FEATURE, oldDerivedClassFeature, derivedClassFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestmodelsPackage.DERIVED_CLASS__DERIVED_CLASS_FEATURE:
				return getDerivedClassFeature();
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
			case TestmodelsPackage.DERIVED_CLASS__DERIVED_CLASS_FEATURE:
				setDerivedClassFeature((String)newValue);
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
			case TestmodelsPackage.DERIVED_CLASS__DERIVED_CLASS_FEATURE:
				setDerivedClassFeature(DERIVED_CLASS_FEATURE_EDEFAULT);
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
			case TestmodelsPackage.DERIVED_CLASS__DERIVED_CLASS_FEATURE:
				return DERIVED_CLASS_FEATURE_EDEFAULT == null ? derivedClassFeature != null : !DERIVED_CLASS_FEATURE_EDEFAULT.equals(derivedClassFeature);
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
		result.append(" (derivedClassFeature: ");
		result.append(derivedClassFeature);
		result.append(')');
		return result.toString();
	}

} //DerivedClassImpl
