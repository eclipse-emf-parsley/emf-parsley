/**
 */
package org.eclipse.emf.parsley.examples.mail;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mail</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.examples.mail.Mail#getFrom <em>From</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.mail.Mail#getRecipients <em>Recipients</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.mail.Mail#getMessage <em>Message</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.mail.Mail#getSubject <em>Subject</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.parsley.examples.mail.MailPackage#getMail()
 * @model
 * @generated
 */
public interface Mail extends EObject {
	/**
	 * Returns the value of the '<em><b>From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' attribute.
	 * @see #setFrom(String)
	 * @see org.eclipse.emf.parsley.examples.mail.MailPackage#getMail_From()
	 * @model
	 * @generated
	 */
	String getFrom();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.examples.mail.Mail#getFrom <em>From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' attribute.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(String value);

	/**
	 * Returns the value of the '<em><b>Recipients</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Recipients</em>' attribute list.
	 * @see org.eclipse.emf.parsley.examples.mail.MailPackage#getMail_Recipients()
	 * @model
	 * @generated
	 */
	EList<String> getRecipients();

	/**
	 * Returns the value of the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message</em>' attribute.
	 * @see #setMessage(String)
	 * @see org.eclipse.emf.parsley.examples.mail.MailPackage#getMail_Message()
	 * @model
	 * @generated
	 */
	String getMessage();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.examples.mail.Mail#getMessage <em>Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message</em>' attribute.
	 * @see #getMessage()
	 * @generated
	 */
	void setMessage(String value);

	/**
	 * Returns the value of the '<em><b>Subject</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subject</em>' attribute.
	 * @see #setSubject(String)
	 * @see org.eclipse.emf.parsley.examples.mail.MailPackage#getMail_Subject()
	 * @model
	 * @generated
	 */
	String getSubject();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.examples.mail.Mail#getSubject <em>Subject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subject</em>' attribute.
	 * @see #getSubject()
	 * @generated
	 */
	void setSubject(String value);

} // Mail
