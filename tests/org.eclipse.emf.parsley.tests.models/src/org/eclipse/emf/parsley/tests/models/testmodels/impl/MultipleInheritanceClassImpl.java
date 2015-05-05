/**
 */
package org.eclipse.emf.parsley.tests.models.testmodels.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.parsley.tests.models.testmodels.ABaseClass;
import org.eclipse.emf.parsley.tests.models.testmodels.BaseClass;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName;
import org.eclipse.emf.parsley.tests.models.testmodels.DerivedClass;
import org.eclipse.emf.parsley.tests.models.testmodels.DerivedDerivedClass;
import org.eclipse.emf.parsley.tests.models.testmodels.MultipleInheritanceClass;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Multiple Inheritance Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.MultipleInheritanceClassImpl#getBaseClassFeature <em>Base Class Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.MultipleInheritanceClassImpl#getBaseMultiReferenceFeature <em>Base Multi Reference Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.MultipleInheritanceClassImpl#getDerivedClassFeature <em>Derived Class Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.MultipleInheritanceClassImpl#getDerivedMultiReferenceFeature <em>Derived Multi Reference Feature</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MultipleInheritanceClassImpl extends TestEClassImpl implements MultipleInheritanceClass {
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
	protected MultipleInheritanceClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestmodelsPackage.Literals.MULTIPLE_INHERITANCE_CLASS;
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
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__BASE_CLASS_FEATURE, oldBaseClassFeature, baseClassFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ClassWithName> getBaseMultiReferenceFeature() {
		if (baseMultiReferenceFeature == null) {
			baseMultiReferenceFeature = new EObjectResolvingEList<ClassWithName>(ClassWithName.class, this, TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__BASE_MULTI_REFERENCE_FEATURE);
		}
		return baseMultiReferenceFeature;
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
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__DERIVED_CLASS_FEATURE, oldDerivedClassFeature, derivedClassFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ClassWithName> getDerivedMultiReferenceFeature() {
		if (derivedMultiReferenceFeature == null) {
			derivedMultiReferenceFeature = new EObjectResolvingEList<ClassWithName>(ClassWithName.class, this, TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__DERIVED_MULTI_REFERENCE_FEATURE);
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
			case TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__BASE_CLASS_FEATURE:
				return getBaseClassFeature();
			case TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__BASE_MULTI_REFERENCE_FEATURE:
				return getBaseMultiReferenceFeature();
			case TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__DERIVED_CLASS_FEATURE:
				return getDerivedClassFeature();
			case TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__DERIVED_MULTI_REFERENCE_FEATURE:
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
			case TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__BASE_CLASS_FEATURE:
				setBaseClassFeature((String)newValue);
				return;
			case TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__BASE_MULTI_REFERENCE_FEATURE:
				getBaseMultiReferenceFeature().clear();
				getBaseMultiReferenceFeature().addAll((Collection<? extends ClassWithName>)newValue);
				return;
			case TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__DERIVED_CLASS_FEATURE:
				setDerivedClassFeature((String)newValue);
				return;
			case TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__DERIVED_MULTI_REFERENCE_FEATURE:
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
			case TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__BASE_CLASS_FEATURE:
				setBaseClassFeature(BASE_CLASS_FEATURE_EDEFAULT);
				return;
			case TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__BASE_MULTI_REFERENCE_FEATURE:
				getBaseMultiReferenceFeature().clear();
				return;
			case TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__DERIVED_CLASS_FEATURE:
				setDerivedClassFeature(DERIVED_CLASS_FEATURE_EDEFAULT);
				return;
			case TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__DERIVED_MULTI_REFERENCE_FEATURE:
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
			case TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__BASE_CLASS_FEATURE:
				return BASE_CLASS_FEATURE_EDEFAULT == null ? baseClassFeature != null : !BASE_CLASS_FEATURE_EDEFAULT.equals(baseClassFeature);
			case TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__BASE_MULTI_REFERENCE_FEATURE:
				return baseMultiReferenceFeature != null && !baseMultiReferenceFeature.isEmpty();
			case TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__DERIVED_CLASS_FEATURE:
				return DERIVED_CLASS_FEATURE_EDEFAULT == null ? derivedClassFeature != null : !DERIVED_CLASS_FEATURE_EDEFAULT.equals(derivedClassFeature);
			case TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__DERIVED_MULTI_REFERENCE_FEATURE:
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
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == ABaseClass.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == BaseClass.class) {
			switch (derivedFeatureID) {
				case TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__BASE_CLASS_FEATURE: return TestmodelsPackage.BASE_CLASS__BASE_CLASS_FEATURE;
				case TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__BASE_MULTI_REFERENCE_FEATURE: return TestmodelsPackage.BASE_CLASS__BASE_MULTI_REFERENCE_FEATURE;
				default: return -1;
			}
		}
		if (baseClass == DerivedClass.class) {
			switch (derivedFeatureID) {
				case TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__DERIVED_CLASS_FEATURE: return TestmodelsPackage.DERIVED_CLASS__DERIVED_CLASS_FEATURE;
				case TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__DERIVED_MULTI_REFERENCE_FEATURE: return TestmodelsPackage.DERIVED_CLASS__DERIVED_MULTI_REFERENCE_FEATURE;
				default: return -1;
			}
		}
		if (baseClass == DerivedDerivedClass.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == ABaseClass.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == BaseClass.class) {
			switch (baseFeatureID) {
				case TestmodelsPackage.BASE_CLASS__BASE_CLASS_FEATURE: return TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__BASE_CLASS_FEATURE;
				case TestmodelsPackage.BASE_CLASS__BASE_MULTI_REFERENCE_FEATURE: return TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__BASE_MULTI_REFERENCE_FEATURE;
				default: return -1;
			}
		}
		if (baseClass == DerivedClass.class) {
			switch (baseFeatureID) {
				case TestmodelsPackage.DERIVED_CLASS__DERIVED_CLASS_FEATURE: return TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__DERIVED_CLASS_FEATURE;
				case TestmodelsPackage.DERIVED_CLASS__DERIVED_MULTI_REFERENCE_FEATURE: return TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS__DERIVED_MULTI_REFERENCE_FEATURE;
				default: return -1;
			}
		}
		if (baseClass == DerivedDerivedClass.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(", derivedClassFeature: ");
		result.append(derivedClassFeature);
		result.append(')');
		return result.toString();
	}

} //MultipleInheritanceClassImpl
