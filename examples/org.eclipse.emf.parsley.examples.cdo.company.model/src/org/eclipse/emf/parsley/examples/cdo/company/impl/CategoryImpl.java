/**
 */
package org.eclipse.emf.parsley.examples.cdo.company.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.internal.cdo.CDOObjectImpl;

import org.eclipse.emf.parsley.examples.cdo.company.Category;
import org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage;
import org.eclipse.emf.parsley.examples.cdo.company.Product;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Category</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.impl.CategoryImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.impl.CategoryImpl#getCategories <em>Categories</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.examples.cdo.company.impl.CategoryImpl#getProducts <em>Products</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CategoryImpl extends CDOObjectImpl implements Category {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CategoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CompanyPackage.Literals.CATEGORY;
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
	public String getName() {
		return (String)eGet(CompanyPackage.Literals.CATEGORY__NAME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		eSet(CompanyPackage.Literals.CATEGORY__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Category> getCategories() {
		return (EList<Category>)eGet(CompanyPackage.Literals.CATEGORY__CATEGORIES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Product> getProducts() {
		return (EList<Product>)eGet(CompanyPackage.Literals.CATEGORY__PRODUCTS, true);
	}

} //CategoryImpl
