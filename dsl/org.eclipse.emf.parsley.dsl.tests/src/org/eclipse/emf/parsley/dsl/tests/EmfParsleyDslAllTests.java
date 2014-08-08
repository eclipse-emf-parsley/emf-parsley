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

import org.eclipse.emf.parsley.dsl.tests.EmfParsleyDslGeneratorTests;
import org.eclipse.emf.parsley.dsl.tests.EmfParsleyDslJvmModelInferrerTests;
import org.eclipse.emf.parsley.dsl.tests.EmfParsleyDslParserTests;
import org.eclipse.emf.parsley.dsl.tests.EmfParsleyDslPluginXmlGeneratorTests;
import org.eclipse.emf.parsley.dsl.tests.EmfParsleyDslValidatorTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Lorenzo Bettini
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	EClassTests.class,
	EmfParsleyDslGeneratorUtilsTests.class,
	EmfParsleyDslParserTests.class,
	EmfParsleyDslSmokeTests.class,
	EmfParsleyDslScopingTests.class,
	EmfParsleyDslValidatorTests.class,
	EmfParsleyDslJvmModelInferrerTests.class,
	EmfParsleyDslPluginXmlGeneratorTests.class,
	EmfParsleyDslGeneratorTests.class,
	EmfParsleyDslOrganizeImportsTest.class
})
public class EmfParsleyDslAllTests {

}
