package org.eclipse.emf.parsley.examples.mail.messageview.binding;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.composite.FormControlFactory;
import org.eclipse.emf.parsley.util.DatabindingUtil;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

@SuppressWarnings("all")
public class MessageviewFormControlFactory extends FormControlFactory {
  public Control control_Mail_message(final IObservableValue observableValue, final EStructuralFeature feature) {
    Control control = createControl_Mail_message();
    bindValue(
    	feature,
    	createTarget_Mail_message(control),
    	observableValue);
    return control;
  }

  protected Control createControl_Mail_message() {
    final Text t = this.createText("", 
      SWT.MULTI, SWT.BORDER, 
      SWT.WRAP, SWT.V_SCROLL);
    t.setEditable(false);
    GridData _gridData = new GridData(GridData.FILL_BOTH);
    t.setLayoutData(_gridData);
    return t;
  }

  protected IObservableValue createTarget_Mail_message(final Control it) {
    ISWTObservableValue _observeText = DatabindingUtil.observeText(it, SWT.Modify);
    return _observeText;
  }
}
