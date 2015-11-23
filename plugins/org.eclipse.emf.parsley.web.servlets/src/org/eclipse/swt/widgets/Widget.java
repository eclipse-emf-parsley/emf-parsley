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

public abstract class Widget {
    Display display;
    int style, state;
    Object data;
    protected String value;

    /* Global state flags */
    static final int DISPOSED = 1 << 0;
    static final int CANVAS = 1 << 1;
    static final int KEYED_DATA = 1 << 2;
    static final int DISABLED = 1 << 3;
    static final int HIDDEN = 1 << 4;

    /* More global widget state flags */
    static final int TRACK_MOUSE = 1 << 13;
    static final int FOREIGN_HANDLE = 1 << 14;
    static final int DRAG_DETECT = 1 << 15;

    /* Notify of the opportunity to skin this widget */
    static final int SKIN_NEEDED = 1 << 21;

    Widget() {}

    public Widget(Widget parent, int style) {
        this.style = style;
        display = parent.display;
    }

    static int checkBits(int style, int int0, int int1, int int2, int int3, int int4, int int5) {
        int mask = int0 | int1 | int2 | int3 | int4 | int5;
        if ((style & mask) == 0)
            style |= int0;
        if ((style & int0) != 0)
            style = (style & ~mask) | int0;
        if ((style & int1) != 0)
            style = (style & ~mask) | int1;
        if ((style & int2) != 0)
            style = (style & ~mask) | int2;
        if ((style & int3) != 0)
            style = (style & ~mask) | int3;
        if ((style & int4) != 0)
            style = (style & ~mask) | int4;
        if ((style & int5) != 0)
            style = (style & ~mask) | int5;
        return style;
    }

    public Display getDisplay() {
        Display display = this.display;
        if (display == null)
            error(SWT.ERROR_WIDGET_DISPOSED);
        return display;
    }

    void error(int code) {
        SWT.error(code);
    }

    public boolean isDisposed() {
        return (state & DISPOSED) != 0;
    }

    void reskinWidget() {
        if ((state & SKIN_NEEDED) != SKIN_NEEDED) {
            this.state |= SKIN_NEEDED;
            display.addSkinnableWidget(this);
        }
    }

    void checkOrientation(Widget parent) {
        style &= ~SWT.MIRRORED;
        if ((style & (SWT.LEFT_TO_RIGHT | SWT.RIGHT_TO_LEFT)) == 0) {
            if (parent != null) {
                if ((parent.style & SWT.LEFT_TO_RIGHT) != 0)
                    style |= SWT.LEFT_TO_RIGHT;
                if ((parent.style & SWT.RIGHT_TO_LEFT) != 0)
                    style |= SWT.RIGHT_TO_LEFT;
            }
        }
        style = checkBits(style, SWT.LEFT_TO_RIGHT, SWT.RIGHT_TO_LEFT, 0, 0, 0, 0);
    }

    public void addListener(int eventType, Listener listener) {
        // checkWidget();
        if (listener == null)
            error(SWT.ERROR_NULL_ARGUMENT);
        // _addListener(eventType, listener);
        /// +
    }

    public void setData(Object data) {
        // checkWidget();
        if ((state & KEYED_DATA) != 0) {
            ((Object[]) this.data)[0] = data;
        } else {
            this.data = data;
        }
    }

    public void setData(String key, Object value) {
        // checkWidget();
        if (key == null)
            error(SWT.ERROR_NULL_ARGUMENT);
        int index = 1;
        Object[] table = null;
        if ((state & KEYED_DATA) != 0) {
            table = (Object[]) data;
            while (index < table.length) {
                if (key.equals(table[index]))
                    break;
                index += 2;
            }
        }
        if (value != null) {
            if ((state & KEYED_DATA) != 0) {
                if (index == table.length) {
                    Object[] newTable = new Object[table.length + 2];
                    System.arraycopy(table, 0, newTable, 0, table.length);
                    data = table = newTable;
                }
            } else {
                table = new Object[3];
                table[0] = data;
                data = table;
                state |= KEYED_DATA;
            }
            table[index] = key;
            table[index + 1] = value;
        } else {
            if ((state & KEYED_DATA) != 0) {
                if (index != table.length) {
                    int length = table.length - 2;
                    if (length == 1) {
                        data = table[0];
                        state &= ~KEYED_DATA;
                    } else {
                        Object[] newTable = new Object[length];
                        System.arraycopy(table, 0, newTable, 0, index);
                        System.arraycopy(table, index + 2, newTable, index, length - index);
                        data = newTable;
                    }
                }
            }
        }
        if (key.equals(SWT.SKIN_CLASS) || key.equals(SWT.SKIN_ID))
            this.reskin(SWT.ALL);
    }


    public void reskin(int flags) {
        reskinWidget();
        if ((flags & SWT.ALL) != 0)
            reskinChildren(flags);
    }

    void reskinChildren(int flags) {}

    public Object getData() {
        return (state & KEYED_DATA) != 0 ? ((Object[]) data)[0] : data;
    }

    public Object getData(String key) {
        if (key == null)
            error(SWT.ERROR_NULL_ARGUMENT);
        if ((state & KEYED_DATA) != 0) {
            Object[] table = (Object[]) data;
            for (int i = 1; i < table.length; i += 2) {
                if (key.equals(table[i]))
                    return table[i + 1];
            }
        }
        return null;
    }
}
