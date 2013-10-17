package org.eclipse.emf.parsley.tests

import org.junit.Test

import static org.eclipse.emf.parsley.examples.library.EXTLibraryFactory.*
import static extension org.junit.Assert.*
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.util.EcoreUtil2
import org.junit.Before
import org.eclipse.emf.parsley.examples.library.Writer
import org.eclipse.emf.parsley.examples.library.Book
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage
import java.util.List

class EmfCopyTest {

	private Library lib = null
	
	private Writer wr = null
	
	private Book b = null
	
	@Before
	def void setUp() {
		lib = createTestLibrary
		wr = lib.writers.head
		b = lib.books.head
	}
	
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
	
	def private assertBooks(Writer w, int expectedSize) {
		expectedSize.assertEquals(w.books.size)
	}

	def private booksByReflection(Writer w) {
		w.eGet(EXTLibraryPackage.eINSTANCE.writer_Books) as List<Book>
	}

	def private addBooksByReflection(Writer w, List<Book> books) {
		w.eSet(EXTLibraryPackage.eINSTANCE.writer_Books, books)
	}

	def private createTestLibrary() {
		eINSTANCE.createLibrary() => [
			name = "TEST"
			val writer = createTestWriter("Writer")
			val book = createTestBook("Book")
			writers += writer
			books += book
			writer.books += book
		]
	}

	def private createTestWriter(String t) {
		eINSTANCE.createWriter() => [ 
			firstName = t
		]
	}
	
	def private createTestBook(String t) {
		eINSTANCE.createBook() => [ title = t ]
	}
}