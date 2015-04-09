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

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.eclipse.emf.parsley.runtime.ui.IImageHelper
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesTestRule
import org.junit.Rule

abstract class AbstractImageBasedTest extends AbstractEmfParsleyShellBasedTest {

	val protected TEST_IMAGE = "test_image.png"
	
	@Rule public extension EmfParsleyFixturesTestRule fixtures = new EmfParsleyFixturesTestRule()

	def protected getDelegateLabelProvider() {
		getOrCreateInjector.getInstance(AdapterFactoryLabelProvider)
	}

	def protected getImageHelper() {
		getOrCreateInjector.getInstance(IImageHelper)
	}

	protected def loadTestImage() {
		imageHelper.getImage(TEST_IMAGE)
	}

}
