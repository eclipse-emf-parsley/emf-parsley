package org.eclipse.emf.parsley.examples.cdo.company.ui.customers;

import org.eclipse.emf.parsley.examples.cdo.company.CompanyConstants;
import org.eclipse.emf.parsley.views.AbstractSaveableTreeView;
import org.eclipse.emf.common.util.URI;
import org.eclipse.swt.widgets.Composite;

public class CustomersTreeView extends AbstractSaveableTreeView {

	@Override
	protected URI createResourceURI() {
		return URI.createURI(CompanyConstants.RESOURCE_ID);
	}
	
	
	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		getSite().setSelectionProvider(getTreeViewer());
	}
}
