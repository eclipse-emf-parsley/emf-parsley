/**
 */
package org.eclipse.emf.parsley.examples.cdo.company;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Customer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.Customer#getSalesOrders <em>Sales Orders</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getCustomer()
 * @model
 * @generated
 */
public interface Customer extends Addressable {
	/**
	 * Returns the value of the '<em><b>Sales Orders</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.parsley.examples.cdo.company.SalesOrder}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.parsley.examples.cdo.company.SalesOrder#getCustomer <em>Customer</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sales Orders</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sales Orders</em>' containment reference list.
	 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getCustomer_SalesOrders()
	 * @see org.eclipse.emf.parsley.examples.cdo.company.SalesOrder#getCustomer
	 * @model opposite="customer" containment="true"
	 * @generated
	 */
	EList<SalesOrder> getSalesOrders();

} // Customer
