/**
 * 
 */
package org.eclipse.emf.parsley.ui.provider;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * Provides labels for EStructuralFeatures for FormToolkit. With respect to the
 * superclass {@link FeatureCaptionProvider} you can also specify the Label,
 * besides its text.  If a custom PropertyDescriptionProvider is provided (through
 * injection) then it tries to get the text also from that one, before
 * using the default text.
 * 
 * @author Lorenzo Bettini - Initial Contribution and API
 * 
 */
public class FormFeatureCaptionProvider extends DialogFeatureCaptionProvider {

	protected FormToolkit formToolkit;

	@Override
	protected Label createLabel(Composite parent, String text) {
		Label lab = formToolkit.createLabel(parent, text);
		lab.setLayoutData(new GridData());
		return lab;
	}

	public FormToolkit getFormToolkit() {
		return formToolkit;
	}

	public void setFormToolkit(FormToolkit formToolkit) {
		this.formToolkit = formToolkit;
	}

}