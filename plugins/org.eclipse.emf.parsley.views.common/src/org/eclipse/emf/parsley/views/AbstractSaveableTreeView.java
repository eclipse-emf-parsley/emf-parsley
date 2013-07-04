package org.eclipse.emf.parsley.views;


import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.viewers.ViewerInitializer;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

public abstract class AbstractSaveableTreeView extends AbstractSaveableView {

	@Inject
	protected ViewerInitializer viewerInitializer;

	protected TreeViewer treeViewer;

	protected Object getContents(Resource resource) {
		return resource;
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		treeViewer = new TreeViewer(parent);
		viewerInitializer.initialize(treeViewer, getResource());

		addContextMenu(treeViewer);
		
		getSite().setSelectionProvider(treeViewer);
	}

	@Override
	public void setFocus() {
		treeViewer.getTree().setFocus();
	}

	public StructuredViewer getViewer() {
		return treeViewer;
	}

}
