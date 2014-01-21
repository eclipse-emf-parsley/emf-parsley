/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.views;


import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.viewers.ViewerInitializer;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

public abstract class AbstractSaveableTreeView extends AbstractSaveableViewerView {

	@Inject
	protected ViewerInitializer viewerInitializer;

	protected TreeViewer treeViewer;

	protected Object getContents(Resource resource) {
		return resource;
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		treeViewer = new TreeViewer(parent);
		viewerInitializer.initialize(treeViewer, getContents(getResource()));

		addContextMenu(treeViewer);
		addMouseListener(treeViewer);
		
		getSite().setSelectionProvider(treeViewer);
	}

	@Override
	public void setFocus() {
		treeViewer.getTree().setFocus();
	}

	public StructuredViewer getViewer() {
		return treeViewer;
	}

	public TreeViewer getTreeViewer() {
		return treeViewer;
	}

}
