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

import com.google.inject.Inject
import org.eclipse.core.databinding.Binding
import org.eclipse.emf.databinding.EMFDataBindingContext
import org.eclipse.emf.databinding.edit.EMFEditProperties
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.edit.domain.EditingDomain
import org.eclipse.emf.parsley.internal.databinding.DatabindingValidationUtil
import org.eclipse.emf.parsley.internal.databinding.EmfValidationTargetToModelUpdateValueStrategy
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyControlBasedTest
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.eclipse.emf.parsley.util.DatabindingUtil
import org.eclipse.jface.databinding.swt.ISWTObservableValue
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Control
import org.eclipse.swt.widgets.Text
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*
import org.eclipse.swt.widgets.Combo

/**
 * This verifies that our custom databinding update value strategy does not interfere
 * with model updates.
 * 
 * @author Lorenzo Bettini
 */
class EmfValidationTargetToModelUpdateValueStrategyTest extends AbstractEmfParsleyControlBasedTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	@Inject
	var EditingDomain editingDomain

	@Inject
	var DatabindingValidationUtil databindingValidationUtil

	var EMFDataBindingContext edbc;

	@Before
	def void setupFields() {
		getOrCreateInjector.injectMembers(this)
		edbc = new EMFDataBindingContext();
	}

	@Test
	def void testUpdateModelWithErrors() {
		val o = testFactory.createClassForDefaultValidation // error: notEmpty must be set
		val text = new Text(shell, SWT.NONE)
		initializeDatabindingText(o, text, testPackage.classForDefaultValidation_NotEmpty)
		text.assertText("")
		o.notEmpty = "test"
		text.assertText("test")
	}

	@Test
	def void testUpdateModelWithIssuesNotErrors() {
		val o = testFactory.createClassForValidation => [
			notEmpty = "a" // this issues a warning
		]
		val text = new Text(shell, SWT.NONE)
		initializeDatabindingText(o, text, testPackage.classForValidation_NotEmpty)
		text.assertText("a")
		o.notEmpty = "test"
		text.assertText("test")
	}

	@Test
	def void testUpdateTargetWithStringUpdatesModel() {
		val o = testFactory.createClassForDefaultValidation
		val text = new Text(shell, SWT.NONE)
		initializeDatabindingText(o, text, testPackage.classForDefaultValidation_NotEmpty)
		text.assertText("")
		text.modifyText("test")
		assertEquals("test", o.notEmpty)
	}

	@Test
	def void testUpdateTargetWithEmptyStringUpdatesModelWithNull() {
		val o = testFactory.createClassForDefaultValidation => [
			notEmpty = "test"
		]
		val text = new Text(shell, SWT.NONE)
		initializeDatabindingText(o, text, testPackage.classForDefaultValidation_NotEmpty)
		text.assertText("test")
		text.modifyText("")
		assertNull(o.notEmpty)
	}

	@Test
	def void testUpdateTargetWithValidInteger() {
		val o = testFactory.createClassForDefaultValidation => [
			integerAttribute = 10
		]
		val text = new Text(shell, SWT.NONE)
		initializeDatabindingText(o, text, testPackage.classForDefaultValidation_IntegerAttribute)
		text.assertText("10")
		text.modifyText("100")
		assertEquals(100, o.integerAttribute)
	}

	@Test
	def void testUpdateTargetWithNonValidInteger() {
		val o = testFactory.createClassForDefaultValidation => [
			integerAttribute = 10
		]
		val text = new Text(shell, SWT.NONE)
		initializeDatabindingText(o, text, testPackage.classForDefaultValidation_IntegerAttribute)
		text.assertText("10")
		text.modifyText("Z")
		assertEquals(10, o.integerAttribute)
	}

	@Test
	def void testUpdateTargetWithReference() {
		val o = testFactory.createClassForDefaultValidation => [
			notNullReference = testFactory.createClassWithName => [
				name = "Test"
			]
		]
		val control = new Combo(shell, SWT.NONE)
		initializeDatabindingCombo(o, control, testPackage.classForDefaultValidation_NotNullReference)
		assertEquals("Test", o.notNullReference.name)
	}

	@Test
	def void testWhenConverterIsNull() {
		val targetToModelUpdateValueStrategy =
			new EmfValidationTargetToModelUpdateValueStrategy(
				null, null, databindingValidationUtil);
		val o = testFactory.createClassForDefaultValidation
		val converted = targetToModelUpdateValueStrategy.convert(o)
		o.assertSame(converted)
	}

	def private initializeDatabindingText(EObject o, Control control, EStructuralFeature feature) {
		val target = DatabindingUtil.observeText(control, SWT.Modify)
		initializeDatabindingInternal(feature, o, target);
	}

	def private initializeDatabindingCombo(EObject o, Control control, EStructuralFeature feature) {
		val target = DatabindingUtil.observeSelection(control)
		initializeDatabindingInternal(feature, o, target);
	}

	private def Binding initializeDatabindingInternal(EStructuralFeature feature, EObject o, ISWTObservableValue target) {
		val source = EMFEditProperties.value(editingDomain, feature).observe(o);
		val targetToModelUpdateValueStrategy =
			new EmfValidationTargetToModelUpdateValueStrategy(
				o, feature, databindingValidationUtil);
		
		edbc.bindValue(
			target, source, targetToModelUpdateValueStrategy,
				null)
	}
}