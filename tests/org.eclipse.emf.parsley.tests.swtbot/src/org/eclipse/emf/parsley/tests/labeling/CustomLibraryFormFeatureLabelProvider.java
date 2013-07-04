/**
 * 
 */
package org.eclipse.emf.parsley.tests.labeling;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.ui.provider.FormPropertyDescriptionProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.IFormColors;

/**
 * Note that we will bind also a {@link CustomLibraryFeatureLabelProvider} so we
 * will use automatically also its customizations.
 * 
 * @author bettini
 * 
 */
public class CustomLibraryFormFeatureLabelProvider extends
		FormPropertyDescriptionProvider {

	/**
	 * This will have the precedence over the one in
	 * {@link CustomLibraryFeatureLabelProvider}
	 */
	public String text_Person_lastName(EStructuralFeature feature) {
		return "Last name";
	}

	public Label label_Writer_name(Composite parent, EStructuralFeature feature) {
		Label label = defaultLabel(parent, feature);
		label.setBackground(getFormToolkit().getColors().getColor(
				IFormColors.TITLE));
		return label;
	}
}
