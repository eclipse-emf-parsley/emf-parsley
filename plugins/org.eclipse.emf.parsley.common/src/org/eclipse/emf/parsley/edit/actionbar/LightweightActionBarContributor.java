/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Francesco Guidieri - initial API and implementation
 * Lorenzo Bettini - updated for using EditingMenuBuilder
 *******************************************************************************/
package org.eclipse.emf.parsley.edit.actionbar;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.edit.action.EditingActionManager;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;

import com.google.inject.Inject;

/**
 * This class has been created to replace {link WorkbenchActionBarContributor}
 * where a workbench is not available (for example in e4) or simply to use a
 * lightweight version for views. The main difference is that this class has to
 * be initialized with editing domain.
 *
 * <pre>
 *
 * viewer.addSelectionChangedListener(treeActionBarContributor);
 *
 * treeActionBarContributor.initialize(editingDomain);
 *
 * </pre>
 *
 * @author Francesco Guidieri - initial API and implementation
 * @author Lorenzo Bettini - updated for using EditingMenuBuilder
 */
public class LightweightActionBarContributor implements IMenuListener, ISelectionChangedListener {

	@Inject
	private EditingActionManager editingActionManager;

	public void initialize(EditingDomain editingDomain) {
		editingActionManager.createActions();
		editingActionManager.setEditingDomain(editingDomain);
	}

	@Override
	public void menuAboutToShow(IMenuManager menuManager) {
		menuManager.add(new Separator("edit"));
		editingActionManager.emfMenuAboutToShow(menuManager);
		editingActionManager.menuAboutToShow(menuManager);
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		editingActionManager.updateSelection(event.getSelection());
	}

}
