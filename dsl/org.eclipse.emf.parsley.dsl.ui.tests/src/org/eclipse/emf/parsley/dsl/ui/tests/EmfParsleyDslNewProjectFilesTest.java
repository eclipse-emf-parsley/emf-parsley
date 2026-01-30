package org.eclipse.emf.parsley.dsl.ui.tests;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.parsley.dsl.ui.wizard.EmfParsleyDslNewProjectFiles;
import org.junit.Test;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 */
public class EmfParsleyDslNewProjectFilesTest {

	private final EmfParsleyDslNewProjectFiles generator = new EmfParsleyDslNewProjectFiles();

	@Test
	public void testDslModuleWithoutAdditionalContents() {
		assertGeneratedContentEqualsTo(
			generator.genEmptyDslModule("testproject"),
			"""
			/* testproject EMF Parsley Dsl Module file */
			module testproject {
			\t
			}
			"""
		);
	}

	@Test
	public void testDslModuleWithFQN() {
		assertGeneratedContentEqualsTo(
			generator.genEmptyDslModule("mypackage.testproject"),
			"""
			/* mypackage.testproject EMF Parsley Dsl Module file */
			module mypackage.testproject {
			\t
			}
			"""
		);
	}

	@Test
	public void testDslModuleWithView() {
		assertGeneratedContentEqualsTo(
			generator.genDslModuleWithViewPart("mypackage.testproject", "my.views.MyView", ""),
			"""
			import my.views.MyView

			/* mypackage.testproject EMF Parsley Dsl Module file */
			module mypackage.testproject {
			\t
				parts {
					viewpart mypackage.testproject {
						viewname "Testproject"
						viewclass MyView
					}
				}
			\t
			}
			"""
		);
	}

	@Test
	public void testGenConfigurator() {
		assertGeneratedContentEqualsTo(
			generator.genConfigurator(
				generator.genResourceURI("MyClass") +
				"\n" +
				generator.genEClass("MyClass")
			),
			"""
			configurator {
				resourceURI {
					MyClass -> {
						// TODO create and return a org.eclipse.emf.common.util.URI
						return null;
					}
				}
			\t
				eClass {
					MyClass -> {
						// TODO return the EClass of objects to be shown
					}
				}
			}
			"""
		);
	}

	@Test
	public void testGenResourceManager() {
		assertGeneratedContentEqualsTo(
			generator.genResourceManager(
				generator.genInitializeResource()
			),
			"""
			resourceManager {
				initializeResource {
					// Optional: initialize an empty Resource
					// 'it' is of type Resource
					// e.g., it.getContents += myFactory.createMyClass
				}
			}
			"""
		);
	}

	@Test
	public void testViewGen() {
		assertGeneratedContentEqualsTo(
			generator.genViewClass("mypackage.testproject", "MyClass", "my.views.MyBaseClass"),
			"""
			package mypackage.testproject;

			public class MyClass extends my.views.MyBaseClass {
			\t
			}
			"""
		);
	}

	private void assertGeneratedContentEqualsTo(CharSequence actual, CharSequence expected) {
		assertEquals(expected.toString(), actual.toString());
	}
}
