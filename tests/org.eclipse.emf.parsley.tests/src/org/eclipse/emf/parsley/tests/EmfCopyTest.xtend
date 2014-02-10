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
import org.junit.Test

import static extension org.junit.Assert.*

class EmfCopyTest extends EmfAbstractTest {

	@Test def void testCloneDoesNotCopyBidirectional() {
		wr.books.size.assertEquals(1)
		val copy = EcoreUtil2.clone(wr)
		copy.books.size.assertEquals(0)
	}

	@Test def void testManualESet() {
		val books = wr.booksByReflection
		books.size.assertEquals(1)
		val copy = EcoreUtil2.clone(wr)
		copy.addBooksByReflection(books)
		wr.books.size.assertEquals(0)
		copy.books.size.assertEquals(1)
		// manual eSet preserves bidirectionality
		b.author.assertSame(copy)
	}

	@Test def void testCopyState() {
		val state = EcoreUtil2.copyState(wr)
		
		wr.books.clear
		wr.assertBooks(0)
		b.author.assertNull
		
		// this brings the book back to the author
		state.copyStateTo(wr)
		wr.assertBooks(1)
		b.author.assertSame(wr)
	}

	@Test def void testReadOnlyCase() {
		1.assertEquals(lib.books.size)
		val state = EcoreUtil2.copyState(lib)
		state.get(EXTLibraryPackage.eINSTANCE.library_Books).assertNull
	}

	@Test def void testFeatureMapCase() {
		1.assertEquals(lib.people.size)
		val state = EcoreUtil2.copyState(lib)
		state.copyStateTo(lib)
		1.assertEquals(lib.people.size)
		1.assertEquals(lib.writers.size)
	}
	
}