package org.eclipse.emf.parsley.examples.views;

import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.parsley.views.AbstractSaveableTreeFormView;

public class SaveableResourceTreeFormView extends AbstractSaveableTreeFormView{

	@Override
	protected URI createResourceURI() {
		return URI.createPlatformResourceURI("/library/Library.xmi", true);
	}

}
