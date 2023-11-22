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
package org.eclipse.emf.parsley.views;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public abstract class AbstractSaveableTreeView extends AbstractSaveableViewerView<TreeViewer> {

	@Inject
	private ViewerFactory viewerFactory;

	private TreeViewer treeViewer;

	protected Object getContents() {
		return getContents(getResource());
	}

	protected Object getContents(Resource resource) {
		return resource;
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		treeViewer = createAndInitializeTreeViewer(parent);

		afterCreateViewer();
	}

	protected TreeViewer createAndInitializeTreeViewer(Composite parent) {
		TreeViewer viewer = new TreeViewer(parent);
		getViewerFactory().initialize(viewer, getContents());
		return viewer;
	}

	protected ViewerFactory getViewerFactory() {
		return viewerFactory;
	}

	@Override
	public void setFocus() {
		treeViewer.getTree().setFocus();
	}

	@Override
	public TreeViewer getViewer() {
		return treeViewer;
	}

}
