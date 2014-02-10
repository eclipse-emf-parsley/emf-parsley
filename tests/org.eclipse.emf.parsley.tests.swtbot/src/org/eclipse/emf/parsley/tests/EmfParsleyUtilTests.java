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
import static org.junit.Assert.assertNotNull;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.util.EmfParsleyUtil;
import org.junit.Test;

import com.google.common.collect.Iterables;

/**
 * @author Lorenzo Bettini
 * 
 */
public class EmfParsleyUtilTests {
	@Test
	public void testEnsureCollection() {
		assertNotNull(EmfParsleyUtil.ensureCollection(null));
		assertEquals(1, EmfParsleyUtil.ensureCollection(new Integer(0))
				.size());

		assertEquals(2, EmfParsleyUtil.ensureCollection(createIterable())
				.size());
	}

	protected Iterable<EObject> createIterable() {
		Library library = EXTLibraryFactory.eINSTANCE.createLibrary();
		library.getBooks().add(EXTLibraryFactory.eINSTANCE.createBook());
		library.getWriters().add(EXTLibraryFactory.eINSTANCE.createWriter());
		Iterable<EObject> iterable = Iterables.<EObject> concat(
				library.getBooks(), library.getWriters());
		return iterable;
	}
}
