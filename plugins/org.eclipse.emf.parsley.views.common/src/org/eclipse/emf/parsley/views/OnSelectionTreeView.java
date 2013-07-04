/**
 * 
 */
package org.eclipse.emf.parsley.views;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * A View that visualizes the tree of an emf selected resource (it also acts as
 * a selection provider).
 * 
 * @author Lorenzo Bettini
 * 
 */
public class OnSelectionTreeView extends OnSelectionStructuredViewerAbstractView {

	public OnSelectionTreeView() {
	}

	@Override
	protected StructuredViewer createViewer(Composite parent) {
		return new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
	}
}
