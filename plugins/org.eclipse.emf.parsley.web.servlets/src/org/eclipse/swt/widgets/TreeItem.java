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
import org.eclipse.swt.internal.win32.OS;

public class TreeItem extends Item {
    public long /* int */ handle;
    Tree parent;
    String[] strings;
    Image[] images;
    Font font;
    Font[] cellFont;
    boolean cached;
    int background = -1, foreground = -1;
    int[] cellBackground, cellForeground;

    public TreeItem(Tree parent, int style) {
        this(parent, style, OS.TVGN_ROOT, OS.TVI_LAST, 0);
    }

    public TreeItem(Tree parent, int style, int index) {
        this(parent, style, OS.TVGN_ROOT, findPrevious(parent, index), 0);
    }

    public TreeItem(TreeItem parentItem, int style) {
        this(checkNull(parentItem).parent, style, parentItem.handle, OS.TVI_LAST, 0);
    }

    public TreeItem(TreeItem parentItem, int style, int index) {
        this(checkNull(parentItem).parent, style, parentItem.handle, findPrevious(parentItem, index), 0);
    }

    TreeItem(Tree parent, int style, long /* int */ hParent, long /* int */ hInsertAfter, long /* int */ hItem) {
        super(parent, style);
        this.parent = parent;
        parent.createItem(this, hParent, hInsertAfter, hItem);
    }

    static TreeItem checkNull(TreeItem item) {
        if (item == null)
            SWT.error(SWT.ERROR_NULL_ARGUMENT);
        return item;
    }

    static long /* int */ findPrevious(TreeItem parentItem, int index) {
        if (parentItem == null)
            return 0;
        if (index < 0)
            SWT.error(SWT.ERROR_INVALID_RANGE);
        if (index == 0)
            return OS.TVI_FIRST;
        Tree parent = parentItem.parent;
        long /* int */ hwnd = parent.handle, hParent = parentItem.handle;
        // long /* int */ hFirstItem = OS.SendMessage(hwnd, OS.TVM_GETNEXTITEM, OS.TVGN_CHILD,
        // hParent);
        // long /* int */ hItem = parent.findItem(hFirstItem, index - 1);
        // if (hItem == 0)
        // SWT.error(SWT.ERROR_INVALID_RANGE);
        // return hItem;
        return 0; /// +
    }

    static long /* int */ findPrevious(Tree parent, int index) {
        if (parent == null)
            return 0;
        if (index < 0)
            SWT.error(SWT.ERROR_INVALID_RANGE);
        if (index == 0)
            return OS.TVI_FIRST;
        long /* int */ hwnd = parent.handle;
        // long /*int*/ hFirstItem = OS.SendMessage (hwnd, OS.TVM_GETNEXTITEM, OS.TVGN_ROOT, 0);
        // long /*int*/ hItem = parent.findItem (hFirstItem, index - 1);
        // if (hItem == 0) SWT.error (SWT.ERROR_INVALID_RANGE);
        // return hItem;
        return 0; /// +
    }

}
