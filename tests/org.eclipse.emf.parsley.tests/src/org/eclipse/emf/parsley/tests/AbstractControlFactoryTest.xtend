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

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.parsley.inject.parameters.CompositeParameter
import org.eclipse.emf.parsley.inject.parameters.EObjectParameter
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyControlBasedTest
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.junit.Rule

abstract class AbstractControlFactoryTest extends AbstractEmfParsleyControlBasedTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	override protected getEditingDomain() {
		return fixtures.editingDomain
	}

	def protected createResourceSet() {
		return fixtures.createResourceSet
	}

	def protected getCompositeParameter() {
		return new CompositeParameter(shell)
	}

	def protected getEObjectParameter(EObject object) {
		return new EObjectParameter(object, editingDomain)
	}
}
