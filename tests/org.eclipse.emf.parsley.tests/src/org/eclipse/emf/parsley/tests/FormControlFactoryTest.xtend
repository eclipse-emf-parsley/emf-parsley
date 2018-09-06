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

import com.google.inject.Inject
import org.eclipse.emf.parsley.composite.FormControlFactory
import org.eclipse.emf.parsley.inject.parameters.CompositeParameter
import org.eclipse.emf.parsley.inject.parameters.EObjectParameter
import org.eclipse.emf.parsley.inject.parameters.FormToolkitParameter
import org.junit.Test

import static extension org.junit.Assert.*

class FormControlFactoryTest extends DialogControlFactoryTest {

	private static class CustomFormControlFactory extends FormControlFactory {

		@Inject
		new(CompositeParameter compositeParameter, EObjectParameter eObjectParameter,
			FormToolkitParameter formToolkitParameter) {
			super(compositeParameter, eObjectParameter, formToolkitParameter)
		}

		// make it available for tests
		override getFormToolkit() {
			super.getFormToolkit()
		}

	}

	override protected createAndInitializeFactory() {
		new CustomFormControlFactory(compositeParameter, getEObjectParameter(classForControlsInstance),
			new FormToolkitParameter(formToolkit)).injectMembers => [
			// shell must be visibile since we need to check visibility of some controls
			getShell().open();
		]
	}

	@Test def void testToolkit() {
		(factory as CustomFormControlFactory).formToolkit.assertNotNull
	}
}
