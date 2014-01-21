/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.tests;

import static org.junit.Assert.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage;
import org.eclipse.emf.parsley.examples.library.Library;
import org.junit.Test;

public class EClassTests {

	@Test
	public void testEClass() {
		EClass c = EXTLibraryPackage.Literals.LIBRARY;
		assertEquals(Library.class, c.getInstanceClass());
		assertEquals("org.eclipse.emf.parsley.examples.library.Library",
				c.getInstanceClassName());
	}

}
