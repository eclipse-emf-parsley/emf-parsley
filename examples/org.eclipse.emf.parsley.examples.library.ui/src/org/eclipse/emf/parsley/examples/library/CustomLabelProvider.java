package org.eclipse.emf.parsley.examples.library;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.examples.library.Book;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.examples.library.Person;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;

import com.google.inject.Inject;



public class CustomLabelProvider extends ViewerLabelProvider {

	@Inject
	public CustomLabelProvider(AdapterFactoryLabelProvider delegate) {
		super(delegate);
	}

	public String text(Book book) {
		return "Book: " + book.getTitle();
	}
	
	public String image(Library l) {
		return "library.gif";
	}
	
	public String image(Person p) {
		return "person.gif";
	}
	
	public String image(Book b) {
		return "book.png";
	}
}
