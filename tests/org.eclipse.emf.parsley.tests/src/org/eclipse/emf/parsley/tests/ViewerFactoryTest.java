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

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.edit.domain.SingletonAdapterFactoryEditingDomainProvider;
import org.eclipse.swt.SWT;
import org.junit.Test;

import com.google.inject.Injector;
import com.google.inject.Provider;

public class ViewerFactoryTest extends AbstractViewerTest {

	private Injector injector;

	private static final String LOCAL_EMPTY_RESOURCE_URI = "resources/EmptyResource.xmi";

	@Override
	public void setupViewerStuff() {
		super.setupViewerStuff();
		// make sure we use a @Singleton adapter factory editing domain
		// so that we can setup its resource set
		injector = createInjector(new EmfParsleyGuiceModuleForTesting() {
			@Override
			public Class<? extends Provider<AdapterFactoryEditingDomain>> provideAdapterFactoryEditingDomain() {
				return SingletonAdapterFactoryEditingDomainProvider.class;
			}
		});
		createViewerFactory(injector);
	}

	@Test
	public void testInitializeWithUri() {
		var editingDomain = injector.getInstance(EditingDomain.class);
		fixtures.setupResouceFactory(editingDomain.getResourceSet());
		viewerFactory.initialize(treeViewer, fixtures.createTestResourceURI());
		syncExecVoid(() -> {
			assertEquals(1, treeViewer.getTree().getItems().length);
		});
	}

	@Test
	public void testInitializeWithEditingDomain() {
		var editingDomain = injector.getInstance(EditingDomain.class);
		// use a resource that is physically present in the file system
		// i.e., something stored in the git repository
		var resourceSet = fixtures.setupResouceFactory(editingDomain.getResourceSet());
		resourceSet.getResource(URI.createURI(LOCAL_EMPTY_RESOURCE_URI), true);
		viewerFactory.initialize(treeViewer, editingDomain);
		syncExecVoid(() -> {
			assertEquals(1, treeViewer.getTree().getItems().length);
		});
	}

	@Test
	public void testInitializeWithObject() {
		viewerFactory.initialize(treeViewer, testContainer);
		syncExecVoid(() -> {
			assertEquals(numOfElements * 3, treeViewer.getTree().getItems().length);
		});
	}

	@Test
	public void testCreateTableViewer() {
		tableViewer = viewerFactory.createTableViewer(getShell(), SWT.BORDER, fixtures.getTestPackage().getClassWithName());
		tableViewer.setInput(testContainer);
		syncExecVoid(() -> {
			assertEquals(numOfElements, tableViewer.getTable().getItems().length);
		});
	}

	@Test
	public void testCreateTreeViewerWithColumns() {
		treeViewer = viewerFactory.createTreeViewerWithColumns(getShell(), fixtures.getTestPackage().getClassForControls(), testContainer);
		syncExecVoid(() -> {
			// the tree contains all the elements, independently from
			// the eclass for representing the table columns
			assertEquals(numOfElements * 3, treeViewer.getTree().getItemCount());
			// the first column is the tree so we skip it
			// the first feature of ClassForControls
			assertEquals("booleanFeature", treeViewer.getTree().getColumns()[1].getText());
			assertEquals("booleanObjectFeature", treeViewer.getTree().getColumns()[2].getText());
		});
	}

	@Test
	public void testBuildColumnsWithFeatures() {
		viewerFactory.buildColumns(
			treeViewer,
			Arrays.asList(fixtures.getTestPackage().getClassForControls_BooleanFeature(), fixtures.getTestPackage().getClassForControls_BooleanObjectFeature())
		);
		viewerFactory.initialize(treeViewer, fixtures.getTestPackage().getClassForControls());
		syncExecVoid(() -> {
			// the first column is the tree so we skip it
			// custom caption
			assertEquals("booleanFeature", treeViewer.getTree().getColumns()[1].getText());
			assertEquals("booleanObjectFeature", treeViewer.getTree().getColumns()[2].getText());
		});
	}
}
