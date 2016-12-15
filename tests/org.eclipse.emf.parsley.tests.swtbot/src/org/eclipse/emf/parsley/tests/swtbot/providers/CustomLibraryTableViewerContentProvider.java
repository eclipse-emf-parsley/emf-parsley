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
package org.eclipse.emf.parsley.tests.swtbot.providers;


import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.parsley.edit.ui.provider.TableViewerContentProvider;
import org.eclipse.emf.parsley.examples.library.Writer;

import com.google.inject.Inject;

/**
 * A custom table content provider for Library: when a writer is selected
 * we return its books; its books would not be shown automatically since they
 * are not proper contents, but just references.
 * 
 * @author Lorenzo Bettini
 * 
 */
public class CustomLibraryTableViewerContentProvider extends TableViewerContentProvider {

	@Inject
	public CustomLibraryTableViewerContentProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public Object elements(Writer writer) {
		return writer.getBooks();
	}
}
