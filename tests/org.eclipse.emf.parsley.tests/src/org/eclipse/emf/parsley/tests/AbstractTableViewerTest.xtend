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
import org.eclipse.jface.viewers.TableViewer
import org.eclipse.swt.SWT
import org.junit.Before
import org.eclipse.emf.parsley.viewers.TableViewerFactory
import org.eclipse.emf.parsley.util.EmfParsleyUtil

abstract class AbstractTableViewerTest extends AbstractImageBasedTest {

	var protected TableViewerFactory tableViewerFactory
	
	var protected TableViewer tableViewer

	val protected numOfElements = 10

	@Before
	def void setupTableViewerStuff() {
		tableViewerFactory = getOrCreateInjector.getInstance(TableViewerFactory)
		tableViewer = createTableViewer
		createContents
	}

	def protected buildAndFill(Object contents, EClass eClass) {
		syncExecVoid[
			tableViewerFactory.initialize(
				tableViewer, eClass
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
