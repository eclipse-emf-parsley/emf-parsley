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
package org.eclipse.swt;


public class SWT {

    public static final int NONE = 0;
    public static final int BEGINNING = 1;
    public static final int BORDER = 1 << 11;
    public static final int H_SCROLL = 1 << 8;
    public static final int V_SCROLL = 1 << 9;
    public static final int DROP_DOWN = 1 << 2;
    public static final int SIMPLE = 1 << 6;
    public static final int READ_ONLY = 1 << 3;
    public static final int NO_SCROLL = 1 << 4;
    public static final int SINGLE = 1 << 2;
    public static final int MULTI = 1 << 1;
    public static final int FLAT = 1 << 23;

    public static final int ERROR_NULL_ARGUMENT = 4;

    public static final int TRAIL = 1 << 17;
    public static final int LEAD = 1 << 14;

    public static final int LEFT = LEAD;
    public static final int RIGHT = TRAIL;
    public static final int CENTER = 1 << 24;

    public static final int LEFT_TO_RIGHT = 1 << 25;
    public static final int RIGHT_TO_LEFT = 1 << 26;
    public static final int NO_TRIM = 1 << 3;

    public static final int ERROR_WIDGET_DISPOSED = 24;

    public static final int SEARCH = 1 << 7;
    public static final int PASSWORD = 1 << 22;
    public static final int WRAP = 1 << 6;
    public static final int CLOSE = 1 << 6;
    public static final int TITLE = 1 << 5;
    public static final int MIN = 1 << 7;
    public static final int MAX = 1 << 10;
    public static final int RESIZE = 1 << 4;
    public static final int SHEET = 1 << 28;
    public static final int ALL = 1 << 0;
    public static final int ARROW = 1 << 2;
    public static final int DOWN = 1 << 10;
    public static final int UP = 1 << 7;

    public static final int MIRRORED = 1 << 27;
    public static final int TRANSPARENT = 1 << 30;
    public static final int SYSTEM_MODAL = 1 << 17;
    public static final int APPLICATION_MODAL = 1 << 16;
    public static final int NO_FOCUS = 1 << 19;
    public static final int ERROR_INVALID_RANGE = 6;
    public static final int ERROR_INVALID_ARGUMENT = 5;

    public static final int PRIMARY_MODAL = 1 << 15;

    public static final int SHELL_TRIM = CLOSE | TITLE | MIN | MAX | RESIZE;
    public static final int DIALOG_TRIM = TITLE | CLOSE | BORDER;

    public static final int Modify = 24;
    public static final int Dispose = 12;

    public static final String SKIN_CLASS = "org.eclipse.swt.skin.class"; //$NON-NLS-1$
    public static final String SKIN_ID = "org.eclipse.swt.skin.id"; //$NON-NLS-1$
    public static final int KeyDown = 1;
    public static final int KeyUp = 2;
    public static final int Verify = 25;


    public static final int TOGGLE = 1 << 1;
    public static final int PUSH = 1 << 3;
    public static final int RADIO = 1 << 4;
    public static final int CHECK = 1 << 5;
    public static final int COMMAND = 1 << 22;

    public static void error(int code) {
        System.err.println(code); /// +
    }
}
