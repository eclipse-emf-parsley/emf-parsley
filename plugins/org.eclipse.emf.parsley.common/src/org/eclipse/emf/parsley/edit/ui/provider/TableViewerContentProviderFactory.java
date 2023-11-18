/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available underthe terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.edit.ui.provider;

import org.eclipse.emf.ecore.EClass;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Factory methods to create specific {@link TableViewerContentProvider}.
 *
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class TableViewerContentProviderFactory {

	@Inject
	private Provider<TableViewerContentProvider> provider;

	public TableViewerContentProvider createTableViewerContentProvider(EClass type) {
		TableViewerContentProvider contentProvider = provider.get();
		contentProvider.setEClass(type);
		return contentProvider;
	}
}
