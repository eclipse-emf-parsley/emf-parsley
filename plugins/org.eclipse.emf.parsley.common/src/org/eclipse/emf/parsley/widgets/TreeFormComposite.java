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
package org.eclipse.emf.parsley.widgets;


import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * A generic composite with a Tree and a Form with details of the selected
 * object in the tree.
 * 
 * @author Lorenzo Bettini, Francesco Guidieri
 * 
 */
public class TreeFormComposite extends AbstractMasterDetailComposite {

	public TreeFormComposite(Composite parent, int style) {
		this(parent, style, SWT.VERTICAL, new int[0]);
	}
	
	public TreeFormComposite(Composite parent, int style, int sashStyle, int[] weights) {
		super(parent, style, sashStyle, weights);
	}

	protected StructuredViewer createViewer(Composite parent) {
		return new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
	}

}
