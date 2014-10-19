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

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.CommandActionHandler;
import org.eclipse.emf.edit.ui.action.ControlAction;
import org.eclipse.emf.edit.ui.action.CopyAction;
import org.eclipse.emf.edit.ui.action.CutAction;
import org.eclipse.emf.edit.ui.action.DeleteAction;
import org.eclipse.emf.edit.ui.action.LoadResourceAction;
import org.eclipse.emf.edit.ui.action.PasteAction;
import org.eclipse.emf.edit.ui.action.RedoAction;
import org.eclipse.emf.edit.ui.action.UndoAction;
import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcher;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import com.google.common.collect.Lists;

@SuppressWarnings("restriction")
public class EditingActionManager {

	private CommandActionHandler deleteAction;

	private CommandActionHandler cutAction;

	private CommandActionHandler copyAction;

	private CommandActionHandler pasteAction;

	private UndoAction undoAction;

	private RedoAction redoAction;
	
	private PolymorphicDispatcher<List<IMenuContributionSpecification>> menuContributionsDispatcher = PolymorphicDispatcher
			.createForSingleTarget("menuContributions", 1, 1, this);

	private List<IMenuContributionSpecification> currentMenuContributions = Collections.emptyList();

	public void initializeActions(IActionBars actionBars) {

		createActions();

		actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(),
				deleteAction);
		actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(), cutAction);
		actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(),
				copyAction);
		actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(),
				pasteAction);
		actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(),
				undoAction);
		actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(),
				redoAction);
	}

	public void createActions() {
		ISharedImages sharedImages = getSharedImages();

		deleteAction = createDeleteAction();
		deleteAction.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));

		cutAction = createCutAction();
		cutAction.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));

		copyAction = createCopyAction();
		copyAction.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));

		pasteAction = createPasteAction();
		pasteAction.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));

		undoAction = createUndoAction();
		undoAction.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_UNDO));

		redoAction = createRedoAction();
		redoAction.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_REDO));
	}

	protected ISharedImages getSharedImages() {
		return new ISharedImages() {
			public Image getImage(String symbolicName) {
				return getImageDescriptor(symbolicName).createImage();
			}
			public ImageDescriptor getImageDescriptor(String symbolicName) {
				return (ImageDescriptor) WorkbenchImages.getImageDescriptor(symbolicName);
			}
		};
	}

	public void shareGlobalActions(IPage page, IActionBars actionBars) {
		if (!(page instanceof IPropertySheetPage)) {
			actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(),
					deleteAction);
			actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(),
					cutAction);
			actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(),
					copyAction);
			actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(),
					pasteAction);
		}
		actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(),
				undoAction);
		actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(),
				redoAction);
	}

	public void removeSelectionChangeListener(
			ISelectionProvider selectionProvider) {
		selectionProvider.removeSelectionChangedListener(deleteAction);
		selectionProvider.removeSelectionChangedListener(cutAction);
		selectionProvider.removeSelectionChangedListener(copyAction);
		selectionProvider.removeSelectionChangedListener(pasteAction);
	}

	public void setEditingDomain(EditingDomain editingDomain) {
		deleteAction.setEditingDomain(editingDomain);
		cutAction.setEditingDomain(editingDomain);
		copyAction.setEditingDomain(editingDomain);
		pasteAction.setEditingDomain(editingDomain);
		undoAction.setEditingDomain(editingDomain);
		redoAction.setEditingDomain(editingDomain);
	}

	public void addSelectionListener(ISelectionProvider selectionProvider) {
		selectionProvider.addSelectionChangedListener(deleteAction);
		selectionProvider.addSelectionChangedListener(cutAction);
		selectionProvider.addSelectionChangedListener(copyAction);
		selectionProvider.addSelectionChangedListener(pasteAction);
	}

	public void updateSelection(ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			deleteAction.updateSelection(structuredSelection);
			cutAction.updateSelection(structuredSelection);
			copyAction.updateSelection(structuredSelection);
			pasteAction.updateSelection(structuredSelection);
			
			updateMenuContributions(structuredSelection);
		}
	}

	public void updateUndoRedo() {
		undoAction.update();
		redoAction.update();
	}

	public void menuAboutToShow(IMenuManager menuManager) {
		for (IMenuContributionSpecification menuContributionSpecification : currentMenuContributions) {
			menuManager.add(menuContributionSpecification.getContributionItem());
		}
	}

	protected void updateMenuContributions(IStructuredSelection selection) {
		currentMenuContributions = menuContributionsDispatcher.invoke(selection);
	}

	protected List<IMenuContributionSpecification> menuContributions(IStructuredSelection selection) {
		return Lists.newArrayList(
				undoAction(),
				redoAction(),
				separator(),
				cutAction(),
				copyAction(),
				pasteAction(),
				separator(),
				deleteAction(),
				separator()
		);
	}

	protected IMenuContributionSpecification separator() {
		return new MenuSeparatorContributionSpecification();
	}

	protected IMenuContributionSpecification deleteAction() {
		return new MenuCommandActionHandlerContributionSpecification(deleteAction);
	}

	protected DeleteAction createDeleteAction() {
		return new DeleteAction(true);
	}

	protected IMenuContributionSpecification cutAction() {
		return new MenuCommandActionHandlerContributionSpecification(cutAction);
	}

	protected CutAction createCutAction() {
		return new CutAction();
	}

	protected IMenuContributionSpecification copyAction() {
		return new MenuCommandActionHandlerContributionSpecification(copyAction);
	}

	protected CopyAction createCopyAction() {
		return new CopyAction();
	}

	protected IMenuContributionSpecification pasteAction() {
		return new MenuCommandActionHandlerContributionSpecification(pasteAction);
	}

	protected PasteAction createPasteAction() {
		return new PasteAction();
	}

	protected IMenuContributionSpecification undoAction() {
		return new MenuActionContributionSpecification(undoAction);
	}

	protected UndoAction createUndoAction() {
		return new UndoAction();
	}

	protected IMenuContributionSpecification redoAction() {
		return new MenuActionContributionSpecification(redoAction);
	}

	protected RedoAction createRedoAction() {
		return new RedoAction();
	}

	protected LoadResourceAction createLoadResourceAction() {
		return new LoadResourceAction();
	}

	public ControlAction createControlAction() {
		return new ControlAction();
	}

	public EditingDomainValidateAction createValidateAction() {
		return new EditingDomainValidateAction();
	}

}
