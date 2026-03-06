package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest;
import org.eclipse.emf.parsley.resource.LoadResourceResponse;
import org.eclipse.emf.parsley.resource.ResourceLoader;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.tests.util.ModuleWithResourceInitializer;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.inject.Injector;

public class ResourceLoaderTest extends AbstractEmfParsleyTest {

	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	private TestableResourceLoader resourceLoader;

	private static final String TEST_URI = "http://dummy/My.testmodels";
	private static final String LOCAL_RESOURCE_URI = "resources/TestContainer.xmi";
	private static final String LOCAL_EMPTY_RESOURCE_URI = "resources/EmptyResource.xmi";

	/**
	 * Protected methods are public so that we can test them
	 */
	static class TestableResourceLoader extends ResourceLoader {
		@Override
		public void initializeEmptyResource(Resource resource) {
			super.initializeEmptyResource(resource);
		}
	}

	@Before
	public void setupResourceLoader() {
		// note that members must be explicitly injected in the tests
		resourceLoader = new TestableResourceLoader();
	}

	@Test
	public void testLocalResource() {
		final var injector = getOrCreateInjector();
		injector.injectMembers(resourceLoader);
		final var resource = resourceLoader.getResource(
			fixtures.createAndSetupResourceSet(),
			URI.createURI(LOCAL_RESOURCE_URI)
		);
		assertFalse("resource should not be empty", resource.getContents().isEmpty());
	}

	@Test
	public void testLocalEmptyResource() {
		final var injector = getOrCreateInjector();
		injector.injectMembers(resourceLoader);
		final var resource = resourceLoader.getResource(
			fixtures.createAndSetupResourceSet(),
			URI.createURI(LOCAL_EMPTY_RESOURCE_URI)
		);
		assertTrue("resource should be empty", resource.getContents().isEmpty());
	}

	@Test
	public void testInitializeOnNullResource() { // NOSONAR: we just ensure it doesn't throw
		resourceLoader.initializeEmptyResource(null);
	}

	@Test
	public void testLoadingNonExistingResourceLeadsToCreatingWithEditingDomain() {
		final var injector = createInjector(new EmfParsleyGuiceModuleForTesting());
		final var response = getResourceFromLoader(injector);
		assertTrue("resource should be empty", response.getResource().getContents().isEmpty());
	}

	@Test
	public void testEmptyResourceIsInitializedUsingResourceManager() {
		final var injector = createInjector(new ModuleWithResourceInitializer());
		final var response = getResourceFromLoader(injector);
		assertFalse("resource should not be empty", response.getResource().getContents().isEmpty());
	}

	@Test
	public void testNonEmptyResourceIsNotInitialized() {
		final var injector = createInjector(new ModuleWithResourceInitializer());
		final var editingDomain = fixtures.createEditingDomain(injector);
		final var response = getResourceFromLoader(injector, editingDomain);
		final var el = response.getResource().getContents().get(0);
		assertNotNull(el);
		final var response2 = getResourceFromLoader(injector, editingDomain);
		final var el2 = response2.getResource().getContents().get(0);
		assertSame(el, el2);
	}

	private LoadResourceResponse getResourceFromLoader(Injector injector) {
		final var editingDomain = fixtures.createEditingDomain(injector);
		return getResourceFromLoader(injector, editingDomain);
	}

	private LoadResourceResponse getResourceFromLoader(Injector injector, EditingDomain e1) {
		final var resourceSet = e1.getResourceSet();
		fixtures.setupResouceFactory(resourceSet);
		
		injector.injectMembers(resourceLoader);
		final var response = resourceLoader.getResource(
			e1,
			URI.createURI(TEST_URI)
		);
		return response;
	}

}
