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
