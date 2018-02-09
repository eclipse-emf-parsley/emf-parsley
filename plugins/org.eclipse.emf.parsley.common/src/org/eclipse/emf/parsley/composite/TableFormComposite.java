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
package org.eclipse.emf.parsley.composite;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.inject.parameters.CompositeParameters;
import org.eclipse.emf.parsley.inject.parameters.EClassParameter;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

/**
 * A generic composite with a Table and a Form with details of the selected
 * object in the table.
 * 
 * @author Francesco Guidieri
 * @author Lorenzo Bettini
 */
public class TableFormComposite extends AbstractMasterDetailComposite {

	@Inject
	private ViewerFactory viewerFactory;

	@Inject
	private CompositeFactory compositeFactory;

	private EClass eClass;

	/**
	 * @since 2.0
	 */
	@Inject
	public TableFormComposite(CompositeParameters params, EClassParameter eClassParam) {
		super(params);
		this.eClass = eClassParam.getEClass();
	}

	@Override
	protected StructuredViewer createViewer(Composite parent) {
		return viewerFactory.createTableViewer(parent,
				SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION, eClass);
	}

	/**
	 * @since 2.0
	 */
	@Override
	protected FormDetailComposite createDetailComposite(Composite parent, EObject selectedObject) {
		return compositeFactory.createFormDetailComposite(parent, SWT.BORDER, selectedObject);
	}

}
