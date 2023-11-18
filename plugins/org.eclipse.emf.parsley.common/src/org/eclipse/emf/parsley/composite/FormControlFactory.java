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
import org.eclipse.emf.parsley.ui.provider.FeatureLabelCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider;
import org.eclipse.emf.parsley.widgets.FormWidgetFactory;
import org.eclipse.emf.parsley.widgets.IWidgetFactory;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.google.inject.Inject;

/**
 * A customization for forms.
 */
public class FormControlFactory extends DialogControlFactory {

	private FormToolkit toolkit = null;

	@Inject
	private FormWidgetFactory formWidgetFactory;

	@Inject
	private FormFeatureCaptionProvider formFeatureCaptionProvider;

	@Override
	protected IWidgetFactory createWidgetFactory() {
		return formWidgetFactory;
	}

	@Override
	protected FeatureLabelCaptionProvider createFeatureLabelCaptionProvider() {
		return formFeatureCaptionProvider;
	}

	public void init(EditingDomain domain, EObject owner, Composite parent, FormToolkit toolkit) {
		this.toolkit = toolkit;
		init(domain, owner, parent);
		formWidgetFactory.init(parent, toolkit);
		formFeatureCaptionProvider.setFormToolkit(toolkit);
	}

	public FormToolkit getToolkit() {
		return toolkit;
	}

}
