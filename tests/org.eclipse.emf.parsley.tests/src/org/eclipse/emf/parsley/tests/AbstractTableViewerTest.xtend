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

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.parsley.util.EmfParsleyUtil
import org.eclipse.emf.parsley.viewers.ViewerFactory
import org.eclipse.jface.viewers.TableViewer
import org.eclipse.swt.SWT
import org.junit.Before
import org.eclipse.jface.viewers.ArrayContentProvider

abstract class AbstractTableViewerTest extends AbstractImageBasedTest {

	var protected ViewerFactory viewerFactory
	
	var protected TableViewer tableViewer

	val protected numOfElements = 10

	@Before
	def void setupTableViewerStuff() {
		viewerFactory = getOrCreateInjector.getInstance(ViewerFactory)
		tableViewer = createTableViewer
		createContents
	}

	def protected buildAndFill(Object contents, EClass eClass) {
		syncExecVoid[
			viewerFactory.buildColumns(
				tableViewer, eClass, new ArrayContentProvider
			)
			tableViewer.input = EmfParsleyUtil.ensureCollection(contents)
		]
	}

	def protected createTableViewer() {
		syncExec[
			new TableViewer(shell, 
				SWT.MULTI.bitwiseOr(SWT.H_SCROLL).
				bitwiseOr(SWT.V_SCROLL).
				bitwiseOr(SWT.FULL_SELECTION))
		]
	}

	def protected createContents() {
		for (i:0..<numOfElements) {
			testContainer.classesWithName += createClassWithName("name "+i)
			testContainer.classesForControls += createClassForControls
			testContainer.classesForTable += createClassForTable("name "+i)
		}
	}	
}
