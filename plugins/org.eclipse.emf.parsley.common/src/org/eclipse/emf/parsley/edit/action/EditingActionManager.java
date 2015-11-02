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
package org.eclipse.emf.parsley.edit.action;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.ControlAction;
import org.eclipse.emf.parsley.edit.domain.EditingDomainPresetStrategy;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Creates the actions and builds the corresponding menu (by delegating to an
 * injected {@link EditingMenuBuilder}); this class also registers actions using
 * {@link IActionBars} and {@link IPage}.
 * 
 * @author Lorenzo Bettini - initial API and implementation
 */
public class EditingActionManager {

	@Inject
	private EditingMenuBuilder editingMenuBuilder;

	@Inject
	private Provider<EditingDomainPresetStrategy> editingDomainStrategyProvider;

	/**
	 * Creates the actions and sets the corresponding global action
	 * handler using the passed {@link IActionBars}
	 * @param actionBars
	 */
	public void initializeActions(IActionBars actionBars) {

		createActions();

		actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(),
				editingMenuBuilder.getDeleteAction());
		actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(),
				editingMenuBuilder.getCutAction());
		actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(),
				editingMenuBuilder.getCopyAction());
		actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(),
				editingMenuBuilder.getPasteAction());
		actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(),
				editingMenuBuilder.getUndoAction());
		actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(),
				editingMenuBuilder.getRedoAction());
	}

	/**
	 * Creates the action instances; this method is meant to be called only
	 * once.
	 */
	public void createActions() {
		editingMenuBuilder.createActions();

		ISharedImages sharedImages = getSharedImages();

		editingMenuBuilder.getDeleteAction().setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));

		editingMenuBuilder.getCutAction().setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));

		editingMenuBuilder.getCopyAction().setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));

		editingMenuBuilder.getPasteAction().setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));

		editingMenuBuilder.getUndoAction().setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_UNDO));

		editingMenuBuilder.getRedoAction().setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_REDO));
	}

	protected ISharedImages getSharedImages() {
		return PlatformUI.getWorkbench().getSharedImages();
	}

	/**
	 * Creates the actions and sets the corresponding global action
	 * handler using the passed {@link IActionBars} depending on the {@link IPage}
	 * 
	 * @param page
	 * @param actionBars
	 */
	public void shareGlobalActions(IPage page, IActionBars actionBars) {
		if (!(page instanceof IPropertySheetPage)) {
			actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(),
					editingMenuBuilder.getDeleteAction());
			actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(),
					editingMenuBuilder.getCutAction());
			actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(),
					editingMenuBuilder.getCopyAction());
			actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(),
					editingMenuBuilder.getPasteAction());
		}
		actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(),
				editingMenuBuilder.getUndoAction());
		actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(),
				editingMenuBuilder.getRedoAction());
	}

	public void setEditingDomain(EditingDomain editingDomain) {
		if (editingDomain != null) {
			EditingDomainPresetStrategy strategy = editingDomainStrategyProvider.get();
			strategy.setEditingDomain(editingDomain);
			editingMenuBuilder.setEditingDomainFinderStrategy(strategy);
		}
	}

	public void updateSelection(ISelection selection) {
		editingMenuBuilder.updateSelection(selection);
	}

	public void updateUndoRedo() {
		editingMenuBuilder.updateUndoRedo();
	}

	public void menuAboutToShow(IMenuManager menuManager) {
		editingMenuBuilder.menuAboutToShow(menuManager);
	}

	public void emfMenuAboutToShow(IMenuManager menuManager) {
		editingMenuBuilder.emfMenuAboutToShow(menuManager);
	}

	public EditingDomainValidateAction createValidateAction() {
		return editingMenuBuilder.createValidateAction();
	}

	public ControlAction createControlAction() {
		return editingMenuBuilder.createControlAction();
	}

}
