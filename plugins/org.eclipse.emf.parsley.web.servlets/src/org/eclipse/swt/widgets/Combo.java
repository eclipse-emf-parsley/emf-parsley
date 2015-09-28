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
        // checkWidget ();
        // if (noSelection) return -1;
        // return (int)/*64*/OS.SendMessage (handle, OS.CB_GETCURSEL, 0, 0);
        return -1; /// +
    }

    public void clearSelection() {
        // checkWidget ();
        // OS.SendMessage (handle, OS.CB_SETEDITSEL, 0, -1);
        /// +
    }

    public void deselectAll() {
        // checkWidget();
        // OS.SendMessage(handle, OS.CB_SETCURSEL, -1, 0);
        // sendEvent(SWT.Modify);
        // // widget could be disposed at this point
        // clearSegments(false);
        // clearSegmentsCount--;
        /// +
    }

    public void removeAll() {
        // checkWidget ();
        // OS.SendMessage (handle, OS.CB_RESETCONTENT, 0, 0);
        // sendEvent (SWT.Modify);
        // if (isDisposed ()) return;
        // if ((style & SWT.H_SCROLL) != 0) setScrollWidth (0);
        /// +
    }

    public void setItems(String[] items) {
        System.out.println();
        this.items = Arrays.asList(items);
        System.out.println(this.items);
        // checkWidget ();
        // if (items == null) error (SWT.ERROR_NULL_ARGUMENT);
        // for (int i=0; i<items.length; i++) {
        // if (items [i] == null) error (SWT.ERROR_INVALID_ARGUMENT);
        // }
        // RECT rect = null;
        // long /*int*/ hDC = 0, oldFont = 0, newFont = 0;
        // int newWidth = 0;
        // if ((style & SWT.H_SCROLL) != 0) {
        // rect = new RECT ();
        // hDC = OS.GetDC (handle);
        // newFont = OS.SendMessage (handle, OS.WM_GETFONT, 0, 0);
        // if (newFont != 0) oldFont = OS.SelectObject (hDC, newFont);
        // setScrollWidth (0);
        // }
        // OS.SendMessage (handle, OS.CB_RESETCONTENT, 0, 0);
        // int codePage = getCodePage ();
        // for (int i=0; i<items.length; i++) {
        // String string = items [i];
        // TCHAR buffer = new TCHAR (codePage, string, true);
        // int code = (int)/*64*/OS.SendMessage (handle, OS.CB_ADDSTRING, 0, buffer);
        // if (code == OS.CB_ERR) error (SWT.ERROR_ITEM_NOT_ADDED);
        // if (code == OS.CB_ERRSPACE) error (SWT.ERROR_ITEM_NOT_ADDED);
        // if ((style & SWT.H_SCROLL) != 0) {
        // int flags = OS.DT_CALCRECT | OS.DT_SINGLELINE | OS.DT_NOPREFIX;
        // OS.DrawText (hDC, buffer, -1, rect, flags);
        // newWidth = Math.max (newWidth, rect.right - rect.left);
        // }
        // }
        // if ((style & SWT.H_SCROLL) != 0) {
        // if (newFont != 0) OS.SelectObject (hDC, oldFont);
        // OS.ReleaseDC (handle, hDC);
        // setScrollWidth (newWidth + 3);
        // }
        // sendEvent (SWT.Modify);
        // // widget could be disposed at this point
        /// +
    }

    public void select(int index) {
        // checkWidget ();
        // int count = (int)/*64*/OS.SendMessage (handle, OS.CB_GETCOUNT, 0, 0);
        // if (0 <= index && index < count) {
        // int selection = (int)/*64*/OS.SendMessage (handle, OS.CB_GETCURSEL, 0, 0);
        // int code = (int)/*64*/OS.SendMessage (handle, OS.CB_SETCURSEL, index, 0);
        // if (code != OS.CB_ERR && code != selection) {
        // sendEvent (SWT.Modify);
        // // widget could be disposed at this point
        // }
        // }
        System.out.println("Combo.select(" + index + ")");
    }
}
