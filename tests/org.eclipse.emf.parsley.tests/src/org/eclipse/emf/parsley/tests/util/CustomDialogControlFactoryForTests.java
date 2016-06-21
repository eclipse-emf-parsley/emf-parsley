/**
 * 
 */
package org.eclipse.emf.parsley.tests.util;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.parsley.composite.DialogControlFactory;
import org.eclipse.emf.parsley.util.DatabindingUtil;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

/**
 * Used for testing.
 * 
 * @author Lorenzo Bettini
 *
 */
public class CustomDialogControlFactoryForTests extends DialogControlFactory {
	@SuppressWarnings("rawtypes")
	public Text control_BaseClass_baseClassFeature(final EMFDataBindingContext edbc,
			final IObservableValue modelObservableValue) {
		final Text text = this.createText("");
		text.setEditable(false);
		final ISWTObservableValue targetObservableValue = DatabindingUtil.observeText(text, SWT.Modify);
		edbc.bindValue(targetObservableValue, modelObservableValue, null, null);
		return text;
	}
}
