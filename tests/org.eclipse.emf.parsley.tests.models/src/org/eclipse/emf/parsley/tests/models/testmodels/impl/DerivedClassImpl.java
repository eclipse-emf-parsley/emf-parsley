/**
 */
package org.eclipse.emf.parsley.tests.models.testmodels.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName;
import org.eclipse.emf.parsley.tests.models.testmodels.DerivedClass;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Derived Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.DerivedClassImpl#getDerivedClassFeature <em>Derived Class Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.DerivedClassImpl#getDerivedMultiReferenceFeature <em>Derived Multi Reference Feature</em>}</li>
 * </ul>
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
	 * The cached value of the '{@link #getDerivedMultiReferenceFeature() <em>Derived Multi Reference Feature</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDerivedMultiReferenceFeature()
	 * @generated
	 * @ordered
	 */
	protected EList<ClassWithName> derivedMultiReferenceFeature;

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
	public EList<ClassWithName> getDerivedMultiReferenceFeature() {
		if (derivedMultiReferenceFeature == null) {
			derivedMultiReferenceFeature = new EObjectResolvingEList<ClassWithName>(ClassWithName.class, this, TestmodelsPackage.DERIVED_CLASS__DERIVED_MULTI_REFERENCE_FEATURE);
		}
		return derivedMultiReferenceFeature;
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
			case TestmodelsPackage.DERIVED_CLASS__DERIVED_MULTI_REFERENCE_FEATURE:
				return getDerivedMultiReferenceFeature();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TestmodelsPackage.DERIVED_CLASS__DERIVED_CLASS_FEATURE:
				setDerivedClassFeature((String)newValue);
				return;
			case TestmodelsPackage.DERIVED_CLASS__DERIVED_MULTI_REFERENCE_FEATURE:
				getDerivedMultiReferenceFeature().clear();
				getDerivedMultiReferenceFeature().addAll((Collection<? extends ClassWithName>)newValue);
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
			case TestmodelsPackage.DERIVED_CLASS__DERIVED_MULTI_REFERENCE_FEATURE:
				getDerivedMultiReferenceFeature().clear();
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
			case TestmodelsPackage.DERIVED_CLASS__DERIVED_MULTI_REFERENCE_FEATURE:
				return derivedMultiReferenceFeature != null && !derivedMultiReferenceFeature.isEmpty();
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
