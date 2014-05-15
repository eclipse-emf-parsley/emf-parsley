/**
 */
package org.eclipse.emf.parsley.examples.cdo.company;

import java.util.Date;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Purchase Order</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.PurchaseOrder#getDate <em>Date</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.PurchaseOrder#getSupplier <em>Supplier</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getPurchaseOrder()
 * @model
 * @generated
 */
public interface PurchaseOrder extends Order {
	/**
	 * Returns the value of the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Date</em>' attribute.
	 * @see #setDate(Date)
	 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getPurchaseOrder_Date()
	 * @model
	 * @generated
	 */
	Date getDate();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.examples.cdo.company.PurchaseOrder#getDate <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Date</em>' attribute.
	 * @see #getDate()
	 * @generated
	 */
	void setDate(Date value);

	/**
	 * Returns the value of the '<em><b>Supplier</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.parsley.examples.cdo.company.Supplier#getPurchaseOrders <em>Purchase Orders</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Supplier</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Supplier</em>' reference.
	 * @see #setSupplier(Supplier)
	 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getPurchaseOrder_Supplier()
	 * @see org.eclipse.emf.parsley.examples.cdo.company.Supplier#getPurchaseOrders
	 * @model opposite="purchaseOrders" required="true"
	 * @generated
	 */
	Supplier getSupplier();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.examples.cdo.company.PurchaseOrder#getSupplier <em>Supplier</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Supplier</em>' reference.
	 * @see #getSupplier()
	 * @generated
	 */
	void setSupplier(Supplier value);

} // PurchaseOrder
