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
package org.eclipse.emf.parsley.composite;

import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.inject.AfterInject;
import org.eclipse.emf.parsley.inject.EmfParsleyLifecycle;
import org.eclipse.emf.parsley.inject.InjectableComposite;
import org.eclipse.emf.parsley.inject.parameters.CompositeParameters;
import org.eclipse.emf.parsley.util.EmfSelectionHelper;
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
@EmfParsleyLifecycle
public abstract class AbstractMasterDetailComposite extends InjectableComposite implements IViewerProvider {

	private class SelectionChangedListener implements ISelectionChangedListener {
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			EObject selectedObject = emfSelectionHelper.getFirstSelectedEObject(event.getSelection());
			eObjectSelectionChanged(selectedObject);
		}
	}

	@Inject
	private EmfSelectionHelper emfSelectionHelper;

	private SashForm sashForm;

	private Composite masterParentComposite;

	private Composite detailParentComposite;

	private StructuredViewer viewer;

	private AbstractDetailComposite detailComposite;

	/**
	 * @since 2.0
	 */
	public AbstractMasterDetailComposite(CompositeParameters params) {
		this(params, SWT.VERTICAL, new int[0]);
	}

	/**
	 * @since 2.0
	 */
	public AbstractMasterDetailComposite(CompositeParameters params, int sashStyle, int[] weights) {
		super(params);
		setLayout(new FillLayout());
		sashForm = new SashForm(this, sashStyle);

		masterParentComposite = new Composite(sashForm, SWT.NONE);
		masterParentComposite.setLayout(new FillLayout());

		detailParentComposite = new Composite(sashForm, SWT.NONE);
		detailParentComposite.setLayout(new FillLayout());

		if (weights.length > 0) {
			sashForm.setWeights(weights);
		}
	}

	/**
	 * This will be called after the construction has finished.
	 */
	@AfterInject
	private void setupViewer() {
		viewer = createViewer(masterParentComposite);
		viewer.addSelectionChangedListener(new SelectionChangedListener());
	}

	@Override
	public StructuredViewer getViewer() {
		return viewer;
	}

	/**
	 * This method is ensured to be called after the construction of the instance
	 * has finished, thus subclasses fields have been injected as well.
	 * 
	 * @param parent
	 * @return
	 */
	protected abstract StructuredViewer createViewer(Composite parent);

	protected void eObjectSelectionChanged(EObject selectedObject) {
		if (detailComposite != null) {
			detailComposite.dispose();
		}

		if (selectedObject != null) {
			detailComposite = createDetailComposite(detailParentComposite, selectedObject);
			detailParentComposite.layout(true);
		}
	}

	/**
	 * @param parent
	 * @param selectedObject
	 * @since 2.0
	 */
	protected abstract AbstractDetailComposite createDetailComposite(Composite parent, EObject selectedObject);

	public void update(Object contents) {
		viewer.setInput(contents);
	}
}
