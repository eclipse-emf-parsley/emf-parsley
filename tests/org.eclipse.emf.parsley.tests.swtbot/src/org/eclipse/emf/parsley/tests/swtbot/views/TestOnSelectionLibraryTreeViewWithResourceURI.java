/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.swtbot.views;


import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.parsley.views.OnSelectionTreeView;
import org.eclipse.swt.widgets.Composite;

/**
 * OnSelectionTreeView with hardcoded resource URI
 * 
 * @author Lorenzo Bettini
 * 
 */
public class TestOnSelectionLibraryTreeViewWithResourceURI extends OnSelectionTreeView {

	public static final String resourceUri = "platform:/plugin/org.eclipse.emf.parsley.tests.swtbot/models/My.extlibrary";

	public TestOnSelectionLibraryTreeViewWithResourceURI() {

	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		init(URI.createURI(resourceUri));
	}

}
