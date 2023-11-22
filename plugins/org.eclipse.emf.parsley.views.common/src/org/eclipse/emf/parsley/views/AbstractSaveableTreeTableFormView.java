/*******************************************************************************
 * Copyright (c) 2018 RCP Vision (http://www.rcp-vision.com) and others.
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
import org.eclipse.emf.parsley.composite.CompositeFactory;
import org.eclipse.emf.parsley.composite.TreeTableFormComposite;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 * @since 2.0
 *
 */
public abstract class AbstractSaveableTreeTableFormView extends AbstractSaveableViewerView<TreeViewer> {

	@Inject
	private CompositeFactory compositeFactory;

	private TreeTableFormComposite treeTableFormComposite;

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		treeTableFormComposite = compositeFactory
			.createTreeTableFormComposite(parent, SWT.BORDER, getEClass());
		treeTableFormComposite.update(getResource());

		afterCreateViewer();
	}

	@Override
	public void setFocus() {
		treeTableFormComposite.setFocus();
	}

	@Override
	public TreeViewer getViewer() {
		return treeTableFormComposite.getViewer();
	}

	/**
	 * @return the {@link EClass} for objects to be shown in the table
	 */
	protected abstract EClass getEClass();

}
