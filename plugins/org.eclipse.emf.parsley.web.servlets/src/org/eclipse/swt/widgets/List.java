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
import org.eclipse.swt.events.SelectionListener;

public class List extends Scrollable {
    static final int INSET = 3;
    boolean addedUCC = false; // indicates whether Bidi UCC were added; 'state & HAS_AUTO_DIRECTION'
                              // isn't a sufficient indicator

    public List(Composite parent, int style) {
        super(parent, checkStyle(style));
    }

    public void add(String string) {
        if (string == null)
            error(SWT.ERROR_NULL_ARGUMENT);
    }

    public void add(String string, int index) {
        if (string == null)
            error(SWT.ERROR_NULL_ARGUMENT);
    }

    public void addSelectionListener(SelectionListener listener) {
        if (listener == null)
            error(SWT.ERROR_NULL_ARGUMENT);
        TypedListener typedListener = new TypedListener(listener);
    }


    static int checkStyle(int style) {
        return checkBits(style, SWT.SINGLE, SWT.MULTI, 0, 0, 0, 0);
    }



}
