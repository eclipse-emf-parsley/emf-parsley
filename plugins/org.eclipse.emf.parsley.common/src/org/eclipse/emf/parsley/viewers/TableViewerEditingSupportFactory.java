/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.viewers;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ColumnViewer;

import com.google.inject.Inject;
import com.google.inject.MembersInjector;

/**
 * Factory for {@link TableViewerEditingSupport}.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
class TableViewerEditingSupportFactory {

	@Inject
	private MembersInjector<TableViewerEditingSupport> membersInjector;

	public TableViewerEditingSupport createTableViewerEditingSupport(ColumnViewer viewer,
			EStructuralFeature eStructuralFeature) {
		TableViewerEditingSupport editingSupport = new TableViewerEditingSupport(viewer, eStructuralFeature);
		membersInjector.injectMembers(editingSupport);
		return editingSupport;
	}

}