/*******************************************************************************
 * Copyright (c) 2018 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - aligned to other composites
 *******************************************************************************/
package org.eclipse.emf.parsley.composite;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.EmfParsleyConstants;
import org.eclipse.emf.parsley.inject.parameters.CompositeParameters;
import org.eclipse.emf.parsley.inject.parameters.EClassParameter;
import org.eclipse.emf.parsley.viewers.IStructuredViewerProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * A generic composite with a Tree, a Table showing the contents of the selected
 * element in the tree and a Form with details of the selected object in the
 * table.
 * 
 * @author Lorenzo Bettini
 * @since 2.0
 */
public class TreeTableFormComposite extends AbstractMasterDetailComposite implements IStructuredViewerProvider {

	@Inject
	private CompositeFactory compositeFactory;

	private EClass eClass;

	private TreeComposite treeComposite;

	private int sashStyle;

	@Inject
	public TreeTableFormComposite(CompositeParameters params, EClassParameter eClassParam,
			@Named(EmfParsleyConstants.TREE_FORM_SASH_STYLE) int sashStyle,
			@Named(EmfParsleyConstants.TREE_FORM_SASH_WEIGHTS) int[] weights) {
		super(params, sashStyle, weights);
		this.sashStyle = sashStyle;
		this.eClass = eClassParam.getEClass();
	}

	@Override
	protected AbstractMasterDetailComposite createDetailComposite(Composite parent, EObject selectedObject) {
		AbstractMasterDetailComposite tableFormComposite = compositeFactory.createTableFormComposite(parent, SWT.BORDER, eClass);
		// flip the orientation in the nested master-detail composite
		tableFormComposite.setSashFormOrientation(
			((sashStyle & SWT.VERTICAL) != 0) ? SWT.HORIZONTAL : SWT.VERTICAL
		);
		tableFormComposite.update(selectedObject);
		return tableFormComposite;
	}

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
