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


import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class StyledText extends Canvas {

    public StyledText(Composite parent, int style) {
        super(parent, checkStyle(style));
    }

    static int checkStyle(int style) {
        // if ((style & SWT.SINGLE) != 0) {
        // style &= ~(SWT.H_SCROLL | SWT.V_SCROLL | SWT.WRAP | SWT.MULTI);
        // } else {
        // style |= SWT.MULTI;
        // if ((style & SWT.WRAP) != 0) {
        // style &= ~SWT.H_SCROLL;
        // }
        // }
        // style |= SWT.NO_REDRAW_RESIZE | SWT.DOUBLE_BUFFERED | SWT.NO_BACKGROUND;
        // /* Clear SWT.CENTER to avoid the conflict with SWT.EMBEDDED */
        // return style & ~SWT.CENTER;
        return style;
    }
}
