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

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.parsley.viewers.ViewerFactory
import org.eclipse.jface.viewers.TreeViewer
import org.eclipse.swt.SWT
import org.junit.Before
import com.google.inject.Injector
import org.eclipse.emf.ecore.EStructuralFeature
import java.util.List

abstract class AbstractTreeViewerTest extends AbstractImageBasedTest {

	var protected ViewerFactory viewerFactory

	var protected TreeViewer treeViewer

	val protected numOfElements = 10

	@Before
	def void setupViewerStuff() {
		treeViewer = createTreeViewer
		createContents
	}

	def protected buildAndFill(Object contents, EClass eClass) {
		buildAndFill(getOrCreateInjector, contents, eClass)
	}

	def protected buildAndFill(Injector injector, Object contents, EClass eClass) {
		viewerFactory = injector.getInstance(ViewerFactory)
		syncExecVoid[
			viewerFactory.buildColumns(treeViewer, eClass)
			viewerFactory.initialize(treeViewer, contents)
		]
	}

	def protected buildAndFillWithFeatures(List<EStructuralFeature> features, Object contents) {
		buildAndFillWithFeatures(getOrCreateInjector, features, contents)
	}

	def protected buildAndFillWithFeatures(Injector injector, List<EStructuralFeature> features, Object contents) {
		viewerFactory = injector.getInstance(ViewerFactory)
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

	def protected createContents() {
		for (i : 0 ..< numOfElements) {
			testContainer.classesWithName += createClassWithName("name " + i)
			testContainer.classesForControls += createClassForControls
			testContainer.classesForTable += createClassForTable("name " + i)
		}
	}
}
