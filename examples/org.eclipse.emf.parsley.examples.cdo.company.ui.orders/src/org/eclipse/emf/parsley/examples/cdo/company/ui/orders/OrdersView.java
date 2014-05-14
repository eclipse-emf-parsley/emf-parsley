package org.eclipse.emf.parsley.examples.cdo.company.ui.orders;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage;
import org.eclipse.emf.parsley.views.AbstractOnSelectionTableFormView;
import org.eclipse.swt.widgets.Composite;

public class OrdersView extends AbstractOnSelectionTableFormView {

	@Override
	protected EStructuralFeature getEStructuralFeature() {
		return CompanyPackage.eINSTANCE.getOrder_OrderDetails();
	}
	
	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		getSite().setSelectionProvider(getViewer());
	}

}
