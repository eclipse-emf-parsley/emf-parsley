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


public class Decorations extends Canvas {

    Decorations() {}

    public Decorations(Composite parent, int style) {
        super(parent, checkStyle(style));
    }

    static int checkStyle(int style) {
        return style;
    }

    void createWidget() {
        super.createWidget();
        // swFlags = OS.IsWinCE ? OS.SW_SHOWMAXIMIZED : OS.SW_SHOWNOACTIVATE;
        // hAccel = -1;
        /// +
    }
}
