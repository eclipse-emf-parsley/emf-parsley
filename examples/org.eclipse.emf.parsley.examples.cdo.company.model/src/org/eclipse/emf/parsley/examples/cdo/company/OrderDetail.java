/**
 */
package org.eclipse.emf.parsley.examples.cdo.company;

import org.eclipse.emf.cdo.CDOObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Order Detail</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.OrderDetail#getOrder <em>Order</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.OrderDetail#getProduct <em>Product</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.OrderDetail#getPrice <em>Price</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getOrderDetail()
 * @model
 * @extends CDOObject
 * @generated
 */
public interface OrderDetail extends CDOObject {
	/**
	 * Returns the value of the '<em><b>Order</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.parsley.examples.cdo.company.Order#getOrderDetails <em>Order Details</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Order</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Order</em>' container reference.
	 * @see #setOrder(Order)
	 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getOrderDetail_Order()
	 * @see org.eclipse.emf.parsley.examples.cdo.company.Order#getOrderDetails
	 * @model opposite="orderDetails" required="true" transient="false"
	 * @generated
	 */
	Order getOrder();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.examples.cdo.company.OrderDetail#getOrder <em>Order</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Order</em>' container reference.
	 * @see #getOrder()
	 * @generated
	 */
	void setOrder(Order value);

	/**
	 * Returns the value of the '<em><b>Product</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.parsley.examples.cdo.company.Product#getOrderDetails <em>Order Details</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Product</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Product</em>' reference.
	 * @see #setProduct(Product)
	 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getOrderDetail_Product()
	 * @see org.eclipse.emf.parsley.examples.cdo.company.Product#getOrderDetails
	 * @model opposite="orderDetails"
	 * @generated
	 */
	Product getProduct();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.examples.cdo.company.OrderDetail#getProduct <em>Product</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Product</em>' reference.
	 * @see #getProduct()
	 * @generated
	 */
	void setProduct(Product value);

	/**
	 * Returns the value of the '<em><b>Price</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Price</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Price</em>' attribute.
	 * @see #setPrice(float)
	 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getOrderDetail_Price()
	 * @model
	 * @generated
	 */
	float getPrice();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.examples.cdo.company.OrderDetail#getPrice <em>Price</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Price</em>' attribute.
	 * @see #getPrice()
	 * @generated
	 */
	void setPrice(float value);

} // OrderDetail
