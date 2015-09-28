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


import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.TableItem;

/**
 * A TableTreeItem is a selectable user interface object that represents an item in a hierarchy of
 * items in a TableTree.
 * 
 * @deprecated As of 3.1 use Tree, TreeItem and TreeColumn
 */
@Deprecated
public class TableTreeItem extends Item {
    TableItem tableItem;
    TableTree parent;
    TableTreeItem parentItem;
    TableTreeItem[] items = TableTree.EMPTY_ITEMS;
    String[] texts = TableTree.EMPTY_TEXTS;
    Image[] images = TableTree.EMPTY_IMAGES;
    Color background;
    Color foreground;
    Font font;
    boolean expanded;
    boolean checked;
    boolean grayed;

    public TableTreeItem(TableTree parent, int style) {
        this(parent, style, parent.getItemCount());
    }

    public TableTreeItem(TableTree parent, int style, int index) {
        this(parent, null, style, index);
    }

    public TableTreeItem(TableTreeItem parent, int style) {
        this(parent, style, parent.getItemCount());
    }

    public TableTreeItem(TableTreeItem parent, int style, int index) {
        this(parent.getParent(), parent, style, index);
    }

    TableTreeItem(TableTree parent, TableTreeItem parentItem, int style, int index) {
        super(parent, style);
        this.parent = parent;
        this.parentItem = parentItem;
        // if (parentItem == null) {
        //
        // /* Root items are visible immediately */
        // int tableIndex = parent.addItem(this, index);
        // tableItem = new TableItem(parent.getTable(), style, tableIndex);
        // tableItem.setData(TableTree.ITEMID, this);
        // addCheck();
        // /*
        // * Feature in the Table. The table uses the first image that is inserted into the table
        // * to size the table rows. If the user is allowed to insert the first image, this will
        // * cause the +/- images to be scaled. The fix is to insert a dummy image to force the
        // * size.
        // */
        // if (parent.sizeImage == null) {
        // int itemHeight = parent.getItemHeight();
        // parent.sizeImage = new Image(parent.getDisplay(), itemHeight, itemHeight);
        // GC gc = new GC(parent.sizeImage);
        // gc.setBackground(parent.getBackground());
        // gc.fillRectangle(0, 0, itemHeight, itemHeight);
        // gc.dispose();
        // tableItem.setImage(0, parent.sizeImage);
        // }
        // } else {
        // parentItem.addItem(this, index);
        // }
    }

    public TableTree getParent() {
        // checkWidget();
        return parent;
    }

    public int getItemCount() {
        // checkWidget();
        return items.length;
    }


}
