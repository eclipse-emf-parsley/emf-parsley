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

import org.eclipse.emf.parsley.composite.CompositeFactory;
import org.eclipse.emf.parsley.composite.TreeFormComposite;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;

import com.google.inject.Inject;

public class OnSelectionTreeFormView extends AbstractOnSelectionViewerView {

	@Inject
	private CompositeFactory compositeFactory;

	private TreeFormComposite treeFormDetailComposite;

	@Override
	public StructuredViewer getViewer() {
		return treeFormDetailComposite.getViewer();
	}

	@Override
	protected void createViewer(Composite parent) {
		treeFormDetailComposite = compositeFactory.createTreeFormComposite(parent, SWT.BORDER);
	}

	@Override
	protected void updateOnSelection(IWorkbenchPart sourcepart, ISelection selection) {
		treeFormDetailComposite.update(getFirstSelectedElement(selection));
	}

	@Override
	public void setFocus() {
		treeFormDetailComposite.setFocus();
	}

}
