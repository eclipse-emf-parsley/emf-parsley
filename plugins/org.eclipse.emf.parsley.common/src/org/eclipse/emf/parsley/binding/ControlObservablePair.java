/**
 * 
 */
package org.eclipse.emf.parsley.binding;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.swt.widgets.Control;

/**
 * @author bettini
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
