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
import org.eclipse.emf.parsley.composite.CompositeFactory;
import org.eclipse.emf.parsley.composite.TreeFormComposite;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

public abstract class AbstractSaveableTreeFormView extends AbstractSaveableViewerView{

	@Inject
	private CompositeFactory compositeFactory;

	private TreeFormComposite treeFormComposite;

	protected Object getContents(Resource resource) {
		return resource;
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		treeFormComposite = compositeFactory
				.createTreeFormComposite(parent, SWT.BORDER);

		treeFormComposite.update(getContents(getResource()));

		afterCreateViewer();
	}

	public void forceReloadResource(){
		treeFormComposite.update(getContents(getResource()));
	}

	@Override
	public void setFocus() {
		treeFormComposite.setFocus();
	}

	@Override
	public StructuredViewer getViewer() {
		return treeFormComposite.getViewer();
	}

}
