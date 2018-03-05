/*******************************************************************************
 * Copyright (c) 2018 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.composite;

import org.eclipse.emf.parsley.inject.AfterInject;
import org.eclipse.emf.parsley.inject.EmfParsleyLifecycle;
import org.eclipse.emf.parsley.inject.InjectableComposite;
import org.eclipse.emf.parsley.inject.parameters.CompositeParameters;
import org.eclipse.emf.parsley.viewers.IStructuredViewerProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.StructuredViewer;

/**
 * A specialization of {@link AbstractMasterComposite} based on a
 * {@link StructuredViewer}.
 * 
 * @author Lorenzo Bettini
 * @since 2.0
 * 
 */
@EmfParsleyLifecycle
public abstract class AbstractViewerMasterComposite<T extends StructuredViewer> extends InjectableComposite
		implements IStructuredViewerProvider<T>, IMasterComposite {

	private T structuredViewer;

	public AbstractViewerMasterComposite(CompositeParameters params) {
		super(params);
	}

	/**
	 * Called after the construction of the whole instance has finished.
	 */
	@AfterInject
	private void setupViewer() {
		this.structuredViewer = createStructuredViewer();
	}

	abstract protected T createStructuredViewer();

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener selectionChangedListener) {
		getViewer().addSelectionChangedListener(selectionChangedListener);
	}

	@Override
	public T getViewer() {
		return structuredViewer;
	}

	@Override
	public void update(Object contents) {
		structuredViewer.setInput(contents);
	}
}
