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
import org.eclipse.swt.internal.win32.OS;

public class Shell extends Decorations {
    boolean showWithParent, fullScreen, wasMaximized, modified, center;

    public Shell() {
        this((Display) null);
    }

    public Shell(int style) {
        this((Display) null, style);
    }

    public Shell(Display display) {
        this(display, OS.IsWinCE ? SWT.NONE : SWT.SHELL_TRIM);
    }

    public Shell(Display display, int style) {
        this(display, null, style, 0, false);
        System.out.println();
    }

    public Shell(Shell parent) {
        // this(parent, OS.IsWinCE ? SWT.NONE : SWT.DIALOG_TRIM);
        System.out.println();
    }

    public Shell(Shell parent, int style) {
        // this(parent != null ? parent.display : null, parent, style, 0, false);
        System.out.println();
    }

    public static Shell win32_new(Display display, long /* int */ handle) {
        System.out.println();
        return new Shell(display, null, SWT.NO_TRIM, handle, true);
    }

    public static Shell internal_new(Display display, long /* int */ handle) {
        System.out.println();
        return new Shell(display, null, SWT.NO_TRIM, handle, false);
    }


    Shell(Display display, Shell parent, int style, long /* int */ handle, boolean embedded) {
        super();
        // checkSubclass(); ///+
        if (display == null)
            display = Display.getCurrent();
        if (display == null)
            display = Display.getDefault();
        // if (!display.isValidThread()) {
        // error(SWT.ERROR_THREAD_INVALID_ACCESS);
        // }
        // if (parent != null && parent.isDisposed()) {
        // error(SWT.ERROR_INVALID_ARGUMENT);
        // }
        this.center = parent != null && (style & SWT.SHEET) != 0;
        this.style = checkStyle(parent, style);
        this.parent = parent;
        this.display = display;
        this.handle = handle;
        if (handle != 0 && !embedded) {
            state |= FOREIGN_HANDLE;
        }
        reskinWidget();
        createWidget();
    }

    static int checkStyle(Shell parent, int style) {
        style = Decorations.checkStyle(style);
        style &= ~SWT.TRANSPARENT;
        int mask = SWT.SYSTEM_MODAL | SWT.APPLICATION_MODAL | SWT.PRIMARY_MODAL;
        if ((style & SWT.SHEET) != 0) {
            style &= ~SWT.SHEET;
            style |= parent == null ? SWT.SHELL_TRIM : SWT.DIALOG_TRIM;
            if ((style & mask) == 0) {
                style |= parent == null ? SWT.APPLICATION_MODAL : SWT.PRIMARY_MODAL;
            }
        }
        int bits = style & ~mask;
        if ((style & SWT.SYSTEM_MODAL) != 0)
            return bits | SWT.SYSTEM_MODAL;
        if ((style & SWT.APPLICATION_MODAL) != 0)
            return bits | SWT.APPLICATION_MODAL;
        if ((style & SWT.PRIMARY_MODAL) != 0)
            return bits | SWT.PRIMARY_MODAL;
        return bits;
    }

}
