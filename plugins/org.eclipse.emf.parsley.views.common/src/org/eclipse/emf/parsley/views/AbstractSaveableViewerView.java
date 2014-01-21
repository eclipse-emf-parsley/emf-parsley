/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.views;


import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.parsley.edit.actionbar.WorkbenchActionBarContributor;
import org.eclipse.emf.parsley.viewers.ViewerInitializer;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;

import com.google.inject.Inject;

public abstract class AbstractSaveableViewerView extends AbstractSaveableView implements IMenuListener, IViewerProvider {

	@Inject
//	private TreeActionBarContributor actionBarContributor;
	private WorkbenchActionBarContributor actionBarContributor;

	@Inject
	private ViewerInitializer viewerInitializer;

	@Override
	protected void postCommandStackChanged(Command mostRecentCommand) {
		if (mostRecentCommand != null
				&& mostRecentCommand instanceof CreateChildCommand) {
			setSelectionToViewer(mostRecentCommand.getAffectedObjects());
		}
	}

	protected void setSelectionToViewer(Collection<?> collection) {
		final Collection<?> theSelection = collection;
		if (theSelection != null && !theSelection.isEmpty()) {
			Runnable runnable = new Runnable() {
				public void run() {
					// Try to select the items in the current viewer.
					if (getViewer() != null) {
						getViewer()
								.setSelection(
										new StructuredSelection(
												theSelection.toArray()), true);
					}
				}
			};
			getSite().getShell().getDisplay().asyncExec(runnable);
		}
	}

	public void menuAboutToShow(IMenuManager menuManager) {
		actionBarContributor.menuAboutToShow(menuManager);
	}

	protected void addContextMenu(StructuredViewer viewer) {
//		viewerInitializer.addContextMenu(viewer, actionBarContributor,
//				editingDomain, this);
		viewerInitializer.addContextMenu(viewer, actionBarContributor,
				editingDomain, this, this);
	}

	protected void addMouseListener(StructuredViewer viewer) {
		viewerInitializer.addMouseListener(viewer);
	}

}
