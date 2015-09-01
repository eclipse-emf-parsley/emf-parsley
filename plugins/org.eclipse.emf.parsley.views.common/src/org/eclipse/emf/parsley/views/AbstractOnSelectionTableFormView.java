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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.composite.TableFormComposite;
import org.eclipse.emf.parsley.composite.TableFormFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;

import com.google.inject.Inject;

/**
 * A View that visualizes a table with the list of elements of an EObject (it also acts as a
 * selection provider), filtered by the specified type (EClass) and a form.
 *
 * @author Francesco Guidieri - Initial contribution and API
 * @author Lorenzo Bettini - new API for table content provider
 */
public abstract class AbstractOnSelectionTableFormView extends
		AbstractOnSelectionView {

	@Inject
	private TableFormFactory tableFormFactory;

	private TableFormComposite tableFormDetailComposite;

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		tableFormDetailComposite = tableFormFactory.
			createTableFormMasterDetailComposite(parent,
				SWT.BORDER, getEClass());
	}

	@Override
	protected void updateOnSelection(IWorkbenchPart sourcepart,
			ISelection selection) {
		Object selected = getFirstSelectedElement(selection);
		// the content provider is able to handle any input
		tableFormDetailComposite.update(selected);
	}

	@Override
	public void setFocus() {
		tableFormDetailComposite.getViewer().getControl().setFocus();
	}

	/**
	 * @return the {@link EStructuralFeature} to retrieve the values of the
	 *         selected {@link EObject} to show on the table
	 */
	protected abstract EStructuralFeature getEStructuralFeature();

	/**
	 * The default implementation uses the {@link EStructuralFeature} returned by
	 * {@link #getEStructuralFeature()}
	 * @return the {@link EClass} to build the table columns
	 */
	protected EClass getEClass() {
		return (EClass) getEStructuralFeature().getEType();
	}

	public StructuredViewer getViewer() {
		return tableFormDetailComposite.getViewer();
	}

}
