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