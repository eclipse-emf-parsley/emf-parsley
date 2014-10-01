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
package org.eclipse.emf.parsley.ui.provider;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcherExtensions;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.google.inject.Inject;

/**
 * Provides labels for EStructuralFeatures for dialogs. With respect to the
 * superclass {@link FeatureCaptionProvider} you can also specify the Label,
 * besides its text.  If a custom PropertyDescriptionProvider is provided (through
 * injection) then it tries to get the text also from that one, before
 * using the default text.
 * 
 * @author Lorenzo Bettini
 * 
 */
public class DialogFeatureCaptionProvider extends FeatureCaptionProvider {

	@Inject
	private FeatureCaptionProvider delegate;

	public FeatureCaptionProvider getDelegate() {
		return delegate;
	}

	public void setDelegate(FeatureCaptionProvider delegate) {
		this.delegate = delegate;
	}

	public Label getLabel(Composite parent, EClass eClass, EStructuralFeature feature) {
		Label lab = polymorphicGetLabel(parent, eClass, feature);
		if (lab != null)
			return lab;
		return defaultLabel(parent, eClass, feature);
	}

	protected Label defaultLabel(Composite parent, EClass eClass, EStructuralFeature feature) {
		return createLabel(parent, eClass, feature);
	}

	protected Label createLabel(Composite parent, EClass eClass, EStructuralFeature feature) {
		return createLabel(parent, getText(eClass, feature));
	}

	protected Label createLabel(Composite parent, String text) {
		Label lab = new Label(parent, SWT.NONE);
		lab.setText(text);
		lab.setLayoutData(new GridData());
		return lab;
	}

	@Override
	protected String polymorphicGetText(EClass eClass, EStructuralFeature feature) {
		String polymorphicGetText = super.polymorphicGetText(eClass, feature);
		if (polymorphicGetText == null)
			return getDelegate().getText(eClass, feature);
		return polymorphicGetText;
	}

	protected Label polymorphicGetLabel(Composite parent, EClass eClass,
			EStructuralFeature feature) {
		return PolymorphicDispatcherExtensions.
				<Label>polymorphicInvokeBasedOnFeature(
					this, eClass, feature, "label_", parent, feature);
	}

}
