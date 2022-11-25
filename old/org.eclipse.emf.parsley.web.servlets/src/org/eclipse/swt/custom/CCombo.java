/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Vincenzo Caselli - Initial contribution and API
 * 
 *******************************************************************************/
package org.eclipse.swt.custom;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TypedListener;

public class CCombo extends Composite {

	Text text;
	List list;
	int visibleItemCount = 5;
	Shell popup;
	Button arrow;
	boolean hasFocus;
	Listener listener, filter;
	Color foreground, background;
	Font font;
	Shell _shell;

	static final String PACKAGE_PREFIX = "org.eclipse.swt.custom."; //$NON-NLS-1$

	public CCombo(Composite parent, int style) {
		super(parent, style = checkStyle(style));
		_shell = super.getShell();

		int textStyle = SWT.SINGLE;
		if ((style & SWT.READ_ONLY) != 0)
			textStyle |= SWT.READ_ONLY;
		if ((style & SWT.FLAT) != 0)
			textStyle |= SWT.FLAT;
		text = new Text(this, textStyle);
		int arrowStyle = SWT.ARROW | SWT.DOWN;
		if ((style & SWT.FLAT) != 0)
			arrowStyle |= SWT.FLAT;
		arrow = new Button(this, arrowStyle);

	}

	static int checkStyle(int style) {
		int mask = SWT.BORDER | SWT.READ_ONLY | SWT.FLAT | SWT.LEFT_TO_RIGHT | SWT.RIGHT_TO_LEFT;
		return SWT.NO_FOCUS | (style & mask);
	}

	public void add(String string) {
		if (string == null)
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
		list.add(string);
	}

	public void add(String string, int index) {
		if (string == null)
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
		list.add(string, index);
	}

	public void addModifyListener(ModifyListener listener) {
		if (listener == null)
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
		TypedListener typedListener = new TypedListener(listener);
		addListener(SWT.Modify, typedListener);
	}

	public void addSelectionListener(SelectionListener listener) {
		if (listener == null)
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
	}

	public void addVerifyListener(VerifyListener listener) {
		if (listener == null)
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
		TypedListener typedListener = new TypedListener(listener);
		addListener(SWT.Verify, typedListener);
	}

	void arrowEvent(Event event) {
	}

	public void clearSelection() {
		text.clearSelection();
	}

}
