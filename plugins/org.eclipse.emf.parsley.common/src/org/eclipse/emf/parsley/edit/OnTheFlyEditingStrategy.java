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

package org.eclipse.emf.parsley.edit;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.google.inject.Inject;

/**
 * With this strategy the edited {@link EObject} is the original
 * object itself, thus every update will be immediate.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class OnTheFlyEditingStrategy implements IEditingStrategy {
	
	@Inject
	private EditingDomainFinder editingDomainFinder;

	@Override
	public void prepare(EObject original) {
		// nothing to prepare
	}

	@Override
	public void update(EObject edited) {
		// Nothing, it's already updated
	}

	@Override
	public EditingDomain getEditingDomain(EObject edited) {
		return editingDomainFinder.getEditingDomainFor(edited);
	}

	@Override
	public void rollback(EObject edited) {
		// we don't rollback: it's already updated
	}

}
