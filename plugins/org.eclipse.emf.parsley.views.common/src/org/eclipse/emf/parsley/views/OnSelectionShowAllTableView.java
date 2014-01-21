/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.emf.parsley.views;


import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.factories.ViewerFactory;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbenchPart;

import com.google.inject.Inject;

/**
 * A View that visualizes the list of elements of an emf selected resource (it
 * also acts as a selection provider).
 * 
 * @author Lorenzo Bettini
 * 
 */
public class OnSelectionShowAllTableView extends AbstractOnSelectionView {

	@Inject
	protected ViewerFactory viewerFactory;

	@Inject
	protected FeaturesProvider featuresProvider;

	protected Composite parent;

	protected ScrolledComposite scrolledComposite;

	public OnSelectionShowAllTableView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		this.parent = parent;
		resetView();
	}

	@Override
	protected void updateOnSelection(IWorkbenchPart sourcepart,
			ISelection selection) {
		resetView();

		EObject eObject = getFirstSelectedEObject(selection);
		if (eObject != null) {
			Composite composite = new Composite(scrolledComposite, SWT.BORDER);
			scrolledComposite.setContent(composite);
			composite.setLayout(new GridLayout(1, false));
			composite.setLayoutData(new GridData(GridData.FILL_BOTH));

			buildTableForSelectedObject(eObject, composite);

			composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			parent.layout(true, true);
		}
	}

	/**
	 * Builds many tables: the first one with the features of the passed eObject
	 * and, in turn, a table for each single multi feature of the eObject (each
	 * such table with the contents of the multi feature)
	 * 
	 * @param eObject
	 * @param composite
	 */
	protected void buildTableForSelectedObject(EObject eObject,
			Composite composite) {
		EClass eObjectEClass = eObject.eClass();
		buildTable(eObject, eObjectEClass, composite, eObjectEClass.getName());
		List<EStructuralFeature> features = featuresProvider
				.getFeatures(eObjectEClass);
		for (EStructuralFeature eStructuralFeature : features) {
			if (eStructuralFeature.isMany()) {
				EClassifier eType = eStructuralFeature.getEType();
				if (eType instanceof EClass) {
					EClass eClass = (EClass) eType;
					buildTable(eObject.eGet(eStructuralFeature), eClass,
							composite, eStructuralFeature.getName());
				}
			}
		}
	}

	/**
	 * @param object
	 *            the contents to show in the table
	 * @param eClass
	 *            the EClass of the contents (that is, the EClass of the object
	 *            if the contents is a single object, or the EClass of the
	 *            objects in the list, if the contents is a list)
	 * @param composite
	 * @param label
	 */
	protected void buildTable(Object object, EClass eClass,
			Composite composite, String label) {
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setText(label);

		TableViewer tableViewer = viewerFactory.createTableViewer(composite,
				SWT.BORDER | SWT.FULL_SELECTION, object, eClass);

		Table table = tableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

	private void resetView() {
		if (scrolledComposite != null)
			scrolledComposite.dispose();

		scrolledComposite = new ScrolledComposite(parent, SWT.V_SCROLL
				| SWT.BORDER);
		scrolledComposite.setExpandHorizontal(true);
	}

	public void setFocus() {
		scrolledComposite.setFocus();
	}

}
