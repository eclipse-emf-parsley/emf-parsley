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
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

import com.google.inject.Inject;

/**
 * Custom label provider for the Library, with custom text, image, font,
 * foreground and background color.
 * 
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

	public Font font(Book book) {
		return JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);
	}

	public Color foreground(Book book) {
		return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
	}

	public Color background(Book book) {
		return Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
	}

	public String text(Borrower b) {
		return "Borrower: " + b.getFirstName();
	}
}
