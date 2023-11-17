/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.viewers;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.emf.parsley.edit.provider.AdapterFactoryHelper;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

/**
 * Factory for {@link CellEditor}.
 *
 * This default implementation uses EMF {@link PropertyDescriptor}.
 *
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class TableViewerCellEditorFactory {

	@Inject
	private ILabelProvider injectedLabelProvider;

	@Inject
	private AdapterFactoryHelper adapterFactoryHelper;

	public CellEditor createCellEditor(Composite composite, Object element, EStructuralFeature eStructuralFeature) {
		if (!(element instanceof Notifier)) {
			return null;
		}

		Notifier notifier = (Notifier) element;
		IItemPropertyDescriptor itemPropertyDescriptor = adapterFactoryHelper.getItemPropertyDescriptor(notifier,
				eStructuralFeature);

		if (itemPropertyDescriptor == null) {
			return null;
		}

		PropertyDescriptor propertyDescriptor = new PropertyDescriptor(element, itemPropertyDescriptor) {
			@Override
			public ILabelProvider getLabelProvider() {
				return injectedLabelProvider;
			}
		};

		return propertyDescriptor.createPropertyEditor(composite);
	}

}