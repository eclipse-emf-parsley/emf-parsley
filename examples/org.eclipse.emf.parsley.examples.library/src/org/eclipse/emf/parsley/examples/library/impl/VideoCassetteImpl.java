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
 * $Id: VideoCassetteImpl.java,v 1.4 2007/02/20 17:41:55 emerks Exp $
 */
package org.eclipse.emf.parsley.examples.library.impl;



import java.util.Collection;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage;
import org.eclipse.emf.parsley.examples.library.Person;
import org.eclipse.emf.parsley.examples.library.VideoCassette;



/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Video Cassette</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.examples.library.impl.VideoCassetteImpl#getCast <em>Cast</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VideoCassetteImpl extends AudioVisualItemImpl implements VideoCassette
{
  /**
	 * The cached value of the '{@link #getCast() <em>Cast</em>}' reference list.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getCast()
	 * @generated
	 * @ordered
	 */
  protected EList<Person> cast;

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected VideoCassetteImpl()
  {
		super();
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  protected EClass eStaticClass()
  {
		return EXTLibraryPackage.Literals.VIDEO_CASSETTE;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EList<Person> getCast()
  {
		if (cast == null) {
			cast = new EObjectResolvingEList<Person>(Person.class, this, EXTLibraryPackage.VIDEO_CASSETTE__CAST);
		}
		return cast;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
		switch (featureID) {
			case EXTLibraryPackage.VIDEO_CASSETTE__CAST:
				return getCast();
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
  public void eSet(int featureID, Object newValue)
  {
		switch (featureID) {
			case EXTLibraryPackage.VIDEO_CASSETTE__CAST:
				getCast().clear();
				getCast().addAll((Collection<? extends Person>)newValue);
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
  public void eUnset(int featureID)
  {
		switch (featureID) {
			case EXTLibraryPackage.VIDEO_CASSETTE__CAST:
				getCast().clear();
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
  public boolean eIsSet(int featureID)
  {
		switch (featureID) {
			case EXTLibraryPackage.VIDEO_CASSETTE__CAST:
				return cast != null && !cast.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //VideoCassetteImpl
