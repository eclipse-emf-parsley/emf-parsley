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
package org.eclipse.emf.parsley.tests.scenarios;

import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.tests.AbstractEditingMenuBuilderTest;
import org.junit.Before;
import org.junit.Test;

/**
 * In this scenario the EditingDomain is set from outside
 */
public class EditingMenuBuilderTestWithEditingDomainRetrievedScenario // NOSONAR: we want it be called like that
		extends AbstractEditingMenuBuilderTest {

	private Resource resource;

	private EditingDomain resourceEditingDomain;

	@Before
	public void createResource() {
		// this way the objects we use for testing are in a resource
		// and in this scenario they'll have an EditingDomain
		resource = createResourceForTest();
	}

	/**
	 * In this scenario, when an object has not an editing domain
	 * no action is created.
	 */
	@Test
	@Override
	public void testMenuWithNotEObject() {
		final var editingMenuBuilder = getAndInitializeEditingMenuBuilder();
		assertMenuItemsGivenObject(editingMenuBuilder, "aString", "");
	}

	@Override
	protected EditingDomain getEditingDomain() {
		return null;
	}

	@Override
	protected Resource createResourceForTest() {
		if (resource == null) {
			resourceEditingDomain = fixtures.createEditingDomain(getOrCreateInjector());
			resource = fixtures.createTestLibrayResourceAndInitialize(
				fixtures.setupResouceFactory(resourceEditingDomain.getResourceSet())
			);
		}
		return resource;
	}

	@Override
	protected void commandStackListener(CommandStackListener listener) {
		commandStackListener(listener, resourceEditingDomain);
	}

	/**
	 * The listener will get only one event.
	 */
	@Override
	protected void oneTimeCommandStackListener(CommandStackListener listener) {
		oneTimeCommandStackListener(listener, resourceEditingDomain);
	}
}
