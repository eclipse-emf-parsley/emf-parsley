/**
 * 
 */
package org.eclipse.emf.parsley.dsl.tests;

import org.eclipse.emf.parsley.dsl.tests.EmfComponentsDslGeneratorTests;
import org.eclipse.emf.parsley.dsl.tests.EmfComponentsDslJvmModelInferrerTests;
import org.eclipse.emf.parsley.dsl.tests.EmfComponentsDslParserTests;
import org.eclipse.emf.parsley.dsl.tests.EmfComponentsDslPluginXmlGeneratorTests;
import org.eclipse.emf.parsley.dsl.tests.EmfComponentsDslValidatorTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Lorenzo Bettini
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	EClassTests.class,
	EmfComponentsDslParserTests.class,
	EmfParsleyDslScopingTests.class,
	EmfComponentsDslValidatorTests.class,
	EmfComponentsDslJvmModelInferrerTests.class,
	EmfComponentsDslPluginXmlGeneratorTests.class,
	EmfComponentsDslGeneratorTests.class,
	EmfParsleyDslOrganizeImportsTest.class
})
public class EmfComponentsDslAllTests {

}
