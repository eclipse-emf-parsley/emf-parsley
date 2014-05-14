/**
 */
package org.eclipse.emf.parsley.examples.cdo.company;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Company</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.Company#getCategories <em>Categories</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.Company#getSuppliers <em>Suppliers</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.Company#getCustomers <em>Customers</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.Company#getPurchaseOrders <em>Purchase Orders</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.Company#getSalesOrders <em>Sales Orders</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getCompany()
 * @model
 * @generated
 */
public interface Company extends Addressable {
	/**
	 * Returns the value of the '<em><b>Categories</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.parsley.examples.cdo.company.Category}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Categories</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Categories</em>' containment reference list.
	 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getCompany_Categories()
	 * @model containment="true"
	 * @generated
	 */
	EList<Category> getCategories();

	/**
	 * Returns the value of the '<em><b>Suppliers</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.parsley.examples.cdo.company.Supplier}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Suppliers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Suppliers</em>' containment reference list.
	 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getCompany_Suppliers()
	 * @model containment="true"
	 * @generated
	 */
	EList<Supplier> getSuppliers();

	/**
	 * Returns the value of the '<em><b>Customers</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.parsley.examples.cdo.company.Customer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Customers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Customers</em>' containment reference list.
	 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getCompany_Customers()
	 * @model containment="true"
	 * @generated
	 */
	EList<Customer> getCustomers();

	/**
	 * Returns the value of the '<em><b>Purchase Orders</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.parsley.examples.cdo.company.PurchaseOrder}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Purchase Orders</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Purchase Orders</em>' containment reference list.
	 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getCompany_PurchaseOrders()
	 * @model containment="true"
	 * @generated
	 */
	EList<PurchaseOrder> getPurchaseOrders();

	/**
	 * Returns the value of the '<em><b>Sales Orders</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.parsley.examples.cdo.company.SalesOrder}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sales Orders</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sales Orders</em>' containment reference list.
	 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getCompany_SalesOrders()
	 * @model containment="true"
	 * @generated
	 */
	EList<SalesOrder> getSalesOrders();

} // Company
