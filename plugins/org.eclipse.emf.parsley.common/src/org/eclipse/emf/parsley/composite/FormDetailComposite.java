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
package org.eclipse.emf.parsley.composite;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import com.google.inject.Inject;

public class FormDetailComposite extends AbstractDetailComposite {

	private FormControlFactory formControlFactory;

	@Inject
	private ILabelProvider labelProvider;

	private final Composite main;

	private FormToolkit toolkit;

	private final ScrolledForm scrolledForm;

	private FormTitleAdapter headerAdapter;

	public FormDetailComposite(Composite parent, int style) {
		super(parent, style);

		toolkit = new FormToolkit(parent.getDisplay());

		toolkit.adapt(this);
		setLayout(new GridLayout(1, false));

		scrolledForm = toolkit.createScrolledForm(this);
		// make sure that the form takes all the space
		scrolledForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		scrolledForm.getBody().setLayout(new GridLayout(2, false));

		main = scrolledForm.getBody();

		setBackgroundMode(SWT.INHERIT_FORCE);
	}

	@Override
	public void dispose() {
		headerAdapter.dispose();
		super.dispose();
		toolkit.dispose();
	}

	@Inject
	public void setFormControlFactory(FormControlFactory formControlFactory) {
		this.formControlFactory = formControlFactory;
	}

	@Override
	protected void initControlFactory(EditingDomain domain,
			EObject model) {
		updateTitle(model);
		model.eAdapters().add(headerAdapter = new FormTitleAdapter(model));
		formControlFactory.init(domain, model, main, toolkit);
	}

	protected void updateTitle(EObject model) {
		scrolledForm.setText(labelProvider.getText(model));
		scrolledForm.setImage(labelProvider.getImage(model));
	}

	protected ScrolledForm getScrolledForm() {
		return scrolledForm;
	}

	@Override
	protected void createControlForFeature(EClass eClass, EStructuralFeature feature) {
		formControlFactory.createEditingField(feature);
	}

	private class FormTitleAdapter extends AdapterImpl {

		private EObject model;
		private boolean disposing = false;

		public FormTitleAdapter(EObject model) {
			this.model = model;
		}

		public void dispose() {
			disposing = true;
			model.eAdapters().remove(headerAdapter);
		}

		@Override
		public void notifyChanged(Notification msg) {
			if (!disposing) {
				updateTitle(model);
			}
		}
	}

}
