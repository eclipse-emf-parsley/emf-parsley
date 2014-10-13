/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - initial API and implementation
 * Lorenzo Bettini - aligned to other composites
 *******************************************************************************/
package org.eclipse.emf.parsley.widgets;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.parsley.builders.TableViewerBuilder;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

/**
 * A generic composite with a Table and a Form with details of the selected
 * object in the tree.
 * 
 * @author Francesco Guidieri
 * @author Lorenzo Bettini
 */
public class TableFormComposite extends AbstractMasterDetailComposite {
	
	private TableViewerBuilder tableViewerBuilder;
	private TableViewer tableViewer;
	
	public TableFormComposite(Composite parent, int style) {
		super(parent, style);
	}
	
	protected StructuredViewer createViewer(Composite parent) {
		tableViewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		return tableViewer;
	}
	
	public void buildTable(EClass type, IStructuredContentProvider contentProvider){
		removeExistingColumns();
		tableViewerBuilder.build(tableViewer, type,  contentProvider);
	}

	public TableViewerBuilder getTableViewerBuilder() {
		return tableViewerBuilder;
	}

	@Inject
	public void setTableViewerBuilder(TableViewerBuilder tableViewerBuilder) {
		this.tableViewerBuilder = tableViewerBuilder;
	}
	
	
	@Override
	public void update(Object contents) {
		tableViewerBuilder.fill(tableViewer, contents, new ArrayContentProvider());
		getPagebook().showPage(tableViewer.getControl());
	}

	private void removeExistingColumns() {
		for (int i=tableViewer.getTable().getColumns().length;i>0;i--) {
			tableViewer.getTable().getColumns()[i-1].dispose();
		}
		tableViewer.getTable().pack();
	}

	public void buildTable(EClass eType) {
		buildTable(eType, new ArrayContentProvider());
	}
	
}
