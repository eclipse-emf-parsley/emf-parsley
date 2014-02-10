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
package org.eclipse.emf.parsley.widgets;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.binding.DialogControlFactory;
import org.eclipse.emf.parsley.ui.provider.DialogFeatureCaptionProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

public class DialogDetailComposite extends AbstractDetailComposite {

	protected DialogFeatureCaptionProvider dialogFeatureCaptionProvider;

	protected DialogControlFactory dialogControlFactory;

	protected ILabelProvider labelProvider;

	public DialogDetailComposite(Composite parent, int style) {
		super(parent, style);

		setLayout(new GridLayout(1, false));
	}

	public DialogFeatureCaptionProvider getDialogFeatureCaptionProvider() {
		return dialogFeatureCaptionProvider;
	}

	@Inject
	public void setDialogFeatureCaptionProviderr(
			DialogFeatureCaptionProvider formPropertyDescriptionProvider) {
		this.dialogFeatureCaptionProvider = formPropertyDescriptionProvider;
	}

	public DialogControlFactory getDialogControlFactory() {
		return dialogControlFactory;
	}

	@Inject
	public void setDialogControlFactory(
			DialogControlFactory formControlFactory) {
		this.dialogControlFactory = formControlFactory;
	}

	public ILabelProvider getLabelProvider() {
		return labelProvider;
	}

	@Inject
	public void setLabelProvider(ILabelProvider labelProvider) {
		this.labelProvider = labelProvider;
	}

	protected void initControlFactory(EditingDomain domain, EObject model) {
		dialogControlFactory.init(domain, model, this);
	}

	protected void createControlForFeature(EStructuralFeature feature) {
		dialogFeatureCaptionProvider.getLabel(this, feature);
		dialogControlFactory.create(feature);
	}

}
