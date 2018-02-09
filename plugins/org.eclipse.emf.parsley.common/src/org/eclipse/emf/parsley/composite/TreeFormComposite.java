/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 * Francesco Guidieri - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.composite;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.EmfParsleyConstants;
import org.eclipse.emf.parsley.inject.parameters.CompositeParameters;
import org.eclipse.emf.parsley.viewers.IStructuredViewerProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * A generic composite with a Tree and a Form with details of the selected
 * object in the tree.
 * 
 * @author Francesco Guidieri
 * @author Lorenzo Bettini
 */
public class TreeFormComposite extends AbstractMasterDetailComposite implements IStructuredViewerProvider {

	@Inject
	private CompositeFactory compositeFactory;

	private TreeComposite treeComposite;

	/**
	 * @since 2.0
	 */
	@Inject
	public TreeFormComposite(CompositeParameters params,
			@Named(EmfParsleyConstants.TREE_FORM_SASH_STYLE) int sashStyle,
			@Named(EmfParsleyConstants.TREE_FORM_SASH_WEIGHTS) int[] weights) {
		super(params, sashStyle, weights);
	}

	/**
	 * @since 2.0
	 */
	@Override
	protected FormDetailComposite createDetailComposite(Composite parent, EObject selectedObject) {
		return compositeFactory.createFormDetailComposite(parent, SWT.BORDER, selectedObject);
	}

	/**
	 * @since 2.0
	 */
	@Override
	protected TreeComposite createMasterComposite(Composite parent) {
		treeComposite = compositeFactory.createTreeComposite(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		return treeComposite;
	}

	@Override
	public StructuredViewer getViewer() {
		return treeComposite.getViewer();
	}
}
