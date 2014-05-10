/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - Initial contribution and API
 * Lorenzo Bettini - contributions and testing
 *******************************************************************************/
package org.eclipse.emf.parsley.views;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.factories.TableFormFactory;
import org.eclipse.emf.parsley.factories.ViewerFactory;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.emf.parsley.widgets.TableFormComposite;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;

import com.google.inject.Inject;

/**
 * A View that visualizes a table with the list of elements of an EObject (it also acts as a
 * selection provider), filtered by the specified type (EClass) and feature.
 *
 * @author Francesco Guidieri - Initial contribution and API
 * @author Lorenzo Bettini
 */
public abstract class AbstractOnSelectionTableFormView extends
		AbstractOnSelectionView {

	@Inject
	protected ViewerFactory viewerFactory;

	@Inject
	protected FeaturesProvider featuresProvider;

	@Inject
	protected TableFormFactory tableFormFactory;

	protected TableFormComposite tableFormDetailComposite;

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		tableFormDetailComposite = tableFormFactory.createTableFormMasterDetailComposite(parent,
				SWT.BORDER, getEClass());
	}

	@Override
	protected void updateOnSelection(IWorkbenchPart sourcepart,
			ISelection selection) {

		EObject eObject = getFirstSelectedEObject(selection);
		tableFormDetailComposite.update(null);
		if (eObject != null) {
			update(eObject);
		}
	}

	protected void update(EObject eObject) {
		EStructuralFeature feature = getEStructuralFeature();

		if (!eObject.eClass().getEAllStructuralFeatures().contains(feature))
			return;

		Object value = eObject.eGet(feature);
		tableFormDetailComposite.update(value);
	}


	public void setFocus() {
		tableFormDetailComposite.getViewer().getControl().setFocus();
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
