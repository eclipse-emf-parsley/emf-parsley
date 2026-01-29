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
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForValidation;
import org.eclipse.emf.parsley.tests.models.testmodels.TestContainer;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.validation.DiagnosticUtil;
import org.eclipse.emf.parsley.validation.ValidationRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.inject.Inject;

public class DiagnosticUtilTest extends AbstractEmfParsleyTest {

	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	@Inject
	private DiagnosticUtil diagnosticUtil;

	@Inject
	private ValidationRunner validationRunner;

	private TestContainer testContainer;

	@Before
	public void setupDiagnostic() {
		getOrCreateInjector().injectMembers(this);
		
		testContainer = fixtures.getTestFactory().createTestContainer();
		
		// this will issue an error
		ClassForValidation classForValidation1 = fixtures.getTestFactory().createClassForValidation();
		testContainer.getObjectsForValidation().add(classForValidation1);
		
		// this will issue a warning
		ClassForValidation classForValidation2 = fixtures.getTestFactory().createClassForValidation();
		classForValidation2.setNotEmpty("a");
		testContainer.getObjectsForValidation().add(classForValidation2);
	}

	@Test
	public void testFilterErrors() {
		Diagnostic diagnostic = validationRunner.validate(testContainer);
		assertEquals(2, diagnosticUtil.flatten(diagnostic).size());
		Iterable<Diagnostic> errors = diagnosticUtil.errors(diagnostic);
		int count = 0;
		for (Diagnostic error : errors) {
			assertTrue(error.getSeverity() == Diagnostic.ERROR);
			count++;
		}
		assertEquals(1, count);
	}

	@Test
	public void testFormat() {
		Diagnostic diagnostic = validationRunner.validate(testContainer);
		Iterable<Diagnostic> errors = diagnosticUtil.errors(diagnostic);
		assertEquals("ERROR: the field 'notEmpty' cannot be empty", diagnosticUtil.format(errors.iterator().next()));
	}
}
