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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.eclipse.core.databinding.Binding;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.internal.databinding.DatabindingValidationUtil;
import org.eclipse.emf.parsley.internal.databinding.EmfValidationTargetToModelUpdateValueStrategy;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyControlBasedTest;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForDefaultValidation;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForValidation;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.util.DatabindingUtil;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.inject.Inject;

/**
 * This verifies that our custom databinding update value strategy does not interfere
 * with model updates.
 * 
 * @author Lorenzo Bettini
 */
public class EmfValidationTargetToModelUpdateValueStrategyTest extends AbstractEmfParsleyControlBasedTest {

	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	@Inject
	private EditingDomain editingDomain;

	@Inject
	private DatabindingValidationUtil databindingValidationUtil;

	private EMFDataBindingContext edbc;

	@Before
	public void setupFields() {
		getOrCreateInjector().injectMembers(this);
		edbc = new EMFDataBindingContext();
	}

	@Test
	public void testUpdateModelWithErrors() {
		// error: notEmpty must be set
		final ClassForDefaultValidation o = fixtures.getTestFactory().createClassForDefaultValidation();
		final Text text = new Text(getShell(), SWT.NONE);
		initializeDatabindingText(o, text, fixtures.getTestPackage().getClassForDefaultValidation_NotEmpty());
		assertText(text, "");
		o.setNotEmpty("test");
		assertText(text, "test");
	}

	@Test
	public void testUpdateModelWithIssuesNotErrors() {
		final ClassForValidation o = fixtures.getTestFactory().createClassForValidation();
		o.setNotEmpty("a"); // this issues a warning
		final Text text = new Text(getShell(), SWT.NONE);
		initializeDatabindingText(o, text, fixtures.getTestPackage().getClassForValidation_NotEmpty());
		assertText(text, "a");
		o.setNotEmpty("test");
		assertText(text, "test");
	}

	@Test
	public void testUpdateTargetWithStringUpdatesModel() {
		final ClassForDefaultValidation o = fixtures.getTestFactory().createClassForDefaultValidation();
		final Text text = new Text(getShell(), SWT.NONE);
		initializeDatabindingText(o, text, fixtures.getTestPackage().getClassForDefaultValidation_NotEmpty());
		assertText(text, "");
		modifyText(text, "test");
		assertEquals("test", o.getNotEmpty());
	}

	@Test
	public void testUpdateTargetWithEmptyStringUpdatesModelWithNull() {
		final ClassForDefaultValidation o = fixtures.getTestFactory().createClassForDefaultValidation();
		o.setNotEmpty("test");
		final Text text = new Text(getShell(), SWT.NONE);
		initializeDatabindingText(o, text, fixtures.getTestPackage().getClassForDefaultValidation_NotEmpty());
		assertText(text, "test");
		modifyText(text, "");
		assertNull(o.getNotEmpty());
	}

	@Test
	public void testUpdateTargetWithValidInteger() {
		final ClassForDefaultValidation o = fixtures.getTestFactory().createClassForDefaultValidation();
		o.setIntegerAttribute(10);
		final Text text = new Text(getShell(), SWT.NONE);
		initializeDatabindingText(o, text, fixtures.getTestPackage().getClassForDefaultValidation_IntegerAttribute());
		assertText(text, "10");
		modifyText(text, "100");
		assertEquals(100, o.getIntegerAttribute());
	}

	@Test
	public void testUpdateTargetWithNonValidInteger() {
		final ClassForDefaultValidation o = fixtures.getTestFactory().createClassForDefaultValidation();
		o.setIntegerAttribute(10);
		final Text text = new Text(getShell(), SWT.NONE);
		initializeDatabindingText(o, text, fixtures.getTestPackage().getClassForDefaultValidation_IntegerAttribute());
		assertText(text, "10");
		modifyText(text, "Z");
		assertEquals(10, o.getIntegerAttribute());
	}

	@Test
	public void testUpdateTargetWithReference() {
		final ClassForDefaultValidation o = fixtures.getTestFactory().createClassForDefaultValidation();
		final ClassWithName classWithName = fixtures.getTestFactory().createClassWithName();
		classWithName.setName("Test");
		o.setNotNullReference(classWithName);
		final Combo control = new Combo(getShell(), SWT.NONE);
		initializeDatabindingCombo(o, control, fixtures.getTestPackage().getClassForDefaultValidation_NotNullReference());
		assertEquals("Test", o.getNotNullReference().getName());
	}

	@Test
	public void testWhenConverterIsNull() {
		final EmfValidationTargetToModelUpdateValueStrategy targetToModelUpdateValueStrategy =
			new EmfValidationTargetToModelUpdateValueStrategy(
				null, null, databindingValidationUtil);
		final ClassForDefaultValidation o = fixtures.getTestFactory().createClassForDefaultValidation();
		final Object converted = targetToModelUpdateValueStrategy.convert(o);
		assertSame(o, converted);
	}

	private void initializeDatabindingText(EObject o, Control control, EStructuralFeature feature) {
		final ISWTObservableValue<?> target = DatabindingUtil.observeText(control, SWT.Modify);
		initializeDatabindingInternal(feature, o, target);
	}

	private void initializeDatabindingCombo(EObject o, Control control, EStructuralFeature feature) {
		final ISWTObservableValue<?> target = DatabindingUtil.observeSelection(control);
		initializeDatabindingInternal(feature, o, target);
	}

	@SuppressWarnings("unchecked")
	private Binding initializeDatabindingInternal(EStructuralFeature feature, EObject o, ISWTObservableValue<?> target) {
		final var source = EMFEditProperties.value(editingDomain, feature).observe(o);
		final var targetToModelUpdateValueStrategy =
			new EmfValidationTargetToModelUpdateValueStrategy(
				o, feature, databindingValidationUtil);
		
		return edbc.bindValue(
			target, source, targetToModelUpdateValueStrategy,
				null);
	}
}
