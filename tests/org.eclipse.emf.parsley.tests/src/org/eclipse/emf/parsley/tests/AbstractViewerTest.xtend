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
import java.util.List
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.parsley.viewers.ViewerFactory
import org.eclipse.jface.viewers.TableViewer
import org.eclipse.jface.viewers.TreeViewer
import org.eclipse.swt.SWT
import org.junit.Before

abstract class AbstractViewerTest extends AbstractImageBasedTest {

	var protected ViewerFactory viewerFactory

	var protected TreeViewer treeViewer

	var protected TableViewer tableViewer

	val protected numOfElements = 10

	@Before
	def void setupViewerStuff() {
		treeViewer = createTreeViewer
		tableViewer = createTableViewer
		createContents
	}

	def protected buildAndFillTreeViewer(Object contents, EClass eClass) {
		buildAndFillTreeViewer(getOrCreateInjector, contents, eClass)
	}

	def protected buildAndFillTreeViewer(Injector injector, Object contents, EClass eClass) {
		createViewerFactory(injector)
		syncExecVoid[
			viewerFactory.buildColumns(treeViewer, eClass)
			viewerFactory.initialize(treeViewer, contents)
		]
	}

	protected def createViewerFactory() {
		createViewerFactory(getOrCreateInjector)
	}

	protected def createViewerFactory(Injector injector) {
		viewerFactory = injector.getInstance(ViewerFactory)
	}

	def protected buildAndFillTreeViewerWithFeatures(List<EStructuralFeature> features, Object contents) {
		buildAndFillTreeViewerWithFeatures(getOrCreateInjector, features, contents)
	}

	def protected buildAndFillTreeViewerWithFeatures(Injector injector, List<EStructuralFeature> features, Object contents) {
		createViewerFactory(injector)
		syncExecVoid[
			viewerFactory.buildColumns(treeViewer, features)
			viewerFactory.initialize(treeViewer, contents)
		]
	}

	def protected createTreeViewer() {
		syncExec[
			new TreeViewer(shell,
				SWT.MULTI.bitwiseOr(SWT.H_SCROLL).bitwiseOr(SWT.V_SCROLL).bitwiseOr(SWT.FULL_SELECTION))
		]
	}

	def protected buildAndFillTableViewer(Object contents, EClass eClass) {
		buildAndFillTableViewer(getOrCreateInjector, contents, eClass)
	}

	def protected buildAndFillTableViewer(Injector injector, Object contents, EClass eClass) {
		viewerFactory = injector.getInstance(ViewerFactory)
		syncExecVoid[
			viewerFactory.buildColumns(tableViewer, eClass)
			tableViewer.input = contents
		]
	}

	def protected createTableViewer() {
		syncExec[
			new TableViewer(shell,
				SWT.MULTI.bitwiseOr(SWT.H_SCROLL).bitwiseOr(SWT.V_SCROLL).bitwiseOr(SWT.FULL_SELECTION))
		]
	}

	def protected createContents() {
		for (i : 0 ..< numOfElements) {
			testContainer.classesWithName += createClassWithName("name " + i)
			testContainer.classesForControls += createClassForControls
			testContainer.classesForTable += createClassForTable("name " + i)
		}
	}
}
