package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyControlBasedTest;
import org.eclipse.emf.parsley.listeners.AsyncCommandStackListenerHelper;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.tests.util.TestableCommandStackListenerClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.inject.Injector;

public class AsyncCommandStackListenerTest extends AbstractEmfParsleyControlBasedTest {

	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	private EditingDomain editingDomain = null;

	private TestableCommandStackListenerClient client;

	private AsyncCommandStackListenerHelper helper;

	private Resource resource;

	public static class TestableCommand extends AbstractCommand {
		
		final List<Object> affectedObjects = new ArrayList<>();
		
		@Override
		public Collection<?> getAffectedObjects() {
			return affectedObjects;
		}

		@Override
		public boolean canExecute() {
			return true;
		}

		@Override
		public void execute() {
			// not used
		}

		@Override
		public void redo() {
			// not used
		}

	}

	@Before
	public void initialize() {
		Injector injector = getOrCreateInjector();
		editingDomain = injector.getProvider(EditingDomain.class).get();
		client = new TestableCommandStackListenerClient();
		helper = injector.getInstance(AsyncCommandStackListenerHelper.class);
		resource = new ResourceImpl();
	}

	@Test
	public void testCommandAffectingNoObject() {
		setupListener(resource);
		executeCommand(new TestableCommand());
		assertEquals(0, client.commandsAffectingResources.size());
		assertEquals(1, client.commands.size());
	}

	@Test
	public void testCommandAffectingNoEObject() {
		setupListener(resource);
		TestableCommand cmd = new TestableCommand();
		cmd.affectedObjects.add("a string");
		executeCommand(cmd);
		assertEquals(0, client.commandsAffectingResources.size());
		assertEquals(1, client.commands.size());
	}

	@Test
	public void testCommandAffectingObjectOfDifferentResource() {
		setupListener(resource);
		Resource resource2 = new ResourceImpl();
		var object = fixtures.createClassWithName("");
		resource2.getContents().add(object);
		TestableCommand cmd = new TestableCommand();
		cmd.affectedObjects.add(object);
		executeCommand(cmd);
		assertEquals(0, client.commandsAffectingResources.size());
		assertEquals(1, client.commands.size());
	}

	@Test
	public void testCommandAffectingObjectOfDifferentResourceWithoutResourceToObserve() {
		setupListener();
		Resource resource2 = new ResourceImpl();
		var object = fixtures.createClassWithName("");
		resource2.getContents().add(object);
		TestableCommand cmd = new TestableCommand();
		cmd.affectedObjects.add(object);
		executeCommand(cmd);
		assertEquals(1, client.commandsAffectingResources.size());
		assertEquals(1, client.commands.size());
	}

	@Test
	public void testCommandAffectingResource() {
		setupListener(resource);
		var object = fixtures.createClassWithName("");
		resource.getContents().add(object);
		TestableCommand cmd = new TestableCommand();
		cmd.affectedObjects.add(object);
		executeCommand(cmd);
		assertEquals(1, client.commandsAffectingResources.size());
		assertEquals(1, client.commands.size());
	}

	@Test
	public void testCommandThrowingNPE() {
		// check that EMF internally detects the NPE
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=479491
		setupListener(resource);
		ByteArrayOutputStream err = new ByteArrayOutputStream();
		PrintStream backup = System.err;
		System.setErr(new PrintStream(err));
		try {
			executeCommand(new TestableCommand() {
				@Override
				public void execute() {
					throw new NullPointerException();
				}
			});
			// since the command throws a NPE, the most recent command will be null
			// so we won't get any notification
			assertEquals(0, client.commandsAffectingResources.size());
			assertEquals(0, client.commands.size());
			// check that EMF internally caught the exception
			assertTrue("not found the expected string in:\n" + err.toString(),
				err.toString().contains("An exception was ignored during command execution")
			);
		} finally {
			System.setErr(backup);
		}
	}

	private void executeCommand(Command command) {
		editingDomain.getCommandStack().execute(command);
		// the listener executes in the UI thread with async
		// so we must flush all the pending events
		flushPendingEvents();
	}

	private void setupListener() {
		helper.addCommandStackListener(editingDomain, getShell(), client);
	}

	private void setupListener(Resource resource) {
		helper.addCommandStackListener(editingDomain, getShell(), client, resource);
	}
}
