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

import java.util.List
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.eclipse.emf.parsley.util.EcoreUtil2
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*
import org.eclipse.jface.viewers.ILabelProvider
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName

class EcoreUtil2Test extends AbstractEmfParsleyTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	new() {
		// just to cover the protected constructor
		new EcoreUtil2() {
			
		}
	}

	@Test def void testCopyStateNull() {
		EcoreUtil2.copyState(null) => [
			assertEquals(0, size)
		]
	}

	@Test def void testCloneDoesNotCopyBidirectional() {
		writer.books.size.assertEquals(1)
		val copy = EcoreUtil2.clone(writer)
		copy.books.size.assertEquals(0)
	}

	@Test def void testManualESet() {
		val books = writer.booksByReflection
		books.size.assertEquals(1)
		val copy = EcoreUtil2.clone(writer)
		copy.addBooksByReflection(books)
		writer.books.size.assertEquals(0)
		copy.books.size.assertEquals(1)
		// manual eSet preserves bidirectionality
		book.author.assertSame(copy)
	}

	@Test def void testCopyState() {
		val state = EcoreUtil2.copyState(writer)

		writer.books.clear
		writer.assertBooks(0)
		book.author.assertNull

		// this brings the book back to the author
		state.copyStateTo(writer)
		writer.assertBooks(1)
		book.author.assertSame(writer)
	}

	@Test def void testReadOnlyCase() {
		1.assertEquals(library.books.size)
		val state = EcoreUtil2.copyState(library)
		state.get(EXTLibraryPackage.eINSTANCE.library_Books).assertNull
	}

	@Test def void testDerivedCase() {
		classForControlsInstance.derivedStringFeature = "test"
		val state = EcoreUtil2.copyState(classForControlsInstance)
		state.get(testPackage.classForControls_DerivedStringFeature).assertNull
	}

	@Test def void testFeatureMapCase() {
		1.assertEquals(library.people.size)
		val state = EcoreUtil2.copyState(library)
		state.copyStateTo(library)
		1.assertEquals(library.people.size)
		1.assertEquals(library.writers.size)
	}

	@Test def void testGetAllContentsOfTypeFromEObject() {
		testContainer.classesWithName += createClassWithName("1")
		testContainer.classesWithName += createClassWithName("2")
		testContainer.contained = createTestContainer => [
			classesWithName += createClassWithName("3")
			classesForControls += createClassForControls
			classesWithName += createClassWithName("4")
		]
		EcoreUtil2.getAllContentsOfType(testContainer, testPackage.classWithName).
			assertList("Class With Name 1, Class With Name 2, Class With Name 3, Class With Name 4")
		EcoreUtil2.getAllContentsOfType(testContainer.contained, ClassWithName).
			assertList("Class With Name 3, Class With Name 4")
	}

	@Test def void testGetAllContentsOfTypeFromEObjectWithNoMatching() {
		testContainer.classesForControls += createClassForControls
		EcoreUtil2.getAllContentsOfType(testContainer, testPackage.classWithName).
			assertList("")
	}

	@Test def void testGetAllContentsOfTypeFromResource() {
		testContainer.classesWithName += createClassWithName("1")
		testContainer.classesWithName += createClassWithName("2")
		testContainer.contained = createTestContainer => [
			classesWithName += createClassWithName("3")
			classesWithName += createClassWithName("4")
		]
		val resource = createResource
		resource.contents += testContainer
		resource.contents += createTestContainer => [
			classesWithName += createClassWithName("5")
			classesForControls += createClassForControls
			classesWithName += createClassWithName("6")
		]
		EcoreUtil2.getAllContentsOfType(resource, testPackage.classWithName).
			assertList("Class With Name 1, Class With Name 2, Class With Name 3, Class With Name 4, Class With Name 5, Class With Name 6")
	}

	@Test def void testGetAllContentsOfTypeFromEmptyResource() {
		val resource = createResource
		EcoreUtil2.getAllContentsOfType(resource, testPackage.classWithName).
			assertList("")
	}
	def private assertList(List<?> list, CharSequence expected) {
		val labelProvider = getOrCreateInjector.getInstance(ILabelProvider)
		expected.toString.assertEquals(
			list.map[labelProvider.getText(it)].join(", ")
		)
	}
}