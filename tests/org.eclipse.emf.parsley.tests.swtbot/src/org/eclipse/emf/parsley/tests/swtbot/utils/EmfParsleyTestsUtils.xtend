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
package org.eclipse.emf.parsley.tests.swtbot.utils

import org.eclipse.emf.ecore.EStructuralFeature
import java.util.List
import org.eclipse.emf.parsley.examples.library.Book
import org.eclipse.emf.parsley.examples.library.Writer
import org.eclipse.emf.parsley.examples.library.Borrower

class EmfParsleyTestsUtils {
	
	def toStringNameBased(Iterable<EStructuralFeature> features) {
		features.map[ it.name ].join(", ")
	}

	def toStringRep(List<?> elements) {
		elements.map[stringRep].join(", ")
	}
	
	def dispatch stringRep(Object o) {
		o.toString
	}

	def dispatch stringRep(Book book) {
		"Book: " + book.title
	}

	def dispatch stringRep(Writer writer) {
		"Writer: " + writer.name
	}

	def dispatch stringRep(Borrower b) {
		"Borrower: " + b.firstName + 
			(if (b.lastName !== null) " " + b.lastName else "")
	}
}