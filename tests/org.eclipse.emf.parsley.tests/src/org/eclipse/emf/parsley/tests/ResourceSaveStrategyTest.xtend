package org.eclipse.emf.parsley.tests

import org.eclipse.emf.common.util.URI
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest
import org.eclipse.emf.parsley.resource.ResourceSaveStrategy
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsFactory
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*

class ResourceSaveStrategyTest extends AbstractEmfParsleyTest {
	
	@Rule public extension EmfParsleyFixturesTestRule fixtures = new EmfParsleyFixturesTestRule()

	var ResourceSaveStrategy resourceSaveStrategy;

	val static TEST_RESOURCE_URI = "resources/TestResource.xmi"

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

	def private createTestResource() {
		val resourceSet = createAndSetupResourceSet
		val resource = resourceSet.createResource(URI.createURI(TEST_RESOURCE_URI))
		return resource
	}

	def private loadTestResource() {
		val resourceSet = createAndSetupResourceSet
		val resource = resourceSet.getResource(URI.createURI(TEST_RESOURCE_URI), true)
		return resource
	}
}