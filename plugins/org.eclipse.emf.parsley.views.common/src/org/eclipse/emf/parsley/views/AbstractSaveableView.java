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

public abstract class AbstractSaveableView extends ViewPart implements
		ISaveablePart, IEditingDomainProvider, IMenuListener, IViewerProvider {
	@Inject
	protected Provider<AdapterFactoryEditingDomain> editingDomainProvider;

	@Inject
	protected ResourceLoader resourceLoader;

	@Inject
	protected ResourceSaveManager resourceSaveManager;

	@Inject
//	private TreeActionBarContributor actionBarContributor;
	private WorkbenchActionBarContributor actionBarContributor;

	@Inject
	private ViewerInitializer viewerInitializer;

	private Resource resource;

	private boolean dirty;

	protected AdapterFactoryEditingDomain editingDomain;

	@Override
	public void createPartControl(Composite parent) {
		initializeEditingDomain();

		URI uri = createResourceURI();
		loadResource(uri);
	}

	protected abstract URI createResourceURI();

	protected void loadResource(URI uri) {
		resource = resourceLoader.getResource(editingDomain, uri).getResource();
	}

	protected void initializeEditingDomain() {
		editingDomain = editingDomainProvider.get();
		editingDomain.getCommandStack().addCommandStackListener(
				new CommandStackListener() {
					public void commandStackChanged(final EventObject event) {
						getSite().getWorkbenchWindow().getShell().getDisplay()
								.asyncExec(new Runnable() {
									public void run() {
										// Try to select the affected objects.
										Command mostRecentCommand = EmfCommandsUtil
												.mostRecentCommand(event);

										if (EmfCommandsUtil.affectsResource(
												mostRecentCommand,
												getResource())) {
											mostRecentCommandAffectsResource(mostRecentCommand);
										}

										postCommandStackChanged(mostRecentCommand);
									}

								});
					}
				});
	}

	/**
	 * This is called after mostRecentCommandAffectsResource, so that you can
	 * perform additional custom actions.
	 * 
	 * The default implementation is to select in the viewer the possible new
	 * added child; this will also make context menu work seamlessly (if the
	 * selection stays in the parent element, then it will not obviously change
	 * and the context menu actions will not be recreated and they will be
	 * stale).
	 * 
	 * @param mostRecentCommand
	 */
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

	/**
	 * It is called when the {@link CommandStack} changed and the change
	 * concerns something which is in this {@link Resource}.
	 * 
	 * @param mostRecentCommand
	 */
	protected void mostRecentCommandAffectsResource(Command mostRecentCommand) {
		setDirtyAndFirePropertyChange(true);
	}

	protected void setDirtyAndFirePropertyChange(boolean dirtyState) {
		setDirty(dirtyState);
		firePropertyChange(PROP_DIRTY);
	}

	protected void addContextMenu(StructuredViewer viewer) {
//		viewerInitializer.addContextMenu(viewer, actionBarContributor,
//				editingDomain, this);
		viewerInitializer.addContextMenu(viewer, actionBarContributor,
				editingDomain, this, this);
	}

	protected Resource getResource() {
		return resource;
	}

	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	public void menuAboutToShow(IMenuManager menuManager) {
		actionBarContributor.menuAboutToShow(menuManager);
	}

	public void doSave(IProgressMonitor monitor) {
		try {
			saveResourceAndUpdateDirtyState();
		} catch (IOException e) {
			// TODO Serious log!
			e.printStackTrace();
		}
	}

	protected void saveResourceAndUpdateDirtyState() throws IOException {
		if (resourceSaveManager.save(resource)) {
			setDirtyAndFirePropertyChange(false);
		}
	}

	public boolean isDirty() {
		return dirty;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	public void doSaveAs() {
	}

	public boolean isSaveAsAllowed() {
		return false;
	}

	public boolean isSaveOnCloseNeeded() {
		return false;
	}

}
