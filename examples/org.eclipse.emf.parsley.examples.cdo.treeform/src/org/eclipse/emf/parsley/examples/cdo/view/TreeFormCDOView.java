package org.eclipse.emf.parsley.examples.cdo.view;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.parsley.views.AbstractSaveableTreeFormView;

public class TreeFormCDOView extends AbstractSaveableTreeFormView {

	@Override
	protected URI createResourceURI() {
		return URI.createURI("cdo://localhost:2037/demo/myResource/");
	}

}
