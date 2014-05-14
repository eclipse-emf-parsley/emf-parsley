/**
 */
package org.eclipse.emf.parsley.examples.cdo.company.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.parsley.examples.cdo.company.Category;
import org.eclipse.emf.parsley.examples.cdo.company.Company;
import org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage;
import org.eclipse.emf.parsley.examples.cdo.company.Customer;
import org.eclipse.emf.parsley.examples.cdo.company.PurchaseOrder;
import org.eclipse.emf.parsley.examples.cdo.company.SalesOrder;
import org.eclipse.emf.parsley.examples.cdo.company.Supplier;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Company</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.impl.CompanyImpl#getCategories <em>Categories</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.impl.CompanyImpl#getSuppliers <em>Suppliers</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.impl.CompanyImpl#getCustomers <em>Customers</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.impl.CompanyImpl#getPurchaseOrders <em>Purchase Orders</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.impl.CompanyImpl#getSalesOrders <em>Sales Orders</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CompanyImpl extends AddressableImpl implements Company {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompanyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CompanyPackage.Literals.COMPANY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Category> getCategories() {
		return (EList<Category>)eGet(CompanyPackage.Literals.COMPANY__CATEGORIES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Supplier> getSuppliers() {
		return (EList<Supplier>)eGet(CompanyPackage.Literals.COMPANY__SUPPLIERS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Customer> getCustomers() {
		return (EList<Customer>)eGet(CompanyPackage.Literals.COMPANY__CUSTOMERS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<PurchaseOrder> getPurchaseOrders() {
		return (EList<PurchaseOrder>)eGet(CompanyPackage.Literals.COMPANY__PURCHASE_ORDERS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<SalesOrder> getSalesOrders() {
		return (EList<SalesOrder>)eGet(CompanyPackage.Literals.COMPANY__SALES_ORDERS, true);
	}

} //CompanyImpl
