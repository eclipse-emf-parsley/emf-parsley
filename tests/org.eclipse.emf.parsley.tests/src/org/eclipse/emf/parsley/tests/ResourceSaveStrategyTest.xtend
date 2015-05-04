package org.eclipse.emf.parsley.tests

import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest
import org.eclipse.emf.parsley.resource.ResourceSaveStrategy
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsFactory
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*

class ResourceSaveStrategyTest extends AbstractEmfParsleyTest {
	
	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

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

}