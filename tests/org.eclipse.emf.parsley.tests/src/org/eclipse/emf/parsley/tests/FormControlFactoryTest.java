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
package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertNotNull;

import org.eclipse.emf.parsley.composite.FormControlFactory;
import org.eclipse.emf.parsley.inject.parameters.CompositeParameter;
import org.eclipse.emf.parsley.inject.parameters.FormToolkitParameter;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.junit.Test;

import com.google.inject.Inject;

public class FormControlFactoryTest extends DialogControlFactoryTest {

	private static class CustomFormControlFactory extends FormControlFactory {

		@Inject
		public CustomFormControlFactory(CompositeParameter compositeParameter,
				org.eclipse.emf.parsley.inject.parameters.EObjectParameter eObjectParameter,
				FormToolkitParameter formToolkitParameter) {
			super(compositeParameter, eObjectParameter, formToolkitParameter);
		}

		// make it available for tests
		@Override
		public FormToolkit getFormToolkit() {
			return super.getFormToolkit();
		}

	}

	@Override
	protected FormControlFactory createAndInitializeFactory() {
		CustomFormControlFactory customFormControlFactory = new CustomFormControlFactory(
			getCompositeParameter(), getEObjectParameter(classForControlsInstance),
			new FormToolkitParameter(getFormToolkit()));
		injectMembers(customFormControlFactory);
		// shell must be visibile since we need to check visibility of some controls
		getShell().open();
		return customFormControlFactory;
	}

	@Test
	public void testToolkit() {
		assertNotNull(((CustomFormControlFactory) factory).getFormToolkit());
	}
}
