/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.tests

import com.google.inject.Inject
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.parsley.dsl.model.Model
import org.eclipse.emf.parsley.dsl.tests.inputs.TestInputs
import org.eclipse.emf.parsley.dsl.tests.inputs.TestInputsWithErrors
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.BeforeClass
import org.junit.runner.RunWith

import static extension org.junit.Assert.*

@RunWith(XtextRunner)
@InjectWith(EmfParsleyDslInjectorProvider)
abstract class EmfParsleyDslAbstractTest {

	@Inject
	protected TestInputs inputs

	@Inject
	protected TestInputsWithErrors inputsWithErrors

	@Inject extension ParseHelper<Model>

	@Inject extension ValidationTestHelper

	@BeforeClass
	def static void setCRLF() {
		System::setProperty("line.separator", "\n")
	}

	def parseAndAssertNoError(CharSequence s) {
		var ts = s.parse
		ts.assertNoErrors
		ts
	}

	def parseAndAssertError(CharSequence s, EClass objectType, String code, String messagePart) {
		s.parse.assertError(objectType, code, messagePart)
	}

	def parseModel(CharSequence s) {
		s.parse
	}

	def parseAndAssertErrors(CharSequence s) {
		(s.parse.validate.size > 0).assertTrue
	}

	def module(CharSequence s) {
		s.parseAndAssertNoError.module
	}

	def assertEqualsStrings(Object expected, Object actual) {
		assertEquals(
			("" + expected).replace("\r", ""),
			("" + actual).replace("\r", "")
		)
	}

	def partSpecification(CharSequence s) {
		s.module.partsSpecifications.parts.head
	}
}
