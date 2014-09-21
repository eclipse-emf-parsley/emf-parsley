/**
 */
package org.eclipse.emf.parsley.tests.models.testmodels.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.parsley.tests.models.testmodels.BaseClass;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Base Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.BaseClassImpl#getBaseClassFeature <em>Base Class Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.BaseClassImpl#getBaseMultiReferenceFeature <em>Base Multi Reference Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BaseClassImpl extends MinimalEObjectImpl.Container implements BaseClass {
	/**
	 * The default value of the '{@link #getBaseClassFeature() <em>Base Class Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseClassFeature()
	 * @generated
	 * @ordered
	 */
	protected static final String BASE_CLASS_FEATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBaseClassFeature() <em>Base Class Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseClassFeature()
	 * @generated
	 * @ordered
	 */
	protected String baseClassFeature = BASE_CLASS_FEATURE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBaseMultiReferenceFeature() <em>Base Multi Reference Feature</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseMultiReferenceFeature()
	 * @generated
	 * @ordered
	 */
	protected EList<ClassWithName> baseMultiReferenceFeature;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BaseClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestmodelsPackage.Literals.BASE_CLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBaseClassFeature() {
		return baseClassFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBaseClassFeature(String newBaseClassFeature) {
		String oldBaseClassFeature = baseClassFeature;
		baseClassFeature = newBaseClassFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.BASE_CLASS__BASE_CLASS_FEATURE, oldBaseClassFeature, baseClassFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ClassWithName> getBaseMultiReferenceFeature() {
		if (baseMultiReferenceFeature == null) {
			baseMultiReferenceFeature = new EObjectResolvingEList<ClassWithName>(ClassWithName.class, this, TestmodelsPackage.BASE_CLASS__BASE_MULTI_REFERENCE_FEATURE);
		}
		return baseMultiReferenceFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestmodelsPackage.BASE_CLASS__BASE_CLASS_FEATURE:
				return getBaseClassFeature();
			case TestmodelsPackage.BASE_CLASS__BASE_MULTI_REFERENCE_FEATURE:
				return getBaseMultiReferenceFeature();
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
			case TestmodelsPackage.BASE_CLASS__BASE_CLASS_FEATURE:
				setBaseClassFeature((String)newValue);
				return;
			case TestmodelsPackage.BASE_CLASS__BASE_MULTI_REFERENCE_FEATURE:
				getBaseMultiReferenceFeature().clear();
				getBaseMultiReferenceFeature().addAll((Collection<? extends ClassWithName>)newValue);
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
			case TestmodelsPackage.BASE_CLASS__BASE_CLASS_FEATURE:
				setBaseClassFeature(BASE_CLASS_FEATURE_EDEFAULT);
				return;
			case TestmodelsPackage.BASE_CLASS__BASE_MULTI_REFERENCE_FEATURE:
				getBaseMultiReferenceFeature().clear();
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
			case TestmodelsPackage.BASE_CLASS__BASE_CLASS_FEATURE:
				return BASE_CLASS_FEATURE_EDEFAULT == null ? baseClassFeature != null : !BASE_CLASS_FEATURE_EDEFAULT.equals(baseClassFeature);
			case TestmodelsPackage.BASE_CLASS__BASE_MULTI_REFERENCE_FEATURE:
				return baseMultiReferenceFeature != null && !baseMultiReferenceFeature.isEmpty();
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
		result.append(" (baseClassFeature: ");
		result.append(baseClassFeature);
		result.append(')');
		return result.toString();
	}

} //BaseClassImpl
