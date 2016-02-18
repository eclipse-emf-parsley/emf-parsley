/*******************************************************************************
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
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
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionListener;


public class TreeColumn extends Item {
	Tree parent;
	boolean resizable, moveable;
	String toolTipText;
	int id;

	public TreeColumn(Tree parent, int style) {
		super(parent, checkStyle(style));
		resizable = true;
		this.parent = parent;
		parent.createItem(this, parent.getColumnCount());
	}

	public TreeColumn(Tree parent, int style, int index) {
		super(parent, checkStyle(style));
		resizable = true;
		this.parent = parent;
		parent.createItem(this, index);
	}

	public void addControlListener(ControlListener listener) {
	}

	public void addSelectionListener(SelectionListener listener) {
	}

	static int checkStyle(int style) {
		return checkBits(style, SWT.LEFT, SWT.CENTER, SWT.RIGHT, 0, 0, 0);
	}

	void destroyWidget() {
	}

	public Tree getParent() {
		return parent;
	}

	public void setText(String string) {
	}

	public void setWidth(int width) {
	}

}
