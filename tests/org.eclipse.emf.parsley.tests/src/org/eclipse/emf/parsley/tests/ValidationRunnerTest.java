package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Level;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest;
import org.eclipse.emf.parsley.junit4.util.LogAppenderTestRule;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForDefaultValidation;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForValidation;
import org.eclipse.emf.parsley.tests.models.testmodels.TestContainer;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.validation.LogIssueReporter;
import org.eclipse.emf.parsley.validation.ValidationRunner;
import org.junit.Rule;
import org.junit.Test;

public class ValidationRunnerTest extends AbstractEmfParsleyTest {
	
	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	@Rule
	public LogAppenderTestRule logAppender = new LogAppenderTestRule(LogIssueReporter.class);

	@Test
	public void testValidateObject() {
		ClassForValidation objectForValidation = fixtures.getTestFactory().createClassForValidation();
		createValidationRunner().validate(objectForValidation, createLogIssueReporter());
		logAppender.assertContainsMessage("ERROR: the field 'notEmpty' cannot be empty");
	}

	@Test
	public void testValidateObjectDefault() {
		// standard Ecore validation
		ClassForDefaultValidation objectForValidation = fixtures.getTestFactory().createClassForDefaultValidation();
		createValidationRunner().validate(objectForValidation, createLogIssueReporter());
		logAppender.assertContainsMessage("The required feature 'notEmpty'");
	}

	@Test
	public void testValidateObjectDefaultOk() {
		// standard Ecore validation
		ClassForDefaultValidation objectForValidation = fixtures.getTestFactory().createClassForDefaultValidation();
		objectForValidation.setNotEmpty("foo");
		objectForValidation.setNotNullReference(fixtures.getTestFactory().createClassWithName());
		createValidationRunner().validate(objectForValidation, createLogIssueReporter());
		logAppender.assertEmpty();
	}

	@Test
	public void testValidateContents() {
		TestContainer container = fixtures.getTestFactory().createTestContainer();
		container.getObjectsForValidation().add(fixtures.getTestFactory().createClassForValidation());
		container.getObjectsForValidation().add(fixtures.getTestFactory().createClassForValidation());
		createValidationRunner().validate(container, createLogIssueReporter());
		logAppender.assertContainsMessage("ERROR: the field 'notEmpty' cannot be empty,ERROR: the field 'notEmpty' cannot be empty");
	}

	@Test
	public void testValidateResourceContents() {
		Resource resource = fixtures.createResource();
		// resource with two root objects
		TestContainer container1 = fixtures.getTestFactory().createTestContainer();
		container1.getObjectsForValidation().add(fixtures.getTestFactory().createClassForValidation());
		resource.getContents().add(container1);
		TestContainer container2 = fixtures.getTestFactory().createTestContainer();
		container2.getObjectsForValidation().add(fixtures.getTestFactory().createClassForValidation());
		resource.getContents().add(container2);
		createValidationRunner().validate(resource, createLogIssueReporter());
		logAppender.assertContainsMessage("ERROR: the field 'notEmpty' cannot be empty,ERROR: the field 'notEmpty' cannot be empty");
	}

	@Test
	public void testWarning() {
		ClassForValidation objectForValidation = fixtures.getTestFactory().createClassForValidation();
		objectForValidation.setNotEmpty("a");
		createValidationRunner().validate(objectForValidation, createLogIssueReporter());
		logAppender.assertContainsMessage("WARNING: the field 'notEmpty' should be more than one character");
	}

	@Test
	public void testInfo() {
		Level currentLevel = logAppender.getLogger().getLevel();
		logAppender.getLogger().setLevel(Level.INFO);
		ClassForValidation objectForValidation = fixtures.getTestFactory().createClassForValidation();
		objectForValidation.setNotEmpty("ab");
		createValidationRunner().validate(objectForValidation, createLogIssueReporter());
		logAppender.assertContainsMessage("INFO: the field 'notEmpty' is two characters long");
		logAppender.getLogger().setLevel(currentLevel);
	}

	@Test
	public void testNoIssue() {
		Level currentLevel = logAppender.getLogger().getLevel();
		logAppender.getLogger().setLevel(Level.INFO);
		ClassForValidation objectForValidation = fixtures.getTestFactory().createClassForValidation();
		objectForValidation.setNotEmpty("abc");
		createValidationRunner().validate(objectForValidation, createLogIssueReporter());
		logAppender.assertContainsMessage("OK: Diagnosis");
		logAppender.getLogger().setLevel(currentLevel);
	}

	@Test
	public void testOkDiagnosticIsLogged() {
		ClassForValidation objectForValidation = fixtures.getTestFactory().createClassForValidation();
		objectForValidation.setNotEmpty("abc");
		assertEquals(1,
			createValidationRunner().validate(objectForValidation, createLogIssueReporter()).size());
	}

	private ValidationRunner createValidationRunner() {
		return getOrCreateInjector().getInstance(ValidationRunner.class);
	}

	private LogIssueReporter createLogIssueReporter() {
		return getOrCreateInjector().getInstance(LogIssueReporter.class);
	}
}
