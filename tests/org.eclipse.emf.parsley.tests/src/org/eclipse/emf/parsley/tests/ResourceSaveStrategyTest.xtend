package org.eclipse.emf.parsley.tests

import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest
import org.eclipse.emf.parsley.junit4.util.LogAppenderTestRule
import org.eclipse.emf.parsley.resource.ResourceSaveStrategy
import org.eclipse.emf.parsley.resource.ValidateBeforeSaveStrategy
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsFactory
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.eclipse.emf.parsley.tests.util.LogIssueReporterForTests
import org.eclipse.emf.parsley.validation.LogIssueReporter
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*

class ResourceSaveStrategyTest extends AbstractEmfParsleyTest {
	
	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()
	
	@Rule public val LogAppenderTestRule logAppender = new LogAppenderTestRule(LogIssueReporter);

	var ResourceSaveStrategy resourceSaveStrategy;

	@Before
	def void setupResourceLoader() {
		// note that members must be explicitly injected in the tests
		resourceSaveStrategy = new ResourceSaveStrategy
	}

	@Test
	def void testDefaultSaveStrategy() {
		getOrCreateInjector.injectMembers(resourceSaveStrategy)
		var resource = createTestResource
		resource.contents.add(
					TestmodelsFactory.eINSTANCE.createClassWithName());
		resourceSaveStrategy.save(resource).assertTrue
		// now reload it and check that it was effectively saved
		resource = loadTestResource
		assertEquals(1, resource.contents.size)
	}

	@Test
	def void testValidateBeforeSaveStrategyOnResourceWithError() {
		resourceSaveStrategy = createValidateBeforeSaveResourceStrategy
		var resource = createTestResource
		resourceSaveStrategy.save(resource).assertTrue
		resource.contents.add(
			TestmodelsFactory.eINSTANCE.createClassForValidation
		);
		resourceSaveStrategy.save(resource).assertFalse
		// now reload it and check that it was not saved
		resource = loadTestResource
		assertEquals(0, resource.contents.size)
		logAppender.assertContainsMessage("ERROR: the field 'notEmpty' cannot be empty")
	}

	@Test
	def void testValidateBeforeSaveStrategyOnResourceWithWarning() {
		resourceSaveStrategy = createValidateBeforeSaveResourceStrategy
		var resource = createTestResource
		resourceSaveStrategy.save(resource).assertTrue
		resource.contents.add(
			TestmodelsFactory.eINSTANCE.createClassForValidation => [
				notEmpty = "a" // this will only issue a warning
			]
		);
		resourceSaveStrategy.save(resource).assertTrue
		// now reload it and check that it was saved
		resource = loadTestResource
		assertEquals(1, resource.contents.size)
		logAppender.assertContainsMessage("WARNING: the field 'notEmpty' should be more than one character")
	}

	def private createValidateBeforeSaveResourceStrategy() {
		createInjector(new EmfParsleyGuiceModuleForTesting() {
			
			override bindResourceSaveStrategy() {
				ValidateBeforeSaveStrategy
			}
			
			override bindIssueReporter() {
				LogIssueReporterForTests
			}
			
		}).getInstance(ResourceSaveStrategy)
	}
}