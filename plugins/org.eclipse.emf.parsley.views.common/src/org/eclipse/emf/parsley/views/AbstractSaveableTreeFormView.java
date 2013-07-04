package org.eclipse.emf.parsley.views;


import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.factories.TreeFormFactory;
import org.eclipse.emf.parsley.widgets.TreeFormComposite;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

public abstract class AbstractSaveableTreeFormView extends AbstractSaveableView
		{

	@Inject
	protected TreeFormFactory treeFormFactory;

	protected TreeFormComposite treeFormComposite;

	protected Object getContents(Resource resource) {
		return resource;
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		treeFormComposite = treeFormFactory
				.createTreeFormMasterDetailComposite(parent, SWT.BORDER);

		treeFormComposite.update(getContents(getResource()));

		addContextMenu(treeFormComposite.getViewer());
	}

	@Override
	public void setFocus() {
		treeFormComposite.setFocus();
	}

	public StructuredViewer getViewer() {
		return treeFormComposite.getViewer();
	}

}
