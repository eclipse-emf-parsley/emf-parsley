/**
 */
package org.eclipse.emf.parsley.examples.cdo.company.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.internal.cdo.CDOObjectImpl;

import org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage;
import org.eclipse.emf.parsley.examples.cdo.company.Order;
import org.eclipse.emf.parsley.examples.cdo.company.OrderDetail;
import org.eclipse.emf.parsley.examples.cdo.company.Product;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Order Detail</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.impl.OrderDetailImpl#getOrder <em>Order</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.impl.OrderDetailImpl#getProduct <em>Product</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.impl.OrderDetailImpl#getPrice <em>Price</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OrderDetailImpl extends CDOObjectImpl implements OrderDetail {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OrderDetailImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CompanyPackage.Literals.ORDER_DETAIL;
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
	public Order getOrder() {
		return (Order)eGet(CompanyPackage.Literals.ORDER_DETAIL__ORDER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrder(Order newOrder) {
		eSet(CompanyPackage.Literals.ORDER_DETAIL__ORDER, newOrder);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Product getProduct() {
		return (Product)eGet(CompanyPackage.Literals.ORDER_DETAIL__PRODUCT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProduct(Product newProduct) {
		eSet(CompanyPackage.Literals.ORDER_DETAIL__PRODUCT, newProduct);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getPrice() {
		return (Float)eGet(CompanyPackage.Literals.ORDER_DETAIL__PRICE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrice(float newPrice) {
		eSet(CompanyPackage.Literals.ORDER_DETAIL__PRICE, newPrice);
	}

} //OrderDetailImpl
