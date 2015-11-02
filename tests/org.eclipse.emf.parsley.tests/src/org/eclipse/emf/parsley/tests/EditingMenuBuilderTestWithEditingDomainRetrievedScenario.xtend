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

import org.eclipse.emf.common.command.CommandStackListener
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.edit.domain.EditingDomain
import org.junit.Before
import org.junit.Test

/**
 * In this scenario the EditingDomain is set from outside
 */
class EditingMenuBuilderTestWithEditingDomainRetrievedScenario extends AbstractEditingMenuBuilderTest {

	var Resource resource

	var EditingDomain resourceEditingDomain

	@Before
	def void createResource() {
		// this way the objects we use for testing are in a resource
		// and in this scenario they'll have an EditingDomain
		resource = createResourceForTest
	}

	/**
	 * In this scenario, when an object has not an editing domain
	 * no action is created.
	 */
	@Test
	override void testMenuWithNotEObject() {
		val editingMenuBuilder = getAndInitializeEditingMenuBuilder
		editingMenuBuilder.assertMenuItemsGivenObject("aString", "")
	}

	override protected getEditingDomain() {
		return null
	}

	override protected createResourceForTest() {
		if (resource == null) {
			resourceEditingDomain = getOrCreateInjector.createEditingDomain
			resource = createTestLibrayResourceAndInitialize(
				resourceEditingDomain.resourceSet.setupResouceFactory
			)
		}
		return resource
	}

	override protected commandStackListener(CommandStackListener listener) {
		commandStackListener(listener, resourceEditingDomain)
	}

	/**
	 * The listener will get only one event.
	 */
	override protected oneTimeCommandStackListener(CommandStackListener listener) {
		oneTimeCommandStackListener(listener, resourceEditingDomain)
	}
}
