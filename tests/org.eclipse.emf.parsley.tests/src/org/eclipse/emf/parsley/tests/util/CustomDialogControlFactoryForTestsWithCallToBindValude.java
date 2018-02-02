/**
 * 
 */
package org.eclipse.emf.parsley.tests.util;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.composite.DialogControlFactory;
import org.eclipse.emf.parsley.inject.parameters.CompositeParameter;
import org.eclipse.emf.parsley.inject.parameters.EObjectParameter;
import org.eclipse.emf.parsley.util.DatabindingUtil;
import org.eclipse.swt.widgets.Text;

import com.google.inject.Inject;

/**
 * Used for testing.
 * 
 * @author Lorenzo Bettini
 *
 */
public class CustomDialogControlFactoryForTestsWithCallToBindValude extends DialogControlFactory {

	@Inject
	public CustomDialogControlFactoryForTestsWithCallToBindValude(CompositeParameter compositeParameter,
			EObjectParameter eObjectParameter) {
		super(compositeParameter, eObjectParameter);
	}

	@SuppressWarnings("rawtypes")
	public Text control_BaseClass_baseClassFeature(IObservableValue observableValue, EStructuralFeature f) {
		final Text text = this.createText("");
		// by default the editable is true, thus setting it to false
		// gives us evidence that this method was called
		text.setEditable(false);
		bindValue(f, DatabindingUtil.observeText(text), observableValue);
		return text;
	}
}
