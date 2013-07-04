package org.eclipse.emf.parsley.examples.rap.ui;

import org.eclipse.emf.parsley.views.AbstractSaveableTreeFormView;

import org.eclipse.emf.common.util.URI;

public class SaveableView extends AbstractSaveableTreeFormView {
	public static final String ID = "org.eclipse.emf.parsley.examples.rap.ui.saveableview";

	@Override
	protected URI createResourceURI() {
		return URI.createFileURI(System.getProperty("java.io.tmpdir")
				+ "/My.model");
	}
}