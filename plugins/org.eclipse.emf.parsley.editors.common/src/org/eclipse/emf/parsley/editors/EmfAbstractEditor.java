/**
 * <copyright>
 *
 * Copyright (c) 2002, 2013 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Lorenzo Bettini - refactored and adapted for EMF Parsley
 *
 * </copyright>
 *
 * $Id: EmfAbstractEditor.java,v 1.62 2011/05/12 20:21:12 emerks Exp $
 */
package org.eclipse.emf.parsley.editors;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.ui.MarkerHelper;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.ui.dialogs.ResourceDialog;
import org.eclipse.emf.common.ui.editor.ProblemEditorPart;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.presentation.EcoreEditorPlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.util.EditUIMarkerHelper;
import org.eclipse.emf.edit.ui.view.ExtendedPropertySheetPage;
import org.eclipse.emf.parsley.EmfParsleyActivator;
import org.eclipse.emf.parsley.edit.actionbar.WorkbenchActionBarContributor;
import org.eclipse.emf.parsley.edit.ui.dnd.ViewerDragAndDropHelper;
import org.eclipse.emf.parsley.editors.listeners.ResourceDeltaVisitor;
import org.eclipse.emf.parsley.handlers.OutlineSelectionHandler;
import org.eclipse.emf.parsley.internal.editors.listeners.PartListenerAdapter;
import org.eclipse.emf.parsley.internal.editors.util.ProblemIndicationEContentAdapter;
import org.eclipse.emf.parsley.listeners.AsyncCommandStackListenerClient;
import org.eclipse.emf.parsley.listeners.AsyncCommandStackListenerHelper;
import org.eclipse.emf.parsley.listeners.IEditorMouseListener;
import org.eclipse.emf.parsley.menus.ViewerContextMenuHelper;
import org.eclipse.emf.parsley.resource.ResourceLoader;
import org.eclipse.emf.parsley.util.EmfParsleyUiUtil;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.views.contentoutline.ContentOutline;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheet;
import org.eclipse.ui.views.properties.PropertySheetPage;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * This is an example of a Ecore model editor. <!-- begin-user-doc --> <!--
 * end-user-doc -->
 *
 * @generated
 */
public abstract class EmfAbstractEditor extends MultiPageEditorPart implements
		IEditingDomainProvider, ISelectionProvider, IMenuListener,
		IViewerProvider, IGotoMarker, AsyncCommandStackListenerClient {

	protected static final int CONTAINER_Y_SIZE_DELTA = 6;

	/**
	 * This is the one adapter factory used for providing views of the model.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected ComposedAdapterFactory adapterFactory;

	protected EmfEditorContentOutlinePage contentOutlinePage;

	protected boolean saving = false;

	/**
	 * This is the property sheet page. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 *
	 * @generated
	 */
	protected List<PropertySheetPage> propertySheetPages = new ArrayList<>();

	/**
	 * This is the viewer that shadows the selection in the content outline. The
	 * parent relation must be correctly defined for this to work. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected StructuredViewer selectionViewer;

	/**
	 * This keeps track of all the
	 * {@link org.eclipse.jface.viewers.ISelectionChangedListener}s that are
	 * listening to this editor. <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected Collection<ISelectionChangedListener> selectionChangedListeners = new ArrayList<>();

	/**
	 * This keeps track of the selection of the editor as a whole. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected ISelection editorSelection = StructuredSelection.EMPTY;

	/**
	 * The MarkerHelper is responsible for creating workspace resource markers
	 * presented in Eclipse's Problems View. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 *
	 * @generated
	 */
	protected MarkerHelper markerHelper = new EditUIMarkerHelper();

	/**
	 * This listens for when the outline becomes active <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	protected IPartListener partListener = new PartListenerAdapter() {
		@Override
		public void partActivated(IWorkbenchPart p) {
			if (p instanceof ContentOutline) {
				if (((ContentOutline) p).getCurrentPage() == contentOutlinePage) {
					getActionBarContributor().setActiveEditor(EmfAbstractEditor.this);
				}
			} else if (p instanceof PropertySheet) {
				if (propertySheetPages.contains(((PropertySheet)p).getCurrentPage())) {
					getActionBarContributor().setActiveEditor(EmfAbstractEditor.this);
					handleActivate();
				}
			} else if (p == EmfAbstractEditor.this) {
				handleActivate();
			}
		}
	};

	/**
	 * Resources that have been removed since last activation. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected Collection<Resource> removedResources = new ArrayList<>();

	/**
	 * Resources that have been changed since last activation. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected Collection<Resource> changedResources = new ArrayList<>();

	/**
	 * Resources that have been saved. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 *
	 * @generated
	 */
	protected Collection<Resource> savedResources = new ArrayList<>();

	/**
	 * Map to store the diagnostic associated with a resource. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected Map<Resource, Diagnostic> resourceToDiagnosticMap = new LinkedHashMap<>();

	/**
	 * Controls whether the problem indication should be updated. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected boolean updateProblemIndication = true;

	/**
	 * Adapter used to update the problem indication when resources are demanded
	 * loaded. <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	protected EContentAdapter problemIndicationAdapter = new ProblemIndicationEContentAdapter() {
		@Override
		public void notifyChanged(Notification notification) {
			if (notification.getNotifier() instanceof Resource) {
				switch (notification.getFeatureID(Resource.class)) {
				case Resource.RESOURCE__IS_LOADED:
				case Resource.RESOURCE__ERRORS:
				case Resource.RESOURCE__WARNINGS:
					Resource resource = (Resource) notification.getNotifier();
					handleResourceDiagnostic(resource);
					break;
				default:
					break;
				}
			} else {
				super.notifyChanged(notification);
			}
		}
	};

	/**
	 * This listens for workspace changes. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 *
	 * @generated
	 */
	protected IResourceChangeListener resourceChangeListener = EmfAbstractEditor.this::handleIResourceChangeEvent;

	@Inject
	protected ViewerFactory viewerFactory;

	@Inject
	protected Provider<IEditorMouseListener> mouseAdapterProvider;

	@Inject
	protected EmfEditorContentOutlineFactory emfContentOutlineFactory;

	@Inject
	protected OutlineSelectionHandler outlineSelectionHandler;

	/**
	 * This keeps track of the editing domain that is used to track all changes
	 * to the model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Inject
	protected AdapterFactoryEditingDomain editingDomain;

	@Inject
	protected ResourceLoader resourceLoader;

	@Inject
	protected ViewerContextMenuHelper contextMenuHelper;

	@Inject
	protected ViewerDragAndDropHelper dragAndDropHelper;

	@Inject
	private AsyncCommandStackListenerHelper commandStackListenerHelper;

	/**
	 * Handles activation of the editor or it's associated views. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected void handleActivate() {
		// Recompute the read only state.
		//
		if (editingDomain.getResourceToReadOnlyMap() != null) {
			editingDomain.getResourceToReadOnlyMap().clear();

			// Refresh any actions that may become enabled or disabled.
			//
			setSelection(getSelection());
		}

		if (!removedResources.isEmpty()) {
			if (handleDirtyConflict()) {
				getSite().getPage().closeEditor(EmfAbstractEditor.this, false);
			} else {
				removedResources.clear();
				changedResources.clear();
				savedResources.clear();
			}
		} else if (!changedResources.isEmpty()) {
			changedResources.removeAll(savedResources);
			handleChangedResources();
			changedResources.clear();
			savedResources.clear();
		}
	}

	/**
	 * Handles what to do with changed resources on activation. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected void handleChangedResources() {
		// if we are saving we do not want to deal with changed resources
		// resources are being changed because we are saving them
		if (saving) {
			return;
		}

		if (!changedResources.isEmpty()
				&& (!isDirty() || handleDirtyConflict())) {
			if (isDirty()) {
				changedResources.addAll(editingDomain.getResourceSet()
						.getResources());
			}
			editingDomain.getCommandStack().flush();

			updateProblemIndication = false;
			reloadChangedResources();

			if (AdapterFactoryEditingDomain.isStale(editorSelection)) {
				setSelection(StructuredSelection.EMPTY);
			}

			updateProblemIndication = true;
			updateProblemIndication();
		}
	}

	protected void reloadChangedResources() {
		for (Resource resource : changedResources) {
			if (resource.isLoaded()) {
				resource.unload();
				try {
					resource.load(Collections.emptyMap());
				} catch (IOException exception) {
					if (!resourceToDiagnosticMap.containsKey(resource)) {
						resourceToDiagnosticMap
								.put(resource,
										analyzeResourceProblems(resource,
												exception));
					}
				}
			}
		}
	}

	/**
	 * Updates the problems indication with the information described in the
	 * specified diagnostic. <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected void updateProblemIndication() {
		if (updateProblemIndication) {
			BasicDiagnostic diagnostic = createDiagnostic();

			int lastEditorPage = getPageCount() - 1;
			if (lastEditorPage >= 0
					&& getEditor(lastEditorPage) instanceof ProblemEditorPart) {
				((ProblemEditorPart) getEditor(lastEditorPage))
						.setDiagnostic(diagnostic);
				if (diagnostic.getSeverity() != Diagnostic.OK) {
					setActivePage(lastEditorPage);
				}
			} else if (diagnostic.getSeverity() != Diagnostic.OK) {
				ProblemEditorPart problemEditorPart = new ProblemEditorPart();
				problemEditorPart.setDiagnostic(diagnostic);
				problemEditorPart.setMarkerHelper(markerHelper);
				try {
					addPage(++lastEditorPage, problemEditorPart,
							getEditorInput());
					setPageText(lastEditorPage, problemEditorPart.getPartName());
					setActivePage(lastEditorPage);
					showTabs();
				} catch (PartInitException exception) {
					EcoreEditorPlugin.INSTANCE.log(exception);
				}
			}

			updateMarkers(diagnostic);
		}
	}

	protected BasicDiagnostic createDiagnostic() {
		BasicDiagnostic diagnostic = new BasicDiagnostic(Diagnostic.OK,
				"org.eclipse.emf.ecore.editor", 0, null,
				new Object[] { editingDomain.getResourceSet() });
		for (Diagnostic childDiagnostic : resourceToDiagnosticMap.values()) {
			if (childDiagnostic.getSeverity() != Diagnostic.OK) {
				diagnostic.add(childDiagnostic);
			}
		}
		return diagnostic;
	}

	protected void updateMarkers(BasicDiagnostic diagnostic) {
		if (markerHelper.hasMarkers(editingDomain.getResourceSet())) {
			markerHelper.deleteMarkers(editingDomain.getResourceSet());
			if (diagnostic.getSeverity() != Diagnostic.OK) {
				try {
					markerHelper.createMarkers(diagnostic);
				} catch (CoreException exception) {
					EcoreEditorPlugin.INSTANCE.log(exception);
				}
			}
		}
	}

	/**
	 * Shows a dialog that asks if conflicting changes should be discarded. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected boolean handleDirtyConflict() {
		return MessageDialog.openQuestion(getSite().getShell(),
				getString("_UI_FileConflict_label"),
				getString("_WARN_FileConflict"));
	}

	/**
	 * This creates a model editor. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 *
	 * @generated
	 */
	public EmfAbstractEditor() {
		super();
	}

	protected void initializeEditingDomain() {
		adapterFactory = (ComposedAdapterFactory) editingDomain.getAdapterFactory();
		// Add a listener to set the most recent command's affected objects to
		// be the selection of the viewer with focus.
		commandStackListenerHelper.addCommandStackListener(editingDomain, getSite().getWorkbenchWindow().getShell(),
				this);
	}

	@Override
	public void mostRecentCommandAffectsResource(Command mostRecentCommand) {
		firePropertyChange(IEditorPart.PROP_DIRTY);
		if (mostRecentCommand != null) {
			setSelectionToViewer(mostRecentCommand.getAffectedObjects());
		}
	}

	@Override
	public void postCommandStackChanged(Command mostRecentCommand) {
		for (Iterator<PropertySheetPage> i = propertySheetPages.iterator(); i.hasNext();) {
			PropertySheetPage propertySheetPage = i.next();
			if (propertySheetPage.getControl().isDisposed()) {
				i.remove();
			} else {
				propertySheetPage.refresh();
			}
		}
	}

	/**
	 * This sets the selection into whichever viewer is active. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public void setSelectionToViewer(Collection<?> collection) {
		final Collection<?> theSelection = collection;
		// Make sure it's okay.
		//
		if (theSelection != null && !theSelection.isEmpty()) {
			Runnable runnable = () -> {
				// Try to select the items in the current content viewer of
				// the editor.
				//
				if (selectionViewer != null) {
					selectionViewer.setSelection(new StructuredSelection(
							theSelection.toArray()), true);
				}
			};
			getSite().getShell().getDisplay().asyncExec(runnable);
		}
	}

	/**
	 * This returns the editing domain as required by the
	 * {@link IEditingDomainProvider} interface. This is important for
	 * implementing the static methods of {@link AdapterFactoryEditingDomain}
	 * and for supporting {@link org.eclipse.emf.edit.ui.action.CommandAction}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public AdapterFactoryEditingDomain getEditingDomain() {
		return editingDomain;
	}

	protected ISelectionChangedListener createSelectionChangedListener() {
		// This just notifies those things that are affected by the section.
		return selectionChangedEvent -> setSelection(selectionChangedEvent.getSelection());
	}

	public void createContextMenuFor(StructuredViewer viewer) {
		contextMenuHelper.addViewerContextMenu(viewer,
				this, this, getActionBarContributor());
		dragAndDropHelper.addDragAndDrop(viewer, editingDomain);

		IEditorMouseListener listener = getMouseAdapter();
		viewer.getControl().addMouseListener(listener);
	}

	protected IEditorMouseListener getMouseAdapter() {
		return mouseAdapterProvider.get();
	}

	/**
	 * Returns a diagnostic describing the errors and warnings listed in the
	 * resource and the specified exception (if any). <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public Diagnostic analyzeResourceProblems(Resource resource,
			Exception exception) {
		if (!resource.getErrors().isEmpty()
				|| !resource.getWarnings().isEmpty()) {
			BasicDiagnostic basicDiagnostic = new BasicDiagnostic(
					Diagnostic.ERROR,
					"org.eclipse.emf.ecore.editor",
					0,
					getString("_UI_CreateModelError_message", resource.getURI()),
					new Object[] { exception == null ? (Object) resource
							: exception });
			basicDiagnostic.merge(EcoreUtil.computeDiagnostic(resource, true));
			return basicDiagnostic;
		} else if (exception != null) {
			return new BasicDiagnostic(Diagnostic.ERROR,
					"org.eclipse.emf.ecore.editor", 0, getString(
							"_UI_CreateModelError_message", resource.getURI()),
					new Object[] { exception });
		} else {
			return Diagnostic.OK_INSTANCE;
		}
	}

	/**
	 * If there is just one page in the multi-page editor part, this hides the
	 * single tab at the bottom. <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected void hideTabs() {
		if (getPageCount() <= 1) {
			setPageText(0, "");
			if (getContainer() instanceof CTabFolder) {
				((CTabFolder) getContainer()).setTabHeight(1);
				Point point = getContainer().getSize();
				getContainer().setSize(point.x, point.y + CONTAINER_Y_SIZE_DELTA);
			}
		}
	}

	/**
	 * If there is more than one page in the multi-page editor part, this shows
	 * the tabs at the bottom. <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected void showTabs() {
		if (getPageCount() > 1) {
			setPageText(0, getString("_UI_SelectionPage_label"));
			if (getContainer() instanceof CTabFolder) {
				((CTabFolder) getContainer()).setTabHeight(SWT.DEFAULT);
				Point point = getContainer().getSize();
				getContainer().setSize(point.x, point.y - CONTAINER_Y_SIZE_DELTA);
			}
		}
	}

	/**
	 * This is used to track the active viewer. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	protected void pageChange(int pageIndex) {
		super.pageChange(pageIndex);

		if (contentOutlinePage != null) {
			handleContentOutlineSelection(contentOutlinePage.getSelection());
		}
	}

	/**
	 * This is how the framework determines which interfaces we implement. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object getAdapter(Class key) {
		if (key.equals(IContentOutlinePage.class)) {
			return showOutlineView() ? getContentOutlinePage() : null;
		} else if (key.equals(IPropertySheetPage.class)) {
			return getPropertySheetPage();
		} else if (key.equals(IGotoMarker.class)) {
			return this;
		} else {
			return super.getAdapter(key);
		}
	}

	public IContentOutlinePage getContentOutlinePage() {
		if (contentOutlinePage == null) {
			contentOutlinePage = emfContentOutlineFactory.create(this);

			// Listen to selection so that we can handle it is a special way.
			contentOutlinePage
					.addSelectionChangedListener(
						// This ensures that we handle selections correctly.
						event -> handleContentOutlineSelection(event.getSelection()));
		}

		return contentOutlinePage;
	}

	public void setSelectionOnRoot(StructuredViewer viewer) {
		if (!editingDomain.getResourceSet().getResources().isEmpty()) {
			// Select the root object in the view.
			viewer.setSelection(new StructuredSelection(editingDomain
					.getResourceSet().getResources().get(0)), true);
		}
	}

	/**
	 * This accesses a cached version of the property sheet. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public IPropertySheetPage getPropertySheetPage() {
		PropertySheetPage propertySheetPage = new ExtendedPropertySheetPage(editingDomain) {
			@Override
			public void setSelectionToViewer(List<?> selection) {
				EmfAbstractEditor.this.setSelectionToViewer(selection);
				EmfAbstractEditor.this.setFocus();
			}

			@Override
			public void setActionBars(IActionBars actionBars) {
				super.setActionBars(actionBars);
				getActionBarContributor().shareGlobalActions(this, actionBars);
			}
		};
		propertySheetPage.setPropertySourceProvider(new AdapterFactoryContentProvider(adapterFactory));
		propertySheetPages.add(propertySheetPage);

		return propertySheetPage;
	}

	public void handleContentOutlineSelection(ISelection selection) {
		if (outlineSelectionHandler.getSelectionViewer() == null) {
			outlineSelectionHandler.setSelectionViewer(selectionViewer);
		}
		outlineSelectionHandler.handleContentOutlineSelection(selection);
	}

	/**
	 * This is for implementing {@link IEditorPart} and simply tests the command
	 * stack. <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public boolean isDirty() {
		return ((BasicCommandStack) editingDomain.getCommandStack())
				.isSaveNeeded();
	}

	@Override
	public void doSave(IProgressMonitor progressMonitor) {
		// Save only resources that have actually changed.
		final Map<Object, Object> saveOptions = new HashMap<>();
		saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);

		updateProblemIndication = false;
		saving = true;
		try {
			// Do the work within an operation because this is a long running
			// activity that modifies the workbench.
			new ProgressMonitorDialog(getSite().getShell()).run(true, false,
					monitor -> performSave(saveOptions));

			// Refresh the necessary state.
			((BasicCommandStack) editingDomain.getCommandStack()).saveIsDone();
			firePropertyChange(IEditorPart.PROP_DIRTY);
		} catch (InterruptedException exception) {
			// Something went wrong that shouldn't.
			EcoreEditorPlugin.INSTANCE.log(exception);
			// Restore interrupted state...
			Thread.currentThread().interrupt();
		} catch (InvocationTargetException e) {
			// Something went wrong that shouldn't.
			EcoreEditorPlugin.INSTANCE.log(e);
		}
		saving = false;
		updateProblemIndication = true;
		updateProblemIndication();
	}

	/**
	 * This returns whether something has been persisted to the URI of the
	 * specified resource. The implementation uses the URI converter from the
	 * editor's resource set to try to open an input stream. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected boolean isPersisted(Resource resource) {
		boolean result = false;
		try {
			InputStream stream = editingDomain.getResourceSet()
					.getURIConverter().createInputStream(resource.getURI());
			if (stream != null) {
				result = true;
				stream.close();
			}
		} catch (IOException e) {
			EmfParsleyActivator.logError("EmfAbstractEditor.isPersisted", e);
		}
		return result;
	}

	/**
	 * This always returns true because it is not currently supported. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	public static final String ECORE_FILE_EXTENSION = "ecore";
	public static final String EMOF_FILE_EXTENSION = "emof";

	/**
	 * This also changes the editor's input. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public void doSaveAs() {
		new ResourceDialog(getSite().getShell(), null, SWT.NONE) {
			@Override
			protected boolean isSave() {
				return true;
			}

			@Override
			protected boolean processResources() {
				List<URI> uris = getURIs();
				if (!uris.isEmpty()) {
					URI uri = uris.get(0);
					doSaveAs(uri, new URIEditorInput(uri));
					return true;
				} else {
					return false;
				}
			}
		}.open();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected void doSaveAs(URI uri, IEditorInput editorInput) {
		(editingDomain.getResourceSet().getResources().get(0)).setURI(uri);
		setInputWithNotify(editorInput);
		setPartName(editorInput.getName());
		IProgressMonitor progressMonitor = getActionBars()
				.getStatusLineManager() != null ? getActionBars()
				.getStatusLineManager().getProgressMonitor()
				: new NullProgressMonitor();
		doSave(progressMonitor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void gotoMarker(IMarker marker) {
		try {
			if (marker.getType().equals(EValidator.MARKER)) {
				String uriAttribute = marker.getAttribute(
						EValidator.URI_ATTRIBUTE, null);
				if (uriAttribute != null) {
					URI uri = URI.createURI(uriAttribute);
					EObject eObject = editingDomain.getResourceSet()
							.getEObject(uri, true);
					if (eObject != null) {
						setSelectionToViewer(Collections
								.singleton(editingDomain.getWrapper(eObject)));
					}
				}
			}
		} catch (CoreException exception) {
			EcoreEditorPlugin.INSTANCE.log(exception);
		}
	}

	/**
	 * This is called during startup. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 *
	 * @generated
	 */
	@Override
	public void init(IEditorSite site, IEditorInput editorInput) {
		setSite(site);
		setInputWithNotify(editorInput);
		setPartName(editorInput.getName());
		site.setSelectionProvider(this);
		site.getPage().addPartListener(partListener);
		initializeEditingDomain();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(
				resourceChangeListener, IResourceChangeEvent.POST_CHANGE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void setFocus() {
		getControl(getActivePage()).setFocus();
	}

	/**
	 * This implements {@link org.eclipse.jface.viewers.ISelectionProvider}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.add(listener);
	}

	/**
	 * This implements {@link org.eclipse.jface.viewers.ISelectionProvider}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		selectionChangedListeners.remove(listener);
	}

	/**
	 * This implements {@link org.eclipse.jface.viewers.ISelectionProvider} to
	 * return this editor's overall selection. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public ISelection getSelection() {
		return editorSelection;
	}

	/**
	 * This implements {@link org.eclipse.jface.viewers.ISelectionProvider} to
	 * set this editor's overall selection. Calling this result will notify the
	 * listeners. <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void setSelection(ISelection selection) {
		editorSelection = selection;

		for (ISelectionChangedListener listener : selectionChangedListeners) {
			listener.selectionChanged(new SelectionChangedEvent(this, selection));
		}
		setStatusLineManager(selection);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public void setStatusLineManager(ISelection selection) {
		IStatusLineManager statusLineManager = EmfParsleyUiUtil
				.getStatusLineManager();

		if (statusLineManager != null) {
			if (selection instanceof IStructuredSelection) {
				Collection<?> collection = ((IStructuredSelection) selection)
						.toList();
				switch (collection.size()) {
				case 0:
					statusLineManager
							.setMessage(getString("_UI_NoObjectSelected"));
					break;
				case 1:
					String text = new AdapterFactoryItemDelegator(
							adapterFactory).getText(collection.iterator()
							.next());
					statusLineManager.setMessage(getString(
							"_UI_SingleObjectSelected", text));
					break;
				default:
					statusLineManager.setMessage(getString(
							"_UI_MultiObjectSelected",
							Integer.toString(collection.size())));
					break;
				}
			} else {
				statusLineManager.setMessage("");
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

	/**
	 * This looks up a string in plugin.properties, making a substitution. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	private static String getString(String key, Object s1) {
		return EcoreEditorPlugin.INSTANCE.getString(key, new Object[] { s1 });
	}

	/**
	 * This implements {@link org.eclipse.jface.action.IMenuListener} to help
	 * fill the context menus with contributions from the Edit menu. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void menuAboutToShow(IMenuManager menuManager) {
		((IMenuListener) getEditorSite().getActionBarContributor())
				.menuAboutToShow(menuManager);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public WorkbenchActionBarContributor getActionBarContributor() {
		return (WorkbenchActionBarContributor) getEditorSite()
				.getActionBarContributor();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public IActionBars getActionBars() {
		return getActionBarContributor().getActionBars();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public AdapterFactory getAdapterFactory() {
		return adapterFactory;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void dispose() {
		updateProblemIndication = false;

		ResourcesPlugin.getWorkspace().removeResourceChangeListener(
				resourceChangeListener);

		getSite().getPage().removePartListener(partListener);

		adapterFactory.dispose();

		if (getActionBarContributor().getActiveEditor() == this) {
			getActionBarContributor().setActiveEditor(null);
		}

		for (PropertySheetPage propertySheetPage : propertySheetPages) {
			propertySheetPage.dispose();
		}

		if (contentOutlinePage != null) {
			contentOutlinePage.dispose();
		}

		super.dispose();
	}

	/**
	 * Returns whether the outline view should be presented to the user. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected boolean showOutlineView() {
		return false;
	}

	@Override
	public Viewer getViewer() {
		return selectionViewer;
	}

	protected void handleResourceDiagnostic(Resource resource) {
		Diagnostic diagnostic = analyzeResourceProblems(resource, null);
		if (diagnostic.getSeverity() != Diagnostic.OK) {
			resourceToDiagnosticMap.put(resource, diagnostic);
		} else {
			resourceToDiagnosticMap.remove(resource);
		}

		if (updateProblemIndication) {
			getSite().getShell().getDisplay().asyncExec(() -> updateProblemIndication());
		}
	}

	protected void handleIResourceChangeEvent(IResourceChangeEvent event) {
		IResourceDelta delta = event.getDelta();
		try {
			final ResourceDeltaVisitor visitor = new ResourceDeltaVisitor();
			visitor.init(editingDomain.getResourceSet(), savedResources);
			delta.accept(visitor);

			if (!visitor.getRemovedResources().isEmpty()) {
				getSite().getShell().getDisplay().asyncExec(() -> {
					removedResources.addAll(visitor
							.getRemovedResources());
					if (!isDirty()) {
						getSite().getPage().closeEditor(
								EmfAbstractEditor.this, false);
					}
				});
			}

			if (!visitor.getChangedResources().isEmpty()) {
				getSite().getShell().getDisplay().asyncExec(() -> {
					changedResources.addAll(visitor
							.getChangedResources());
					if (getSite().getPage().getActiveEditor() == EmfAbstractEditor.this) {
						handleActivate();
					}
				});
			}
		} catch (CoreException exception) {
			EcoreEditorPlugin.INSTANCE.log(exception);
		}
	}

	protected void performSave(final Map<Object, Object> saveOptions) {
		// Save the resources to the file system.
		//
		boolean first = true;
		for (Resource resource : editingDomain.getResourceSet()
				.getResources()) {
			if ((first || !resource.getContents().isEmpty() || isPersisted(resource))
					&& !editingDomain.isReadOnly(resource)) {
				try {
					long timeStamp = resource.getTimeStamp();
					resource.save(saveOptions);
					if (resource.getTimeStamp() != timeStamp) {
						savedResources.add(resource);
					}
				} catch (Exception exception) {
					resourceToDiagnosticMap
							.put(resource,
									analyzeResourceProblems(resource,
											exception));
				}
				first = false;
			}
		}
	}

}
