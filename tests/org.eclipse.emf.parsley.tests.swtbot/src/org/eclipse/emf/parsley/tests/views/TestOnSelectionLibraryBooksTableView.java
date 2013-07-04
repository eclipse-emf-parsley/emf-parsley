/**
 * 
 */
package org.eclipse.emf.parsley.tests.views;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage;
import org.eclipse.emf.parsley.views.AbstractOnSelectionTableView;


/**
 * @author Lorenzo Bettini
 *
 */
public class TestOnSelectionLibraryBooksTableView extends
		AbstractOnSelectionTableView {

	@Override
	protected EStructuralFeature getEStructuralFeature() {
		return EXTLibraryPackage.Literals.LIBRARY__BOOKS;
	}

	@Override
	protected EClass getEClass() {
		return EXTLibraryPackage.Literals.BOOK;
	}

}
