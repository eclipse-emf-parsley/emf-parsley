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
package org.eclipse.emf.parsley.dsl.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.parsley.dsl.model.Model;
import org.eclipse.emf.parsley.dsl.model.Module;
import org.eclipse.emf.parsley.dsl.model.PartSpecification;
import org.eclipse.emf.parsley.dsl.tests.inputs.TestInputs;
import org.eclipse.emf.parsley.dsl.tests.inputs.TestInputsWithErrors;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

@RunWith(XtextRunner.class)
@InjectWith(EmfParsleyDslInjectorProvider.class)
public abstract class EmfParsleyDslAbstractTest {

	@Inject
	protected TestInputs inputs;

	@Inject
	protected TestInputsWithErrors inputsWithErrors;

	@Inject
	private ParseHelper<Model> parseHelper;

	@Inject
	private ValidationTestHelper validationTestHelper;

	@BeforeClass
	public static void setCRLF() {
		System.setProperty("line.separator", "\n");
	}

	public Model assertNoErrorAfterParsing(CharSequence s) throws Exception {
		var ts = parseHelper.parse(s);
		validationTestHelper.assertNoErrors(ts);
		return ts;
	}

	public void parseAndAssertError(CharSequence s, EClass objectType, String code, String messagePart) throws Exception {
		validationTestHelper.assertError(parseHelper.parse(s), objectType, code, messagePart);
	}

	public Model parseModel(CharSequence s) throws Exception {
		return parseHelper.parse(s);
	}

	public void parseAndAssertErrors(CharSequence s) throws Exception {
		assertTrue(validationTestHelper.validate(parseHelper.parse(s)).size() > 0);
	}

	public Module module(CharSequence s) throws Exception {
		return assertNoErrorAfterParsing(s).getModule();
	}

	public void assertEqualsStrings(Object expected, Object actual) {
		assertEquals(
			("" + expected).replace("\r", ""),
			("" + actual).replace("\r", "")
		);
	}

	public PartSpecification partSpecification(CharSequence s) throws Exception {
		return module(s).getPartsSpecifications().getParts().get(0);
	}
}
