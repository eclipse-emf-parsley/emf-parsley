package org.eclipse.emf.parsley.dsl.ui.wizard.template;

/**
 * @author Francesco Guidieri
 * 
 */
public class SaveableTreeFormTemplateWizardHelper extends SaveableTreeTemplateWizardHelper{
	
	public static final SaveableTreeFormTemplateWizardHelper singlethon=new SaveableTreeFormTemplateWizardHelper();
	
	public String getLabel() {
		return "Saveable Tree Form View";
	}

	@Override
	public String getPostFix() {
		return "TreeFormView";
	}

	@Override
	public String getDescription() {
		return "<p>This wizard creates a plug-in that contains a view with a <b>tree form</b> component.</p>"
				+ "<p>This view read the content form a resource and can save changes.</p>"
				+ "<p><b>The user must specify:</b></p>"
				+ "<li>the resource URI</li>";
	}
	
}
