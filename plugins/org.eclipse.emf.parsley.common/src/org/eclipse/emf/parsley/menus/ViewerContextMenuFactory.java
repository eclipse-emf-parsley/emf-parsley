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
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Menu;

/**
 * @author Lorenzo Bettini
 * 
 */
public class ViewerContextMenuFactory {

	public MenuManager createContextMenuFor(StructuredViewer viewer,
			AdapterFactoryEditingDomain editingDomain) {
		MenuManager menuManager = createMenuManager();
		createMenu(viewer, menuManager);
		
		addDragAndDrop(viewer, editingDomain);
		
		return menuManager;
	}

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

	protected void createMenu(StructuredViewer viewer,
			MenuManager menuManager) {
		Menu menu = menuManager.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		
	}

	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("#PopUp");
		menuManager.add(new Separator("additions"));
		menuManager.setRemoveAllWhenShown(true);
		return menuManager;
	}

}
