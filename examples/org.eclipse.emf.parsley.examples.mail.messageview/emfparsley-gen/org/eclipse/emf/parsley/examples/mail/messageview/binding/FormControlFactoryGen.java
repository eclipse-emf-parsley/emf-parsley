/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.examples.mail.messageview.binding;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.parsley.binding.FormControlFactory;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

@SuppressWarnings("all")
public class FormControlFactoryGen extends FormControlFactory {
  public Control control_Mail_message(final DataBindingContext dataBindingContext, final IObservableValue observableValue) {
    Control control = createControl_Mail_message();
    dataBindingContext.bindValue(
    	createTarget_Mail_message(control),
    	observableValue);
    return control;
  }
  
  protected Control createControl_Mail_message() {
    Text _xblockexpression = null;
    {
      final Text t = this.createText("", 
        SWT.MULTI, SWT.BORDER, 
        SWT.WRAP, SWT.V_SCROLL);
      t.setEditable(false);
      _xblockexpression = (t);
    }
    return _xblockexpression;
  }
  
  protected IObservableValue createTarget_Mail_message(final Control it) {
    ISWTObservableValue _observeText = SWTObservables.observeText(it, SWT.Modify);
    return _observeText;
  }
}
