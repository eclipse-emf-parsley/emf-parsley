/**
 * 
 */
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
	EmfParsleyDslParserTests.class,
	EmfParsleyDslScopingTests.class,
	EmfParsleyDslValidatorTests.class,
	EmfParsleyDslJvmModelInferrerTests.class,
	EmfParsleyDslPluginXmlGeneratorTests.class,
	EmfParsleyDslGeneratorTests.class,
	EmfParsleyDslOrganizeImportsTest.class
})
public class EmfParsleyDslAllTests {

}
