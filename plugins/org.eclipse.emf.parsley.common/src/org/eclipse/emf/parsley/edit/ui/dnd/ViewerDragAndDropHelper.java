/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.edit.ui.dnd;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.emf.parsley.internal.edit.ui.dnd.DynamicEditingDomainViewerDropAdapter;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;

import com.google.inject.Inject;
import com.google.inject.MembersInjector;

/**
 * Helper methods to add drag and drop on a viewer.
 *
 * @author Lorenzo Bettini - Initial contribution and API
 */
public class ViewerDragAndDropHelper {

	@Inject
	private MembersInjector<DynamicEditingDomainViewerDropAdapter> membersInjector;

	/**
	 * With this version the editing domain will be retrieved dynamically from
	 * the object being dragged.
	 *
	 * @param viewer
	 */
	public void addDragAndDrop(StructuredViewer viewer) {
		addDragAndDrop(viewer, null);
	}

	/**
	 * If the passed editing domain is null, then the editing domain will be
	 * retrieved dynamically from the object being dragged.
	 *
	 * @param viewer
	 * @param editingDomain
	 */
	public void addDragAndDrop(StructuredViewer viewer, EditingDomain editingDomain) {
		int dndOperations = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
		Transfer[] transfers = new Transfer[] { LocalTransfer.getInstance() };
		viewer.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(viewer));
		if (editingDomain != null) {
			viewer.addDropSupport(dndOperations, transfers, new EditingDomainViewerDropAdapter(editingDomain, viewer));
		} else {
			DynamicEditingDomainViewerDropAdapter dynamicEditingDomainViewerDropAdapter = new DynamicEditingDomainViewerDropAdapter(
					viewer);
			membersInjector.injectMembers(dynamicEditingDomainViewerDropAdapter);
			viewer.addDropSupport(dndOperations, transfers, dynamicEditingDomainViewerDropAdapter);
		}
	}

}
