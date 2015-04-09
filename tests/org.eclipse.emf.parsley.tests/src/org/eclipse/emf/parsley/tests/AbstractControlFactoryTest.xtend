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

import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyControlBasedTest
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesTestRule
import org.junit.Rule

abstract class AbstractControlFactoryTest extends AbstractEmfParsleyControlBasedTest {
	
	@Rule public extension EmfParsleyFixturesTestRule fixtures = new EmfParsleyFixturesTestRule()
	
	override protected getEditingDomain() {
		return fixtures.editingDomain
	}

	def protected createResourceSet() {
		return fixtures.createResourceSet
	}

}