/**
 */
package org.eclipse.emf.parsley.examples.cdo.company;

import org.eclipse.emf.cdo.CDOObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Product</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.Product#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.Product#getOrderDetails <em>Order Details</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.Product#getVat <em>Vat</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.Product#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.Product#getPrice <em>Price</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getProduct()
 * @model
 * @extends CDOObject
 * @generated
 */
public interface Product extends CDOObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getProduct_Name()
	 * @model annotation="teneo.jpa value='@Id'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.examples.cdo.company.Product#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Order Details</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.parsley.examples.cdo.company.OrderDetail}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.parsley.examples.cdo.company.OrderDetail#getProduct <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Order Details</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Order Details</em>' reference list.
	 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getProduct_OrderDetails()
	 * @see org.eclipse.emf.parsley.examples.cdo.company.OrderDetail#getProduct
	 * @model opposite="product"
	 * @generated
	 */
	EList<OrderDetail> getOrderDetails();

	/**
	 * Returns the value of the '<em><b>Vat</b></em>' attribute.
	 * The default value is <code>"vat15"</code>.
	 * The literals are from the enumeration {@link org.eclipse.emf.parsley.examples.cdo.company.VAT}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vat</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vat</em>' attribute.
	 * @see org.eclipse.emf.parsley.examples.cdo.company.VAT
	 * @see #setVat(VAT)
	 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getProduct_Vat()
	 * @model default="vat15"
	 *        annotation="teneo.jpa value='@Basic(optional=false)\n@Column(nullable=false)'"
	 * @generated
	 */
	VAT getVat();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.examples.cdo.company.Product#getVat <em>Vat</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vat</em>' attribute.
	 * @see org.eclipse.emf.parsley.examples.cdo.company.VAT
	 * @see #getVat()
	 * @generated
	 */
	void setVat(VAT value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getProduct_Description()
	 * @model transient="true"
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.examples.cdo.company.Product#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

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
	 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getProduct_Price()
	 * @model
	 * @generated
	 */
	float getPrice();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.parsley.examples.cdo.company.Product#getPrice <em>Price</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Price</em>' attribute.
	 * @see #getPrice()
	 * @generated
	 */
	void setPrice(float value);

} // Product
