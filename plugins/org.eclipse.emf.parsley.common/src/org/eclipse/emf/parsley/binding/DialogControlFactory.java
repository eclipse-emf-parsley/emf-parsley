/**
 * <copyright> 
 *
 * Copyright (c) 2008, 2013 itemis AG and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   itemis AG - Initial API and implementation
 *   Lorenzo Bettini - refactoring for EmfParsley
 *
 * </copyright>
 *
 */
package org.eclipse.emf.parsley.binding;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.ui.provider.DialogFeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.FeatureLabelCaptionProvider;
import org.eclipse.emf.parsley.widgets.DialogWidgetFactory;
import org.eclipse.emf.parsley.widgets.IWidgetFactory;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

/**
 * 
 * Creates Control for an {@link EStructuralFeature}
 * 
 * @author Dennis Huebner initial code
 * @author Lorenzo Bettini refactoring for EmfParsley
 * 
 */
public class DialogControlFactory extends AbstractControlFactory {

	@Inject
	private DialogWidgetFactory dialogWidgetFactory;

	@Inject
	private DialogFeatureCaptionProvider dialogFeatureCaptionProvider;

	@Override
	protected IWidgetFactory createWidgetFactory() {
		return dialogWidgetFactory;
	}

	@Override
	public void init(Composite parent) {
		dialogWidgetFactory.init(parent);
	}

	@Override
	protected FeatureLabelCaptionProvider createFeatureLabelCaptionProvider() {
		return dialogFeatureCaptionProvider;
	}
}
