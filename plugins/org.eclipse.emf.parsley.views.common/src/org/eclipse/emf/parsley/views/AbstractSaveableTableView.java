/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Francesco Guidieri - Initial contribution and API
 * Lorenzo Bettini - some cleanup
 *******************************************************************************/
package org.eclipse.emf.parsley.views;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

/**
 * @author Francesco Guidieri - Initial contribution and API
 * @author Lorenzo Bettini - some cleanup
 */
public abstract class AbstractSaveableTableView extends AbstractSaveableViewerView {

	@Inject
	private ViewerFactory viewerFactory;

	private TableViewer tableViewer;

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		tableViewer = viewerFactory.
				createTableViewer(parent, createTableStyles(), getEClass());
		tableViewer.setInput(getResource());

		afterCreateViewer();
	}

	protected int createTableStyles() {
		return SWT.BORDER | SWT.FULL_SELECTION;
	}

	/**
	 * @return the {@link EClass} for objects to be shown in the table
	 */
	protected abstract EClass getEClass();

	@Override
	public void setFocus() {
		tableViewer.getTable().setFocus();
	}

	@Override
	public TableViewer getViewer() {
		return tableViewer;
	}

}
