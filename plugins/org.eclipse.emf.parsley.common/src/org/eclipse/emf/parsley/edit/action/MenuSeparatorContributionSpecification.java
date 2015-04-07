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

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * The implementation wrapping a {@link Separator}
 * 
 * @author Lorenzo Bettini - initial contribution and API
 *
 */
public class MenuSeparatorContributionSpecification implements IMenuContributionSpecification {

	public MenuSeparatorContributionSpecification() {
	}

	@Override
	public void updateSelection(IStructuredSelection selection) {
		// nothing to update for a standard Action
	}

	@Override
	public IContributionItem getContributionItem() {
		return new Separator();
	}

}
