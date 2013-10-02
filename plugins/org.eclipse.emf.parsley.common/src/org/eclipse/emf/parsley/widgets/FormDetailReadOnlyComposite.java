/**
 * 
 */
package org.eclipse.emf.parsley.widgets;

import org.eclipse.emf.parsley.binding.FormControlFactory;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

/**
 * A version of the form where all controls are by default readonly
 * controls.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class FormDetailReadOnlyComposite extends FormDetailComposite {

	public FormDetailReadOnlyComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	@Inject
	public void setFormControlFactory(FormControlFactory formControlFactory) {
		super.setFormControlFactory(formControlFactory);
		this.formControlFactory.setReadonly(true);
	}

}
