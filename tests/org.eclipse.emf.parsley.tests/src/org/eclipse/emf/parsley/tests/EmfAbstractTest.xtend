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
package org.eclipse.emf.parsley.tests

import java.util.List
import org.eclipse.emf.parsley.examples.library.Book
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer
import org.junit.Before

import static org.eclipse.emf.parsley.examples.library.EXTLibraryFactory.*

import static extension org.junit.Assert.*
import org.eclipse.emf.parsley.EmfParsleyConstants

class EmfAbstractTest {

	protected Library lib = null
	
	protected Writer wr = null
	
	protected Book b = null
	
	new() {
		// the following is useless... but it's just to have coverage
		// for the protected constructor of EmfParsleyConstants
		// and the protected constructor is "required" by sonar...
		new EmfParsleyConstants() {
			
		}
	}
	
	@Before
	def void setUp() {
		lib = createTestLibrary
		wr = lib.writers.head
		b = lib.books.head
	}
	
	def protected assertBooks(Writer w, int expectedSize) {
		expectedSize.assertEquals(w.books.size)
	}

	def protected booksByReflection(Writer w) {
		w.eGet(EXTLibraryPackage.eINSTANCE.writer_Books) as List<Book>
	}

	def protected addBooksByReflection(Writer w, List<Book> books) {
		w.eSet(EXTLibraryPackage.eINSTANCE.writer_Books, books)
	}

	def protected createTestLibrary() {
		eINSTANCE.createLibrary() => [
			name = "TEST"
			val writer = createTestWriter("Writer")
			val book = createTestBook("Book")
			writers += writer
			books += book
			writer.books += book
		]
	}

	def protected createTestWriter(String t) {
		eINSTANCE.createWriter() => [ 
			firstName = t
		]
	}
	
	def protected createTestBook(String t) {
		eINSTANCE.createBook() => [ title = t ]
	}

	def protected addTestBook(String t) {
		lib.books += createTestBook(t)
	}
}