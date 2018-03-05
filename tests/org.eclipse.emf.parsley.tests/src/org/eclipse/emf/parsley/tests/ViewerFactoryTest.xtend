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

import com.google.inject.Injector
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.edit.domain.EditingDomain
import org.eclipse.emf.parsley.edit.domain.SingletonAdapterFactoryEditingDomainProvider
import org.eclipse.swt.SWT
import org.junit.Test

import static extension org.junit.Assert.*

class ViewerFactoryTest extends AbstractViewerTest {

	var Injector injector

	val static LOCAL_EMPTY_RESOURCE_URI = "resources/EmptyResource.xmi"

	override setupViewerStuff() {
		super.setupViewerStuff()
		// make sure we use a @Singleton adapter factory editing domain
		// so that we can setup its resource set
		injector = createInjector(new EmfParsleyGuiceModuleForTesting() {
			override provideAdapterFactoryEditingDomain() {
				SingletonAdapterFactoryEditingDomainProvider
			}
		})
		createViewerFactory(injector)
	}

	@Test def void testInitializeWithUri() {
		val editingDomain = injector.getInstance(EditingDomain)
		editingDomain.resourceSet.setupResouceFactory
		viewerFactory.initialize(treeViewer, createTestResourceURI)
		syncExecVoid[
			1.assertEquals(treeViewer.treeItems.size)
		]
	}

	@Test def void testInitializeWithEditingDomain() {
		val editingDomain = injector.getInstance(EditingDomain)
		// use a resource that is physically present in the file system
		// i.e., something stored in the git repository
		editingDomain.resourceSet.setupResouceFactory.
			getResource(URI.createURI(LOCAL_EMPTY_RESOURCE_URI), true)
		viewerFactory.initialize(treeViewer, editingDomain)
		syncExecVoid[
			1.assertEquals(treeViewer.treeItems.size)
		]
	}

	@Test def void testInitializeWithObject() {
		viewerFactory.initialize(treeViewer, testContainer)
		syncExecVoid[
			(numOfElements * 3).assertEquals(treeViewer.treeItems.size)
		]
	}

	@Test def void testCreateTableViewer() {
		tableViewer = viewerFactory.createTableViewer(shell, SWT.BORDER, testPackage.classWithName)
		tableViewer.input = testContainer
		syncExecVoid[
			numOfElements.assertEquals(tableViewer.table.items.length)
		]
	}

	@Test def void testCreateTreeViewer() {
		treeViewer = viewerFactory.createTreeViewer(shell, SWT.MULTI.bitwiseOr(SWT.H_SCROLL).bitwiseOr(SWT.V_SCROLL).bitwiseOr(SWT.FULL_SELECTION))
		treeViewer.input = testContainer
		syncExecVoid[
			(numOfElements * 3).assertEquals(treeViewer.treeItems.size)
		]
	}

	@Test def void testCreateTreeViewerWithColumnsAndContents() {
		treeViewer = viewerFactory.createTreeViewerWithColumns(shell, testPackage.classForControls, testContainer)
		syncExecVoid[
			// the tree contains all the elements, independently from
			// the eclass for representing the table columns
			(numOfElements * 3).assertEquals(treeViewer.tree.itemCount)
			// the first column is the tree so we skip it
			// the first feature of ClassForControls
			"booleanFeature".assertEquals(treeViewer.tree.columns.get(1).text)
			"booleanObjectFeature".assertEquals(treeViewer.tree.columns.get(2).text)
		]
	}

	@Test def void testCreateTreeViewerWithColumns() {
		treeViewer = viewerFactory.createTreeViewerWithColumns(shell, SWT.NONE, testPackage.classForControls)
		treeViewer.setInput = testContainer
		syncExecVoid[
			// the tree contains all the elements, independently from
			// the eclass for representing the table columns
			(numOfElements * 3).assertEquals(treeViewer.tree.itemCount)
			// the first column is the tree so we skip it
			// the first feature of ClassForControls
			"booleanFeature".assertEquals(treeViewer.tree.columns.get(1).text)
			"booleanObjectFeature".assertEquals(treeViewer.tree.columns.get(2).text)
		]
	}

	@Test def void testBuildColumnsWithFeatures() {
		viewerFactory.buildColumns(
			treeViewer,
			#[testPackage.classForControls_BooleanFeature, testPackage.classForControls_BooleanObjectFeature]
		)
		viewerFactory.initialize(treeViewer, testPackage.classForControls)
		syncExecVoid[
			// the first column is the tree so we skip it
			// custom caption
			"booleanFeature".assertEquals(treeViewer.tree.columns.get(1).text)
			"booleanObjectFeature".assertEquals(treeViewer.tree.columns.get(2).text)
		]
	}

	@Test def void testCreateTableColumnLabelProvider() {
		viewerFactory.createTableColumnLabelProvider(testPackage.classForControls_BooleanObjectFeature)
	}

	@Test def void testCreateTableViewerComparator() {
		viewerFactory.createTableViewerComparator(#[testPackage.classForControls_BooleanObjectFeature])
	}
}
