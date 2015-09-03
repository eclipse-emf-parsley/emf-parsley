package org.eclipse.emf.parsley.examples.cdo.company.ui.orders;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage;
import org.eclipse.emf.parsley.views.AbstractOnSelectionTableFormView;

public class OrdersView extends AbstractOnSelectionTableFormView {

	@Override
	protected EClass getEClass() {
		return CompanyPackage.eINSTANCE.getOrderDetail();
	}

}
