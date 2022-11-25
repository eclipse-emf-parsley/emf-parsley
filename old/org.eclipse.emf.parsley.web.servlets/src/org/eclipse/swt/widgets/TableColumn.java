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

public class TableColumn extends Item {
	Table parent;
	boolean resizable, moveable;
	String toolTipText;
	int id;

	public TableColumn(Table parent, int style) {
		super(parent, checkStyle(style));
		resizable = true;
		this.parent = parent;
		parent.createItem(this, parent.getColumnCount());
	}

	public TableColumn (Table parent, int style, int index) {
		super (parent, checkStyle (style));
		resizable = true;
		this.parent = parent;
		parent.createItem (this, index);
	}

	static int checkStyle(int style) {
		return checkBits(style, SWT.LEFT, SWT.CENTER, SWT.RIGHT, 0, 0, 0);
	}
}
