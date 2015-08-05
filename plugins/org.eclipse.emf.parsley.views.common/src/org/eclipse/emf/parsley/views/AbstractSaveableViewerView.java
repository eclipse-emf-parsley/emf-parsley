/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.views;


import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.parsley.menus.ViewerContextMenuHelper;
import org.eclipse.emf.parsley.viewers.IViewerMouseListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;

import com.google.inject.Inject;
import com.google.inject.Provider;

public abstract class AbstractSaveableViewerView extends AbstractSaveableView implements IViewerProvider {

	@Inject
	private ViewerContextMenuHelper contextMenuHelper;
	
	@Inject
	protected Provider<IViewerMouseListener> viewerMouseListenerProvider;

	@Override
	protected void postCommandStackChanged(Command mostRecentCommand) {
		if (mostRecentCommand != null
				&& (mostRecentCommand instanceof CreateChildCommand
						||
						mostRecentCommand instanceof AddCommand)) {
			setSelectionToViewer(mostRecentCommand.getAffectedObjects());
		}
	}

	protected void setSelectionToViewer(Collection<?> collection) {
		final Collection<?> theSelection = collection;
		if (theSelection != null && !theSelection.isEmpty()) {
			Runnable runnable = new Runnable() {
				@Override
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

	

	protected void addContextMenu(StructuredViewer viewer) {
		contextMenuHelper.addViewerContextMenu(viewer, editingDomain, this);
	}

	/**
	 * Adds the {@link IViewerMouseListener} specified in the guice module.
	 * 
	 * @param viewer
	 */
	public void addMouseListener(StructuredViewer viewer) {
		viewer.getControl().addMouseListener(viewerMouseListenerProvider.get());
	}

}
