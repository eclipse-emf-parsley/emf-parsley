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
import org.eclipse.swt.events.SelectionListener;

public class List extends Scrollable {
    static final int INSET = 3;
    // static final long /* int */ ListProc;
    // static final TCHAR ListClass = new TCHAR(0, "LISTBOX", true);
    boolean addedUCC = false; // indicates whether Bidi UCC were added; 'state & HAS_AUTO_DIRECTION'
                              // isn't a sufficient indicator

    public List(Composite parent, int style) {
        super(parent, checkStyle(style));
    }

    public void add(String string) {
        // checkWidget();
        if (string == null)
            error(SWT.ERROR_NULL_ARGUMENT);
        // TCHAR buffer = new TCHAR(getCodePage(), string, true);
        // int result = (int) /* 64 */OS.SendMessage(handle, OS.LB_ADDSTRING, 0, buffer);
        // if (result == OS.LB_ERR)
        // error(SWT.ERROR_ITEM_NOT_ADDED);
        // if (result == OS.LB_ERRSPACE)
        // error(SWT.ERROR_ITEM_NOT_ADDED);
        // if ((style & SWT.H_SCROLL) != 0)
        // setScrollWidth(buffer, true);
        /// +
    }

    public void add(String string, int index) {
        // checkWidget();
        if (string == null)
            error(SWT.ERROR_NULL_ARGUMENT);
        // if (index == -1)
        // error(SWT.ERROR_INVALID_RANGE);
        // TCHAR buffer = new TCHAR(getCodePage(), string, true);
        // int result = (int) /* 64 */OS.SendMessage(handle, OS.LB_INSERTSTRING, index, buffer);
        // if (result == OS.LB_ERRSPACE)
        // error(SWT.ERROR_ITEM_NOT_ADDED);
        // if (result == OS.LB_ERR) {
        // int count = (int) /* 64 */OS.SendMessage(handle, OS.LB_GETCOUNT, 0, 0);
        // if (0 <= index && index <= count) {
        // error(SWT.ERROR_ITEM_NOT_ADDED);
        // } else {
        // error(SWT.ERROR_INVALID_RANGE);
        // }
        // }
        // if ((style & SWT.H_SCROLL) != 0)
        // setScrollWidth(buffer, true);
        /// +
    }

    public void addSelectionListener(SelectionListener listener) {
        // checkWidget();
        if (listener == null)
            error(SWT.ERROR_NULL_ARGUMENT);
        TypedListener typedListener = new TypedListener(listener);
        // addListener(SWT.Selection, typedListener);
        // addListener(SWT.DefaultSelection, typedListener);
        /// +
    }


    static int checkStyle(int style) {
        return checkBits(style, SWT.SINGLE, SWT.MULTI, 0, 0, 0, 0);
    }



}
