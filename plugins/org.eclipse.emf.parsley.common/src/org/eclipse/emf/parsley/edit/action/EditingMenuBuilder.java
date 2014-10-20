/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
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
import org.eclipse.emf.parsley.util.EmfSelectionHelper;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

/**
 * Creates the actions and builds the corresponding menu; this class does not
 * depend on eclipse.ui bundles and it can be used without a workbench.
 * 
 * Customizations of this class should provide a different overloaded
 * implementation of the method {@link #menuContributions(IStructuredSelection)}
 * : depending on the parameter, and return a different list of
 * {@link IMenuContributionSpecification} accordingly; such methods will be
 * selected polymorphically.
 * 
 * The creation of the contributions should use the internal DSL methods, e.g.,
 * 
 * <pre>
 * protected List&lt;IMenuContributionSpecification&gt; menuContributions(
 * 		MyClass o) {
 * 	return Lists.newArrayList(actionUndo(), actionRedo(), separator(),
 * 			actionCut(), actionCopy(), actionPaste(), separator(),
 * 			actionDelete(), separator());
 * }
 * 
 * protected List&lt;IMenuContributionSpecification&gt; menuContributions(
 * 		MyOtherClass o) {
 * 	return Lists.newArrayList(
 *  		actionCut(), actionCopy(), actionPaste());
 * }
 * </pre>
 * 
 * @author Lorenzo Bettini - initial API and implementation
 */
public class EditingMenuBuilder {
	
	@Inject
	protected EmfSelectionHelper selectionHelper;

	private CommandActionHandler deleteAction;

	private CommandActionHandler cutAction;

	private CommandActionHandler copyAction;

	private CommandActionHandler pasteAction;

	private UndoAction undoAction;

	private RedoAction redoAction;
	
	private PolymorphicDispatcher<List<IMenuContributionSpecification>> menuContributionsDispatcher = PolymorphicDispatcher
			.createForSingleTarget("menuContributions", 1, 1, this);

	private List<IMenuContributionSpecification> currentMenuContributions = Collections.emptyList();

	/**
	 * Creates the action instances; this method is meant to be called
	 * only once.
	 */
	public void createActions() {
		deleteAction = createDeleteAction();
		cutAction = createCutAction();
		copyAction = createCopyAction();
		pasteAction = createPasteAction();
		undoAction = createUndoAction();
		redoAction = createRedoAction();
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
			
			updateMenuContributions(structuredSelection);
			
			for (IMenuContributionSpecification menuContributionSpecification : currentMenuContributions) {
				menuContributionSpecification.updateSelection(structuredSelection);
			}
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

	public void updateMenuContributions(ISelection selection) {
		currentMenuContributions = menuContributionsDispatcher
				.invoke(selectionHelper.getFirstSelectedElement(selection));
	}

	/**
	 * The default implementation for the contributions that will appear in the menu;
	 * Customizations of this class should provide a different overloaded implementation of
	 * this method depending on the parameter, and return a different list of
	 * {@link IMenuContributionSpecification} accordingly; such methods will be selected
	 * polymorphically.
	 * 
	 * @param object
	 * @return
	 */
	protected List<IMenuContributionSpecification> menuContributions(Object object) {
		return Lists.newArrayList(
				actionUndo(),
				actionRedo(),
				separator(),
				actionCut(),
				actionCopy(),
				actionPaste(),
				separator(),
				actionDelete(),
				separator()
		);
	}

	protected IMenuContributionSpecification separator() {
		return new MenuSeparatorContributionSpecification();
	}

	protected IMenuContributionSpecification actionDelete() {
		return new MenuCommandActionHandlerContributionSpecification(deleteAction);
	}

	protected DeleteAction createDeleteAction() {
		return new DeleteAction(true);
	}

	protected IMenuContributionSpecification actionCut() {
		return new MenuCommandActionHandlerContributionSpecification(cutAction);
	}

	protected CutAction createCutAction() {
		return new CutAction();
	}

	protected IMenuContributionSpecification actionCopy() {
		return new MenuCommandActionHandlerContributionSpecification(copyAction);
	}

	protected CopyAction createCopyAction() {
		return new CopyAction();
	}

	protected IMenuContributionSpecification actionPaste() {
		return new MenuCommandActionHandlerContributionSpecification(pasteAction);
	}

	protected PasteAction createPasteAction() {
		return new PasteAction();
	}

	protected IMenuContributionSpecification actionUndo() {
		return new MenuActionContributionSpecification(undoAction);
	}

	protected UndoAction createUndoAction() {
		return new UndoAction();
	}

	protected IMenuContributionSpecification actionRedo() {
		return new MenuActionContributionSpecification(redoAction);
	}

	protected CommandActionHandler getDeleteAction() {
		return deleteAction;
	}

	protected CommandActionHandler getCutAction() {
		return cutAction;
	}

	protected CommandActionHandler getCopyAction() {
		return copyAction;
	}

	protected CommandActionHandler getPasteAction() {
		return pasteAction;
	}

	protected UndoAction getUndoAction() {
		return undoAction;
	}

	protected RedoAction getRedoAction() {
		return redoAction;
	}

	protected PolymorphicDispatcher<List<IMenuContributionSpecification>> getMenuContributionsDispatcher() {
		return menuContributionsDispatcher;
	}

	protected List<IMenuContributionSpecification> getCurrentMenuContributions() {
		return currentMenuContributions;
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
