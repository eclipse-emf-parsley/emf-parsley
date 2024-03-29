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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;

import com.google.inject.Inject;

/**
 * A View that shows the list of elements of an EObject (it also acts as a
 * selection provider), filtered by the specified type.
 *
 * @author Francesco Guidieri - Initial contribution and API
 * @author Lorenzo Bettini - new API for table content provider
 *
 */
public abstract class AbstractOnSelectionTableView extends AbstractOnSelectionViewerView {

	@Inject
	private ViewerFactory viewerFactory;

	private TableViewer tableViewer;

	@Override
	public StructuredViewer getViewer() {
		return tableViewer;
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		parent.layout(true, true);
	}

	@Override
	protected void updateOnSelection(IWorkbenchPart sourcepart, ISelection selection) {
		Object selected = getFirstSelectedElement(selection);
		// the content provider is able to handle any input
		tableViewer.setInput(selected);
	}

	@Override
	protected void createViewer(Composite parent) {
		tableViewer = viewerFactory.createTableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION, getEClass());
	}

	/**
	 * {@link EClass} to build the table columns.
	 *
	 * @return the {@link EClass} to build the table columns
	 */
	protected abstract EClass getEClass();
}
