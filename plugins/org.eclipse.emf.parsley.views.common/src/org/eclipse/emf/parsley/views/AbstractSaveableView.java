/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.views;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.parsley.listeners.AsyncCommandStackListenerClient;
import org.eclipse.emf.parsley.listeners.AsyncCommandStackListenerHelper;
import org.eclipse.emf.parsley.resource.ResourceLoader;
import org.eclipse.emf.parsley.resource.ResourceSaveStrategy;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.part.ViewPart;

import com.google.inject.Inject;
import com.google.inject.Provider;

public abstract class AbstractSaveableView extends ViewPart implements ISaveablePart, IEditingDomainProvider, AsyncCommandStackListenerClient {
	private Resource resource;

	private boolean dirty;

	private static final Logger LOGGER = Logger.getLogger(AbstractSaveableView.class);

	@Inject
	private Provider<AdapterFactoryEditingDomain> editingDomainProvider;

	@Inject
	private ResourceLoader resourceLoader;

	@Inject
	private ResourceSaveStrategy resourceSaveStrategy;

	@Inject
	private AsyncCommandStackListenerHelper commandStackListenerHelper;

	protected AdapterFactoryEditingDomain editingDomain;

	@Override
	public void createPartControl(Composite parent) {
		editingDomain = editingDomainProvider.get();
		resource = loadResource(createResourceURI());
		commandStackListenerHelper.addCommandStackListener(editingDomain, getSite().getWorkbenchWindow().getShell(),
				this, resource);
	}

	protected abstract URI createResourceURI();

	protected Resource loadResource(URI uri) {
		return resourceLoader.getResource(editingDomain, uri).getResource();
	}

	/**
	 * It is called when the {@link CommandStack} changed and the change
	 * concerns something which is in our {@link Resource}.
	 * 
	 * @param mostRecentCommand
	 */
	@Override
	public void mostRecentCommandAffectsResource(Command mostRecentCommand) {
		setDirtyAndFirePropertyChange(true);
	}

	protected void setDirtyAndFirePropertyChange(boolean dirtyState) {
		setDirty(dirtyState);
		firePropertyChange(PROP_DIRTY);
	}

	protected Resource getResource() {
		return resource;
	}

	@Override
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		try {
			saveResourceAndUpdateDirtyState();
		} catch (IOException e) {
			LOGGER.error("doSave", e);
		}
	}

	protected void saveResourceAndUpdateDirtyState() throws IOException {
		if (resourceSaveStrategy.save(resource)) {
			setDirtyAndFirePropertyChange(false);
		}
	}

	@Override
	public boolean isDirty() {
		return dirty;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public boolean isSaveOnCloseNeeded() {
		return false;
	}

}
