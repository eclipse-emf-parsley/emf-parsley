/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Francesco Guidieri - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.views;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.parsley.composite.CompositeFactory;
import org.eclipse.emf.parsley.composite.TableFormComposite;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

/**
 * @author Francesco Guidieri - Initial contribution and API
 * @author Lorenzo Bettini - aligned to {@link AbstractSaveableTableView}
 *
 */
public abstract class AbstractSaveableTableFormView extends AbstractSaveableViewerView<TableViewer> {

	@Inject
	private CompositeFactory compositeFactory;

	private TableFormComposite tableFormComposite;

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		tableFormComposite = compositeFactory
			.createTableFormComposite(parent, SWT.BORDER, getEClass());
		tableFormComposite.update(getResource());

		afterCreateViewer();
	}

	@Override
	public void setFocus() {
		tableFormComposite.setFocus();
	}

	/**
	 * @since 2.0
	 */
	@Override
	public TableViewer getViewer() {
		return tableFormComposite.getViewer();
	}

	/**
	 * @return the {@link EClass} for objects to be shown in the table
	 */
	protected abstract EClass getEClass();

}
