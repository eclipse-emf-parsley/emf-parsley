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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Lorenzo Bettini, Francesco Guidieri
 *
 *         factory for ColumnLabelProvider
 *
 */
public class ColumnLabelProviderFactory {

	@Inject
	private Provider<TableColumnLabelProvider> tableColumnProviderProvider;

	public ColumnLabelProvider createColumnLabelProvider(EStructuralFeature eStructuralFeature) {
		TableColumnLabelProvider columnProvider = tableColumnProviderProvider.get();
		columnProvider.seteStructuralFeature(eStructuralFeature);
		return columnProvider;
	}

}
