/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.emf.parsley.views;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.widgets.Composite;

/**
 * A View that visualizes the list of elements of an emf selected resource (it also acts as
 * a selection provider).
 * 
 * @author Lorenzo Bettini
 * 
 */
public class OnSelectionListView extends OnSelectionStructuredViewerAbstractView {

	public OnSelectionListView() {
	}

	@Override
	protected StructuredViewer createViewer(Composite parent) {
		return new ListViewer(parent);
	}
}
