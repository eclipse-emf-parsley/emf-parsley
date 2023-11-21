/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Lorenzo Bettini - initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.edit.action;

import java.util.List;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * The implementation wrapping a submenu
 *
 * @author Lorenzo Bettini - initial contribution and API
 *
 */
public class MenuSubmenuSpecification implements IMenuContributionSpecification {

	private String text;

	private List<IMenuContributionSpecification> subContributionSpecifications;

	public MenuSubmenuSpecification(String text, List<IMenuContributionSpecification> subContributionSpecifications) {
		this.text = text;
		this.subContributionSpecifications = subContributionSpecifications;
	}

	@Override
	public void updateSelection(IStructuredSelection selection) {
		for (IMenuContributionSpecification m : subContributionSpecifications) {
			m.updateSelection(selection);
		}
	}

	@Override
	public IContributionItem getContributionItem() {
		MenuManager menuManager =  new MenuManager(text);
		for (IMenuContributionSpecification m : subContributionSpecifications) {
			menuManager.add(m.getContributionItem());
		}
		return menuManager;
	}

}
