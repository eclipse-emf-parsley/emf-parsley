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

public class Table extends Composite {
    TableItem[] items;
    int[] keys;
    TableColumn[] columns;
    int columnCount, customCount, keyCount;

    public Table(Composite parent, int style) {
        super(parent, checkStyle(style));
    }

    static int checkStyle(int style) {
        if ((style & SWT.NO_SCROLL) == 0) {
            style |= SWT.H_SCROLL | SWT.V_SCROLL;
        }
        return checkBits(style, SWT.SINGLE, SWT.MULTI, 0, 0, 0, 0);
    }

    public int getItemCount() {
        return 1; /// +
    }

    void createItem(TableItem item, int index) {}

    public int getColumnCount() {
        return columnCount;
    }

    void createItem(TableColumn column, int index) {}

    public int indexOf(TableItem item) {
        // checkWidget();
        if (item == null)
            error(SWT.ERROR_NULL_ARGUMENT);
        // TODO - find other loops that can be optimized
        // if (keys == null) {
        // int count = (int) /* 64 */OS.SendMessage(handle, OS.LVM_GETITEMCOUNT, 0, 0);
        // if (1 <= lastIndexOf && lastIndexOf < count - 1) {
        // if (_getItem(lastIndexOf, false) == item)
        // return lastIndexOf;
        // if (_getItem(lastIndexOf + 1, false) == item)
        // return ++lastIndexOf;
        // if (_getItem(lastIndexOf - 1, false) == item)
        // return --lastIndexOf;
        // }
        // if (lastIndexOf < count / 2) {
        // for (int i = 0; i < count; i++) {
        // if (_getItem(i, false) == item)
        // return lastIndexOf = i;
        // }
        // } else {
        // for (int i = count - 1; i >= 0; --i) {
        // if (_getItem(i, false) == item)
        // return lastIndexOf = i;
        // }
        // }
        // } else {
        // for (int i = 0; i < keyCount; i++) {
        // if (items[i] == item)
        // return keys[i];
        // }
        // }
        return -1; /// +
    }

}
