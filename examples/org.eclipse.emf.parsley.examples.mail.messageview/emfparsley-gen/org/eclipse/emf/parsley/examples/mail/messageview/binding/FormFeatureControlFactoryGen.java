package org.eclipse.emf.parsley.examples.mail.messageview.binding;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.parsley.binding.FormControlFactory;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

@SuppressWarnings("all")
public class FormFeatureControlFactoryGen extends FormControlFactory {
  public Control control_Mail_message(final DataBindingContext dataBindingContext, final IObservableValue observableValue) {
    Control control = createControl_Mail_message();
    dataBindingContext.bindValue(
    	createTarget_Mail_message(control),
    	observableValue);
    return control;
  }
  
  protected Control createControl_Mail_message() {
    FormToolkit _toolkit = this.getToolkit();
    Composite _parent = this.getParent();
    int _bitwiseOr = (SWT.MULTI | SWT.BORDER);
    int _bitwiseOr_1 = (_bitwiseOr | SWT.WRAP);
    int _bitwiseOr_2 = (_bitwiseOr_1 | SWT.V_SCROLL);
    Text _createText = _toolkit.createText(_parent, "", _bitwiseOr_2);
    return _createText;
  }
  
  protected IObservableValue createTarget_Mail_message(final Control it) {
    ISWTObservableValue _observeText = SWTObservables.observeText(it, SWT.Modify);
    return _observeText;
  }
}
