/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.labeling;

import org.eclipse.emf.parsley.examples.library.Book;
import org.eclipse.emf.parsley.examples.library.BookCategory;
import org.eclipse.emf.parsley.examples.library.Writer;
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

/**
 * Custom table label provider for the Library, with custom text, image, font,
 * foreground and background color.
 * 
 * @author Lorenzo Bettini
 * 
 */
public class CustomLibraryTableColumnLabelProvider extends TableColumnLabelProvider {

	public String text_Writer_name(Object element) {
		if (element instanceof Writer) {
			Writer writer = (Writer) element;
			return "Writer " + writer.getName();
		}
		return null;
	}

	public Font rowFont(Book book) {
		if (book.getAuthor() == null) {
			return JFaceResources.getFontRegistry().getItalic(JFaceResources.DEFAULT_FONT);
		}
		return null;
	}

	public Color rowForeground(Book book) {
		if ("".equals(book.getTitle())) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
		}
		return null;
	}

	public Color rowBackground(Book book) {
		if (book.getCategory() == BookCategory.SCIENCE_FICTION_LITERAL) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
		}
		return null;
	}
}
