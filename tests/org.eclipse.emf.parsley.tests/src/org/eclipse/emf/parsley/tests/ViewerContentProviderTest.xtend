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
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.eclipse.emf.parsley.tests.models.testmodels.TestContainer
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.eclipse.jface.viewers.IContentProvider
import org.eclipse.jface.viewers.ILabelProvider
import org.eclipse.jface.viewers.TreeViewer
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*
import org.eclipse.emf.ecore.resource.Resource
import java.util.ArrayList
import org.eclipse.core.runtime.AssertionFailedException
import org.eclipse.emf.parsley.tests.util.NonStructuredViewer
import org.eclipse.jface.viewers.TableViewer

class ViewerContentProviderTest extends AbstractEmfParsleyShellBasedTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	val static CLASS_FOR_CONTROLS_LABEL = "Class For Controls"

	val static CLASS_WITH_NAME_TEST = "Class With Name Test"

	val static TEST_CONTAINER = "Test Container"

	val static IN_ANOTHER_CONTAINER = "another container"

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
		CLASS_FOR_CONTROLS_LABEL.
		assertArray(
			new ViewerContentProvider(adapterFactory) {
				def elements(TestContainer e) {
					// don't return classesWithName
					e.classesForControls
				}
			}.injectMembers.
			getElements(fillTestContainer)
		)
	}

	@Test
	def void testCustomGetElementsFromResource() {
		CLASS_FOR_CONTROLS_LABEL.
		assertArray(
			getContentProviderWithCustomGetElements().
				getElements(fillTestContainer.eResource)
		)
	}

	@Test
	def void testCustomGetChildren() {
		CLASS_FOR_CONTROLS_LABEL.
		assertArray(
			new ViewerContentProvider(adapterFactory) {
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

	@Test
	def void testElementsAreRefreshedWhenNewElementIsAdded() {
		// this tests the default behavior, when getElements
		// is not customized
		fillTestContainer
		val treeViewer = setupTreeViewer(testContainer.eResource,
			contentProvider)
		treeViewer.expandAll
		assertAllLabels(treeViewer,
			'''
			«TEST_CONTAINER»
			  «CLASS_WITH_NAME_TEST»
			  «CLASS_FOR_CONTROLS_LABEL»
			'''
		)
		execAndFlushPendingEvents[
			testContainer.classesForControls += createClassForControls
		]
		assertAllLabels(treeViewer,
			'''
			«TEST_CONTAINER»
			  «CLASS_WITH_NAME_TEST»
			  «CLASS_FOR_CONTROLS_LABEL»
			  «CLASS_FOR_CONTROLS_LABEL»
			'''
		)
	}

	/**
	 * This only tests elements containing null, then the setup of the tree viewer
	 * will fail due to the null element in the viewer.
	 */
	@Test(expected=AssertionFailedException)
	def void smokeTestCustomGetElementsContainsNull() {
		testContainer = createTestContainerInResource
		setupTreeViewer(testContainer.eResource,
			new ViewerContentProvider(adapterFactory) {
				def elements(Resource resource) {
					new ArrayList() => [
						add(null)
					]
				}
			}.injectMembers)
	}

	/**
	 * This only tests that we gracefully handle refresh also on
	 * non structured viewers.
	 */
	@Test
	def void smokeTestCustomGetElementsForNonStructuredViewer() {
		fillTestContainer
		new NonStructuredViewer(shell) => [
			it.contentProvider = getContentProviderWithCustomGetElements()
			it.input = testContainer.eResource
		]
		execAndFlushPendingEvents[
			testContainer.classesForControls += createClassForControls
		]
	}

	@Test
	def void testRootElementsAreRefreshedWhenNewElementIsAdded() {
		fillTestContainer
		val treeViewer = setupTreeViewer(testContainer.eResource,
			getContentProviderWithCustomGetElements())
		assertAllLabels(treeViewer, CLASS_FOR_CONTROLS_LABEL)
		execAndFlushPendingEvents[
			testContainer.classesForControls += createClassForControls
		]
		assertAllLabels(treeViewer,
			'''
			«CLASS_FOR_CONTROLS_LABEL»
			«CLASS_FOR_CONTROLS_LABEL»
			'''
		)
	}

	@Test
	def void testRootElementsAreRefreshedWhenNewElementIsAdded2() {
		fillTestContainer
		val treeViewer = setupTreeViewer(testContainer.eResource,
			getContentProviderWithCustomGetElements())
		assertAllLabels(treeViewer, CLASS_FOR_CONTROLS_LABEL)
		execAndFlushPendingEvents[
			testContainer.classesForControls += createClassForControls
			testContainer.classesForControls += createClassForControls
		]
		assertAllLabels(treeViewer,
			'''
			«CLASS_FOR_CONTROLS_LABEL»
			«CLASS_FOR_CONTROLS_LABEL»
			«CLASS_FOR_CONTROLS_LABEL»
			'''
		)
	}

	@Test
	def void testRootElementsAreRefreshedWhenNewElementIsAddedToAnAddedContainer() {
		fillTestContainer
		val treeViewer = setupTreeViewer(testContainer.eResource,
			getContentProviderWithCustomGetElements())
		assertAllLabels(treeViewer, CLASS_FOR_CONTROLS_LABEL)
		execAndFlushPendingEvents[
			createTestContainer(testContainer.eResource, IN_ANOTHER_CONTAINER) => [
				classesForControls += createClassForControls()
				classesForControls.head => [
					stringFeature = (eContainer as TestContainer).name	
				]
			]
		]
		assertAllLabels(treeViewer,
			'''
			«CLASS_FOR_CONTROLS_LABEL»
			«CLASS_FOR_CONTROLS_LABEL» «IN_ANOTHER_CONTAINER»
			'''
		)
	}

	@Test
	def void testRootElementsAreRefreshedWhenNewElementIsAddedToAnEmptyContainer() {
		testContainer = createTestContainerInResource
		val treeViewer = setupTreeViewer(testContainer.eResource,
			getContentProviderWithCustomGetElements())
		assertAllLabels(treeViewer, "")
		execAndFlushPendingEvents[
			testContainer.classesForControls += createClassForControls
		]
		assertAllLabels(treeViewer, CLASS_FOR_CONTROLS_LABEL)
	}

	@Test
	def void testRootElementsAreRefreshedWhenNewElementIsAddedToAnEmptyResource() {
		val resource = createResourceInResouceSet
		val treeViewer = setupTreeViewer(resource,
			getContentProviderWithCustomGetElements())
		assertAllLabels(treeViewer, "")
		execAndFlushPendingEvents[
			createTestContainer(resource).classesForControls += createClassForControls
		]
		assertAllLabels(treeViewer, CLASS_FOR_CONTROLS_LABEL)
	}

	@Test
	def void testRootElementsAreRefreshedWhenNewElementIsAddedAndAddedAgain() {
		fillTestContainer
		val treeViewer = setupTreeViewer(testContainer.eResource,
			getContentProviderWithCustomGetElements())
		assertAllLabels(treeViewer, CLASS_FOR_CONTROLS_LABEL)
		execAndFlushPendingEvents[testContainer.classesForControls += createClassForControls]
		assertAllLabels(treeViewer,
			'''
			«CLASS_FOR_CONTROLS_LABEL»
			«CLASS_FOR_CONTROLS_LABEL»
			'''
		)
		execAndFlushPendingEvents[testContainer.classesForControls += createClassForControls]
		assertAllLabels(treeViewer,
			'''
			«CLASS_FOR_CONTROLS_LABEL»
			«CLASS_FOR_CONTROLS_LABEL»
			«CLASS_FOR_CONTROLS_LABEL»
			'''
		)
	}

	@Test
	def void testRootElementsAreRefreshedWhenExistingElementIsRemoved() {
		fillTestContainer
		val treeViewer = setupTreeViewer(testContainer.eResource,
			getContentProviderWithCustomGetElements())
		assertAllLabels(treeViewer, CLASS_FOR_CONTROLS_LABEL)
		execAndFlushPendingEvents[
			testContainer.classesForControls -= testContainer.classesForControls.head
		]
		assertAllLabels(treeViewer, "")
	}

	@Test
	def void testRootElementsAreRefreshedWhenContentsAreCleared() {
		fillTestContainer
		val treeViewer = setupTreeViewer(testContainer.eResource,
			getContentProviderWithCustomGetElements())
		assertAllLabels(treeViewer, CLASS_FOR_CONTROLS_LABEL)
		execAndFlushPendingEvents[
			testContainer.classesForControls.clear
			true
		]
		assertAllLabels(treeViewer, "")
	}

	@Test
	def void testRootElementsAreRefreshedWhenNewElementsAreAddedAndThenRemoved() {
		fillTestContainer
		val treeViewer = setupTreeViewer(testContainer.eResource,
			getContentProviderWithCustomGetElements())
		assertAllLabels(treeViewer, CLASS_FOR_CONTROLS_LABEL)
		execAndFlushPendingEvents[
			testContainer.classesForControls += createClassForControls
			testContainer.classesForControls += createClassForControls
		]
		assertAllLabels(treeViewer,
			'''
			«CLASS_FOR_CONTROLS_LABEL»
			«CLASS_FOR_CONTROLS_LABEL»
			«CLASS_FOR_CONTROLS_LABEL»
			'''
		)
		execAndFlushPendingEvents[
			testContainer.classesForControls -= testContainer.classesForControls.head
		]
		assertAllLabels(treeViewer,
			'''
			«CLASS_FOR_CONTROLS_LABEL»
			«CLASS_FOR_CONTROLS_LABEL»
			'''
		)
		execAndFlushPendingEvents[
			testContainer.classesForControls -= testContainer.classesForControls.head
		]
		assertAllLabels(treeViewer,
			'''
			«CLASS_FOR_CONTROLS_LABEL»
			'''
		)
	}

	@Test
	def void testElementsForTableViewer() {
		// this tests the default behavior, when getElements
		// is not customized
		fillTestContainer
		val tableViewer = setupTableViewer(testContainer.eResource,
			contentProvider)
		assertTable(tableViewer, TEST_CONTAINER)
	}

	@Test
	def void testCustomElementsForTableViewer() {
		// this tests the default behavior, when getElements
		// is not customized
		fillTestContainer
		val tableViewer = setupTableViewer(testContainer.eResource,
			getContentProviderWithCustomGetElements())
		assertTable(tableViewer, CLASS_FOR_CONTROLS_LABEL)
	}

	@Test
	def void testTableElementsAreRefreshedWhenNewElementsAreAddedAndThenRemoved() {
		fillTestContainer
		val tableViewer = setupTableViewer(testContainer.eResource,
			getContentProviderWithCustomGetElements())
		assertTable(tableViewer, CLASS_FOR_CONTROLS_LABEL)
		execAndFlushPendingEvents[
			testContainer.classesForControls += createClassForControls
			testContainer.classesForControls += createClassForControls
		]
		assertTable(tableViewer,
			'''
			«CLASS_FOR_CONTROLS_LABEL»
			«CLASS_FOR_CONTROLS_LABEL»
			«CLASS_FOR_CONTROLS_LABEL»
			'''
		)
		execAndFlushPendingEvents[
			testContainer.classesForControls -= testContainer.classesForControls.head
		]
		assertTable(tableViewer,
			'''
			«CLASS_FOR_CONTROLS_LABEL»
			«CLASS_FOR_CONTROLS_LABEL»
			'''
		)
		execAndFlushPendingEvents[
			testContainer.classesForControls -= testContainer.classesForControls.head
		]
		assertTable(tableViewer,
			'''
			«CLASS_FOR_CONTROLS_LABEL»
			'''
		)
	}

	@Test
	def void testTableElementsAreRefreshedWhenExistingElementIsRemoved() {
		fillTestContainer
		val tableViewer = setupTableViewer(testContainer.eResource,
			getContentProviderWithCustomGetElements())
		assertTable(tableViewer, CLASS_FOR_CONTROLS_LABEL)
		execAndFlushPendingEvents[
			testContainer.classesForControls -= testContainer.classesForControls.head
		]
		assertTable(tableViewer, "")
	}

	@Test
	def void testTableElementsAreRefreshedWhenContentsAreCleared() {
		fillTestContainer
		val tableViewer = setupTableViewer(testContainer.eResource,
			getContentProviderWithCustomGetElements())
		assertTable(tableViewer, CLASS_FOR_CONTROLS_LABEL)
		execAndFlushPendingEvents[
			testContainer.classesForControls.clear
			true
		]
		assertTable(tableViewer, "")
	}
	
	private def getContentProviderWithCustomGetElements() {
		new ViewerContentProvider() {
			def elements(Resource resource) {
				// don't return classesWithName
				resource.allContents.
					filter(TestContainer).toIterable.
					map[classesForControls].flatten
			}
		}.injectMembers
	}

	def private fillTestContainer() {
		testContainer = createTestContainerInResource => [
			classesForControls += classForControlsInstance
			classesWithName += createClassWithName("Test")
		]
		return testContainer
	}

	def private assertArray(CharSequence expected, Object[] a) {
		expected.toString.assertEquals(
			a.map[labelProvider.getText(it)].join(", ")
		)
	}

	/**
	 * In order to make the tests reliable for viewer refreshing, it is crucial
	 * to use a Resource as input, not an EObject
	 */
	def private setupTreeViewer(Resource resouce, IContentProvider contentProvider) {
		new TreeViewer(shell) => [
			it.contentProvider = contentProvider
			it.labelProvider = labelProvider
			it.input = resouce
		]
	}

	/**
	 * In order to make the tests reliable for viewer refreshing, it is crucial
	 * to use a Resource as input, not an EObject; here we're not interested in columns,
	 * just in the rows, so we set a label provider which is usually not needed for
	 * table viewers (since we have column label providers).
	 */
	def private setupTableViewer(Resource resouce, IContentProvider contentProvider) {
		new TableViewer(shell) => [
			it.contentProvider = contentProvider
			it.labelProvider = labelProvider
			it.input = resouce
		]
	}
}
