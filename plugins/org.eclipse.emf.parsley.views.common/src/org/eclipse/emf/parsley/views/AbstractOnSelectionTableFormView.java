/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - Initial contribution and API
 * Lorenzo Bettini - contributions and testing
 *******************************************************************************/
package org.eclipse.emf.parsley.views;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.parsley.composite.CompositeFactory;
import org.eclipse.emf.parsley.composite.TableFormComposite;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;

import com.google.inject.Inject;

/**
 * A View that shows a table with the list of elements of an EObject (it also
 * acts as a selection provider), filtered by the specified type (EClass) and a
 * form.
 *
 * @author Francesco Guidieri - Initial contribution and API
 * @author Lorenzo Bettini - new API for table content provider
 */
public abstract class AbstractOnSelectionTableFormView extends AbstractOnSelectionViewerView {

	@Inject
	private CompositeFactory compositeFactory;

	private TableFormComposite tableFormComposite;

	@Override
	public StructuredViewer getViewer() {
		return tableFormComposite.getViewer();
	}

	@Override
	protected void updateOnSelection(IWorkbenchPart sourcepart, ISelection selection) {
		Object selected = getFirstSelectedElement(selection);
		// the content provider is able to handle any input
		tableFormComposite.update(selected);
	}

	@Override
	protected void createViewer(Composite parent) {
		tableFormComposite = compositeFactory.
			createTableFormComposite(parent, SWT.BORDER, getEClass());
	}

	@Override
	public void setFocus() {
		tableFormComposite.getViewer().getControl().setFocus();
	}

	/**
	 * {@link EClass} to build the table columns.
	 * 
	 * @return the {@link EClass} to build the table columns
	 */
	protected abstract EClass getEClass();

}
