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

import javax.inject.Inject
import org.eclipse.emf.common.ui.celleditor.ExtendedComboBoxCellEditor
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor.EDataTypeCellEditor
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.eclipse.emf.parsley.viewers.TableViewerCellEditorFactory
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*

class TableViewerCellEditorFactoryTest extends AbstractEmfParsleyShellBasedTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	@Inject var TableViewerCellEditorFactory cellEditorFactory

	@Before
	def void setupFactory() {
		getOrCreateInjector.injectMembers(this)
	}

	@Test def void testNotEObject() {
		createCellEditor("a string", testPackage.classForControls_BooleanFeature).assertNull
	}

	@Test def void testNoPropertyDescriptor() {
		// we simulate the absence of a property description specifying
		// a feature that is not present in the object's EClass
		createCellEditor(classForControlsInstance, testPackage.testContainer_ClassesForTable).assertNull
	}

	@Test def void testReadOnlyFeature() {
		createCellEditor(classForControlsInstance, testPackage.classForControls_UnchangeableStringFeature).assertNull
	}

	@Test def void testBooleanFeature() {
		createCellEditor(classForControlsInstance, testPackage.classForControls_BooleanFeature).class.assertClass(
			ExtendedComboBoxCellEditor)
	}

	@Test def void testEnumFeature() {
		createCellEditor(classForControlsInstance, testPackage.classForControls_EnumFeature).class.assertClass(
			ExtendedComboBoxCellEditor)
	}

	@Test def void testNonBooleanFeature() {
		createCellEditor(classForControlsInstance, testPackage.classForControls_BigIntegerFeature).class.assertClass(
			EDataTypeCellEditor)
	}

	def private createCellEditor(Object o, EStructuralFeature feature) {
		return cellEditorFactory.createCellEditor(shell, o, feature)
	}

	def private assertClass(Class<?> actual, Class<?> expected) {
		expected.simpleName.assertEquals(actual.simpleName)
	}
}