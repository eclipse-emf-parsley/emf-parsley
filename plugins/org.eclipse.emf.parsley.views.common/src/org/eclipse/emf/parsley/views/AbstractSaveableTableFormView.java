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


import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.factories.TableFormFactory;
import org.eclipse.emf.parsley.widgets.TableFormComposite;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

/**
 * @author Francesco Guidieri - Initial contribution and API
 * @author Lorenzo Bettini - aligned to {@link AbstractSaveableTableView}
 *
 */
public abstract class AbstractSaveableTableFormView extends AbstractSaveableViewerView	{

	@Inject
	protected TableFormFactory tableFormFactory;

	protected TableFormComposite tableFormComposite;

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		tableFormComposite = tableFormFactory
				.createTableFormMasterDetailComposite(parent, SWT.BORDER, getEClass());

		tableFormComposite.update(getContents(getResource()));

		addContextMenu(tableFormComposite.getViewer());
	}

	@Override
	public void setFocus() {
		tableFormComposite.setFocus();
	}

	@Override
	public StructuredViewer getViewer() {
		return tableFormComposite.getViewer();
	}
	
	/**
	 * @return the {@link EClass} for objects to be shown in the table
	 */
	protected abstract EClass getEClass();
	
	/**
	 * @param resource
	 * @return the contents from the passed resource to be shown in the table
	 */
	protected abstract Object getContents(Resource resource);

	@Override
	protected void mostRecentCommandAffectsResource(Command mostRecentCommand) {
		super.mostRecentCommandAffectsResource(mostRecentCommand);
		// for TableViewer the refresh does not seem to be automatic
		tableFormComposite.getViewer().refresh();
	}
}
