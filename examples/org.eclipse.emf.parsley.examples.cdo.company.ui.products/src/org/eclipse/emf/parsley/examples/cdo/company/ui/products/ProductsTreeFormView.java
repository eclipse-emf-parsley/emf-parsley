package org.eclipse.emf.parsley.examples.cdo.company.ui.products;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.parsley.examples.cdo.company.CompanyConstants;
import org.eclipse.emf.parsley.views.AbstractSaveableTreeFormView;

public class ProductsTreeFormView extends AbstractSaveableTreeFormView {

	@Override
	protected URI createResourceURI() {
		return URI.createURI(CompanyConstants.RESOURCE_ID);
	}
	
}
