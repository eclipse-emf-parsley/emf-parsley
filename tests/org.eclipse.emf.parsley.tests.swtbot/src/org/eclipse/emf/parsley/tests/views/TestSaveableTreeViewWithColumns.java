/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.views;

import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage;
import org.eclipse.emf.parsley.views.SaveableTreeView;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Francesco Guidieri
 *
 */
public class TestSaveableTreeViewWithColumns extends SaveableTreeView {

	@Override
	protected TreeViewer createAndInitializeTreeViewer(Composite parent) {
		Composite viewerContainer = new Composite(parent, SWT.NONE);
		TreeColumnLayout layout = new TreeColumnLayout();
		viewerContainer.setLayout(layout);
		TreeViewer treeViewer = super.createAndInitializeTreeViewer(viewerContainer);
		viewerFactory.buildColumns(treeViewer, EXTLibraryPackage.eINSTANCE.getWriter());
		return treeViewer;
	}

}
