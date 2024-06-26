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

import com.google.inject.Inject
import org.eclipse.emf.common.util.Diagnostic
import org.eclipse.emf.parsley.internal.databinding.DatabindingValidationUtil
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.eclipse.emf.parsley.validation.DiagnosticUtil
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*

class DatabindingValidationUtilTest extends AbstractEmfParsleyTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	@Inject
	var DatabindingValidationUtil fixture

	/**
	 * Used only for formatting errors
	 */
	@Inject
	var DiagnosticUtil diagnosticUtil

	@Before
	def void setupUtil() {
		getOrCreateInjector.injectMembers(this)
	}

	@Test
	def void testDiagnosticForObjectAndFeature() {
		val objectForValidation = testFactory.createClassForDefaultValidation
		fixture.getDiagnostic(objectForValidation, testPackage.classForDefaultValidation_NotEmpty).
			assertDiagnostic('''ERROR: The required feature 'Not Empty' of 'Class For Default Validation' must be set''')
	}

	@Test
	def void testDiagnosticForObjectAndFeatureForDifferentFeature() {
		val objectForValidation = testFactory.createClassForDefaultValidation
		fixture.getDiagnostic(objectForValidation, testPackage.classForDefaultValidation_CanBeEmpty).
			assertDiagnostic("")
	}

	@Test
	def void testDiagnosticForObjectAndFeatureForSeveralObjects() {
		val container = testFactory.createTestContainer => [
			objectsForDefaultValidation += testFactory.createClassForDefaultValidation // this will issue an error
			objectsForDefaultValidation += testFactory.createClassForDefaultValidation => [
				notEmpty = "foo"
			]
		]
		fixture.getDiagnostic(container.objectsForDefaultValidation.lastOrNull,
			testPackage.classForDefaultValidation_NotEmpty).assertDiagnostic("")
		fixture.getDiagnostic(container.objectsForDefaultValidation.head,
			testPackage.classForDefaultValidation_NotEmpty).
			assertDiagnostic('''ERROR: The required feature 'Not Empty' of 'Class For Default Validation' must be set''')
	}

	@Test
	def void testDiagnosticForObjectAndFeatureWithCustomValidator() {
		val objectForValidation = testFactory.createClassForValidation
		fixture.getDiagnostic(objectForValidation, testPackage.classForValidation_NotEmpty).
			assertDiagnostic('''ERROR: the field 'notEmpty' cannot be empty''')
	}

	@Test
	def void testDiagnosticWarningForObjectAndFeatureWithCustomValidator() {
		val objectForValidation = testFactory.createClassForValidation => [
			notEmpty = "a"
		]
		fixture.getDiagnostic(objectForValidation, testPackage.classForValidation_NotEmpty).
			assertDiagnostic('''WARNING: the field 'notEmpty' should be more than one character''')
	}

	@Test
	def void testDiagnosticInfoForObjectAndFeatureWithCustomValidator() {
		val objectForValidation = testFactory.createClassForValidation => [
			notEmpty = "ab"
		]
		fixture.getDiagnostic(objectForValidation, testPackage.classForValidation_NotEmpty).
			assertDiagnostic('''INFO: the field 'notEmpty' is two characters long''')
	}

	def private assertDiagnostic(Iterable<Diagnostic> diagnostic, CharSequence expected) {
		expected.toString.assertEquals(
			diagnostic.map[diagnosticUtil.format(it)].join(System.getProperty("line.separator"))
		)
	}

}
