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

public class DateTime extends Composite {

    public DateTime(Composite parent, int style) {
        super(parent, checkStyle(style));
    }

    static int checkStyle(int style) {
        // style &= ~(SWT.H_SCROLL | SWT.V_SCROLL);
        // style = checkBits(style, SWT.DATE, SWT.TIME, SWT.CALENDAR, 0, 0, 0);
        // style = checkBits(style, SWT.MEDIUM, SWT.SHORT, SWT.LONG, 0, 0, 0);
        // if ((style & SWT.DATE) == 0)
        // style &= ~SWT.DROP_DOWN;
        return style; /// +
    }

}
