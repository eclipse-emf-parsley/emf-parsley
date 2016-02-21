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
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.composite.TreeFormComposite;
import org.eclipse.emf.parsley.composite.TreeFormFactory;
import org.eclipse.emf.parsley.edit.ui.dnd.ViewerDragAndDropHelper;
import org.eclipse.emf.parsley.examples.eclipse4.parsleypart.ParsleypartActivator;
import org.eclipse.emf.parsley.menus.ViewerContextMenuHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Injector;

public class ParsleyE4ModelPart {

	// Guice injector
	private Injector injector = ParsleypartActivator.getDefault().getInjector();

	private TreeFormComposite treeFormComposite;

	@Inject
	MApplication application;

	@PostConstruct
	public void postConstruct(Composite parent) {
		// Guice injected EMF Parsley factory for the tree detail form
		TreeFormFactory treeFormFactory = injector.getInstance(TreeFormFactory.class);
		// Guice injected viewer context menu helper
		ViewerContextMenuHelper contextMenuHelper = injector.getInstance(ViewerContextMenuHelper.class);
		// Guice injected viewer drag and drop helper
		ViewerDragAndDropHelper dragAndDropHelper = injector.getInstance(ViewerDragAndDropHelper.class);
		// The EditingDomain is needed for context menu and drag and drop
		EditingDomain editingDomain = injector.getInstance(EditingDomain.class);

		// Initialize Parsley Tree Form:
		// 1) create the tree-form composite
		treeFormComposite = treeFormFactory.createTreeFormComposite(parent, SWT.BORDER);

		// 2) initialize the context menu to the tree-form composite
		contextMenuHelper.addViewerContextMenu(treeFormComposite.getViewer(), editingDomain);

		// 3) set drag and drop to the tree-form composite
		dragAndDropHelper.addDragAndDrop(treeFormComposite.getViewer(), editingDomain);

		// 4) fill the data
		treeFormComposite.update(application);
	}

	@Focus
	public void onFocus() {
		treeFormComposite.setFocus();
	}
}