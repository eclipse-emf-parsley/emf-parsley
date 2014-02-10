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

package org.eclipse.emf.parsley.binding;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * An abstraction for creating {@link Label}, {@link Text}, {@link Button}, etc.
 * It will be implemented creating standard controls and form controls.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public abstract class AbstractWidgetFactory {
	
	protected Composite parent = null;
	
	public void init(Composite parent) {
		this.parent = parent;
	}

	public Label createLabel(String text) {
		return createLabel(parent, text);
	}

	public abstract Label createLabel(Composite parent, String text);

	public Button createButton(String text, int...styles) {
		return createButton(parent, text, aggregateStyles(styles));
	}

	public abstract Button createButton(Composite parent, String text, int style);

	public Text createText(String text) {
		return createText(parent, text);
	}

	public Text createText(String text, int...styles) {
		return createText(parent, text, aggregateStyles(styles));
	}

	public Text createText(Composite parent, String text) {
		return createText(parent, text, SWT.BORDER);
	}

	public abstract Text createText(Composite parent, String text, int style);

	public ComboViewer createComboViewer(int...styles) {
		return createComboViewer(parent, aggregateStyles(styles));
	}

	public abstract ComboViewer createComboViewer(Composite parent, int style);
	
	protected int aggregateStyles(int...styles) {
		int result = 0;
		for (int s : styles) {
			result |= s;
		}
		return result;
	}
}
