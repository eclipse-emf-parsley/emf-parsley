package org.eclipse.emf.parsley.dsl.ui.wizard.template;

/**
 * @author Francesco Guidieri
 * 
 */
public class SaveableTableFormTemplateWizardHelper extends SaveableTableTemplateWizardHelper{
	
	public static final SaveableTableFormTemplateWizardHelper singlethon=new SaveableTableFormTemplateWizardHelper();
	
	public String getLabel() {
		return "Saveable Table Form View";
	}

	@Override
	public String getPostFix() {
		return "TableFormView";
	}

	@Override
	public String getDescription() {
		return "<p>This wizard creates a plug-in that contains a view with a <b>table form</b> component.</p>"
				+ "<p>This view read the content form a resource and can save changes.</p>"
				+ "<p><b>The user must specify:</b></p>"
				+ "<li>the resource URI</li>"
				+ "<li>how to reach the contents from the resource</li>"
				+ "<li>the EClass to be represented</li>";
	}

}
