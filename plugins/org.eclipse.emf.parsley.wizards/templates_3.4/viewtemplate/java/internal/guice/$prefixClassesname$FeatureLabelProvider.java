package $packageName$.internal.guice;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import org.eclipse.emf.parsley.ui.provider.FormPropertyDescriptionProvider;

public class $prefixClassesname$FeatureLabelProvider extends FeatureLabelProvider{
	
	/**
	 * 
	 * @param feature
	 * @return
	 */
	public String text_$prefixClassesname$_code(EStructuralFeature feature) {
		// TODO Auto-generated method stub
		return "Code: ";
	}
	
	/**
	 * 
	 * @param parent
	 * @param feature
	 * @return
	 */
	public Label label_$prefixClassesname$_description(Composite parent, EStructuralFeature feature) {
		// TODO Auto-generated method stub
		return new Label(parent, SWT.NONE);
	}

	
}
