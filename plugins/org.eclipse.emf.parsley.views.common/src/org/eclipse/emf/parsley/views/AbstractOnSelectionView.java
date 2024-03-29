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


import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.util.EmfSelectionHelper;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import com.google.inject.Inject;

/**
 * An abstract View that reacts on selection (it should show something related
 * to an emf resource or object).
 *
 * @author Lorenzo Bettini
 *
 */
public abstract class AbstractOnSelectionView extends ViewPart {

	@Inject
	protected EmfSelectionHelper selectionHelper;

	// the listener we register with the selection service
	private ISelectionListener listener = (sourcepart, selection) -> {
// we ignore our own selections
if (sourcepart != this) {
	updateOnSelection(sourcepart, selection);
}
};

	protected abstract void updateOnSelection(IWorkbenchPart sourcepart,
			ISelection selection);

	@Override
	public void createPartControl(Composite parent) {
		getSite().getWorkbenchWindow().getSelectionService()
				.addSelectionListener(listener);
	}

	protected Object getFirstSelectedElement(ISelection selection) {
		return selectionHelper.getFirstSelectedElement(selection);
	}

	protected EObject getFirstSelectedEObject(ISelection selection) {
		return selectionHelper.getFirstSelectedEObject(selection);
	}

	@Override
	public void dispose() {
		// important: We need do unregister our listener when the view is
		// disposed
		getSite().getWorkbenchWindow().getSelectionService()
				.removeSelectionListener(listener);
		super.dispose();
	}
}
