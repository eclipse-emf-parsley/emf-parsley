package org.eclipse.emf.parsley.tests.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.parsley.listeners.AsyncCommandStackListenerClient;

public class TestableCommandStackListenerClient implements AsyncCommandStackListenerClient {

	public final List<Command> commandsAffectingResources = new ArrayList<>();

	public final List<Command> commands = new ArrayList<>();

	@Override
	public void mostRecentCommandAffectsResource(Command mostRecentCommand) {
		commandsAffectingResources.add(mostRecentCommand);
	}

	@Override
	public void postCommandStackChanged(Command mostRecentCommand) {
		commands.add(mostRecentCommand);
	}

}
