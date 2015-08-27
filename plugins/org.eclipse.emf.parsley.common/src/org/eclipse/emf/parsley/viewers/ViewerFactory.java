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
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.parsley.resource.ResourceLoader;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Factory for viewers for EMF resources.
 * 
 * @author Lorenzo Bettini
 * 
 */
public class ViewerFactory {

	@Inject
	protected Provider<IContentProvider> contentProviderProvider;
	
	@Inject
	protected Provider<ILabelProvider> labelProviderProvider;
	
	@Inject
	protected ResourceLoader resourceLoader;

	@Inject
	protected Provider<AdapterFactoryEditingDomain> editingDomainProvider;
	
	@Inject
	protected TableViewerColumnBuilder columnBuilder;

	public void initialize(StructuredViewer viewer, Object object) {
		Object input;
		if(object instanceof URI){
			AdapterFactoryEditingDomain editingDomain=loadResource((URI)object);
			input= editingDomain.getResourceSet();
		}else if(object instanceof AdapterFactoryEditingDomain){
			AdapterFactoryEditingDomain editingDomain=(AdapterFactoryEditingDomain) object;
			input= editingDomain.getResourceSet();
		}else{
			input=object;
		}
		initialize(viewer, input, contentProviderProvider.get(), labelProviderProvider.get());
	}

	public void initialize(StructuredViewer viewer, Object input,
			IContentProvider contentProvider, IBaseLabelProvider labelProvider) {
		viewer.setContentProvider(contentProvider);
		if (labelProvider != null) {
			viewer.setLabelProvider(labelProvider);
		}
		viewer.setInput(input);
	}

	public TableViewer createTableViewer(Composite parent, int style, EClass type) {
		TableViewer tableViewer = createTableViewer(parent, style);
		buildColumns(tableViewer, type);
		return tableViewer;
	}

	public TableViewer createTableViewer(Composite parent, int style, EClass type, IStructuredContentProvider contentProvider) {
		TableViewer tableViewer = createTableViewer(parent, style);
		buildColumns(tableViewer, type, contentProvider);
		return tableViewer;
	}

	public void fill(TableViewer tableViewer, Object object, EStructuralFeature eReference) {
		IObservableList observableList = EMFProperties.list(eReference).observe(object);
		tableViewer.setInput(observableList);
	}

	public void buildColumns(TableViewer tableViewer, EClass eClass) {
		buildColumns(tableViewer, eClass, new ArrayContentProvider());
	}

	public void buildColumns(TableViewer tableViewer, EClass eClass, 
			IStructuredContentProvider contentProvider) {
		tableViewer.setContentProvider(contentProvider);
		columnBuilder.buildTableViewer(tableViewer, eClass);
	}
	
	private AdapterFactoryEditingDomain loadResource(URI resourceURI) {
		AdapterFactoryEditingDomain editingDomain = editingDomainProvider.get();
		resourceLoader.getResource(editingDomain, resourceURI);
		return editingDomain;
	}
	
	private TableViewer createTableViewer(Composite parent, int style) {
		Composite viewerContainer = new Composite(parent, SWT.BORDER);
		TableColumnLayout layout = new TableColumnLayout();
		viewerContainer.setLayout(layout);
		return new TableViewer(viewerContainer, style);
	}
	
}
