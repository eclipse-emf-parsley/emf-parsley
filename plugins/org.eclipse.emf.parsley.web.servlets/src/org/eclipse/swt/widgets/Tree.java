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

public class Tree extends Composite {
    public Tree(Composite parent, int style) {
        super(parent, checkStyle(style));
    }

    static int checkStyle(int style) {
        /*
         * Feature in Windows. Even when WS_HSCROLL or WS_VSCROLL is not specified, Windows creates
         * trees and tables with scroll bars. The fix is to set H_SCROLL and V_SCROLL.
         * 
         * NOTE: This code appears on all platforms so that applications have consistent scroll bar
         * behavior.
         */
        if ((style & SWT.NO_SCROLL) == 0) {
            style |= SWT.H_SCROLL | SWT.V_SCROLL;
        }
        /*
         * Note: Windows only supports TVS_NOSCROLL and TVS_NOHSCROLL.
         */
        if ((style & SWT.H_SCROLL) != 0 && (style & SWT.V_SCROLL) == 0) {
            style |= SWT.V_SCROLL;
        }
        return checkBits(style, SWT.SINGLE, SWT.MULTI, 0, 0, 0, 0);
    }


    void createItem(TreeItem item, long /* int */ hParent, long /* int */ hInsertAfter, long /* int */ hItem) {
    }

}
