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
import org.eclipse.swt.events.ModifyListener;

public class Text extends Scrollable {

    public Text(Composite parent, int style) {
        super(parent, checkStyle(style));
    }

    public int getStyle() {
        return style;
    }

    public void setText(String text) {
        this.value = text;
    }

    public String getText() {
        return this.value;
    }

    static int checkStyle(int style) {
        if ((style & SWT.SEARCH) != 0) {
            style |= SWT.SINGLE | SWT.BORDER;
            style &= ~SWT.PASSWORD;
            /*
             * NOTE: ICON_CANCEL has the same value as H_SCROLL and ICON_SEARCH has the same value
             * as V_SCROLL so they are cleared because SWT.SINGLE is set.
             */
        }
        if ((style & SWT.SINGLE) != 0 && (style & SWT.MULTI) != 0) {
            style &= ~SWT.MULTI;
        }
        style = checkBits(style, SWT.LEFT, SWT.CENTER, SWT.RIGHT, 0, 0, 0);
        if ((style & SWT.SINGLE) != 0)
            style &= ~(SWT.H_SCROLL | SWT.V_SCROLL | SWT.WRAP);
        if ((style & SWT.WRAP) != 0) {
            style |= SWT.MULTI;
            style &= ~SWT.H_SCROLL;
        }
        if ((style & SWT.MULTI) != 0)
            style &= ~SWT.PASSWORD;
        if ((style & (SWT.SINGLE | SWT.MULTI)) != 0)
            return style;
        if ((style & (SWT.H_SCROLL | SWT.V_SCROLL)) != 0)
            return style | SWT.MULTI;
        return style | SWT.SINGLE;
    }

    public void setEditable(boolean editable) {}

    public void addModifyListener(ModifyListener listener) {
        if (listener == null)
            error(SWT.ERROR_NULL_ARGUMENT);
        TypedListener typedListener = new TypedListener(listener);
        addListener(SWT.Modify, typedListener);
    }

    public int getCaretPosition() {
        return 0;
    }

    public void clearSelection() {
    }
}
