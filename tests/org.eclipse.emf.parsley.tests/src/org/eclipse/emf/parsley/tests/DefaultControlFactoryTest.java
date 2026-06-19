/*******************************************************************************
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.composite.DialogControlFactory;
import org.eclipse.emf.parsley.tests.models.testmodels.BaseClass;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.junit.Test;

/**
 * Tests control creation without using polymorphic dispatch by default.
 * 
 * @author Lorenzo Bettini
 */
public class DefaultControlFactoryTest extends DialogControlFactoryTest {

	@Override
	protected DialogControlFactory createAndInitializeFactory() {
		DialogControlFactory factory = new DialogControlFactory(getCompositeParameter(), getEObjectParameter(classForControlsInstance)) {
			@Override
			public Control create(EStructuralFeature feature) {
				return super.createDefaultControl(feature);
			}
		};
		injectMembers(factory);
		// shell must be visibile since we need to check visibility of some controls
		getShell().open();
		return factory;
	}

	@Test
	public void testCustomControlPolymorphicUsingDefaultCreate() {
		DialogControlFactory factory = new DialogControlFactory(getCompositeParameter(), getEObjectParameter(fixtures.getTestFactory().createBaseClass())) {
			public Control control_BaseClass_baseClassFeature(BaseClass e) {
				Control control = createDefaultControl(fixtures.getTestPackage().getBaseClass_BaseClassFeature());
				((Text) control).setText("Foo");
				return control;
			}
		};
		injectMembers(factory);
		Control control = createControl(factory, fixtures.getTestPackage().getBaseClass_BaseClassFeature());
		assertTextEditable(control, true);
		assertText(control, "Foo");
	}
}
