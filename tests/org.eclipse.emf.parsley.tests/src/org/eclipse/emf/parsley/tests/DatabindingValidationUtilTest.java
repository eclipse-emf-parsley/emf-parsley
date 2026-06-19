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
package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.parsley.internal.databinding.DatabindingValidationUtil;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForDefaultValidation;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForValidation;
import org.eclipse.emf.parsley.tests.models.testmodels.TestContainer;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.validation.DiagnosticUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.inject.Inject;

public class DatabindingValidationUtilTest extends AbstractEmfParsleyTest {

	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	@Inject
	private DatabindingValidationUtil fixture;

	/**
	 * Used only for formatting errors
	 */
	@Inject
	private DiagnosticUtil diagnosticUtil;

	@Before
	public void setupUtil() {
		getOrCreateInjector().injectMembers(this);
	}

	@Test
	public void testDiagnosticForObjectAndFeature() {
		ClassForDefaultValidation objectForValidation = fixtures.getTestFactory().createClassForDefaultValidation();
		assertDiagnostic(
				fixture.getDiagnostic(objectForValidation,
						fixtures.getTestPackage().getClassForDefaultValidation_NotEmpty()),
				"ERROR: The required feature 'Not Empty' of 'Class For Default Validation' must be set");
	}

	@Test
	public void testDiagnosticForObjectAndFeatureForDifferentFeature() {
		ClassForDefaultValidation objectForValidation = fixtures.getTestFactory().createClassForDefaultValidation();
		assertDiagnostic(
				fixture.getDiagnostic(objectForValidation,
						fixtures.getTestPackage().getClassForDefaultValidation_CanBeEmpty()),
				"");
	}

	@Test
	public void testDiagnosticForObjectAndFeatureForSeveralObjects() {
		TestContainer container = fixtures.getTestFactory().createTestContainer();
		// this will issue an error
		container.getObjectsForDefaultValidation().add(fixtures.getTestFactory().createClassForDefaultValidation());
		ClassForDefaultValidation validObject = fixtures.getTestFactory().createClassForDefaultValidation();
		validObject.setNotEmpty("foo");
		container.getObjectsForDefaultValidation().add(validObject);

		assertDiagnostic(
				fixture.getDiagnostic(
						container.getObjectsForDefaultValidation()
								.get(container.getObjectsForDefaultValidation().size() - 1),
						fixtures.getTestPackage().getClassForDefaultValidation_NotEmpty()),
				"");
		assertDiagnostic(
				fixture.getDiagnostic(container.getObjectsForDefaultValidation().get(0),
						fixtures.getTestPackage().getClassForDefaultValidation_NotEmpty()),
				"ERROR: The required feature 'Not Empty' of 'Class For Default Validation' must be set");
	}

	@Test
	public void testDiagnosticForObjectAndFeatureWithCustomValidator() {
		ClassForValidation objectForValidation = fixtures.getTestFactory().createClassForValidation();
		assertDiagnostic(
				fixture.getDiagnostic(objectForValidation,
						fixtures.getTestPackage().getClassForValidation_NotEmpty()),
				"ERROR: the field 'notEmpty' cannot be empty");
	}

	@Test
	public void testDiagnosticWarningForObjectAndFeatureWithCustomValidator() {
		ClassForValidation objectForValidation = fixtures.getTestFactory().createClassForValidation();
		objectForValidation.setNotEmpty("a");
		assertDiagnostic(
				fixture.getDiagnostic(objectForValidation,
						fixtures.getTestPackage().getClassForValidation_NotEmpty()),
				"WARNING: the field 'notEmpty' should be more than one character");
	}

	@Test
	public void testDiagnosticInfoForObjectAndFeatureWithCustomValidator() {
		ClassForValidation objectForValidation = fixtures.getTestFactory().createClassForValidation();
		objectForValidation.setNotEmpty("ab");
		assertDiagnostic(
				fixture.getDiagnostic(objectForValidation,
						fixtures.getTestPackage().getClassForValidation_NotEmpty()),
				"INFO: the field 'notEmpty' is two characters long");
	}

	private void assertDiagnostic(Iterable<Diagnostic> diagnostic, String expected) {
		assertEquals(expected, StreamSupport.stream(diagnostic.spliterator(), false)
				.map(d -> diagnosticUtil.format(d))
				.collect(Collectors.joining(System.getProperty("line.separator"))));
	}

}
