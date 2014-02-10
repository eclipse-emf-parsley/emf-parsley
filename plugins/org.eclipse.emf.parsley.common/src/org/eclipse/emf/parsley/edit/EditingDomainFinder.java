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
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * Retrieves the EditingDomain for an EObject. The default implementation simply
 * uses {@link AdapterFactoryEditingDomain} getEditingDomainFor.
 * 
 * @author Lorenzo Bettini
 * 
 */
public class EditingDomainFinder {

	public EditingDomain getEditingDomainFor(EObject object) {
		return AdapterFactoryEditingDomain.getEditingDomainFor(object);
	}
}
