/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests

import org.eclipse.emf.parsley.EmfParsleyConstants
import org.junit.Test

class ProtectedConstructorTest {
	
	new() {
		// the following are useless... but it's just to have coverage
		// for the protected constructor of EmfParsleyConstants
		// and the protected constructor is "required" by sonar...
		new EmfParsleyConstants() {
			
		}
	}
	
	@Test def void emptyTest() {
		
	}
}