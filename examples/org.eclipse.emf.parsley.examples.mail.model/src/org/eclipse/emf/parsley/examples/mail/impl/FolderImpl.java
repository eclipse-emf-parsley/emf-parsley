/**
 */
package org.eclipse.emf.parsley.examples.mail.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.parsley.examples.mail.Folder;
import org.eclipse.emf.parsley.examples.mail.Mail;
import org.eclipse.emf.parsley.examples.mail.MailPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Folder</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.examples.mail.impl.FolderImpl#getSubfolders <em>Subfolders</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.mail.impl.FolderImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.mail.impl.FolderImpl#getMails <em>Mails</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FolderImpl extends FolderContentImpl implements Folder {
	/**
	 * The cached value of the '{@link #getSubfolders() <em>Subfolders</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubfolders()
	 * @generated
	 * @ordered
	 */
	protected EList<Folder> subfolders;

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
	 * The cached value of the '{@link #getMails() <em>Mails</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMails()
	 * @generated
	 * @ordered
	 */
	protected EList<Mail> mails;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FolderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MailPackage.Literals.FOLDER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Folder> getSubfolders() {
		if (subfolders == null) {
			subfolders = new EObjectContainmentEList<Folder>(Folder.class, this, MailPackage.FOLDER__SUBFOLDERS);
		}
		return subfolders;
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
			eNotify(new ENotificationImpl(this, Notification.SET, MailPackage.FOLDER__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Mail> getMails() {
		if (mails == null) {
			mails = new EObjectContainmentEList<Mail>(Mail.class, this, MailPackage.FOLDER__MAILS);
		}
		return mails;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MailPackage.FOLDER__SUBFOLDERS:
				return ((InternalEList<?>)getSubfolders()).basicRemove(otherEnd, msgs);
			case MailPackage.FOLDER__MAILS:
				return ((InternalEList<?>)getMails()).basicRemove(otherEnd, msgs);
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
			case MailPackage.FOLDER__SUBFOLDERS:
				return getSubfolders();
			case MailPackage.FOLDER__NAME:
				return getName();
			case MailPackage.FOLDER__MAILS:
				return getMails();
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
			case MailPackage.FOLDER__SUBFOLDERS:
				getSubfolders().clear();
				getSubfolders().addAll((Collection<? extends Folder>)newValue);
				return;
			case MailPackage.FOLDER__NAME:
				setName((String)newValue);
				return;
			case MailPackage.FOLDER__MAILS:
				getMails().clear();
				getMails().addAll((Collection<? extends Mail>)newValue);
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
			case MailPackage.FOLDER__SUBFOLDERS:
				getSubfolders().clear();
				return;
			case MailPackage.FOLDER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case MailPackage.FOLDER__MAILS:
				getMails().clear();
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
			case MailPackage.FOLDER__SUBFOLDERS:
				return subfolders != null && !subfolders.isEmpty();
			case MailPackage.FOLDER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case MailPackage.FOLDER__MAILS:
				return mails != null && !mails.isEmpty();
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
		result.append(')');
		return result.toString();
	}

} //FolderImpl
