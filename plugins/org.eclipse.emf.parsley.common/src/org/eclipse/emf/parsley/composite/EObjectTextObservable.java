/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.htmlù
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.composite;

import org.eclipse.core.databinding.observable.value.AbstractObservableValue;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Text;

/**
 * To perform data binding between a Text and an EObject (representing
 * a reference)
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
@SuppressWarnings("rawtypes")
public class EObjectTextObservable extends AbstractObservableValue {
	
	private ILabelProvider labelProvider;
	
	private Text text;
	
	private EObject current;

	public EObjectTextObservable(ILabelProvider labelProvider, Text text) {
		super();
		this.labelProvider = labelProvider;
		this.text = text;
	}

	@Override
	public Object getValueType() {
		return null;
	}

	@Override
	protected Object doGetValue() {
		return current;
	}
	
	@Override
	protected void doSetValue(Object value) {
		if (!(value instanceof EObject)) {
			return;
		}
		
		current = (EObject) value;
		
		text.setText(labelProvider.getText(current));
	}

}
