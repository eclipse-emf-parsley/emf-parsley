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
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

public class Tree extends Composite {
	int columnCount;
	TreeItem [] items;
	TreeColumn [] columns;
	
	public Tree(Composite parent, int style) {
		super(parent, checkStyle(style));
	}

	static int checkStyle(int style) {
		/*
		 * Feature in Windows. Even when WS_HSCROLL or WS_VSCROLL is not
		 * specified, Windows creates trees and tables with scroll bars. The fix
		 * is to set H_SCROLL and V_SCROLL.
		 * 
		 * NOTE: This code appears on all platforms so that applications have
		 * consistent scroll bar behavior.
		 */
		if ((style & SWT.NO_SCROLL) == 0) {
			style |= SWT.H_SCROLL | SWT.V_SCROLL;
		}
		/*
		 * Note: Windows only supports TVS_NOSCROLL and TVS_NOHSCROLL.
		 */
		if ((style & SWT.H_SCROLL) != 0 && (style & SWT.V_SCROLL) == 0) {
			style |= SWT.V_SCROLL;
		}
		return checkBits(style, SWT.SINGLE, SWT.MULTI, 0, 0, 0, 0);
	}

	void createItem(TreeItem item, long /* int */ hParent, long /* int */ hInsertAfter, long /* int */ hItem) {
	}

	public int getColumnCount () {
		return columnCount;
	}

	void createItem (TreeColumn column, int index) {
		if (!(0 <= index && index <= columnCount)) error (SWT.ERROR_INVALID_RANGE);
		if (columnCount == columns.length) {
			TreeColumn [] newColumns = new TreeColumn [columns.length + 4];
			System.arraycopy (columns, 0, newColumns, 0, columns.length);
			columns = newColumns;
		}
		for (int i=0; i<items.length; i++) {
			TreeItem item = items [i];
			if (item != null) {
				String [] strings = item.strings;
				if (strings != null) {
					String [] temp = new String [columnCount + 1];
					System.arraycopy (strings, 0, temp, 0, index);
					System.arraycopy (strings, index, temp, index + 1, columnCount - index);
					item.strings = temp;
				}
				Image [] images = item.images;
				if (images != null) {
					Image [] temp = new Image [columnCount + 1];
					System.arraycopy (images, 0, temp, 0, index);
					System.arraycopy (images, index, temp, index + 1, columnCount - index);
					item.images = temp;
				}
				if (index == 0) {
					if (columnCount != 0) {
						if (strings == null) {
							item.strings = new String [columnCount + 1];
							item.strings [1] = item.text;
						}
						item.text = "";
						if (images == null) {
							item.images = new Image [columnCount + 1];
							item.images [1] = item.image;
						}
						item.image = null;
					}
				}
				if (item.cellBackground != null) {
					int [] cellBackground = item.cellBackground;
					int [] temp = new int [columnCount + 1];
					System.arraycopy (cellBackground, 0, temp, 0, index);
					System.arraycopy (cellBackground, index, temp, index + 1, columnCount - index);
					temp [index] = -1;
					item.cellBackground = temp;
				}
				if (item.cellForeground != null) {
					int [] cellForeground = item.cellForeground;
					int [] temp = new int [columnCount + 1];
					System.arraycopy (cellForeground, 0, temp, 0, index);
					System.arraycopy (cellForeground, index, temp, index + 1, columnCount - index);
					temp [index] = -1;
					item.cellForeground = temp;
				}
				if (item.cellFont != null) {
					Font [] cellFont = item.cellFont;
					Font [] temp = new Font [columnCount + 1];
					System.arraycopy (cellFont, 0, temp, 0, index);
					System.arraycopy (cellFont, index, temp, index + 1, columnCount- index);
					item.cellFont = temp;
				}
			}
		}
		System.arraycopy (columns, index, columns, index + 1, columnCount++ - index);
		columns [index] = column;
		
	}

}
