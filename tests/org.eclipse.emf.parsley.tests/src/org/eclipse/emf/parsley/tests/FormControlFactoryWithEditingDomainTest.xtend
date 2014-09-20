/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests

import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
import org.eclipse.emf.edit.domain.EditingDomain
import org.junit.Before

class FormControlFactoryWithEditingDomainTest extends FormControlFactoryTest {
	
	var EditingDomain editingDomain = null
	
	@Before
	def void setEditingDomainToNull() {
		editingDomain = null
	}
	
	override protected getEditingDomain() {
		if (editingDomain === null) {
			editingDomain = getOrCreateInjector.getProvider(AdapterFactoryEditingDomain).get()
		}
		return editingDomain
	}

	override protected createResourceSet() {
		getEditingDomain.resourceSet
	}
	
}