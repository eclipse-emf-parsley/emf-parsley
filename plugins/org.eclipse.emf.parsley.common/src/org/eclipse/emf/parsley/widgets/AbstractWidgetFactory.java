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

package org.eclipse.emf.parsley.widgets;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * An implementation of {@link IWidgetFactory} with some defaults for some
 * overloaded methods.
 *
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public abstract class AbstractWidgetFactory implements IWidgetFactory {

	protected Composite parent = null;

	@Override
	public void init(Composite parent) {
		this.parent = parent;
	}

	@Override
	public Label createLabel(String text) {
		return createLabel(parent, text);
	}

	@Override
	public Button createButton(String text, int... styles) {
		return createButton(parent, text, aggregateStyles(styles));
	}

	@Override
	public Text createText(String text) {
		return createText(parent, text);
	}

	@Override
	public Text createText(String text, int... styles) {
		return createText(parent, text, aggregateStyles(styles));
	}

	@Override
	public Text createText(Composite parent, String text) {
		return createText(parent, text, SWT.BORDER);
	}

	@Override
	public Text createText(Composite parent, int... styles) {
		return createText(parent, "", aggregateStyles(styles));
	}

	@Override
	public ComboViewer createComboViewer(int... styles) {
		return createComboViewer(parent, aggregateStyles(styles));
	}

	@Override
	public DateTime createDateTime() {
		return createDateTime(parent, SWT.DROP_DOWN);
	}

	@Override
	public DateTime createDateTime(int... styles) {
		return createDateTime(parent, aggregateStyles(styles));
	}

	@Override
	public DateTime createDateTime(Composite parent) {
		return createDateTime(parent, SWT.DATE | SWT.DROP_DOWN);
	}

	protected int aggregateStyles(int... styles) {
		int result = 0;
		for (int s : styles) {
			result |= s;
		}
		return result;
	}

	@Override
	public Composite getParent() {
		return parent;
	}

}
