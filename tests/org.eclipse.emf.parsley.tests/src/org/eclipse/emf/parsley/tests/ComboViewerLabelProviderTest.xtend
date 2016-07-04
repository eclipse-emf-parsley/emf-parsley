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
import org.eclipse.emf.edit.command.SetCommand
import org.eclipse.emf.parsley.ui.provider.ComboViewerLabelProvider
import org.junit.Before
import org.junit.Test

import static extension org.junit.Assert.*

class ComboViewerLabelProviderTest extends AbstractImageBasedTest {

	@Inject
	var ComboViewerLabelProvider labelProvider

	@Before
	def void setupLabelProvider() {
		getOrCreateInjector.injectMembers(this)
	}

	@Test
	def void testDefaultGetText() {
		"Class For Controls".assertEquals(labelProvider.getText(classForControlsInstance))
	}

	@Test
	def void testGetTextWithElementForSettingReferenceToNull() {
		"".assertEquals(labelProvider.getText(SetCommand.UNSET_VALUE))
	}

}
