/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.emf.parsley.tests.resource;


import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.examples.library.Book;
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.examples.library.Writer;
import org.eclipse.emf.parsley.resource.EmptyResourceInitializer;

/**
 * @author Lorenzo Bettini
 * 
 */
public class TestEmptyLibraryResourceInitializer extends
		EmptyResourceInitializer {

	@Override
	public void initialize(Resource resource) {
		super.initialize(resource);

		if (resource.getURI().lastSegment().endsWith("extlibrary")) {
			Library library = EXTLibraryFactory.eINSTANCE.createLibrary();
			resource.getContents().add(library);
			createBook(library, "First Book", null);
			createBook(library, "Second Book", createWriter("A Writer"));
			createBook(library, "Third Book", null);
		}
	}

	protected void createBook(Library library, String title, Writer writer) {
		Book book = EXTLibraryFactory.eINSTANCE.createBook();
		book.setTitle(title);
		library.getBooks().add(book);
		if (writer != null) {
			library.getWriters().add(writer);
			book.setAuthor(writer);
		}
	}

	protected Writer createWriter(String name) {
		Writer writer = EXTLibraryFactory.eINSTANCE.createWriter();
		writer.setName(name);
		return writer;
	}
}
