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
import org.eclipse.emf.parsley.ui.provider.DialogFeatureCaptionProvider;
import org.eclipse.emf.parsley.widgets.DialogWidgetFactory;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

/**
 * An implementation specific for dialogs.
 * 
 * @author Lorenzo Bettini - initial API and implementation
 * 
 */
public class DialogControlFactory extends AbstractControlFactory {

	private Composite parent;

	/**
	 * @since 2.0
	 */
	@Inject
	public DialogControlFactory(CompositeParameter compositeParameter, EObjectParameter eObjectParameter) {
		super(eObjectParameter);
		this.parent = compositeParameter.getComposite();
	}

	@Override
	public Composite getParent() {
		return parent;
	}

	/**
	 * @since 2.0
	 */
	@Override
	protected DialogWidgetFactory createWidgetFactory(CompositeFactory factory) {
		return factory.createDialogWidgetFactory(parent);
	}

	/**
	 * @since 2.0
	 */
	@Override
	protected DialogFeatureCaptionProvider createFeatureLabelCaptionProvider(CompositeFactory compositeFactory) {
		return compositeFactory.createFeatureLabelCaptionProvider();
	}
}
