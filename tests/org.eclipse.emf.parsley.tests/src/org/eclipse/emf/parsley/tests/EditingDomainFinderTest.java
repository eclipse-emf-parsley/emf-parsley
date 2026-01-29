/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.edit.EditingDomainFinder;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest;
import org.eclipse.emf.parsley.resource.ResourceLoader;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.inject.Inject;

public class EditingDomainFinderTest extends AbstractEmfParsleyTest {

	@Rule public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	@Inject private EditingDomainFinder editingDomainFinder;

	@Inject private EditingDomain editingDomain;

	@Before
	public void setupFinder() {
		getOrCreateInjector().injectMembers(this);
	}

	@Test public void testNotEObject() {
		assertNull(editingDomainFinder.getEditingDomainFor("aString"));
	}

	@Test public void testEObjectWithoutResource() {
		assertNull(editingDomainFinder.getEditingDomainFor(fixtures.getClassForControlsInstance()));
	}

	@Test public void testEObjectInResource() {
		final var resource = loadResourceWithContents();
		assertSame(editingDomain, editingDomainFinder.getEditingDomainFor(classWithName(resource)));
	}

	@Test public void testResource() {
		final var resource = loadResourceWithContents();
		assertSame(editingDomain, editingDomainFinder.getEditingDomainFor(resource));
	}

	@Test public void testResourceNotInResourceSet() {
		final var resource = new ResourceImpl();
		assertNull(editingDomainFinder.getEditingDomainFor(resource));
	}

	@Test public void testResourceSetNotInEditingDomain() {
		final var resource = new ResourceImpl();
		final var resourceSet = new ResourceSetImpl();
		resourceSet.getResources().add(resource);
		assertNotNull(resource.getResourceSet());
		assertNull(editingDomainFinder.getEditingDomainFor(resource));
	}

	private Resource loadResourceWithContents() {
		final var injector = getOrCreateInjector();
		final var resourceSet = editingDomain.getResourceSet();

		fixtures.setupResouceFactory(resourceSet);

		final var resource = injector.getInstance(ResourceLoader.class).getResource(editingDomain,
			URI.createURI("http:///My.testmodels")).getResource();
		assertNotNull(resource);
		resource.getContents().add(fixtures.createClassWithName("test"));
		return resource;
	}

	private ClassWithName classWithName(Resource resource) {
		return (ClassWithName) resource.getContents().get(0);
	}

}
