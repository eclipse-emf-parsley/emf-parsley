/**
 */
package org.eclipse.emf.parsley.examples.cdo.company.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.emf.parsley.examples.cdo.company.Company;
import org.eclipse.emf.parsley.examples.cdo.company.companyFactory;
import org.eclipse.emf.parsley.examples.cdo.company.companyPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.emf.parsley.examples.cdo.company.Company} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CompanyItemProvider
	extends AddressableItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompanyItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(companyPackage.Literals.COMPANY__CATEGORIES);
			childrenFeatures.add(companyPackage.Literals.COMPANY__SUPPLIERS);
			childrenFeatures.add(companyPackage.Literals.COMPANY__CUSTOMERS);
			childrenFeatures.add(companyPackage.Literals.COMPANY__PURCHASE_ORDERS);
			childrenFeatures.add(companyPackage.Literals.COMPANY__SALES_ORDERS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns Company.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Company"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Company)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Company_type") :
			getString("_UI_Company_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Company.class)) {
			case companyPackage.COMPANY__CATEGORIES:
			case companyPackage.COMPANY__SUPPLIERS:
			case companyPackage.COMPANY__CUSTOMERS:
			case companyPackage.COMPANY__PURCHASE_ORDERS:
			case companyPackage.COMPANY__SALES_ORDERS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(companyPackage.Literals.COMPANY__CATEGORIES,
				 companyFactory.eINSTANCE.createCategory()));

		newChildDescriptors.add
			(createChildParameter
				(companyPackage.Literals.COMPANY__SUPPLIERS,
				 companyFactory.eINSTANCE.createSupplier()));

		newChildDescriptors.add
			(createChildParameter
				(companyPackage.Literals.COMPANY__CUSTOMERS,
				 companyFactory.eINSTANCE.createCustomer()));

		newChildDescriptors.add
			(createChildParameter
				(companyPackage.Literals.COMPANY__PURCHASE_ORDERS,
				 companyFactory.eINSTANCE.createPurchaseOrder()));

		newChildDescriptors.add
			(createChildParameter
				(companyPackage.Literals.COMPANY__SALES_ORDERS,
				 companyFactory.eINSTANCE.createSalesOrder()));
	}

}
