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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.SWT;

public class Combo extends Composite {
	List<String> items = new ArrayList<String>();

	public Combo(Composite parent, int style) {
		super(parent, checkStyle(style));
		/* This code is intentionally commented */
		// if ((style & SWT.H_SCROLL) != 0) this.style |= SWT.H_SCROLL;
		this.style |= SWT.H_SCROLL;
	}

	static int checkStyle(int style) {
		style &= ~SWT.BORDER;

		style &= ~(SWT.H_SCROLL | SWT.V_SCROLL);
		style = checkBits(style, SWT.DROP_DOWN, SWT.SIMPLE, 0, 0, 0, 0);
		if ((style & SWT.SIMPLE) != 0)
			return style & ~SWT.READ_ONLY;
		return style;
	}

	public String[] getItems() {
		String[] result;
		int count = getItemCount();
		result = new String[count];
		for (int i = 0; i < count; i++)
			result[i] = getItem(i);
		return result;
	}

	public int getItemCount() {
		return items.size(); /// +
	}

	public String getItem(int index) {
		return items.get(index);
	}

	public int getSelectionIndex() {
		return -1; /// +
	}

	public void clearSelection() {
	}

	public void deselectAll() {
	}

	public void removeAll() {
	}

	public void setItems(String[] items) {
		this.items = Arrays.asList(items);
	}

	public void select(int index) {
	}
}
