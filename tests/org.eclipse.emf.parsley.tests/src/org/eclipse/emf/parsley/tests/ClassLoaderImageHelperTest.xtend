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

import org.eclipse.emf.parsley.EmfParsleyActivator
import org.eclipse.emf.parsley.tests.util.LogAppenderTestRule
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*

class ClassLoaderImageHelperTest extends AbstractImageBasedTest {
	
	@Rule public val LogAppenderTestRule logAppender = new LogAppenderTestRule(EmfParsleyActivator);

	@Test def void testGetImageOk() {
		loadTestImage.assertNotNull
	}

	@Test def void testGetImageNonExistent() {
		getImageHelper.getImage("non-existent.png").assertNull
	}
}