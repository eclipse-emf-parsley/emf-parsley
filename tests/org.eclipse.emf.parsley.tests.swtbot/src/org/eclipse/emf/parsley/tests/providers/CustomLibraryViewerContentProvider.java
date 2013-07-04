/**
 * 
 */
package org.eclipse.emf.parsley.tests.providers;


import java.util.ArrayList;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider;
import org.eclipse.emf.parsley.examples.library.Book;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.examples.library.Writer;

import com.google.inject.Inject;

/**
 * A custom content provider for Library
 * 
 * @author Lorenzo Bettini
 * 
 */
public class CustomLibraryViewerContentProvider extends ViewerContentProvider {

	@Inject
	public CustomLibraryViewerContentProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public Object children(Library library) {
		return library.getBooks();
	}

	public Object children(Book book) {
		ArrayList<Object> children = new ArrayList<Object>();
		Writer author = book.getAuthor();
		if (author != null) {
			children.add(author);
		}
		children.addAll(book.getBorrowers());
		return children;
	}
}
