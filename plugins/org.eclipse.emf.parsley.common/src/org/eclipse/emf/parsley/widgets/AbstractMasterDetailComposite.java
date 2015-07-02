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


import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.factories.FormFactory;
import org.eclipse.emf.parsley.util.EmfSelectionHelper;
import org.eclipse.emf.parsley.viewers.ViewerInitializer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

/**
 * A generic abstract composite with a viewer and a Form with details of the
 * selected object in the viewer. The viewer is intended to be defined by user.
 * 
 * @author Lorenzo Bettini, Francesco Guidieri
 * 
 */
public abstract class AbstractMasterDetailComposite extends Composite implements IViewerProvider {

	protected class SelectionChangedListener implements
			ISelectionChangedListener {
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			EObject selectedObject = emfSelectionHelper
					.getFirstSelectedEObject(event.getSelection());

			eObjectSelectionChanged(selectedObject);
		}

	}

	private ViewerInitializer viewerInitializer;

	private FormFactory formFactory;

	private EmfSelectionHelper emfSelectionHelper;

	private final StructuredViewer viewer;

	private final Composite masterComposite;

	private final Composite detailComposite;

	private FormDetailComposite detailForm;

	private SashForm sashForm;

	public AbstractMasterDetailComposite(Composite parent, int style) {
		this(parent, style, SWT.VERTICAL, new int[0]);
	}
	
	public AbstractMasterDetailComposite(Composite parent, int style, int sashStyle, int[] weights) {
		super(parent, style);
		setLayout( new FillLayout());
		sashForm = new SashForm(this, sashStyle);

		masterComposite = new Composite(sashForm, SWT.NONE);
		masterComposite.setLayout( new FillLayout());
		
		detailComposite = new Composite(sashForm, SWT.NONE);
		detailComposite.setLayout( new FillLayout());
		viewer = createViewer(masterComposite);
		viewer.addSelectionChangedListener(new SelectionChangedListener());
		if(weights.length>0){
			sashForm.setWeights(weights);
		}
	}

	@Override
	public StructuredViewer getViewer() {
		return viewer;
	}

	public void update(Object element) {
		if (element != null) {
			viewerInitializer.initialize(viewer, element);	
		}

	}

	protected abstract StructuredViewer createViewer(Composite parent);

	protected void eObjectSelectionChanged(EObject selectedObject) {
		if (detailForm != null) {
			detailForm.dispose();
		}

		if (selectedObject != null) {
			detailForm = createFormDetailComposite();
			detailForm.init(selectedObject);
			detailComposite.layout(true);
		}
	}

	protected FormDetailComposite createFormDetailComposite() {
		return formFactory.createFormDetailComposite(detailComposite,
				SWT.BORDER);
	}

	public ViewerInitializer getViewerInitializer() {
		return viewerInitializer;
	}

	@Inject
	public void setViewerInitializer(ViewerInitializer viewerInitializer) {
		this.viewerInitializer = viewerInitializer;
	}

	public FormFactory getFormFactory() {
		return formFactory;
	}

	@Inject
	public void setFormFactory(FormFactory formFactory) {
		this.formFactory = formFactory;
	}

	public EmfSelectionHelper getEmfSelectionHelper() {
		return emfSelectionHelper;
	}

	@Inject
	public void setEmfSelectionHelper(EmfSelectionHelper emfSelectionHelper) {
		this.emfSelectionHelper = emfSelectionHelper;
	}

	public SashForm getSashForm() {
		return sashForm;
	}

}
