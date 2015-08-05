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
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.util.EmfParsleyUtil;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
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

	/**
	 * Builds and fills with contents, which are assumed to be of the specified
	 * {@link EClass}, a {@link TableViewer}; it defaults to using an
	 * {@link ArrayContentProvider}
	 * 
	 * @param tableViewer
	 * @param contents
	 * @param eClass
	 */
	public void buildAndFill(TableViewer tableViewer, Object contents,
			EClass eClass) {
		buildAndFill(tableViewer, contents, eClass, new ArrayContentProvider());
//		buildAndFill2(tableViewer, contents, eClass, new ObservableListContentProvider());
	}

	
	public void buildAndFill(TableViewer tableViewer, Object contents,
			EClass eClass,EReference eReference) {
		buildAndFill2(tableViewer, contents, eClass, eReference, new ObservableListContentProvider());
	}
	/**
	 * Builds and fills with contents, which are assumed to be of the specified
	 * {@link EClass}, a {@link TableViewer}, and uses the specified content
	 * provider.
	 * 
	 * @param tableViewer
	 * @param contents
	 * @param eClass
	 * @param contentProvider
	 */
	public void buildAndFill(TableViewer tableViewer, Object contents,
			EClass eClass, IStructuredContentProvider contentProvider) {
		build(tableViewer, eClass, contentProvider);
		fill(tableViewer, contents, contentProvider);
	}

	
	public void buildAndFill2(TableViewer tableViewer, Object object,
			EClass eClass, EReference eReference, ObservableListContentProvider contentProvider) {
		build2(tableViewer, eClass, contentProvider);
		fill2(tableViewer, object, eReference);
	}

	public void fill(TableViewer tableViewer, Object contents,
			IStructuredContentProvider contentProvider) {
		tableViewer.setContentProvider(contentProvider);
		tableViewer.setInput(EmfParsleyUtil.ensureCollection(contents));
	}
	
	public void fill2(TableViewer tableViewer, Object object,EStructuralFeature eReference) {
		IObservableList observableList = EMFProperties.list(eReference).observe(object);
		tableViewer.setInput(observableList);
	}
	
	
	public void build2(TableViewer tableViewer, EClass eClass) {
		build2(tableViewer, eClass, new ObservableListContentProvider());
	}
	
	public void build(TableViewer tableViewer, EClass eClass,
			IStructuredContentProvider contentProvider) {
		columnBuilder.buildTableViewer(tableViewer, eClass, contentProvider);
	}
	
	public void build2(TableViewer tableViewer, EClass eClass,
			ObservableListContentProvider contentProvider) {
		columnBuilder.buildTableViewer2(tableViewer, eClass, contentProvider);
		tableViewer.setContentProvider(contentProvider);
	}
	
	public TableViewer createTableViewer(Composite parent, int style, EClass type) {
		TableViewer tableViewer = internal_createTableViewer(parent, style);
		build2(tableViewer, type);
		return tableViewer;
	}

	public TableViewer createTableViewer(Composite parent, int style, EClass type, Object content) {
		TableViewer tableViewer = internal_createTableViewer(parent, style);
		buildAndFill(tableViewer, content, type);
		return tableViewer;
	}


	private TableViewer internal_createTableViewer(Composite parent, int style) {
		Composite viewerContainer = new Composite(parent, SWT.NONE);
		TableColumnLayout layout = new TableColumnLayout();
		viewerContainer.setLayout(layout);
		TableViewer tableViewer = new TableViewer(viewerContainer, style);
		return tableViewer;
	}
}
