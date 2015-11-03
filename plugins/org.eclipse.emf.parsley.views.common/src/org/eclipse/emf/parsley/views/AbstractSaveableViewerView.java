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
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.parsley.edit.ui.dnd.ViewerDragAndDropHelper;
import org.eclipse.emf.parsley.menus.ViewerContextMenuHelper;
import org.eclipse.emf.parsley.viewers.IStructuredViewerProvider;
import org.eclipse.emf.parsley.viewers.IViewerMouseListener;
import org.eclipse.jface.viewers.StructuredSelection;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * A common saveable view based on a viewer.
 * 
 * @author Francesco Guidieri - Initial contribution and API
 * @author Lorenzo Bettini - some refactoring
 */
public abstract class AbstractSaveableViewerView extends AbstractSaveableView implements IStructuredViewerProvider {

	@Inject
	private ViewerContextMenuHelper contextMenuHelper;

	@Inject
	private ViewerDragAndDropHelper dragAndDropHelper;

	@Inject
	private Provider<IViewerMouseListener> viewerMouseListenerProvider;

	/**
	 * This assumes that the viewer has already been created, that is,
	 * {@link #getViewer()} must not return null.
	 */
	protected void afterCreateViewer() {
		addContextMenuToViewer();
		addDragAndDropToViewer();
		addMouseListenerToViewer();
		setViewerAsSelectionProvider();
	}

	protected void setViewerAsSelectionProvider() {
		getSite().setSelectionProvider(getViewer());
	}

	/**
	 * This is called after mostRecentCommandAffectsResource, so that you can
	 * perform additional custom actions.
	 * 
	 * The default implementation is to select in the viewer the possible new
	 * added child; this will also make context menu work seamlessly (if the
	 * selection stays in the parent element, then it will not obviously change
	 * and the context menu actions will not be recreated and they will be
	 * stale).
	 * 
	 * @param mostRecentCommand
	 */
	@Override
	public void postCommandStackChanged(Command mostRecentCommand) {
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

	protected void addContextMenuToViewer() {
		contextMenuHelper.addViewerContextMenu(getViewer(), this);
	}

	protected void addDragAndDropToViewer() {
		dragAndDropHelper.addDragAndDrop(getViewer(), getEditingDomain());
	}

	/**
	 * Adds the {@link IViewerMouseListener} specified in the guice module.
	 * 
	 * @param viewer
	 */
	public void addMouseListenerToViewer() {
		getViewer().getControl().addMouseListener(viewerMouseListenerProvider.get());
	}

}
