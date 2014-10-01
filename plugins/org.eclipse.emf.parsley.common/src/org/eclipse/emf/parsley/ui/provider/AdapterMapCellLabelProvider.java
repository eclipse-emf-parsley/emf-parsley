/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.ui.provider;

import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.jface.databinding.viewers.ObservableMapCellLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

public class AdapterMapCellLabelProvider extends ObservableMapCellLabelProvider {

	ILabelProvider wrappedLabelProvider;
	
	public AdapterMapCellLabelProvider(IObservableMap attributeMap, ILabelProvider wrappedLabelProvider) {
		super(attributeMap);
		this.wrappedLabelProvider=wrappedLabelProvider;
	}
	
	
	@Override
	public void update(ViewerCell cell) {
		Object element = cell.getElement();
		Object value = attributeMaps[0].get(element);
		// TODO To finish
		cell.setText(wrappedLabelProvider.getText(value)); 
	}
	

}
