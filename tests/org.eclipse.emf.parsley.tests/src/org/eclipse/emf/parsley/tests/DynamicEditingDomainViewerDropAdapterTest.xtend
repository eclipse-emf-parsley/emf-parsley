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
package org.eclipse.emf.parsley.tests

import org.eclipse.emf.common.command.BasicCommandStack
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.edit.command.CommandParameter
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
import org.eclipse.emf.edit.domain.EditingDomain
import org.eclipse.emf.edit.domain.IEditingDomainProvider
import org.eclipse.emf.parsley.internal.edit.ui.dnd.DynamicEditingDomainViewerDropAdapter
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.eclipse.jface.viewers.StructuredSelection
import org.eclipse.jface.viewers.TreeViewer
import org.eclipse.swt.dnd.DropTargetEvent
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static org.mockito.Matchers.*
import static org.mockito.Mockito.*

/**
 * @author Lorenzo Bettini
 */
class DynamicEditingDomainViewerDropAdapterTest extends AbstractEmfParsleyShellBasedTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	var DynamicEditingDomainViewerDropAdapter dropAdapter

	var EditingDomain editingDomain = null

	@Before
	def void setupTests() {
		dropAdapter = new DynamicEditingDomainViewerDropAdapter(mock(TreeViewer))
		getOrCreateInjector.injectMembers(dropAdapter)
	}

	@Test
	def void testWithoutEditingDomain() {
		val event = mockEvent("a string")
		dropAdapter.drop(event)
		// no exception is expected since nothing will happen
		// when there's no editing domain
	}

	@Test
	def void testWithEditingDomain() {
		createResourceForTest
		val event = mockEvent(library)
		dropAdapter.drop(event)
		// creation of a command may call createCommand recursively,
		// we just want to check that it is called at least once
		verify(editingDomain, atLeastOnce).createCommand(any(Class), any(CommandParameter))
	}

	def private mockEvent(Object o) {
		mock(DropTargetEvent) => [
			data = new StructuredSelection(o)
		]
	}

	def private createResourceForTest() {
		val e = new AdapterFactoryEditingDomain(adapterFactory, new BasicCommandStack)
		editingDomain = spy(e) => [
			resourceSet.setupResouceFactory
		]
		// since the adapter factory editing domain's resource set is an
		// internal class instance we need to play with mocks a little bit
		// so that the spied editing domain is effectively retrieved by the
		// EditingDomainFinder
		val resourceSet = spy(editingDomain.resourceSet as IEditingDomainProvider)
		when(resourceSet.editingDomain).thenReturn(editingDomain)
		when(editingDomain.resourceSet).thenReturn(resourceSet as ResourceSet)
		val resource = createTestLibrayResourceAndInitialize(
			editingDomain
		)
		return resource
	}

}
