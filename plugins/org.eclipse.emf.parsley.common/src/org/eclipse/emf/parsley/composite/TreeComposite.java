/*******************************************************************************
 * Copyright (c) 2018 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.composite;

import org.eclipse.emf.parsley.inject.parameters.CompositeParameters;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.layout.FillLayout;

import com.google.inject.Inject;

/**
 * A generic composite with a {@link TreeViewer}.
 * 
 * @author Lorenzo Bettini
 * @since 2.0
 */
public class TreeComposite extends AbstractViewerMasterComposite {

	@Inject
	private ViewerFactory viewerFactory;

	@Inject
	public TreeComposite(CompositeParameters params) {
		super(params);
		setLayout(new FillLayout());
	}

	@Override
	protected StructuredViewer createStructuredViewer() {
		TreeViewer treeViewer = viewerFactory.createTreeViewer(this, getStyle());
		return treeViewer;
	}

}
