package org.eclipse.emf.parsley.tests

import org.apache.log4j.Level
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest
import org.eclipse.emf.parsley.junit4.util.LogAppenderTestRule
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.eclipse.emf.parsley.validation.LogIssueReporter
import org.eclipse.emf.parsley.validation.ValidationRunner
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*

class ValidationRunnerTest extends AbstractEmfParsleyTest {
	
	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	@Rule public val LogAppenderTestRule logAppender = new LogAppenderTestRule(LogIssueReporter);

	@Test
	def void testValidateObject() {
		val objectForValidation = testFactory.createClassForValidation
		createValidationRunner.validate(objectForValidation, createLogIssueReporter)
		logAppender.assertContainsMessage("ERROR: the field 'notEmpty' cannot be empty")
	}

	@Test
	def void testValidateObjectDefault() {
		// standard Ecore validation
		val objectForValidation = testFactory.createClassForDefaultValidation
		createValidationRunner.validate(objectForValidation, createLogIssueReporter)
		logAppender.assertContainsMessage("The required feature 'notEmpty'")
	}

	@Test
	def void testValidateObjectDefaultOk() {
		// standard Ecore validation
		val objectForValidation = testFactory.createClassForDefaultValidation => [
			notEmpty = "foo"
		]
		createValidationRunner.validate(objectForValidation, createLogIssueReporter)
		logAppender.assertEmpty
	}

	@Test
	def void testValidateContents() {
		val container = testFactory.createTestContainer => [
			objectsForValidation += testFactory.createClassForValidation
			objectsForValidation += testFactory.createClassForValidation
		]
		createValidationRunner.validate(container, createLogIssueReporter)
		logAppender.assertContainsMessage("ERROR: the field 'notEmpty' cannot be empty,ERROR: the field 'notEmpty' cannot be empty")
	}

	@Test
	def void testValidateResourceContents() {
		val resource = createResource => [
			// resource with two root objects
			getContents += testFactory.createTestContainer => [
				objectsForValidation += testFactory.createClassForValidation
			]
			getContents += testFactory.createTestContainer => [
				objectsForValidation += testFactory.createClassForValidation
			]
		]
		createValidationRunner.validate(resource, createLogIssueReporter)
		logAppender.assertContainsMessage("ERROR: the field 'notEmpty' cannot be empty,ERROR: the field 'notEmpty' cannot be empty")
	}

	@Test
	def void testWarning() {
		val objectForValidation = testFactory.createClassForValidation => [
			notEmpty = "a"
		]
		createValidationRunner.validate(objectForValidation, createLogIssueReporter)
		logAppender.assertContainsMessage("WARNING: the field 'notEmpty' should be more than one character")
	}

	@Test
	def void testInfo() {
		val currentLevel = logAppender.logger.level
		logAppender.logger.level = Level.INFO
		val objectForValidation = testFactory.createClassForValidation => [
			notEmpty = "ab"
		]
		createValidationRunner.validate(objectForValidation, createLogIssueReporter)
		logAppender.assertContainsMessage("INFO: the field 'notEmpty' is two characters long")
		logAppender.logger.level = currentLevel
	}

	@Test
	def void testNoIssue() {
		val currentLevel = logAppender.logger.level
		logAppender.logger.level = Level.INFO
		val objectForValidation = testFactory.createClassForValidation => [
			notEmpty = "abc"
		]
		createValidationRunner.validate(objectForValidation, createLogIssueReporter)
		logAppender.assertContainsMessage("OK: Diagnosis")
		logAppender.logger.level = currentLevel
	}

	@Test
	def void testOkDiagnosticIsLogged() {
		val objectForValidation = testFactory.createClassForValidation => [
			notEmpty = "abc"
		]
		1.assertEquals(
			createValidationRunner.validate(objectForValidation, createLogIssueReporter).size)
	}

	def private createValidationRunner() {
		getOrCreateInjector.getInstance(ValidationRunner)
	}

	def private createLogIssueReporter() {
		getOrCreateInjector.getInstance(LogIssueReporter)
	}
}