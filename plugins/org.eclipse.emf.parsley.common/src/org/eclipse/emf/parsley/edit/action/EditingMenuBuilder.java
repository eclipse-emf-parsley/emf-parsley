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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.ChangeCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.CommandActionHandler;
import org.eclipse.emf.edit.ui.action.ControlAction;
import org.eclipse.emf.edit.ui.action.CopyAction;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.emf.edit.ui.action.CreateSiblingAction;
import org.eclipse.emf.edit.ui.action.CutAction;
import org.eclipse.emf.edit.ui.action.DeleteAction;
import org.eclipse.emf.edit.ui.action.LoadResourceAction;
import org.eclipse.emf.edit.ui.action.PasteAction;
import org.eclipse.emf.edit.ui.action.RedoAction;
import org.eclipse.emf.edit.ui.action.UndoAction;
import org.eclipse.emf.parsley.edit.domain.EditingDomainFinderStrategy;
import org.eclipse.emf.parsley.runtime.util.IAcceptor;
import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcher;
import org.eclipse.emf.parsley.util.EmfSelectionHelper;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;

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
 * protected List&lt;IMenuContributionSpecification&gt; menuContributions(MyClass o) {
 * 	return Lists.newArrayList(actionUndo(), actionRedo(), separator(), actionCut(), actionCopy(), actionPaste(),
 * 			separator(), actionDelete(), separator());
 * }
 * 
 * protected List&lt;IMenuContributionSpecification&gt; menuContributions(MyOtherClass o) {
 * 	return Lists.newArrayList(actionCut(), actionCopy(), actionPaste());
 * }
 * </pre>
 * 
 * @author Lorenzo Bettini - initial API and implementation
 */
public class EditingMenuBuilder {

	/**
	 * Customization for executing a lambda; it also implements
	 * getAffectedObjects() which is crucial for dirty state handling.
	 * 
	 * @author Lorenzo Bettini
	 *
	 * @param <T>
	 */
	private static final class CustomChangeCommand<T extends Notifier> extends ChangeCommand {
		private final T element;
		private final IAcceptor<T> changeImplementation;

		private CustomChangeCommand(T element, IAcceptor<T> changeImplementation) {
			super(element);
			this.element = element;
			this.changeImplementation = changeImplementation;
		}

		@Override
		protected void doExecute() {
			changeImplementation.accept(element);
		}

		@Override
		public Collection<?> getAffectedObjects() {
			return changeDescription.getObjectChanges().keySet();
		}
	}

	/**
	 * Custom implementation of doUndo to make getAffectedObjects() work
	 * correctly in our context, see
	 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=476289
	 * 
	 * @author Lorenzo Bettini
	 * 
	 * @param <T>
	 */
	private static class CustomAddCommand<T> extends AddCommand {

		public CustomAddCommand(EditingDomain domain, EList<? super T> list, T value) {
			super(domain, list, value);
		}

		@Override
		public void doUndo() {
			// we must save the container, before undoing, since that
			// will remove the object from the container
			EObject eContainer = ((EObject) collection.iterator().next()).eContainer();
			super.doUndo();
			affectedObjects = Collections.singleton(eContainer);
		}
	}

	@Inject
	private EmfSelectionHelper selectionHelper;

	@Inject
	private ILabelProvider labelProvider;

	@Inject
	private EditingDomainFinderStrategy editingDomainFinderStrategy;

	private CommandActionHandler deleteAction;

	private CommandActionHandler cutAction;

	private CommandActionHandler copyAction;

	private CommandActionHandler pasteAction;

	private UndoAction undoAction;

	private RedoAction redoAction;

	private PolymorphicDispatcher<List<IMenuContributionSpecification>> menuContributionsDispatcher = PolymorphicDispatcher
			.createForSingleTarget("menuContributions", 1, 1, this);

	private PolymorphicDispatcher<List<IMenuContributionSpecification>> emfMenuContributionsDispatcher = PolymorphicDispatcher
			.createForSingleTarget("emfMenuContributions", 1, 1, this);

	private List<IMenuContributionSpecification> currentMenuContributions = Collections.emptyList();

	private List<IMenuContributionSpecification> currentEmfMenuContributions = Collections.emptyList();

	/**
	 * this will be used only for checking the default "null" result for
	 * polymorphic invocation avoiding to return null
	 */
	private static final List<IMenuContributionSpecification> POLYMORPHIC_NULL_RESULT = new ArrayList<IMenuContributionSpecification>();

	protected List<IMenuContributionSpecification> getCurrentEmfMenuContributions() {
		return currentEmfMenuContributions;
	}

	protected List<IMenuContributionSpecification> getCurrentMenuContributions() {
		return currentMenuContributions;
	}

	public void setEditingDomainFinderStrategy(EditingDomainFinderStrategy strategy) {
		editingDomainFinderStrategy = strategy;
	}

	/**
	 * Creates the action instances; this method is meant to be called only
	 * once.
	 */
	public void createActions() {
		deleteAction = createDeleteAction();
		cutAction = createCutAction();
		copyAction = createCopyAction();
		pasteAction = createPasteAction();
		undoAction = createUndoAction();
		redoAction = createRedoAction();
	}

	protected EditingDomain getEditingDomain() {
		return editingDomainFinderStrategy.getEditingDomain();
	}

	protected void updateEditingDomain(Object object) {
		editingDomainFinderStrategy.updateEditingDomain(object);
		updateEditingDomainInActions(getEditingDomain());
	}

	protected void updateEditingDomainInActions(EditingDomain editingDomain) {
		deleteAction.setEditingDomain(editingDomain);
		cutAction.setEditingDomain(editingDomain);
		copyAction.setEditingDomain(editingDomain);
		pasteAction.setEditingDomain(editingDomain);
		undoAction.setEditingDomain(editingDomain);
		redoAction.setEditingDomain(editingDomain);
	}

	public void updateSelection(ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;

			updateMenuContributions(structuredSelection);

			for (IMenuContributionSpecification menuContributionSpecification : getCurrentMenuContributions()) {
				menuContributionSpecification.updateSelection(structuredSelection);
			}
		}
	}

	public void updateUndoRedo() {
		if (getEditingDomain() != null) {
			undoAction.update();
			redoAction.update();
		}
	}

	public void menuAboutToShow(IMenuManager menuManager) {
		for (IMenuContributionSpecification menuContributionSpecification : getCurrentMenuContributions()) {
			menuManager.add(menuContributionSpecification.getContributionItem());
		}
	}

	public void emfMenuAboutToShow(IMenuManager menuManager) {
		for (IMenuContributionSpecification menuContributionSpecification : getCurrentEmfMenuContributions()) {
			menuManager.add(menuContributionSpecification.getContributionItem());
		}
	}

	protected void updateMenuContributions(ISelection selection) {
		Object firstSelectedElement = selectionHelper.getFirstSelectedElement(selection);

		updateEditingDomain(firstSelectedElement);
		if (getEditingDomain() == null) {
			currentMenuContributions = Collections.emptyList();
			currentEmfMenuContributions = Collections.emptyList();
			return;
		}

		currentMenuContributions = menuContributionsDispatcher.invoke(firstSelectedElement);

		currentEmfMenuContributions = emfMenuContributionsDispatcher.invoke(firstSelectedElement);

		if (POLYMORPHIC_NULL_RESULT == currentEmfMenuContributions) {
			// for creating new child and new sibling standard actions we need
			// an ISelection
			currentEmfMenuContributions = emfMenuContributionsDispatcher.invoke(selection);
		}
	}

	/**
	 * The default implementation for the contributions that will appear in the
	 * menu; Customizations of this class should provide a different overloaded
	 * implementation of this method depending on the parameter, and return a
	 * different list of {@link IMenuContributionSpecification} accordingly;
	 * such methods will be selected polymorphically.
	 * 
	 * @param object
	 * @return
	 */
	protected List<IMenuContributionSpecification> menuContributions(Object object) {
		return defaultMenuContributions(object);
	}

	/**
	 * The default implementation on Object.
	 * 
	 * @param object
	 * @return
	 */
	protected List<IMenuContributionSpecification> emfMenuContributions(Object object) {
		return POLYMORPHIC_NULL_RESULT;
	}

	/**
	 * The default implementation an {@link ISelection} for the contributions
	 * for EMF related actions that will appear in the menu; Customizations of
	 * this class should provide a different overloaded implementation of this
	 * method depending on the parameter, and return a different list of
	 * {@link IMenuContributionSpecification} accordingly; such methods will be
	 * selected polymorphically.
	 * 
	 * @param selection
	 * @return
	 */
	protected List<IMenuContributionSpecification> emfMenuContributions(ISelection selection) {
		return defaultEmfMenuContributions(selection);
	}

	/**
	 * The default polymorphic implementation.
	 * 
	 * @param object
	 * @return
	 */
	protected List<IMenuContributionSpecification> defaultMenuContributions(Object object) {
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

	/**
	 * The default polymorphic implementation.
	 * 
	 * @param selection
	 * @return
	 */
	protected List<IMenuContributionSpecification> defaultEmfMenuContributions(ISelection selection) {
		return Lists.newArrayList(
				submenu("&New Child", createChildActions(selection)),
				submenu("N&ew Sibling", createSiblingActions(selection))
		);
	}

	protected IMenuContributionSpecification separator() {
		return new MenuSeparatorContributionSpecification();
	}

	protected IMenuContributionSpecification submenu(String text,
			List<IMenuContributionSpecification> menuContributions) {
		return new MenuSubmenuSpecification(text, menuContributions);
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

	protected RedoAction createRedoAction() {
		return new RedoAction();
	}

	protected LoadResourceAction createLoadResourceAction() {
		return new LoadResourceAction();
	}

	protected ControlAction createControlAction() {
		return new ControlAction();
	}

	protected EditingDomainValidateAction createValidateAction() {
		return new EditingDomainValidateAction();
	}

	/**
	 * Creates a menu contribution for an action with the given text, that will
	 * add the specified value to the specified list, when executed.
	 * 
	 * @param text
	 * @param list
	 * @param value
	 * @return
	 */
	protected <T> IMenuContributionSpecification actionAdd(String text, EList<? super T> list, T value) {
		return actionAdd(text, list, value, null);
	}

	/**
	 * Creates a menu contribution for an action with the given text, that will
	 * add the specified value to the specified list, when executed; the passed
	 * addedObjectInizialier will be executed after the object has been added to
	 * the specified list.
	 * 
	 * @param text
	 * @param list
	 * @param value
	 * @param addedObjectInitialier
	 * @return
	 */
	protected <T> IMenuContributionSpecification actionAdd(String text, EList<? super T> list, T value,
			IAcceptor<T> addedObjectInitialier) {
		AddCommand addCommand = addCommand(list, value);
		addCommand.setDescription(text);
		return new MenuActionContributionSpecification(
				new EmfCommandAction<T>(text, getEditingDomain(), addCommand, addedObjectInitialier));
	}

	/**
	 * Creates a command for adding the specified value to the specified list.
	 * 
	 * @param list
	 * @param value
	 * @return
	 */
	protected <T> AddCommand addCommand(EList<? super T> list, T value) {
		return new CustomAddCommand<T>(getEditingDomain(), list, value);
	}

	/**
	 * Creates a menu contribution for an action with the given text, that will
	 * modify the model, when executed, by running the passed lambda
	 * changeImplementation; the specified element will be passed to the lambda
	 * and represents the model's element that will be changed.
	 * 
	 * All modifications that concern such element will be recorded so that
	 * undo/redo will work. Important: only the changes concerning the element
	 * will be recorded: if during the modification you also change other
	 * elements in the model, such changes will not be tracked. If you want to
	 * track also those changes, you need to make sure to specify a container of
	 * the element (you can even specify its Resource to keep track of all the
	 * changes in the object's resource). However, recall that the element you
	 * specify is the argument passed to the lamba.
	 * 
	 * @param text
	 * @param element
	 * @param changeImplementation
	 * @return
	 */
	protected <T extends Notifier> IMenuContributionSpecification actionChange(String text, T element,
			IAcceptor<T> changeImplementation) {
		ChangeCommand changeCommand = changeCommand(element, changeImplementation);
		changeCommand.setDescription(text);
		return new MenuActionContributionSpecification(
				new EmfCommandAction<T>(text, getEditingDomain(), changeCommand));
	}

	/**
	 * Creates a command for modifying the element, tracking all the changes
	 * that concern such element in order to implement undo/redo.
	 * 
	 * @param element
	 * @param changeImplementation
	 * @return
	 */
	protected <T extends Notifier> ChangeCommand changeCommand(final T element,
			final IAcceptor<T> changeImplementation) {
		return new CustomChangeCommand<T>(element, changeImplementation);
	}

	/**
	 * Creates the standard EMF "new child" action contributions
	 * 
	 * @param selection
	 * @return
	 */
	public List<IMenuContributionSpecification> createChildActions(ISelection selection) {
		Collection<?> descriptors = getDescriptors(selection, false);
		List<IMenuContributionSpecification> actions = new ArrayList<IMenuContributionSpecification>();

		for (Object descriptor : descriptors) {
			CreateChildAction act = new CreateChildAction(getEditingDomain(), selection, descriptor);
			Object imageObj = labelProvider.getImage(((CommandParameter) descriptor).getValue());
			act.setImageDescriptor(ImageDescriptor.createFromImage((Image) imageObj));
			actions.add(new MenuActionContributionSpecification(act));
		}

		return actions;
	}

	/**
	 * Creates the standard EMF "new sibling" action contributions
	 * 
	 * @param selection
	 * @return
	 */
	public List<IMenuContributionSpecification> createSiblingActions(ISelection selection) {
		Collection<?> descriptors = getDescriptors(selection, true);
		List<IMenuContributionSpecification> actions = new ArrayList<IMenuContributionSpecification>();

		for (Object descriptor : descriptors) {
			CreateSiblingAction act = new CreateSiblingAction(getEditingDomain(), selection, descriptor);
			Object imageObj = labelProvider.getImage(((CommandParameter) descriptor).getValue());
			act.setImageDescriptor(ImageDescriptor.createFromImage((Image) imageObj));
			actions.add(new MenuActionContributionSpecification(act));
		}

		return actions;
	}

	protected Collection<?> getDescriptors(ISelection selection, boolean siblings) {
		final Object firstSelectedElement = selectionHelper.getFirstSelectedElement(selection);
		if (getEditingDomain() == null) {
			return Collections.emptyList();
		}
		if (siblings) {
			return getEditingDomain().getNewChildDescriptors(null, firstSelectedElement);
		}
		return getEditingDomain().getNewChildDescriptors(firstSelectedElement, null);
	}
}
