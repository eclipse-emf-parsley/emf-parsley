/**
 */
package org.eclipse.emf.parsley.examples.cdo.company;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sales Order</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.SalesOrder#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.SalesOrder#getCustomer <em>Customer</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getSalesOrder()
 * @model
 * @generated
 */
public interface SalesOrder extends Order {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(int)
	 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getSalesOrder_Id()
	 * @model
	 * @generated
	 */
	int getId();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.examples.cdo.company.SalesOrder#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(int value);

	/**
	 * Returns the value of the '<em><b>Customer</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.parsley.examples.cdo.company.Customer#getSalesOrders <em>Sales Orders</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Customer</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Customer</em>' container reference.
	 * @see #setCustomer(Customer)
	 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getSalesOrder_Customer()
	 * @see org.eclipse.emf.parsley.examples.cdo.company.Customer#getSalesOrders
	 * @model opposite="salesOrders" transient="false"
	 * @generated
	 */
	Customer getCustomer();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.examples.cdo.company.SalesOrder#getCustomer <em>Customer</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Customer</em>' container reference.
	 * @see #getCustomer()
	 * @generated
	 */
	void setCustomer(Customer value);

} // SalesOrder
