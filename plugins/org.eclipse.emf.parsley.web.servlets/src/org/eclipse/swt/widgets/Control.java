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
package org.eclipse.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyListener;

public abstract class Control extends Widget {

	Composite parent;
	public long /* int */ handle;
	int drawCount, foreground, background, backgroundAlpha = 255;
	Object layoutData;

	Control() {
	}

	public Control(Composite parent, int style) {
		super(parent, style);
		this.parent = parent;
	}

	long /* int */ widgetParent() {
		return parent.handle;
	}

	void createHandle() {
	}

	void createWidget() {
		state |= DRAG_DETECT;
		foreground = background = -1;
		checkOrientation(parent);
		createHandle();
	}

	public void setEnabled(boolean enabled) {
	}

	public boolean getEnabled() {
		return true; /// +
	}

	public boolean isEnabled() {
		return true; /// +
	}

	public void setLayoutData(Object layoutData) {
		this.layoutData = layoutData;
	}

	public Object getLayoutData() {
		return layoutData;
	}

	public void addKeyListener(KeyListener listener) {
		if (listener == null)
			error(SWT.ERROR_NULL_ARGUMENT);
		TypedListener typedListener = new TypedListener(listener);
		addListener(SWT.KeyUp, typedListener);
		addListener(SWT.KeyDown, typedListener);
	}

	public Shell getShell() {
		return parent.getShell();
	}

	public void addDisposeListener(DisposeListener listener) {
		if (listener == null)
			error(SWT.ERROR_NULL_ARGUMENT);
		TypedListener typedListener = new TypedListener(listener);
		addListener(SWT.Dispose, typedListener);
	}

	public void setRedraw(boolean redraw) {
	}

}
