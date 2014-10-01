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

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcherExtensions
import org.junit.Test

import static extension org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcherExtensions.*
import static extension org.junit.Assert.*

class PolymorphicDispatcherTest extends EmfParsleyAbstractTest {

	var Object target;
	
	new() {
		// the following is useless... but it's just to have coverage
		// for the protected constructor 
		new PolymorphicDispatcherExtensions() {
			
		}
	}

	@Test
	def void testGivenNoSuchMethodReturnsNull() {
		target = new Object
		null.assertPolymorphicInvoke(
			testPackage.baseClass, testPackage.baseClass_BaseClassFeature
		)
	}

	@Test
	def void testPolymorphicInvokeWithMethodForTheContainingEClass() {
		val expectedText = "BaseClass.baseClassFeature"
		
		target = new Object() {
			def String text_BaseClass_baseClassFeature(EStructuralFeature feature) {
				return expectedText
			}
		}
		
		expectedText.assertPolymorphicInvoke(
			testPackage.baseClass, testPackage.baseClass_BaseClassFeature
		)
	}

	def private assertPolymorphicInvoke(Object expected, EClass eClass, EStructuralFeature feature) {
		expected.assertEquals(polymorphicInvoke(eClass, feature))
	}
	
	def private polymorphicInvoke(EClass eClass, EStructuralFeature feature) {
		target.
			polymorphicInvokeBasedOnFeature(eClass, feature, "text_", feature)
	}
}
