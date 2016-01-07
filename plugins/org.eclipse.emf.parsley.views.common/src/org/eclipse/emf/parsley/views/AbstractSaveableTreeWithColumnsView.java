/*******************************************************************************
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.views;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public abstract class AbstractSaveableTreeWithColumnsView extends AbstractSaveableTreeView {

	@Override
	protected TreeViewer createAndInitializeTreeViewer(Composite parent) {
		return getViewerFactory().createTreeViewerWithColumns(parent, getEClass(), getContents());
	}

	/**
	 * @return the {@link EClass} used to get the features to represent in the columns
	 */
	protected abstract EClass getEClass();
}
