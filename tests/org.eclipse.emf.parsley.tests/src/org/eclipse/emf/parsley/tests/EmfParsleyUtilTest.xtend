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

import com.google.common.collect.Iterables
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory
import org.eclipse.emf.parsley.util.EmfParsleyUtil
import org.junit.Test

import static extension org.junit.Assert.*

/**
 * @author Lorenzo Bettini
 * 
 */
public class EmfParsleyUtilTest {
	
	new() {
		// the following is useless... but it's just to have coverage
		// for the protected constructor of EmfParsleyConstants
		// and the protected constructor is "required" by sonar...
		new EmfParsleyUtil() {
			
		};
	}
	
	@Test
	def void testEnsureCollectionGivenNull() {
		assertNotNull(EmfParsleyUtil.ensureCollection(null));
	}

	@Test
	def void testEnsureCollectionGivenSingleElement() {
		assertEquals(1, EmfParsleyUtil.ensureCollection(new Integer(0)).size());
	}

	@Test
	def void testEnsureCollectionGivenArray() {
		assertEquals(2, EmfParsleyUtil.ensureCollection(createArray())
				.size());
	}

	@Test
	def void testEnsureCollectionGivenIterable() {
		assertEquals(2, EmfParsleyUtil.ensureCollection(createIterable())
				.size());
	}

	@Test
	def void testEnsureCollectionGivenCollection() {
		val list = newArrayList("first", "second");
		assertEquals(list, EmfParsleyUtil.ensureCollection(list));
	}

	@Test
	def void testEnsureCollectionGivenIterator() {
		val list = newArrayList("first", "second");
		assertEquals(list.size(),
				EmfParsleyUtil.ensureCollection(list.iterator()).size());
	}

	@Test
	def void testToIntArrayEmpty() {
		val list = newArrayList();
		assertEquals("",
				EmfParsleyUtil.toIntArray(list).map[toString].join(""));
	}

	@Test
	def void testToIntArray() {
		val list = newArrayList(1, 2, 3);
		assertEquals("1, 2, 3",
				EmfParsleyUtil.toIntArray(list).map[toString].join(", "));
	}

	@Test
	def void testGetEObjectOrNullWithNotEObject() {
		EmfParsleyUtil.getEObjectOrNull("test").assertNull
	}

	@Test
	def void testGetEObjectOrNullWithEObject() {
		val Object o = EXTLibraryFactory.eINSTANCE.createBook()
		EmfParsleyUtil.getEObjectOrNull(o).assertSame(o)
	}

	def protected Object[] createArray() {
		val Object[] arr = #{ EXTLibraryFactory.eINSTANCE.createBook(), EXTLibraryFactory.eINSTANCE.createBook() };
		return arr;
	}

	def protected Iterable<EObject> createIterable() {
		val library = EXTLibraryFactory.eINSTANCE.createLibrary();
		library.getBooks().add(EXTLibraryFactory.eINSTANCE.createBook());
		library.getWriters().add(EXTLibraryFactory.eINSTANCE.createWriter());
		val iterable = Iterables.<EObject> concat(
				library.getBooks(), library.getWriters());
		return iterable;
	}
}
