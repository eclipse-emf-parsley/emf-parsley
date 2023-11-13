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

import com.google.inject.MembersInjector
import javax.inject.Inject
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.eclipse.emf.parsley.listeners.AsyncCommandStackListenerHelper
import org.eclipse.emf.parsley.resource.ResourceLoader
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.eclipse.emf.parsley.tests.util.TestableCommandStackListenerClient
import org.eclipse.emf.parsley.viewers.ColumnLabelProviderFactory
import org.eclipse.emf.parsley.viewers.TableViewerEditingSupport
import org.eclipse.jface.viewers.ArrayContentProvider
import org.eclipse.jface.viewers.CellEditor
import org.eclipse.jface.viewers.ColumnViewer
import org.eclipse.jface.viewers.TableViewer
import org.eclipse.jface.viewers.TableViewerColumn
import org.eclipse.jface.viewers.ViewerCell
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Widget
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static org.junit.Assert.*

/**
 * This checks that the model is updated only when it makes sense to update it,
 * i.e., if the value in the cell editor is effectively changed and it's different
 * from the original value in the cell editor.
 * 
 * @author Lorenzo Bettini
 */
class TableViewerEditingSupportTest extends AbstractEmfParsleyShellBasedTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	@Inject var MembersInjector<TableViewerEditingSupport> membersInjector

	@Inject var ColumnLabelProviderFactory columnLabelProviderFactory

	@Inject var AdapterFactoryEditingDomain editingDomain

	@Inject var ResourceLoader resourceLoader

	@Inject var AsyncCommandStackListenerHelper helper

	var TestableCommandStackListenerClient client

	var TestableTableViewer tableViewer

	val static TEST_URI = "http://dummy/My.testmodels"

	var ClassForControls modelInstance

	var ClassWithName referredInstance1

	var ClassWithName referredInstance2

	/**
	 * Make some protected methods public so that we can call them
	 */
	static class TestableTableViewerEditingSupport extends TableViewerEditingSupport {

		new(ColumnViewer viewer, EStructuralFeature eStructuralFeature) {
			super(viewer, eStructuralFeature)
		}

		override initializeCellEditorValue(CellEditor cellEditor, ViewerCell cell) {
			super.initializeCellEditorValue(cellEditor, cell)
		}

		override canEdit(Object element) {
			super.canEdit(element)
		}

		override getValue(Object element) {
			super.getValue(element)
		}

		override setValue(Object element, Object value) {
			super.setValue(element, value)
		}

		override getCellEditor(Object element) {
			super.getCellEditor(element)
		}

	}

	/**
	 * Make some protected methods public so that we can call them
	 */
	static class TestableTableViewer extends TableViewer {

		new(Composite parent) {
			super(parent)
		}

		override getViewerRowFromItem(Widget item) {
			super.getViewerRowFromItem(item)
		}

	}

	@Before
	def void setupEditingSupport() {
		getOrCreateInjector.injectMembers(this)
		tableViewer = new TestableTableViewer(shell)
		client = new TestableCommandStackListenerClient
		helper.addCommandStackListener(editingDomain, shell, client)
		modelInstance = classForControlsInstance
		referredInstance1 = createClassWithName("1")
		referredInstance2 = createClassWithName("2")
	}

	@Test def void testBooleanNewValue() {
		assertUpdatesAfterSimulation(testPackage.classForControls_BooleanFeature, true, true)
	}

	@Test def void testBooleanNewValueEqualsToOriginal() {
		assertUpdatesAfterSimulation(testPackage.classForControls_BooleanFeature, false, false)
	}

	@Test def void testStringFeatureNewValue() {
		assertUpdatesAfterSimulation(testPackage.classForControls_StringFeature, "a", true)
	}

	@Test def void testStringFeatureNewValueEqualsToOriginalDefault() {
		// if a string feature was null in the model the original value in the cell editor
		// is an empty string
		assertUpdatesAfterSimulation(testPackage.classForControls_StringFeature, "", false)
	}

	@Test def void testIntegerFeatureNewValueEqualsToOriginalDefault() {
		// an int feature is set to 0 by default
		assertUpdatesAfterSimulation(testPackage.classForControls_IntFeature, "0", false)
	}

	@Test def void testIntegerFeatureNewValue() {
		assertUpdatesAfterSimulation(testPackage.classForControls_IntFeature, "1", true)
	}

	@Test def void testReferenceFeatureNewValueEqualsToOriginalDefault() {
		// a reference feature is set to null by default
		assertUpdatesAfterSimulation(testPackage.classForControls_ReferenceToClassWithName, "", false)
	}

	@Test def void testReferenceFeatureNewValue() {
		// a reference feature is set to null by default
		assertUpdatesAfterSimulation(testPackage.classForControls_ReferenceToClassWithName, referredInstance1, true)
	}

	def private assertUpdatesAfterSimulation(EStructuralFeature feature, Object newValue, boolean expectModelChanged) {
		val editingSupport = createEditingSupport(feature)
		val items = tableViewer.table.items
		// the table viewer has only one row
		val tableItem = items.head
		val row = tableViewer.getViewerRowFromItem(tableItem)
		// and only one column, the one for the specified feature
		val cell = row.getCell(0)
		val cellEditor = editingSupport.getCellEditor(modelInstance)
		// this is the inital value in the cell editor
		editingSupport.initializeCellEditorValue(cellEditor, cell)
		// simulates editing
		cellEditor.value = newValue
		// should the model be updated with the cell editor new value?
		editingSupport.setValue(modelInstance, cellEditor.value)
		// since we use a command stack listener, notified asynchronously, we must
		// make sure that all async events are dispatched
		flushPendingEvents
		assertEquals("events: " + client.commandsAffectingResources, expectModelChanged,
			!client.commandsAffectingResources.empty)
		assertTrue(editingSupport.canEdit(modelInstance))
	}

	def private createEditingSupport(EStructuralFeature feature) {
		editingDomain.getResourceFromLoader => [
			contents += modelInstance
			contents += referredInstance1
			contents += referredInstance2
		]
		val tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn.setLabelProvider(columnLabelProviderFactory.createColumnLabelProvider(feature));
		new TestableTableViewerEditingSupport(tableViewer, feature) => [
			membersInjector.injectMembers(it)
			tableViewerColumn.editingSupport = it
			tableViewer.contentProvider = new ArrayContentProvider
			tableViewer.input = newArrayList(classForControlsInstance)
		]
	}

	private def getResourceFromLoader(AdapterFactoryEditingDomain e1) {
		val resourceSet = e1.resourceSet
		resourceSet.setupResouceFactory

		val response = resourceLoader.getResource(
			e1,
			URI.createURI(TEST_URI)
		)
		response.resource
	}

}