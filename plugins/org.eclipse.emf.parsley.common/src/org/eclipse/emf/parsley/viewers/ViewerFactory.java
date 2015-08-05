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


import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.parsley.resource.ResourceLoader;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;

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

	

	public TreeViewer createTreeViewer(Composite parent, int style,Object obj) {
		TreeViewer treeViewer = new TreeViewer(parent, style);
		initialize(treeViewer, obj);
		return treeViewer;
	}

	public TreeViewer createTreeViewer(Tree tree, Object obj) {
		TreeViewer treeViewer = new TreeViewer(tree);
		initialize(treeViewer, obj);
		return treeViewer;
	}


	public TreeViewer createTreeViewer(Tree tree,
			AdapterFactoryEditingDomain editingDomain) {
		TreeViewer treeViewer = new TreeViewer(tree);
		initialize(treeViewer, editingDomain);
		return treeViewer;
	}

	public void initialize(StructuredViewer viewer, Object object) {
		Object input;
		if(object instanceof URI){
			AdapterFactoryEditingDomain editingDomain=(AdapterFactoryEditingDomain)loadResource((URI)object);
			input= editingDomain.getResourceSet();
		}else if(object instanceof AdapterFactoryEditingDomain){
			AdapterFactoryEditingDomain editingDomain=(AdapterFactoryEditingDomain) object;
			input= editingDomain.getResourceSet();
		}else{
			input=object;
		}
		initialize(viewer, input, contentProviderProvider.get(),
				labelProviderProvider.get());
	}

	/**
	 * @param viewer
	 * @param input
	 * @param contentProvider
	 * @param labelProvider
	 *            can be null (in that case it is not set)
	 */
	public void initialize(StructuredViewer viewer, Object input,
			IContentProvider contentProvider,
			IBaseLabelProvider labelProvider) {
		viewer.setContentProvider(contentProvider);
		if (labelProvider != null) {
			viewer.setLabelProvider(labelProvider);
		}
		viewer.setInput(input);
	}
	
	protected AdapterFactoryEditingDomain loadResource(URI resourceURI) {
		AdapterFactoryEditingDomain editingDomain = editingDomainProvider.get();
		resourceLoader.getResource(editingDomain, resourceURI);
		return editingDomain;
	}
}
