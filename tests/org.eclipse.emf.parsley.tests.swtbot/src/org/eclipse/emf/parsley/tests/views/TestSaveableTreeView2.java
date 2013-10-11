package org.eclipse.emf.parsley.tests.views;


import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.parsley.tests.EmfParsleyAbstractTests;
import org.eclipse.emf.parsley.views.AbstractSaveableTreeView;

public class TestSaveableTreeView2 extends AbstractSaveableTreeView {

	@Override
	protected URI createResourceURI() {
		return URI.createPlatformResourceURI(
				EmfParsleyAbstractTests.MY2_EXTLIBRARY_RELATIVE_PATH, true);
	}

}
