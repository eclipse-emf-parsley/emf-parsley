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
package org.eclipse.emf.parsley.edit.actionbar;


import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.ControlAction;
import org.eclipse.emf.parsley.edit.action.EditingActionManager;
import org.eclipse.emf.parsley.edit.action.EditingDomainValidateAction;
import org.eclipse.emf.parsley.edit.action.EmfActionManager;
import org.eclipse.emf.parsley.util.ActionBarsUtils;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;

import com.google.inject.Inject;

/**
 * This is a contributor for an editor, multi-page or otherwise, that implements
 * {@link IEditingDomainProvider}. It automatically hooks up the Undo, Redo,
 * Cut, Copy, Paste, and Delete actions on the Edit menu to the corresponding
 * commands supported by the {@link org.eclipse.emf.edit.domain.EditingDomain}.
 * The editor site'selection provider is used to keep the Cut, Copy, Paste, and
 * Delete actions up-to-date. The actions are also refreshed every time the
 * editor fires to its {@link IPropertyListener}s.
 * <p>
 * Another very useful feature of this contributor is that it can be used as
 * follows:
 * 
 * <pre>
 * ((IMenuListener) ((IEditorSite) getSite()).getActionBarContributor())
 * 		.menuAboutToShow(menuManager);
 * </pre>
 * 
 * to contribute the Edit menu actions to a pop-up menu.
 */
public class WorkbenchActionBarContributor extends
		MultiPageEditorActionBarContributor implements IMenuListener,
		IPropertyListener, ISelectionChangedListener {

	protected IWorkbenchPart activePart;

	protected EditingDomainValidateAction validateAction;

	protected ControlAction controlAction;

	public static final int ADDITIONS_LAST_STYLE = 0x1;

	protected int style;

	@Inject
	protected EmfActionManager emfActionManager;
	
	@Inject
	protected EditingActionManager editingActionManager;

	protected ISelectionProvider explicitSelectionProvider = null;

	public WorkbenchActionBarContributor() {
		this(ADDITIONS_LAST_STYLE);
	}
	
	public WorkbenchActionBarContributor(int style) {
		super();
		this.style = style;
	}

	public void setExplicitSelectionProvider(
			ISelectionProvider explicitSelectionProvider) {
		this.explicitSelectionProvider = explicitSelectionProvider;
	}

	@Override
	public void init(IActionBars actionBars) {
		super.init(actionBars);
		initializeActions(actionBars);
	}

	protected void initializeActions(IActionBars actionBars) {
		editingActionManager.initializeActions(actionBars);
		validateAction = editingActionManager.createValidateAction();
		controlAction = editingActionManager.createControlAction();
	}

	@Override
	public void contributeToMenu(IMenuManager menuManager) {
		IMenuManager submenuManager = new MenuManager("Emf Parsley",
				"org.eclipse.emf.parsley.MenuID");

		menuManager.insertAfter("additions", submenuManager);
		submenuManager.add(new Separator("settings"));
		submenuManager.add(new Separator("actions"));
		submenuManager.add(new Separator("additions"));
		submenuManager.add(new Separator("additions-end"));

		emfActionManager.contributeToMenu(submenuManager);

		submenuManager.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager menuManager) {
				menuManager.updateAll(true);
			}
		});

		addGlobalActions(submenuManager);
	}

	@Override
	public void contributeToStatusLine(IStatusLineManager statusLineManager) {
		super.contributeToStatusLine(statusLineManager);
	}

	@Override
	public void contributeToToolBar(IToolBarManager toolBarManager) {
		toolBarManager.add(new Separator("ecore-settings"));
		toolBarManager.add(new Separator("ecore-additions"));
	}

	public void shareGlobalActions(IPage page, IActionBars actionBars) {
		editingActionManager.shareGlobalActions(page, actionBars);
	}

	public IEditorPart getActiveEditor() {
		if (activePart instanceof IEditorPart) {
			return (IEditorPart) activePart;
		}
		return null;
	}

	@Override
	public void setActiveEditor(IEditorPart part) {
		super.setActiveEditor(part);

		setActivePart(part);
	}

	public void setActivePart(IWorkbenchPart part) {
		if (part != activePart) {
			if (activePart != null) {
				deactivate();
			}

			if (part instanceof IEditingDomainProvider) {
				activePart = part;
				activate();

			}
		}
		activePart = part;
	}

	@Override
	public void setActivePage(IEditorPart part) {
		// Do nothing
	}

	public void deactivate() {
		activePart.removePropertyListener(this);

		ISelectionProvider selectionProvider = retrieveSelectionProvider();

		if (selectionProvider != null) {
			editingActionManager
					.removeSelectionChangeListener(selectionProvider);
			if (controlAction != null) {
				selectionProvider.removeSelectionChangedListener(controlAction);
			}
			if (validateAction != null) {
				selectionProvider
						.removeSelectionChangedListener(validateAction);
			}
		}
	}

	public void activate() {
		activePart.addPropertyListener(this);

		ensureActionsAreInitialized();

		if (activePart instanceof IEditingDomainProvider) {
			EditingDomain editingDomain = ((IEditingDomainProvider) activePart)
					.getEditingDomain();
			editingActionManager.setEditingDomain(editingDomain);

			if (controlAction != null) {
				controlAction.setEditingDomain(editingDomain);
			}
			if (validateAction != null) {
				validateAction.setEditingDomain(editingDomain);
			}
		}

		ISelectionProvider selectionProvider = retrieveSelectionProvider();

		if (selectionProvider != null) {

			editingActionManager.addSelectionListener(selectionProvider);

			if (controlAction != null) {
				selectionProvider.addSelectionChangedListener(controlAction);
			}

			if (validateAction != null) {
				selectionProvider.addSelectionChangedListener(validateAction);
			}
		}
		update();
	}

	protected ISelectionProvider retrieveSelectionProvider() {
		if (explicitSelectionProvider != null)
			return explicitSelectionProvider;

		return activePart instanceof ISelectionProvider ? (ISelectionProvider) activePart
				: activePart.getSite().getSelectionProvider();
	}

	public void update() {
		ISelectionProvider selectionProvider = retrieveSelectionProvider();

		if (selectionProvider != null) {
			ISelection selection = selectionProvider.getSelection();
			IStructuredSelection structuredSelection = selection instanceof IStructuredSelection ? (IStructuredSelection) selection
					: StructuredSelection.EMPTY;

			editingActionManager.updateSelection(structuredSelection);
			if (controlAction != null) {
				controlAction.updateSelection(structuredSelection);
			}

			if (validateAction != null) {
				validateAction.updateSelection(structuredSelection);
			}
		}
		editingActionManager.updateUndoRedo();

	}

	public void menuAboutToShow(IMenuManager menuManager) {
		if ((style & ADDITIONS_LAST_STYLE) == 0) {
			menuManager.add(new Separator("additions"));
		}
		menuManager.add(new Separator("edit"));

		ensureActionsAreInitialized();
		editingActionManager.menuAboutToShow(menuManager);

		if ((style & ADDITIONS_LAST_STYLE) != 0) {
			menuManager.add(new Separator("additions"));
			menuManager.add(new Separator());
		}
		menuManager.add(new Separator("additions-end"));

		addGlobalActions(menuManager);
		
		emfActionManager.menuAboutToShow(menuManager);
	}

	protected void ensureActionsAreInitialized() {
		if (editingActionManager.getUndoAction() != null)
			return;
		initializeActions(ActionBarsUtils.getActionBars(activePart));
	}

	protected void addGlobalActions(IMenuManager menuManager) {
		menuManager.insertAfter("additions-end", new Separator("ui-actions"));
		
		String key = (style & ADDITIONS_LAST_STYLE) == 0 ? "additions-end"
				: "additions";
		if (validateAction != null) {
			menuManager.insertBefore(key, new ActionContributionItem(
					validateAction));
		}

		if (controlAction != null) {
			menuManager.insertBefore(key, new ActionContributionItem(
					controlAction));
		}

		if (validateAction != null || controlAction != null) {
			menuManager.insertBefore(key, new Separator());
		}
	}

	public void propertyChanged(Object source, int id) {
		update();
	}

	public void selectionChanged(SelectionChangedEvent event) {
		EditingDomain domain = ((IEditingDomainProvider) activePart)
				.getEditingDomain();
		emfActionManager.updateSelection(event.getSelection(), domain);
	}

}
