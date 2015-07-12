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

package org.eclipse.emf.parsley.composite;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.swt.widgets.Control;

/**
 * @author Lorenzo Bettini
 *
 */
public class ControlObservablePair {

	protected Control control;
	
	protected IObservableValue observableValue;
	
	public ControlObservablePair() {
	}
	
	public ControlObservablePair(Control control, IObservableValue observableValue) {
		this.control = control;
		this.observableValue = observableValue;
	}
	
	public Control getControl() {
		return control;
	}

	public void setControl(Control control) {
		this.control = control;
	}

	public IObservableValue getObservableValue() {
		return observableValue;
	}

	public void setObservableValue(IObservableValue observableValue) {
		this.observableValue = observableValue;
	}

}
