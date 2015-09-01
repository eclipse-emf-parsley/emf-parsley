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
import org.eclipse.emf.common.notify.AdapterFactory
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.parsley.edit.ui.provider.TableViewerContentProvider
import org.eclipse.emf.parsley.edit.ui.provider.TableViewerContentProviderFactory
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.eclipse.emf.parsley.tests.TableViewerContentProviderFactoryTest.CustomTableViewerContentProvider
import org.eclipse.emf.parsley.tests.models.testmodels.TestContainer
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.eclipse.jface.viewers.ILabelProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static org.eclipse.emf.parsley.tests.TableViewerContentProviderFactoryTest.*

import static extension org.junit.Assert.*

class TableViewerContentProviderFactoryTest extends AbstractEmfParsleyShellBasedTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	val static CLASS_FOR_CONTROLS_LABEL = "Class For Controls"

	@Inject var TableViewerContentProviderFactory contentProviderFactory

	/**
	 * We need this only to render the contents returned by the content provider
	 */
	@Inject var ILabelProvider labelProvider

	static class CustomTableViewerContentProvider extends TableViewerContentProvider {

		@Inject
		new(AdapterFactory adapterFactory) {
			super(adapterFactory)
		}

		def elements(TestContainer e) {
			// don't return classesWithName
			e.classesForControls
		}
	}

	@Before
	def void setupContentProvider() {
		injectMembers(this)
	}

	@Test
	def void testDefaultGetElements() {
		"Class With Name Test".
		assertArray(
			contentProviderFactory.createTableViewerContentProvider(testPackage.classWithName).
				getElements(fillTestContainer)
		)
	}

	@Test
	def void testCustomGetElementsWithEObjectAsType() {
		// this checks that the factory uses possible custom table viewer content provider
		// implementations
		val injector = createInjector(new EmfParsleyGuiceModuleForTesting() {
			
			override bindTableViewerContentProvider() {
				CustomTableViewerContentProvider
			}
			
		})

		// even if we filter with EObject, our custom implementation
		// has the precedence
		CLASS_FOR_CONTROLS_LABEL.
		assertArray(
			injector.getInstance(TableViewerContentProviderFactory).
			createTableViewerContentProvider(EcorePackage.eINSTANCE.EObject).
			getElements(fillTestContainer)
		)
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
}
