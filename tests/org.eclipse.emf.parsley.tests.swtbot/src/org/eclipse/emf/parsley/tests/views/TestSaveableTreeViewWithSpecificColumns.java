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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
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
public class TestSaveableTreeViewWithSpecificColumns extends SaveableTreeView {

	@Override
	protected TreeViewer createAndInitializeTreeViewer(Composite parent) {
		Composite viewerContainer = new Composite(parent, SWT.NONE);
		TreeColumnLayout layout = new TreeColumnLayout();
		viewerContainer.setLayout(layout);
		TreeViewer treeViewer = super.createAndInitializeTreeViewer(viewerContainer);
		@SuppressWarnings("serial")
		List<EStructuralFeature> features = new ArrayList<EStructuralFeature>() {
			{
				add(EXTLibraryPackage.eINSTANCE.getWriter_Name());
				add(EXTLibraryPackage.eINSTANCE.getAddressable_Address());
			}
		};
		getViewerFactory().buildColumns(treeViewer, features);
		return treeViewer;
	}

}
