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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.eclipse.emf.common.ui.celleditor.ExtendedComboBoxCellEditor;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor.EDataTypeCellEditor;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.viewers.TableViewerCellEditorFactory;
import org.eclipse.jface.viewers.CellEditor;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.inject.Inject;

public class TableViewerCellEditorFactoryTest extends AbstractEmfParsleyShellBasedTest {

	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	@Inject
	private TableViewerCellEditorFactory cellEditorFactory;

	@Before
	public void setupFactory() {
		getOrCreateInjector().injectMembers(this);
	}

	@Test
	public void testNotEObject() {
		assertNull(createCellEditor("a string", fixtures.getTestPackage().getClassForControls_BooleanFeature()));
	}

	@Test
	public void testNoPropertyDescriptor() {
		// we simulate the absence of a property description specifying
		// a feature that is not present in the object's EClass
		assertNull(createCellEditor(fixtures.getClassForControlsInstance(), fixtures.getTestPackage().getTestContainer_ClassesForTable()));
	}

	@Test
	public void testReadOnlyFeature() {
		assertNull(createCellEditor(fixtures.getClassForControlsInstance(), fixtures.getTestPackage().getClassForControls_UnchangeableStringFeature()));
	}

	@Test
	public void testBooleanFeature() {
		assertClassNames(
				createCellEditor(fixtures.getClassForControlsInstance(), fixtures.getTestPackage().getClassForControls_BooleanFeature()).getClass(),
				ExtendedComboBoxCellEditor.class.getSimpleName(),
				"CheckBoxCellEditor"); // this was introduced in EMF 2.14, and it's used by default in EMF 2.14
				// we can't refer to the actual type since it's not present in previous versions
	}

	@Test
	public void testEnumFeature() {
		assertClass(
				createCellEditor(fixtures.getClassForControlsInstance(), fixtures.getTestPackage().getClassForControls_EnumFeature()).getClass(),
				ExtendedComboBoxCellEditor.class);
	}

	@Test
	public void testNonBooleanFeature() {
		assertClass(
				createCellEditor(fixtures.getClassForControlsInstance(), fixtures.getTestPackage().getClassForControls_BigIntegerFeature()).getClass(),
				EDataTypeCellEditor.class);
	}

	private CellEditor createCellEditor(Object o, EStructuralFeature feature) {
		return cellEditorFactory.createCellEditor(getShell(), o, feature);
	}

	private void assertClass(Class<?> actual, Class<?> expected) {
		assertEquals(expected.getSimpleName(), actual.getSimpleName());
	}

	private void assertClassNames(Class<?> actual, String... expected) {
		for (String e : expected) {
			if (e.equals(actual.getSimpleName())) {
				return;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < expected.length; i++) {
			if (i > 0) {
				sb.append(", ");
			}
			sb.append(expected[i]);
		}
		fail(actual.getSimpleName() + " does not match any of " + sb.toString());
	}
}
