/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
/**
 */
package org.eclipse.emf.parsley.examples.mail;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Folder</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.examples.mail.Folder#getSubfolders <em>Subfolders</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.mail.Folder#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.mail.Folder#getMails <em>Mails</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.parsley.examples.mail.MailPackage#getFolder()
 * @model
 * @generated
 */
public interface Folder extends FolderContent {
	/**
	 * Returns the value of the '<em><b>Subfolders</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.parsley.examples.mail.Folder}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subfolders</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subfolders</em>' containment reference list.
	 * @see org.eclipse.emf.parsley.examples.mail.MailPackage#getFolder_Subfolders()
	 * @model containment="true"
	 * @generated
	 */
	EList<Folder> getSubfolders();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.emf.parsley.examples.mail.MailPackage#getFolder_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.examples.mail.Folder#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Mails</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.parsley.examples.mail.Mail}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mails</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mails</em>' containment reference list.
	 * @see org.eclipse.emf.parsley.examples.mail.MailPackage#getFolder_Mails()
	 * @model containment="true"
	 * @generated
	 */
	EList<Mail> getMails();

} // Folder
