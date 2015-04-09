package org.eclipse.emf.parsley.tests

import org.eclipse.emf.parsley.EmfParsleyConstants
import org.eclipse.emf.parsley.util.EcoreUtil2
import org.junit.Test

class ProtectedConstructorTest {
	
	new() {
		// the following are useless... but it's just to have coverage
		// for the protected constructor of EmfParsleyConstants
		// and the protected constructor is "required" by sonar...
		new EmfParsleyConstants() {
			
		}
		new EcoreUtil2() {
			
		}
	}
	
	@Test def void emptyTest() {
		
	}
}