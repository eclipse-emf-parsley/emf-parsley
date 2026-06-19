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
import org.junit.Test;

public class FormControlFactoryTest extends DialogControlFactoryTest {
	
	@Override
	protected FormControlFactory createAndInitializeFactory() {
		FormControlFactory formControlFactory = new FormControlFactory();
		initialize(formControlFactory, classForControlsInstance);
		return formControlFactory;
	}

	@Test
	public void testToolkit() {
		assertNotNull(((FormControlFactory) factory).getToolkit());
	}
}
