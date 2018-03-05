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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.inject.AfterInject;
import org.eclipse.emf.parsley.inject.EmfParsleyLifecycle;
import org.eclipse.emf.parsley.inject.InjectableComposite;
import org.eclipse.emf.parsley.inject.parameters.CompositeParameters;
import org.eclipse.emf.parsley.util.EmfSelectionHelper;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

/**
 * A generic abstract composite with a master composite with some contents and a
 * detail composite with details of the selected object in the master composite;
 * both the master and the detail composites are created by subclasses.
 * 
 * Note that the master-detail composite can be in turn nested inside another
 * master-detail composite, and used as a detail composite.
 * 
 * @author Lorenzo Bettini
 * @author Francesco Guidieri
 * 
 */
@EmfParsleyLifecycle
public abstract class AbstractMasterDetailComposite extends InjectableComposite implements IDetailComposite {

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

	private IMasterComposite masterComposite;

	private IDetailComposite detailComposite;

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
	private void setupMasterComposite() {
		masterComposite = createMasterComposite(masterParentComposite);
		masterComposite.addSelectionChangedListener(new SelectionChangedListener());
	}

	protected SashForm getSashForm() {
		return sashForm;
	}

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
	 * Updates the contents of the master composite.
	 * @param contents
	 */
	public void update(Object contents) {
		masterComposite.update(contents);
	}

	/**
	 * This method is ensured to be called after the construction of the instance
	 * has finished, thus subclasses fields have been injected as well.
	 * 
	 * @param parent
	 * @return
	 * @since 2.0
	 */
	protected abstract IMasterComposite createMasterComposite(Composite parent);

	/**
	 * This method is called each time the object selected in the master composite changes.
	 * 
	 * @param parent
	 * @param selectedObject
	 * @since 2.0
	 */
	protected abstract IDetailComposite createDetailComposite(Composite parent, EObject selectedObject);

	/**
	 * @see SashForm#setOrientation(int)
	 * @since 2.0
	 */
	public void setSashFormOrientation(int orientation) {
		getSashForm().setOrientation(orientation);
	}

}
