/**
 * 
 */
package org.eclipse.emf.parsley.editors;


import java.util.Iterator;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.ETypeParameter;
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
 * @author bettini
 * 
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
		TreeViewer emfTreeViewer = treeViewerFactory.createTreeViewer(tree,
				editingDomain);
		selectionViewer = emfTreeViewer;
		setSelectionOnRoot(selectionViewer);

		updateActionBarContributor();

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

	protected void updateActionBarContributor() {
		if (!editingDomain.getResourceSet().getResources().isEmpty()) {
			for (Iterator<EObject> i = editingDomain.getResourceSet()
					.getResources().get(0).getAllContents(); i.hasNext();) {
				EObject eObject = i.next();
				if (eObject instanceof ETypeParameter
						|| eObject instanceof EGenericType
						&& !((EGenericType) eObject).getETypeArguments()
								.isEmpty()) {
					((EmfActionBarContributor) getActionBarContributor())
							.showGenerics(true);
					break;
				}
			}
		}
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

	protected boolean showOutlineView() {
		return true;
	}
}
