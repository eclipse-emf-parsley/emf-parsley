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

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * Provides labels for EStructuralFeatures using {@link FormToolkit}.
 * 
 * @author Lorenzo Bettini - Initial Contribution and API
 * 
 */
public class FormFeatureCaptionProvider extends DialogFeatureCaptionProvider {

	private FormToolkit formToolkit;

	@Override
	protected Label createLabel(Composite parent, String text) {
		Label lab = getFormToolkit().createLabel(parent, text);
		lab.setLayoutData(new GridData());
		return lab;
	}

	public FormToolkit getFormToolkit() {
		return formToolkit;
	}

	public void setFormToolkit(FormToolkit formToolkit) {
		this.formToolkit = formToolkit;
	}

}
