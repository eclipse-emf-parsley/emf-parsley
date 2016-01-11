/*******************************************************************************
s * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.views;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.parsley.edit.ui.dnd.ViewerDragAndDropHelper;
import org.eclipse.emf.parsley.menus.ViewerContextMenuHelper;
import org.eclipse.emf.parsley.viewers.IStructuredViewerProvider;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;

import com.google.inject.Inject;

/**
 * An abstract View that reacts on selection and is based on a viewer.
 * 
 * @author Lorenzo Bettini
 * 
 */
public abstract class AbstractOnSelectionViewerView extends AbstractOnSelectionView
		implements IStructuredViewerProvider {

	@Inject
	private ViewerFactory viewerFactory;

	@Inject
	private ViewerContextMenuHelper contextMenuHelper;

	@Inject
	private ViewerDragAndDropHelper dragAndDropHelper;

	public AbstractOnSelectionViewerView() {
	}

	@Override
	protected void updateOnSelection(IWorkbenchPart sourcepart, ISelection selection) {
		Object element = getFirstSelectedElement(selection);
		viewerFactory.initialize(getViewer(), element);
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		createViewer(parent);
		afterCreateViewer();
	}

	/**
	 * This assumes that the viewer has already been created, that is,
	 * {@link #getViewer()} must not return null.
	 */
	protected void afterCreateViewer() {
		setViewerAsSelectionProvider();
		addContextMenuToViewer();
		addDragAndDropToViewer();
	}

	protected void setViewerAsSelectionProvider() {
		getSite().setSelectionProvider(getViewer());
	}

	/**
	 * Subclasses should implement this.
	 * 
	 * @param parent
	 */
	protected abstract void createViewer(Composite parent);

	@Override
	public void setFocus() {
		getViewer().getControl().setFocus();
	}

	public void init(URI resourceURI) {
		viewerFactory.initialize(getViewer(), resourceURI);
	}

	protected void addContextMenuToViewer() {
		contextMenuHelper.addViewerContextMenu(getViewer());
	}

	protected void addDragAndDropToViewer() {
		dragAndDropHelper.addDragAndDrop(getViewer());
	}

}
