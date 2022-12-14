/**
 * <copyright>
 *
 * Copyright (c) 2005, 2013 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: Borrower.java,v 1.4 2008/05/04 17:03:18 emerks Exp $
 */
package org.eclipse.emf.parsley.examples.library;


import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Borrower</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.examples.library.Borrower#getBorrowed <em>Borrowed</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.parsley.examples.library.EXTLibraryPackage#getBorrower()
 * @model
 * @generated
 */
public interface Borrower extends Person
{
  /**
	 * Returns the value of the '<em><b>Borrowed</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.parsley.examples.library.Lendable}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.parsley.examples.library.Lendable#getBorrowers <em>Borrowers</em>}'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Borrowed</em>' reference list.
	 * @see org.eclipse.emf.parsley.examples.library.EXTLibraryPackage#getBorrower_Borrowed()
	 * @see org.eclipse.emf.parsley.examples.library.Lendable#getBorrowers
	 * @model opposite="borrowers"
	 * @generated
	 */
  EList<Lendable> getBorrowed();

} // Borrower
