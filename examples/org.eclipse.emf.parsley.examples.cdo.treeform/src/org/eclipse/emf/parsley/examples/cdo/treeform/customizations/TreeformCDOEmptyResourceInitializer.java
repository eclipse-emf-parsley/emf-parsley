package org.eclipse.emf.parsley.examples.cdo.treeform.customizations;

import library.Author;
import library.Book;
import library.Library;
import library.LibraryFactory;

import org.eclipse.emf.cdo.common.id.CDOReference;
import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.util.CommitException;
import org.eclipse.emf.cdo.util.ConcurrentAccessException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.resource.EmptyResourceInitializer;

public class TreeformCDOEmptyResourceInitializer extends
		EmptyResourceInitializer {

	@Override
	public void initialize(Resource resource) {
		Library library= LibraryFactory.eINSTANCE.createLibrary();
		
		Author author = LibraryFactory.eINSTANCE.createAuthor();
		author.setName("Ed Merks");
		library.getAuthors().add(author);

		Book book = LibraryFactory.eINSTANCE.createBook();
		book.setTitle("Domain Specific Languages");
		library.getBooks().add(book);
		author = LibraryFactory.eINSTANCE.createAuthor();
		author.setName("Martin Fowler");
		book.getAuthors().add(author);
		library.getAuthors().add(author);
		
		resource.getContents().add(library);
		
		try {
			CDOResource cdoResource = (CDOResource)resource;
			CDOTransaction cdoTransaction = (CDOTransaction)cdoResource.cdoView();
			cdoTransaction.commit();
		} catch (ConcurrentAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

