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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.inject.parameters.CompositeParameters;
import org.eclipse.emf.parsley.inject.parameters.EObjectParameter;
import org.eclipse.emf.parsley.internal.listeners.DisposingAwareAdapter;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import com.google.inject.Inject;

public class FormDetailComposite extends AbstractDetailComposite {

	@Inject
	private ILabelProvider labelProvider;

	@Inject
	private CompositeFactory compositeFactory;

	private final Composite main;

	private FormToolkit toolkit;

	private final ScrolledForm scrolledForm;

	private DisposingAwareAdapter headerAdapter;

	/**
	 * @since 2.0
	 */
	@Inject
	public FormDetailComposite(CompositeParameters compositeParameters, EObjectParameter eObjectParameter) {
		super(compositeParameters, eObjectParameter);

		toolkit = new FormToolkit(compositeParameters.getParent().getDisplay());

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

	protected void updateTitle(EObject model) {
		scrolledForm.setText(labelProvider.getText(model));
		scrolledForm.setImage(labelProvider.getImage(model));
	}

	protected ScrolledForm getScrolledForm() {
		return scrolledForm;
	}

	/**
	 * @since 2.0
	 */
	@Override
	protected FormControlFactory createControlFactory(final EObject object, EditingDomain editingDomain) {
		updateTitle(object);
		headerAdapter = new DisposingAwareAdapter(object, this, () -> updateTitle(object));
		return compositeFactory.createFormControlFactory(main, object, editingDomain, toolkit);
	}

}
