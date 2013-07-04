/**
 * 
 */
package org.eclipse.emf.parsley.tests.labeling;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.examples.library.Book;
import org.eclipse.emf.parsley.examples.library.Borrower;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;

import com.google.inject.Inject;



/**
 * @author bettini
 * 
 */
public class CustomLibraryLabelProvider extends ViewerLabelProvider {

	@Inject
	public CustomLibraryLabelProvider(AdapterFactoryLabelProvider delegate) {
		super(delegate);
	}

	public String text(Book book) {
		return "Book: " + book.getTitle();
	}

	public String image(Book book) {
		return "book2.png";
	}
	
	public String text(Borrower b) {
		return "Borrower: " + b.getFirstName();
	}
}
