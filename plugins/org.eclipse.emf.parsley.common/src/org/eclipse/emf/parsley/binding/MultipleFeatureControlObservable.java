/*******************************************************************************
 * Copyright (c) 2009, 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.binding;

import org.eclipse.core.databinding.observable.value.AbstractObservableValue;
import org.eclipse.core.databinding.observable.value.ValueDiff;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

/**
 * @author Dennis Huebner - Initial contribution and API
 * @author Lorenzo Bettini - Refactoring and adaptations
 */
public class MultipleFeatureControlObservable extends AbstractObservableValue implements ISelectionChangedListener {

	private final MultipleFeatureControl mfc;

	public MultipleFeatureControlObservable(MultipleFeatureControl mfc) {
		this.mfc = mfc;
		mfc.getInternalSelectionProvider().addSelectionChangedListener(this);
	}

	@Override
	protected Object doGetValue() {
		return mfc.getValue();
	}

	@Override
	protected void doSetValue(Object value) {
		mfc.setValue(value);
	}

	public Object getValueType() {
		return null;
	}

	public void selectionChanged(final SelectionChangedEvent event) {
		fireValueChange(new ValueDiff() {

			@Override
			public Object getOldValue() {
				return null;
			}

			@Override
			public Object getNewValue() {
				return ((IStructuredSelection) event.getSelection()).toList();
			}
		});
	}

}
