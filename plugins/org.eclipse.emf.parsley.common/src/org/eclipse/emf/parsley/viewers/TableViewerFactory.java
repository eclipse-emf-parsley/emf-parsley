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

package org.eclipse.emf.parsley.viewers;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

/**
 * @author Lorenzo Bettini - initial API and implementation
 * 
 */
public class TableViewerFactory {

	@Inject
	protected TableViewerColumnBuilder columnBuilder;
	
	public TableViewer createTableViewer(Composite parent, int style, EClass type) {
		TableViewer tableViewer = createTableViewer(parent, style);
		initialize(tableViewer, type);
		return tableViewer;
	}

	public TableViewer createTableViewer(Composite parent, int style, EClass type, IStructuredContentProvider contentProvider) {
		TableViewer tableViewer = createTableViewer(parent, style);
		initialize(tableViewer, type, contentProvider);
		return tableViewer;
	}

	public void initialize(TableViewer tableViewer, EClass eClass) {
		initialize(tableViewer, eClass, new ArrayContentProvider());
	}
	
	public void initialize(TableViewer tableViewer, EClass eClass, 
			IStructuredContentProvider contentProvider) {
		tableViewer.setContentProvider(contentProvider);
		columnBuilder.buildTableViewer(tableViewer, eClass);
	}
	
	public void fill(TableViewer tableViewer, Object object,
			EStructuralFeature eReference) {
		IObservableList observableList = EMFProperties.list(eReference).observe(object);
		tableViewer.setInput(observableList);
	}

	private TableViewer createTableViewer(Composite parent, int style) {
		Composite viewerContainer = new Composite(parent, SWT.BORDER);
		TableColumnLayout layout = new TableColumnLayout();
		viewerContainer.setLayout(layout);
		return new TableViewer(viewerContainer, style);
	}
}
