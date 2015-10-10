/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
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
import com.google.inject.Provider
import java.util.ArrayList
import org.eclipse.core.runtime.AssertionFailedException
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.parsley.edit.ui.provider.TableViewerContentProvider
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.eclipse.emf.parsley.tests.models.testmodels.TestContainer
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.eclipse.emf.parsley.tests.util.NonStructuredViewer
import org.eclipse.jface.viewers.ILabelProvider
import org.eclipse.jface.viewers.TableViewer
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*
import org.eclipse.emf.parsley.tests.util.ResourceAndEObject

class TableViewerContentProviderTest extends AbstractEmfParsleyShellBasedTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	val static CLASS_FOR_CONTROLS_LABEL = "Class For Controls"

	val static CLASS_WITH_NAME_TEST = "Class With Name Test"

	@Inject var Provider<TableViewerContentProvider> contentProviderProvider

	/**
	 * We need this only to render the contents returned by the content provider
	 */
	@Inject var ILabelProvider labelProvider

	@Before
	def void setupContentProvider() {
		injectMembers(this)
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
		"Class With Name Test".
		assertArray(
			contentProvider(testPackage.classWithName).getElements(fillTestContainer)
		)
	}

	@Test
	def void testDefaultGetElementsSettingTypeAfterConstruction() {
		val cp = contentProvider
		cp.EClass = testPackage.classWithName
		"Class With Name Test".
		assertArray(cp.getElements(fillTestContainer))
	}

	@Test
	def void testDefaultGetElements2() {
		"Class For Controls".
		assertArray(
			contentProvider(testPackage.classForControls).getElements(fillTestContainer)
		)
	}

	@Test
	def void testDefaultGetElementsFromResource() {
		"Class With Name Test".
		assertArray(
			contentProvider(testPackage.classWithName).getElements(fillTestContainer.eResource)
		)
	}

	@Test
	def void testDefaultEObjectGetElementsFromResource() {
		// EObject as a filter
		"Test Container, Class With Name Test, Class For Controls".
		assertArray(
			contentProvider(EcorePackage.eINSTANCE.EObject).getElements(fillTestContainer.eResource)
		)
	}

	@Test
	def void testDefaultEObjectGetElementsFromContainer() {
		// EObject as a filter
		// the container object is not returned
		"Class With Name Test, Class For Controls".
		assertArray(
			contentProvider(EcorePackage.eINSTANCE.EObject).getElements(fillTestContainer)
		)
	}

	@Test
	def void testCustomGetElements() {
		CLASS_FOR_CONTROLS_LABEL.
		assertArray(
			new TableViewerContentProvider(testPackage.classForControls) {
				def elements(TestContainer e) {
					// don't return classesWithName
					e.classesForControls
				}
			}.injectMembers.
			getElements(fillTestContainer)
		)
	}

	@Test
	def void testCustomGetElementsWithEObjectAsType() {
		// even if we filter with EObject, our custom implementation
		// has the precedence
		CLASS_FOR_CONTROLS_LABEL.
		assertArray(
			new TableViewerContentProvider(EcorePackage.eINSTANCE.EObject) {
				def elements(TestContainer e) {
					// don't return classesWithName
					e.classesForControls
				}
			}.injectMembers.
			getElements(fillTestContainer)
		)
	}

	@Test
	def void testCustomGetElementsIgnoredWithDifferentInput() {
		// we pass resource as input, we customize for the container
		// so our custom implementation is ignored
		// and we filter with EObject so we get all the contents
		"Test Container, Class With Name Test, Class For Controls".
		assertArray(
			new TableViewerContentProvider(EcorePackage.eINSTANCE.EObject) {
				def elements(TestContainer e) {
					// don't return classesWithName
					e.classesForControls
				}
			}.injectMembers.
			getElements(fillTestContainer.eResource)
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

	/**
	 * This only tests elements containing null, then the setup of the viewer
	 * will fail due to the null element in the viewer.
	 */
	@Test(expected=AssertionFailedException)
	def void smokeTestCustomGetElementsContainsNull() {
		testContainer = createTestContainerInResource
		setupTableViewer(testContainer.eResource,
			new TableViewerContentProvider(testPackage.classForControls) {
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
			it.contentProvider = getContentProviderWithCustomGetElements(testPackage.classForControls)
			it.input = testContainer.eResource
		]
		execAndFlushPendingEvents[
			testContainer.classesForControls += createClassForControls
		]
	}

	@Test
	def void testElementsForTableViewer() {
		// this tests the default behavior, when getElements
		// is not customized
		fillTestContainer
		val tableViewer = setupTableViewer(testContainer.eResource,
			testPackage.classWithName)
		assertTable(tableViewer, CLASS_WITH_NAME_TEST)
	}

	@Test
	def void testElementsForTableViewerWithResourceAndEObject() {
		// this tests the case of a Resource which is also an EObject
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=479417
		val resource = new ResourceAndEObject
		resource.contents += createClassWithName("Test")
		val tableViewer = setupTableViewer(resource,
			testPackage.classWithName)
		assertTable(tableViewer, CLASS_WITH_NAME_TEST)
	}

	@Test
	def void testElementsForTableViewerWithInputNotEObject() {
		val tableViewer = setupTableViewer("input",
			testPackage.classWithName)
		assertTable(tableViewer, "")
	}

	@Test
	def void testCustomElementsForTableViewer() {
		// this tests the default behavior, when getElements
		// is not customized
		fillTestContainer
		val tableViewer = setupTableViewer(testContainer.eResource,
			getContentProviderWithCustomGetElements(testPackage.classForControls))
		assertTable(tableViewer, CLASS_FOR_CONTROLS_LABEL)
	}

	@Test
	def void testTableElementsAreRefreshedWhenNewElementsAreAddedAndThenRemoved() {
		fillTestContainer
		val tableViewer = setupTableViewer(testContainer.eResource,
			getContentProviderWithCustomGetElements(testPackage.classForControls))
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
			getContentProviderWithCustomGetElements(testPackage.classForControls))
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
			getContentProviderWithCustomGetElements(testPackage.classForControls))
		assertTable(tableViewer, CLASS_FOR_CONTROLS_LABEL)
		execAndFlushPendingEvents[
			testContainer.classesForControls.clear
			true
		]
		assertTable(tableViewer, "")
	}

	private def getContentProviderWithCustomGetElements(EClass type) {
		new TableViewerContentProvider(type) {
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
	 * to use a Resource as input, not an EObject; here we're not interested in columns,
	 * just in the rows, so we set a label provider which is usually not needed for
	 * table viewers (since we have column label providers).
	 */
	def private setupTableViewer(Resource resource, TableViewerContentProvider contentProvider) {
		new TableViewer(shell) => [
			it.contentProvider = contentProvider
			it.labelProvider = labelProvider
			it.input = resource
		]
	}

	/**
	 * In order to make the tests reliable for viewer refreshing, it is crucial
	 * to use a Resource as input, not an EObject; here we're not interested in columns,
	 * just in the rows, so we set a label provider which is usually not needed for
	 * table viewers (since we have column label providers).
	 */
	def private setupTableViewer(Resource resource, EClass type) {
		new TableViewer(shell) => [
			it.contentProvider = contentProvider(type)
			it.labelProvider = labelProvider
			it.input = resource
		]
	}

	def private setupTableViewer(Object input, EClass type) {
		new TableViewer(shell) => [
			it.contentProvider = contentProvider(type)
			it.labelProvider = labelProvider
			it.input = input
		]
	}

	def private contentProvider() {
		contentProvider(null)
	}

	def private contentProvider(EClass type) {
		contentProviderProvider.get => [
			EClass = type
		]
	}
}
