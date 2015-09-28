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

public class TableItem extends Item {
    Table parent;

    public TableItem(Table parent, int style) {
        this(parent, style, checkNull(parent).getItemCount(), true);
    }


    TableItem(Table parent, int style, int index, boolean create) {
        super(parent, style);
        this.parent = parent;
        if (create)
            parent.createItem(this, index);
    }

    static Table checkNull(Table control) {
        if (control == null)
            SWT.error(SWT.ERROR_NULL_ARGUMENT);
        return control;
    }
}
