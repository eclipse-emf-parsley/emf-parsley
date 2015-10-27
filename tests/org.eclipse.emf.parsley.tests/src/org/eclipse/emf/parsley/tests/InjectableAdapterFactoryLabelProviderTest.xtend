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

import com.google.inject.Inject
import org.eclipse.emf.parsley.edit.ui.provider.InjectableAdapterFactoryLabelProvider
import org.junit.Before
import org.junit.Test

import static extension org.junit.Assert.*

class InjectableAdapterFactoryLabelProviderTest extends AbstractImageBasedTest {

	@Inject
	var InjectableAdapterFactoryLabelProvider labelProvider

	@Before
	def void setupLabelProvider() {
		getOrCreateInjector.injectMembers(this)
	}

	@Test
	def void testGetTextNull() {
		"".assertEquals(labelProvider.getText(null))
	}

	@Test
	def void testDefaultGetText() {
		"Class For Controls".assertEquals(labelProvider.getText(classForControlsInstance))
	}

	@Test
	def void testDefaultGetTextNotEObject() {
		"aString".assertEquals(labelProvider.getText("aString"))
	}

}
