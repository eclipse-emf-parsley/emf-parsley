/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.handlers;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;

/**
 * This class is used in editors to manage the outline selection.
 * @author Lorenzo Bettini
 * @see EmfAbstractEditor
 */
public class OutlineSelectionHandler {
	protected StructuredViewer selectionViewer;

	public StructuredViewer getSelectionViewer() {
		return selectionViewer;
	}

	public void setSelectionViewer(StructuredViewer selectionViewer) {
		this.selectionViewer = selectionViewer;
	}

	public void handleContentOutlineSelection(ISelection selection) {
		if (selectionViewer != null && !selection.isEmpty()
				&& selection instanceof IStructuredSelection) {
			Iterator<?> selectedElements = ((IStructuredSelection) selection)
					.iterator();
			if (selectedElements.hasNext()) {
				// Get the first selected element.
				//
				Object selectedElement = selectedElements.next();

				ArrayList<Object> selectionList = new ArrayList<Object>();
				selectionList.add(selectedElement);
				while (selectedElements.hasNext()) {
					selectionList.add(selectedElements.next());
				}

				// Set the selection to the widget.
				//
				selectionViewer.setSelection(new StructuredSelection(
						selectionList));
			}
		}
	}
}
