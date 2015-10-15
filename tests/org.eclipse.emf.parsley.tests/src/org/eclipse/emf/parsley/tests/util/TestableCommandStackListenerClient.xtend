package org.eclipse.emf.parsley.tests.util

import org.eclipse.emf.common.command.Command
import org.eclipse.emf.parsley.listeners.AsyncCommandStackListenerClient

class TestableCommandStackListenerClient implements AsyncCommandStackListenerClient {

	public val commandsAffectingResources = <Command>newArrayList()

	public val commands = <Command>newArrayList()

	override mostRecentCommandAffectsResource(Command mostRecentCommand) {
		commandsAffectingResources += mostRecentCommand
	}

	override postCommandStackChanged(Command mostRecentCommand) {
		commands += mostRecentCommand
	}

}