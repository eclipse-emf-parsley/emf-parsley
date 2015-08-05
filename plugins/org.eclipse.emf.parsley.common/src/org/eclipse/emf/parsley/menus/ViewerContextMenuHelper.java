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
package org.eclipse.emf.parsley.menus;

import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.emf.edit.ui.provider.UnwrappingSelectionProvider;
import org.eclipse.emf.parsley.edit.actionbar.TreeActionBarContributor;
import org.eclipse.emf.parsley.edit.actionbar.WorkbenchActionBarContributor;
import org.eclipse.emf.parsley.viewers.ViewerSelectionProvider;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchPart;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Lorenzo Bettini
 * 
 */
public class ViewerContextMenuHelper {

	@Inject
	private Provider<AdapterFactoryEditingDomain> editingDomainProvider;
	
	@Inject
	private TreeActionBarContributor treeActionBarContributor;
	
	@Inject
	private WorkbenchActionBarContributor workbenchActionBarContributor;

	

	protected void addDragAndDrop(StructuredViewer viewer,
			AdapterFactoryEditingDomain editingDomain) {
		int dndOperations = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
		Transfer[] transfers = new Transfer[] { LocalTransfer.getInstance() };
		viewer.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(
				viewer));
		viewer.addDropSupport(dndOperations, transfers,
				new EditingDomainViewerDropAdapter(editingDomain,
						viewer));
	}

	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("#PopUp");
		menuManager.add(new Separator("additions"));
		menuManager.setRemoveAllWhenShown(true);
		return menuManager;
	}




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
	 * @param editingDomain
	 *            should be created by injection
	 * @param activePart
	 * @param menuListener
	 *            the listener should have a method like
	 * @param actionBarContributor
	 *            should be created by injection
	 */
	public void addViewerContextMenu(StructuredViewer viewer,
			AdapterFactoryEditingDomain editingDomain,
			IWorkbenchPart activePart,
			IMenuListener menuListener, WorkbenchActionBarContributor actionBarContributor) {

		MenuManager menuManager = createContextMenu(viewer, editingDomain);
		activePart.getSite().registerContextMenu(menuManager,
				new UnwrappingSelectionProvider(viewer));
		
		menuManager.addMenuListener(menuListener);

		bridgeSelectionProviderAndActionBarContributor(viewer, actionBarContributor);

		actionBarContributor.setActivePart(activePart);
	}

	public void addViewerContextMenu(StructuredViewer viewer,
			AdapterFactoryEditingDomain editingDomain, IWorkbenchPart activePart) {

		MenuManager menuManager = createContextMenu(viewer, editingDomain, workbenchActionBarContributor);

		activePart.getSite().registerContextMenu(menuManager,
				new UnwrappingSelectionProvider(viewer));
		
		bridgeSelectionProviderAndActionBarContributor(viewer, workbenchActionBarContributor);
		
		workbenchActionBarContributor.setActivePart(activePart);
	}

	public void addViewerContextMenu(StructuredViewer viewer){
		addViewerContextMenu(viewer, editingDomainProvider.get());
	}

	public void addViewerContextMenu(StructuredViewer viewer, AdapterFactoryEditingDomain editingDomain){
		
		createContextMenu(viewer, editingDomain, treeActionBarContributor);
		viewer.addSelectionChangedListener(treeActionBarContributor);
		treeActionBarContributor.initialize(editingDomain);
	}
	

	private MenuManager createContextMenu(StructuredViewer viewer, AdapterFactoryEditingDomain editingDomain) {
		MenuManager menuManager = createMenuManager();
		Menu menu = menuManager.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		addDragAndDrop(viewer, editingDomain);
		return menuManager;
	}

	private MenuManager createContextMenu(StructuredViewer viewer, AdapterFactoryEditingDomain editingDomain, final IMenuListener menuListener) {
		MenuManager menuManager = createContextMenu(viewer, editingDomain);
		menuManager.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(IMenuManager manager) {
				menuListener.menuAboutToShow(manager);
			}
		});
		return menuManager;
	}

	private void bridgeSelectionProviderAndActionBarContributor(StructuredViewer viewer,
			WorkbenchActionBarContributor actionBarContributor) {
		ViewerSelectionProvider viewerSelectionProvider = new ViewerSelectionProvider(
				viewer);
		actionBarContributor
		.setExplicitSelectionProvider(viewerSelectionProvider);
		viewerSelectionProvider
		.addSelectionChangedListener(actionBarContributor);
	}

}

