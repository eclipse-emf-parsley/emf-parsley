package org.eclipse.emf.parsley.examples.maven.binding;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.composite.DialogControlFactory;
import org.eclipse.emf.parsley.util.DatabindingUtil;
import org.eclipse.swt.widgets.Control;

@SuppressWarnings("all")
public class MavenDialogControlFactory extends DialogControlFactory {
  public Control control_EClass_name(final IObservableValue observableValue, final EStructuralFeature feature) {
    Control control = createControl_EClass_name();
    bindValue(
    	feature,
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
