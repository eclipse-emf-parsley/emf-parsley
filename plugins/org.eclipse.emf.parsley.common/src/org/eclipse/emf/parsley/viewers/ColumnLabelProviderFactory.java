/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.viewers;


import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.ui.provider.AdapterMapCellLabelProvider;
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Lorenzo Bettini
 * 
 * factory for ColumnLabelProvider
 * 
 */
public class ColumnLabelProviderFactory {

	@Inject
	protected Provider<ILabelProvider> labelProviderProvider;

	@Inject
	protected Provider<TableColumnLabelProvider> tableColumnProviderProvider;

	public ColumnLabelProvider createColumnLabelProvider(
			EStructuralFeature eStructuralFeature) {
		TableColumnLabelProvider columnProvider = tableColumnProviderProvider
				.get();
		columnProvider.seteStructuralFeature(eStructuralFeature);
		return columnProvider;
	}

	public CellLabelProvider createColumnLabelProvider(
			EStructuralFeature eStructuralFeature,
			IStructuredContentProvider contentProvider) {
		if (contentProvider instanceof ObservableListContentProvider) {
			return createColumnLabelProvider(eStructuralFeature,
					(ObservableListContentProvider) contentProvider);
		} else {
			return createColumnLabelProvider(eStructuralFeature);
		}
	}

	public CellLabelProvider createColumnLabelProvider(
			EStructuralFeature eStructuralFeature,
			ObservableListContentProvider cp) {
		IObservableMap observableMap = EMFProperties.value(eStructuralFeature)
				.observeDetail(cp.getKnownElements());
		return new AdapterMapCellLabelProvider(observableMap,
				labelProviderProvider.get());
	}

}
