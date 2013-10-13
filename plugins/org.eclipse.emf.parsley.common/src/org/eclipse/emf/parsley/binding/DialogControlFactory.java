/**
 * <copyright> 
 *
 * Copyright (c) 2008 itemis AG and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   itemis AG - Initial API and implementation
 *
 * </copyright>
 *
 */
package org.eclipse.emf.parsley.binding;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * 
 * Creates Control for an {@link EStructuralFeature}
 * 
 * @author Dennis Huebner initial code
 * @author Lorenzo Bettini refactoring for EmfParsley
 * 
 */
public class DialogControlFactory extends AbstractControlFactory {

	public DialogControlFactory() {

	}

	public Label createLabel(String text) {
		return createLabel(parent, text);
	}

	public Label createLabel(Composite parent, String text) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(text);
		return label;
	}

	public Button createButton(String text, int style) {
		return createButton(parent, text, style);
	}

	public Button createButton(Composite parent, String text, int style) {
		Button button = new Button(parent, style);
		button.setText(text);
		return button;
	}

	public Text createText(String text) {
		return createText(parent, text);
	}

	public Text createText(String text, int style) {
		return createText(parent, text, style);
	}

	public Text createText(Composite parent, String text) {
		return createText(parent, text, SWT.BORDER);
	}

	public Text createText(Composite parent, String text, int style) {
		Text t = new Text(parent, style);
		t.setText(text);
		return t;
	}

	public ComboViewer createComboViewer(int style) {
		return createComboViewer(parent, style);
	}

	public ComboViewer createComboViewer(Composite parent, int style) {
		ComboViewer combo = new ComboViewer(parent, style);
		return combo;
	}

}
