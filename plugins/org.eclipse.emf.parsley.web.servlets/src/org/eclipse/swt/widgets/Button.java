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
import org.eclipse.swt.graphics.Image;

public class Button extends Control {
    private boolean selected;
    static /* final */ boolean COMMAND_LINK = false;
    String text = "", message = "";
    Image image, image2, disabledImage;

    public Button(Composite parent, int style) {
        super(parent, checkStyle(style));
    }


    public void setText(String string) {
        // checkWidget();
        if (string == null)
            error(SWT.ERROR_NULL_ARGUMENT);
        if ((style & SWT.ARROW) != 0)
            return;
        text = string;
        /* This code is intentionally commented */
        // if (OS.COMCTL32_MAJOR < 6) {
        // if (text.length () == 0 && image != null) {
        // _setImage (image);
        // return;
        // }
        // }
        _setText(string);
    }

    public void setSelection(boolean selected) {
        System.out.println("Button.setSelection(" + selected + ")");
        this.selected = selected;
    }

    static int checkStyle(int style) {
        style = checkBits(style, SWT.PUSH, SWT.ARROW, SWT.CHECK, SWT.RADIO, SWT.TOGGLE, COMMAND_LINK ? SWT.COMMAND : 0);
        if ((style & (SWT.PUSH | SWT.TOGGLE)) != 0) {
            return checkBits(style, SWT.CENTER, SWT.LEFT, SWT.RIGHT, 0, 0, 0);
        }
        if ((style & (SWT.CHECK | SWT.RADIO)) != 0) {
            return checkBits(style, SWT.LEFT, SWT.RIGHT, SWT.CENTER, 0, 0, 0);
        }
        if ((style & SWT.ARROW) != 0) {
            style |= SWT.NO_FOCUS;
            return checkBits(style, SWT.UP, SWT.DOWN, SWT.LEFT, SWT.RIGHT, 0, 0);
        }
        return style;
    }

    void _setText(String text) {
        System.out.println(text);
        // int oldBits = OS.GetWindowLong(handle, OS.GWL_STYLE), newBits = oldBits;
        // if (OS.COMCTL32_MAJOR >= 6) {
        // newBits &= ~(OS.BS_LEFT | OS.BS_CENTER | OS.BS_RIGHT);
        // if ((style & SWT.LEFT) != 0)
        // newBits |= OS.BS_LEFT;
        // if ((style & SWT.CENTER) != 0)
        // newBits |= OS.BS_CENTER;
        // if ((style & SWT.RIGHT) != 0)
        // newBits |= OS.BS_RIGHT;
        // if (imageList != null) {
        // BUTTON_IMAGELIST buttonImageList = new BUTTON_IMAGELIST();
        // buttonImageList.himl = imageList.getHandle();
        // if (text.length() == 0) {
        // if ((style & SWT.LEFT) != 0)
        // buttonImageList.uAlign = OS.BUTTON_IMAGELIST_ALIGN_LEFT;
        // if ((style & SWT.CENTER) != 0)
        // buttonImageList.uAlign = OS.BUTTON_IMAGELIST_ALIGN_CENTER;
        // if ((style & SWT.RIGHT) != 0)
        // buttonImageList.uAlign = OS.BUTTON_IMAGELIST_ALIGN_RIGHT;
        // } else {
        // buttonImageList.uAlign = OS.BUTTON_IMAGELIST_ALIGN_LEFT;
        // buttonImageList.margin_left = computeLeftMargin();
        // buttonImageList.margin_right = MARGIN;
        // newBits &= ~(OS.BS_CENTER | OS.BS_RIGHT);
        // newBits |= OS.BS_LEFT;
        // }
        // OS.SendMessage(handle, OS.BCM_SETIMAGELIST, 0, buttonImageList);
        // }
        // } else {
        // newBits &= ~(OS.BS_BITMAP | OS.BS_ICON);
        // }
        // if (newBits != oldBits) {
        // OS.SetWindowLong(handle, OS.GWL_STYLE, newBits);
        // OS.InvalidateRect(handle, null, true);
        // }
        // /*
        // * Bug in Windows. When a Button control is right-to-left and is disabled, the first pixel
        // * of the text is clipped. The fix is to append a space to the text.
        // */
        // if ((style & SWT.RIGHT_TO_LEFT) != 0) {
        // if (OS.COMCTL32_MAJOR < 6 || !OS.IsAppThemed()) {
        // text = OS.IsWindowEnabled(handle) ? text : text + " ";
        // }
        // }
        // TCHAR buffer = new TCHAR(getCodePage(), text, true);
        // OS.SetWindowText(handle, buffer);
        // if ((state & HAS_AUTO_DIRECTION) != 0) {
        // updateTextDirection(AUTO_TEXT_DIRECTION);
        // }
    }

    public void setImage(Image image) {
        System.out.println("Button.setImage()");
        // checkWidget ();
        // if (image != null && image.isDisposed()) error(SWT.ERROR_INVALID_ARGUMENT);
        // if ((style & SWT.ARROW) != 0) return;
        // this.image = image;
        // /* This code is intentionally commented */
        // // if (OS.COMCTL32_MAJOR < 6) {
        //// if (image == null || text.length () != 0) {
        //// _setText (text);
        //// return;
        //// }
        // // }
        // _setImage (image);
        /// +
    }

    public boolean getSelection() {
        System.out.println();
        return selected;
    }
}
