package org.eclipse.emf.parsley.tests.providers

import com.google.inject.Inject
import org.eclipse.emf.common.notify.AdapterFactory
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.parsley.examples.library.Book

class CustomElementsLibraryViewerContentProvider extends CustomLibraryViewerContentProvider {
	
	@Inject
	new(AdapterFactory adapterFactory) {
		super(adapterFactory)
	}
	
	def Object elements(Resource resource) {
		resource.allContents.filter(Book)
	}
}