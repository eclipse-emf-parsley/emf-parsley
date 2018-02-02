/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.swtbot.labeling;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.inject.parameters.FormToolkitParameter;
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.IFormColors;

import com.google.inject.Inject;

/**
 * Note that we will bind also a {@link CustomLibraryFeatureCaptionProvider} so we
 * will use automatically also its customizations.
 * 
 * @author Lorenzo Bettini
 * 
 */
public class CustomLibraryFormFeatureCaptionProvider extends
		FormFeatureCaptionProvider {

	@Inject
	public CustomLibraryFormFeatureCaptionProvider(FormToolkitParameter param) {
		super(param);
	}

	/**
	 * This will have the precedence over the one in
	 * {@link CustomLibraryFeatureCaptionProvider}
	 */
	public String text_Person_lastName(EStructuralFeature feature) {
		return "Last name";
	}

	public Label label_Writer_name(Composite parent, EStructuralFeature feature) {
		Label label = defaultLabel(parent, feature.getEContainingClass(), feature);
		label.setBackground(getFormToolkit().getColors().getColor(
				IFormColors.TITLE));
		return label;
	}
}
