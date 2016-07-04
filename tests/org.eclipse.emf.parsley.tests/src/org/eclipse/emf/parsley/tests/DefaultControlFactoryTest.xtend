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
package org.eclipse.emf.parsley.tests

import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.parsley.composite.DialogControlFactory
import org.eclipse.swt.widgets.Text
import org.eclipse.emf.parsley.tests.models.testmodels.BaseClass
import org.junit.Test

/**
 * Tests control creation without using polymorphic dispatch by default.
 * 
 * @author Lorenzo Bettini
 */
class DefaultControlFactoryTest extends DialogControlFactoryTest {

	def override protected createAndInitializeFactory() {
		new DialogControlFactory() {
			override create(EStructuralFeature feature) {
				super.createDefaultControl(feature)
			}
		} => [
			initialize(classForControlsInstance)
		]
	}

	@Test
	def void testCustomControlPolymorphicUsingDefaultCreate() {
		val factory = new DialogControlFactory {
			def control_BaseClass_baseClassFeature(BaseClass e) {
				createDefaultControl(testPackage.baseClass_BaseClassFeature) => [
					(it as Text).text = "Foo"
				]
			}
		} => [initialize(testFactory.createBaseClass)]
		val control = factory.createControl(testPackage.baseClass_BaseClassFeature)
		control.assertTextEditable(true)
		control.assertText("Foo")
	}
}
