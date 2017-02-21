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
 * $Id: Writer.java,v 1.4 2008/05/04 17:03:17 emerks Exp $
 */
package org.eclipse.emf.parsley.examples.library;


import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Writer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.examples.library.Writer#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.library.Writer#getBooks <em>Books</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.parsley.examples.library.EXTLibraryPackage#getWriter()
 * @model
 * @generated
 */
public interface Writer extends Person
{
  /**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.emf.parsley.examples.library.EXTLibraryPackage#getWriter_Name()
	 * @model transient="true" volatile="true"
	 * @generated
	 */
  String getName();

  /**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.examples.library.Writer#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
  void setName(String value);

  /**
	 * Returns the value of the '<em><b>Books</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.parsley.examples.library.Book}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.parsley.examples.library.Book#getAuthor <em>Author</em>}'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Books</em>' reference list.
	 * @see org.eclipse.emf.parsley.examples.library.EXTLibraryPackage#getWriter_Books()
	 * @see org.eclipse.emf.parsley.examples.library.Book#getAuthor
	 * @model opposite="author"
	 * @generated
	 */
  EList<Book> getBooks();

} // Writer
