/*******************************************************************************
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests

import org.eclipse.emf.common.util.Diagnostic
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.eclipse.emf.parsley.validation.DiagnosticUtil
import org.eclipse.emf.parsley.validation.ValidationRunner
import org.junit.Rule
import org.junit.Test

import static org.junit.Assert.*
import com.google.inject.Inject
import org.junit.Before
import org.eclipse.emf.parsley.tests.models.testmodels.TestContainer

class DiagnosticUtilTest extends AbstractEmfParsleyTest {
	
	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	@Inject
	var DiagnosticUtil diagnosticUtil

	@Inject
	var ValidationRunner validationRunner

	var TestContainer testContainer

	@Before
	def void setupDiagnostic() {
		getOrCreateInjector.injectMembers(this)
		testContainer = testFactory.createTestContainer => [
			objectsForValidation += testFactory.createClassForValidation // this will issue an error
			objectsForValidation += testFactory.createClassForValidation => [
				notEmpty = "a" // this will issue a warning
			]
		]
	}

	@Test
	def void testFilterErrors() {
		val diagnostic = validationRunner.validate(testContainer)
		assertEquals(2, diagnosticUtil.flatten(diagnostic).size)
		val errors = diagnosticUtil.errors(diagnostic)
		assertEquals(1, errors.size)
		assertTrue(errors.head.severity == Diagnostic.ERROR)
	}

	@Test
	def void testFormat() {
		val diagnostic = validationRunner.validate(testContainer)
		val errors = diagnosticUtil.errors(diagnostic)
		assertEquals("ERROR: the field 'notEmpty' cannot be empty", diagnosticUtil.format(errors.head))
	}

}