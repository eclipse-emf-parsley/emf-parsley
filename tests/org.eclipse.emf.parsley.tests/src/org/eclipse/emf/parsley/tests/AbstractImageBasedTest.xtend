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
import org.junit.Rule
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule

abstract class AbstractImageBasedTest extends AbstractEmfParsleyShellBasedTest {

	val public static TEST_IMAGE = "test_image.png"

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

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
