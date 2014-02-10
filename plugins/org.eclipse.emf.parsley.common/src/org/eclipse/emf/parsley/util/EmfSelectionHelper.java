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
package org.eclipse.emf.parsley.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Tree;

/**
 * Utility methods for retrieving EMF stuff from several sources
 * (for instance, {@link ISelection}, {@link MouseEvent})
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 * 
 */
public class EmfSelectionHelper {

	public Object getFirstSelectedElement(ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ss = (IStructuredSelection) selection;
			return ss.getFirstElement();
		}
		return null;
	}

	public EObject getFirstSelectedEObject(ISelection selection) {
		Object selected = getFirstSelectedElement(selection);
		if (selected instanceof EObject) {
			return (EObject) selected;
		}
		return null;
	}
	
	public EObject getEObjectFromMouseEvent(MouseEvent event) {
		Object source = event.getSource();
		if (source instanceof Tree) {
			Tree tree = (Tree) source;
			if (tree.getSelectionCount() == 1) {
				Object selection = tree.getSelection()[0].getData();
				if (selection instanceof EObject) {
					return (EObject) selection;
				}
			}
		}
		return null;
	}
}
