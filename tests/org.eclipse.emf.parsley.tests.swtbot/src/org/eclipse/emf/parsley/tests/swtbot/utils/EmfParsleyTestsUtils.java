/**
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 */
package org.eclipse.emf.parsley.tests.swtbot.utils;

import static java.util.Arrays.asList;
import static org.eclipse.xtext.xbase.lib.IterableExtensions.join;
import static org.eclipse.xtext.xbase.lib.IterableExtensions.map;
import static org.eclipse.xtext.xbase.lib.ListExtensions.map;

import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.examples.library.Book;
import org.eclipse.emf.parsley.examples.library.Borrower;
import org.eclipse.emf.parsley.examples.library.Writer;

public class EmfParsleyTestsUtils {
	public String toStringNameBased(Iterable<EStructuralFeature> features) {
		return join(map(features, it -> {
			return it.getName();
		}), ", ");
	}

	public String toStringRep(List<?> elements) {
		return join(map(elements, it -> {
			return stringRep(it);
		}), ", ");
	}

	protected String _stringRep(Object o) {
		return o.toString();
	}

	protected String _stringRep(Book book) {
		return "Book: " + book.getTitle();
	}

	protected String _stringRep(Writer writer) {
		return "Writer: " + writer.getName();
	}

	protected String _stringRep(Borrower b) {
		String lastName = b.getLastName();
		return "Borrower: " + b.getFirstName() + 
			(lastName != null ? " " + lastName : "");
	}

	public String stringRep(Object book) {
		if (book instanceof Book) {
			return _stringRep((Book) book);
		} else if (book instanceof Borrower) {
			return _stringRep((Borrower) book);
		} else if (book instanceof Writer) {
			return _stringRep((Writer) book);
		} else if (book != null) {
			return _stringRep(book);
		} else {
			throw new IllegalArgumentException("Unhandled parameter types: " + asList(book).toString());
		}
	}
}
