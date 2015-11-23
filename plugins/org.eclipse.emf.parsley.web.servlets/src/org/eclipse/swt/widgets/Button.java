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
        _setText(string);
    }

    public void setSelection(boolean selected) {
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
    }

    public void setImage(Image image) {
    }

    public boolean getSelection() {
        return selected;
    }
}
