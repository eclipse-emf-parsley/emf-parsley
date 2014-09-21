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

import org.eclipse.core.databinding.observable.value.IObservableValue
import org.eclipse.emf.databinding.EMFDataBindingContext
import org.eclipse.emf.databinding.EMFProperties
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.edit.domain.EditingDomain
import org.eclipse.emf.parsley.binding.DialogControlFactory
import org.eclipse.emf.parsley.tests.models.testmodels.BaseClass
import org.eclipse.jface.databinding.swt.SWTObservables
import org.eclipse.swt.SWT
import org.junit.Test
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.parsley.binding.ControlObservablePair

class CustomDialogControlFactoryTest extends AbstractControlFactoryTest {
	
	def override protected createResourceSet() {
		new ResourceSetImpl
	}
	
	/**
	 * Test the polymorphic method pattern
	 * 
	 * <pre>
	 * Control control_ClassName_FeatureName(ClassName e)
	 * </pre>
	 */
	@Test
	def void testCustomControlPolymorphic() {
		val factory = new DialogControlFactory {
			def control_BaseClass_baseClassFeature(BaseClass e) {
				createText("Foo")
			}
		} => [initialize(createBaseClassObject)]
		val control = factory.createControl(testPackage.baseClass_BaseClassFeature)
		control.assertTextEditable(true)
		control.assertText("Foo")
	}

	/**
	 * Test the polymorphic method pattern
	 * 
	 * <pre>
	 * Control control_ClassName_FeatureName(EMFDataBindingContext edbc, IObservableValue modelObservableValue)
	 * </pre>
	 * 
	 * The programmer is responsible of creating and returning the Control
	 * after having set the target observable value
	 */
	@Test
	def void testCustomControlWithDatabindingContextPolymorphic() {
		val o1 = createBaseClassObject
		val factory = new DialogControlFactory {
			def control_BaseClass_baseClassFeature(EMFDataBindingContext edbc, IObservableValue modelObservableValue) {
				val text = createText("")
				// by default the editable is true, thus setting it to false
				// gives us evidence that this method was called
				text.editable = false
				val targetObservableValue = SWTObservables.observeText(text, SWT.Modify)
				edbc.bindValue(targetObservableValue, modelObservableValue, null, null);
				return text
			}
		} => [initialize(o1)]
		val control = factory.createControl(testPackage.baseClass_BaseClassFeature)
		control.assertTextEditable(false)
		control.assertText("")
		o1.baseClassFeature = "Foo"
		control.assertText("Foo")
	}

	/**
	 * Test the polymorphic method pattern
	 * 
	 * <pre>
	 * ControlObservablePair control_ClassName_FeatureName(EStructuralFeature)
	 * </pre>
	 * 
	 * The programmer is responsible of creating and returning the Control
	 * and the target observable value into a ControlObservablePair
	 */
	@Test
	def void testCustomControlWithControlObservablePair() {
		val o1 = createBaseClassObject
		val factory = new DialogControlFactory {
			def control_BaseClass_baseClassFeature(EStructuralFeature f) {
				val text = createText("")
				// by default the editable is true, thus setting it to false
				// gives us evidence that this method was called
				text.editable = false
				return new ControlObservablePair(text, SWTObservables.observeText(text, SWT.Modify))
			}
		} => [initialize(o1)]
		val control = factory.createControl(testPackage.baseClass_BaseClassFeature)
		control.assertTextEditable(false)
		control.assertText("")
		o1.baseClassFeature = "Foo"
		control.assertText("Foo")
	}

	/**
	 * As above, but for a multi feature
	 */
	@Test
	def void testCustomControlWithControlObservablePairForMulti() {
		val o1 = createBaseClassObject
		o1.baseMultiReferenceFeature += createClassWithName("Foo")
		val factory = new DialogControlFactory {
			def control_BaseClass_baseMultiReferenceFeature(EStructuralFeature f) {
				val text = createText("")
				return new ControlObservablePair(text, SWTObservables.observeText(text, SWT.Modify))
			}
		} => [initialize(o1)]
		// we create a Text for a multi feature so the text will stay empty anyway
		// but we test that our custom method is actually polymorphicall invoked
		val control = factory.createControl(testPackage.baseClass_BaseMultiReferenceFeature)
		control.assertTextEditable(true)
		control.assertText("")
		o1.baseMultiReferenceFeature += createClassWithName("Bar")
		control.assertText("")
	}

	/**
	 * Test the polymorphic method pattern
	 * 
	 * <pre>
	 * observe_ClassName_FeatureName(EditingDomain, ClassName)
	 * </pre>
	 * 
	 * The programmer can customize which feature of which object to observe.
	 * In this test we create a Text on an EObject for a feature but we
	 * observe the same feature on another EObject (of the same type).
	 */
	@Test
	def void testCustomObservePolymorphic() {
		val o1 = createBaseClassObject
		val o2 = createBaseClassObject
		
		val factory = new DialogControlFactory {
			def observe_BaseClass_baseClassFeature(EditingDomain d, BaseClass e) {
				// observe the change for the feature on the second object
				EMFProperties.value(testPackage.baseClass_BaseClassFeature).observe(o2)
			}
		} => [initialize(o1)]
		val control = factory.createControl(testPackage.baseClass_BaseClassFeature)
		control.assertTextEditable(true)
		// the Text changes when the second object changes,
		// although the Text was created on the first object
		control.assertText("")
		o1.baseClassFeature = "Foo"
		control.assertText("")
		o2.baseClassFeature = "Foo"
		control.assertText("Foo")
	}

	def private createBaseClassObject() {
		testFactory.createBaseClass
	}
}