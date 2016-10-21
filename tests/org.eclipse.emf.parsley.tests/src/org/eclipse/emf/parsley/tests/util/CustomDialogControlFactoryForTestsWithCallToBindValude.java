/**
 * 
 */
package org.eclipse.emf.parsley.tests.util;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.composite.DialogControlFactory;
import org.eclipse.emf.parsley.util.DatabindingUtil;
import org.eclipse.swt.widgets.Text;

/**
 * Used for testing.
 * 
 * @author Lorenzo Bettini
 *
 */
public class CustomDialogControlFactoryForTestsWithCallToBindValude extends DialogControlFactory {
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
