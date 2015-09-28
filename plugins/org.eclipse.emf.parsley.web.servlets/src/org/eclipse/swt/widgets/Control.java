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
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyListener;

public abstract class Control extends Widget {

    Composite parent;
    public long /* int */ handle;
    int drawCount, foreground, background, backgroundAlpha = 255;
    Object layoutData;

    Control() {}

    public Control(Composite parent, int style) {
        super(parent, style);
        this.parent = parent;
    }

    long /* int */ widgetParent() {
        return parent.handle;
    }

    void createHandle() {
        // long /* int */ hwndParent = widgetParent();
        // handle = OS.CreateWindowEx(widgetExtStyle(), windowClass(), null, widgetStyle(),
        // OS.CW_USEDEFAULT, 0, OS.CW_USEDEFAULT, 0, hwndParent, 0, OS.GetModuleHandle(null),
        // widgetCreateStruct());
        // if (handle == 0)
        // error(SWT.ERROR_NO_HANDLES);
        // int bits = OS.GetWindowLong(handle, OS.GWL_STYLE);
        // if ((bits & OS.WS_CHILD) != 0) {
        // OS.SetWindowLongPtr(handle, OS.GWLP_ID, handle);
        // }
        // if (OS.IsDBLocale && hwndParent != 0) {
        // long /* int */ hIMC = OS.ImmGetContext(hwndParent);
        // OS.ImmAssociateContext(handle, hIMC);
        // OS.ImmReleaseContext(hwndParent, hIMC);
        // }
        /// +
    }

    void createWidget() {
        state |= DRAG_DETECT;
        foreground = background = -1;
        checkOrientation(parent);
        createHandle();
        /// +
        // checkBackground();
        // checkBuffered();
        // checkComposited();
        // register();
        // subclass();
        // setDefaultFont();
        // checkMirrored();
        // checkBorder();
        // checkGesture();
        // if ((state & PARENT_BACKGROUND) != 0) {
        // setBackground();
        // }
    }

    public void setEnabled(boolean enabled) {
        // checkWidget ();
        // Control control = null;
        // boolean fixFocus = false;
        // if (!enabled) {
        // if (display.focusEvent != SWT.FocusOut) {
        // control = display.getFocusControl ();
        // fixFocus = isFocusAncestor (control);
        // }
        // }
        // enableWidget (enabled);
        // if (fixFocus) fixFocus (control);
        /// +
    }

    public boolean getEnabled() {
        // checkWidget();
        // return OS.IsWindowEnabled(handle);
        return true; /// +
    }

    public boolean isEnabled() {
        // checkWidget ();
        // return getEnabled() && parent.isEnabled();
        return true; /// +
    }

    public void setLayoutData(Object layoutData) {
        // checkWidget ();
        this.layoutData = layoutData;
    }

    public void addKeyListener(KeyListener listener) {
        // checkWidget();
        if (listener == null)
            error(SWT.ERROR_NULL_ARGUMENT);
        TypedListener typedListener = new TypedListener(listener);
        addListener(SWT.KeyUp, typedListener);
        addListener(SWT.KeyDown, typedListener);
    }

    public Shell getShell() {
        // checkWidget();
        return parent.getShell();
    }

    public void addDisposeListener(DisposeListener listener) {
        // checkWidget();
        if (listener == null)
            error(SWT.ERROR_NULL_ARGUMENT);
        TypedListener typedListener = new TypedListener(listener);
        addListener(SWT.Dispose, typedListener);
    }


    public void setRedraw(boolean redraw) {
        // checkWidget ();
        // if (drawCount == 0) {
        // int bits = OS.GetWindowLong (handle, OS.GWL_STYLE);
        // if ((bits & OS.WS_VISIBLE) == 0) state |= HIDDEN;
        // }
        // if (redraw) {
        // if (--drawCount == 0) {
        // long /*int*/ topHandle = topHandle ();
        // OS.SendMessage (topHandle, OS.WM_SETREDRAW, 1, 0);
        // if (handle != topHandle) OS.SendMessage (handle, OS.WM_SETREDRAW, 1, 0);
        // if ((state & HIDDEN) != 0) {
        // state &= ~HIDDEN;
        // OS.ShowWindow (topHandle, OS.SW_HIDE);
        // if (handle != topHandle) OS.ShowWindow (handle, OS.SW_HIDE);
        // } else {
        // if (OS.IsWinCE) {
        // OS.InvalidateRect (topHandle, null, true);
        // if (handle != topHandle) OS.InvalidateRect (handle, null, true);
        // } else {
        // int flags = OS.RDW_ERASE | OS.RDW_FRAME | OS.RDW_INVALIDATE | OS.RDW_ALLCHILDREN;
        // OS.RedrawWindow (topHandle, null, 0, flags);
        // }
        // }
        // }
        // } else {
        // if (drawCount++ == 0) {
        // long /*int*/ topHandle = topHandle ();
        // OS.SendMessage (topHandle, OS.WM_SETREDRAW, 0, 0);
        // if (handle != topHandle) OS.SendMessage (handle, OS.WM_SETREDRAW, 0, 0);
        // }
        // }
    }

}
