/**
 */
package org.eclipse.emf.parsley.tests.models.testmodels.impl;

import java.math.BigDecimal;
import java.util.Date;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.parsley.tests.models.testmodels.ClassForCompare;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class For Compare</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForCompareImpl#getStringAttribute <em>String Attribute</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForCompareImpl#getIntAttribute <em>Int Attribute</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForCompareImpl#getBigDecimalAttribute <em>Big Decimal Attribute</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForCompareImpl#getDateAttribute <em>Date Attribute</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ClassForCompareImpl extends MinimalEObjectImpl.Container implements ClassForCompare {
	/**
	 * The default value of the '{@link #getStringAttribute() <em>String Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStringAttribute()
	 * @generated
	 * @ordered
	 */
	protected static final String STRING_ATTRIBUTE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStringAttribute() <em>String Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStringAttribute()
	 * @generated
	 * @ordered
	 */
	protected String stringAttribute = STRING_ATTRIBUTE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIntAttribute() <em>Int Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntAttribute()
	 * @generated
	 * @ordered
	 */
	protected static final int INT_ATTRIBUTE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getIntAttribute() <em>Int Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntAttribute()
	 * @generated
	 * @ordered
	 */
	protected int intAttribute = INT_ATTRIBUTE_EDEFAULT;

	/**
	 * The default value of the '{@link #getBigDecimalAttribute() <em>Big Decimal Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBigDecimalAttribute()
	 * @generated
	 * @ordered
	 */
	protected static final BigDecimal BIG_DECIMAL_ATTRIBUTE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBigDecimalAttribute() <em>Big Decimal Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBigDecimalAttribute()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal bigDecimalAttribute = BIG_DECIMAL_ATTRIBUTE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDateAttribute() <em>Date Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDateAttribute()
	 * @generated
	 * @ordered
	 */
	protected static final Date DATE_ATTRIBUTE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDateAttribute() <em>Date Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDateAttribute()
	 * @generated
	 * @ordered
	 */
	protected Date dateAttribute = DATE_ATTRIBUTE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassForCompareImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestmodelsPackage.Literals.CLASS_FOR_COMPARE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStringAttribute() {
		return stringAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStringAttribute(String newStringAttribute) {
		String oldStringAttribute = stringAttribute;
		stringAttribute = newStringAttribute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_COMPARE__STRING_ATTRIBUTE, oldStringAttribute, stringAttribute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getIntAttribute() {
		return intAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIntAttribute(int newIntAttribute) {
		int oldIntAttribute = intAttribute;
		intAttribute = newIntAttribute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_COMPARE__INT_ATTRIBUTE, oldIntAttribute, intAttribute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getBigDecimalAttribute() {
		return bigDecimalAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBigDecimalAttribute(BigDecimal newBigDecimalAttribute) {
		BigDecimal oldBigDecimalAttribute = bigDecimalAttribute;
		bigDecimalAttribute = newBigDecimalAttribute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_COMPARE__BIG_DECIMAL_ATTRIBUTE, oldBigDecimalAttribute, bigDecimalAttribute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getDateAttribute() {
		return dateAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDateAttribute(Date newDateAttribute) {
		Date oldDateAttribute = dateAttribute;
		dateAttribute = newDateAttribute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_COMPARE__DATE_ATTRIBUTE, oldDateAttribute, dateAttribute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestmodelsPackage.CLASS_FOR_COMPARE__STRING_ATTRIBUTE:
				return getStringAttribute();
			case TestmodelsPackage.CLASS_FOR_COMPARE__INT_ATTRIBUTE:
				return getIntAttribute();
			case TestmodelsPackage.CLASS_FOR_COMPARE__BIG_DECIMAL_ATTRIBUTE:
				return getBigDecimalAttribute();
			case TestmodelsPackage.CLASS_FOR_COMPARE__DATE_ATTRIBUTE:
				return getDateAttribute();
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
			case TestmodelsPackage.CLASS_FOR_COMPARE__STRING_ATTRIBUTE:
				setStringAttribute((String)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_COMPARE__INT_ATTRIBUTE:
				setIntAttribute((Integer)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_COMPARE__BIG_DECIMAL_ATTRIBUTE:
				setBigDecimalAttribute((BigDecimal)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_COMPARE__DATE_ATTRIBUTE:
				setDateAttribute((Date)newValue);
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
			case TestmodelsPackage.CLASS_FOR_COMPARE__STRING_ATTRIBUTE:
				setStringAttribute(STRING_ATTRIBUTE_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_COMPARE__INT_ATTRIBUTE:
				setIntAttribute(INT_ATTRIBUTE_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_COMPARE__BIG_DECIMAL_ATTRIBUTE:
				setBigDecimalAttribute(BIG_DECIMAL_ATTRIBUTE_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_COMPARE__DATE_ATTRIBUTE:
				setDateAttribute(DATE_ATTRIBUTE_EDEFAULT);
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
			case TestmodelsPackage.CLASS_FOR_COMPARE__STRING_ATTRIBUTE:
				return STRING_ATTRIBUTE_EDEFAULT == null ? stringAttribute != null : !STRING_ATTRIBUTE_EDEFAULT.equals(stringAttribute);
			case TestmodelsPackage.CLASS_FOR_COMPARE__INT_ATTRIBUTE:
				return intAttribute != INT_ATTRIBUTE_EDEFAULT;
			case TestmodelsPackage.CLASS_FOR_COMPARE__BIG_DECIMAL_ATTRIBUTE:
				return BIG_DECIMAL_ATTRIBUTE_EDEFAULT == null ? bigDecimalAttribute != null : !BIG_DECIMAL_ATTRIBUTE_EDEFAULT.equals(bigDecimalAttribute);
			case TestmodelsPackage.CLASS_FOR_COMPARE__DATE_ATTRIBUTE:
				return DATE_ATTRIBUTE_EDEFAULT == null ? dateAttribute != null : !DATE_ATTRIBUTE_EDEFAULT.equals(dateAttribute);
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
		result.append(" (stringAttribute: ");
		result.append(stringAttribute);
		result.append(", intAttribute: ");
		result.append(intAttribute);
		result.append(", bigDecimalAttribute: ");
		result.append(bigDecimalAttribute);
		result.append(", dateAttribute: ");
		result.append(dateAttribute);
		result.append(')');
		return result.toString();
	}

} //ClassForCompareImpl
