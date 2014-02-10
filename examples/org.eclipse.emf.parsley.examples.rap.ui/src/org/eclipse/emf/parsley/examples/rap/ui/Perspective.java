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
package org.eclipse.emf.parsley.examples.rap.ui;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * Configures the perspective layout. This class is contributed through the
 * plugin.xml.
 */
public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);

		layout.addStandaloneView(View.ID, true, IPageLayout.LEFT, .5f,
				editorArea);
		layout.addStandaloneView(SaveableView.ID, true, IPageLayout.LEFT, .5f,
				editorArea);
		
//		IFolderLayout left =
//                layout.createFolder("bottom", IPageLayout.BOTTOM, (float) 0.26, editorArea);
//        left.addView(IPageLayout.ID_PROP_SHEET);
	}
}
