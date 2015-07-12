/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.widgets;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * An implementation that creates widgets suitable for forms.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class FormWidgetFactory extends DialogWidgetFactory {

	private FormToolkit toolkit = null;

	public void init(Composite parent, FormToolkit toolkit) {
		super.init(parent);
		this.toolkit = toolkit;
	}

	public FormToolkit getToolkit() {
		return toolkit;
	}

	@Override
	public Label createLabel(Composite parent, String text) {
		return toolkit.createLabel(parent, text);
	}

	@Override
	public Button createButton(Composite parent, String text, int style) {
		return toolkit.createButton(parent, text, style);
	}

	@Override
	public Text createText(Composite parent, String text, int style) {
		Text t = toolkit.createText(parent, text, style);
		t.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TREE_BORDER);
		return t;
	}

	@Override
	public ComboViewer createComboViewer(Composite parent, int style) {
		ComboViewer combo = new ComboViewer(parent, style);
		toolkit.adapt(combo.getCombo());
		return combo;
	}
}
