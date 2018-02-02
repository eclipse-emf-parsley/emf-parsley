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

import org.eclipse.emf.parsley.composite.FormControlFactory
import org.junit.Test

import static extension org.junit.Assert.*

class FormControlFactoryTest extends DialogControlFactoryTest {

	private static class CustomFormControlFactory extends FormControlFactory {

		// make it available for tests
		override public getFormToolkit() {
			super.getFormToolkit()
		}

	}

	def override protected createAndInitializeFactory() {
		new CustomFormControlFactory() => [
			initialize(classForControlsInstance)
		]
	}

	@Test def void testToolkit() {
		(factory as CustomFormControlFactory).formToolkit.assertNotNull
	}
}
