package org.eclipse.emf.parsley.tests.factories.editabletable;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.tests.EmfParsleyAbstractTests;
import org.eclipse.emf.parsley.views.AbstractSaveableTableView;
import org.eclipse.swt.SWT;

public class TestSaveableResourceTableWithEditView extends AbstractSaveableTableView {

	@Override
	protected Object getContents(Resource resource) {
		Library library=(Library) resource.getContents().get(0);
		return library.getBooks();
	}

	@Override
	protected EClass getEClass() {
		return EXTLibraryPackage.Literals.BOOK;
	}
	
	@Override
	protected URI createResourceURI() {
		return URI.createPlatformResourceURI(
				EmfParsleyAbstractTests.MY_EXTLIBRARY_RELATIVE_PATH, true);
	}
	
	@Override
	protected int createTableStyles() {
		return SWT.SINGLE;
	}

}