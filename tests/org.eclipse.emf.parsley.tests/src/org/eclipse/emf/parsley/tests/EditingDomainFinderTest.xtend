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

import com.google.inject.Inject
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.edit.domain.EditingDomain
import org.eclipse.emf.parsley.edit.EditingDomainFinder
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest
import org.eclipse.emf.parsley.resource.ResourceLoader
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*
import org.eclipse.emf.ecore.resource.impl.ResourceImpl
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl

class EditingDomainFinderTest extends AbstractEmfParsleyTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	@Inject var EditingDomainFinder editingDomainFinder

	@Inject var EditingDomain editingDomain

	@Before
	def void setupFinder() {
		getOrCreateInjector.injectMembers(this)
	}

	@Test def void testNotEObject() {
		editingDomainFinder.getEditingDomainFor("aString").assertNull
	}

	@Test def void testEObjectWithoutResource() {
		editingDomainFinder.getEditingDomainFor(classForControlsInstance).assertNull
	}

	@Test def void testEObjectInResource() {
		val resource = loadResourceWithContents()
		editingDomain.assertSame(editingDomainFinder.getEditingDomainFor(classWithName(resource)))
	}

	@Test def void testResource() {
		val resource = loadResourceWithContents()
		editingDomain.assertSame(editingDomainFinder.getEditingDomainFor(resource))
	}

	@Test def void testResourceNotInResourceSet() {
		val resource = new ResourceImpl
		editingDomainFinder.getEditingDomainFor(resource).assertNull
	}

	@Test def void testResourceSetNotInEditingDomain() {
		val resource = new ResourceImpl => [ res |
			new ResourceSetImpl => [
				resources += res
			]
		]
		resource.resourceSet.assertNotNull
		editingDomainFinder.getEditingDomainFor(resource).assertNull
	}

	def private loadResourceWithContents() {
		val injector = getOrCreateInjector
		val resourceSet = editingDomain.resourceSet

		resourceSet.setupResouceFactory

		val resource = injector.getInstance(ResourceLoader).getResource(editingDomain,
			URI.createURI("http:///My.testmodels")).resource
		resource.assertNotNull
		resource.contents += createClassWithName("test")
		resource
	}

	def private classWithName(Resource resource) {
		resource.contents.head as ClassWithName
	}

}