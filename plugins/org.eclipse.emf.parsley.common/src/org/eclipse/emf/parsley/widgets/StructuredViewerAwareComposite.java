/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.widgets;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.widgets.Composite;

public class StructuredViewerAwareComposite extends Composite implements IStructuredViewerAware {

    private StructuredViewer viewer;

    public StructuredViewerAwareComposite(Composite parent, int style) {
        super(parent, style);
    }

    @Override
	public void setViewer(StructuredViewer viewer) {
        this.viewer = viewer;
    }

    @Override
	public StructuredViewer getViewer() {
        return viewer;
    }

}
