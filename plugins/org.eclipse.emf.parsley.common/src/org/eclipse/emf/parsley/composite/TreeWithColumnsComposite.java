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
import org.eclipse.jface.viewers.TreeViewer;

import com.google.inject.Inject;

/**
 * A generic composite with a {@link TreeViewer} with columns.
 * 
 * @author Lorenzo Bettini
 * @since 2.0
 */
public class TreeWithColumnsComposite extends TreeComposite {

	@Inject
	private ViewerFactory viewerFactory;

	private EClass eClass;

	@Inject
	public TreeWithColumnsComposite(CompositeParameters params, EClassParameter eClassParam) {
		super(params);
		this.eClass = eClassParam.getEClass();
	}

	@Override
	protected TreeViewer createStructuredViewer() {
		return viewerFactory.createTreeViewerWithColumns(this, getStyle(), eClass);
	}

}
