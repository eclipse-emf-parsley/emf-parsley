/**
 */
package org.eclipse.emf.parsley.examples.cdo.company.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage;
import org.eclipse.emf.parsley.examples.cdo.company.PurchaseOrder;
import org.eclipse.emf.parsley.examples.cdo.company.Supplier;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Supplier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.impl.SupplierImpl#getPurchaseOrders <em>Purchase Orders</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.impl.SupplierImpl#isPreferred <em>Preferred</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SupplierImpl extends AddressableImpl implements Supplier {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SupplierImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CompanyPackage.Literals.SUPPLIER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<PurchaseOrder> getPurchaseOrders() {
		return (EList<PurchaseOrder>)eGet(CompanyPackage.Literals.SUPPLIER__PURCHASE_ORDERS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPreferred() {
		return (Boolean)eGet(CompanyPackage.Literals.SUPPLIER__PREFERRED, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreferred(boolean newPreferred) {
		eSet(CompanyPackage.Literals.SUPPLIER__PREFERRED, newPreferred);
	}

} //SupplierImpl
