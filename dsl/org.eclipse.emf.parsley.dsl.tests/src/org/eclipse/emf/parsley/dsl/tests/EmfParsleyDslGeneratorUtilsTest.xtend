/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.tests

import org.junit.Test

import static extension org.junit.Assert.*
import org.eclipse.emf.parsley.dsl.jvmmodel.EmfParsleyDslGeneratorUtils

class EmfParsleyDslGeneratorUtilsTest {

	extension EmfParsleyDslGeneratorUtils utils = new EmfParsleyDslGeneratorUtils

	@Test
	def testGetPropertyNameForGetterSetterMethod() {
		"foo".assertEquals("getFoo".propertyNameForGetterSetterMethod)
		"foo".assertEquals("foo".propertyNameForGetterSetterMethod)
		"get".assertEquals("get".propertyNameForGetterSetterMethod)
		"geteFoo".assertEquals("geteFoo".propertyNameForGetterSetterMethod)
		
		"foo".assertEquals("isFoo".propertyNameForGetterSetterMethod)
		"is".assertEquals("is".propertyNameForGetterSetterMethod)
		"iseFoo".assertEquals("iseFoo".propertyNameForGetterSetterMethod)
	}

	@Test
	def testShouldGenerateExtensionsWithNull() {
		utils.shouldGenerateExtensions(null).assertFalse
	}
}