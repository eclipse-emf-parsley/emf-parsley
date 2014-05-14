/**
 */
package org.eclipse.emf.parsley.examples.cdo.company.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.internal.cdo.CDOObjectImpl;

import org.eclipse.emf.parsley.examples.cdo.company.Addressable;
import org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Addressable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.impl.AddressableImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.impl.AddressableImpl#getStreet <em>Street</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.impl.AddressableImpl#getCity <em>City</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AddressableImpl extends CDOObjectImpl implements Addressable {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AddressableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CompanyPackage.Literals.ADDRESSABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return (String)eGet(CompanyPackage.Literals.ADDRESSABLE__NAME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		eSet(CompanyPackage.Literals.ADDRESSABLE__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStreet() {
		return (String)eGet(CompanyPackage.Literals.ADDRESSABLE__STREET, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStreet(String newStreet) {
		eSet(CompanyPackage.Literals.ADDRESSABLE__STREET, newStreet);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCity() {
		return (String)eGet(CompanyPackage.Literals.ADDRESSABLE__CITY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCity(String newCity) {
		eSet(CompanyPackage.Literals.ADDRESSABLE__CITY, newCity);
	}

} //AddressableImpl
