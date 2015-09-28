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
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TreeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TypedListener;

@Deprecated
public class TableTree extends Composite {
    Table table;
    TableTreeItem[] items = EMPTY_ITEMS;
    Image plusImage, minusImage, sizeImage;
    Listener listener;

    boolean inDispose = false;

    static final TableTreeItem[] EMPTY_ITEMS = new TableTreeItem[0];
    static final String[] EMPTY_TEXTS = new String[0];
    static final Image[] EMPTY_IMAGES = new Image[0];
    static final String ITEMID = "TableTreeItemID"; //$NON-NLS-1$

    public TableTree(Composite parent, int style) {
        super(parent, checkStyle(style));
        table = new Table(this, style);
    }


    int addItem(TableTreeItem item, int index) {
        if (index < 0 || index > items.length)
            SWT.error(SWT.ERROR_INVALID_ARGUMENT);
        TableTreeItem[] newItems = new TableTreeItem[items.length + 1];
        System.arraycopy(items, 0, newItems, 0, index);
        newItems[index] = item;
        System.arraycopy(items, index, newItems, index + 1, items.length - index);
        items = newItems;

        /* Return the index in the table where this table should be inserted */
        if (index == items.length - 1)
            return table.getItemCount();
        else
            return table.indexOf(items[index + 1].tableItem);
    }


    public void addSelectionListener(SelectionListener listener) {
        // checkWidget();
        if (listener == null)
            SWT.error(SWT.ERROR_NULL_ARGUMENT);
        TypedListener typedListener = new TypedListener(listener);
        // addListener(SWT.Selection, typedListener);
        // addListener(SWT.DefaultSelection, typedListener);
    }

    public void addTreeListener(TreeListener listener) {
        // checkWidget();
        if (listener == null)
            SWT.error(SWT.ERROR_NULL_ARGUMENT);
        TypedListener typedListener = new TypedListener(listener);
        // addListener(SWT.Expand, typedListener);
        // addListener(SWT.Collapse, typedListener);
    }

    private static int checkStyle(int style) {
        int mask = SWT.LEFT_TO_RIGHT | SWT.RIGHT_TO_LEFT;
        style = style & mask;
        return style;
    }

    public int getItemCount() {
        // checkWidget();
        return items.length;
    }
}
