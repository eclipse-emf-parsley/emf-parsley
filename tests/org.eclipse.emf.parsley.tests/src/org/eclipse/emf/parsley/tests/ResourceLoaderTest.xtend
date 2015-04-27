package org.eclipse.emf.parsley.tests

import com.google.inject.Injector
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest
import org.eclipse.emf.parsley.resource.ResourceLoader
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesTestRule
import org.eclipse.emf.parsley.tests.util.ModuleWithResourceInitializer
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static org.junit.Assert.*
import org.eclipse.emf.ecore.resource.Resource

class ResourceLoaderTest extends AbstractEmfParsleyTest {
	
	@Rule public extension EmfParsleyFixturesTestRule fixtures = new EmfParsleyFixturesTestRule()

	var TestableResourceLoader resourceLoader;

	val static TEST_URI = "http://dummy/My.testmodels"
	val static LOCAL_RESOURCE_URI = "resources/TestContainer.xmi"
	val static LOCAL_EMPTY_RESOURCE_URI = "resources/EmptyResource.xmi"

	/**
	 * Protected methods are public so that we can test them
	 */
	static class TestableResourceLoader extends ResourceLoader {
		
		override initializeEmptyResource(Resource resource) {
			super.initializeEmptyResource(resource)
		}
		
	}

	@Before
	def void setupResourceLoader() {
		// note that members must be explicitly injected in the tests
		resourceLoader = new TestableResourceLoader
	}

	@Test
	def void testLocalResource() {
		val injector = getOrCreateInjector
		injector.injectMembers(resourceLoader)
		val resource = resourceLoader.getResource(
			createAndSetupResourceSet,
			URI.createURI(LOCAL_RESOURCE_URI)
		)
		assertFalse("resource should not be empty", resource.contents.empty)
	}

	@Test
	def void testLocalEmptyResource() {
		val injector = getOrCreateInjector
		injector.injectMembers(resourceLoader)
		val resource = resourceLoader.getResource(
			createAndSetupResourceSet,
			URI.createURI(LOCAL_EMPTY_RESOURCE_URI)
		)
		assertTrue("resource should be empty", resource.contents.empty)
	}

	@Test
	def void testInitializeOnNullResource() {
		resourceLoader.initializeEmptyResource(null)
	}

	@Test
	def void testLoadingNonExistingResourceLeadsToCreatingWithEditingDomain() {
		val injector = createInjector(new EmfParsleyGuiceModuleForTesting)
		val response = getResourceFromLoader(injector)
		assertTrue("resource should be empty", response.resource.contents.empty)
	}
	
	@Test
	def void testEmptyResourceIsInitializedUsingResourceManager() {
		val injector = createInjector(new ModuleWithResourceInitializer)
		val response = getResourceFromLoader(injector)
		assertFalse("resource should not be empty", response.resource.contents.empty)
	}

	@Test
	def void testNonEmptyResourceIsNotInitialized() {
		val injector = createInjector(new ModuleWithResourceInitializer)
		val editingDomain = injector.createAdapterFactoryEditingDomain
		val response = getResourceFromLoader(injector, editingDomain)
		val el = response.resource.contents.head
		assertNotNull(el)
		val response2 = getResourceFromLoader(injector, editingDomain)
		val el2 = response2.resource.contents.head
		assertSame(el, el2)
	}

	private def getResourceFromLoader(Injector injector) {
		val editingDomain = injector.createAdapterFactoryEditingDomain
		getResourceFromLoader(injector, editingDomain)
	}
	
	private def getResourceFromLoader(Injector injector, AdapterFactoryEditingDomain e1) {
		val resourceSet = e1.resourceSet
		resourceSet.setupResouceFactory
		
		injector.injectMembers(resourceLoader)
		val response = resourceLoader.
			getResource(e1,
				URI.createURI(TEST_URI)
			)
		response
	}

}