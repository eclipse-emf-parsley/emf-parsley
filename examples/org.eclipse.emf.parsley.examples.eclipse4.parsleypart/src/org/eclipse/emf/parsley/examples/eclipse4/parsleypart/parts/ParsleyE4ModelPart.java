/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Vincenzo Caselli - initial API and implementation
 * Francesco Guidieri - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.examples.eclipse4.parsleypart.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.emf.parsley.composite.TreeFormComposite;
import org.eclipse.emf.parsley.composite.TreeFormFactory;
import org.eclipse.emf.parsley.examples.eclipse4.parsleypart.ParsleypartActivator;
import org.eclipse.emf.parsley.menus.ViewerContextMenuHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Injector;

public class ParsleyE4ModelPart {

	//Guice injector
	private Injector injector=ParsleypartActivator.getDefault().getInjector();

	// Guice injected EMF Parsley factory for the tree detail form
	private TreeFormFactory treeFormFactory;
	// Guice injected viewer initializer
	private ViewerContextMenuHelper contextMenuFactory;
	
	private TreeFormComposite treeFormComposite;
	
	@Inject
	MApplication application;

	@Inject
	@PostConstruct
	public void postConstruct(Composite parent) {
		// Guice injected EMF Parsley factory for the tree detail form
		treeFormFactory = injector.getInstance(TreeFormFactory.class);
		// Guice injected viewer initializer
		contextMenuFactory = injector.getInstance(ViewerContextMenuHelper.class);
		
		// Initialize Parsley Tree Form:  
		// 1) create the tree-form composite
		treeFormComposite = treeFormFactory
				.createTreeFormComposite(parent, SWT.BORDER);

		// 2) initialize and bind the context menu to the tree-form composite
		contextMenuFactory.addViewerContextMenu(treeFormComposite.getViewer());
		
		// 4) fill the data
		treeFormComposite.update(application);
	}

	

	@Focus
	public void onFocus() {
		treeFormComposite.setFocus();
	}
}