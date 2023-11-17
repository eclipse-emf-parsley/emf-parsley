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
import org.eclipse.emf.parsley.edit.actionbar.LightweightActionBarContributor;
import org.eclipse.emf.parsley.edit.actionbar.WorkbenchActionBarContributor;
import org.eclipse.emf.parsley.viewers.ViewerSelectionProvider;
import org.eclipse.jface.action.IMenuListener;
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
	private Provider<LightweightActionBarContributor> lightweightActionBarContributorProvider;

	@Inject
	private Provider<WorkbenchActionBarContributor> workbenchActionBarContributorProvider;

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
	 * @param workbenchActionBarContributor
	 */
	public void addViewerContextMenu(Viewer viewer, IWorkbenchPart activePart, IMenuListener menuListener,
			WorkbenchActionBarContributor workbenchActionBarContributor) {

		MenuManager menuManager = createContextMenu(viewer);
		menuManager.addMenuListener(menuListener);

		setupWorkbenchActionBarContributorContextMenu(viewer, activePart, workbenchActionBarContributor, menuManager);
	}

	/**
	 * Adds a context menu to the passed {@link StructuredViewer}.
	 *
	 * @param viewer
	 * @param activePart
	 */
	public void addViewerContextMenu(StructuredViewer viewer, IWorkbenchPart activePart) {
		final WorkbenchActionBarContributor workbenchActionBarContributor = workbenchActionBarContributorProvider.get();
		MenuManager menuManager = createContextMenu(viewer, workbenchActionBarContributor);

		setupWorkbenchActionBarContributorContextMenu(viewer, activePart, workbenchActionBarContributor, menuManager);
	}

	private void setupWorkbenchActionBarContributorContextMenu(Viewer viewer, IWorkbenchPart activePart,
			WorkbenchActionBarContributor workbenchActionBarContributor, MenuManager menuManager) {
		activePart.getSite().registerContextMenu(menuManager, new UnwrappingSelectionProvider(viewer));
		bridgeSelectionProviderAndActionBarContributor(viewer, workbenchActionBarContributor);
		workbenchActionBarContributor.setActivePart(activePart);
	}

	private void bridgeSelectionProviderAndActionBarContributor(Viewer viewer,
			WorkbenchActionBarContributor actionBarContributor) {
		ViewerSelectionProvider viewerSelectionProvider = new ViewerSelectionProvider(viewer);
		actionBarContributor.setExplicitSelectionProvider(viewerSelectionProvider);
		viewerSelectionProvider.addSelectionChangedListener(actionBarContributor);
	}

	/**
	 * Adds a context menu to the passed {@link StructuredViewer}.
	 *
	 * @param viewer
	 */
	public void addViewerContextMenu(StructuredViewer viewer) {
		addViewerContextMenu(viewer, (EditingDomain) null);
	}

	/**
	 * Adds a context menu to the passed {@link StructuredViewer}.
	 *
	 * @param viewer
	 * @param editingDomain
	 */
	public void addViewerContextMenu(Viewer viewer, EditingDomain editingDomain) {
		final LightweightActionBarContributor lightweightActionBarContributor = lightweightActionBarContributorProvider
				.get();
		createContextMenu(viewer, lightweightActionBarContributor);
		viewer.addSelectionChangedListener(lightweightActionBarContributor);
		lightweightActionBarContributor.initialize(editingDomain);
	}

	private MenuManager createContextMenu(Viewer viewer) {
		MenuManager menuManager = createMenuManager();
		Menu menu = menuManager.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		return menuManager;
	}

	private MenuManager createContextMenu(Viewer viewer, final IMenuListener menuListener) {
		MenuManager menuManager = createContextMenu(viewer);
		menuManager.addMenuListener(manager -> menuListener.menuAboutToShow(manager));
		return menuManager;
	}

}
