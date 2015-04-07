/**
 */
package org.eclipse.emf.parsley.tests.models.testmodels.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForFeatureMapEntry1;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForFeatureMapEntry2;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName;
import org.eclipse.emf.parsley.tests.models.testmodels.EnumForControls;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class For Controls</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#isBooleanFeature <em>Boolean Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getBooleanObjectFeature <em>Boolean Object Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getBooleanDataTypeFeature <em>Boolean Data Type Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#isBooleanPrimitiveDataTypeFeature <em>Boolean Primitive Data Type Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getEnumFeature <em>Enum Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getStringFeature <em>String Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getUnchangeableStringFeature <em>Unchangeable String Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getDerivedStringFeature <em>Derived String Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getReferenceToClassWithName <em>Reference To Class With Name</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getMultiReferenceFeature <em>Multi Reference Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getFeatureMapEntries <em>Feature Map Entries</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getFeatureMapEntries1 <em>Feature Map Entries1</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getFeatureMapEntries2 <em>Feature Map Entries2</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassForControlsImpl extends MinimalEObjectImpl.Container implements ClassForControls {
	/**
	 * The default value of the '{@link #isBooleanFeature() <em>Boolean Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBooleanFeature()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BOOLEAN_FEATURE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isBooleanFeature() <em>Boolean Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBooleanFeature()
	 * @generated
	 * @ordered
	 */
	protected boolean booleanFeature = BOOLEAN_FEATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getBooleanObjectFeature() <em>Boolean Object Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBooleanObjectFeature()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean BOOLEAN_OBJECT_FEATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBooleanObjectFeature() <em>Boolean Object Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBooleanObjectFeature()
	 * @generated
	 * @ordered
	 */
	protected Boolean booleanObjectFeature = BOOLEAN_OBJECT_FEATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getBooleanDataTypeFeature() <em>Boolean Data Type Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBooleanDataTypeFeature()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean BOOLEAN_DATA_TYPE_FEATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBooleanDataTypeFeature() <em>Boolean Data Type Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBooleanDataTypeFeature()
	 * @generated
	 * @ordered
	 */
	protected Boolean booleanDataTypeFeature = BOOLEAN_DATA_TYPE_FEATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #isBooleanPrimitiveDataTypeFeature() <em>Boolean Primitive Data Type Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBooleanPrimitiveDataTypeFeature()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BOOLEAN_PRIMITIVE_DATA_TYPE_FEATURE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isBooleanPrimitiveDataTypeFeature() <em>Boolean Primitive Data Type Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBooleanPrimitiveDataTypeFeature()
	 * @generated
	 * @ordered
	 */
	protected boolean booleanPrimitiveDataTypeFeature = BOOLEAN_PRIMITIVE_DATA_TYPE_FEATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getEnumFeature() <em>Enum Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnumFeature()
	 * @generated
	 * @ordered
	 */
	protected static final EnumForControls ENUM_FEATURE_EDEFAULT = EnumForControls.FIRST;

	/**
	 * The cached value of the '{@link #getEnumFeature() <em>Enum Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnumFeature()
	 * @generated
	 * @ordered
	 */
	protected EnumForControls enumFeature = ENUM_FEATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getStringFeature() <em>String Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStringFeature()
	 * @generated
	 * @ordered
	 */
	protected static final String STRING_FEATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStringFeature() <em>String Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStringFeature()
	 * @generated
	 * @ordered
	 */
	protected String stringFeature = STRING_FEATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getUnchangeableStringFeature() <em>Unchangeable String Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnchangeableStringFeature()
	 * @generated
	 * @ordered
	 */
	protected static final String UNCHANGEABLE_STRING_FEATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUnchangeableStringFeature() <em>Unchangeable String Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnchangeableStringFeature()
	 * @generated
	 * @ordered
	 */
	protected String unchangeableStringFeature = UNCHANGEABLE_STRING_FEATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDerivedStringFeature() <em>Derived String Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDerivedStringFeature()
	 * @generated
	 * @ordered
	 */
	protected static final String DERIVED_STRING_FEATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDerivedStringFeature() <em>Derived String Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDerivedStringFeature()
	 * @generated
	 * @ordered
	 */
	protected String derivedStringFeature = DERIVED_STRING_FEATURE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getReferenceToClassWithName() <em>Reference To Class With Name</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceToClassWithName()
	 * @generated
	 * @ordered
	 */
	protected ClassWithName referenceToClassWithName;

	/**
	 * The cached value of the '{@link #getMultiReferenceFeature() <em>Multi Reference Feature</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMultiReferenceFeature()
	 * @generated
	 * @ordered
	 */
	protected EList<ClassWithName> multiReferenceFeature;

	/**
	 * The cached value of the '{@link #getFeatureMapEntries() <em>Feature Map Entries</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeatureMapEntries()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap featureMapEntries;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassForControlsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestmodelsPackage.Literals.CLASS_FOR_CONTROLS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isBooleanFeature() {
		return booleanFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBooleanFeature(boolean newBooleanFeature) {
		boolean oldBooleanFeature = booleanFeature;
		booleanFeature = newBooleanFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__BOOLEAN_FEATURE, oldBooleanFeature, booleanFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getBooleanObjectFeature() {
		return booleanObjectFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBooleanObjectFeature(Boolean newBooleanObjectFeature) {
		Boolean oldBooleanObjectFeature = booleanObjectFeature;
		booleanObjectFeature = newBooleanObjectFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__BOOLEAN_OBJECT_FEATURE, oldBooleanObjectFeature, booleanObjectFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getBooleanDataTypeFeature() {
		return booleanDataTypeFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBooleanDataTypeFeature(Boolean newBooleanDataTypeFeature) {
		Boolean oldBooleanDataTypeFeature = booleanDataTypeFeature;
		booleanDataTypeFeature = newBooleanDataTypeFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__BOOLEAN_DATA_TYPE_FEATURE, oldBooleanDataTypeFeature, booleanDataTypeFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isBooleanPrimitiveDataTypeFeature() {
		return booleanPrimitiveDataTypeFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBooleanPrimitiveDataTypeFeature(boolean newBooleanPrimitiveDataTypeFeature) {
		boolean oldBooleanPrimitiveDataTypeFeature = booleanPrimitiveDataTypeFeature;
		booleanPrimitiveDataTypeFeature = newBooleanPrimitiveDataTypeFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__BOOLEAN_PRIMITIVE_DATA_TYPE_FEATURE, oldBooleanPrimitiveDataTypeFeature, booleanPrimitiveDataTypeFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumForControls getEnumFeature() {
		return enumFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnumFeature(EnumForControls newEnumFeature) {
		EnumForControls oldEnumFeature = enumFeature;
		enumFeature = newEnumFeature == null ? ENUM_FEATURE_EDEFAULT : newEnumFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__ENUM_FEATURE, oldEnumFeature, enumFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStringFeature() {
		return stringFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStringFeature(String newStringFeature) {
		String oldStringFeature = stringFeature;
		stringFeature = newStringFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__STRING_FEATURE, oldStringFeature, stringFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUnchangeableStringFeature() {
		return unchangeableStringFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDerivedStringFeature() {
		return derivedStringFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDerivedStringFeature(String newDerivedStringFeature) {
		String oldDerivedStringFeature = derivedStringFeature;
		derivedStringFeature = newDerivedStringFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__DERIVED_STRING_FEATURE, oldDerivedStringFeature, derivedStringFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassWithName getReferenceToClassWithName() {
		if (referenceToClassWithName != null && referenceToClassWithName.eIsProxy()) {
			InternalEObject oldReferenceToClassWithName = (InternalEObject)referenceToClassWithName;
			referenceToClassWithName = (ClassWithName)eResolveProxy(oldReferenceToClassWithName);
			if (referenceToClassWithName != oldReferenceToClassWithName) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TestmodelsPackage.CLASS_FOR_CONTROLS__REFERENCE_TO_CLASS_WITH_NAME, oldReferenceToClassWithName, referenceToClassWithName));
			}
		}
		return referenceToClassWithName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassWithName basicGetReferenceToClassWithName() {
		return referenceToClassWithName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferenceToClassWithName(ClassWithName newReferenceToClassWithName) {
		ClassWithName oldReferenceToClassWithName = referenceToClassWithName;
		referenceToClassWithName = newReferenceToClassWithName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__REFERENCE_TO_CLASS_WITH_NAME, oldReferenceToClassWithName, referenceToClassWithName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ClassWithName> getMultiReferenceFeature() {
		if (multiReferenceFeature == null) {
			multiReferenceFeature = new EObjectResolvingEList<ClassWithName>(ClassWithName.class, this, TestmodelsPackage.CLASS_FOR_CONTROLS__MULTI_REFERENCE_FEATURE);
		}
		return multiReferenceFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getFeatureMapEntries() {
		if (featureMapEntries == null) {
			featureMapEntries = new BasicFeatureMap(this, TestmodelsPackage.CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES);
		}
		return featureMapEntries;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ClassForFeatureMapEntry1> getFeatureMapEntries1() {
		return getFeatureMapEntries().list(TestmodelsPackage.Literals.CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ClassForFeatureMapEntry2> getFeatureMapEntries2() {
		return getFeatureMapEntries().list(TestmodelsPackage.Literals.CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TestmodelsPackage.CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES:
				return ((InternalEList<?>)getFeatureMapEntries()).basicRemove(otherEnd, msgs);
			case TestmodelsPackage.CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES1:
				return ((InternalEList<?>)getFeatureMapEntries1()).basicRemove(otherEnd, msgs);
			case TestmodelsPackage.CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES2:
				return ((InternalEList<?>)getFeatureMapEntries2()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BOOLEAN_FEATURE:
				return isBooleanFeature();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BOOLEAN_OBJECT_FEATURE:
				return getBooleanObjectFeature();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BOOLEAN_DATA_TYPE_FEATURE:
				return getBooleanDataTypeFeature();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BOOLEAN_PRIMITIVE_DATA_TYPE_FEATURE:
				return isBooleanPrimitiveDataTypeFeature();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__ENUM_FEATURE:
				return getEnumFeature();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__STRING_FEATURE:
				return getStringFeature();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__UNCHANGEABLE_STRING_FEATURE:
				return getUnchangeableStringFeature();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__DERIVED_STRING_FEATURE:
				return getDerivedStringFeature();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__REFERENCE_TO_CLASS_WITH_NAME:
				if (resolve) return getReferenceToClassWithName();
				return basicGetReferenceToClassWithName();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__MULTI_REFERENCE_FEATURE:
				return getMultiReferenceFeature();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES:
				if (coreType) return getFeatureMapEntries();
				return ((FeatureMap.Internal)getFeatureMapEntries()).getWrapper();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES1:
				return getFeatureMapEntries1();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES2:
				return getFeatureMapEntries2();
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
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BOOLEAN_FEATURE:
				setBooleanFeature((Boolean)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BOOLEAN_OBJECT_FEATURE:
				setBooleanObjectFeature((Boolean)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BOOLEAN_DATA_TYPE_FEATURE:
				setBooleanDataTypeFeature((Boolean)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BOOLEAN_PRIMITIVE_DATA_TYPE_FEATURE:
				setBooleanPrimitiveDataTypeFeature((Boolean)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__ENUM_FEATURE:
				setEnumFeature((EnumForControls)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__STRING_FEATURE:
				setStringFeature((String)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__DERIVED_STRING_FEATURE:
				setDerivedStringFeature((String)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__REFERENCE_TO_CLASS_WITH_NAME:
				setReferenceToClassWithName((ClassWithName)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__MULTI_REFERENCE_FEATURE:
				getMultiReferenceFeature().clear();
				getMultiReferenceFeature().addAll((Collection<? extends ClassWithName>)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES:
				((FeatureMap.Internal)getFeatureMapEntries()).set(newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES1:
				getFeatureMapEntries1().clear();
				getFeatureMapEntries1().addAll((Collection<? extends ClassForFeatureMapEntry1>)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES2:
				getFeatureMapEntries2().clear();
				getFeatureMapEntries2().addAll((Collection<? extends ClassForFeatureMapEntry2>)newValue);
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
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BOOLEAN_FEATURE:
				setBooleanFeature(BOOLEAN_FEATURE_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BOOLEAN_OBJECT_FEATURE:
				setBooleanObjectFeature(BOOLEAN_OBJECT_FEATURE_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BOOLEAN_DATA_TYPE_FEATURE:
				setBooleanDataTypeFeature(BOOLEAN_DATA_TYPE_FEATURE_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BOOLEAN_PRIMITIVE_DATA_TYPE_FEATURE:
				setBooleanPrimitiveDataTypeFeature(BOOLEAN_PRIMITIVE_DATA_TYPE_FEATURE_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__ENUM_FEATURE:
				setEnumFeature(ENUM_FEATURE_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__STRING_FEATURE:
				setStringFeature(STRING_FEATURE_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__DERIVED_STRING_FEATURE:
				setDerivedStringFeature(DERIVED_STRING_FEATURE_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__REFERENCE_TO_CLASS_WITH_NAME:
				setReferenceToClassWithName((ClassWithName)null);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__MULTI_REFERENCE_FEATURE:
				getMultiReferenceFeature().clear();
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES:
				getFeatureMapEntries().clear();
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES1:
				getFeatureMapEntries1().clear();
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES2:
				getFeatureMapEntries2().clear();
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
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BOOLEAN_FEATURE:
				return booleanFeature != BOOLEAN_FEATURE_EDEFAULT;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BOOLEAN_OBJECT_FEATURE:
				return BOOLEAN_OBJECT_FEATURE_EDEFAULT == null ? booleanObjectFeature != null : !BOOLEAN_OBJECT_FEATURE_EDEFAULT.equals(booleanObjectFeature);
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BOOLEAN_DATA_TYPE_FEATURE:
				return BOOLEAN_DATA_TYPE_FEATURE_EDEFAULT == null ? booleanDataTypeFeature != null : !BOOLEAN_DATA_TYPE_FEATURE_EDEFAULT.equals(booleanDataTypeFeature);
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BOOLEAN_PRIMITIVE_DATA_TYPE_FEATURE:
				return booleanPrimitiveDataTypeFeature != BOOLEAN_PRIMITIVE_DATA_TYPE_FEATURE_EDEFAULT;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__ENUM_FEATURE:
				return enumFeature != ENUM_FEATURE_EDEFAULT;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__STRING_FEATURE:
				return STRING_FEATURE_EDEFAULT == null ? stringFeature != null : !STRING_FEATURE_EDEFAULT.equals(stringFeature);
			case TestmodelsPackage.CLASS_FOR_CONTROLS__UNCHANGEABLE_STRING_FEATURE:
				return UNCHANGEABLE_STRING_FEATURE_EDEFAULT == null ? unchangeableStringFeature != null : !UNCHANGEABLE_STRING_FEATURE_EDEFAULT.equals(unchangeableStringFeature);
			case TestmodelsPackage.CLASS_FOR_CONTROLS__DERIVED_STRING_FEATURE:
				return DERIVED_STRING_FEATURE_EDEFAULT == null ? derivedStringFeature != null : !DERIVED_STRING_FEATURE_EDEFAULT.equals(derivedStringFeature);
			case TestmodelsPackage.CLASS_FOR_CONTROLS__REFERENCE_TO_CLASS_WITH_NAME:
				return referenceToClassWithName != null;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__MULTI_REFERENCE_FEATURE:
				return multiReferenceFeature != null && !multiReferenceFeature.isEmpty();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES:
				return featureMapEntries != null && !featureMapEntries.isEmpty();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES1:
				return !getFeatureMapEntries1().isEmpty();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__FEATURE_MAP_ENTRIES2:
				return !getFeatureMapEntries2().isEmpty();
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
		result.append(" (booleanFeature: ");
		result.append(booleanFeature);
		result.append(", booleanObjectFeature: ");
		result.append(booleanObjectFeature);
		result.append(", booleanDataTypeFeature: ");
		result.append(booleanDataTypeFeature);
		result.append(", booleanPrimitiveDataTypeFeature: ");
		result.append(booleanPrimitiveDataTypeFeature);
		result.append(", enumFeature: ");
		result.append(enumFeature);
		result.append(", stringFeature: ");
		result.append(stringFeature);
		result.append(", unchangeableStringFeature: ");
		result.append(unchangeableStringFeature);
		result.append(", derivedStringFeature: ");
		result.append(derivedStringFeature);
		result.append(", featureMapEntries: ");
		result.append(featureMapEntries);
		result.append(')');
		return result.toString();
	}

} //ClassForControlsImpl
