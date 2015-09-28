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
package org.eclipse.swt.layout;

import org.eclipse.swt.SWT;

public final class GridData {
    public static final int BEGINNING = SWT.BEGINNING;
    public int verticalAlignment = CENTER;
    public int horizontalAlignment = BEGINNING;
    public static final int CENTER = 2;


    public GridData() {
        super();
    }

    public GridData(int style) {
        super();
        // if ((style & VERTICAL_ALIGN_BEGINNING) != 0)
        // verticalAlignment = BEGINNING;
        // if ((style & VERTICAL_ALIGN_CENTER) != 0)
        // verticalAlignment = CENTER;
        // if ((style & VERTICAL_ALIGN_FILL) != 0)
        // verticalAlignment = FILL;
        // if ((style & VERTICAL_ALIGN_END) != 0)
        // verticalAlignment = END;
        // if ((style & HORIZONTAL_ALIGN_BEGINNING) != 0)
        // horizontalAlignment = BEGINNING;
        // if ((style & HORIZONTAL_ALIGN_CENTER) != 0)
        // horizontalAlignment = CENTER;
        // if ((style & HORIZONTAL_ALIGN_FILL) != 0)
        // horizontalAlignment = FILL;
        // if ((style & HORIZONTAL_ALIGN_END) != 0)
        // horizontalAlignment = END;
        // grabExcessHorizontalSpace = (style & GRAB_HORIZONTAL) != 0;
        // grabExcessVerticalSpace = (style & GRAB_VERTICAL) != 0;
        /// +
    }


}
