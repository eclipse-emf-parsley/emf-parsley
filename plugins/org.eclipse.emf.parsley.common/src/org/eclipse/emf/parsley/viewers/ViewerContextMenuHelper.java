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
package org.eclipse.emf.parsley.viewers;


import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.ui.provider.UnwrappingSelectionProvider;
import org.eclipse.emf.parsley.edit.actionbar.TreeActionBarContributor;
import org.eclipse.emf.parsley.edit.actionbar.WorkbenchActionBarContributor;
import org.eclipse.emf.parsley.menus.ViewerContextMenuFactory;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.ui.IWorkbenchPart;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * This class contains some useful methods for adding context menu to viewers.
 * 
 * @author Lorenzo Bettini - Initial Contribution and API
 * @author Francesco Guidieri - extensions
 * 
 */
public class ViewerContextMenuHelper {

	@Inject
	protected Provider<AdapterFactory> adapterFactoryProvider;

	@Inject
	protected ViewerContextMenuFactory viewerContextMenuFactory;

	@Inject
	protected Provider<IViewerMouseListener> viewerMouseListenerProvider;


	/**
	 * Adds a context menu to the passed {@link StructuredViewer}.
	 * 
	 * The passed {@link IMenuListener} should implement a method like
	 * 
	 * <pre>
	 * public void menuAboutToShow(IMenuManager menuManager) {
	 * 	actionBarContributor.menuAboutToShow(menuManager);
	 * }
	 * </pre>
	 * 
	 * @param viewer
	 * @param actionBarContributor
	 *            should be created by injection
	 * @param editingDomain
	 *            should be created by injection
	 * @param menuListener
	 *            the listener should have a method like
	 * @param activePart
	 */
	public void addContextMenu(StructuredViewer viewer,
			WorkbenchActionBarContributor actionBarContributor,
			AdapterFactoryEditingDomain editingDomain,
			IMenuListener menuListener, IWorkbenchPart activePart) {

		MenuManager menuManager = viewerContextMenuFactory
				.createContextMenuFor(viewer, editingDomain);
		activePart.getSite().registerContextMenu(menuManager,
				new UnwrappingSelectionProvider(viewer));
		
		menuManager.addMenuListener(menuListener);

		ViewerSelectionProvider viewerSelectionProvider = new ViewerSelectionProvider(
				viewer);
		actionBarContributor
				.setExplicitSelectionProvider(viewerSelectionProvider);
		viewerSelectionProvider
				.addSelectionChangedListener(actionBarContributor);

		actionBarContributor.setActivePart(activePart);
	}
	
	public void addContextMenu(StructuredViewer viewer, 
			TreeActionBarContributor treeActionBarContributor,
			AdapterFactoryEditingDomain editingDomain, 
			IMenuListener menuListener){
		
		final MenuManager menuManager = viewerContextMenuFactory
				.createContextMenuFor(viewer, editingDomain);

		menuManager.addMenuListener(menuListener);
		viewer.addSelectionChangedListener(treeActionBarContributor);
		treeActionBarContributor.initialize(editingDomain);
	}

	/**
	 * Adds the {@link IViewerMouseListener} specified in the guice module.
	 * 
	 * @param viewer
	 */
	public void addMouseListener(StructuredViewer viewer) {
		viewer.getControl().addMouseListener(viewerMouseListenerProvider.get());
	}

	

}
