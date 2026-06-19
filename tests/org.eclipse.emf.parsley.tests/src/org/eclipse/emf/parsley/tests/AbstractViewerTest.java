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

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.junit.Before;

import com.google.inject.Injector;

public abstract class AbstractViewerTest extends AbstractImageBasedTest {

	protected ViewerFactory viewerFactory;

	protected TreeViewer treeViewer;

	protected TableViewer tableViewer;

	protected final int numOfElements = 10;

	protected org.eclipse.emf.parsley.tests.models.testmodels.TestContainer testContainer;

	@Before
	public void setupViewerStuff() {
		testContainer = fixtures.getTestContainer();
		treeViewer = createTreeViewer();
		tableViewer = createTableViewer();
		createContents();
	}

	protected void buildAndFillTreeViewer(Object contents, EClass eClass) {
		buildAndFillTreeViewer(getOrCreateInjector(), contents, eClass);
	}

	protected void buildAndFillTreeViewer(Injector injector, Object contents, EClass eClass) {
		createViewerFactory(injector);
		syncExecVoid(() -> {
			viewerFactory.buildColumns(treeViewer, eClass);
			viewerFactory.initialize(treeViewer, contents);
		});
	}

	protected void createViewerFactory() {
		createViewerFactory(getOrCreateInjector());
	}

	protected void createViewerFactory(Injector injector) {
		viewerFactory = injector.getInstance(ViewerFactory.class);
	}

	protected void buildAndFillTreeViewerWithFeatures(List<EStructuralFeature> features, Object contents) {
		buildAndFillTreeViewerWithFeatures(getOrCreateInjector(), features, contents);
	}

	protected void buildAndFillTreeViewerWithFeatures(Injector injector, List<EStructuralFeature> features, Object contents) {
		createViewerFactory(injector);
		syncExecVoid(() -> {
			viewerFactory.buildColumns(treeViewer, features);
			viewerFactory.initialize(treeViewer, contents);
		});
	}

	protected TreeViewer createTreeViewer() {
		return syncExec(() -> 
			new TreeViewer(getShell(),
				SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION)
		);
	}

	protected void buildAndFillTableViewer(Object contents, EClass eClass) {
		buildAndFillTableViewer(getOrCreateInjector(), contents, eClass);
	}

	protected void buildAndFillTableViewer(Injector injector, Object contents, EClass eClass) {
		viewerFactory = injector.getInstance(ViewerFactory.class);
		syncExecVoid(() -> {
			viewerFactory.buildColumns(tableViewer, eClass);
			tableViewer.setInput(contents);
		});
	}

	protected TableViewer createTableViewer() {
		return syncExec(() -> 
			new TableViewer(getShell(),
				SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION)
		);
	}

	protected void createContents() {
		for (int i = 0; i < numOfElements; i++) {
			fixtures.getTestContainer().getClassesWithName().add(fixtures.createClassWithName("name " + i));
			fixtures.getTestContainer().getClassesForControls().add(fixtures.createClassForControls());
			fixtures.getTestContainer().getClassesForTable().add(fixtures.createClassForTable("name " + i));
		}
	}
}
