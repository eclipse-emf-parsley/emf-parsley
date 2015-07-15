/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.emf.parsley.viewers.TableViewerBuilder;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbenchPart;

import com.google.inject.Inject;

/**
 * A View that visualizes the list of elements of an EObject (it also acts as a
 * selection provider), filtered by the specified type.
 *
 * @author Francesco Guidieri - Initial contribution and API
 *
 */
public abstract class AbstractOnSelectionTableView extends
		AbstractOnSelectionView {

	@Inject
	protected ViewerFactory viewerFactory;
	
	@Inject
	private TableViewerBuilder tableViewerBuilder;

	@Inject
	protected FeaturesProvider featuresProvider;

	protected Composite parent;

	protected TableViewer tableViewer;

	public AbstractOnSelectionTableView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		this.parent = parent;
		createTableViewer();
		getSite().setSelectionProvider(tableViewer);
		parent.layout(true, true);
	}

	@Override
	protected void updateOnSelection(IWorkbenchPart sourcepart,
			ISelection selection) {
		if (tableViewer == null) {
			return;
		}
		EObject eObject = getFirstSelectedEObject(selection);
		tableViewer.setInput(null);
		if (eObject != null) {
			update(eObject);
		}
	}

	protected void update(EObject eObject) {
		EStructuralFeature feature = getEStructuralFeature();

		if (!eObject.eClass().getEAllStructuralFeatures().contains(feature)) {
			return;
		}

//		Object value = eObject.eGet(feature);
//		tableViewer.setInput(value);
		
		tableViewerBuilder.fill2(tableViewer, eObject, feature);
	}

	protected void createTableViewer() {
		ScrolledComposite scrolledComposite = new ScrolledComposite(parent,
				SWT.V_SCROLL | SWT.BORDER);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		tableViewer = viewerFactory.createTableViewer2(scrolledComposite,
				SWT.BORDER | SWT.FULL_SELECTION, getEClass());

		Table table = tableViewer.getTable();
		scrolledComposite.setContent(table);
		scrolledComposite.setMinSize(table
				.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	@Override
	public void setFocus() {
		tableViewer.getTable().setFocus();
	}

	/**
	 * @return the {@link EStructuralFeature} to retrieve the values of the
	 *         selected {@link EObject} to show on the table
	 */
	protected abstract EStructuralFeature getEStructuralFeature();

	/**
	 * The default implementation uses the {@link EStructuralFeature} returned by
	 * {@link #getEStructuralFeature()}
	 * @return the {@link EClass} to build the table columns
	 */
	protected EClass getEClass() {
		return (EClass) getEStructuralFeature().getEType();
	}
}
