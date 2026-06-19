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
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest;
import org.eclipse.emf.parsley.listeners.AsyncCommandStackListenerHelper;
import org.eclipse.emf.parsley.resource.ResourceLoader;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.tests.util.TestableCommandStackListenerClient;
import org.eclipse.emf.parsley.viewers.TableViewerEditingSupport;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.inject.Inject;
import com.google.inject.MembersInjector;

/**
 * This checks that the model is updated only when it makes sense to update it,
 * i.e., if the value in the cell editor is effectively changed and it's different
 * from the original value in the cell editor.
 * 
 * @author Lorenzo Bettini
 */
public class TableViewerEditingSupportTest extends AbstractEmfParsleyShellBasedTest {

	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	@Inject
	private MembersInjector<TableViewerEditingSupport> membersInjector;

	@Inject
	private ViewerFactory viewerFactory;

	@Inject
	private AdapterFactoryEditingDomain editingDomain;

	@Inject
	private ResourceLoader resourceLoader;

	@Inject
	private AsyncCommandStackListenerHelper helper;

	private TestableCommandStackListenerClient client;

	private TestableTableViewer tableViewer;

	private static final String TEST_URI = "http://dummy/My.testmodels";

	private ClassForControls modelInstance;

	private ClassWithName referredInstance1;

	private ClassWithName referredInstance2;

	/**
	 * Make some protected methods public so that we can call them
	 */
	public static class TestableTableViewerEditingSupport extends TableViewerEditingSupport {

		public TestableTableViewerEditingSupport(ColumnViewer viewer, EStructuralFeature eStructuralFeature) {
			super(viewer, eStructuralFeature);
		}

		@Override
		public void initializeCellEditorValue(CellEditor cellEditor, ViewerCell cell) {
			super.initializeCellEditorValue(cellEditor, cell);
		}

		@Override
		public boolean canEdit(Object element) {
			return super.canEdit(element);
		}

		@Override
		public Object getValue(Object element) {
			return super.getValue(element);
		}

		@Override
		public void setValue(Object element, Object value) {
			super.setValue(element, value);
		}

		@Override
		public CellEditor getCellEditor(Object element) {
			return super.getCellEditor(element);
		}

	}

	/**
	 * Make some protected methods public so that we can call them
	 */
	public static class TestableTableViewer extends TableViewer {

		public TestableTableViewer(Composite parent) {
			super(parent);
		}

		@Override
		public org.eclipse.jface.viewers.ViewerRow getViewerRowFromItem(Widget item) {
			return super.getViewerRowFromItem(item);
		}

	}

	@Before
	public void setupEditingSupport() {
		getOrCreateInjector().injectMembers(this);
		tableViewer = new TestableTableViewer(getShell());
		client = new TestableCommandStackListenerClient();
		helper.addCommandStackListener(editingDomain, getShell(), client);
		modelInstance = fixtures.getClassForControlsInstance();
		referredInstance1 = fixtures.createClassWithName("1");
		referredInstance2 = fixtures.createClassWithName("2");
	}

	@Test
	public void testBooleanNewValue() {
		assertUpdatesAfterSimulation(fixtures.getTestPackage().getClassForControls_BooleanFeature(), true, true);
	}

	@Test
	public void testBooleanNewValueEqualsToOriginal() {
		assertUpdatesAfterSimulation(fixtures.getTestPackage().getClassForControls_BooleanFeature(), false, false);
	}

	@Test
	public void testStringFeatureNewValue() {
		assertUpdatesAfterSimulation(fixtures.getTestPackage().getClassForControls_StringFeature(), "a", true);
	}

	@Test
	public void testStringFeatureNewValueEqualsToOriginalDefault() {
		// if a string feature was null in the model the original value in the cell editor
		// is an empty string
		assertUpdatesAfterSimulation(fixtures.getTestPackage().getClassForControls_StringFeature(), "", false);
	}

	@Test
	public void testIntegerFeatureNewValueEqualsToOriginalDefault() {
		// an int feature is set to 0 by default
		assertUpdatesAfterSimulation(fixtures.getTestPackage().getClassForControls_IntFeature(), "0", false);
	}

	@Test
	public void testIntegerFeatureNewValue() {
		assertUpdatesAfterSimulation(fixtures.getTestPackage().getClassForControls_IntFeature(), "1", true);
	}

	@Test
	public void testReferenceFeatureNewValueEqualsToOriginalDefault() {
		// a reference feature is set to null by default
		assertUpdatesAfterSimulation(fixtures.getTestPackage().getClassForControls_ReferenceToClassWithName(), "", false);
	}

	@Test
	public void testReferenceFeatureNewValue() {
		// a reference feature is set to null by default
		assertUpdatesAfterSimulation(fixtures.getTestPackage().getClassForControls_ReferenceToClassWithName(), referredInstance1, true);
	}

	private void assertUpdatesAfterSimulation(EStructuralFeature feature, Object newValue, boolean expectModelChanged) {
		var editingSupport = createEditingSupport(feature);
		var items = tableViewer.getTable().getItems();
		// the table viewer has only one row
		var tableItem = items[0];
		var row = tableViewer.getViewerRowFromItem(tableItem);
		// and only one column, the one for the specified feature
		var cell = row.getCell(0);
		var cellEditor = editingSupport.getCellEditor(modelInstance);
		// this is the inital value in the cell editor
		editingSupport.initializeCellEditorValue(cellEditor, cell);
		// simulates editing
		cellEditor.setValue(newValue);
		// should the model be updated with the cell editor new value?
		editingSupport.setValue(modelInstance, cellEditor.getValue());
		// since we use a command stack listener, notified asynchronously, we must
		// make sure that all async events are dispatched
		flushPendingEvents();
		assertEquals("events: " + client.commandsAffectingResources, expectModelChanged,
			!client.commandsAffectingResources.isEmpty());
		assertTrue(editingSupport.canEdit(modelInstance));
	}

	private TestableTableViewerEditingSupport createEditingSupport(EStructuralFeature feature) {
		var resource = getResourceFromLoader(editingDomain);
		resource.getContents().add(modelInstance);
		resource.getContents().add(referredInstance1);
		resource.getContents().add(referredInstance2);
		var tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn.setLabelProvider(viewerFactory.createTableColumnLabelProvider(feature));
		var editingSupport = new TestableTableViewerEditingSupport(tableViewer, feature);
		membersInjector.injectMembers(editingSupport);
		tableViewerColumn.setEditingSupport(editingSupport);
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setInput(Collections.singletonList(fixtures.getClassForControlsInstance()));
		return editingSupport;
	}

	private Resource getResourceFromLoader(AdapterFactoryEditingDomain e1) {
		var resourceSet = e1.getResourceSet();
		fixtures.setupResouceFactory(resourceSet);

		var response = resourceLoader.getResource(
			e1,
			URI.createURI(TEST_URI)
		);
		return response.getResource();
	}

}
