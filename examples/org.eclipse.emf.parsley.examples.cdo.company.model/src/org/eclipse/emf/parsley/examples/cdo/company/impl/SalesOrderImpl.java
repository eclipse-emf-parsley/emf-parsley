/**
 */
package org.eclipse.emf.parsley.examples.cdo.company.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage;
import org.eclipse.emf.parsley.examples.cdo.company.Customer;
import org.eclipse.emf.parsley.examples.cdo.company.SalesOrder;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sales Order</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.impl.SalesOrderImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.impl.SalesOrderImpl#getCustomer <em>Customer</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SalesOrderImpl extends OrderImpl implements SalesOrder {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SalesOrderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CompanyPackage.Literals.SALES_ORDER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getId() {
		return (Integer)eGet(CompanyPackage.Literals.SALES_ORDER__ID, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(int newId) {
		eSet(CompanyPackage.Literals.SALES_ORDER__ID, newId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Customer getCustomer() {
		return (Customer)eGet(CompanyPackage.Literals.SALES_ORDER__CUSTOMER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCustomer(Customer newCustomer) {
		eSet(CompanyPackage.Literals.SALES_ORDER__CUSTOMER, newCustomer);
	}

} //SalesOrderImpl
