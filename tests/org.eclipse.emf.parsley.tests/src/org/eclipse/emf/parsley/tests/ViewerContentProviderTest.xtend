/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests

import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest
import org.eclipse.emf.parsley.tests.models.testmodels.TestContainer
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesTestRule
import org.eclipse.jface.viewers.ILabelProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*

class ViewerContentProviderTest extends AbstractEmfParsleyTest {

	@Rule public extension EmfParsleyFixturesTestRule fixtures = new EmfParsleyFixturesTestRule()

	var ViewerContentProvider contentProvider
	
	/**
	 * We need this only to render the contents returned by the content provider
	 */
	var ILabelProvider labelProvider
	
	@Before
	def void setupContentProvider() {
		contentProvider = getOrCreateInjector.getInstance(ViewerContentProvider)
		labelProvider = getOrCreateInjector.getInstance(ILabelProvider)
	}

	@Test
	def void testGetElementsNull() {
		0.assertEquals(contentProvider.getElements(null).length)
	}

	@Test
	def void testGetChildrenNull() {
		0.assertEquals(contentProvider.getChildren(null).length)
	}

	@Test
	def void testHasChildrenNull() {
		assertFalse(contentProvider.hasChildren(null))
	}

	@Test
	def void testDefaultGetElements() {
		"Class With Name Test, Class For Controls".
		assertArray(
			contentProvider.getElements(fillTestContainer)
		)
	}

	@Test
	def void testDefaultGetChildren() {
		"Class With Name Test, Class For Controls".
		assertArray(
			contentProvider.getChildren(fillTestContainer)
		)
	}

	@Test
	def void testDefaultHasChildren() {
		assertFalse(contentProvider.hasChildren(testContainer))
		assertTrue(contentProvider.hasChildren(fillTestContainer))
	}

	@Test
	def void testCustomGetElements() {
		"Class For Controls".
		assertArray(
			new ViewerContentProvider(null) {
				def elements(TestContainer e) {
					// don't return classesWithName
					e.classesForControls
				}
			}.injectMembers.getElements(fillTestContainer)
		)
	}

	@Test
	def void testCustomGetChildren() {
		"Class For Controls".
		assertArray(
			new ViewerContentProvider(null) {
				def children(TestContainer e) {
					// don't return classesWithName
					e.classesForControls
				}
			}.injectMembers.getChildren(fillTestContainer)
		)
	}

	@Test
	def void testCustomHasChildrenUsesCustomGetChildren() {
		testContainer.classesWithName += createClassWithName("Test")
		// although it contains a ClassWithName, the custom getChildren
		// does not return such elements as children, so hasChildren
		// returns false
		assertFalse(
			new ViewerContentProvider(adapterFactory) {
				def children(TestContainer e) {
					// don't return classesWithName
					e.classesForControls
				}
			}.injectMembers.hasChildren(testContainer)
		)
	}

	@Test
	def void testGetParentWithoutResource() {
		contentProvider.getParent(testContainer).assertNull
	}

	@Test
	def void testGetParentWithResource() {
		val res = createResource => [ contents += testContainer ]
		res.assertEquals(contentProvider.getParent(testContainer))
	}

	@Test
	def void testGetParentAvoidsLoopReturningNull() {
		// this creates a cycle
		testContainer.contained = testContainer
		testContainer.assertSame(testContainer.eContainer)
		contentProvider.getParent(testContainer).assertNull
	}

	def private fillTestContainer() {
		testContainer => [
			classesForControls += classForControlsInstance
			classesWithName += createClassWithName("Test")
		]
	}

	def private assertArray(CharSequence expected, Object[] a) {
		expected.toString.assertEquals(
			a.map[labelProvider.getText(it)].join(", ")
		)
	}
}
