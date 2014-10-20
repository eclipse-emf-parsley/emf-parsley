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
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * An abstract specification of a menu item; mostly it will be a wrapper for an
 * {@link IContributionItem}.
 * 
 * @author Lorenzo Bettini - initial contribution and API
 *
 */
public interface IMenuContributionSpecification {

	/**
	 * This specification must update its selection using the passed parameter.
	 * 
	 * @param selection
	 */
	void updateSelection(IStructuredSelection selection);

	/**
	 * @return the wrapped {@link IContributionItem}
	 */
	IContributionItem getContributionItem();
}
