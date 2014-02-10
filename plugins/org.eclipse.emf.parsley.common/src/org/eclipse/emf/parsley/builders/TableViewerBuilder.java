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

package org.eclipse.emf.parsley.builders;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.parsley.util.EmfParsleyUtil;
import org.eclipse.emf.parsley.viewers.ViewerInitializer;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;

import com.google.inject.Inject;

/**
 * @author Lorenzo Bettini
 * 
 */
public class TableViewerBuilder {

	@Inject
	protected TableViewerColumnBuilder columnBuilder;

	@Inject
	protected ViewerInitializer viewerInitializer;

	public void buildAndFill(TableViewer tableViewer, Object contents,
			EClass eClass) {
		buildAndFill(tableViewer, contents, eClass, new ArrayContentProvider());
	}

	public void buildAndFill(TableViewer tableViewer, Object contents,
			EClass eClass, IStructuredContentProvider contentProvider) {
		build(tableViewer, eClass, contentProvider);
		fill(tableViewer, contents, contentProvider, null);
	}

	public void buildAndFill(TableViewer tableViewer, Object contents,
			EClass eClass, IStructuredContentProvider contentProvider,
			IBaseLabelProvider labelProvider) {
		build(tableViewer, eClass, contentProvider);
		fill(tableViewer, contents, contentProvider, labelProvider);
	}

	public void fill(TableViewer tableViewer, Object contents,
			IStructuredContentProvider contentProvider,
			IBaseLabelProvider labelProvider) {
		viewerInitializer.initialize(tableViewer,
				EmfParsleyUtil.ensureCollection(contents), contentProvider,
				labelProvider);
	}
	
	public void build(TableViewer tableViewer, EClass eClass,
			IStructuredContentProvider contentProvider) {
		columnBuilder.buildTableViewer(tableViewer, eClass, contentProvider);
	}
}
