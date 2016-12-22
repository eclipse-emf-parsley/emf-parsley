/**
 */
package org.eclipse.emf.parsley.tests.models.testmodels.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.parsley.tests.models.testmodels.ClassForDefaultValidation;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class For Default Validation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForDefaultValidationImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForDefaultValidationImpl#getNotEmpty <em>Not Empty</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForDefaultValidationImpl#getCanBeEmpty <em>Can Be Empty</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.ClassForDefaultValidationImpl#getNotNullReference <em>Not Null Reference</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ClassForDefaultValidationImpl extends MinimalEObjectImpl.Container implements ClassForDefaultValidation {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

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
	 * The default value of the '{@link #getCanBeEmpty() <em>Can Be Empty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCanBeEmpty()
	 * @generated
	 * @ordered
	 */
	protected static final String CAN_BE_EMPTY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCanBeEmpty() <em>Can Be Empty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCanBeEmpty()
	 * @generated
	 * @ordered
	 */
	protected String canBeEmpty = CAN_BE_EMPTY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getNotNullReference() <em>Not Null Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotNullReference()
	 * @generated
	 * @ordered
	 */
	protected ClassWithName notNullReference;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassForDefaultValidationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestmodelsPackage.Literals.CLASS_FOR_DEFAULT_VALIDATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_DEFAULT_VALIDATION__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_DEFAULT_VALIDATION__NOT_EMPTY, oldNotEmpty, notEmpty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCanBeEmpty() {
		return canBeEmpty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCanBeEmpty(String newCanBeEmpty) {
		String oldCanBeEmpty = canBeEmpty;
		canBeEmpty = newCanBeEmpty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_DEFAULT_VALIDATION__CAN_BE_EMPTY, oldCanBeEmpty, canBeEmpty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassWithName getNotNullReference() {
		if (notNullReference != null && notNullReference.eIsProxy()) {
			InternalEObject oldNotNullReference = (InternalEObject)notNullReference;
			notNullReference = (ClassWithName)eResolveProxy(oldNotNullReference);
			if (notNullReference != oldNotNullReference) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TestmodelsPackage.CLASS_FOR_DEFAULT_VALIDATION__NOT_NULL_REFERENCE, oldNotNullReference, notNullReference));
			}
		}
		return notNullReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassWithName basicGetNotNullReference() {
		return notNullReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNotNullReference(ClassWithName newNotNullReference) {
		ClassWithName oldNotNullReference = notNullReference;
		notNullReference = newNotNullReference;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.CLASS_FOR_DEFAULT_VALIDATION__NOT_NULL_REFERENCE, oldNotNullReference, notNullReference));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestmodelsPackage.CLASS_FOR_DEFAULT_VALIDATION__NAME:
				return getName();
			case TestmodelsPackage.CLASS_FOR_DEFAULT_VALIDATION__NOT_EMPTY:
				return getNotEmpty();
			case TestmodelsPackage.CLASS_FOR_DEFAULT_VALIDATION__CAN_BE_EMPTY:
				return getCanBeEmpty();
			case TestmodelsPackage.CLASS_FOR_DEFAULT_VALIDATION__NOT_NULL_REFERENCE:
				if (resolve) return getNotNullReference();
				return basicGetNotNullReference();
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
			case TestmodelsPackage.CLASS_FOR_DEFAULT_VALIDATION__NAME:
				setName((String)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_DEFAULT_VALIDATION__NOT_EMPTY:
				setNotEmpty((String)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_DEFAULT_VALIDATION__CAN_BE_EMPTY:
				setCanBeEmpty((String)newValue);
				return;
			case TestmodelsPackage.CLASS_FOR_DEFAULT_VALIDATION__NOT_NULL_REFERENCE:
				setNotNullReference((ClassWithName)newValue);
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
			case TestmodelsPackage.CLASS_FOR_DEFAULT_VALIDATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_DEFAULT_VALIDATION__NOT_EMPTY:
				setNotEmpty(NOT_EMPTY_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_DEFAULT_VALIDATION__CAN_BE_EMPTY:
				setCanBeEmpty(CAN_BE_EMPTY_EDEFAULT);
				return;
			case TestmodelsPackage.CLASS_FOR_DEFAULT_VALIDATION__NOT_NULL_REFERENCE:
				setNotNullReference((ClassWithName)null);
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
			case TestmodelsPackage.CLASS_FOR_DEFAULT_VALIDATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case TestmodelsPackage.CLASS_FOR_DEFAULT_VALIDATION__NOT_EMPTY:
				return NOT_EMPTY_EDEFAULT == null ? notEmpty != null : !NOT_EMPTY_EDEFAULT.equals(notEmpty);
			case TestmodelsPackage.CLASS_FOR_DEFAULT_VALIDATION__CAN_BE_EMPTY:
				return CAN_BE_EMPTY_EDEFAULT == null ? canBeEmpty != null : !CAN_BE_EMPTY_EDEFAULT.equals(canBeEmpty);
			case TestmodelsPackage.CLASS_FOR_DEFAULT_VALIDATION__NOT_NULL_REFERENCE:
				return notNullReference != null;
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
		result.append(" (name: ");
		result.append(name);
		result.append(", notEmpty: ");
		result.append(notEmpty);
		result.append(", canBeEmpty: ");
		result.append(canBeEmpty);
		result.append(')');
		return result.toString();
	}

} //ClassForDefaultValidationImpl
