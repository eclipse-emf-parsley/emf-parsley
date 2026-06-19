/*******************************************************************************
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.internal.edit.ui.dnd.DynamicEditingDomainViewerDropAdapter;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.tests.util.Wrapper;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Lorenzo Bettini
 */
public class DynamicEditingDomainViewerDropAdapterTest extends AbstractEmfParsleyShellBasedTest {

	@Rule public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	private DynamicEditingDomainViewerDropAdapter dropAdapter;

	private EditingDomain editingDomain = null;

	@Before
	public void setupTests() {
		dropAdapter = new DynamicEditingDomainViewerDropAdapter(mock(TreeViewer.class));
		getOrCreateInjector().injectMembers(dropAdapter);
	}

	@Test
	public void testWithoutEditingDomain() { // NOSONAR: we just ensure it doesn't throw
		final var event = mockEvent("a string");
		dropAdapter.drop(event);
		// no exception is expected since nothing will happen
		// when there's no editing domain
	}

	@Test
	public void testWithEditingDomain() {
		final var commandWrapper = Wrapper.forType(Command.class);
		final var adapterFactory = getOrCreateInjector().getInstance(AdapterFactory.class);
		editingDomain = new AdapterFactoryEditingDomain(adapterFactory, new BasicCommandStack()) {

			@Override
			public Command createCommand(Class<? extends Command> commandClass, CommandParameter commandParameter) {
				commandWrapper.set(super.createCommand(commandClass, commandParameter));
				return commandWrapper.get();
			}

		};
		fixtures.setupResouceFactory(editingDomain.getResourceSet());
		fixtures.createTestLibrayResourceAndInitialize(editingDomain);
		final var event = mockEvent(fixtures.getLibrary());
		dropAdapter.drop(event);
		// creation of a command may call createCommand recursively,
		// we just want to check that it is called at least once
		assertNotNull(commandWrapper.get());
		// mockito does not work for Indigo and Kepler in this case		
		// verify(editingDomain, atLeastOnce).createCommand(any(Class), any(CommandParameter))
	}

	private DropTargetEvent mockEvent(Object o) {
		DropTargetEvent event = mock(DropTargetEvent.class);
		event.data = new StructuredSelection(o);
		return event;
	}

}
