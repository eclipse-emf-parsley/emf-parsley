package org.eclipse.emf.parsley.tests.menu

import org.eclipse.emf.parsley.edit.action.EditingMenuBuilder
import org.eclipse.emf.parsley.examples.library.Writer
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory

class CustomLibraryMenuBuilder extends EditingMenuBuilder {
	
	def emfMenuContributions(Writer w) {
		#[
			actionChange("New book", w.eContainer as Library,
				[
					library |
					val book = EXTLibraryFactory.eINSTANCE.createBook
					library.books += book
					book.title = "A new book"
					book.author = w
				]
			)
		]
	}
	
}