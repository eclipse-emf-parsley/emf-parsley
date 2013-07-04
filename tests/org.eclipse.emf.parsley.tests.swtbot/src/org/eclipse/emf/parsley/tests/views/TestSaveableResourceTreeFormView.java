package org.eclipse.emf.parsley.tests.views;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.parsley.tests.EmfComponentsAbstractTests;
import org.eclipse.emf.parsley.views.AbstractSaveableTreeFormView;


public class TestSaveableResourceTreeFormView extends
		AbstractSaveableTreeFormView {

	@Override
	protected URI createResourceURI() {
		return URI.createPlatformResourceURI(
				EmfComponentsAbstractTests.MY_EXTLIBRARY_RELATIVE_PATH, true);
	}

}
