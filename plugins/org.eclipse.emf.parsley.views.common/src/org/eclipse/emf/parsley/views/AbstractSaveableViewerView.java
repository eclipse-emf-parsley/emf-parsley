package org.eclipse.emf.parsley.views;


import java.io.IOException;
import java.util.Collection;
import java.util.EventObject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.parsley.edit.ResourceSaveManager;
import org.eclipse.emf.parsley.edit.actionbar.WorkbenchActionBarContributor;
import org.eclipse.emf.parsley.resource.ResourceLoader;
import org.eclipse.emf.parsley.util.EmfCommandsUtil;
import org.eclipse.emf.parsley.viewers.ViewerInitializer;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.part.ViewPart;

import com.google.inject.Inject;
import com.google.inject.Provider;

public abstract class AbstractSaveableViewerView extends AbstractSaveableView implements IMenuListener, IViewerProvider {

	@Inject
//	private TreeActionBarContributor actionBarContributor;
	private WorkbenchActionBarContributor actionBarContributor;

	@Inject
	private ViewerInitializer viewerInitializer;

	@Override
	protected void postCommandStackChanged(Command mostRecentCommand) {
		if (mostRecentCommand != null
				&& mostRecentCommand instanceof CreateChildCommand) {
			setSelectionToViewer(mostRecentCommand.getAffectedObjects());
		}
	}

	protected void setSelectionToViewer(Collection<?> collection) {
		final Collection<?> theSelection = collection;
		if (theSelection != null && !theSelection.isEmpty()) {
			Runnable runnable = new Runnable() {
				public void run() {
					// Try to select the items in the current viewer.
					if (getViewer() != null) {
						getViewer()
								.setSelection(
										new StructuredSelection(
												theSelection.toArray()), true);
					}
				}
			};
			getSite().getShell().getDisplay().asyncExec(runnable);
		}
	}

	public void menuAboutToShow(IMenuManager menuManager) {
		actionBarContributor.menuAboutToShow(menuManager);
	}

	protected void addContextMenu(StructuredViewer viewer) {
//		viewerInitializer.addContextMenu(viewer, actionBarContributor,
//				editingDomain, this);
		viewerInitializer.addContextMenu(viewer, actionBarContributor,
				editingDomain, this, this);
	}

}
