package org.eclipse.emf.parsley.examples.cdo.company.ui.orders;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage;
import org.eclipse.emf.parsley.views.AbstractOnSelectionTableFormView;

public class OrdersView extends AbstractOnSelectionTableFormView {

	@Override
	protected EStructuralFeature getEStructuralFeature() {
		return CompanyPackage.eINSTANCE.getOrder_OrderDetails();
	}

}
