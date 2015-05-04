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

import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage
import org.eclipse.emf.parsley.util.EcoreUtil2
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule

class EmfCopyTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	@Test def void testCloneDoesNotCopyBidirectional() {
		writer.books.size.assertEquals(1)
		val copy = EcoreUtil2.clone(writer)
		copy.books.size.assertEquals(0)
	}

	@Test def void testManualESet() {
		val books = writer.booksByReflection
		books.size.assertEquals(1)
		val copy = EcoreUtil2.clone(writer)
		copy.addBooksByReflection(books)
		writer.books.size.assertEquals(0)
		copy.books.size.assertEquals(1)
		// manual eSet preserves bidirectionality
		book.author.assertSame(copy)
	}

	@Test def void testCopyState() {
		val state = EcoreUtil2.copyState(writer)
		
		writer.books.clear
		writer.assertBooks(0)
		book.author.assertNull
		
		// this brings the book back to the author
		state.copyStateTo(writer)
		writer.assertBooks(1)
		book.author.assertSame(writer)
	}

	@Test def void testReadOnlyCase() {
		1.assertEquals(library.books.size)
		val state = EcoreUtil2.copyState(library)
		state.get(EXTLibraryPackage.eINSTANCE.library_Books).assertNull
	}

	@Test def void testFeatureMapCase() {
		1.assertEquals(library.people.size)
		val state = EcoreUtil2.copyState(library)
		state.copyStateTo(library)
		1.assertEquals(library.people.size)
		1.assertEquals(library.writers.size)
	}
	
}