package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest;
import org.eclipse.emf.parsley.junit4.util.LogAppenderTestRule;
import org.eclipse.emf.parsley.resource.ResourceSaveStrategy;
import org.eclipse.emf.parsley.resource.ValidateBeforeSaveStrategy;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsFactory;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.tests.util.LogIssueReporterForTests;
import org.eclipse.emf.parsley.validation.IssueReporter;
import org.eclipse.emf.parsley.validation.LogIssueReporter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class ResourceSaveStrategyTest extends AbstractEmfParsleyTest {
	
	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();
	
	@Rule
	public final LogAppenderTestRule logAppender = new LogAppenderTestRule(LogIssueReporter.class);

	private ResourceSaveStrategy resourceSaveStrategy;

	@Before
	public void setupResourceLoader() {
		// note that members must be explicitly injected in the tests
		resourceSaveStrategy = new ResourceSaveStrategy();
	}

	@Test
	public void testDefaultSaveStrategy() throws IOException {
		getOrCreateInjector().injectMembers(resourceSaveStrategy);
		Resource resource = fixtures.createTestResource();
		resource.getContents().add(
					TestmodelsFactory.eINSTANCE.createClassWithName());
		assertTrue(resourceSaveStrategy.save(resource));
		// now reload it and check that it was effectively saved
		resource = fixtures.loadTestResource();
		assertEquals(1, resource.getContents().size());
	}

	@Test
	public void testValidateBeforeSaveStrategyOnResourceWithError() throws IOException {
		resourceSaveStrategy = createValidateBeforeSaveResourceStrategy();
		Resource resource = fixtures.createTestResource();
		assertTrue(resourceSaveStrategy.save(resource));
		resource.getContents().add(
			TestmodelsFactory.eINSTANCE.createClassForValidation()
		);
		assertFalse(resourceSaveStrategy.save(resource));
		// now reload it and check that it was not saved
		resource = fixtures.loadTestResource();
		assertEquals(0, resource.getContents().size());
		logAppender.assertContainsMessage("ERROR: the field 'notEmpty' cannot be empty");
	}

	@Test
	public void testValidateBeforeSaveStrategyOnResourceWithWarning() throws IOException {
		resourceSaveStrategy = createValidateBeforeSaveResourceStrategy();
		Resource resource = fixtures.createTestResource();
		assertTrue(resourceSaveStrategy.save(resource));
		var classForValidation = TestmodelsFactory.eINSTANCE.createClassForValidation();
		classForValidation.setNotEmpty("a"); // this will only issue a warning
		resource.getContents().add(classForValidation);
		assertTrue(resourceSaveStrategy.save(resource));
		// now reload it and check that it was saved
		resource = fixtures.loadTestResource();
		assertEquals(1, resource.getContents().size());
		logAppender.assertContainsMessage("WARNING: the field 'notEmpty' should be more than one character");
	}

	private ResourceSaveStrategy createValidateBeforeSaveResourceStrategy() {
		return createInjector(new EmfParsleyGuiceModuleForTesting() {
			
			@Override
			public Class<? extends ResourceSaveStrategy> bindResourceSaveStrategy() {
				return ValidateBeforeSaveStrategy.class;
			}
			
			@Override
			public Class<? extends IssueReporter> bindIssueReporter() {
				return LogIssueReporterForTests.class;
			}
			
		}).getInstance(ResourceSaveStrategy.class);
	}
}
