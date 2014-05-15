/**
 */
package org.eclipse.emf.parsley.examples.cdo.company;

import org.eclipse.emf.cdo.CDOObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Order</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.Order#getOrderDetails <em>Order Details</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getOrder()
 * @model annotation="teneo.jpa value='@Entity(name=\"BaseOrder\")'"
 * @extends CDOObject
 * @generated
 */
public interface Order extends CDOObject {
	/**
	 * Returns the value of the '<em><b>Order Details</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.parsley.examples.cdo.company.OrderDetail}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.parsley.examples.cdo.company.OrderDetail#getOrder <em>Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Order Details</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Order Details</em>' containment reference list.
	 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getOrder_OrderDetails()
	 * @see org.eclipse.emf.parsley.examples.cdo.company.OrderDetail#getOrder
	 * @model opposite="order" containment="true"
	 * @generated
	 */
	EList<OrderDetail> getOrderDetails();

} // Order
