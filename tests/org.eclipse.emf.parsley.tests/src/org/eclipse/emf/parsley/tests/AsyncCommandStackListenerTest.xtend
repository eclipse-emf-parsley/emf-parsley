package org.eclipse.emf.parsley.tests

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import org.eclipse.emf.common.command.AbstractCommand
import org.eclipse.emf.common.command.Command
import org.eclipse.emf.ecore.resource.impl.ResourceImpl
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
import org.eclipse.emf.edit.domain.EditingDomain
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyControlBasedTest
import org.eclipse.emf.parsley.listeners.AsyncCommandStackListenerClient
import org.eclipse.emf.parsley.listeners.AsyncCommandStackListenerHelper
import org.eclipse.emf.parsley.tests.AsyncCommandStackListenerTest.TestableCommand
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*
import org.eclipse.emf.ecore.resource.Resource

class AsyncCommandStackListenerTest extends AbstractEmfParsleyControlBasedTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	var EditingDomain editingDomain = null

	var TestableClient client

	var AsyncCommandStackListenerHelper helper

	var Resource resource

	static class TestableClient implements AsyncCommandStackListenerClient {

		public val commandsAffectingResources = <Command>newArrayList()

		public val commands = <Command>newArrayList()

		override mostRecentCommandAffectsResource(Command mostRecentCommand) {
			commandsAffectingResources += mostRecentCommand
		}

		override postCommandStackChanged(Command mostRecentCommand) {
			commands += mostRecentCommand
		}

	}

	static class TestableCommand extends AbstractCommand {
		
		val affectedObjects = newArrayList()
		
		override getAffectedObjects() {
			return affectedObjects
		}

		override canExecute() {
			true
		}

		override execute() {
		}

		override redo() {
		}

	}

	@Before
	def void initialize() {
		val injector = getOrCreateInjector
		editingDomain = injector.getProvider(AdapterFactoryEditingDomain).get()
		client = new TestableClient
		helper = injector.getInstance(AsyncCommandStackListenerHelper)
		resource = new ResourceImpl
	}

	@Test def void testCommandAffectingNoObject() {
		setupListener(resource)
		executeCommand(new TestableCommand)
		0.assertEquals(client.commandsAffectingResources.size)
		1.assertEquals(client.commands.size)
	}

	@Test def void testCommandAffectingNoEObject() {
		setupListener(resource)
		executeCommand(new TestableCommand => [affectedObjects += "a string"])
		0.assertEquals(client.commandsAffectingResources.size)
		1.assertEquals(client.commands.size)
	}

	@Test def void testCommandAffectingObjectOfDifferentResource() {
		setupListener(resource)
		val resource2 = new ResourceImpl
		val object = createClassWithName("")
		resource2.contents += object
		executeCommand(new TestableCommand => [affectedObjects += object])
		0.assertEquals(client.commandsAffectingResources.size)
		1.assertEquals(client.commands.size)
	}

	@Test def void testCommandAffectingObjectOfDifferentResourceWithoutResourceToObserve() {
		setupListener()
		val resource2 = new ResourceImpl
		val object = createClassWithName("")
		resource2.contents += object
		executeCommand(new TestableCommand => [affectedObjects += object])
		1.assertEquals(client.commandsAffectingResources.size)
		1.assertEquals(client.commands.size)
	}

	@Test def void testCommandAffectingResource() {
		setupListener(resource)
		val object = createClassWithName("")
		resource.contents += object
		executeCommand(new TestableCommand => [affectedObjects += object])
		1.assertEquals(client.commandsAffectingResources.size)
		1.assertEquals(client.commands.size)
	}

	@Test def void testCommandThrowingNPE() {
		// check that EMF internally detects the NPE
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=479491
		setupListener(resource)
		val err = new ByteArrayOutputStream()
		val backup = System.err
		System.setErr(new PrintStream(err))
		try {
			executeCommand(new TestableCommand() {
				override execute() {
					throw new NullPointerException
				}
			})
			// since the command throws a NPE, the most recent command will be null
			// so we won't get any notification
			0.assertEquals(client.commandsAffectingResources.size)
			0.assertEquals(client.commands.size)
			// check that EMF internally caught the exception
			assertTrue("not found the expected string in:\n" + err.toString,
				err.toString.contains("An exception was ignored during command execution")
			)
		} finally {
			System.setErr(backup)
		}
	}

	def private executeCommand(Command command) {
		editingDomain.commandStack.execute(command)
		// the listener executes in the UI thread with async
		// so we must flush all the pending events
		flushPendingEvents
	}

	def private setupListener() {
		helper.addCommandStackListener(editingDomain, shell, client)
	}

	def private setupListener(Resource resource) {
		helper.addCommandStackListener(editingDomain, shell, client, resource)
	}
}