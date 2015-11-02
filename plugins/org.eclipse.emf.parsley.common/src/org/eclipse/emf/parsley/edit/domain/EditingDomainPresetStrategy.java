/*******************************************************************************
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.edit.domain;

/**
 * The editing domain is preset and never changed; it is assumed that the
 * editing domain is explicitly set after the creation of an instance of this
 * class.
 * 
 * @author Lorenzo Bettini - initial API and implementation
 *
 */
public class EditingDomainPresetStrategy extends EditingDomainFinderStrategy {

	@Override
	public void updateEditingDomain(Object object) {
		// no update, the editing domain is never changed once initialized
	}

}
