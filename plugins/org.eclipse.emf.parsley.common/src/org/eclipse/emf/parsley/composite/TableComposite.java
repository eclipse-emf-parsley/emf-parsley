/*******************************************************************************
 * Copyright (c) 2018 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.composite;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.parsley.inject.parameters.CompositeParameters;
import org.eclipse.emf.parsley.inject.parameters.EClassParameter;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.layout.FillLayout;

import com.google.inject.Inject;

/**
 * A generic composite with a {@link TableViewer}.
 * 
 * @author Lorenzo Bettini
 * @since 2.0
 */
public class TableComposite extends AbstractViewerMasterComposite<TableViewer> {

	@Inject
	private ViewerFactory viewerFactory;

	private EClass eClass;

	@Inject
	public TableComposite(CompositeParameters params, EClassParameter eClassParam) {
		super(params);
		this.eClass = eClassParam.getEClass();
		setLayout(new FillLayout());
	}

	@Override
	protected TableViewer createStructuredViewer() {
		return viewerFactory.createTableViewer(this, getStyle(), eClass);
	}

}
