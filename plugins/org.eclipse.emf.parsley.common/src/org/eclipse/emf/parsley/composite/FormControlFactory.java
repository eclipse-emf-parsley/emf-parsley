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

import org.eclipse.emf.parsley.inject.parameters.CompositeParameter;
import org.eclipse.emf.parsley.inject.parameters.EObjectParameter;
import org.eclipse.emf.parsley.inject.parameters.FormToolkitParameter;
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider;
import org.eclipse.emf.parsley.widgets.FormWidgetFactory;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.google.inject.Inject;

/**
 * A customization for forms.
 * 
 * @author Lorenzo Bettini - initial API and implementation
 */
public class FormControlFactory extends DialogControlFactory {

	private FormToolkit formToolkit = null;

	/**
	 * @since 2.0
	 */
	@Inject
	public FormControlFactory(CompositeParameter compositeParameter, EObjectParameter eObjectParameter, FormToolkitParameter formToolkitParameter) {
		super(compositeParameter, eObjectParameter);
		this.formToolkit = formToolkitParameter.getFormToolkit();
	}

	/**
	 * @since 2.0
	 */
	protected FormToolkit getFormToolkit() {
		return formToolkit;
	}

	/**
	 * @since 2.0
	 */
	@Override
	protected FormWidgetFactory createWidgetFactory(CompositeFactory factory) {
		return factory.createFormWidgetFactory(getParent(), formToolkit);
	}

	/**
	 * @since 2.0
	 */
	@Override
	protected FormFeatureCaptionProvider createFeatureLabelCaptionProvider(CompositeFactory compositeFactory) {
		return compositeFactory.createFormFeatureCaptionProvider(formToolkit);
	}
}
