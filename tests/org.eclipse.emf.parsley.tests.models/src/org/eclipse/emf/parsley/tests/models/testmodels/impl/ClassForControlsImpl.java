/**
 */
package org.eclipse.emf.parsley.tests.models.testmodels.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
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
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#isBooleanFeature <em>Boolean Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getBooleanObjectFeature <em>Boolean Object Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getBooleanDataTypeFeature <em>Boolean Data Type Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#isBooleanPrimitiveDataTypeFeature <em>Boolean Primitive Data Type Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getEnumFeature <em>Enum Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getStringFeature <em>String Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getStringDataTypeFeature <em>String Data Type Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getStringFeatureWithDefault <em>String Feature With Default</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getUnserializableStringDataTypeFeature <em>Unserializable String Data Type Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getUnchangeableStringFeature <em>Unchangeable String Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getDerivedStringFeature <em>Derived String Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getReferenceToClassWithName <em>Reference To Class With Name</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getMultiReferenceFeature <em>Multi Reference Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getFeatureMapEntries <em>Feature Map Entries</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getFeatureMapEntries1 <em>Feature Map Entries1</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getFeatureMapEntries2 <em>Feature Map Entries2</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getSingleContainmentReference <em>Single Containment Reference</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getBigDecimalFeature <em>Big Decimal Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getBigIntegerFeature <em>Big Integer Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getDoubleFeature <em>Double Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getDoubleObjectFeature <em>Double Object Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getIntFeature <em>Int Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getIntObjectFeature <em>Int Object Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getFloatFeature <em>Float Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getFloatObjectFeature <em>Float Object Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getShortFeature <em>Short Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getShortObjectFeature <em>Short Object Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getByteFeature <em>Byte Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getByteObjectFeature <em>Byte Object Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForControlsImpl#getDateFeature <em>Date Feature</em>}</li>
 * </ul>
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
	 * The default value of the '{@link #getStringDataTypeFeature() <em>String Data Type Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStringDataTypeFeature()
	 * @generated
	 * @ordered
	 */
	protected static final String STRING_DATA_TYPE_FEATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStringDataTypeFeature() <em>String Data Type Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStringDataTypeFeature()
	 * @generated
	 * @ordered
	 */
	protected String stringDataTypeFeature = STRING_DATA_TYPE_FEATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getStringFeatureWithDefault() <em>String Feature With Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStringFeatureWithDefault()
	 * @generated
	 * @ordered
	 */
	protected static final String STRING_FEATURE_WITH_DEFAULT_EDEFAULT = "default";

	/**
	 * The cached value of the '{@link #getStringFeatureWithDefault() <em>String Feature With Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStringFeatureWithDefault()
	 * @generated
	 * @ordered
	 */
	protected String stringFeatureWithDefault = STRING_FEATURE_WITH_DEFAULT_EDEFAULT;

	/**
	 * The default value of the '{@link #getUnserializableStringDataTypeFeature() <em>Unserializable String Data Type Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnserializableStringDataTypeFeature()
	 * @generated
	 * @ordered
	 */
	protected static final String UNSERIALIZABLE_STRING_DATA_TYPE_FEATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUnserializableStringDataTypeFeature() <em>Unserializable String Data Type Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnserializableStringDataTypeFeature()
	 * @generated
	 * @ordered
	 */
	protected String unserializableStringDataTypeFeature = UNSERIALIZABLE_STRING_DATA_TYPE_FEATURE_EDEFAULT;

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
	 * The cached value of the '{@link #getSingleContainmentReference() <em>Single Containment Reference</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSingleContainmentReference()
	 * @generated
	 * @ordered
	 */
	protected ClassWithName singleContainmentReference;

	/**
	 * The default value of the '{@link #getBigDecimalFeature() <em>Big Decimal Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBigDecimalFeature()
	 * @generated
	 * @ordered
	 */
	protected static final BigDecimal BIG_DECIMAL_FEATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBigDecimalFeature() <em>Big Decimal Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBigDecimalFeature()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal bigDecimalFeature = BIG_DECIMAL_FEATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getBigIntegerFeature() <em>Big Integer Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBigIntegerFeature()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger BIG_INTEGER_FEATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBigIntegerFeature() <em>Big Integer Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBigIntegerFeature()
	 * @generated
	 * @ordered
	 */
	protected BigInteger bigIntegerFeature = BIG_INTEGER_FEATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDoubleFeature() <em>Double Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDoubleFeature()
	 * @generated
	 * @ordered
	 */
	protected static final double DOUBLE_FEATURE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getDoubleFeature() <em>Double Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDoubleFeature()
	 * @generated
	 * @ordered
	 */
	protected double doubleFeature = DOUBLE_FEATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDoubleObjectFeature() <em>Double Object Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDoubleObjectFeature()
	 * @generated
	 * @ordered
	 */
	protected static final Double DOUBLE_OBJECT_FEATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDoubleObjectFeature() <em>Double Object Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDoubleObjectFeature()
	 * @generated
	 * @ordered
	 */
	protected Double doubleObjectFeature = DOUBLE_OBJECT_FEATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIntFeature() <em>Int Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntFeature()
	 * @generated
	 * @ordered
	 */
	protected static final int INT_FEATURE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getIntFeature() <em>Int Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntFeature()
	 * @generated
	 * @ordered
	 */
	protected int intFeature = INT_FEATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIntObjectFeature() <em>Int Object Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntObjectFeature()
	 * @generated
	 * @ordered
	 */
	protected static final Integer INT_OBJECT_FEATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIntObjectFeature() <em>Int Object Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntObjectFeature()
	 * @generated
	 * @ordered
	 */
	protected Integer intObjectFeature = INT_OBJECT_FEATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFloatFeature() <em>Float Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFloatFeature()
	 * @generated
	 * @ordered
	 */
	protected static final float FLOAT_FEATURE_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getFloatFeature() <em>Float Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFloatFeature()
	 * @generated
	 * @ordered
	 */
	protected float floatFeature = FLOAT_FEATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFloatObjectFeature() <em>Float Object Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFloatObjectFeature()
	 * @generated
	 * @ordered
	 */
	protected static final Float FLOAT_OBJECT_FEATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFloatObjectFeature() <em>Float Object Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFloatObjectFeature()
	 * @generated
	 * @ordered
	 */
	protected Float floatObjectFeature = FLOAT_OBJECT_FEATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getShortFeature() <em>Short Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortFeature()
	 * @generated
	 * @ordered
	 */
	protected static final short SHORT_FEATURE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getShortFeature() <em>Short Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortFeature()
	 * @generated
	 * @ordered
	 */
	protected short shortFeature = SHORT_FEATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getShortObjectFeature() <em>Short Object Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortObjectFeature()
	 * @generated
	 * @ordered
	 */
	protected static final Short SHORT_OBJECT_FEATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getShortObjectFeature() <em>Short Object Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortObjectFeature()
	 * @generated
	 * @ordered
	 */
	protected Short shortObjectFeature = SHORT_OBJECT_FEATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getByteFeature() <em>Byte Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getByteFeature()
	 * @generated
	 * @ordered
	 */
	protected static final byte BYTE_FEATURE_EDEFAULT = 0x00;

	/**
	 * The cached value of the '{@link #getByteFeature() <em>Byte Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getByteFeature()
	 * @generated
	 * @ordered
	 */
	protected byte byteFeature = BYTE_FEATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getByteObjectFeature() <em>Byte Object Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getByteObjectFeature()
	 * @generated
	 * @ordered
	 */
	protected static final Byte BYTE_OBJECT_FEATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getByteObjectFeature() <em>Byte Object Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getByteObjectFeature()
	 * @generated
	 * @ordered
	 */
	protected Byte byteObjectFeature = BYTE_OBJECT_FEATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDateFeature() <em>Date Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDateFeature()
	 * @generated
	 * @ordered
	 */
	protected static final Date DATE_FEATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDateFeature() <em>Date Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDateFeature()
	 * @generated
	 * @ordered
	 */
	protected Date dateFeature = DATE_FEATURE_EDEFAULT;

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
	public String getStringDataTypeFeature() {
		return stringDataTypeFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStringDataTypeFeature(String newStringDataTypeFeature) {
		String oldStringDataTypeFeature = stringDataTypeFeature;
		stringDataTypeFeature = newStringDataTypeFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__STRING_DATA_TYPE_FEATURE, oldStringDataTypeFeature, stringDataTypeFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStringFeatureWithDefault() {
		return stringFeatureWithDefault;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStringFeatureWithDefault(String newStringFeatureWithDefault) {
		String oldStringFeatureWithDefault = stringFeatureWithDefault;
		stringFeatureWithDefault = newStringFeatureWithDefault;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__STRING_FEATURE_WITH_DEFAULT, oldStringFeatureWithDefault, stringFeatureWithDefault));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUnserializableStringDataTypeFeature() {
		return unserializableStringDataTypeFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnserializableStringDataTypeFeature(String newUnserializableStringDataTypeFeature) {
		String oldUnserializableStringDataTypeFeature = unserializableStringDataTypeFeature;
		unserializableStringDataTypeFeature = newUnserializableStringDataTypeFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__UNSERIALIZABLE_STRING_DATA_TYPE_FEATURE, oldUnserializableStringDataTypeFeature, unserializableStringDataTypeFeature));
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
	public ClassWithName getSingleContainmentReference() {
		return singleContainmentReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSingleContainmentReference(ClassWithName newSingleContainmentReference, NotificationChain msgs) {
		ClassWithName oldSingleContainmentReference = singleContainmentReference;
		singleContainmentReference = newSingleContainmentReference;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__SINGLE_CONTAINMENT_REFERENCE, oldSingleContainmentReference, newSingleContainmentReference);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSingleContainmentReference(ClassWithName newSingleContainmentReference) {
		if (newSingleContainmentReference != singleContainmentReference) {
			NotificationChain msgs = null;
			if (singleContainmentReference != null)
				msgs = ((InternalEObject)singleContainmentReference).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TestmodelsPackage.CLASS_FOR_CONTROLS__SINGLE_CONTAINMENT_REFERENCE, null, msgs);
			if (newSingleContainmentReference != null)
				msgs = ((InternalEObject)newSingleContainmentReference).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TestmodelsPackage.CLASS_FOR_CONTROLS__SINGLE_CONTAINMENT_REFERENCE, null, msgs);
			msgs = basicSetSingleContainmentReference(newSingleContainmentReference, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__SINGLE_CONTAINMENT_REFERENCE, newSingleContainmentReference, newSingleContainmentReference));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getBigDecimalFeature() {
		return bigDecimalFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBigDecimalFeature(BigDecimal newBigDecimalFeature) {
		BigDecimal oldBigDecimalFeature = bigDecimalFeature;
		bigDecimalFeature = newBigDecimalFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__BIG_DECIMAL_FEATURE, oldBigDecimalFeature, bigDecimalFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getBigIntegerFeature() {
		return bigIntegerFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBigIntegerFeature(BigInteger newBigIntegerFeature) {
		BigInteger oldBigIntegerFeature = bigIntegerFeature;
		bigIntegerFeature = newBigIntegerFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__BIG_INTEGER_FEATURE, oldBigIntegerFeature, bigIntegerFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getDoubleFeature() {
		return doubleFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDoubleFeature(double newDoubleFeature) {
		double oldDoubleFeature = doubleFeature;
		doubleFeature = newDoubleFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__DOUBLE_FEATURE, oldDoubleFeature, doubleFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getDoubleObjectFeature() {
		return doubleObjectFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDoubleObjectFeature(Double newDoubleObjectFeature) {
		Double oldDoubleObjectFeature = doubleObjectFeature;
		doubleObjectFeature = newDoubleObjectFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__DOUBLE_OBJECT_FEATURE, oldDoubleObjectFeature, doubleObjectFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getIntFeature() {
		return intFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIntFeature(int newIntFeature) {
		int oldIntFeature = intFeature;
		intFeature = newIntFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__INT_FEATURE, oldIntFeature, intFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getIntObjectFeature() {
		return intObjectFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIntObjectFeature(Integer newIntObjectFeature) {
		Integer oldIntObjectFeature = intObjectFeature;
		intObjectFeature = newIntObjectFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__INT_OBJECT_FEATURE, oldIntObjectFeature, intObjectFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getFloatFeature() {
		return floatFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFloatFeature(float newFloatFeature) {
		float oldFloatFeature = floatFeature;
		floatFeature = newFloatFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__FLOAT_FEATURE, oldFloatFeature, floatFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Float getFloatObjectFeature() {
		return floatObjectFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFloatObjectFeature(Float newFloatObjectFeature) {
		Float oldFloatObjectFeature = floatObjectFeature;
		floatObjectFeature = newFloatObjectFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__FLOAT_OBJECT_FEATURE, oldFloatObjectFeature, floatObjectFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public short getShortFeature() {
		return shortFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShortFeature(short newShortFeature) {
		short oldShortFeature = shortFeature;
		shortFeature = newShortFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__SHORT_FEATURE, oldShortFeature, shortFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Short getShortObjectFeature() {
		return shortObjectFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShortObjectFeature(Short newShortObjectFeature) {
		Short oldShortObjectFeature = shortObjectFeature;
		shortObjectFeature = newShortObjectFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__SHORT_OBJECT_FEATURE, oldShortObjectFeature, shortObjectFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public byte getByteFeature() {
		return byteFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setByteFeature(byte newByteFeature) {
		byte oldByteFeature = byteFeature;
		byteFeature = newByteFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__BYTE_FEATURE, oldByteFeature, byteFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Byte getByteObjectFeature() {
		return byteObjectFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setByteObjectFeature(Byte newByteObjectFeature) {
		Byte oldByteObjectFeature = byteObjectFeature;
		byteObjectFeature = newByteObjectFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__BYTE_OBJECT_FEATURE, oldByteObjectFeature, byteObjectFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getDateFeature() {
		return dateFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDateFeature(Date newDateFeature) {
		Date oldDateFeature = dateFeature;
		dateFeature = newDateFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_CONTROLS__DATE_FEATURE, oldDateFeature, dateFeature));
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
			case TestmodelsPackage.CLASS_FOR_CONTROLS__SINGLE_CONTAINMENT_REFERENCE:
				return basicSetSingleContainmentReference(null, msgs);
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
			case TestmodelsPackage.CLASS_FOR_CONTROLS__STRING_DATA_TYPE_FEATURE:
				return getStringDataTypeFeature();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__STRING_FEATURE_WITH_DEFAULT:
				return getStringFeatureWithDefault();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__UNSERIALIZABLE_STRING_DATA_TYPE_FEATURE:
				return getUnserializableStringDataTypeFeature();
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
			case TestmodelsPackage.CLASS_FOR_CONTROLS__SINGLE_CONTAINMENT_REFERENCE:
				return getSingleContainmentReference();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BIG_DECIMAL_FEATURE:
				return getBigDecimalFeature();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BIG_INTEGER_FEATURE:
				return getBigIntegerFeature();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__DOUBLE_FEATURE:
				return getDoubleFeature();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__DOUBLE_OBJECT_FEATURE:
				return getDoubleObjectFeature();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__INT_FEATURE:
				return getIntFeature();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__INT_OBJECT_FEATURE:
				return getIntObjectFeature();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__FLOAT_FEATURE:
				return getFloatFeature();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__FLOAT_OBJECT_FEATURE:
				return getFloatObjectFeature();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__SHORT_FEATURE:
				return getShortFeature();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__SHORT_OBJECT_FEATURE:
				return getShortObjectFeature();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BYTE_FEATURE:
				return getByteFeature();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BYTE_OBJECT_FEATURE:
				return getByteObjectFeature();
			case TestmodelsPackage.CLASS_FOR_CONTROLS__DATE_FEATURE:
				return getDateFeature();
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
			case TestmodelsPackage.CLASS_FOR_CONTROLS__STRING_DATA_TYPE_FEATURE:
				setStringDataTypeFeature((String)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__STRING_FEATURE_WITH_DEFAULT:
				setStringFeatureWithDefault((String)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__UNSERIALIZABLE_STRING_DATA_TYPE_FEATURE:
				setUnserializableStringDataTypeFeature((String)newValue);
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
			case TestmodelsPackage.CLASS_FOR_CONTROLS__SINGLE_CONTAINMENT_REFERENCE:
				setSingleContainmentReference((ClassWithName)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BIG_DECIMAL_FEATURE:
				setBigDecimalFeature((BigDecimal)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BIG_INTEGER_FEATURE:
				setBigIntegerFeature((BigInteger)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__DOUBLE_FEATURE:
				setDoubleFeature((Double)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__DOUBLE_OBJECT_FEATURE:
				setDoubleObjectFeature((Double)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__INT_FEATURE:
				setIntFeature((Integer)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__INT_OBJECT_FEATURE:
				setIntObjectFeature((Integer)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__FLOAT_FEATURE:
				setFloatFeature((Float)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__FLOAT_OBJECT_FEATURE:
				setFloatObjectFeature((Float)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__SHORT_FEATURE:
				setShortFeature((Short)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__SHORT_OBJECT_FEATURE:
				setShortObjectFeature((Short)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BYTE_FEATURE:
				setByteFeature((Byte)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BYTE_OBJECT_FEATURE:
				setByteObjectFeature((Byte)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__DATE_FEATURE:
				setDateFeature((Date)newValue);
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
			case TestmodelsPackage.CLASS_FOR_CONTROLS__STRING_DATA_TYPE_FEATURE:
				setStringDataTypeFeature(STRING_DATA_TYPE_FEATURE_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__STRING_FEATURE_WITH_DEFAULT:
				setStringFeatureWithDefault(STRING_FEATURE_WITH_DEFAULT_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__UNSERIALIZABLE_STRING_DATA_TYPE_FEATURE:
				setUnserializableStringDataTypeFeature(UNSERIALIZABLE_STRING_DATA_TYPE_FEATURE_EDEFAULT);
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
			case TestmodelsPackage.CLASS_FOR_CONTROLS__SINGLE_CONTAINMENT_REFERENCE:
				setSingleContainmentReference((ClassWithName)null);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BIG_DECIMAL_FEATURE:
				setBigDecimalFeature(BIG_DECIMAL_FEATURE_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BIG_INTEGER_FEATURE:
				setBigIntegerFeature(BIG_INTEGER_FEATURE_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__DOUBLE_FEATURE:
				setDoubleFeature(DOUBLE_FEATURE_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__DOUBLE_OBJECT_FEATURE:
				setDoubleObjectFeature(DOUBLE_OBJECT_FEATURE_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__INT_FEATURE:
				setIntFeature(INT_FEATURE_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__INT_OBJECT_FEATURE:
				setIntObjectFeature(INT_OBJECT_FEATURE_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__FLOAT_FEATURE:
				setFloatFeature(FLOAT_FEATURE_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__FLOAT_OBJECT_FEATURE:
				setFloatObjectFeature(FLOAT_OBJECT_FEATURE_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__SHORT_FEATURE:
				setShortFeature(SHORT_FEATURE_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__SHORT_OBJECT_FEATURE:
				setShortObjectFeature(SHORT_OBJECT_FEATURE_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BYTE_FEATURE:
				setByteFeature(BYTE_FEATURE_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BYTE_OBJECT_FEATURE:
				setByteObjectFeature(BYTE_OBJECT_FEATURE_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__DATE_FEATURE:
				setDateFeature(DATE_FEATURE_EDEFAULT);
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
			case TestmodelsPackage.CLASS_FOR_CONTROLS__STRING_DATA_TYPE_FEATURE:
				return STRING_DATA_TYPE_FEATURE_EDEFAULT == null ? stringDataTypeFeature != null : !STRING_DATA_TYPE_FEATURE_EDEFAULT.equals(stringDataTypeFeature);
			case TestmodelsPackage.CLASS_FOR_CONTROLS__STRING_FEATURE_WITH_DEFAULT:
				return STRING_FEATURE_WITH_DEFAULT_EDEFAULT == null ? stringFeatureWithDefault != null : !STRING_FEATURE_WITH_DEFAULT_EDEFAULT.equals(stringFeatureWithDefault);
			case TestmodelsPackage.CLASS_FOR_CONTROLS__UNSERIALIZABLE_STRING_DATA_TYPE_FEATURE:
				return UNSERIALIZABLE_STRING_DATA_TYPE_FEATURE_EDEFAULT == null ? unserializableStringDataTypeFeature != null : !UNSERIALIZABLE_STRING_DATA_TYPE_FEATURE_EDEFAULT.equals(unserializableStringDataTypeFeature);
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
			case TestmodelsPackage.CLASS_FOR_CONTROLS__SINGLE_CONTAINMENT_REFERENCE:
				return singleContainmentReference != null;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BIG_DECIMAL_FEATURE:
				return BIG_DECIMAL_FEATURE_EDEFAULT == null ? bigDecimalFeature != null : !BIG_DECIMAL_FEATURE_EDEFAULT.equals(bigDecimalFeature);
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BIG_INTEGER_FEATURE:
				return BIG_INTEGER_FEATURE_EDEFAULT == null ? bigIntegerFeature != null : !BIG_INTEGER_FEATURE_EDEFAULT.equals(bigIntegerFeature);
			case TestmodelsPackage.CLASS_FOR_CONTROLS__DOUBLE_FEATURE:
				return doubleFeature != DOUBLE_FEATURE_EDEFAULT;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__DOUBLE_OBJECT_FEATURE:
				return DOUBLE_OBJECT_FEATURE_EDEFAULT == null ? doubleObjectFeature != null : !DOUBLE_OBJECT_FEATURE_EDEFAULT.equals(doubleObjectFeature);
			case TestmodelsPackage.CLASS_FOR_CONTROLS__INT_FEATURE:
				return intFeature != INT_FEATURE_EDEFAULT;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__INT_OBJECT_FEATURE:
				return INT_OBJECT_FEATURE_EDEFAULT == null ? intObjectFeature != null : !INT_OBJECT_FEATURE_EDEFAULT.equals(intObjectFeature);
			case TestmodelsPackage.CLASS_FOR_CONTROLS__FLOAT_FEATURE:
				return floatFeature != FLOAT_FEATURE_EDEFAULT;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__FLOAT_OBJECT_FEATURE:
				return FLOAT_OBJECT_FEATURE_EDEFAULT == null ? floatObjectFeature != null : !FLOAT_OBJECT_FEATURE_EDEFAULT.equals(floatObjectFeature);
			case TestmodelsPackage.CLASS_FOR_CONTROLS__SHORT_FEATURE:
				return shortFeature != SHORT_FEATURE_EDEFAULT;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__SHORT_OBJECT_FEATURE:
				return SHORT_OBJECT_FEATURE_EDEFAULT == null ? shortObjectFeature != null : !SHORT_OBJECT_FEATURE_EDEFAULT.equals(shortObjectFeature);
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BYTE_FEATURE:
				return byteFeature != BYTE_FEATURE_EDEFAULT;
			case TestmodelsPackage.CLASS_FOR_CONTROLS__BYTE_OBJECT_FEATURE:
				return BYTE_OBJECT_FEATURE_EDEFAULT == null ? byteObjectFeature != null : !BYTE_OBJECT_FEATURE_EDEFAULT.equals(byteObjectFeature);
			case TestmodelsPackage.CLASS_FOR_CONTROLS__DATE_FEATURE:
				return DATE_FEATURE_EDEFAULT == null ? dateFeature != null : !DATE_FEATURE_EDEFAULT.equals(dateFeature);
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
		result.append(", stringDataTypeFeature: ");
		result.append(stringDataTypeFeature);
		result.append(", stringFeatureWithDefault: ");
		result.append(stringFeatureWithDefault);
		result.append(", unserializableStringDataTypeFeature: ");
		result.append(unserializableStringDataTypeFeature);
		result.append(", unchangeableStringFeature: ");
		result.append(unchangeableStringFeature);
		result.append(", derivedStringFeature: ");
		result.append(derivedStringFeature);
		result.append(", featureMapEntries: ");
		result.append(featureMapEntries);
		result.append(", bigDecimalFeature: ");
		result.append(bigDecimalFeature);
		result.append(", bigIntegerFeature: ");
		result.append(bigIntegerFeature);
		result.append(", doubleFeature: ");
		result.append(doubleFeature);
		result.append(", doubleObjectFeature: ");
		result.append(doubleObjectFeature);
		result.append(", intFeature: ");
		result.append(intFeature);
		result.append(", intObjectFeature: ");
		result.append(intObjectFeature);
		result.append(", floatFeature: ");
		result.append(floatFeature);
		result.append(", floatObjectFeature: ");
		result.append(floatObjectFeature);
		result.append(", shortFeature: ");
		result.append(shortFeature);
		result.append(", shortObjectFeature: ");
		result.append(shortObjectFeature);
		result.append(", byteFeature: ");
		result.append(byteFeature);
		result.append(", byteObjectFeature: ");
		result.append(byteObjectFeature);
		result.append(", dateFeature: ");
		result.append(dateFeature);
		result.append(')');
		return result.toString();
	}

} //ClassForControlsImpl
