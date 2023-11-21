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

import org.eclipse.emf.parsley.inject.parameters.FormToolkitParameter;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.google.inject.Inject;

/**
 * Provides labels for EStructuralFeatures using {@link FormToolkit}.
 *
 * @author Lorenzo Bettini - Initial Contribution and API
 *
 */
public class FormFeatureCaptionProvider extends DialogFeatureCaptionProvider {

	private FormToolkit formToolkit;

	/**
	 * @since 2.0
	 */
	@Inject
	public FormFeatureCaptionProvider(FormToolkitParameter formToolkitParameter) {
		this.formToolkit = formToolkitParameter.getFormToolkit();
	}

	@Override
	protected Label createLabel(Composite parent, String text) {
		Label lab = getFormToolkit().createLabel(parent, text);
		lab.setLayoutData(new GridData());
		return lab;
	}

	protected FormToolkit getFormToolkit() {
		return formToolkit;
	}

}
