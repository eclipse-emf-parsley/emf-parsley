package org.eclipse.emf.parsley.examples.cdo.company.ui.customers;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.parsley.examples.cdo.company.CompanyConstants;
import org.eclipse.emf.parsley.views.AbstractSaveableTreeView;

public class CustomersTreeView extends AbstractSaveableTreeView {

	@Override
	protected URI createResourceURI() {
		return URI.createURI(CompanyConstants.RESOURCE_ID);
	}
	
	
}
