package org.eclipse.emf.parsley.edit.actionbar;


import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.edit.action.EditingActionManager;
import org.eclipse.emf.parsley.edit.action.EmfActionManager;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredViewer;

import com.google.inject.Inject;

public class TreeActionBarContributor implements IMenuListener,
		ISelectionChangedListener {

	protected EditingDomain editingDomain;

	@Inject
	EmfActionManager emfActionManager;

	@Inject
	EditingActionManager editingActionManager;

	public void initialize(EditingDomain editingDomain) {
		this.editingDomain = editingDomain;
		editingActionManager.createActions();
		editingActionManager.setEditingDomain(editingDomain);
	}

	public void menuAboutToShow(IMenuManager menuManager) {
		menuManager.add(new Separator("edit"));
		emfActionManager.menuAboutToShow(menuManager);
		editingActionManager.menuAboutToShow(menuManager);
	}

	protected SelectionChangedEvent lastSelectionChangedEvent;

	public void selectionChanged(SelectionChangedEvent event) {
		editingActionManager.updateSelection(event.getSelection());
		emfActionManager.updateSelection(event.getSelection(), editingDomain);
	}

	protected StructuredViewer viewer;

    public void setViewerForSelection(StructuredViewer viewer) {
        this.viewer = viewer;
    }

}
