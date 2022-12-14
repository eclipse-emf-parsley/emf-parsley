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
package org.eclipse.emf.parsley.tests

import com.google.inject.Injector
import com.google.inject.Module
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.edit.domain.EditingDomain
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest
import org.eclipse.emf.parsley.resource.ResourceLoader
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.eclipse.emf.parsley.tests.util.GlobalAdapterFactoryEditingDomainModule
import org.eclipse.emf.parsley.tests.util.SingletonAdapterFactoryEditingDomainModule
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*

class EditingDomainTest extends AbstractEmfParsleyTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	@Test
	def void testDefaultEditingDomainProvider() {
		val injector = createDefaultEditingDomainProviderInjector

		val e1 = getEditingDomainInstance(injector)
		val e2 = getEditingDomainInstance(injector)

		e1.assertNotSame(e2)
	}

	@Test
	def void testGlobalEditingDomainProvider() {
		val injector = createGlobalEditingDomainProviderInjector

		val e1 = getEditingDomainInstance(injector)
		val e2 = getEditingDomainInstance(injector)

		e1.assertSame(e2)
	}

	@Test
	def void testSingletonEditingDomainProviderWithTheSameInjector() {
		val injector = createSingletonEditingDomainProviderInjector

		val e1 = getEditingDomainInstance(injector)
		val e2 = getEditingDomainInstance(injector)

		e1.assertSame(e2)
	}

	@Test
	def void testSingletonEditingDomainProviderWithDifferentInjectors() {
		val e1 = getEditingDomainInstance(createSingletonEditingDomainProviderInjector)
		val e2 = getEditingDomainInstance(createSingletonEditingDomainProviderInjector)

		// singleton according to Guice @Singleton
		// since the injectors are different the instances will be
		// different as well
		e1.assertNotSame(e2)
	}

	@Test
	def void testDifferentResourceWithDefaultEditingDomainProvider() {
		val injector = createDefaultEditingDomainProviderInjector

		val res1 = injector.loadResourceWithContents
		val res2 = injector.loadResourceWithContents

		res1.assertNotSame(res2)
	}

	@Test
	def void testSameResourceWithGlobalEditingDomainProvider() {
		val injector = createGlobalEditingDomainProviderInjector

		val res1 = injector.loadResourceWithContents
		val res2 = injector.loadResourceWithContents

		res1.assertSame(res2)

		// we act on the same Resource since we used the same
		// EditingDomain
		res1.classWithName.name = "Changed"
		"Changed".assertEquals(res2.classWithName.name)
	}

	@Test
	def void testSameResourceWithSingletonEditingDomainProvider() {
		val injector = createSingletonEditingDomainProviderInjector

		val res1 = injector.loadResourceWithContents
		val res2 = injector.loadResourceWithContents

		res1.assertSame(res2)

		// we act on the same Resource since we used the same
		// EditingDomain
		res1.classWithName.name = "Changed"
		"Changed".assertEquals(res2.classWithName.name)
	}

	def private loadResourceWithContents(Injector injector) {
		val e1 = injector.createEditingDomain
		val resourceSet = e1.resourceSet

		resourceSet.setupResouceFactory

		val resource = injector.getInstance(ResourceLoader).getResource(e1, URI.createURI("http:///My.testmodels")).
			resource
		resource.assertNotNull
		resource
	}

	def private classWithName(Resource resource) {
		resource.contents.head as ClassWithName
	}

	def private createDefaultEditingDomainProviderInjector() {
		createTestInjector(new EmfParsleyGuiceModuleForTesting)
	}

	def private createGlobalEditingDomainProviderInjector() {
		createTestInjector(new GlobalAdapterFactoryEditingDomainModule)
	}

	def private createSingletonEditingDomainProviderInjector() {
		createTestInjector(new SingletonAdapterFactoryEditingDomainModule)
	}

	def private createTestInjector(Module module) {
		createInjector(module);
	}

	def private getEditingDomainInstance(Injector injector) {
		injector.getInstance(EditingDomain)
	}

}
