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

public abstract class Scrollable extends Control {

    Scrollable() {}

    public Scrollable(Composite parent, int style) {
        super(parent, style);
    }

    void createWidget() {
        super.createWidget();
        // if ((style & SWT.H_SCROLL) != 0)
        // horizontalBar = createScrollBar(SWT.H_SCROLL);
        // if ((style & SWT.V_SCROLL) != 0)
        // verticalBar = createScrollBar(SWT.V_SCROLL);
        /// +
    }
}
