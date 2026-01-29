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
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory;
import org.eclipse.emf.parsley.util.EmfParsleyUtil;
import org.junit.Test;

import com.google.common.collect.Iterables;

/**
 * @author Lorenzo Bettini
 * 
 */
public class EmfParsleyUtilTest {
	
	@Test
	public void testEnsureCollectionGivenNull() {
		assertNotNull(EmfParsleyUtil.ensureCollection(null));
	}

	@Test
	public void testEnsureCollectionGivenSingleElement() {
		assertEquals(1, EmfParsleyUtil.ensureCollection(Integer.valueOf(0)).size());
	}

	@Test
	public void testEnsureCollectionGivenArray() {
		assertEquals(2, EmfParsleyUtil.ensureCollection(createArray())
				.size());
	}

	@Test
	public void testEnsureCollectionGivenIterable() {
		assertEquals(2, EmfParsleyUtil.ensureCollection(createIterable())
				.size());
	}

	@Test
	public void testEnsureCollectionGivenCollection() {
		final var list = new ArrayList<String>();
		list.add("first");
		list.add("second");
		assertEquals(list, EmfParsleyUtil.ensureCollection(list));
	}

	@Test
	public void testEnsureCollectionGivenIterator() {
		final var list = new ArrayList<String>();
		list.add("first");
		list.add("second");
		assertEquals(list.size(),
				EmfParsleyUtil.ensureCollection(list.iterator()).size());
	}

	@Test
	public void testToIntArrayEmpty() {
		final var list = new ArrayList<Integer>();
		assertEquals("",
				Arrays.stream(EmfParsleyUtil.toIntArray(list)).mapToObj(Integer::toString).reduce("", (a, b) -> a + b));
	}

	@Test
	public void testToIntArray() {
		final var list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		assertEquals("1, 2, 3",
				Arrays.stream(EmfParsleyUtil.toIntArray(list)).mapToObj(Integer::toString).reduce((a, b) -> a + ", " + b).orElse(""));
	}

	@Test
	public void testGetEObjectOrNullWithNotEObject() {
		assertNull(EmfParsleyUtil.getEObjectOrNull("test"));
	}

	@Test
	public void testGetEObjectOrNullWithEObject() {
		final Object o = EXTLibraryFactory.eINSTANCE.createBook();
		assertSame(o, EmfParsleyUtil.getEObjectOrNull(o));
	}

	@Test
	public void testCompareNullWithNull() {
		assertEquals(0, EmfParsleyUtil.compareValues(null, null));
	}

	@Test
	public void testCompareComparableWithNull() {
		assertEquals(1, EmfParsleyUtil.compareValues("aaa", null));
	}

	@Test
	public void testCompareNullWithObject() {
		assertEquals(-1, EmfParsleyUtil.compareValues(null, "aaa"));
	}

	@Test
	public void testCompareComparableWithComparable() {
		assertEquals(-1, EmfParsleyUtil.compareValues("aaa", "bbb"));
	}

	@Test
	public void testCompareComparableWithComparableUpperCase() {
		assertEquals(-32, EmfParsleyUtil.compareValues("AAA", "aaa"));
	}

	@Test
	public void testCompareNonComparableWithComparable() {
		assertNotEquals(0, EmfParsleyUtil.compareValues(new Object(), ""));
	}

	@Test
	public void testCompareNonComparableWithNonComparable() {
		assertNotEquals(0, EmfParsleyUtil.compareValues(new Object(), new Object()));
	}

	@Test
	public void testCompareNonComparableWithNull() {
		assertNotEquals(0, EmfParsleyUtil.compareValues(new Object(), null));
	}

	@Test
	public void testCompareNullWithNonComparable() {
		assertNotEquals(0, EmfParsleyUtil.compareValues(null, new Object()));
	}
	
	@Test
	public void testCompareComparableWithNonComparable() {
		assertNotEquals(0, EmfParsleyUtil.compareValues("", new Object()));
	}

	@Test
	public void testCompareWithEObject() {
		assertEquals(0, EmfParsleyUtil.compareValues(null, null));
	}

	protected Object[] createArray() {
		final Object[] arr = { EXTLibraryFactory.eINSTANCE.createBook(), EXTLibraryFactory.eINSTANCE.createBook() };
		return arr;
	}

	protected Iterable<EObject> createIterable() {
		final var library = EXTLibraryFactory.eINSTANCE.createLibrary();
		library.getBooks().add(EXTLibraryFactory.eINSTANCE.createBook());
		library.getWriters().add(EXTLibraryFactory.eINSTANCE.createWriter());
		final Iterable<EObject> iterable = Iterables.<EObject>concat(
				library.getBooks(), library.getWriters());
		return iterable;
	}
}
