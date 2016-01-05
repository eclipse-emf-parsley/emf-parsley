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

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.edit.ui.provider.TableViewerContentProviderFactory;
import org.eclipse.emf.parsley.resource.ResourceLoader;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
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
	private Provider<IContentProvider> contentProviderProvider;

	@Inject
	private Provider<ILabelProvider> labelProviderProvider;

	@Inject
	private ResourceLoader resourceLoader;

	@Inject
	private Provider<EditingDomain> editingDomainProvider;

	@Inject
	private TableViewerColumnBuilder columnBuilder;

	@Inject
	private TreeViewerColumnBuilder treeColumnBuilder;

	@Inject
	private TableViewerContentProviderFactory tableViewerContentProviderFactory;

	/**
	 * Initializes the viewer, and uses as input the resource specified by an
	 * {@link URI}.
	 * 
	 * @param viewer
	 * @param uri
	 */
	public void initialize(StructuredViewer viewer, URI uri) {
		initialize(viewer, loadResource(uri));
	}

	/**
	 * Initializes the viewer, and uses as input the resource set of the
	 * specified editingDomain.
	 * 
	 * @param viewer
	 * @param editingDomain
	 */
	public void initialize(StructuredViewer viewer, EditingDomain editingDomain) {
		initialize(viewer, editingDomain.getResourceSet());
	}

	/**
	 * Initializes the viewer, and uses as input the specified object.
	 * 
	 * @param viewer
	 * @param object
	 */
	public void initialize(StructuredViewer viewer, Object object) {
		initialize(viewer, object, contentProviderProvider.get(), labelProviderProvider.get());
	}

	/**
	 * Creates a {@link TableViewer} that will represent objects of the
	 * specified type.
	 * 
	 * @param parent
	 * @param style
	 * @param type
	 * @return
	 */
	public TableViewer createTableViewer(Composite parent, int style, EClass type) {
		TableViewer tableViewer = createTableViewer(parent, style);
		buildColumns(tableViewer, type);
		return tableViewer;
	}

	/**
	 * Creates a {@link TreeViewer} with columns; the tree will display the
	 * specified content and the columns will represent the features of the
	 * contents that are instances of the specified {@link EClass}.
	 * 
	 * @param parent
	 * @param type
	 *            to get the features to represent in the columns
	 * @param content
	 * @return
	 */
	public TreeViewer createTreeViewerWithColumns(Composite parent, EClass type, Object content) {
		Composite viewerContainer = new Composite(parent, SWT.NONE);
		TreeColumnLayout layout = new TreeColumnLayout();
		viewerContainer.setLayout(layout);
		TreeViewer treeViewer = new TreeViewer(viewerContainer);
		initialize(treeViewer, content);
		buildColumns(treeViewer, type);
		return treeViewer;
	}

	/**
	 * Initializes the specified {@link TableViewer} building its columns
	 * according to the specified type.
	 * 
	 * @param tableViewer
	 * @param eClass
	 */
	public void buildColumns(TableViewer tableViewer, EClass eClass) {
		buildColumns(tableViewer, eClass, tableViewerContentProviderFactory.createTableViewerContentProvider(eClass));
	}

	/**
	 * Initializes the specified {@link TreeViewer} building its columns
	 * according to the specified type.
	 * 
	 * @param treeViewer
	 * @param eClass
	 */
	public void buildColumns(TreeViewer treeViewer, EClass eClass) {
		treeColumnBuilder.buildTreeViewer(treeViewer, eClass);
	}

	/**
	 * Initializes the specified {@link TreeViewer} building its columns
	 * according to the specified {@link EStructuralFeature}s.
	 * 
	 * @param treeViewer
	 * @param typeFeatures
	 */
	public void buildColumns(TreeViewer treeViewer, List<EStructuralFeature> typeFeatures) {
		treeColumnBuilder.buildTreeViewer(treeViewer, typeFeatures);
	}

	private void initialize(StructuredViewer viewer, Object input, IContentProvider contentProvider,
			IBaseLabelProvider labelProvider) {
		viewer.setContentProvider(contentProvider);
		viewer.setLabelProvider(labelProvider);
		viewer.setInput(input);
	}

	private void buildColumns(TableViewer tableViewer, EClass eClass, IStructuredContentProvider contentProvider) {
		tableViewer.setContentProvider(contentProvider);
		columnBuilder.buildTableViewer(tableViewer, eClass);
	}

	private EditingDomain loadResource(URI resourceURI) {
		EditingDomain editingDomain = editingDomainProvider.get();
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
