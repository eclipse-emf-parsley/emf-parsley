/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.editors;


import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.presentation.EcoreEditorPlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.celleditor.AdapterFactoryTreeEditor;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.emf.parsley.resource.LoadResourceResponse;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.widgets.Tree;


/**
 * @author Lorenzo Bettini - initial API and implementation
 */
public class EmfTreeEditor extends EmfAbstractEditor {

	/**
	 * 
	 */
	public EmfTreeEditor() {

	}

	@Override
	public void createPages() {
		// Create a page for the selection tree view.
		URI resourceURI = EditUIUtil.getURI(getEditorInput());

		LoadResourceResponse response = resourceLoader.getResource(
				editingDomain, resourceURI);

		handleProblems(response);

		Tree tree = new Tree(getContainer(), SWT.MULTI);
		TreeViewer emfTreeViewer = viewerFactory.createTreeViewer(tree,
				editingDomain);
		selectionViewer = emfTreeViewer;
		setSelectionOnRoot(selectionViewer);

		new AdapterFactoryTreeEditor(emfTreeViewer.getTree(), adapterFactory);

		createContextMenuFor(selectionViewer);
		
		selectionViewer.addSelectionChangedListener(createSelectionChangedListener());

		int pageIndex = addPage(tree);
		setPageText(pageIndex, getString("_UI_SelectionPage_label"));

		setActivePage(0);

		// Ensures that this editor will only display the page's tab
		// area if there are more than one page
		//
		getContainer().addControlListener(new ControlAdapter() {
			boolean guard = false;

			@Override
			public void controlResized(ControlEvent event) {
				if (!guard) {
					guard = true;
					hideTabs();
					guard = false;
				}
			}
		});

		updateProblemIndication();
	}

	protected void handleProblems(LoadResourceResponse response) {
		Resource resource = response.getResource();
		Exception exception = response.getException();
		Diagnostic diagnostic = analyzeResourceProblems(resource, exception);
		if (diagnostic.getSeverity() != Diagnostic.OK) {
			resourceToDiagnosticMap.put(resource,
					analyzeResourceProblems(resource, exception));
		}
		editingDomain.getResourceSet().eAdapters()
				.add(problemIndicationAdapter);
	}

	/**
	 * This looks up a string in the plugin's plugin.properties file. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static String getString(String key) {
		return EcoreEditorPlugin.INSTANCE.getString(key);
	}

	@Override
	protected boolean showOutlineView() {
		return true;
	}
}
