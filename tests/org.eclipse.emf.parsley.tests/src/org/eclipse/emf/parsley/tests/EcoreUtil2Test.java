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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.util.EcoreUtil2;
import org.eclipse.jface.viewers.ILabelProvider;
import org.junit.Rule;
import org.junit.Test;

public class EcoreUtil2Test extends AbstractEmfParsleyTest {

	@Rule public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	@Test public void testCopyStateNull() {
		final var state = EcoreUtil2.copyState(null);
		assertEquals(0, state.size());
	}

	@Test public void testCloneDoesNotCopyBidirectional() {
		assertEquals(1, fixtures.getWriter().getBooks().size());
		final var copy = EcoreUtil2.clone(fixtures.getWriter());
		assertEquals(0, copy.getBooks().size());
	}

	@Test public void testManualESet() {
		final var books = fixtures.booksByReflection(fixtures.getWriter());
		assertEquals(1, books.size());
		final var copy = EcoreUtil2.clone(fixtures.getWriter());
		fixtures.addBooksByReflection(copy, books);
		assertEquals(0, fixtures.getWriter().getBooks().size());
		assertEquals(1, copy.getBooks().size());
		// manual eSet preserves bidirectionality
		assertSame(copy, fixtures.getBook().getAuthor());
	}

	@Test public void testCopyState() {
		final var state = EcoreUtil2.copyState(fixtures.getWriter());

		fixtures.getWriter().getBooks().clear();
		assertBooks(fixtures.getWriter(), 0);
		assertNull(fixtures.getBook().getAuthor());

		// this brings the book back to the author
		state.copyStateTo(fixtures.getWriter());
		assertBooks(fixtures.getWriter(), 1);
		assertSame(fixtures.getWriter(), fixtures.getBook().getAuthor());
	}

	@Test public void testReadOnlyCase() {
		assertEquals(1, fixtures.getLibrary().getBooks().size());
		final var state = EcoreUtil2.copyState(fixtures.getLibrary());
		assertNull(state.get(EXTLibraryPackage.eINSTANCE.getLibrary_Books()));
	}

	@Test public void testDerivedCase() {
		fixtures.getClassForControlsInstance().setDerivedStringFeature("test");
		final var state = EcoreUtil2.copyState(fixtures.getClassForControlsInstance());
		assertNull(state.get(fixtures.getTestPackage().getClassForControls_DerivedStringFeature()));
	}

	@Test public void testFeatureMapCase() {
		assertEquals(1, fixtures.getLibrary().getPeople().size());
		final var state = EcoreUtil2.copyState(fixtures.getLibrary());
		state.copyStateTo(fixtures.getLibrary());
		assertEquals(1, fixtures.getLibrary().getPeople().size());
		assertEquals(1, fixtures.getLibrary().getWriters().size());
	}

	@Test public void testGetAllContentsOfTypeFromEObject() {
		fixtures.getTestContainer().getClassesWithName().add(fixtures.createClassWithName("1"));
		fixtures.getTestContainer().getClassesWithName().add(fixtures.createClassWithName("2"));
		final var contained = fixtures.createTestContainer();
		contained.getClassesWithName().add(fixtures.createClassWithName("3"));
		contained.getClassesForControls().add(fixtures.createClassForControls());
		contained.getClassesWithName().add(fixtures.createClassWithName("4"));
		fixtures.getTestContainer().setContained(contained);
		assertList(EcoreUtil2.getAllContentsOfType(fixtures.getTestContainer(), fixtures.getTestPackage().getClassWithName()),
			"Class With Name 1, Class With Name 2, Class With Name 3, Class With Name 4");
		assertList(EcoreUtil2.getAllContentsOfType(fixtures.getTestContainer().getContained(), ClassWithName.class),
			"Class With Name 3, Class With Name 4");
	}

	@Test public void testGetAllContentsOfTypeFromEObjectWithNoMatching() {
		fixtures.getTestContainer().getClassesForControls().add(fixtures.createClassForControls());
		assertList(EcoreUtil2.getAllContentsOfType(fixtures.getTestContainer(), fixtures.getTestPackage().getClassWithName()),
			"");
	}

	@Test public void testGetAllContentsOfTypeFromResource() {
		fixtures.getTestContainer().getClassesWithName().add(fixtures.createClassWithName("1"));
		fixtures.getTestContainer().getClassesWithName().add(fixtures.createClassWithName("2"));
		final var contained = fixtures.createTestContainer();
		contained.getClassesWithName().add(fixtures.createClassWithName("3"));
		contained.getClassesWithName().add(fixtures.createClassWithName("4"));
		fixtures.getTestContainer().setContained(contained);
		final var resource = fixtures.createResource();
		resource.getContents().add(fixtures.getTestContainer());
		final var anotherContainer = fixtures.createTestContainer();
		anotherContainer.getClassesWithName().add(fixtures.createClassWithName("5"));
		anotherContainer.getClassesForControls().add(fixtures.createClassForControls());
		anotherContainer.getClassesWithName().add(fixtures.createClassWithName("6"));
		resource.getContents().add(anotherContainer);
		assertList(EcoreUtil2.getAllContentsOfType(resource, fixtures.getTestPackage().getClassWithName()),
			"Class With Name 1, Class With Name 2, Class With Name 3, Class With Name 4, Class With Name 5, Class With Name 6");
	}

	@Test public void testGetAllContentsOfTypeFromEmptyResource() {
		final var resource = fixtures.createResource();
		assertList(EcoreUtil2.getAllContentsOfType(resource, fixtures.getTestPackage().getClassWithName()),
			"");
	}

	@Test public void testSafeEGetWithTheRightFeature() {
		fixtures.getClassForControlsInstance().setStringFeature("test");
		assertEquals("test",
			EcoreUtil2.safeEGet(fixtures.getClassForControlsInstance(), fixtures.getTestPackage().getClassForControls_StringFeature())
		);
	}

	@Test public void testSafeEGetWithTheWrongFeature() {
		// the specified feature does not belong to the eclass
		assertNull(EcoreUtil2.safeEGet(fixtures.getClassForControlsInstance(), fixtures.getTestPackage().getClassWithName_Name()));
	}

	private void assertList(List<?> list, CharSequence expected) {
		final var labelProvider = getOrCreateInjector().getInstance(ILabelProvider.class);
		assertEquals(expected.toString(),
			list.stream().map(it -> labelProvider.getText(it)).collect(Collectors.joining(", "))
		);
	}

	private void assertBooks(org.eclipse.emf.parsley.examples.library.Writer writer, int expected) {
		assertEquals(expected, writer.getBooks().size());
	}
}
