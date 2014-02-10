/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.labeling;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.examples.library.Book;
import org.eclipse.emf.parsley.examples.library.Borrower;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;

import com.google.inject.Inject;



/**
 * @author Lorenzo Bettini
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
