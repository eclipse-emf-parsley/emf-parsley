package org.eclipse.emf.parsley.examples.maven.binding;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.parsley.composite.DialogControlFactory;
import org.eclipse.emf.parsley.util.DatabindingUtil;
import org.eclipse.swt.widgets.Control;

@SuppressWarnings("all")
public class MavenDialogControlFactory extends DialogControlFactory {
  public Control control_EClass_name(final DataBindingContext dataBindingContext, final IObservableValue observableValue) {
    Control control = createControl_EClass_name();
    dataBindingContext.bindValue(
    	createTarget_EClass_name(control),
    	observableValue);
    return control;
  }
  
  protected Control createControl_EClass_name() {
    return this.createText("");
  }
  
  protected IObservableValue createTarget_EClass_name(final Control it) {
    return DatabindingUtil.observeText(it);
  }
}
