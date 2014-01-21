/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.edit.action;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.emf.edit.ui.action.CreateSiblingAction;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.SubContributionItem;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;

import com.google.inject.Inject;

public class EmfActionManager {

	protected Collection<IAction> createSiblingActions;

	protected IMenuManager createChildMenuManager;

	protected IMenuManager createSiblingMenuManager;

	protected Collection<IAction> createChildActions;

	@Inject
	protected ILabelProvider iLabelProvider;

	private void generateCreateChildActions(EditingDomain domain,
			Collection<?> descriptors, ISelection selection) {
		Collection<IAction> actions = new ArrayList<IAction>();
		if (descriptors != null) {
			for (Object descriptor : descriptors) {
				CreateChildAction act = new CreateChildAction(domain,
						selection, descriptor);
				Object imageObj = iLabelProvider
						.getImage(((CommandParameter) descriptor).getValue());
				act.setImageDescriptor(ImageDescriptor
						.createFromImage((Image) imageObj));
				actions.add(act);
			}
		}
		createChildActions = actions;
	}

	private void generateCreateSiblingActions(EditingDomain domain,
			Collection<?> descriptors, ISelection selection) {
		Collection<IAction> actions = new ArrayList<IAction>();
		if (descriptors != null) {
			for (Object descriptor : descriptors) {
				CreateSiblingAction act = new CreateSiblingAction(domain,
						selection, descriptor);
				Object imageObj = iLabelProvider
						.getImage(((CommandParameter) descriptor).getValue());
				act.setImageDescriptor(ImageDescriptor
						.createFromImage((Image) imageObj));
				actions.add(act);
			}
		}
		createSiblingActions = actions;
	}

	public void depopulateChildManager(IMenuManager menuManager) {
		depopulateManager(menuManager, createChildActions);
	}

	public void depopulateSibilingManager(IMenuManager menuManager) {
		depopulateManager(menuManager, createSiblingActions);
	}

	public Collection<IAction> getCreateSiblingActions() {
		return createSiblingActions;
	}

	public Collection<IAction> getCreateChildActions() {
		return createChildActions;
	}

	public void menuAboutToShow(IMenuManager menuManager) {
		MenuManager submenuManager = null;

		submenuManager = new MenuManager("&New Child");
		populateManager(submenuManager, createChildActions, null);
		menuManager.insertBefore("edit", submenuManager);

		submenuManager = new MenuManager("N&ew Sibling");
		populateManager(submenuManager, createSiblingActions, null);
		menuManager.insertBefore("edit", submenuManager);
	}

	public void populateChildMenuManager(IMenuManager menuManager) {
		populateManager(menuManager, createChildActions, null);
	}

	public void populateSibilingMenuManager(IMenuManager menuManager) {
		populateManager(menuManager, createSiblingActions, null);
	}

	public void contributeToMenu(IMenuManager submenuManager) {
		createChildMenuManager = new MenuManager("&New Child");
		submenuManager.insertBefore("additions", createChildMenuManager);

		createSiblingMenuManager = new MenuManager("N&ew Sibling");
		submenuManager.insertBefore("additions", createSiblingMenuManager);
	}

	public void updateSelection(ISelection selection, EditingDomain domain) {
		depopulateChildManager(createChildMenuManager);
		depopulateSibilingManager(createSiblingMenuManager);

		Collection<?> newChildDescriptors = null;
		Collection<?> newSiblingDescriptors = null;

		if (selection instanceof IStructuredSelection
				&& ((IStructuredSelection) selection).size() == 1) {
			Object object = ((IStructuredSelection) selection)
					.getFirstElement();

			newChildDescriptors = domain.getNewChildDescriptors(object, null);
			newSiblingDescriptors = domain.getNewChildDescriptors(null, object);
		}

		generateCreateChildActions(domain, newChildDescriptors, selection);
		generateCreateSiblingActions(domain, newSiblingDescriptors, selection);

		if (createChildMenuManager != null) {
			populateChildMenuManager(createChildMenuManager);
			createChildMenuManager.update(true);
		}
		if (createSiblingMenuManager != null) {
			populateSibilingMenuManager(createSiblingMenuManager);
			createSiblingMenuManager.update(true);
		}
	}
	
	protected void depopulateManager(IContributionManager manager,
			Collection<? extends IAction> actions) {
		if (manager != null && actions != null) {
			IContributionItem[] items = manager.getItems();
			for (int i = 0; i < items.length; i++) {
				// Look into SubContributionItems
				//
				IContributionItem contributionItem = items[i];
				while (contributionItem instanceof SubContributionItem) {
					contributionItem = ((SubContributionItem) contributionItem)
							.getInnerItem();
				}

				// Delete the ActionContributionItems with matching action.
				//
				if (contributionItem instanceof ActionContributionItem) {
					IAction action = ((ActionContributionItem) contributionItem)
							.getAction();
					if (actions.contains(action)) {
						manager.remove(contributionItem);
					}
				}
			}
		}
	}

	protected void populateManager(IContributionManager manager,
			Collection<? extends IAction> actions, String contributionID) {
		if (actions != null) {
			for (IAction action : actions) {
				if (contributionID != null) {
					manager.insertBefore(contributionID, action);
				} else {
					manager.add(action);
				}
			}
		}
	}
}
