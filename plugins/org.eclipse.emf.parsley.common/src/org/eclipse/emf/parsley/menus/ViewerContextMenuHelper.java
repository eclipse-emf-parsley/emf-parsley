/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.menus;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.provider.UnwrappingSelectionProvider;
import org.eclipse.emf.parsley.edit.actionbar.TreeActionBarContributor;
import org.eclipse.emf.parsley.edit.actionbar.WorkbenchActionBarContributor;
import org.eclipse.emf.parsley.viewers.ViewerSelectionProvider;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchPart;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Provides utility methods for adding context menu to a viewer.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 * @author Francesco Guidieri - refactoring for
 *         https://bugs.eclipse.org/bugs/show_bug.cgi?id=455727
 */
public class ViewerContextMenuHelper {

	@Inject
	private Provider<EditingDomain> editingDomainProvider;

	@Inject
	private TreeActionBarContributor treeActionBarContributor;

	@Inject
	private WorkbenchActionBarContributor workbenchActionBarContributor;

	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("#PopUp");
		menuManager.add(new Separator("additions"));
		menuManager.setRemoveAllWhenShown(true);
		return menuManager;
	}

	/**
	 * Adds a context menu to the passed {@link Viewer}.
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
	 * @param activePart
	 * @param menuListener
	 *            the listener should have a method like
	 * @param actionBarContributor
	 *            should be created by injection
	 */
	public void addViewerContextMenu(Viewer viewer,
			IWorkbenchPart activePart,
			IMenuListener menuListener,
			WorkbenchActionBarContributor actionBarContributor) {

		MenuManager menuManager = createContextMenu(viewer);
		activePart.getSite().registerContextMenu(menuManager,
				new UnwrappingSelectionProvider(viewer));

		menuManager.addMenuListener(menuListener);

		bridgeSelectionProviderAndActionBarContributor(viewer, actionBarContributor);

		actionBarContributor.setActivePart(activePart);
	}

	/**
	 * Adds a context menu to the passed {@link StructuredViewer}.
	 * 
	 * @param viewer
	 * @param activePart
	 */
	public void addViewerContextMenu(StructuredViewer viewer, IWorkbenchPart activePart) {

		MenuManager menuManager = createContextMenu(viewer, workbenchActionBarContributor);

		activePart.getSite().registerContextMenu(menuManager,
				new UnwrappingSelectionProvider(viewer));

		bridgeSelectionProviderAndActionBarContributor(viewer, workbenchActionBarContributor);

		workbenchActionBarContributor.setActivePart(activePart);
	}

	/**
	 * Adds a context menu to the passed {@link StructuredViewer}.
	 * 
	 * @param viewer
	 */
	public void addViewerContextMenu(StructuredViewer viewer){
		addViewerContextMenu(viewer, editingDomainProvider.get());
	}

	/**
	 * Adds a context menu to the passed {@link StructuredViewer}.
	 * 
	 * @param viewer
	 * @param editingDomain
	 *            should be created by injection
	 */
	public void addViewerContextMenu(Viewer viewer, EditingDomain editingDomain) {
		createContextMenu(viewer, treeActionBarContributor);
		viewer.addSelectionChangedListener(treeActionBarContributor);
		treeActionBarContributor.initialize(editingDomain);
	}

	private MenuManager createContextMenu(Viewer viewer) {
		MenuManager menuManager = createMenuManager();
		Menu menu = menuManager.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		return menuManager;
	}

	private MenuManager createContextMenu(Viewer viewer, final IMenuListener menuListener) {
		MenuManager menuManager = createContextMenu(viewer);
		menuManager.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(IMenuManager manager) {
				menuListener.menuAboutToShow(manager);
			}
		});
		return menuManager;
	}

	private void bridgeSelectionProviderAndActionBarContributor(Viewer viewer,
			WorkbenchActionBarContributor actionBarContributor) {
		ViewerSelectionProvider viewerSelectionProvider = new ViewerSelectionProvider(viewer);
		actionBarContributor.setExplicitSelectionProvider(viewerSelectionProvider);
		viewerSelectionProvider.addSelectionChangedListener(actionBarContributor);
	}

}

