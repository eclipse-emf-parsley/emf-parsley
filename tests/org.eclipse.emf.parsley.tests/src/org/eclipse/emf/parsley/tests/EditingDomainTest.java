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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest;
import org.eclipse.emf.parsley.resource.ResourceLoader;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.tests.util.GlobalAdapterFactoryEditingDomainModule;
import org.eclipse.emf.parsley.tests.util.SingletonAdapterFactoryEditingDomainModule;
import org.junit.Rule;
import org.junit.Test;

import com.google.inject.Injector;
import com.google.inject.Module;

public class EditingDomainTest extends AbstractEmfParsleyTest {

	@Rule public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	@Test
	public void testDefaultEditingDomainProvider() {
		final var injector = createDefaultEditingDomainProviderInjector();

		final var e1 = getEditingDomainInstance(injector);
		final var e2 = getEditingDomainInstance(injector);

		assertNotSame(e1, e2);
	}

	@Test
	public void testGlobalEditingDomainProvider() {
		final var injector = createGlobalEditingDomainProviderInjector();

		final var e1 = getEditingDomainInstance(injector);
		final var e2 = getEditingDomainInstance(injector);

		assertSame(e1, e2);
	}

	@Test
	public void testSingletonEditingDomainProviderWithTheSameInjector() {
		final var injector = createSingletonEditingDomainProviderInjector();

		final var e1 = getEditingDomainInstance(injector);
		final var e2 = getEditingDomainInstance(injector);

		assertSame(e1, e2);
	}

	@Test
	public void testSingletonEditingDomainProviderWithDifferentInjectors() {
		final var e1 = getEditingDomainInstance(createSingletonEditingDomainProviderInjector());
		final var e2 = getEditingDomainInstance(createSingletonEditingDomainProviderInjector());

		// singleton according to Guice @Singleton
		// since the injectors are different the instances will be
		// different as well
		assertNotSame(e1, e2);
	}

	@Test
	public void testDifferentResourceWithDefaultEditingDomainProvider() {
		final var injector = createDefaultEditingDomainProviderInjector();

		final var res1 = loadResourceWithContents(injector);
		final var res2 = loadResourceWithContents(injector);

		assertNotSame(res1, res2);
	}

	@Test
	public void testSameResourceWithGlobalEditingDomainProvider() {
		final var injector = createGlobalEditingDomainProviderInjector();

		final var res1 = loadResourceWithContents(injector);
		final var res2 = loadResourceWithContents(injector);

		assertSame(res1, res2);

		// we act on the same Resource since we used the same
		// EditingDomain
		classWithName(res1).setName("Changed");
		assertEquals("Changed", classWithName(res2).getName());
	}

	@Test
	public void testSameResourceWithSingletonEditingDomainProvider() {
		final var injector = createSingletonEditingDomainProviderInjector();

		final var res1 = loadResourceWithContents(injector);
		final var res2 = loadResourceWithContents(injector);

		assertSame(res1, res2);

		// we act on the same Resource since we used the same
		// EditingDomain
		classWithName(res1).setName("Changed");
		assertEquals("Changed", classWithName(res2).getName());
	}

	private Resource loadResourceWithContents(Injector injector) {
		final var e1 = fixtures.createEditingDomain(injector);
		final ResourceSet resourceSet = e1.getResourceSet();

		fixtures.setupResouceFactory(resourceSet);

		final var resource = injector.getInstance(ResourceLoader.class).getResource(e1, URI.createURI("http:///My.testmodels")).
			getResource();
		assertNotNull(resource);
		return resource;
	}

	private ClassWithName classWithName(Resource resource) {
		return (ClassWithName) resource.getContents().get(0);
	}

	private Injector createDefaultEditingDomainProviderInjector() {
		return createTestInjector(new EmfParsleyGuiceModuleForTesting());
	}

	private Injector createGlobalEditingDomainProviderInjector() {
		return createTestInjector(new GlobalAdapterFactoryEditingDomainModule());
	}

	private Injector createSingletonEditingDomainProviderInjector() {
		return createTestInjector(new SingletonAdapterFactoryEditingDomainModule());
	}

	private Injector createTestInjector(Module module) {
		return createInjector(module);
	}

	private EditingDomain getEditingDomainInstance(Injector injector) {
		return injector.getInstance(EditingDomain.class);
	}

}
